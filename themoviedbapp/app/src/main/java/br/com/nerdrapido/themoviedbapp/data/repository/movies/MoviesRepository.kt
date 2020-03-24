package br.com.nerdrapido.themoviedbapp.data.repository.movies

import br.com.nerdrapido.themoviedbapp.data.model.ResponseWrapper
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

    suspend fun getMovie(movieRequest: MovieRequest): ResponseWrapper<MovieResponse>

    suspend fun getMovieRecommendations(recommendationRequest: RecommendationRequest): ResponseWrapper<RecommendationResponse>

    suspend fun getMovieAccountState(accountStatesRequest: MovieAccountStatesRequest): ResponseWrapper<MovieAccountStateResponse>

    suspend fun getMovieVideos(movieVideoRequest: MovieVideoRequest): ResponseWrapper<MovieVideoResponse>
}