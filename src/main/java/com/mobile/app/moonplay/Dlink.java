package com.mobile.app.moonplay;

public class Dlink {
    private String name;
    private String video;
    private String photo;
    private String genre;
    private String originame;
    private String mb;
    private String x;
    private String subtitle;
    public Dlink(){

    }

    public Dlink(String name, String video, String photo, String genre, String originame, String mb, String x, String subtitle) {
        this.name = name;
        this.video = video;
        this.photo = photo;
        this.genre = genre;
        this.originame = originame;
        this.mb = mb;
        this.x = x;
        this.subtitle = subtitle;
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

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getOriginame() {
        return originame;
    }

    public void setOriginame(String originame) {
        this.originame = originame;
    }

    public String getMb() {
        return mb;
    }

    public void setMb(String mb) {
        this.mb = mb;
    }

    public String getX() {
        return x;
    }

    public void setX(String x) {
        this.x = x;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }
}
