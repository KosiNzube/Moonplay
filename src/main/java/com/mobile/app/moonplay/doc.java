package com.mobile.app.moonplay;

import com.google.firebase.Timestamp;

public class doc {
    private String picture;
    private String name;
    private String description;
    private int likes;
    private String cat;
    private String poster;

    private String rec;
    private int pins;
    private com.google.firebase.Timestamp timestamp;
    private String id;

    public doc(){

    }


    public doc(String picture, String name, String description, int likes, String cat, String poster, String rec, int pins, Timestamp timestamp, String id) {
        this.picture = picture;
        this.name = name;
        this.description = description;
        this.likes = likes;
        this.cat = cat;
        this.poster = poster;
        this.rec = rec;
        this.pins = pins;
        this.timestamp = timestamp;
        this.id = id;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public String getCat() {
        return cat;
    }

    public void setCat(String cat) {
        this.cat = cat;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getRec() {
        return rec;
    }

    public void setRec(String rec) {
        this.rec = rec;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
