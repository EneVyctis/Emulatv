package com.emulatv.emulatv_api.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.emulatv.emulatv_api.util.AgentHandler;

@RestController
@RequestMapping("/navigation")
@CrossOrigin(origins = "*")
public class NavigationController {
        private AgentHandler agentHandler;

        public NavigationController(AgentHandler agentHandler) {
            this.agentHandler = agentHandler;
        }

        @GetMapping("/next")
        public void next() {
            agentHandler.getRequest("/navigation/next");
        }

                @GetMapping("/previous")
        public void previous() {
            agentHandler.getRequest("/navigation/previous");
        }

                @GetMapping("/enter")
        public void enter() {
            agentHandler.getRequest("/navigation/enter");
        }

                @GetMapping("/escape")
        public void escape() {
            agentHandler.getRequest("/navigation/escape");
        }
}
