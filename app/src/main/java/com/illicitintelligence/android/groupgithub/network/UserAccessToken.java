package com.illicitintelligence.android.groupgithub.network;

public class UserAccessToken {
    private String accessToken;
    private String userName;

    public UserAccessToken(String accessToken, String userName) {
        this.accessToken = accessToken;
        this.userName = userName;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
