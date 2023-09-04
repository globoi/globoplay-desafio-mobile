package com.tiagopereira.globotmdb.viewmodel.repository

import com.tiagopereira.globotmdb.utils.Constants.Companion.KEY_API
import com.tiagopereira.globotmdb.utils.Constants.Companion.LANGUAGE
import com.tiagopereira.globotmdb.utils.RetrofitService

class VideoRepository constructor(private val retrofitService: RetrofitService) {

    suspend fun getTrailerById(idMovie: Int) = retrofitService.getVideoId(idMovie, KEY_API, LANGUAGE)
}