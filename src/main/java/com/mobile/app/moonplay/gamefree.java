package com.mobile.app.moonplay;

import com.google.firebase.Timestamp;

public class gamefree {
    private String xbet;
    private String sporty;
    private String betking;
    private int msport;
    private String teams;
    private int odd;

    private String staker;
    private int pins;
    private Timestamp timestamp;
    private String risk;

    public gamefree(){

    }


    public gamefree(String xbet, String sporty, String betking, int msport, String teams, int odd, String staker, int pins, Timestamp timestamp, String risk) {
        this.xbet = xbet;
        this.sporty = sporty;
        this.betking = betking;
        this.msport = msport;
        this.teams = teams;
        this.odd = odd;
        this.staker = staker;
        this.pins = pins;
        this.timestamp = timestamp;
        this.risk = risk;
    }

    public String getXbet() {
        return xbet;
    }

    public void setXbet(String xbet) {
        this.xbet = xbet;
    }

    public String getSporty() {
        return sporty;
    }

    public void setSporty(String sporty) {
        this.sporty = sporty;
    }

    public String getBetking() {
        return betking;
    }

    public void setBetking(String betking) {
        this.betking = betking;
    }

    public int getMsport() {
        return msport;
    }

    public void setMsport(int msport) {
        this.msport = msport;
    }

    public String getTeams() {
        return teams;
    }

    public void setTeams(String teams) {
        this.teams = teams;
    }

    public int getOdd() {
        return odd;
    }

    public void setOdd(int odd) {
        this.odd = odd;
    }

    public String getStaker() {
        return staker;
    }

    public void setStaker(String staker) {
        this.staker = staker;
    }

    public int getPins() {
        return pins;
    }

    public void setPins(int pins) {
        this.pins = pins;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public String getRisk() {
        return risk;
    }

    public void setRisk(String risk) {
        this.risk = risk;
    }
}
