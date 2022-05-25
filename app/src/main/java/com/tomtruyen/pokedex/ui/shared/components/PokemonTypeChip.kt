package com.tomtruyen.pokedex.ui.shared.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp

@Composable
fun PokemonTypeChip(type: String, color: Int) {
    val shape = CircleShape

    Box(
        modifier = Modifier
            .background(color = colorResource(id = color), shape = shape)
            .clip(shape = shape)
            .padding(horizontal = 10.dp, vertical = 3.dp)
    ) {
        Text(
            text = type.replaceFirstChar { it.uppercase() },
            style = TextStyle(color = Color.White)
        )
    }
}