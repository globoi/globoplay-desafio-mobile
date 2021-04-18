package com.example.globoplay_desafio_mobile.viewmodels.movies;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import com.example.globoplay_desafio_mobile.models.movies.GenderModel;
import com.example.globoplay_desafio_mobile.repositories.MovieRepository;
import java.util.List;

public class MovieListViewModel extends ViewModel {

    private MovieRepository movieRepository;

    //Constructor
    public MovieListViewModel() {
        movieRepository = MovieRepository.getInstance();
    }

    //Getter movies by genres
    public LiveData<List<GenderModel>> getGenres(){
        return movieRepository.getGenres();
    }

    // Call the method getGenresMovies in MovieRepository
    public void getGenresMovies(){
        movieRepository.getGenresMovies();
    }
}
