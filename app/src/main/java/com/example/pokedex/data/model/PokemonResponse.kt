package com.example.pokedex.data.model

data class PokemonResponse(
    val count: Int,
    val next: Any,
    val previous: Any,
    var results: List<Result>
)