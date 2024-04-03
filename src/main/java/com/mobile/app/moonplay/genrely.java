package com.mobile.app.moonplay;

import com.google.firebase.Timestamp;

public class genrely {
    private String image;
    private String name;
    private String tags;
    private String id;
    private Timestamp timestamp;


    public genrely(){

    }

    public genrely(String image, String name, String tags, String id, Timestamp timestamp) {
        this.image = image;
        this.name = name;
        this.tags = tags;
        this.id = id;
        this.timestamp = timestamp;
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

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }
}
