package br.com.nerdrapido.themoviedbapp.domain.usecase

import br.com.nerdrapido.themoviedbapp.data.model.movie.MovieRequest
import br.com.nerdrapido.themoviedbapp.data.model.movie.MovieResponse
import br.com.nerdrapido.themoviedbapp.data.model.recommendation.RecommendationRequest
import br.com.nerdrapido.themoviedbapp.data.model.recommendation.RecommendationResponse
import br.com.nerdrapido.themoviedbapp.data.repository.movies.MoviesRepository

/**
 * Created By FELIPE GUSBERTI @ 13/03/2020
 */
class GetMovieUseCase(private val moviesRepository: MoviesRepository) {

    suspend fun getMovieById(id: Int): MovieResponse {
        val movieRequest = MovieRequest(id, "pt-BR")
        return moviesRepository.getMovie(movieRequest)
    }

    suspend fun getMovieRecommendationByMovieId(id: Int, page: Int): RecommendationResponse {
        val recommendationRequest = RecommendationRequest(id, "pt-BR", page)
        return moviesRepository.getMovieRecommendations(recommendationRequest)
    }

}