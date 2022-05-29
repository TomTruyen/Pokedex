package com.tomtruyen.pokedex.ui.shared.components.toolbar

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.StrictMode
import android.os.StrictMode.VmPolicy
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Share
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.integerResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.squareup.picasso.Picasso
import com.squareup.picasso.Target
import com.tomtruyen.pokedex.R
import com.tomtruyen.pokedex.models.PokemonDetails
import com.tomtruyen.pokedex.ui.screens.ScreenUtils
import me.onebone.toolbar.CollapsingToolbarScope
import com.tomtruyen.pokedex.utils.Utils


@Composable
fun CollapsingToolbarScope.DetailToolbar(
    pokemon: PokemonDetails,
    textSize: TextUnit,
    navController: NavHostController,
    isFavorite: Boolean,
    onFavorite: () -> Unit
) {
    val context = LocalContext.current
    val width = LocalConfiguration.current.screenWidthDp.dp
    val cacheDir = LocalContext.current.externalCacheDir

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(125.dp)
            .pin()
            .background(color = Color.Transparent)
    )

    if (width < integerResource(id = R.integer.large_screen_size).dp) {
        IconButton(
            modifier = Modifier.road(
                whenCollapsed = Alignment.TopStart,
                whenExpanded = Alignment.TopStart
            ),
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

    Row(modifier = Modifier.road(whenCollapsed = Alignment.TopEnd, whenExpanded = Alignment.TopEnd)) {
        IconButton(
            onClick = {
                // Load image
                Picasso.get().load(pokemon.sprites["front_default"] as String).into(
                    object: Target {
                        override fun onBitmapLoaded(bitmap: Bitmap?, from: Picasso.LoadedFrom?) {
                            // Fix: exposed beyond app through StrictMode
                            val builder = VmPolicy.Builder()
                            StrictMode.setVmPolicy(builder.build())

                            // Share intent
                            val share = Intent()
                            share.action = Intent.ACTION_SEND
                            share.putExtra(Intent.EXTRA_SUBJECT, pokemon.name)
                            share.putExtra(Intent.EXTRA_TEXT, ScreenUtils.getDetailDeepLink(pokemon.name))
                            share.putExtra(Intent.EXTRA_STREAM, Utils.getBitmapFromView(bitmap, cacheDir))
                            share.type = "image/*"
                            share.flags = Intent.FLAG_GRANT_READ_URI_PERMISSION
                            context.startActivity(Intent.createChooser(share, "Share pokémon"))
                        }

                        override fun onBitmapFailed(e: Exception?, errorDrawable: Drawable?) {}
                        override fun onPrepareLoad(placeHolderDrawable: Drawable?) {}
                    }
                )
            }
        ) {
            Icon(
                imageVector = Icons.Filled.Share,
                contentDescription = null,
                tint = Color.White
            )
        }

        IconButton(
            onClick = onFavorite
        ) {
            Icon(
                imageVector = if (isFavorite) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
                contentDescription = null,
                tint = Color.White
            )
        }
    }
}