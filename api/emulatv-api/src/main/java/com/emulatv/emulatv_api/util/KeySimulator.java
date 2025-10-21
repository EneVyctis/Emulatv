package com.emulatv.emulatv_api.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class KeySimulator {

    private final String agentIp;
    private final String agentPort;
    private final RestTemplate restTemplate;

    public KeySimulator(
            @Value("${node.api.ip}") String agentIp,
            @Value("${node.api.port}") String agentPort
    ) {
        this.agentIp = agentIp;
        this.agentPort = agentPort;
        this.restTemplate = new RestTemplate();
    }

    private String getAgentUrl() {
        return "http://" + agentIp + ":" + agentPort;
    }

    public void getRequest(String url) {
        restTemplate.getForObject(getAgentUrl() + url, String.class);
    }
}
