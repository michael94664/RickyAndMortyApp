package com.example.rickyandmorty_michaelzhong.model.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.example.rickyandmorty_michaelzhong.model.Repository.Companion.CACHE_ID

@Dao
interface CacheDAO {

    @Query("SELECT * FROM Cache WHERE id = $CACHE_ID")
    fun getLastCache(): CacheItem

    @Insert(onConflict = REPLACE)
    fun updateCache(cacheItem: CacheItem)
}