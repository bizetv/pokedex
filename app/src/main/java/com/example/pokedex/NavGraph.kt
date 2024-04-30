package com.example.pokedex

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.pokedex.Destinations.POKEMON_DETAIL
import com.example.pokedex.Destinations.POKEMON_LIST
import com.example.pokedex.ui.view.PokemonDetailScreen
import com.example.pokedex.ui.view.PokemonListScreen
import com.example.pokedex.ui.viewmodel.PokemonDetailViewModel
import com.example.pokedex.ui.viewmodel.PokemonListViewModel


object Destinations {
    const val POKEMON_LIST = "pokemonList"
    const val POKEMON_DETAIL = "pokemonDetail/{id}"
}

@Composable
fun NavGraph(
    navController: NavHostController = rememberNavController())
{
    NavHost(navController = navController, startDestination = POKEMON_LIST) {
        composable(POKEMON_LIST) {
            val pokemonListViewModel = hiltViewModel<PokemonListViewModel>()

            PokemonListScreen(onPokemonClicked = { id: String ->
                navController.navigate("pokemonDetail/$id")
            }, pokemonListViewModel
            )
        }
        composable(POKEMON_DETAIL){
            val id = it.arguments?.getString("id")
            val pokemonDetailViewModel = hiltViewModel<PokemonDetailViewModel>()

            id?.let { it1 -> PokemonDetailScreen(it1, pokemonDetailViewModel) }
        }
    }
}