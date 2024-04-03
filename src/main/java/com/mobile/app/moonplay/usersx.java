package com.mobile.app.moonplay;

import com.google.firebase.Timestamp;

public class usersx {
    private String name;
    private boolean substate;
    private String id;
    private Timestamp timestamp;


    public usersx() {


    }


    public usersx(String name, boolean substate, String id, Timestamp timestamp) {
        this.name = name;
        this.substate = substate;
        this.id = id;
        this.timestamp = timestamp;
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

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }
}
