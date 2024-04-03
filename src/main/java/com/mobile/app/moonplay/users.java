package com.mobile.app.moonplay;

public class users {
    private String name;
    private boolean substate;
    private String id;
    private String x;
    private String y;

    private String instagram;
    private String whatsapp;
    private String fb;


    public users() {


    }

    public users(String name, boolean substate, String id, String x, String y, String instagram, String whatsapp, String fb) {
        this.name = name;
        this.substate = substate;
        this.id = id;
        this.x = x;
        this.y = y;
        this.instagram = instagram;
        this.whatsapp = whatsapp;
        this.fb = fb;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isSubstate() {
        return substate;
    }

    public void setSubstate(boolean substate) {
        this.substate = substate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getX() {
        return x;
    }

    public void setX(String x) {
        this.x = x;
    }

    public String getY() {
        return y;
    }

    public void setY(String y) {
        this.y = y;
    }

    public String getInstagram() {
        return instagram;
    }

    public void setInstagram(String instagram) {
        this.instagram = instagram;
    }

    public String getWhatsapp() {
        return whatsapp;
    }

    public void setWhatsapp(String whatsapp) {
        this.whatsapp = whatsapp;
    }

    public String getFb() {
        return fb;
    }

    public void setFb(String fb) {
        this.fb = fb;
    }
}
