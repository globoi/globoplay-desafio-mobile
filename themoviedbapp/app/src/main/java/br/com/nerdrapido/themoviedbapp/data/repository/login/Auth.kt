package br.com.nerdrapido.themoviedbapp.data.repository.login

import br.com.nerdrapido.themoviedbapp.data.model.login.*
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.POST

/**
 * Created By FELIPE GUSBERTI @ 04/03/2020
 */
interface Auth {

    @POST("auth/request_token")
    suspend fun createRequestToken(@Body requestTokenRequest: RequestTokenRequest): RequestTokenResponse

    @POST("auth/access_token")
    suspend fun createAccessToken(@Body accessTokenRequest: AccessTokenRequest): AccessTokenResponse

    @DELETE("auth/access_token")
    suspend fun deleteAccessToken(@Body deleteAccessTokenRequest: DeleteAccessTokenRequest): DeleteAccesTokenResponse
}