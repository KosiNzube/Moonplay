package com.mobile.app.moonplay;

import com.tonyodev.fetch2.Status;

public class id {
    private String name;
    private int id;
    private int requestid;
    private int progress;
    private Status status;

    public id(String name, int id, int requestid, int progress, Status status) {
        this.name = name;
        this.id = id;
        this.requestid = requestid;
        this.progress = progress;
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRequestid() {
        return requestid;
    }

    public void setRequestid(int requestid) {
        this.requestid = requestid;
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
