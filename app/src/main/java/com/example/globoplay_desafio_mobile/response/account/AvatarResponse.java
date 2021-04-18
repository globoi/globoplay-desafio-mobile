package com.example.globoplay_desafio_mobile.response.account;

import com.google.gson.annotations.SerializedName;

public class AvatarResponse {

    @SerializedName("tmdb")
    private AvatarTmdbResponse avatarTmdbResponse;

    public AvatarTmdbResponse getAvatarTmdbResponse() {
        return avatarTmdbResponse;
    }
}
