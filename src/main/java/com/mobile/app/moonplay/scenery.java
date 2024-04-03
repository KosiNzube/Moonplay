package com.mobile.app.moonplay;

public class scenery {
    private String video;
    private String name;
    private String genre;
    public scenery(){

    }

    public scenery(String video, String name, String genre) {
        this.video = video;
        this.name = name;
        this.genre = genre;
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

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
}
