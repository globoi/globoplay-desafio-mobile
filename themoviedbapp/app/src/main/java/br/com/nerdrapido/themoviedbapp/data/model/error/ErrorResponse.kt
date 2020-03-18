package br.com.nerdrapido.themoviedbapp.data.model.error

import com.google.gson.annotations.SerializedName

/**
 * Created By FELIPE GUSBERTI @ 16/03/2020
 */
data class ErrorResponse(
    @SerializedName("status_code")
    val statusCode : Int?,
    @SerializedName("status_message")
    val statusMessage : String?
)