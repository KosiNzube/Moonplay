package com.mobile.app.moonplay;

import com.google.firebase.Timestamp;

import java.io.Serializable;

public class Streampostiontime implements Serializable {
    private String images;
    private int mio;
    private String Dname;
    private String Dlink;
    private String geenre;
    private String sub;
    private Timestamp timestamp;


    public Streampostiontime(){

    }

    public Streampostiontime(String images, int mio, String dname, String dlink, String geenre, String sub, Timestamp timestamp) {
        this.images = images;
        this.mio = mio;
        Dname = dname;
        Dlink = dlink;
        this.geenre = geenre;
        this.sub = sub;
        this.timestamp = timestamp;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public int getMio() {
        return mio;
    }

    public void setMio(int mio) {
        this.mio = mio;
    }

    public String getDname() {
        return Dname;
    }

    public void setDname(String dname) {
        Dname = dname;
    }

    public String getDlink() {
        return Dlink;
    }

    public void setDlink(String dlink) {
        Dlink = dlink;
    }

    public String getGeenre() {
        return geenre;
    }

    public void setGeenre(String geenre) {
        this.geenre = geenre;
    }

    public String getSub() {
        return sub;
    }

    public void setSub(String sub) {
        this.sub = sub;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }
}
