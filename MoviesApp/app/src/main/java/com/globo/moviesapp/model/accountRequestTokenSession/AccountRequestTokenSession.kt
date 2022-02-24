package com.globo.moviesapp.model.accountRequestTokenSession

import java.io.Serializable

data class AccountRequestTokenSession(
    var username: String,
    var password: String,
    var request_token: String
): Serializable