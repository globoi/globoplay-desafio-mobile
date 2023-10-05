package com.mazer.globoplayapp.domain.use_cases

import androidx.lifecycle.LiveData
import com.mazer.globoplayapp.data.repos.MovieRepository
import com.mazer.globoplayapp.domain.entities.Movie
import com.mazer.globoplayapp.presentation.wrapper.VideoUI

/**
 * Essa classe agrupa os UseCases de carregar os filmes "Popular", "Top Rated" e "Upcoming"
 *
 */
class GetMovieListUseCase(private val movieRepository: MovieRepository) {

    suspend fun getPopularMovies(page: Int): List<Movie> {
        return movieRepository.getPopularMovies(page)
    }

    suspend fun getTopRatedMovies(page: Int): List<Movie> {
        return movieRepository.getTopRatedMovies(page)
    }

    suspend fun getUpcomingMovies(page: Int): List<Movie> {
        return movieRepository.getUpcomingMovies(page)
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

    suspend fun getVideoList(movieId: Int): ArrayList<VideoUI>{
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