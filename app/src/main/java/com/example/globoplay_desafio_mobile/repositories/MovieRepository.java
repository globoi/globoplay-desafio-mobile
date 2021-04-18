package com.example.globoplay_desafio_mobile.repositories;

import androidx.lifecycle.LiveData;
import com.example.globoplay_desafio_mobile.models.movies.CastModel;
import com.example.globoplay_desafio_mobile.models.movies.GenderModel;
import com.example.globoplay_desafio_mobile.models.movies.MovieModel;
import com.example.globoplay_desafio_mobile.models.movies.VideoModel;
import com.example.globoplay_desafio_mobile.request.MovieApiClient;

import java.util.List;

public class MovieRepository {

    private static MovieRepository instance;

    private MovieApiClient movieApiClient;

    public static MovieRepository getInstance() {
        if (instance == null) {
            instance = new MovieRepository();
        }
            return instance;

    }

    //Constructor
    private MovieRepository(){
        movieApiClient = MovieApiClient.getInstance();
    }

    //Getter movies by genres
    public LiveData<List<GenderModel>> getGenres(){
        return movieApiClient.getGenres();
    }
    //Getter movie cast
    public LiveData<List<CastModel>> getMovieCast(){
        return movieApiClient.getMovieCast();
    }
    //Getter similar movies
    public LiveData<List<MovieModel>> getSimilarMovies(){
        return movieApiClient.getSimilarMovies();
    }
    //Getter url video movies
    public LiveData<List<VideoModel>> getUrlVideosMovie(){
        return movieApiClient.getUrlVideosMovie();
    }
    //Getter all movies
    public LiveData<List<MovieModel>> getAllMovies(){
        return movieApiClient.getAllMovies();
    }
    //Getter total pages
    public int getTotal_pages(){
        return movieApiClient.getTotal_pages();
    }


    //Setters
    public void setNullmovieCast() {
        movieApiClient.setNullmovieCast();
    }

    public void setNullurlVideosMovie() {
        movieApiClient.setNullurlVideosMovie();
    }

    public void setNullsimilarMovies() {
        movieApiClient.setNullsimilarMovies();
    }


    // Call the method getGenresMovies in MovieApiClient
    public void getGenresMovies(){
        movieApiClient.getGenresMovies();
    }

    // Call the method getCast in MovieApiClient
    public void getCastID(int id_movie){
        movieApiClient.getCastID(id_movie);
    }

    // Call the method getSimilarMoviesID in MovieApiClient
    public void getSimilarMoviesID(int id_movie){
        movieApiClient.getSimilarMoviesID(id_movie);
    }

    // Call the method getUrlVideoMoviesID in MovieApiClient
    public void getUrlVideoMoviesID(int id_movie){
        movieApiClient.getUrlVideoMoviesID(id_movie);
    }

    // Call the method getMoviesPagination in MovieApiClient
    public void getMoviesPagination(int genre, int page){
        movieApiClient.getMoviesPagination(genre,page);
    }

    public void setNullMoviesPagination(){
        movieApiClient.setNullMoviesPagination();
    }
}
