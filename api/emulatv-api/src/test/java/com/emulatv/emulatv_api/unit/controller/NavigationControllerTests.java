package com.emulatv.emulatv_api.unit.controller;

import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.emulatv.emulatv_api.controller.NavigationController;
import com.emulatv.emulatv_api.util.AgentHandler;

public class NavigationControllerTests {
    
    @Mock
    private AgentHandler agentHandler;

    @InjectMocks
    private NavigationController navigationController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void next_ShouldCallAgentHandlerWithCorrectPath(){
        navigationController.next();

        verify(agentHandler).getRequest("/navigation/next");
    }

    @Test
    void previous_ShouldCallAgentHandlerWithCorrectPath(){
        navigationController.previous();

        verify(agentHandler).getRequest("/navigation/previous");
    }

    @Test
    void enter_ShouldCallAgentHandlerWithCorrectPath(){
        navigationController.enter();

        verify(agentHandler).getRequest("/navigation/enter");
    }

    @Test
    void escape_ShouldCallAgentHandlerWithCorrectPath(){
        navigationController.escape();

        verify(agentHandler).getRequest("/navigation/escape");
    }
}
