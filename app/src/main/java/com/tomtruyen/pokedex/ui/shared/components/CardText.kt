package com.tomtruyen.pokedex.ui.shared.components

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp

@Composable
fun CardText(text: String, modifier: Modifier = Modifier, color: Color = Color(0xFFACB2C1), style: TextStyle = TextStyle()) {
    Text(
        text = text,
        style = style.merge(TextStyle(
            fontSize = 15.sp,
            color = color
        )),
        modifier = modifier
    )
}