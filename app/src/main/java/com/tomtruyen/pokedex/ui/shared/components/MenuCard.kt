package com.tomtruyen.pokedex.ui.shared.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tomtruyen.pokedex.R

@Composable
fun MenuCard(title: String, subtitle: String, colors: List<Color>, modifier: Modifier) {
    Card(
        modifier = modifier.height(100.dp)
            .background(
                brush = Brush.horizontalGradient(
                    colors = colors
                ),
                shape = RoundedCornerShape(10.dp),
            ),
        shape = RoundedCornerShape(10.dp),
        backgroundColor = Color.Transparent,
        elevation = 0.dp
    ) {
        Box {
            Image(
                modifier = Modifier.align(Alignment.CenterEnd),
                painter = painterResource(id = R.drawable.ic_pokeball),
                contentDescription = null
            )
            Column(
                modifier = Modifier.background(Color.Transparent)
                    .padding(horizontal = 15.dp, vertical = 10.dp)
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.Bottom
            ) {
                Text(

                    text = title,
                    style = TextStyle(color = Color.White, fontWeight = FontWeight.Bold, fontSize = 18.sp)
                )
                Text(
                    text = subtitle,
                    style = TextStyle(color = Color(255, 255, 255, 128), fontSize = 15.sp)
                )
            }
        }
    }
}