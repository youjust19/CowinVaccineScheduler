package com.pj.CowinVaccineTracker.service;

import com.pj.CowinVaccineTracker.dto.OTPRequest;
import com.pj.CowinVaccineTracker.dto.OTPResponse;
import com.pj.CowinVaccineTracker.dto.ScheduleRequest;
import com.pj.CowinVaccineTracker.dto.Session;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import static com.pj.CowinVaccineTracker.helper.Restheaders.getRequestHeaders;

@Component
public class SchedulingService {

    @Value( "${cowinapi.scheduleUrl}" )
    private String scheduleUrl;
    @Value("#{'${user.beneficiaries}'.split(',')}")
    private List<String> beneficiaries;

    private static Log log = LogFactory.getLog(SchedulingService.class);

    @Autowired
    private RestTemplate restTemplate;

    private Map<String, Session> sessionMap = new HashMap<>();

    private Stack<Session> sessionStack =  new Stack<>();
    public String schedulewithCaptcha(String session_id,String captcha, String token) {
        if(sessionStack.isEmpty()){
            log.error("NO CURRENT SESSION AVAILABLE");
            return "NO CURRENT SESSION AVAILABLE";
        }
        Session currentSession = sessionStack.pop();


        log.info("Sending Request for Session: "+ currentSession);

        String slot = currentSession.getSlots().size()>1 ?currentSession.getSlots().get(1) : currentSession.getSlots().get(0);

        ScheduleRequest request = new ScheduleRequest(1, currentSession.getCenter_id(),
                                                    currentSession.getSession_id(), slot, beneficiaries, captcha);
        HttpEntity<ScheduleRequest> requestEntity = new HttpEntity<>(request, getRequestHeaders(token));
        try {
            String body = restTemplate.postForEntity(scheduleUrl, requestEntity, String.class).getBody();
            log.info(body);
            return body;
        } catch (RestClientException e) {
            log.error(e.getMessage());
            return e.getMessage();
        }
    }

    public void addToSessionMap(String key, Session session) {
        this.sessionMap.put(key, session);
    }

    public void clearSessionMap(){
        this.sessionMap.clear();
    }

    public void addToSessionStack(Session session) {
        this.sessionStack.push(session);
    }
}
