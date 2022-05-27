package com.tomtruyen.pokedex.ui.shared.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tomtruyen.pokedex.R
import com.tomtruyen.pokedex.enums.Sort
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SortBottomSheet(state: ModalBottomSheetState, value: Sort, onSort: (Sort) -> Unit) {
    val coroutineScope = rememberCoroutineScope()

    var activeSort by remember {
        mutableStateOf(value)
    }

    Column(
        modifier = Modifier.padding(12.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Sorteren op",
                style = TextStyle(
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Bold,
                    color = colorResource(id = R.color.dark_one)
                )
            )
            IconButton(
                onClick = {
                    coroutineScope.launch {
                        // Set the actual viewModel sort value on closing
                        // Otherwise it remembers the state and displays an incorrect
                        // SortItem as selected due to previous actions
                        activeSort = value
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

        SortItem(
            icon = R.drawable.ic_alphabetic_asc,
            text = "Alfabetisch oplopend",
            selected = activeSort == Sort.ALPHABETIC_ASC,
            onClick = { activeSort = Sort.ALPHABETIC_ASC },
            modifier = Modifier.padding(vertical = 5.dp)
        )
        SortItem(
            icon = R.drawable.ic_alphabetic_desc,
            text = "Alfabetisch aflopend",
            selected = activeSort == Sort.ALPHABETIC_DESC,
            onClick = { activeSort = Sort.ALPHABETIC_DESC },
            modifier = Modifier.padding(vertical = 5.dp)
        )
        SortItem(
            icon = R.drawable.ic_numeric_asc,
            text = "Numeriek oplopend",
            selected = activeSort == Sort.NUMERIC_ASC,
            onClick = { activeSort = Sort.NUMERIC_ASC },
            modifier = Modifier.padding(vertical = 5.dp)
        )
        SortItem(
            icon = R.drawable.ic_numeric_desc,
            text = "Numeriek aflopend",
            selected = activeSort == Sort.NUMERIC_DESC,
            onClick = { activeSort = Sort.NUMERIC_DESC },
            modifier = Modifier.padding(vertical = 5.dp)
        )

        PrimaryButton(
            text = "Toepassen",
            onClick = {
                onSort(activeSort)
                coroutineScope.launch {
                    state.hide()
                }
            },
            modifier = Modifier.padding(vertical = 16.dp)
        )
    }
}