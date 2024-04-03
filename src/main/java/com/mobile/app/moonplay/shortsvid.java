package com.mobile.app.moonplay;

public class shortsvid {
    private String video;
    private int last;

    public shortsvid(){

    }

    public shortsvid(String video, int last) {
        this.video = video;
        this.last = last;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public int getLast() {
        return last;
    }

    public void setLast(int last) {
        this.last = last;
    }
}
