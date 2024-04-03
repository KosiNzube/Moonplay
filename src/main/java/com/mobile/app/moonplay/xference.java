package com.mobile.app.moonplay;

import com.google.firebase.Timestamp;

import java.util.ArrayList;

public class xference {
    private String image;
    private boolean p;
    private String id;
    private String moviename;
    private String name;
    private Timestamp timestamp;
    private ArrayList<Movie> likes;

    public xference(){

    }


    public xference(String image, boolean p, String id, String moviename, String name, Timestamp timestamp, ArrayList<Movie> likes) {
        this.image = image;
        this.p = p;
        this.id = id;
        this.moviename = moviename;
        this.name = name;
        this.timestamp = timestamp;
        this.likes = likes;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public boolean isP() {
        return p;
    }

    public void setP(boolean p) {
        this.p = p;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMoviename() {
        return moviename;
    }

    public void setMoviename(String moviename) {
        this.moviename = moviename;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public ArrayList<Movie> getLikes() {
        return likes;
    }

    public void setLikes(ArrayList<Movie> likes) {
        this.likes = likes;
    }
}
