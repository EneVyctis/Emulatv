package com.emulatv.emulatv_api.util;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class KeySimulator {

    private static final Logger log = LoggerFactory.getLogger(KeySimulator.class);
    private final RestTemplate restTemplate;
    private final String baseUrl;

    public KeySimulator(@Value("${agent.base-url:http://emulatv-nginx/agent/}") String baseUrl) {
        this.restTemplate = new RestTemplate();
        this.baseUrl = baseUrl.endsWith("/") ? baseUrl : baseUrl + "/";
    }
    
    public String getRequest(String path) {
        String fullUrl = baseUrl + path;
        try {
            String response = restTemplate.getForObject(fullUrl, String.class);
            log.info("Agent response from {}: {}", fullUrl, response);
            return response;
        } catch (Exception e) {
            log.error("Error calling agent at {}: {}", fullUrl, e.getMessage());
            return null;
        }
    }
}
