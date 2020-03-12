package br.com.nerdrapido.themoviedbapp.data.model.login

import com.google.gson.annotations.SerializedName

/**
 * Created By FELIPE GUSBERTI @ 04/03/2020
 */
data class CreateSessionResponse (
    @SerializedName("status_code")
    val statusCode: Int?,
    @SerializedName("session_id")
    val sessionId: String?
)