package com.tiagopereira.globotmdb.application

import android.app.Application
import com.tiagopereira.globotmdb.utils.RetrofitService
import com.tiagopereira.globotmdb.viewmodel.repository.MainRepository

class MyApplication: Application() {
    private val retrofitService = RetrofitService.getInstance()
    val mainRepository by lazy { MainRepository(retrofitService) }
}