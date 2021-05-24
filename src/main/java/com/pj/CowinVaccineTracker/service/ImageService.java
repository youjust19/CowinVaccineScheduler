package com.pj.CowinVaccineTracker.service;

import com.pj.CowinVaccineTracker.dto.ScheduleRequest;
import lombok.SneakyThrows;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import static com.pj.CowinVaccineTracker.helper.Restheaders.getRequestHeaders;

@Component
public class ImageService {

    @Value( "${cowinapi.captchaURl}" )
    private String captchaURl;
    private static Log log = LogFactory.getLog(ImageService.class);
    @Autowired
    private RestTemplate restTemplate;

    public String getCaptcha(String token) {

        HttpEntity<ScheduleRequest> requestEntity = new HttpEntity<>(null, getRequestHeaders(token));
        try {
            String body = restTemplate.postForEntity(captchaURl, requestEntity, String.class).getBody();
            log.info(body);
            return body;
        } catch (RestClientException e) {
            log.error(e.getMessage());
            return e.getMessage();
        }
    }
}
