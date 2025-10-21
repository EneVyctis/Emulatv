package com.emulatv.emulatv_api.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.emulatv.emulatv_api.util.KeySimulator;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/navigation")
@CrossOrigin(origins = "*")
public class VolumeController {
    private KeySimulator keySimulator;

    public VolumeController(KeySimulator keySimulator){
        this.keySimulator = keySimulator;
    }

    @GetMapping("/volume/up")
    public void volumeUp() {
        keySimulator.getRequest("/navigation/volume/up");
    }

    @GetMapping("/volume/down")
    public void volumeDown() {
        keySimulator.getRequest("/navigation/volume/down");
    }
    
    
}
