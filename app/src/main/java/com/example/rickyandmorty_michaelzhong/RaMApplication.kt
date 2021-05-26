package com.example.rickyandmorty_michaelzhong

import android.app.Application
import com.example.rickyandmorty_michaelzhong.model.db.CacheDB

class RaMApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        CacheDB.create(this)
    }
}