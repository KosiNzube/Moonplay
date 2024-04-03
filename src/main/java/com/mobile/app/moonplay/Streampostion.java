package com.mobile.app.moonplay;

import java.io.Serializable;

public class Streampostion implements Serializable {
    private String images;
    private int mio;
    private String Dname;
    private String Dlink;
    private String geenre;
    private String sub;


    public Streampostion(){

    }

    public Streampostion(String images, int mio, String dname, String dlink, String geenre, String sub) {
        this.images = images;
        this.mio = mio;
        Dname = dname;
        Dlink = dlink;
        this.geenre = geenre;

        this.sub = sub;
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
}
