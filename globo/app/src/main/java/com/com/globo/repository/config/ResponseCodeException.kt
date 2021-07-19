package com.com.globo.repository.config

import java.lang.IllegalStateException

data class ResponseCodeException(val responseCode: Int) : IllegalStateException()