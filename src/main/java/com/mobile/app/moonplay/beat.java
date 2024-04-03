package com.mobile.app.moonplay;

public class beat {


    private String link;
    private String length;
    private String name;
    private String image;
    private boolean own;
    private String producer;
    private String genre;
    private String tags;



    public beat(){

    }

    public beat(String link, String length, String name, String image, boolean own, String producer, String genre, String tags) {
        this.link = link;
        this.length = length;
        this.name = name;
        this.image = image;
        this.own = own;
        this.producer = producer;
        this.genre = genre;
        this.tags = tags;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public boolean isOwn() {
        return own;
    }

    public void setOwn(boolean own) {
        this.own = own;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }
}
