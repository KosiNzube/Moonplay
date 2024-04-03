package com.mobile.app.moonplay;

public class mainpost {

    private String video;
    private String mcaption;
    private String id;
    private String picture1;
    private String picture2;
    private String picture3;
    private String name;
    private String caption;
    private int views;


    public mainpost(){
    }

    public mainpost(String video, String mcaption, String id, String picture1, String picture2, String picture3, String name, String caption, int views) {
        this.video = video;
        this.mcaption = mcaption;
        this.id = id;
        this.picture1 = picture1;
        this.picture2 = picture2;
        this.picture3 = picture3;
        this.name = name;
        this.caption = caption;
        this.views = views;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public String getMcaption() {
        return mcaption;
    }

    public void setMcaption(String mcaption) {
        this.mcaption = mcaption;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPicture1() {
        return picture1;
    }

    public void setPicture1(String picture1) {
        this.picture1 = picture1;
    }

    public String getPicture2() {
        return picture2;
    }

    public void setPicture2(String picture2) {
        this.picture2 = picture2;
    }

    public String getPicture3() {
        return picture3;
    }

    public void setPicture3(String picture3) {
        this.picture3 = picture3;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }
}
