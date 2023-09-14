package com.pokedex.api.model

import com.pokedex.domain.PokemonType

data class PokemonsApiResult (
    val count: Number,
    val next: String?,
    val previous: String?,
    val results: List<PokemonResult>
)

data class PokemonResult (
    val name: String,
    val url: String
)

data class PokemonApiResult (
    val id: Int,
    val name: String,
    val types: List<PokemonTypes>
)

data class PokemonTypes (
    val slot: Int,
    val type: PokemonType
)