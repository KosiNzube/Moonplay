package com.mobile.app.moonplay;

public class genrex {
    private String image;
    private String genre;


    public genrex(String image, String genre) {
        this.image = image;
        this.genre = genre;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
}
