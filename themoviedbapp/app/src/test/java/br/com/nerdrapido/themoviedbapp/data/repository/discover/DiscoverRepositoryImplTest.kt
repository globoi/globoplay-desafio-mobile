package br.com.nerdrapido.themoviedbapp.data.repository.discover

import br.com.nerdrapido.themoviedbapp.data.model.discover.DiscoverRequest
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
internal class DiscoverRepositoryImplTest : AbstractMovieDbApiReposTest() {

    private val repos: DiscoverRepository by inject()


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
    fun `loadDiscover() test if error is treated`() {
        runBlocking {
            throwWhenNotApiError(
                repos.loadDiscover(
                    DiscoverRequest(
                        "", 1, "", "", ""
                    )
                )
            )
        }
    }
}