package com.globo.moviesapp.network.movie

import com.globo.moviesapp.model.movieVideo.MovieVideo
import com.globo.moviesapp.model.movieUpdateFavorite.MovieUpdateFavorite
import com.globo.moviesapp.model.movie.Movie
import com.globo.moviesapp.model.movieAggregateCredits.MovieAggregateCredits
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import retrofit2.Retrofit
import java.lang.Exception
import javax.inject.Inject

class MovieRepository @Inject constructor(
    val retrofit: Retrofit
) {
    fun openMovieVideos(apiKey: String, movieId: Int): List<MovieVideo> {
        val call = retrofit.create(MovieServiceInterface::class.java)
            .getMovieVideosCall(movieId, apiKey).execute()

        val result = call.body()
        val error = call.errorBody().toString()

        if (error != "null"){
            throw Exception(call.message())
        }

        val type = object : TypeToken<List<MovieVideo>>() {}.type
        return Gson().fromJson(result?.get("results"), type)
    }

    fun updateFavoriteMovie(mediaType: String, mediaId: Int, favorite: Boolean, accountId: Int,
                            apiKey: String, language: String, sessionId: String) {

        val movieUpdateFavorite = MovieUpdateFavorite(mediaType, mediaId, favorite)
        val call = retrofit.create(MovieServiceInterface::class.java)
            .updateFavoriteMovie(accountId, apiKey, language, sessionId, movieUpdateFavorite).execute()

        val error = call.errorBody().toString()

        if (error != "null"){
            throw Exception(call.message())
        }
    }

    fun checkMovieFavorite(movieId: Int, apiKey: String, language: String, sessionId: String): Boolean {
        val call = retrofit.create(MovieServiceInterface::class.java)
            .checkMovieFavoriteCall(movieId, apiKey, language, sessionId).execute()

        val result = call.body()
        val error = call.errorBody().toString()

        if (error != "null"){
            throw Exception(call.message())
        }

        return Gson().fromJson(result?.get("favorite"), Boolean::class.java)
    }

    fun getMovieFavoriteAll(accountId: Int, apiKey: String, language: String, sessionId: String): List<Movie> {
        val call = retrofit.create(MovieServiceInterface::class.java)
            .getMovieFavoriteCall(accountId, apiKey, language, sessionId).execute()

        val result = call.body()
        val error = call.errorBody().toString()

        if (error != "null"){
            throw Exception(call.message())
        }

        val type = object : TypeToken<List<Movie>>() {}.type
        return Gson().fromJson(result?.get("results"), type)
    }

    fun getMovieRecommendationAll(apiKey: String, language: String, movieId: Int): List<Movie> {
        val call = retrofit.create(MovieServiceInterface::class.java)
            .getMovieRecommendationsCall(movieId, apiKey, language).execute()

        val result = call.body()
        val error = call.errorBody().toString()

        if (error != "null"){
            throw Exception(call.message())
        }

        val type = object : TypeToken<List<Movie>>() {}.type
        return Gson().fromJson(result?.get("results"), type)
    }

    fun openMovieAggregateCredits(apiKey: String, language: String, movieId: Int): MovieAggregateCredits {
        val call = retrofit.create(MovieServiceInterface::class.java)
            .openMovieAggregateCreditsCall(movieId, apiKey, language).execute()

        val result = call.body()
        val error = call.errorBody().toString()

        if (error != "null"){
            throw Exception(call.message())
        }

        return Gson().fromJson(result, MovieAggregateCredits::class.java)
    }

    fun openMovie(apiKey: String, language: String, movieId: Int): Movie {
        val call = retrofit.create(MovieServiceInterface::class.java)
            .openMovieCall(movieId, apiKey, language).execute()

        val result = call.body()
        val error = call.errorBody().toString()

        if (error != "null"){
            throw Exception(call.message())
        }

        return Gson().fromJson(result, Movie::class.java)
    }

    fun getMovieAll(apiKey: String, language: String, genre: String): List<Movie> {
        val call = retrofit.create(MovieServiceInterface::class.java)
            .getMovieCall(apiKey, language, genre).execute()

        val result = call.body()
        val error = call.errorBody().toString()

        if (error != "null"){
            throw Exception(call.message())
        }

        val type = object : TypeToken<List<Movie>>() {}.type
        return Gson().fromJson(result?.get("results"), type)
    }
}