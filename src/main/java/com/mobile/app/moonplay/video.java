package com.mobile.app.moonplay;

public class video {
    String descrition;
    String music;
    String video;
    int likes;

    public video(){

    }

    public video(String descrition, String music, String video, int likes) {
        this.descrition = descrition;
        this.music = music;
        this.video = video;
        this.likes = likes;
    }

    public String getDescrition() {
        return descrition;
    }

    public void setDescrition(String descrition) {
        this.descrition = descrition;
    }

    public String getMusic() {
        return music;
    }

    public void setMusic(String music) {
        this.music = music;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }
}
