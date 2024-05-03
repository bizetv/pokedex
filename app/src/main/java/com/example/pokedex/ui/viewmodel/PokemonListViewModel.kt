package com.example.pokedex.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokedex.data.PokemonListRepository
import com.example.pokedex.data.model.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonListViewModel @Inject constructor(private val repository: PokemonListRepository) :
    ViewModel() {
    private val _pokemon = MutableLiveData<List<Result>>()
    val pokemonList: LiveData<List<Result>> = _pokemon

    fun fetchPokemonList(offset: Int) {
        viewModelScope.launch {
            try {
                val pokemon = repository.getPokemonList(offset.toString())
                if (_pokemon.value == null)
                    _pokemon.value = pokemon.results
                else
                    _pokemon.value = _pokemon.value!!.plus(pokemon.results)
                if (  pokemon.next != ""){
                    fetchPokemonList((offset +100))
                }
            } catch (e: Exception) {
                // Handle error
            }
        }
    }
}