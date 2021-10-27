package com.surbhi.decathlon.service;

import com.surbhi.decathlon.model.Sms;
import com.surbhi.decathlon.model.repository.Developer;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AlertService {

    public static String SMS_URL = "http://run.mocky.io/v3/fd99c100-f88a-4d70-aaf7-393dbbd5d99f";

    public void notify(Developer developer) throws Exception {
        Sms sms = new Sms();
        sms.setPhoneNumber(developer.getPhoneNumber());
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Sms> request = new HttpEntity<>(sms);
        ResponseEntity response = restTemplate.postForEntity(SMS_URL, request, String.class);
        if (HttpStatus.OK != response.getStatusCode()) {
            throw new Exception("call to external SMS service failed");
        }
    }
}
