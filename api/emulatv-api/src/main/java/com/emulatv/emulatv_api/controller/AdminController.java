package com.emulatv.emulatv_api.controller;

import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.emulatv.emulatv_api.model.Service;
import com.emulatv.emulatv_api.repository.ServiceRepository;
import com.emulatv.emulatv_api.util.AgentHandler;
import com.emulatv.emulatv_api.util.MultipartInputStreamFileResource;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping("/admin")
@CrossOrigin(origins = "*")
public class AdminController {
    
    @Autowired
    private ServiceRepository serviceRepository;
    private AgentHandler agentHandler;

    public AdminController(AgentHandler agentHandler){
        this.agentHandler = agentHandler;
    }
    
    RestTemplate restTemplate = new RestTemplate();

    @PostMapping("/upload-service")
    public ResponseEntity<?> postUploadService(
        @RequestParam String name, 
        @RequestParam String website,
        @RequestParam("icon") MultipartFile iconFile) {
        
        if (iconFile.isEmpty()){
            return ResponseEntity.badRequest().body("No file found");
        }

        if(!"image/svg+xml".equals(iconFile.getContentType())) {
            return ResponseEntity.badRequest().body("File must be an svg");
        }

        String fileName = name + ".svg";
        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        try{
            body.add("icon", new MultipartInputStreamFileResource(iconFile.getInputStream(), fileName));
        } catch (IOException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error reading the file");
        }

        body.add("name", name);
        body.add("website", website);

        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body);
        ResponseEntity<String> response = agentHandler.postForEntityRequest("/upload-service", requestEntity);

        if ( !response.getStatusCode().is2xxSuccessful()){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Agent has not accepted the file" + response.getBody());
        }

        Service service = new Service();
        service.setName(name);
        service.setWebsite(website);
        serviceRepository.save(service);

        return ResponseEntity.ok("Service has been successfully added !");
    }
    
}
