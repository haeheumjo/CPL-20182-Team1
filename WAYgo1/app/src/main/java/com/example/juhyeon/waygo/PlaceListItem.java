package com.example.juhyeon.waygo;

import android.graphics.drawable.Drawable;

public class PlaceListItem {
    private Drawable iconimage;
    private String placename ;

    public void setIcon(Drawable icon) {
        iconimage = icon ;
    }
    public void setPlace(String place) {
        placename = place ;
    }
    public Drawable getIcon() {
        return this.iconimage ;
    }
    public String getPlace() {
        return this.placename;
    }
}
