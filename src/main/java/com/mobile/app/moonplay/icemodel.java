package com.mobile.app.moonplay;

import android.graphics.Bitmap;

public class icemodel {
  private String video;
  private Bitmap thumbnail;
  private int time;
  private String resolution;

  private String subtitle;



  public icemodel(){

  }

  public icemodel(String video, Bitmap thumbnail, int time, String resolution, String subtitle) {
    this.video = video;
    this.thumbnail = thumbnail;
    this.time = time;
    this.resolution = resolution;
    this.subtitle = subtitle;
  }

  public String getVideo() {
    return video;
  }

  public void setVideo(String video) {
    this.video = video;
  }

  public Bitmap getThumbnail() {
    return thumbnail;
  }

  public void setThumbnail(Bitmap thumbnail) {
    this.thumbnail = thumbnail;
  }

  public int getTime() {
    return time;
  }

  public void setTime(int time) {
    this.time = time;
  }

  public String getResolution() {
    return resolution;
  }

  public void setResolution(String resolution) {
    this.resolution = resolution;
  }

  public String getSubtitle() {
    return subtitle;
  }

  public void setSubtitle(String subtitle) {
    this.subtitle = subtitle;
  }
}
