package com.tomtruyen.pokedex.ui.shared.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tomtruyen.pokedex.R

@Composable
fun PrimaryButton(text: String, onClick: () -> Unit, enabled: Boolean = true, modifier: Modifier = Modifier) {
    Button(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .height(44.dp),
        shape = CircleShape,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = colorResource(id = R.color.dark_one)
        ),
        elevation = ButtonDefaults.elevation(0.dp, 0.dp),
        enabled = enabled
    ) {
        Text(
            text = text,
            style = TextStyle(
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 17.sp
            )
        )
    }
}