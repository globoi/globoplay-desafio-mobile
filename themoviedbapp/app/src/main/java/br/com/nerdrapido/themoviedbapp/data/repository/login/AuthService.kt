package br.com.nerdrapido.themoviedbapp.data.repository.login

import br.com.nerdrapido.themoviedbapp.data.model.login.*
import retrofit2.http.*

/**
 * Created By FELIPE GUSBERTI @ 04/03/2020
 */
interface AuthService {

    @GET("authentication/token/new")
    suspend fun createRequestToken(): RequestTokenResponse

    @POST("authentication/session/new")
    suspend fun createSession(@Body createSessionRequest: CreateSessionRequest): CreateSessionResponse

    /**
     * Deletes session.
     * [@DELETE] workaround: https://stackoverflow.com/a/37944875
     */
    @HTTP(method = "DELETE", path = "authentication/session", hasBody = true)
    suspend fun deleteAccessToken(@Body deleteAccessTokenRequest: DeleteAccessTokenRequest): DeleteAccessTokenResponse
}