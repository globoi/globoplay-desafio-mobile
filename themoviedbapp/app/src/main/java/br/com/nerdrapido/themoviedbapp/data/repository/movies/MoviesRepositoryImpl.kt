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
import br.com.nerdrapido.themoviedbapp.data.repository.abstracts.AbstractMovieDbApiRepos
import br.com.nerdrapido.themoviedbapp.data.repository.session.SessionRepository
import br.com.nerdrapido.themoviedbapp.domain.usecase.GetLanguageUseCase
import retrofit2.Retrofit

/**
 * Created By FELIPE GUSBERTI @ 13/03/2020
 */
class MoviesRepositoryImpl(
    private val getLanguageUseCase: GetLanguageUseCase,
    private val sessionRepository: SessionRepository,
    retrofit: Retrofit
) :
    AbstractMovieDbApiRepos(retrofit),
    MoviesRepository {

    private val service = retrofit.create(MoviesService::class.java)


    override suspend fun getMovie(movieRequest: MovieRequest): ResponseWrapper<MovieResponse> {
        return safeApiCall(dispatcher) {
            service.moviesDetail(
                movieRequest.movieId.toString(),
                movieRequest.language
            )
        }
    }

    override suspend fun getMovieRecommendations(recommendationRequest: RecommendationRequest): ResponseWrapper<RecommendationResponse> {
        return safeApiCall(dispatcher) {
            service.moviesRecommendation(
                recommendationRequest.movieId.toString(),
                getLanguageUseCase.getLanguage(),
                recommendationRequest.page
            )
        }
    }

    override suspend fun getMovieAccountState(accountStatesRequest: MovieAccountStatesRequest): ResponseWrapper<MovieAccountStateResponse> {
        return safeApiCall(dispatcher) {
            service.movieAccountState(
                accountStatesRequest.movieId.toString(), sessionRepository.getSessionId()
            )
        }
    }

    override suspend fun getMovieVideos(movieVideoRequest: MovieVideoRequest): ResponseWrapper<MovieVideoResponse> {
        return safeApiCall(dispatcher) {
            service.movieVideos(
                movieVideoRequest.movieId.toString(),
                getLanguageUseCase.getLanguage()
            )
        }
    }
}