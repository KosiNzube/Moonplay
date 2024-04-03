package com.mobile.app.moonplay;

import com.google.firebase.Timestamp;

public class quick {
    private String name;
    private String cast;
    private String link;
    private String description;
    private String image;
    private String photo;
    private String length;
    private String genre;
    private String subtitle;
    private String size;
    private com.google.firebase.Timestamp timestamp;
    public quick() {

    }

    public quick(String name, String cast, String link, String description, String image, String photo, String length, String genre, String subtitle, String size, Timestamp timestamp) {
        this.name = name;
        this.cast = cast;
        this.link = link;
        this.description = description;
        this.image = image;
        this.photo = photo;
        this.length = length;
        this.genre = genre;
        this.subtitle = subtitle;
        this.size = size;
        this.timestamp = timestamp;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCast() {
        return cast;
    }

    public void setCast(String cast) {
        this.cast = cast;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }
}