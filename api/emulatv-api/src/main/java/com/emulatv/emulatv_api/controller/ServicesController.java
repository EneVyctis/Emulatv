package com.emulatv.emulatv_api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import com.emulatv.emulatv_api.model.Service;
import com.emulatv.emulatv_api.repository.ServiceRepository;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@CrossOrigin(origins = "*")
public class ServicesController {
    private final ServiceRepository serviceRepository;

    public ServicesController(ServiceRepository serviceRepository){
        this.serviceRepository = serviceRepository;
    }

    @GetMapping("/list/services")
    public List<Service> getServices(){
        // Implicitely uses Jackson to return a json
        return serviceRepository.findAll();
    }
    
    @GetMapping("/list/services/{id}")
    public ResponseEntity<Optional<Service>> getServiceById(@PathVariable long id) {
        if(id<0){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(serviceRepository.findById(id));
    }

}
