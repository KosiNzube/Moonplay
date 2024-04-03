package com.mobile.app.moonplay;

public class anime {

    private String name;

    public anime(){

    }

    public anime(String name) {
        this.name = name.toString();
    }

    public String getName() {
        return name.toString();
    }

    public void setName(String name) {
        this.name = name.toString();
    }
}
