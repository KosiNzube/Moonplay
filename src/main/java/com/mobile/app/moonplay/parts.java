package com.mobile.app.moonplay;

public class parts {
    private String part;
    private String video;
    private String mb;

    public parts(String part, String video, String mb) {
        this.part = part;
        this.video = video;
        this.mb = mb;
    }

    public String getPart() {
        return part;
    }

    public void setPart(String part) {
        this.part = part;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public String getMb() {
        return mb;
    }

    public void setMb(String mb) {
        this.mb = mb;
    }
}
