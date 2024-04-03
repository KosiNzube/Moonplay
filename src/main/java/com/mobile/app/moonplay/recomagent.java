package com.mobile.app.moonplay;

public class recomagent {
    public  String bannerImage;
    public String coverImage;
    public String id;
    public String description;
    public String averageScore;
    public String title;
    public String trailerid;
    public String site;
    public String seasonYear;
    public String format;

    public recomagent(){

    }

    public recomagent(String bannerImage, String coverImage, String id, String description, String averageScore, String title, String trailerid, String site, String seasonYear, String format) {
        this.bannerImage = bannerImage;
        this.coverImage = coverImage;
        this.id = id;
        this.description = description;
        this.averageScore = averageScore;
        this.title = title;
        this.trailerid = trailerid;
        this.site = site;
        this.seasonYear = seasonYear;
        this.format = format;
    }

    public String getBannerImage() {
        return bannerImage;
    }

    public void setBannerImage(String bannerImage) {
        this.bannerImage = bannerImage;
    }

    public String getCoverImage() {
        return coverImage;
    }

    public void setCoverImage(String coverImage) {
        this.coverImage = coverImage;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAverageScore() {
        return averageScore;
    }

    public void setAverageScore(String averageScore) {
        this.averageScore = averageScore;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTrailerid() {
        return trailerid;
    }

    public void setTrailerid(String trailerid) {
        this.trailerid = trailerid;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getSeasonYear() {
        return seasonYear;
    }

    public void setSeasonYear(String seasonYear) {
        this.seasonYear = seasonYear;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }
}
