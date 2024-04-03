package com.mobile.app.moonplay;

public class genx {
    private String image;
    private String name;
    private int no;
    private String genre;
    private String id;
    private String idx;

    public genx(){

    }

    public genx(String image, String name, int no, String genre, String id, String idx) {
        this.image = image;
        this.name = name;
        this.no = no;
        this.genre = genre;
        this.id = id;
        this.idx = idx;
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

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdx() {
        return idx;
    }

    public void setIdx(String idx) {
        this.idx = idx;
    }
}
