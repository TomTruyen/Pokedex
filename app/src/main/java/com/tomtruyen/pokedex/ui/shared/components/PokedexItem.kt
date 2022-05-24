package com.tomtruyen.pokedex.ui.shared.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.tomtruyen.pokedex.R
import com.tomtruyen.pokedex.models.Pokemon
import androidx.compose.ui.Modifier
import coil.compose.rememberAsyncImagePainter

@Composable
fun PokedexItem(pokemon: Pokemon) {
    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Row {
            Image(
                painter = rememberAsyncImagePainter(pokemon.sprites["front_default"]),
                contentDescription = null,
                modifier = Modifier.size(50.dp)
            )
            Column {
                Text(text = pokemon.name)
                Text(text = "Nr. ${pokemon.id}")
            }
            Icon(
                painter = painterResource(R.drawable.ic_chevron_right),
                contentDescription = "right arrow"
            )
        }
    }
}