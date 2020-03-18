package br.com.nerdrapido.themoviedbapp.domain.usecase

import br.com.nerdrapido.themoviedbapp.data.model.MediaTypes
import br.com.nerdrapido.themoviedbapp.data.model.ResponseWrapper
import br.com.nerdrapido.themoviedbapp.data.model.addfavorite.PostFavoriteRequest
import br.com.nerdrapido.themoviedbapp.data.model.addfavorite.PostFavoriteResponse
import br.com.nerdrapido.themoviedbapp.data.model.common.MovieListResultObject
import br.com.nerdrapido.themoviedbapp.data.model.favoritemovies.FavoriteMoviesRequest
import br.com.nerdrapido.themoviedbapp.data.model.favoritemovies.FavoriteMoviesResponse
import br.com.nerdrapido.themoviedbapp.data.repository.account.AccountRepository
import br.com.nerdrapido.themoviedbapp.data.repository.session.SessionRepository
import java.sql.Wrapper

/**
 * Created By FELIPE GUSBERTI @ 15/03/2020
 */
class FavoriteMoviesUseCase(
    private val getLanguageUseCase: GetLanguageUseCase,
    private val sessionRepository: SessionRepository,
    private val accountRepository: AccountRepository
) {

    suspend fun getFavoriteMovies(page: Int): ResponseWrapper<FavoriteMoviesResponse> {
        return accountRepository.getFavoriteMovies(
            FavoriteMoviesRequest(
                sessionRepository.getAccountId().toString(),
                sessionRepository.getSessionId() ?: "",
                getLanguageUseCase.getLanguage(),
                "created_at.desc",
                page
            )
        )
//        when (response) {
//            is ResponseWrapper.NetworkError -> return emptyList()
//            is ResponseWrapper.GenericError -> return emptyList()
//            is ResponseWrapper.Success -> return response.value.results ?: emptyList()
//        }
    }

    /**
     * Save a [MovieListResultObject] to a favorite list.
     *
     * TODO: make null treatment better
     */
    suspend fun addMovieToFavorite(movieListResultObject: MovieListResultObject): ResponseWrapper<PostFavoriteResponse>? {
        return movieListResultObject.id?.let {
            return accountRepository.markMovieToFavorite(
                PostFavoriteRequest(
                    MediaTypes.MOVIE.description,
                    it,
                    true
                )
            )
        }
    }
}