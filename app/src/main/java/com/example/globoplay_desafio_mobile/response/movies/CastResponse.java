package com.example.globoplay_desafio_mobile.response.movies;

import com.example.globoplay_desafio_mobile.models.movies.CastModel;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CastResponse {

    @SerializedName("cast")
    private List<CastModel> cast;

    //Getter
    public List<CastModel> getCast() {
        return cast;
    }
}
