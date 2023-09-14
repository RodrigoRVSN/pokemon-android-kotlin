package com.pokedex.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pokedex.api.PokemonRepository
import com.pokedex.domain.Pokemon

class PokemonViewModel: ViewModel() {
   var pokemons = MutableLiveData<List<Pokemon?>>()

    init {
        Thread {
            loadPokemons()
        }.start()
    }

    private fun loadPokemons() {
        val pokemonsApiResult = PokemonRepository.listPokemons()

        pokemonsApiResult?.results?.let {
            pokemons.postValue(it.map { pokemonResult ->
                val id = pokemonResult.url
                    .replace("https://pokeapi.co/api/v2/pokemon/", "")
                    .replace("/", "").toInt()
                val pokemonApi = PokemonRepository.getPokemon(id)

                pokemonApi?.let {
                    Pokemon(
                        pokemonApi.id,
                        pokemonApi.name,
                        pokemonApi.types.map { type -> type.type },
                    )
                }
            })
        }
    }

}
