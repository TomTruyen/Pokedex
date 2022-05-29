package com.tomtruyen.pokedex.ui.shared.components

import androidx.compose.foundation.gestures.rememberTransformableState
import androidx.compose.foundation.gestures.transformable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.tomtruyen.pokedex.R
import com.tomtruyen.pokedex.models.PokemonDetails
import com.tomtruyen.pokedex.utils.PokemonUtils
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class)
@Composable
fun ImageCarousel(pokemon: PokemonDetails) {
    val spritesList = PokemonUtils.getSpritesList(pokemon.sprites)

    val pagerState = rememberPagerState()
    val coroutineScope = rememberCoroutineScope()
    var scale by remember { mutableStateOf(1f) }

    val state = rememberTransformableState { zoomChange, _, _ ->
        scale *= zoomChange
    }
    Box(
        modifier = Modifier.fillMaxWidth()
    ) {


        HorizontalPager(count = spritesList.size, state = pagerState) { index ->
            AsyncImage(
                model = spritesList[index],
                contentDescription = null,
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .width(200.dp)
                    .graphicsLayer(
                        scaleX = scale,
                        scaleY = scale,
                    )
                    .aspectRatio(1f)
                    .transformable(state = state),
                alignment = Alignment.Center
            )
        }

        IconButton(
            onClick = {
                coroutineScope.launch {
                    if (pagerState.currentPage == 0) {
                        pagerState.scrollToPage(pagerState.pageCount - 1)
                    } else {
                        pagerState.scrollToPage(pagerState.currentPage - 1)
                    }
                }
            },
            modifier = Modifier.align(Alignment.CenterStart)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_chevron_left),
                contentDescription = null,
                tint = Color.White
            )
        }

        IconButton(
            onClick = {
                coroutineScope.launch {
                    if (pagerState.currentPage == pagerState.pageCount - 1) {
                        pagerState.scrollToPage(0)
                    } else {
                        pagerState.scrollToPage(pagerState.currentPage + 1)
                    }
                }
            },
            modifier = Modifier.align(Alignment.CenterEnd)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_chevron_right),
                contentDescription = null,
                tint = Color.White
            )
        }
    }
}