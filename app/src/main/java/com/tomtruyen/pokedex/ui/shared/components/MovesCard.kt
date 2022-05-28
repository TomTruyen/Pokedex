package com.tomtruyen.pokedex.ui.shared.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.tomtruyen.pokedex.models.PokemonMove
import kotlin.math.ceil

@OptIn(ExperimentalPagerApi::class)
@Composable
fun MovesCard(moves: List<PokemonMove>) {
    // Split in 2 because each "RowItem" contains a column with 2 moves
    val rowItemCount = ceil(moves.size.toDouble() / 4).toInt()

    Column {
        CardTitle(text = "Moveset")
        Card(
            modifier = Modifier.padding(top = 8.dp, bottom = 28.dp),
            shape = RoundedCornerShape(10.dp),
            elevation = 0.dp
        ) {
            HorizontalPager(
                modifier = Modifier
                    .padding(8.dp)
                    .height(100.dp),
                count = rowItemCount
            ) { page ->
                val startIndex = page * 4

                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Top
                ) {
                    Column(
                        horizontalAlignment = Alignment.Start,
                        verticalArrangement = Arrangement.Top,
                        modifier = Modifier.weight(0.5f),
                    ) {
                        if (startIndex < moves.size)
                            Move(
                                level = moves[startIndex].versionGroupDetails[0].levelLearnedAt,
                                name = moves[startIndex].move.name,
                                modifier = Modifier.padding(8.dp)
                            )

                        if (startIndex + 1 < moves.size)
                            Move(
                                level = moves[startIndex + 1].versionGroupDetails[0].levelLearnedAt,
                                name = moves[startIndex + 1].move.name,
                                modifier = Modifier.padding(8.dp)
                            )
                    }

                    Column {
                        if (startIndex + 2 < moves.size)
                            Move(
                                level = moves[startIndex + 2].versionGroupDetails[0].levelLearnedAt,
                                name = moves[startIndex + 2].move.name,
                                modifier = Modifier.padding(8.dp)
                            )

                        if (startIndex + 3 < moves.size)
                            Move(
                                level = moves[startIndex + 3].versionGroupDetails[0].levelLearnedAt,
                                name = moves[startIndex + 3].move.name,
                                modifier = Modifier.padding(8.dp)
                            )
                    }
                }
            }
        }
    }
}