package com.tomtruyen.pokedex.ui.shared.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import com.tomtruyen.pokedex.R

@Composable
fun PokedexItem() {
    Card {
        Row {
            Column {
                Text(text = "Frillish")
                Text(text = "Nr. 592")
            }
            // Card of typing here
            Icon(
                painter = painterResource(R.drawable.ic_chevron_right),
                contentDescription = "right arrow"
            )
        }
    }
}