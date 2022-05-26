package com.tomtruyen.pokedex.ui.shared.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tomtruyen.pokedex.R

@Composable
fun Move(level: Int, name: String, modifier: Modifier = Modifier) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {
        Box(
            modifier = Modifier
                .background(
                    color = colorResource(id = R.color.purple_700).copy(alpha = 0.25f),
                    shape = RoundedCornerShape(30.dp)
                )
                .border(
                    border = BorderStroke(
                        width = 1.dp,
                        color = colorResource(id = R.color.purple_700)
                    ),
                    shape = RoundedCornerShape(30.dp)
                )
                .padding(horizontal = 10.dp, vertical = 5.dp)
        ) {
            Text(
                text = "Level $level",
                style = TextStyle(color = colorResource(id = R.color.purple_700))
            )
        }

        Text(
            text = name.replace('-', ' ').lowercase().replaceFirstChar { it.uppercase() },
            maxLines = 1,
            color = colorResource(id = R.color.dark_one),
            style = TextStyle(fontWeight = FontWeight.W600, fontSize = 15.sp),
            modifier = Modifier.padding(start = 4.dp)
        )
    }
}