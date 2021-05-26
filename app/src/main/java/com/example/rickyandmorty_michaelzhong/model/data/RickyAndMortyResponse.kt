package com.example.rickyandmorty_michaelzhong.model.data

import com.google.gson.annotations.SerializedName

data class RickyAndMortyResponse(
    val info: Info,
    @SerializedName("results")
    val characters: List<Character>
)