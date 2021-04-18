package com.example.globoplay_desafio_mobile.repositories;

import androidx.lifecycle.MutableLiveData;
import com.example.globoplay_desafio_mobile.models.movies.MovieModel;
import com.example.globoplay_desafio_mobile.request.MediaRequest;
import com.example.globoplay_desafio_mobile.request.WatchListApiClient;

import java.util.List;

public class WatchListRepository {

    private static WatchListRepository instance;

    private WatchListApiClient watchlistApiClient;

    public static WatchListRepository getInstance(){
        if(instance == null)
            instance = new WatchListRepository();

        return instance;
    }

    //Constructor
    public WatchListRepository(){
        watchlistApiClient = new WatchListApiClient();
    }

    //Getter favorite movies
    public MutableLiveData<List<MovieModel>> getMovies() {
        return watchlistApiClient.getMovies();
    }

    //Getter response when add or remove movie from favorites
    public MutableLiveData<Integer> getResponse() {
        return watchlistApiClient.getResponse();
    }


    //Setters
    public void setNullresponseAddRm() {
        watchlistApiClient.setNullresponseAddRm();
    }


    // Call the method getWatchList in WatchlistApiClient
    public void getWatchList(String accountId, String sessionId){
        watchlistApiClient.getWatchList(accountId,sessionId);
    }

    // Call the method AddRmMovieFav in WatchlistApiClient
    public void addRmMovieFav(String accountId, String sessionId, MediaRequest mediaRequest){
        watchlistApiClient.addRmMovieFav(accountId,sessionId,mediaRequest);
    }
}
