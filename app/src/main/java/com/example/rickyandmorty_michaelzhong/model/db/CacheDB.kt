package com.example.rickyandmorty_michaelzhong.model.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(version = 1, entities = [CacheItem::class])
abstract class CacheDB: RoomDatabase() {

    abstract fun getDao(): CacheDAO
    companion object{
        lateinit var cache: CacheDB
        fun create(context: Context){
            cache = Room.databaseBuilder(
                context,
                CacheDB::class.java,
                "Cache.db"
            ).build()

            cache
        }
        fun getDao(): CacheDAO = cache.getDao()
    }
}