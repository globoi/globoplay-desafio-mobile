package com.mazer.globoplayapp.presentation.ui.details

import android.os.Build
import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mazer.globoplayapp.domain.entities.Movie
import com.mazer.globoplayapp.domain.use_cases.GetMovieListUseCase
import com.mazer.globoplayapp.utils.AppConstants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MovieDetailsViewModel(private val getMovieListUseCase: GetMovieListUseCase): ViewModel()  {

    private val _btnAddToFavoriteVisibility = MutableLiveData<Boolean>()
    val btnAddToFavoriteVisibility: LiveData<Boolean> = _btnAddToFavoriteVisibility

    private val _btnFavoritedVisibility = MutableLiveData<Boolean>()
    val btnFavoritedVisibility: LiveData<Boolean> = _btnFavoritedVisibility

    private val _movieDetails = MutableLiveData<Movie>()
    val movieDetails: LiveData<Movie> = _movieDetails

    fun setExtras(bundle: Bundle){
        val movie = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            bundle.getParcelable(AppConstants.MOVIE_EXTRA, Movie::class.java)
        } else {
            bundle.getParcelable(AppConstants.MOVIE_EXTRA)
        }
        getFavoritedMovie(movie?.id)
        _movieDetails.postValue(movie ?: return)

    }

    fun addMovieToFavorites(movie: Movie?) {
        viewModelScope.launch {
            getMovieListUseCase.addToFavorite(movie)
            _btnAddToFavoriteVisibility.postValue(false)
            _btnFavoritedVisibility.postValue(true)
        }
    }

    fun deleteMovieFromFavorites(movie: Movie?) {
        viewModelScope.launch {
            getMovieListUseCase.deleteFromFavorite(movie)
            _btnAddToFavoriteVisibility.postValue(true)
            _btnFavoritedVisibility.postValue(false)
        }
    }

    private fun getFavoritedMovie(movieId: Int?){

        viewModelScope.launch(Dispatchers.IO) {

            movieId?.let {

                val movieFavorite = getMovieListUseCase.getFavoriteMovie(movieId)
                if (movieFavorite != null){
                    _btnAddToFavoriteVisibility.postValue(false)
                    _btnFavoritedVisibility.postValue(true)
                }else{
                    _btnAddToFavoriteVisibility.postValue(true)
                    _btnFavoritedVisibility.postValue(false)
                }
            }
        }
    }



}