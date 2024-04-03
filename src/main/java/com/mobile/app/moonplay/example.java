package com.mobile.app.moonplay;

public class example {
    private String description;
    private String genre;
    private String image;
    private String name;
    private String type;

    public example(String description, String genre, String image, String name, String type) {
        this.description = description;
        this.genre = genre;
        this.image = image;
        this.name = name;
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
