package br.com.nerdrapido.themoviedbapp.data.repository.movies

import br.com.nerdrapido.themoviedbapp.data.model.account.AccountResponse
import br.com.nerdrapido.themoviedbapp.data.model.movie.MovieRequest
import br.com.nerdrapido.themoviedbapp.data.model.movie.MovieResponse
import br.com.nerdrapido.themoviedbapp.data.model.movieaccountstates.MovieAccountStatesRequest
import br.com.nerdrapido.themoviedbapp.data.model.recommendation.RecommendationRequest
import br.com.nerdrapido.themoviedbapp.data.model.recommendation.RecommendationResponse
import br.com.nerdrapido.themoviedbapp.data.repository.abstracts.AbstractMovieDbApiRepos
import retrofit2.Retrofit

/**
 * Created By FELIPE GUSBERTI @ 13/03/2020
 */
class MoviesRepositoryImpl(val retrofit: Retrofit) : AbstractMovieDbApiRepos(retrofit),
    MoviesRepository {

    private val service = retrofit.create(MoviesService::class.java)


    override suspend fun getMovie(movieRequest: MovieRequest): MovieResponse {
        return service.moviesDetail(movieRequest.movieId.toString(), movieRequest.language)
    }

    override suspend fun getMovieRecommendations(recommendationRequest: RecommendationRequest): RecommendationResponse {
        return service.moviesRecommendation(
            recommendationRequest.movieId.toString(),
            recommendationRequest.language,
            recommendationRequest.page
        )
    }

    override suspend fun getMovieAccountState(accountStatesRequest: MovieAccountStatesRequest): AccountResponse {
        return service.movieAccountState(accountStatesRequest.movieId, )
    }
}