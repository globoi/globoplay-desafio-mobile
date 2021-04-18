package com.example.globoplay_desafio_mobile.viewmodels.movies;

import android.util.Log;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import com.example.globoplay_desafio_mobile.models.movies.MovieModel;
import com.example.globoplay_desafio_mobile.repositories.MovieRepository;
import java.util.List;

public class MovieListMoreViewModel extends ViewModel {

    private MovieRepository movieRepository;

    private int mPageNumber = 0;

    //Constructor
    public MovieListMoreViewModel() {
        movieRepository = MovieRepository.getInstance();
    }

    //Getter all movies
    public LiveData<List<MovieModel>> getAllMovies(){
        return movieRepository.getAllMovies();
    }
    //Getter total pages
    public int getTotal_pages(){
        return movieRepository.getTotal_pages();
    }

    //Setters
    public void setNullMoviesPagination(){
        movieRepository.setNullMoviesPagination();
    }

    // Call the method getMoviesPagination in MovieRepository
    public void getMoviesPagination(int genre){
        mPageNumber = mPageNumber + 1;

        Log.v("Tag", "mTotalPages: " + getTotal_pages());

        if(getTotal_pages() == 0 || mPageNumber <= getTotal_pages()) { //if it's not on the last page
            Log.v("Tag", "mPageNumber: " + mPageNumber);
            movieRepository.getMoviesPagination(genre,mPageNumber);
        }
    }

}
