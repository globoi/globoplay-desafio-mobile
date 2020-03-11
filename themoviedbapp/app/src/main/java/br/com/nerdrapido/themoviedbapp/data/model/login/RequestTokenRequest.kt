package br.com.nerdrapido.themoviedbapp.data.model.login

import com.google.gson.annotations.SerializedName
import retrofit2.http.Field

/**
 * Created By FELIPE GUSBERTI @ 04/03/2020
 *
 * https://developers.themoviedb.org/4/auth/create-request-token
 */
data class RequestTokenRequest(
    @Field("redirect_to")
    @SerializedName("redirect_to")
    val redirectTo: String?
)