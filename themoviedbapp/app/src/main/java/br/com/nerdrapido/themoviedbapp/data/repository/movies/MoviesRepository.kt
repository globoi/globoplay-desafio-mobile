package br.com.nerdrapido.themoviedbapp.data.repository.movies

import br.com.nerdrapido.themoviedbapp.data.model.movie.MovieRequest
import br.com.nerdrapido.themoviedbapp.data.model.movie.MovieResponse
import br.com.nerdrapido.themoviedbapp.data.model.movieaccountstates.MovieAccountStateResponse
import br.com.nerdrapido.themoviedbapp.data.model.movieaccountstates.MovieAccountStatesRequest
import br.com.nerdrapido.themoviedbapp.data.model.movievideo.MovieVideoRequest
import br.com.nerdrapido.themoviedbapp.data.model.movievideo.MovieVideoResponse
import br.com.nerdrapido.themoviedbapp.data.model.recommendation.RecommendationRequest
import br.com.nerdrapido.themoviedbapp.data.model.recommendation.RecommendationResponse

/**
 * Created By FELIPE GUSBERTI @ 13/03/2020
 */
interface MoviesRepository {

    suspend fun getMovie(movieRequest: MovieRequest): MovieResponse

    suspend fun getMovieRecommendations(recommendationRequest: RecommendationRequest): RecommendationResponse

    suspend fun getMovieAccountState(accountStatesRequest: MovieAccountStatesRequest): MovieAccountStateResponse

    suspend fun getMovieVideos(movieVideoRequest: MovieVideoRequest): MovieVideoResponse
}