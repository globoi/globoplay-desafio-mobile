package br.com.nerdrapido.themoviedbapp.domain.usecase

import br.com.nerdrapido.themoviedbapp.data.model.ResponseWrapper
import br.com.nerdrapido.themoviedbapp.data.model.movie.MovieRequest
import br.com.nerdrapido.themoviedbapp.data.model.movie.MovieResponse
import br.com.nerdrapido.themoviedbapp.data.model.movieaccountstates.MovieAccountStateResponse
import br.com.nerdrapido.themoviedbapp.data.model.movieaccountstates.MovieAccountStatesRequest
import br.com.nerdrapido.themoviedbapp.data.model.movievideo.MovieVideoRequest
import br.com.nerdrapido.themoviedbapp.data.model.recommendation.RecommendationRequest
import br.com.nerdrapido.themoviedbapp.data.model.recommendation.RecommendationResponse
import br.com.nerdrapido.themoviedbapp.data.repository.movies.MoviesRepository
import br.com.nerdrapido.themoviedbapp.data.repository.session.SessionRepository

/**
 * Created By FELIPE GUSBERTI @ 13/03/2020
 */
class MovieUseCase(
    private val sessionRepository: SessionRepository,
    private val getLanguageUseCase: GetLanguageUseCase,
    private val moviesRepository: MoviesRepository
) {

    suspend fun getMovieById(id: Int): ResponseWrapper<MovieResponse> {
        val movieRequest = MovieRequest(id, getLanguageUseCase.getLanguage())
        return moviesRepository.getMovie(movieRequest)
    }

    suspend fun getMovieRecommendationByMovieId(id: Int, page: Int): ResponseWrapper<RecommendationResponse> {
        val recommendationRequest =
            RecommendationRequest(id, getLanguageUseCase.getLanguage(), page)
        return moviesRepository.getMovieRecommendations(recommendationRequest)
    }

    suspend fun getMovieAccountState(id: Int): ResponseWrapper<MovieAccountStateResponse> {
        return moviesRepository.getMovieAccountState(
            MovieAccountStatesRequest(
                id,
                sessionRepository.getSessionId()
            )
        )
    }

    suspend fun getMovieVideoUrl(id: Int): String? {
        val videos = moviesRepository.getMovieVideos(
            MovieVideoRequest(
                id,
                getLanguageUseCase.getLanguage()
            )
        )
        when (videos) {
            is ResponseWrapper.NetworkError -> return null
            is ResponseWrapper.GenericError -> return null
            is ResponseWrapper.Success -> {
                videos.value.results.forEach {
                    if(it.site == "YouTube" && it.type == "Trailer") {
                        return it.key
                    }
                }
                return null
            }
            else -> return null
        }
    }

}