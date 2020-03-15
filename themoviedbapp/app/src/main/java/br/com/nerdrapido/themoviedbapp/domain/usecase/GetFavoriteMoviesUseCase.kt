package br.com.nerdrapido.themoviedbapp.domain.usecase

import br.com.nerdrapido.themoviedbapp.data.model.common.MovieListResultObject
import br.com.nerdrapido.themoviedbapp.data.model.favoritemovies.FavoriteMoviesRequest
import br.com.nerdrapido.themoviedbapp.data.model.favoritemovies.FavoriteMoviesResponse
import br.com.nerdrapido.themoviedbapp.data.repository.account.AccountRepository
import br.com.nerdrapido.themoviedbapp.data.repository.session.SessionRepository

/**
 * Created By FELIPE GUSBERTI @ 15/03/2020
 */
class GetFavoriteMoviesUseCase(
    private val getLanguageUseCase: GetLanguageUseCase,
    private val sessionRepository: SessionRepository,
    private val accountRepository: AccountRepository
) {

    suspend fun getFavoriteMovies(page: Int): List<MovieListResultObject> {
        return accountRepository.getFavoriteMovies(
            FavoriteMoviesRequest(
                sessionRepository.getAccountId().toString(),
                sessionRepository.getSessionId() ?: "",
                getLanguageUseCase.getLanguage(),
                "created_at.desc",
                page
            )
        ).results ?: emptyList()
    }
}