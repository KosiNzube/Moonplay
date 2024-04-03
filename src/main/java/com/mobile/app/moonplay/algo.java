package com.mobile.app.moonplay;

public class algo {

    private String name;
    private String objectID;


    public algo(){

    }

    public algo(String name, String objectID) {
        this.name = name;
        this.objectID = objectID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getObjectID() {
        return objectID;
    }

    public void setObjectID(String objectID) {
        this.objectID = objectID;
    }
}
