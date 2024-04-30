package com.example.pokedex.ui.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.pokedex.R
import com.example.pokedex.data.model.Cries
import com.example.pokedex.data.model.DreamWorld
import com.example.pokedex.data.model.Home
import com.example.pokedex.data.model.OfficialArtwork
import com.example.pokedex.data.model.Other
import com.example.pokedex.data.model.Pokemon
import com.example.pokedex.data.model.Showdown
import com.example.pokedex.data.model.Species
import com.example.pokedex.data.model.Sprites
import com.example.pokedex.data.model.Stat
import com.example.pokedex.data.model.StatX
import com.example.pokedex.data.model.Type
import com.example.pokedex.data.model.TypeX
import com.example.pokedex.ui.viewmodel.PokemonDetailViewModel

@Composable
fun PokemonDetailScreen(
    id: String,
    pokemonDetailViewModel: PokemonDetailViewModel = viewModel()
) {
    val pokemon by pokemonDetailViewModel.pokemon.observeAsState(null)

    LaunchedEffect(Unit) {
        pokemonDetailViewModel.fetchPokemon(id)
    }
    Box(
        modifier = Modifier
            .fillMaxSize(1f)
            .background(Color.Red)
    ) {
        if (pokemon == null) {
            Text("Loading in progress")
        } else {
            PokemonDetailView(pokemon!!)
        }
    }
}


@Composable
fun PokemonDetailView(pokemon: Pokemon) {
    val typeIconMap = mapOf(
        "bug" to R.drawable.tag_bug,
        "dark" to R.drawable.tag_dark,
        "dragon" to R.drawable.tag_dragon,
        "electric" to R.drawable.tag_electric,
        "fairy" to R.drawable.tag_fairy,
        "fire" to R.drawable.tag_fire,
        "fight" to R.drawable.tag_fight,
        "flying" to R.drawable.tag_flying,
        "ghost" to R.drawable.tag_ghost,
        "grass" to R.drawable.tag_grass,
        "ground" to R.drawable.tag_ground,
        "ice" to R.drawable.tag_ice,
        "normal" to R.drawable.tag_normal,
        "poison" to R.drawable.tag_poison,
        "psychic" to R.drawable.tag_psychic,
        "rock" to R.drawable.tag_rock,
        "steel" to R.drawable.tag_steel,
        "water" to R.drawable.tag_water
    )
    val typeColorMap = mapOf(
        "bug" to Brush.horizontalGradient(
            listOf(
                colorResource(R.color.bug_gradient_start),
                colorResource(R.color.bug_gradient_end)
            )
        ),
        "dark" to Brush.horizontalGradient(
            listOf(
                colorResource(R.color.dark_gradient_start),
                colorResource(R.color.dark_gradient_end)
            )
        ),
        "dragon" to Brush.horizontalGradient(
            listOf(
                colorResource(R.color.dragon_gradient_start),
                colorResource(R.color.dragon_gradient_end)
            )
        ),
        "electric" to Brush.horizontalGradient(
            listOf(
                colorResource(R.color.electric_gradient_start),
                colorResource(R.color.electric_gradient_end)
            )
        ),
        "fairy" to Brush.horizontalGradient(
            listOf(
                colorResource(R.color.fairy_gradient_start),
                colorResource(R.color.fairy_gradient_end)
            )
        ),
        "fight" to Brush.horizontalGradient(
            listOf(
                colorResource(R.color.fight_gradient_start),
                colorResource(R.color.fight_gradient_end)
            )
        ),
        "fire" to Brush.horizontalGradient(
            listOf(
                colorResource(R.color.fire_gradient_start),
                colorResource(R.color.fire_gradient_end)
            )
        ),
        "flying" to Brush.horizontalGradient(
            listOf(
                colorResource(R.color.flying_gradient_start),
                colorResource(R.color.flying_gradient_end)
            )
        ),
        "ghost" to Brush.horizontalGradient(
            listOf(
                colorResource(R.color.ghost_gradient_start),
                colorResource(R.color.ghost_gradient_end)
            )
        ),
        "grass" to Brush.horizontalGradient(
            listOf(
                colorResource(R.color.grass_gradient_start),
                colorResource(R.color.grass_gradient_end)
            )
        ),
        "ground" to Brush.horizontalGradient(
            listOf(
                colorResource(R.color.ground_gradient_start),
                colorResource(R.color.ground_gradient_end)
            )
        ),
        "ice" to Brush.horizontalGradient(
            listOf(
                colorResource(R.color.ice_gradient_start),
                colorResource(R.color.ice_gradient_end)
            )
        ),
        "normal" to Brush.horizontalGradient(
            listOf(
                colorResource(R.color.normal_gradient_start),
                colorResource(R.color.normal_gradient_end)
            )
        ),
        "poison" to Brush.horizontalGradient(
            listOf(
                colorResource(R.color.poison_gradient_start),
                colorResource(R.color.poison_gradient_end)
            )
        ),
        "psychic" to Brush.horizontalGradient(
            listOf(
                colorResource(R.color.psychic_gradient_start),
                colorResource(R.color.psychic_gradient_end)
            )
        ),
        "rock" to Brush.horizontalGradient(
            listOf(
                colorResource(R.color.rock_gradient_start),
                colorResource(R.color.rock_gradient_end)
            )
        ),
        "steel" to Brush.horizontalGradient(
            listOf(
                colorResource(R.color.steel_gradient_start),
                colorResource(R.color.steel_gradient_end)
            )
        ),
        "water" to Brush.horizontalGradient(
            listOf(
                colorResource(R.color.water_gradient_start),
                colorResource(R.color.water_gradient_end)
            )
        ),
    )

    Column(
        modifier = Modifier
            .fillMaxSize(1f)
            .background(brush = typeColorMap[pokemon.types[0].type.name]!!),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(pokemon.sprites.other.officialArtwork.front_default)
                .crossfade(true)
                .build(),
            placeholder = painterResource(R.drawable.default_pokemon),
            contentDescription = "UN POKEMON",
            modifier = Modifier
                .size(170.dp)
                .zIndex(1f)
        )
        Card(
            modifier = Modifier
                .fillMaxSize(1f)
                .absoluteOffset(0.dp, (-40).dp),
        ) {
            Text(
                pokemon.name.replaceFirstChar(Char::titlecase),
                modifier = Modifier
                    .fillMaxWidth(1f)
                    .absoluteOffset(0.dp, 40.dp),
                textAlign = TextAlign.Center,
                fontSize = 40.sp
            )
            Image(
                modifier = Modifier
                    .fillMaxWidth(1f)
                    .height(50.dp)
                    .absoluteOffset(0.dp, 40.dp),
                painter = painterResource(id = typeIconMap[pokemon.types[0].type.name]!!),
                contentDescription = "tag electric"
            )
            Text(
                pokemon.types[0].type.name.replaceFirstChar(Char::titlecase), color = Color.White,
                modifier = Modifier
                    .fillMaxWidth(1f)
                    .absoluteOffset(0.dp, 2.dp),
                textAlign = TextAlign.Center,
            )
            Text(
                modifier = Modifier
                    .absoluteOffset(0.dp, 30.dp)
                    .padding(20.dp)
                    .height(100.dp),
                fontSize = 16.sp,
                textAlign = TextAlign.Center,
                text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec imperdiet, est sit amet ornare cursus, erat massa suscipit metus, ut efficitur tortor purus eget mauris. Fusce nec ligula nibh. Donec euismod erat risus, id finibus purus placerat in. In a nulla dapibus, ornare neque id, eleifend elit. In consectetur nulla id lectus pretium, quis efficitur metus eleifend. Duis mauris eros, viverra nec sem ut, fringilla consectetur mi. Duis ut sagittis libero. Aliquam vestibulum sed ligula sit amet interdum. Aliquam placerat lacinia felis vel ultricies. Etiam sit amet ligula quis elit viverra iaculis et quis nisl. Ut vehicula, ex eu pretium porttitor, nisl tellus mattis dolor, sed sollicitudin augue mauris sed tellus. Nulla facilisi. Mauris eget dui et purus aliquet ultricies. Nunc a feugiat ipsum. Fusce hendrerit ultrices elit sit amet mattis. Donec pretium turpis ex, a finibus elit tempus sed."
            )
            StatDetail(pokemon, typeColorMap)
        }
    }
}

@Composable
fun StatDetail(pokemon: Pokemon, typeColorMap: Map<String, Brush>) {
    Column(modifier = Modifier.padding(20.dp)) {
        Row(
            modifier = Modifier
                .absoluteOffset(0.dp, 30.dp)
                .fillMaxWidth(1f),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("HP")
            CustomProgressBar(
                300.dp,
                Color(0xffF0F0F0),
                typeColorMap[pokemon.types[0].type.name]!!,
                (pokemon.stats[0].base_stat / 255.0 * 100).toInt()
            )
        }
        Row(
            modifier = Modifier
                .absoluteOffset(0.dp, 30.dp)
                .fillMaxWidth(1f),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("ATK")
            CustomProgressBar(
                300.dp,
                Color(0xffF0F0F0),
                typeColorMap[pokemon.types[0].type.name]!!,
                (pokemon.stats[1].base_stat / 255.0 * 100).toInt()
            )

        }
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .absoluteOffset(0.dp, 30.dp)
                .fillMaxWidth(1f)
        ) {
            Text("DEF")
            CustomProgressBar(
                300.dp,
                Color(0xffF0F0F0),
                typeColorMap[pokemon.types[0].type.name]!!,
                (pokemon.stats[2].base_stat / 255.0 * 100).toInt()
            )
        }
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .absoluteOffset(0.dp, 30.dp)
                .fillMaxWidth(1f)
        ) {
            Text("SATK")
            CustomProgressBar(
                300.dp,
                Color(0xffF0F0F0),
                typeColorMap[pokemon.types[0].type.name]!!,
                (pokemon.stats[3].base_stat / 255.0 * 100).toInt()
            )

        }
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .absoluteOffset(0.dp, 30.dp)
                .fillMaxWidth(1f)
        ) {
            Text("SDEF")
            CustomProgressBar(
                300.dp,
                Color(0xffF0F0F0),
                typeColorMap[pokemon.types[0].type.name]!!,
                (pokemon.stats[4].base_stat / 255.0 * 100).toInt()
            )

        }
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .absoluteOffset(0.dp, 30.dp)
                .fillMaxWidth(1f)
        ) {
            Text("SPD")
            CustomProgressBar(
                300.dp,
                Color(0xffF0F0F0),
                typeColorMap[pokemon.types[0].type.name]!!,
                (pokemon.stats[5].base_stat / 255.0 * 100).toInt()
            )

        }
    }
}

@Composable
fun CustomProgressBar(
    width: Dp, backgroundColor: Color, foregroundColor: Brush, percent: Int
) {
    Box(
        modifier = Modifier
            .background(backgroundColor)
            .width(width)
            .clip(shape = RoundedCornerShape(50))
            .height(14.dp)
    ) {
        Box(
            modifier = Modifier
                .background(foregroundColor)
                .width(width * percent / 100)
                .clip(shape = RoundedCornerShape(50))
                .height(14.dp)
        )
    }
}

@Preview
@Composable
fun PokemonDetailViewPreview() {
    PokemonDetailView(
        Pokemon(
            emptyList(),
            10,
            Cries(
                "https://raw.githubusercontent.com/PokeAPI/cries/main/cries/pokemon/latest/2.ogg",
                "https://raw.githubusercontent.com/PokeAPI/cries/main/cries/pokemon/latest/2.ogg"
            ),
            emptyList(),
            emptyList(),
            0,
            emptyList(),
            0,
            false,
            "",
            emptyList(),
            "pikachu",
            0,
            emptyList(),
            emptyList(),
            Species("Pikachu", "https://pokeapi.co/api/v2/pokemon-species/2/"),
            Sprites(
                "", "", "", "", "", "", "", "",
                Other(
                    DreamWorld("", ""),
                    Home("", "", "", ""),
                    OfficialArtwork(
                        "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/25.png",
                        ""
                    ),
                    Showdown("", "", "", "", "", "", "", "")
                )
            ),
            listOf(
                Stat(35, 0, StatX("HP", "")),
                Stat(55, 0, StatX("HP", "")),
                Stat(40, 0, StatX("HP", "")),
                Stat(50, 0, StatX("HP", "")),
                Stat(50, 0, StatX("HP", "")),
                Stat(90, 0, StatX("HP", ""))
            ),
            listOf(Type(0, TypeX("electric", "https://pokeapi.co/api/v2/type/13/"))),
            0
        )
    )
}