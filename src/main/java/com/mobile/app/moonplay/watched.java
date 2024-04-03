package com.mobile.app.moonplay;

public class watched {
    private String name;
    private String photo;
    private String link;
    private int length;
    private String genre;

    public watched(){

    }

    public watched(String name, String photo, String link, int length, String genre) {
        this.name = name;
        this.photo = photo;
        this.link = link;
        this.length = length;
        this.genre = genre;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
}
