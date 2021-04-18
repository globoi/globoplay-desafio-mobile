package com.example.globoplay_desafio_mobile.response.movies;

import com.example.globoplay_desafio_mobile.models.movies.GenderModel;
import com.google.gson.annotations.SerializedName;
import java.util.List;

public class GenderResponse {

    @SerializedName("genres")
    private List<GenderModel> genders;

    // Getter
    public List<GenderModel> getGenders() {
        return genders;
    }
}
