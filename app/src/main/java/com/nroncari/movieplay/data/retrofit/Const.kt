package com.nroncari.movieplay.data.retrofit

object RetrofitConst {
    const val BASE_URL = "https://api.themoviedb.org/3/"
    const val LANGUAGE = "pt-BR"
    const val TAG = "retrofit"
    const val CACHE_SIZE = 5 * 1024 * 1024L // 5 MB de cache
    const val TIMEOUT : Long = 30
}

object ErrorMessagesConst {
    const val REQUISITION_CODE = "code:"
    const val TIMEOUT_ERROR = "Timeout: failed to connect to server"
    const val CONNECT_EXCEPTION_ERROR = "Network: failed to connect to server, verify your connexion"
    const val UNEXPECTED_ERROR = "Unexpected error"
    const val NULL_VALUE_ERROR = "Null value error from server"
}