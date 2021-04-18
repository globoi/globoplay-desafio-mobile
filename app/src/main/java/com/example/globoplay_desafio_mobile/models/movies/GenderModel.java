package com.example.globoplay_desafio_mobile.models.movies;

import java.util.ArrayList;

public class GenderModel {

    private int id;
    private String name;


    private ArrayList<MovieModel> movies = new ArrayList<>();

    //Getters
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public ArrayList<MovieModel> getMovies() {
        return movies;
    }

    //Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMovies(ArrayList<MovieModel> movies) {
        this.movies = movies;
    }

    public void setMovie(MovieModel movie){
        this.movies.add(movie);
    }
}
