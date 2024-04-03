package com.mobile.app.moonplay;

public class learn {
    private String picture;
    private String name;
    private String description;
    private int length;
    private String cat;
    private String poster;
    private String video;

    public learn(){

    }

    public learn(String picture, String name, String description, int length, String cat, String poster, String video) {
        this.picture = picture;
        this.name = name;
        this.description = description;
        this.length = length;
        this.cat = cat;
        this.poster = poster;
        this.video = video;
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

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
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

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }
}
