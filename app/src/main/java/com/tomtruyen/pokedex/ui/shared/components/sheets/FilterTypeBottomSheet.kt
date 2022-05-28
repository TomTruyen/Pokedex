package com.tomtruyen.pokedex.ui.shared.components.sheets

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tomtruyen.pokedex.R
import com.tomtruyen.pokedex.ui.shared.components.TypeFilterItem
import com.tomtruyen.pokedex.utils.PokemonUtils
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun FilterTypeBottomSheet(
    state: ModalBottomSheetState,
    filterTypes: List<String>,
    onClick: (String) -> Unit,
    onClear: () -> Unit
) {
    val types = PokemonUtils.getAllTypes()

    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier.padding(12.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Filter op type",
                style = TextStyle(
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Bold,
                    color = colorResource(id = R.color.dark_one)
                )
            )

            Row {
                if (filterTypes.isNotEmpty()) {
                    IconButton(
                        onClick = onClear
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_delete_outline),
                            contentDescription = null,
                            tint = colorResource(id = R.color.light_grey)
                        )
                    }
                }

                IconButton(
                    onClick = {
                        coroutineScope.launch {
                            state.hide()
                        }
                    }
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_close),
                        contentDescription = null,
                        tint = colorResource(id = R.color.light_grey)
                    )
                }
            }
        }

        LazyColumn(
            modifier = Modifier.fillMaxWidth()
        ) {
            items(types) { type ->
                val color = colorResource(id = PokemonUtils.getTypeColor(type))
                val backgroundAlpha = if (filterTypes.contains(type)) 1f else 0.25f

                TypeFilterItem(
                    text = type.replaceFirstChar { it.uppercase() },
                    onClick = { onClick(type) },
                    modifier = Modifier
                        .padding(vertical = 5.dp)
                        .border(
                            width = 1.dp,
                            color = color,
                            shape = RoundedCornerShape(10.dp)
                        )
                        .clip(RoundedCornerShape(10.dp))
                        .background(color = color.copy(backgroundAlpha))
                )
            }
        }
    }
}