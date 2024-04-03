package com.mobile.app.moonplay;

import com.google.firebase.Timestamp;

public class watchlist {
    private String genre;
    private String image;
    private String name;
    private String video;
    private int pos;
    private com.google.firebase.Timestamp timestamp;
    private String sub;


    public watchlist(){

    }


    public watchlist(String genre, String image, String name, String video, int pos, Timestamp timestamp, String sub) {
        this.genre = genre;
        this.image = image;
        this.name = name;
        this.video = video;
        this.pos = pos;
        this.timestamp = timestamp;
        this.sub = sub;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public int getPos() {
        return pos;
    }

    public void setPos(int pos) {
        this.pos = pos;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public String getSub() {
        return sub;
    }

    public void setSub(String sub) {
        this.sub = sub;
    }
}