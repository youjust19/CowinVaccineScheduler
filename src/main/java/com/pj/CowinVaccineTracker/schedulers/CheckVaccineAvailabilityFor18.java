package com.pj.CowinVaccineTracker.schedulers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.Filter;
import com.jayway.jsonpath.JsonPath;

import static com.jayway.jsonpath.JsonPath.parse;
import static com.jayway.jsonpath.Criteria.where;
import static com.jayway.jsonpath.Filter.filter;
import static com.pj.CowinVaccineTracker.helper.Restheaders.getRequestHeaders;

import com.pj.CowinVaccineTracker.AudioInputPlay;
import com.pj.CowinVaccineTracker.dto.Session;
import com.pj.CowinVaccineTracker.service.OTPService;
import com.pj.CowinVaccineTracker.service.SchedulingService;
import com.twilio.rest.events.v1.sink.SinkTest;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.logging.Logger;

@Component
public class CheckVaccineAvailabilityFor18 {

    @Value( "${user.districtcode}" )
    private String districtCode;
    @Value( "${user.age}" )
    private Integer age;
    @Value( "${user.dose}" )
    private Integer dose;
    @Value("#{'${user.trackforDays}'.split(',')}")
    private List<Integer> trackforDays;

    @Value( "${cowinapi.findByDistrictUrl}" )
    private String findByDistrictUrl;

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private SchedulingService schedulingService;
    @Autowired
    private OTPService otpService;

    private static Log log = LogFactory.getLog(CheckVaccineAvailabilityFor18.class);

    @Scheduled(cron = "0/10 * *  * * ? ")
    public void checkForAvailability() {

        trackforDays.forEach( day -> {
            String dayString = getFormattedDate(day);
            DocumentContext daysResponse =getResponseForDate(dayString);
            String doseNum = "available_capacity_dose"+dose;
            log.info("Tracking for date: "+ dayString + " Dose: "+ dose);
            trackForAge(daysResponse, age, doseNum);
        });
    }

    private DocumentContext getResponseForDate(String date){
        ResponseEntity<String> responseEntity = null;
        String responseBody = null;
        try {
            String url = findByDistrictUrl+"?district_id=" + districtCode + "&date=" ;
            HttpEntity<String> requestEntity = new HttpEntity<>(null, getRequestHeaders());
            responseEntity = restTemplate.exchange(url+ date, HttpMethod.GET, requestEntity, String.class);
        } catch (RestClientException e) {
            log.error(e.getMessage());
            return  JsonPath.parse("{\"sessions\": []}");
        }
        responseBody = responseEntity.getBody();
        return JsonPath.parse(responseBody);
    }

    private void trackForAge(DocumentContext parsedBody, int age, String availableCapacityDoseNo){
        Filter filter = filter(where("min_age_limit").is(age).and(availableCapacityDoseNo).gt(1D));
        List availableList = parsedBody.read("$.sessions[?]", List.class, filter);
        printListData(availableList, age,  availableCapacityDoseNo);
    }

    private void printListData(List list,int age, String availableCapacityDoseNo) {
        if (list.size() > 0) {
            list.forEach( item -> {
                LinkedHashMap<String, Object> foundItem = (LinkedHashMap<String, Object>) item;
                log.info( " ====" +List.of(foundItem.get("pincode"),
                                           foundItem.get("available_capacity_dose1"),
                                           foundItem.get("available_capacity_dose2"),
                                           foundItem.get("min_age_limit")));
                sendMessgeAndPopulateSessionData(item, age, availableCapacityDoseNo);
            });
            new AudioInputPlay().raiseAlarm();
            log.info(list);
        }else{
            schedulingService.clearSessionMap();
        }
    }

    private void sendMessgeAndPopulateSessionData(Object item, int age, String availableCapacityDoseNo) {
        Session session = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false).convertValue(item, Session.class);
        String key = availableCapacityDoseNo+"_"+age;
        schedulingService.addToSessionStack(session);
        schedulingService.addToSessionMap(session.getSession_id(), session);
    }
    private static String getFormattedDate(int days) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDateTime now = LocalDateTime.now().plusDays(days);;
        return dtf.format(now);
    }
}
