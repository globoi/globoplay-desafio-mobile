package com.com.ifood.application

import android.app.Application
import com.com.ifood.repository.room.initDataBase

class MovieApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        initDataBase(applicationContext)
    }
}