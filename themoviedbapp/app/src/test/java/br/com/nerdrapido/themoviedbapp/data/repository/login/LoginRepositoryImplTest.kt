package br.com.nerdrapido.themoviedbapp.data.repository.login

import br.com.nerdrapido.themoviedbapp.data.model.login.CreateSessionRequest
import br.com.nerdrapido.themoviedbapp.data.model.login.DeleteAccessTokenRequest
import br.com.nerdrapido.themoviedbapp.data.model.login.RequestTokenRequest
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
 * Created By FELIPE GUSBERTI @ 08/03/2020
 */
internal class LoginRepositoryImplTest : AbstractMovieDbApiReposTest() {

    private val repos: LoginRepository by inject()

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
    fun `createrequestToken test if error is treated`() {
        runBlocking {
            throwWhenNotApiError(
                repos.createRequestToken(
                    RequestTokenRequest()
                )
            )
        }
    }

    @Test
    fun `createSession test if error is treated`() {
        runBlocking {
            throwWhenNotApiError(
                repos.createSession(
                    CreateSessionRequest(
                        ""
                    )
                )
            )
        }
    }

    @Test
    fun `deleteAccessToken test if error is treated`() {
        runBlocking {
            throwWhenNotApiError(
                repos.deleteAccessToken(
                    DeleteAccessTokenRequest(
                        ""
                    )
                )
            )
        }
    }


}