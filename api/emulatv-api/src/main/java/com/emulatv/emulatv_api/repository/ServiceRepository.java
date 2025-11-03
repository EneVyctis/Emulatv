package com.emulatv.emulatv_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.emulatv.emulatv_api.model.Service;

public interface ServiceRepository extends JpaRepository<Service, Long>{
    
}
