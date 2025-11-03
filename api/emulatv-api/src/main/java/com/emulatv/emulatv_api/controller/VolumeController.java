package com.emulatv.emulatv_api.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.emulatv.emulatv_api.util.AgentHandler;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/navigation")
@CrossOrigin(origins = "*")
public class VolumeController {
    private AgentHandler agentHandler;

    public VolumeController(AgentHandler agentHandler){
        this.agentHandler = agentHandler;
    }

    @GetMapping("/volume/up")
    public void volumeUp() {
        agentHandler.getRequest("/navigation/volume/up");
    }

    @GetMapping("/volume/down")
    public void volumeDown() {
        agentHandler.getRequest("/navigation/volume/down");
    }
    
    
}
