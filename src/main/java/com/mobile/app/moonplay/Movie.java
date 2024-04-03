package com.mobile.app.moonplay;

import com.google.firebase.Timestamp;

public class Movie {
    private String id;
    private String description;
    private String genre;
    private String image;
    private String Mb;
    private String name;
    private String type;
    private String video;
    private String uploader;
    private String resolution;
    private String subtitle;
    private String tag;
    private com.google.firebase.Timestamp timestamp;

    public Movie(){

    }


    public Movie(String id, String description, String genre, String image, String mb, String name, String type, String video, String uploader, String resolution, String subtitle, String tag, Timestamp timestamp) {
        this.id = id;
        this.description = description;
        this.genre = genre;
        this.image = image;
        Mb = mb;
        this.name = name;
        this.type = type;
        this.video = video;
        this.uploader = uploader;
        this.resolution = resolution;
        this.subtitle = subtitle;
        this.tag = tag;
        this.timestamp = timestamp;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public String getMb() {
        return Mb;
    }

    public void setMb(String mb) {
        Mb = mb;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public String getUploader() {
        return uploader;
    }

    public void setUploader(String uploader) {
        this.uploader = uploader;
    }

    public String getResolution() {
        return resolution;
    }

    public void setResolution(String resolution) {
        this.resolution = resolution;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }
}