package com.example.pokedex.data.model

data class PokemonResponse(
    val count: Int,
    val next: String,
    val previous: Any,
    var results: List<Result>
)