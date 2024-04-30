package com.example.pokedex.data

import com.example.pokedex.data.model.Pokemon
import com.example.pokedex.data.model.PokemonResponse
import com.example.pokedex.service.PokemonListService
import javax.inject.Inject

class PokemonListRepository @Inject constructor(
    private val pokemonListService: PokemonListService
) {
    suspend fun getPokemonList(): PokemonResponse {
        return pokemonListService.getPokemonList()
    }

    suspend fun getPokemonList(offset: String): PokemonResponse {
        return pokemonListService.getPokemonList(offset)
    }

    suspend fun getPokemon(searchId: String): Pokemon {
        return pokemonListService.getPokemon(searchId)
    }
}
