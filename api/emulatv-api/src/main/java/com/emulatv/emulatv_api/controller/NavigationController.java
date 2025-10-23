package com.emulatv.emulatv_api.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.emulatv.emulatv_api.util.KeySimulator;

@RestController
@RequestMapping("/navigation")
@CrossOrigin(origins = "*")
public class NavigationController {
        private KeySimulator keySimulator;

        public NavigationController(KeySimulator keySimulator) {
            this.keySimulator = keySimulator;
        }

        @GetMapping("/next")
        public void next() {
            keySimulator.getRequest("/navigation/next");
        }

                @GetMapping("/previous")
        public void previous() {
            keySimulator.getRequest("/navigation/previous");
        }

                @GetMapping("/enter")
        public void enter() {
            keySimulator.getRequest("/navigation/enter");
        }

                @GetMapping("/escape")
        public void escape() {
            keySimulator.getRequest("/navigation/escape");
        }
}
