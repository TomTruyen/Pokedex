package com.tomtruyen.pokedex.ui.shared.components.charts

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import com.github.mikephil.charting.charts.RadarChart
import com.github.mikephil.charting.data.RadarData
import com.github.mikephil.charting.data.RadarDataSet
import com.github.mikephil.charting.data.RadarEntry
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.tomtruyen.pokedex.models.PokemonDetails
import com.tomtruyen.pokedex.utils.PokemonUtils


@Composable
fun PokemonStatsChart(pokemon: PokemonDetails, modifier: Modifier = Modifier) {
    val type = pokemon.types.first().type["name"] ?: ""
    val color = ContextCompat.getColor(LocalContext.current, PokemonUtils.getTypeColor(type))

    AndroidView(
        modifier = modifier,
        factory = { context ->
            val chart = RadarChart(context)

            // Setup chart data
            val labels = mutableListOf<String>()
            val entries = pokemon.stats.map {
                labels.add(PokemonUtils.getStatDisplayName(it))

                RadarEntry(it.baseStat.toFloat())
            }

            // Create and configure dataset
            val dataset = RadarDataSet(entries, "")
            dataset.setDrawFilled(true)
            dataset.color = color
            dataset.fillColor = color
            dataset.fillAlpha = 180

            // Set the values for the chart
            val data = RadarData(dataset)
            data.setDrawValues(false)
            chart.data = data

            // Set the labels for the chart
            chart.xAxis.valueFormatter = IndexAxisValueFormatter(labels)

            // Hide yAxis
            chart.yAxis.isEnabled =  false

            // Remove legend
            chart.legend.isEnabled = false

            // Remove description
            chart.description.isEnabled = false

            // Recompose chart
            chart.invalidate()

            chart
        }
    )
}