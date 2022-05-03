package com.example.testeglobojeremias;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.testeglobojeremias.models.MovieEntity;
import com.example.testeglobojeremias.network.PopularMoviesRepository;

import java.util.List;

public class PopularMoviesViewModel extends AndroidViewModel {

    private final PopularMoviesRepository popularMoviesRepository;

    public PopularMoviesViewModel(@NonNull Application application) {
        super(application);
        popularMoviesRepository = PopularMoviesRepository.getInstance(application);
    }

    public void getPopularMovies(){
        popularMoviesRepository.getPopularMovies();
    }


    public MutableLiveData<Boolean> getProgressLiveData(){
        return popularMoviesRepository.getProgressMutableLiveData();
    }

    public MutableLiveData<Boolean> getFailureLiveData(){
        return popularMoviesRepository.getResponseFailureLiveData();
    }

    public LiveData<List<MovieEntity>> getMovieEntityLiveData(){
        return popularMoviesRepository.getMovieEntityLiveData();
    }
}
