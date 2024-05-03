package com.example.pokedex.service

import com.example.pokedex.data.model.Pokemon
import com.example.pokedex.data.model.PokemonResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokemonListService {
    @GET("pokemon?limit=100")
    suspend fun getPokemonList(@Query("offset") offset: String = "0"): PokemonResponse

    @GET("pokemon")
    suspend fun getPokemonList(): PokemonResponse

    @GET("pokemon/{id}")
    suspend fun getPokemon(@Path("id") searchById: String): Pokemon
}
