package com.mobile.app.moonplay;

public class animecard {
    private String title;

    private String url;
    private String image_url;
    private String type;
    private String start_date;
    private int mal_id;
    private int rank;


    public animecard(){

    }

    public animecard(String title, String url, String image_url, String type, String start_date, int mal_id, int rank) {
        this.title = title;
        this.url = url;
        this.image_url = image_url;
        this.type = type;
        this.start_date = start_date;
        this.mal_id = mal_id;
        this.rank = rank;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public int getMal_id() {
        return mal_id;
    }

    public void setMal_id(int mal_id) {
        this.mal_id = mal_id;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }
}
