package br.com.nerdrapido.themoviedbapp.data.repository.login

import br.com.nerdrapido.themoviedbapp.data.model.login.*

/**
 * Created By FELIPE GUSBERTI @ 05/03/2020
 */
interface LoginRepository {

    suspend fun createRequestToken(requestTokenRequest: RequestTokenRequest): RequestTokenResponse

    suspend fun createSession(createSessionRequest: CreateSessionRequest): CreateSessionResponse

    suspend fun deleteAccessToken(deleteAccessTokenRequest: DeleteAccessTokenRequest): DeleteAccessTokenResponse
}