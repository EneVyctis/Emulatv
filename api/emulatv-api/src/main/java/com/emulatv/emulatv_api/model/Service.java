package com.emulatv.emulatv_api.model;

public class Service {
    private int id;
    private String name;
    private String website;

    public Service(){
        this.id = -1;
        this.name = "";
        this.website = "";
    }

    public Service(int id, String name, String website){
        this.id = id;
        this.name = name;
        this.website = website;
    }

    public int getID(){
        return this.id;
    }

    public String getName(){
        return this.name.toString();
    }

    public String getWebsite(){
        return this.website.toString();
    }

    public void setID(int id){
        this.id = id;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setWebsite(String website){
        this.website = website;
    }
}
