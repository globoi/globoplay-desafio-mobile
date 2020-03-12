package br.com.nerdrapido.themoviedbapp.data.model.login

import com.google.gson.annotations.SerializedName
import retrofit2.http.Field

/**
 * Created By FELIPE GUSBERTI @ 04/03/2020
 */
class DeleteAccessTokenResponse(
    @Field("success")
    @SerializedName("success")
    val success: Boolean
)
