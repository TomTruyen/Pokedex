package com.tomtruyen.pokedex.ui.shared.components.toolbar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.tomtruyen.pokedex.models.PokemonDetails
import me.onebone.toolbar.CollapsingToolbarScope

@Composable
fun CollapsingToolbarScope.DetailToolbar(
    pokemon: PokemonDetails,
    textSize: TextUnit,
    navController: NavHostController,
    isFavorite: Boolean,
    onFavorite: () -> Unit
) {
    val width = LocalConfiguration.current.screenWidthDp.dp

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(125.dp)
            .pin()
            .background(color = Color.Transparent)
    )
    
    if(width < 600.dp) {
        IconButton(
            modifier = Modifier.road(whenCollapsed = Alignment.TopStart, whenExpanded = Alignment.TopStart),
            onClick = { navController.popBackStack() }
        ) {
            Icon(
                imageVector = Icons.Filled.ArrowBack,
                contentDescription = null,
                tint = Color.White
            )
        }
    }

    Text(
        text = pokemon.name.replaceFirstChar { it.uppercase() },
        style = TextStyle(
            fontWeight = FontWeight.Bold,
            fontSize = textSize,
            color = Color.White
        ),
        modifier = Modifier
            .padding(vertical = 12.dp, horizontal = 16.dp)
            .road(
                whenCollapsed = Alignment.TopCenter,
                whenExpanded = Alignment.BottomStart
            )
    )

    IconButton(
        modifier = Modifier.road(whenCollapsed = Alignment.TopEnd, whenExpanded = Alignment.TopEnd),
        onClick = onFavorite
    ) {
        Icon(
            imageVector = if(isFavorite) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
            contentDescription = null,
            tint = Color.White
        )
    }
}