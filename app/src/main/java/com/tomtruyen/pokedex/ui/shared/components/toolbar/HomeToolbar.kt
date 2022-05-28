package com.tomtruyen.pokedex.ui.shared.components.toolbar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import com.tomtruyen.pokedex.R
import kotlinx.coroutines.launch
import me.onebone.toolbar.CollapsingToolbarScope

@Composable
fun CollapsingToolbarScope.HomeToolbar(
    textSize: TextUnit,
    onTypeFilterClick: () -> Unit,
    onSortClick: () -> Unit
) {
    rememberCoroutineScope()

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(125.dp)
            .pin()
            .background(color = Color.Transparent)
    )

    Text(
        modifier = Modifier
            .padding(vertical = 12.dp, horizontal = 16.dp)
            .road(
                whenCollapsed = Alignment.TopStart,
                whenExpanded = Alignment.BottomStart
            ),
        text = "Pokédex",
        style = TextStyle(
            fontSize = textSize,
            fontWeight = FontWeight.Bold,
            color = colorResource(id = R.color.dark_one)
        )
    )

    Row(
        modifier = Modifier.road(
            whenExpanded = Alignment.TopEnd,
            whenCollapsed = Alignment.TopEnd
        )
    ) {
        IconButton(onClick = onTypeFilterClick) {
            Icon(
                painter = painterResource(id = R.drawable.ic_filter),
                contentDescription = null,
                tint = colorResource(id = R.color.dark_one)
            )
        }
        IconButton(onClick = onSortClick) {
            Icon(
                painter = painterResource(id = R.drawable.ic_sort),
                contentDescription = null,
                tint = colorResource(id = R.color.dark_one)
            )
        }
    }
}