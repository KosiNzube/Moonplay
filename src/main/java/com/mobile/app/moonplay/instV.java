package com.mobile.app.moonplay;

import com.google.firebase.Timestamp;

public class instV {
    private String video;
    private String des;
    private String id;
    private String poster;
    private String thumbnail;
    private int likes;
    private String image;
    private String x;

    private com.google.firebase.Timestamp timestamp;



    public instV(){

    }

    public instV(String video, String des, String id, String poster, String thumbnail, int likes, String image, String x, Timestamp timestamp) {
        this.video = video;
        this.des = des;
        this.id = id;
        this.poster = poster;
        this.thumbnail = thumbnail;
        this.likes = likes;
        this.image = image;
        this.x = x;
        this.timestamp = timestamp;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getX() {
        return x;
    }

    public void setX(String x) {
        this.x = x;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }
}
