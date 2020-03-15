package br.com.nerdrapido.themoviedbapp.domain.usecase

import br.com.nerdrapido.themoviedbapp.data.model.movie.MovieRequest
import br.com.nerdrapido.themoviedbapp.data.model.movie.MovieResponse
import br.com.nerdrapido.themoviedbapp.data.model.recommendation.RecommendationRequest
import br.com.nerdrapido.themoviedbapp.data.model.recommendation.RecommendationResponse
import br.com.nerdrapido.themoviedbapp.data.repository.movies.MoviesRepository
import br.com.nerdrapido.themoviedbapp.data.repository.session.SessionRepository

/**
 * Created By FELIPE GUSBERTI @ 13/03/2020
 */
class GetMovieUseCase(
    private val getLanguageUseCase: GetLanguageUseCase,
    private val moviesRepository: MoviesRepository
) {

    suspend fun getMovieById(id: Int): MovieResponse {
        val movieRequest = MovieRequest(id, getLanguageUseCase.getLanguage())
        return moviesRepository.getMovie(movieRequest)
    }

    suspend fun getMovieRecommendationByMovieId(id: Int, page: Int): RecommendationResponse {
        val recommendationRequest =
            RecommendationRequest(id, getLanguageUseCase.getLanguage(), page)
        return moviesRepository.getMovieRecommendations(recommendationRequest)
    }

}