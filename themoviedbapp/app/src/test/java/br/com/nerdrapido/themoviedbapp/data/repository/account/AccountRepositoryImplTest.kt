package br.com.nerdrapido.themoviedbapp.data.repository.account

import br.com.nerdrapido.themoviedbapp.data.model.account.AccountRequest
import br.com.nerdrapido.themoviedbapp.data.model.addfavorite.PostFavoriteRequest
import br.com.nerdrapido.themoviedbapp.data.model.addwatchlist.PostWatchlistRequest
import br.com.nerdrapido.themoviedbapp.data.model.favoritemovies.FavoriteMoviesRequest
import br.com.nerdrapido.themoviedbapp.data.model.watchlistmovies.WatchlistMoviesRequest
import br.com.nerdrapido.themoviedbapp.data.repository.abstracts.AbstractMovieDbApiReposTest
import br.com.nerdrapido.themoviedbapp.domain.retrofit.MockErrorServiceInterceptor
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.koin.core.module.Module
import org.koin.dsl.module
import org.koin.test.inject


/**
 * Created By FELIPE GUSBERTI @ 16/03/2020
 */
internal class AccountRepositoryImplTest : AbstractMovieDbApiReposTest() {

    private val accountRepository: AccountRepository by inject()

    @BeforeEach
    override fun setUp() {
        super.setUp()
    }


    @AfterEach
    override fun afterEach() {
        super.afterEach()
    }

    override fun getOverrideModules(): Module {
        return module {
            single<Interceptor>(override = true) { MockErrorServiceInterceptor() }
        }
    }

    @Test
    fun `getAccount test if error is treated`() {
        runBlocking {
            throwWhenNotApiError(
                accountRepository.getAccount(
                    AccountRequest(
                        ""
                    )
                )
            )
        }
    }

    @Test
    fun `getFavoriteMovies test if error is treated`() {
        runBlocking {
            throwWhenNotApiError(
                accountRepository.getFavoriteMovies(
                    FavoriteMoviesRequest(
                        "1", "1", "1", "1", 1
                    )
                )
            )
        }
    }

    @Test
    fun `getWatchlistMovies test if error is treated`() {
        runBlocking {
            throwWhenNotApiError(
                accountRepository.getWatchlistMovies(
                    WatchlistMoviesRequest(
                        "1", "1", "1", "1", 1
                    )
                )
            )
        }
    }

    @Test
    fun `markMovieToFavorite test if error is treated`() {
        runBlocking {
            throwWhenNotApiError(
                accountRepository.markMovieToFavorite(
                    PostFavoriteRequest(
                        "", 1, true
                    )
                )
            )
        }
    }

    @Test
    fun `addMovieToWatchlist test if error is treated`() {
        runBlocking {
            throwWhenNotApiError(
                accountRepository.addMovieToWatchlist(
                    PostWatchlistRequest(
                        "", 1, true
                    )
                )
            )
        }
    }
}