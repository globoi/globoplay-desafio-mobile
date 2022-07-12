package com.nroncari.movieplay.data.remotedatasource.retrofit

object RetrofitConst {
    const val BASE_URL = "https://api.themoviedb.org/3/"
    const val LANGUAGE = "pt-BR"
    const val CACHE_SIZE = 5 * 1024 * 1024L // 5 MB de cache
    const val TIMEOUT : Long = 30
}