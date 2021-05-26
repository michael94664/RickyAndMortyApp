package com.example.rickyandmorty_michaelzhong.model.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName = "Cache")
data class CacheItem(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Int,
    @ColumnInfo(name = "cacheValue")
    val cacheValue: String
){
    @Ignore
    constructor(cacheValue: String): this(0, cacheValue)
}