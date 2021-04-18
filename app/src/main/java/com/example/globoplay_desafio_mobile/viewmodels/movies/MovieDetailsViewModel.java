package com.example.globoplay_desafio_mobile.viewmodels.movies;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.globoplay_desafio_mobile.models.movies.CastModel;
import com.example.globoplay_desafio_mobile.models.movies.MovieModel;
import com.example.globoplay_desafio_mobile.models.movies.VideoModel;
import com.example.globoplay_desafio_mobile.repositories.MovieRepository;
import com.example.globoplay_desafio_mobile.repositories.WatchListRepository;
import com.example.globoplay_desafio_mobile.request.MediaRequest;
import java.util.List;

public class MovieDetailsViewModel extends ViewModel {

    private MovieRepository movieRepository;
    private WatchListRepository watchListRepository;

    //Constructor
    public MovieDetailsViewModel() {
        movieRepository = MovieRepository.getInstance();
        watchListRepository = WatchListRepository.getInstance();
    }

    //Getter movie cast
    public LiveData<List<CastModel>> getMovieCast(){
        return movieRepository.getMovieCast();
    }

    //Getter similar movies
    public LiveData<List<MovieModel>> getSimilarMovies(){
        return movieRepository.getSimilarMovies();
    }

    //Getter url video movies
    public LiveData<List<VideoModel>> getUrlVideosMovie(){
        return movieRepository.getUrlVideosMovie();
    }

    //Getter response when add or remove movie from favorites
    public MutableLiveData<Integer> getResponse() {
        return watchListRepository.getResponse();
    }

    //Getter favorite movies
    public MutableLiveData<List<MovieModel>> getMovies() {
        return watchListRepository.getMovies();
    }


    //Setters
    public void setNullmovieCast() {
        movieRepository.setNullmovieCast();
    }

    public void setNullurlVideosMovie() {
        movieRepository.setNullurlVideosMovie();
    }

    public void setNullsimilarMovies() {
        movieRepository.setNullsimilarMovies();
    }

    public void setNullresponseAddRm() {
        watchListRepository.setNullresponseAddRm();
    }


    // Call the method getCast in MovieRepository
    public void getCastID(int id_movie){
        movieRepository.getCastID(id_movie);
    }

    // Call the method getSimilarMoviesID in MovieApiClient
    public void getSimilarMoviesID(int id_movie){
        movieRepository.getSimilarMoviesID(id_movie);
    }

    // Call the method getUrlVideoMoviesID in MovieApiClient
    public void getUrlVideoMoviesID(int id_movie){
        movieRepository.getUrlVideoMoviesID(id_movie);
    }

    // Call the method getWatchList in WatchListRepository
    public void getWatchList(String accountId, String sessionId){
        watchListRepository.getWatchList(accountId,sessionId);
    }

    // Call the method AddRmMovieFav in WatchListRepository
    public void addRmMovieFav(String accountId, String sessionId, MediaRequest mediaRequest){
        watchListRepository.addRmMovieFav(accountId,sessionId,mediaRequest);
    }
}
