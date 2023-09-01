package com.tiagopereira.globotmdb.application

import android.app.Application
import com.tiagopereira.globotmdb.database.AppDatabase
import com.tiagopereira.globotmdb.utils.RetrofitService
import com.tiagopereira.globotmdb.viewmodel.repository.DetailsRepository
import com.tiagopereira.globotmdb.viewmodel.repository.MainRepository

class MyApplication: Application() {
    private val database by lazy { AppDatabase.getDatabase(this) }
    private val retrofitService = RetrofitService.getInstance()
    val mainRepository by lazy { MainRepository(retrofitService) }
    val detailsRepository by lazy { DetailsRepository(retrofitService, database.movieDao()) }
}