package com.tomtruyen.pokedex.ui.shared.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.tomtruyen.pokedex.R

@Composable
fun Search(value: String, placeholder: String, onValueChange: (String) -> Unit) {

    BasicTextField(
        textStyle = LocalTextStyle.current.copy(
            color = colorResource(id = R.color.dark_one)
        ),
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = colorResource(id = R.color.background_light_grey),
                shape = RoundedCornerShape(10.dp),
            )
            .padding(8.dp),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Search
        ),
        decorationBox = { innerTextField ->
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_search),
                    contentDescription = null,
                    tint = Color(60, 60, 67, 153)
                )
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .padding(horizontal = 6.dp)
                ) {
                    if (value.isEmpty()) {
                        Text(
                            placeholder,
                            style = LocalTextStyle.current.copy(
                                color = Color(60, 60, 67, 153)
                            )
                        )
                    }

                    innerTextField()
                }
                if (value.isNotEmpty()) {
                    IconButton(
                        onClick = {
                            onValueChange("")
                        },
                        Modifier.size(22.dp)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_close),
                            contentDescription = null,
                            tint = Color(60, 60, 67, 153),
                        )
                    }
                }
            }
        },
        value = value,
        onValueChange = {
            onValueChange(it)
        },
    )
}
