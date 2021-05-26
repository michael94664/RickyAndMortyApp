package com.example.rickyandmorty_michaelzhong.model

import com.example.rickyandmorty_michaelzhong.model.data.Character
import com.example.rickyandmorty_michaelzhong.model.data.RickyAndMortyResponse
import com.example.rickyandmorty_michaelzhong.model.db.CacheDB
import com.example.rickyandmorty_michaelzhong.model.db.CacheItem
import com.example.rickyandmorty_michaelzhong.model.network.RaMRetrofit
import com.example.rickyandmorty_michaelzhong.util.Logger.Companion.logDebug
import com.google.gson.Gson

class Repository() {
    companion object{
        const val CACHE_ID = 1
    }
    private val raMRetrofit: RaMRetrofit = RaMRetrofit()
    fun getCharactersOnlineAsync() = raMRetrofit.getAllCharactersAsync()
    fun getCharactersOffline(): RickyAndMortyResponse {

        val saved = CacheDB.getDao().getLastCache()
        saved?.cacheValue?.let {
            return Gson().fromJson(it, RickyAndMortyResponse::class.java)
        }
    }

    fun saveForOffline(response: RickyAndMortyResponse) {
        val gson = Gson()
        val cacheItem = CacheItem(CACHE_ID, gson.toJson(response))
        CacheDB.getDao().updateCache(cacheItem)
        logDebug("Saved to cache db :)")
    }
}