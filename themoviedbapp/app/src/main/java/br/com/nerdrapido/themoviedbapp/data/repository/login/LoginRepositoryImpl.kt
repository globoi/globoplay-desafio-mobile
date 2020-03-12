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

    override suspend fun createRequestToken(requestTokenRequest: RequestTokenRequest) = authService
        .createRequestToken()

    override suspend fun createSession(createSessionRequest: CreateSessionRequest) = authService
        .createSession(createSessionRequest)

    override suspend fun deleteAccessToken(deleteAccessTokenRequest: DeleteAccessTokenRequest)
            = authService.deleteAccessToken(deleteAccessTokenRequest)
}
