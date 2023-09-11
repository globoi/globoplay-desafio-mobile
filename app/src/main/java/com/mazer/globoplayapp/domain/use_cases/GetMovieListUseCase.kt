package com.mazer.globoplayapp.domain.use_cases

import androidx.lifecycle.LiveData
import com.mazer.globoplayapp.data.repos.MovieRepository
import com.mazer.globoplayapp.domain.entities.Movie
import com.mazer.globoplayapp.domain.entities.Video
import com.mazer.globoplayapp.presentation.wrapper.VideoUI

/**
 * Essa classe agrupa os UseCases de carregar os filmes "Popular", "Top Rated" e "Upcoming"
 *
 */
class GetMovieListUseCase(private val movieRepository: MovieRepository) {

    suspend fun getPopularMovies(): List<Movie> {
        val popularMovies = movieRepository.getPopularMovies()
        val genreList = movieRepository.getGenreList()

        popularMovies.forEach { movies ->
            val genre = genreList.filter { it.id == movies.genreIds[0] }[0]
            movies.genre = genre.name
        }

        return popularMovies
    }

    suspend fun getTopRatedMovies(): List<Movie> {
        val topRatedMovies = movieRepository.getTopRatedMovies()
        val genreList = movieRepository.getGenreList()

        topRatedMovies.forEach { movies ->
            val genre = genreList.filter { it.id == movies.genreIds[0] }[0]
            movies.genre = genre.name
        }

        return topRatedMovies
    }

    suspend fun getUpcomingMovies(): List<Movie> {
        val upcomingMovies = movieRepository.getUpcomingMovies()
        val genreList = movieRepository.getGenreList()

        upcomingMovies.forEach { movies ->
            val genre = genreList.filter { it.id == movies.genreIds[0] }[0]
            movies.genre = genre.name
        }

        return upcomingMovies
    }

    suspend fun getRecommendationList(movieId: Int): List<Movie>{
        return movieRepository.getRecommendationList(movieId)
    }

    suspend fun addToFavorite(movie: Movie?){
        movieRepository.addToFavorites(movie ?: return)
    }

    suspend fun deleteFromFavorite(movie: Movie?){
        movieRepository.deleteFromFavorites(movie ?: return)
    }

    suspend fun getAllFavorites(): LiveData<List<Movie>> {
        return movieRepository.getAllFavorites()
    }

    suspend fun getFavoriteMovie(movieId: Int): Movie? {
        return movieRepository.getFavoriteMovie(movieId)
    }

    suspend fun getVideoList(movieId: Int): List<VideoUI>{
        val videoList = movieRepository.getVideoList(movieId).filterNotNull()
        val videoUIList = arrayListOf<VideoUI>()
        videoList.forEach {
            val videoUI = VideoUI(it, false)
            videoUIList.add(videoUI)
        }
        try {
            videoUIList.first { it.video.type == "Trailer" }.isPlaying = true
        }catch (ex: NoSuchElementException){
            videoUIList.first().isPlaying = true
        }
        return videoUIList
    }

}