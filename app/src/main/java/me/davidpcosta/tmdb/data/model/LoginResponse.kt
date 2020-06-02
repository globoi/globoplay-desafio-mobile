package me.davidpcosta.tmdb.data.model

data class LoginResponse (
    var success: Boolean? = null,
    var sessionId: String? = null,
    var accountId: Long? = null,
    var errorMessage: String? = null
)