package com.mobile.app.moonplay;

public class shortlist {
    private String name;
    private String video;




    public shortlist(){

    }


    public shortlist(String name, String video) {
        this.name = name;
        this.video = video;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }
}