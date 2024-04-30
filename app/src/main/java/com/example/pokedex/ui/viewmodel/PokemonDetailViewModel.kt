package com.example.pokedex.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokedex.data.PokemonListRepository
import com.example.pokedex.data.model.Pokemon
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonDetailViewModel @Inject constructor(private val repository: PokemonListRepository) :
    ViewModel() {
    private val _pokemon = MutableLiveData<Pokemon>()

    val pokemon: LiveData<Pokemon> = _pokemon


    fun fetchPokemon(searchId: String) {
        viewModelScope.launch {
            try {
                val pokemon = repository.getPokemon(searchId)
                _pokemon.value = pokemon
            } catch (e: Exception) {
                // Handle error
            }
        }
    }
}