package com.pokedex.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.pokedex.R
import com.pokedex.domain.Pokemon
import com.pokedex.viewmodel.PokemonViewModel
import com.pokedex.viewmodel.PokemonViewModelFactory

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView

    val viewModel by lazy {
        ViewModelProvider(this, PokemonViewModelFactory())[PokemonViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.rvPokemons)

        viewModel.pokemons.observe(this) {
            loadRecyclerView(it)
        }
    }

    private fun loadRecyclerView(pokemons: List<Pokemon?>) {
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = PokemonAdapter(pokemons)
    }
}