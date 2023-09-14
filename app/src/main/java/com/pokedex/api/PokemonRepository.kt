package com.pokedex.api

import com.pokedex.api.model.PokemonApiResult
import com.pokedex.api.model.PokemonsApiResult
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object PokemonRepository {
    private val service: PokemonService

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://pokeapi.co/api/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        service = retrofit.create(PokemonService::class.java)
    }

    fun listPokemons(limit: Int = 15): PokemonsApiResult? {
        val call = service.listPokemons(limit)

        return call.execute().body()
    }

    fun getPokemon(id: Int): PokemonApiResult? {
        val call = service.getPokemon(id)

        return call.execute().body()
    }
}