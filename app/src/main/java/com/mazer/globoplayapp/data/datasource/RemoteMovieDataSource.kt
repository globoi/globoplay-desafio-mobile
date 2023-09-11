package com.mazer.globoplayapp.data.datasource

import com.mazer.globoplayapp.BuildConfig
import com.mazer.globoplayapp.data.remote.ApiService
import com.mazer.globoplayapp.domain.entities.Genre
import com.mazer.globoplayapp.domain.entities.Movie
import com.mazer.globoplayapp.domain.entities.Video

class RemoteMovieDataSource(private val apiService: ApiService): MovieDataSource  {

    companion object{
        const val API_KEY = BuildConfig.THEMOVIEDB_API_KEY
    }

    override suspend fun getPopularMoviesFromRemote(): List<Movie> {
        val response = apiService.getPopularMovies(API_KEY)
        if (response.isSuccessful) {
            return response.body()?.results ?: emptyList()
        } else {
            throw Exception("Erro ao comunicar com o servidor, tente novamente mais tarde!")
        }
    }

    override suspend fun getTopRatedMoviesFromRemote(): List<Movie> {
        val response = apiService.getTopRatedMovies(API_KEY)
        if (response.isSuccessful) {
            return response.body()?.results ?: emptyList()
        } else {
            throw Exception("Erro ao comunicar com o servidor, tente novamente mais tarde!")
        }
    }

    override suspend fun getUpcomingFromRemote(): List<Movie> {
        val response = apiService.getUpcomingMovies(API_KEY)
        if (response.isSuccessful) {
            return response.body()?.results ?: emptyList()
        } else {
            throw Exception("Erro ao comunicar com o servidor, tente novamente mais tarde!")
        }
    }

    override suspend fun getGenreList(): List<Genre> {
        val response = apiService.getGenreList(API_KEY)
        if (response.isSuccessful) {
            return response.body()?.results ?: emptyList()
        } else {
            throw Exception("Erro ao comunicar com o servidor, tente novamente mais tarde!")
        }
    }

    override suspend fun getRecommendationList(movieId: Int): List<Movie> {
        val response = apiService.getRecommendationList(movieId, API_KEY)
        if (response.isSuccessful) {
            return response.body()?.results ?: emptyList()
        } else {
            throw Exception("Erro ao comunicar com o servidor, tente novamente mais tarde!")
        }
    }

    override suspend fun getVideoList(movieId: Int): List<Video?> {
        val response = apiService.getVideosFromMovie(movieId, API_KEY)
        if (response.isSuccessful) {
            return response.body()?.results ?: emptyList()
        } else {
            throw Exception("Erro ao comunicar com o servidor, tente novamente mais tarde!")
        }
    }


}