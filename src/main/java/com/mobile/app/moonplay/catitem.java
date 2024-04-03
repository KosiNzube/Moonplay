package com.mobile.app.moonplay;

public class catitem {

  private String Title;
  private String image;


  public catitem(){

  }

  public catitem(String title, String image) {
    Title = title;
    this.image = image;
  }

  public String getTitle() {
    return Title;
  }

  public void setTitle(String title) {
    Title = title;
  }

  public String getImage() {
    return image;
  }

  public void setImage(String image) {
    this.image = image;
  }
}
