package com.emulatv.emulatv_api.unit.controller;

import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.emulatv.emulatv_api.util.AgentHandler;
import com.emulatv.emulatv_api.controller.VolumeController;

public class VolumeControllerTest {
    
    @Mock
    private AgentHandler agentHandler;

    @InjectMocks
    private VolumeController volumeController;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void volumeUp_ShouldCallAgentHandlerWithCorrectPath() {

        volumeController.volumeUp();

        verify(agentHandler).getRequest("/navigation/volume/up");
    }

    @Test
    void volumeDown_ShouldCallAgentHandlerWithCorrectPath() {

        volumeController.volumeDown();

        verify(agentHandler).getRequest("/navigation/volume/down");
    }
}
