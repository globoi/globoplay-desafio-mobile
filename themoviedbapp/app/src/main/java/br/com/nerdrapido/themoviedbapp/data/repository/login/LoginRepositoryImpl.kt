package br.com.nerdrapido.themoviedbapp.data.repository.login

import br.com.nerdrapido.themoviedbapp.data.model.login.CreateSessionRequest
import br.com.nerdrapido.themoviedbapp.data.model.login.DeleteAccessTokenRequest
import br.com.nerdrapido.themoviedbapp.data.model.login.RequestTokenRequest
import br.com.nerdrapido.themoviedbapp.data.repository.abstracts.AbstractMovieDbApiRepos
import retrofit2.Retrofit

/**
 * Created By FELIPE GUSBERTI @ 05/03/2020
 */
class LoginRepositoryImpl(retrofit: Retrofit) : AbstractMovieDbApiRepos(retrofit), LoginRepository {

    private val authService: AuthService = retrofit.create(AuthService::class.java)

    override suspend fun createRequestToken(requestTokenRequest: RequestTokenRequest) =
        safeApiCall(dispatcher) {
            authService
                .createRequestToken()
        }

    override suspend fun createSession(createSessionRequest: CreateSessionRequest) =
        safeApiCall(dispatcher) {
            authService
                .createSession(createSessionRequest)
        }

    override suspend fun deleteAccessToken(deleteAccessTokenRequest: DeleteAccessTokenRequest) =
        safeApiCall(dispatcher) { authService.deleteAccessToken(deleteAccessTokenRequest) }
}
