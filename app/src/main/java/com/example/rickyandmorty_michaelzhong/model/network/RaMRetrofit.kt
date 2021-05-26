package com.example.rickyandmorty_michaelzhong.model.network

import com.example.rickyandmorty_michaelzhong.model.data.RickyAndMortyResponse
import com.example.rickyandmorty_michaelzhong.util.Constants.Companion.BASE_URL
import com.example.rickyandmorty_michaelzhong.util.Constants.Companion.END_POINT
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Deferred
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

class RaMRetrofit {

    private val raMEndpointService = createRetrofitInstance().create(RaMEndpointService::class.java)

    private fun createRetrofitInstance(): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun getAllCharactersAsync(): Deferred<RickyAndMortyResponse> = raMEndpointService.getCharactersAsync()

    interface RaMEndpointService {
        @GET(END_POINT)
        fun getCharactersAsync(): Deferred<RickyAndMortyResponse>
    }
}