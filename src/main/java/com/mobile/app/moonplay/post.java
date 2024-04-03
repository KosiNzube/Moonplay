package com.mobile.app.moonplay;

public class post {


    private String video;
    private String caption;
    private int views;



    public post(){
    }

    public post(String video, String caption, int views) {
        this.video = video;
        this.caption = caption;
        this.views = views;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
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
