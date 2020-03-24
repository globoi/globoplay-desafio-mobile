package br.com.nerdrapido.themoviedbapp.data.repository.movies

import br.com.nerdrapido.themoviedbapp.data.model.movie.MovieRequest
import br.com.nerdrapido.themoviedbapp.data.model.movieaccountstates.MovieAccountStatesRequest
import br.com.nerdrapido.themoviedbapp.data.model.movievideo.MovieVideoRequest
import br.com.nerdrapido.themoviedbapp.data.model.recommendation.RecommendationRequest
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
internal class MoviesRepositoryImplTest : AbstractMovieDbApiReposTest() {

    private val repos: MoviesRepository by inject()


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
    fun `getMovie() test if error is treated`() {
        runBlocking {
            throwWhenNotApiError(
                repos.getMovie(
                    MovieRequest(
                        1, ""
                    )
                )
            )
        }
    }

    @Test
    fun `getMovieRecommendations() test if error is treated`() {
        runBlocking {
            throwWhenNotApiError(
                repos.getMovieRecommendations(
                    RecommendationRequest(
                        1, "", 1
                    )
                )
            )
        }
    }

    @Test
    fun `getMovieAccountState() test if error is treated`() {
        runBlocking {
            throwWhenNotApiError(
                repos.getMovieAccountState(
                    MovieAccountStatesRequest(
                        1, ""
                    )
                )
            )
        }
    }

    @Test
    fun `getMovieVideos() test if error is treated`() {
        runBlocking {
            throwWhenNotApiError(
                repos.getMovieVideos(
                    MovieVideoRequest(
                        1, ""
                    )
                )
            )
        }
    }
}