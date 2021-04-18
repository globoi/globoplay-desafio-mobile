package com.example.globoplay_desafio_mobile.models.account;

public class UserModel {

    private String username;
    private String avatar;

    public UserModel(String username, String avatar) {
        this.username = username;
        this.avatar = avatar;
    }

    // Getters
    public String getUsername() {
        return username;
    }

    public String getAvatar() {
        return avatar;
    }

    //Setters
    public void setUsername(String username) {
        this.username = username;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}