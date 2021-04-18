package com.example.globoplay_desafio_mobile.response.account;

import com.example.globoplay_desafio_mobile.models.account.UserModel;
import com.google.gson.annotations.SerializedName;

public class UserResponse {

    private int id;
    private String name;
    private String username;
    @SerializedName("avatar")
    private AvatarResponse avatar;

    // Getters
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public AvatarResponse getAvatar() {
        return avatar;
    }

    //Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setAvatar(AvatarResponse avatar) {
        this.avatar = avatar;
    }
}