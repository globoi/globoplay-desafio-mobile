package br.com.nerdrapido.themoviedbapp.data.repository.movies

import br.com.nerdrapido.themoviedbapp.data.model.movie.MovieRequest
import br.com.nerdrapido.themoviedbapp.data.model.movie.MovieResponse
import br.com.nerdrapido.themoviedbapp.data.model.recommendation.RecommendationRequest
import br.com.nerdrapido.themoviedbapp.data.model.recommendation.RecommendationResponse

/**
 * Created By FELIPE GUSBERTI @ 13/03/2020
 */
interface MoviesRepository {

    suspend fun getMovie(movieRequest: MovieRequest) : MovieResponse

    suspend fun getMovieRecommendations(recommendationRequest: RecommendationRequest): RecommendationResponse
}