package com.example.globoplay_desafio_mobile.response.movies;

import com.example.globoplay_desafio_mobile.models.movies.MovieModel;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;

public class MovieResponse {

    @SerializedName("results")
    private ArrayList<MovieModel> movies;
    private int total_pages;

    // Getter
    public ArrayList<MovieModel> getMovies() {
        return movies;
    }

    public int getTotal_pages() {
        return total_pages;
    }
}
