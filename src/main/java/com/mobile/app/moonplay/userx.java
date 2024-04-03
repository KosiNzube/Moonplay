package com.mobile.app.moonplay;

public class userx {
    private String taste;
    private int substate;
    private String id;


    public userx(){


    }

    public userx(String taste, int substate, String id) {
        this.taste = taste;
        this.substate = substate;
        this.id = id;
    }

    public String getTaste() {
        return taste;
    }

    public void setTaste(String taste) {
        this.taste = taste;
    }

    public int getSubstate() {
        return substate;
    }

    public void setSubstate(int substate) {
        this.substate = substate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
