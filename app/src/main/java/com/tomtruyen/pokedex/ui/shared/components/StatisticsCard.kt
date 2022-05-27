package com.tomtruyen.pokedex.ui.shared.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.tomtruyen.pokedex.models.PokemonDetails
import com.tomtruyen.pokedex.utils.PokemonUtils

@Composable
fun StatisticsCard(pokemon: PokemonDetails) {
    Column {
        CardTitle(text = "Statistieken")
        Card(
            modifier = Modifier.padding(top = 8.dp, bottom = 28.dp),
            shape = RoundedCornerShape(10.dp),
        ) {
            Column(modifier = Modifier.padding(20.dp)) {
                pokemon.stats.map {
                    Row(
                        modifier = Modifier.padding(vertical = 8.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        // Types
                        CardText(
                            text = PokemonUtils.getStatDisplayName(it),
                            modifier = Modifier.weight(0.3f)
                        )
                        CardText(
                            text = it.baseStat.toString(),
                            modifier = Modifier.weight(0.15f),
                            color = Color.Black,
                            style = TextStyle(fontWeight = FontWeight.W500)
                        )
                        StatisticProgressBar(
                            value = it.baseStat,
                            total = 100,
                            modifier = Modifier.weight(0.55f)
                        )
                    }
                }

                val total = PokemonUtils.calculateTotal(pokemon.stats)

                Row(
                    modifier = Modifier.padding(vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    // Types
                    CardText(
                        text = "Total",
                        modifier = Modifier.weight(0.3f)
                    )
                    CardText(
                        text = total.toString(),
                        modifier = Modifier.weight(0.2f),
                        color = Color.Black,
                        style = TextStyle(fontWeight = FontWeight.W500)
                    )
                    StatisticProgressBar(
                        value = total,
                        total = total,
                        modifier = Modifier.weight(0.5f)
                    )
                }
            }
        }
    }
}