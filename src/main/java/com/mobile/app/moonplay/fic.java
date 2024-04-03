package com.mobile.app.moonplay;

import com.google.firebase.Timestamp;

public class fic {
    private String name;
    private String usd;
    private int naira;
    private Timestamp timestamp;

    public fic(){

    }

    public fic(String name, String usd, int naira, Timestamp timestamp) {
        this.name = name;
        this.usd = usd;
        this.naira = naira;
        this.timestamp = timestamp;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsd() {
        return usd;
    }

    public void setUsd(String usd) {
        this.usd = usd;
    }

    public int getNaira() {
        return naira;
    }

    public void setNaira(int naira) {
        this.naira = naira;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }
}
