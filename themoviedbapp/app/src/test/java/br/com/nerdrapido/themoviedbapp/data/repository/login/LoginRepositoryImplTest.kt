package br.com.nerdrapido.themoviedbapp.data.repository.login

import br.com.nerdrapido.themoviedbapp.data.model.login.RequestTokenRequest
import br.com.nerdrapido.themoviedbapp.di.KoinManager
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.koin.test.inject


/**
 * Created By FELIPE GUSBERTI @ 08/03/2020
 */
internal class LoginRepositoryImplTest : KoinTest {

    val loginRepository: LoginRepository by inject()

    @BeforeEach
    fun setUp() {
        startKoin {
            modules(KoinManager.getApplicationModules())
        }
    }

    @AfterEach
    fun afterEach() {
        stopKoin()
    }

    @Test
    fun createrequestToken() {
        val accesResponse = runBlocking {
            loginRepository.createRequestToken(
                requestTokenRequest = RequestTokenRequest(
                    ""
                )
            )
        }
        accesResponse.toString()
    }

    @Test
    fun createAccessToken() {
    }

    @Test
    fun deleteAccessToken() {
    }
}