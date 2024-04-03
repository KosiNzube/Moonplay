package com.mobile.app.moonplay;

public class bigboy {
    private String name;
    private String password;
    private String image;
    private String subscription;

    public bigboy(){

    }
    public bigboy(String name, String password, String image, String subscription) {
        this.name = name;
        this.password = password;
        this.image = image;
        this.subscription = subscription;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getSubscription() {
        return subscription;
    }

    public void setSubscription(String subscription) {
        this.subscription = subscription;
    }
}
