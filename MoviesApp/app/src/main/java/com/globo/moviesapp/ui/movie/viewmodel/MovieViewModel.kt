package com.globo.moviesapp.ui.movie.viewmodel

import android.content.SharedPreferences
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.globo.moviesapp.model.genre.Genre
import com.globo.moviesapp.model.movie.Movie
import com.globo.moviesapp.network.movie.MovieRepository
import java.lang.Exception
import javax.inject.Inject

class MovieViewModel @Inject constructor(
    private val repository: MovieRepository,
    private val sharedPreferences: SharedPreferences
): ViewModel() {

    var apiKey: String? = null
    var languageLocale: String? = null
    var movies =MutableLiveData<List<Movie>>()
    var moviesRecommendation =MutableLiveData<List<Movie>>()
    var moviesFavorite =MutableLiveData<List<Movie>>()
    var movie =MutableLiveData<Movie>()
    var error= MutableLiveData<String>()
    var isLoading = MutableLiveData(true)

    fun updateFavoriteMovie(movieId: Int, favorite: Boolean){
        try {
            val accountId = sharedPreferences.getInt("ACCOUNT_ID", 0)
            val sessionId = sharedPreferences.getString("SESSION_ID", "")
            repository.updateFavoriteMovie("tv", movieId, favorite, accountId, apiKey!!,
                languageLocale!!, sessionId!!)
            openMovie(movieId)
        } catch (e: Exception){
            error.postValue(e.message)
        }
    }

    fun loadMoviesFavorite() {
        try {
            this.isLoading.postValue(true)
            val accountId = sharedPreferences.getInt("ACCOUNT_ID", 0)
            val sessionId = sharedPreferences.getString("SESSION_ID", "")
            val movies = repository.getMovieFavoriteAll(accountId, apiKey!!, languageLocale!!, sessionId!!)
            this.moviesFavorite.postValue(movies)
            this.isLoading.postValue(false)
        } catch (e: Exception){
            error.postValue(e.message)
        }
    }

    fun loadMoviesRecommendations(movieId: Int) {
        try {
            val movies = repository.getMovieRecommendationAll(apiKey!!, languageLocale!!, movieId)
            this.moviesRecommendation.postValue(movies)
        } catch (e: Exception){
            error.postValue(e.message)
        }
    }

    fun openMovie(movieId: Int) {
        try {
            this.isLoading.postValue(true)
            val sessionId = sharedPreferences.getString("SESSION_ID", "")
            val movie = repository.openMovie(apiKey!!, languageLocale!!, movieId)
            movie.aggregateCredits = repository.openMovieAggregateCredits(apiKey!!, languageLocale!!, movieId)
            val videos = repository.openMovieVideos(apiKey!!, movieId)
            movie.keyYoutube = if(!videos.isNullOrEmpty()) videos[0].key else null
            movie.isFavorite = repository.checkMovieFavorite(movieId, apiKey!!, languageLocale!!, sessionId!!)
            this.movie.postValue(movie)
            this.isLoading.postValue(false)
        } catch (e: Exception){
            error.postValue(e.message)
        }
    }

    fun loadMovies(genres: List<Genre>) {
        try {
            val movies = arrayListOf<Movie>()
            genres.forEach {
                movies.addAll(repository.getMovieAll(apiKey!!, languageLocale!!, it.id.toString()))
            }
            this.movies.postValue(movies)
        } catch (e: Exception){
            error.postValue(e.message)
        }
    }
}