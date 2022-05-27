package com.tomtruyen.pokedex.ui.shared.components

import android.widget.ProgressBar
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun StatisticProgressBar(value: Int, total: Int, modifier: Modifier = Modifier) {
    val progress = value / total.toFloat()
    val color = if(progress > 0.5) Color(0xFF70C18F) else Color(0xFFDF6563)

    LinearProgressIndicator(
        progress = progress,
        modifier = Modifier.clip(RoundedCornerShape(100.dp)),
        backgroundColor = Color(0xFFACB2C1),
        color = color
    )
}