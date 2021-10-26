package com.com.ifood.repository.config

import java.lang.IllegalStateException

data class ResponseCodeException(val responseCode: Int) : IllegalStateException()