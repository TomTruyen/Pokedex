package com.tomtruyen.pokedex.ui.shared.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.tomtruyen.pokedex.R
import com.tomtruyen.pokedex.enums.Sort

@Composable
fun SortItem(icon: Int, text: String, selected: Boolean, onClick: () -> Unit,  modifier: Modifier) {
    var rowModifier = modifier
        .fillMaxWidth()
        .height(40.dp)
        .background(
            color = colorResource(id = R.color.background_light_grey),
            shape = RoundedCornerShape(10.dp)
        )
        .clip(RoundedCornerShape(10.dp))
        .clickable { onClick() }

    if(selected) {
        rowModifier = rowModifier.then(
            Modifier.border(
                brush = Brush.horizontalGradient(
                    colors = listOf(
                        Color(101, 203, 154),
                        Color(21, 208, 220),
                    )
                ),
                width = 1.dp,
                shape = RoundedCornerShape(10.dp)
            )
        )
    }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = rowModifier.padding(vertical = 8.dp, horizontal = 16.dp)
    ) {
        Icon(
            painter = painterResource(id = icon),
            contentDescription = null,
            tint = colorResource(id = R.color.light_grey)
        )
        Spacer(modifier = Modifier.width(6.dp))
        Text(
            text = text,
            modifier = Modifier.weight(1f)
        )

        if(selected) {
            Icon(
                modifier = Modifier
                    .graphicsLayer(alpha = 0.99f)
                    .drawWithCache {
                        onDrawWithContent {
                            drawContent()
                            drawRect(
                                brush = Brush.horizontalGradient(
                                    colors = listOf(
                                        Color(101, 203, 154),
                                        Color(21, 208, 220),
                                    ),
                                ),
                                blendMode = BlendMode.SrcAtop
                            )
                        }
                    },
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_check),
                contentDescription = null,
            )
        }
    }
}