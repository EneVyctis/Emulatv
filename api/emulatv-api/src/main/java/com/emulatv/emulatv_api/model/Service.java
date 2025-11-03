package com.emulatv.emulatv_api.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity
@Table(name="service")
public class Service {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private String website;

    // Managed by JPA
    public Service(){}

    public Service(int id, String name, String website){
        this.id = id;
        this.name = name;
        this.website = website;
    }

    public long getId(){
        return this.id;
    }

    public String getName(){
        return this.name;
    }

    public String getWebsite(){
        return this.website;
    }

    public void setId(int id){
        this.id = id;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setWebsite(String website){
        this.website = website;
    }
}
