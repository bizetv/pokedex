package com.example.pokedex.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokedex.data.PokemonListRepository
import com.example.pokedex.data.model.PokemonResponse
import com.example.pokedex.data.model.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonListViewModel @Inject constructor(private val repository: PokemonListRepository) :
    ViewModel() {
    private val _pokemon = MutableLiveData<List<Result>>()
    val pokemonList: LiveData<List<Result>> = _pokemon
    //val pokemonListFlow: Flow<List<Result>>

    init {
        fetchPokemonList(0)
    }

    /*fun fetchPokemonList() {
        viewModelScope.launch {
            try {
                val pokemon = repository.getPokemonList()
                _pokemon.value = pokemon.results
                if (pokemon.next!=null){
                    fetchPokemonList(20)
                }
            } catch (e: Exception) {
                // Handle error
            }
        }
    }*/

    fun fetchPokemonList(offset: Int) {
        viewModelScope.launch {
            try {
                val pokemon = repository.getPokemonList(offset.toString())
                _pokemon.value!!.plus(pokemon.results)
                if (pokemon.next!=null){
                    fetchPokemonList((offset +100))
                }
            } catch (e: Exception) {
                // Handle error
            }
        }
    }
}