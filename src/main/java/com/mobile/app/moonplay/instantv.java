package com.mobile.app.moonplay;

public class instantv {
    private String video;
    private String name;
    private String description;
    private int likes;
    private String cat;
    private String picture;
    private String album;

    private String id;

    public instantv(){

    }

    public instantv(String video, String name, String description, int likes, String cat, String picture, String album, String id) {
        this.video = video;
        this.name = name;
        this.description = description;
        this.likes = likes;
        this.cat = cat;
        this.picture = picture;
        this.album = album;
        this.id = id;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
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

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
