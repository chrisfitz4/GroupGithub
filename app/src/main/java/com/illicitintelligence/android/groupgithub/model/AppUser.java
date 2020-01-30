package com.illicitintelligence.android.groupgithub.model;

public class AppUser {

    private String userName;
    private int color;

    public AppUser(String userName, int color) {
        this.userName = userName;
        this.color = color;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }
}
