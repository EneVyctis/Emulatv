package com.emulatv.emulatv_api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import com.emulatv.emulatv_api.model.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@CrossOrigin(origins = "*")
public class ServicesController {
    private Service[] services = {	
        new Service(0,"Netflix","https://www.netflix.com"),
	    new Service(1, "Youtube", "https://www.youtube.com/"),
	    new Service(2, "Crunchyroll", "https://www.crunchyroll.com/"),
	    new Service(3, "Disney+", "https://www.disneyplus.com/fr-fr"),
    };

    @GetMapping("/list/services")
    public Service[] getServices(){
        // Implicitely uses Jackson to return a json
        return services;
    }
    
    @GetMapping("list/service/{id}")
    public ResponseEntity<Service> getServiceById(@PathVariable int id) {
        if(id<0 || id >= services.length){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(services[id]);
    }

}
