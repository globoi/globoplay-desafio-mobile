package com.example.globoplay_desafio_mobile.viewmodels.watchlist;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.globoplay_desafio_mobile.models.movies.MovieModel;
import com.example.globoplay_desafio_mobile.repositories.WatchListRepository;
import java.util.List;

public class WatchListViewModel extends ViewModel {

    private WatchListRepository watchListRepository;

    //Constructor
    public WatchListViewModel(){
        watchListRepository = WatchListRepository.getInstance();
    }

    //Getter favorite movies
    public MutableLiveData<List<MovieModel>> getMovies() {
        return watchListRepository.getMovies();
    }

    // Call the method getWatchList in WatchListRepository
    public void getWatchList(String accountId, String sessionId){
        watchListRepository.getWatchList(accountId,sessionId);
    }
}
