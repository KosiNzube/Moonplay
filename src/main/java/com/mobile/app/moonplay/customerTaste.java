package com.mobile.app.moonplay;

public class customerTaste {
    private int horror;
    private int thriller;
    private int action;
    private int crime;
    private int drama;
    private int animation;
    private int comedy;
    private int adventure;
    private int scifi;
    private int romance;


    public customerTaste(){

    }

    public customerTaste(int horror, int thriller, int action, int crime, int drama, int animation, int comedy, int adventure, int scifi, int romance) {
        this.horror = horror;
        this.thriller = thriller;
        this.action = action;
        this.crime = crime;
        this.drama = drama;
        this.animation = animation;
        this.comedy = comedy;
        this.adventure = adventure;
        this.scifi = scifi;
        this.romance = romance;
    }

    public int getHorror() {
        return horror;
    }

    public void setHorror(int horror) {
        this.horror = horror;
    }

    public int getThriller() {
        return thriller;
    }

    public void setThriller(int thriller) {
        this.thriller = thriller;
    }

    public int getAction() {
        return action;
    }

    public void setAction(int action) {
        this.action = action;
    }

    public int getCrime() {
        return crime;
    }

    public void setCrime(int crime) {
        this.crime = crime;
    }

    public int getDrama() {
        return drama;
    }

    public void setDrama(int drama) {
        this.drama = drama;
    }

    public int getAnimation() {
        return animation;
    }

    public void setAnimation(int animation) {
        this.animation = animation;
    }

    public int getComedy() {
        return comedy;
    }

    public void setComedy(int comedy) {
        this.comedy = comedy;
    }

    public int getAdventure() {
        return adventure;
    }

    public void setAdventure(int adventure) {
        this.adventure = adventure;
    }

    public int getScifi() {
        return scifi;
    }

    public void setScifi(int scifi) {
        this.scifi = scifi;
    }

    public int getRomance() {
        return romance;
    }

    public void setRomance(int romance) {
        this.romance = romance;
    }
}
