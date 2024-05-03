package com.example.pokedex.ui.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.pokedex.R
import com.example.pokedex.data.model.Result
import com.example.pokedex.ui.viewmodel.PokemonListViewModel

@Composable
fun PokemonListScreen(
    onPokemonClicked: (String) -> Unit,
    pokemonListViewModel: PokemonListViewModel = viewModel()
) {
    val pokemonList by pokemonListViewModel.pokemonList.observeAsState(null)

    LaunchedEffect(Unit) {
        pokemonListViewModel.fetchPokemonList(0)
    }
    Row(
        modifier = Modifier
            .paint(
                painterResource(R.drawable.pokedex_background),
                contentScale = ContentScale.FillBounds
            )
            .fillMaxSize(1f)
    ) {
        if (pokemonList == null) {
            Text("LOADING IN PROGRESS")
        } else {
            LazyVerticalGrid(
                columns = GridCells.Adaptive(minSize = 100.dp),
                contentPadding = PaddingValues(12.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp)

            ) {
                items(pokemonList!!) {
                    PokemonGrid(it, onPokemonClicked)
                }
            }

        }
    }
}

@Composable
fun PokemonGrid(
    result: Result,
    onPokemonClicked: (String) -> Unit
) {
    val id = result.url.removePrefix("https://pokeapi.co/api/v2/pokemon/").removeSuffix("/")
    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        modifier = Modifier
            .height(120.dp)
            .clickable { onPokemonClicked(id) },
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/$id.png")
                .crossfade(true)
                .build(),
            contentDescription = "UN POKEMON",
            placeholder = painterResource(R.drawable.default_pokemon),
            modifier = Modifier
                .height(100.dp)
                .align(Alignment.CenterHorizontally),
        )
        Text(
            modifier = Modifier.fillMaxWidth(1f),
            text = result.name,
            textAlign = TextAlign.Center,
        )
    }
}

@Preview
@Composable
fun PokemonGridPreview() {
    PokemonGrid(
        result = Result("PIKACHU", "http://google.com/pikachu"),
        onPokemonClicked = {}
    )
}