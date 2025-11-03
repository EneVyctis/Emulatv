package com.emulatv.emulatv_api.util;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Component
public class AgentHandler {

    private static final Logger log = LoggerFactory.getLogger(AgentHandler.class);
    private final RestTemplate restTemplate;
    private final String baseUrl;

    public AgentHandler(@Value("${agent.base-url:http://emulatv-nginx/agent/}") String baseUrl) {
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

    public ResponseEntity<String> postForEntityRequest(String path, HttpEntity<MultiValueMap<String, Object>> requestEntity) {
        String fullUrl = baseUrl + path;
        ResponseEntity<String> response;
        try{
            response = restTemplate.postForEntity(fullUrl, requestEntity, String.class);
        } catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error while transferring to agent");
        }
        return response;
    }
}
