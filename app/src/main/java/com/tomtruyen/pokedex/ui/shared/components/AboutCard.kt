package com.tomtruyen.pokedex.ui.shared.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.tomtruyen.pokedex.models.PokemonDetails
import com.tomtruyen.pokedex.utils.PokemonTypeUtils
import com.tomtruyen.pokedex.utils.PokemonUtils

@Composable
fun AboutCard(pokemon: PokemonDetails) {
    Column {
        CardTitle(text = "Info")
        Card(
            modifier = Modifier.padding(top = 12.dp, bottom = 28.dp),
            shape = MaterialTheme.shapes.medium,
            elevation = 0.dp
        ) {
            Column(modifier = Modifier.padding(20.dp)) {
                Row(modifier = Modifier.padding(vertical = 8.dp)) {
                    // Types
                    CardText(text = "Type", modifier = Modifier.weight(0.4f))

                    Row(
                        modifier = Modifier.weight(0.6f)
                    ) {
                        pokemon.types.map {
                            PokemonTypeChip(
                                type = it.type["name"] ?: "",
                                color = PokemonTypeUtils.find(it.type["name"] ?: "").color,
                                modifier = Modifier.padding(end = 5.dp)
                            )
                        }
                    }
                }

                Row(modifier = Modifier.padding(vertical = 8.dp)) {
                    // Number
                    CardText(text = "Nummer", modifier = Modifier.weight(0.4f))
                    CardText(
                        text = pokemon.id.toString().padStart(3, '0'),
                        modifier = Modifier.weight(0.6f),
                        color = Color.Black,
                        style = TextStyle(fontWeight = FontWeight.W500)
                    )
                }

                Row(modifier = Modifier.padding(vertical = 8.dp)) {
                    // Height
                    CardText(text = "Hoogte", modifier = Modifier.weight(0.4f))
                    CardText(
                        text = "${pokemon.height / 10f}m",
                        modifier = Modifier.weight(0.6f),
                        color = Color.Black,
                        style = TextStyle(fontWeight = FontWeight.W500)
                    )
                }

                Row(modifier = Modifier.padding(vertical = 8.dp)) {
                    // Weight
                    CardText(text = "Gewicht", modifier = Modifier.weight(0.4f))
                    CardText(
                        text = "${pokemon.weight / 10f} kg",
                        modifier = Modifier.weight(0.6f),
                        color = Color.Black,
                        style = TextStyle(fontWeight = FontWeight.W500)
                    )
                }

                Row(modifier = Modifier.padding(vertical = 8.dp)) {
                    // Abilities
                    CardText(text = "Vaardigheden", modifier = Modifier.weight(0.4f))
                    CardText(
                        text = pokemon.abilities.joinToString { ability ->
                            ability.ability["name"]!!.replaceFirstChar { it.uppercase() }
                        },
                        color = Color.Black,
                        modifier = Modifier.weight(0.6f),
                        style = TextStyle(fontWeight = FontWeight.W500)
                    )
                }
            }
        }
    }
}