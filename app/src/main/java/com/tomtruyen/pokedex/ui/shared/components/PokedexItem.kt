package com.tomtruyen.pokedex.ui.shared.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.tomtruyen.pokedex.R
import com.tomtruyen.pokedex.models.Pokemon
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.tomtruyen.pokedex.utils.PokemonUtils

@Composable
fun PokedexItem(pokemon: Pokemon) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 5.dp),
        shape = RoundedCornerShape(10.dp),
        backgroundColor = Color.White,
        elevation = 1.dp,
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(16.dp)
        ) {
            Image(
                painter = rememberAsyncImagePainter(pokemon.sprites["front_default"]),
                contentDescription = null,
                modifier = Modifier
                    .height(50.dp)
                    .width(50.dp)
                    .padding(end = 16.dp),
                alignment = Alignment.Center

            )
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = pokemon.name.replaceFirstChar { it.uppercase()},
                    style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 17.sp)
                )
                Text(
                    text = "Nr. ${pokemon.id}",
                    style = TextStyle(color = colorResource(id = R.color.light_grey), fontSize = 15.sp)
                )
            }
            Row {
                pokemon.types.map { 
                    PokemonTypeChip(type = it.type["name"] ?: "", color = PokemonUtils.getTypeColor(it.type["name"] ?: ""))
                    Spacer(modifier = Modifier.size(5.dp))
                }
            }
            Icon(
                painter = painterResource(R.drawable.ic_chevron_right),
                tint = colorResource(id = R.color.light_grey),
                contentDescription = "right arrow"
            )
        }
    }
}