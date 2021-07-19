package com.com.globo.application

import android.app.Application
import com.com.globo.repository.room.initDataBase

class MovieApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        initDataBase(applicationContext)
    }
}