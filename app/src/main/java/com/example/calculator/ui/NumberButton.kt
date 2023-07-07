package com.example.calculator.ui

import android.graphics.Color
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview(
    showBackground = true,
    backgroundColor = Color.WHITE.toLong(),
)
@Composable
fun NumberButton(
    modifier: Modifier = Modifier,
    label: String = "(preview)",
    tag: String = "preview",
    onClick: (tag: String) -> Unit = {},
) {
    Column(
        modifier = modifier
            .wrapContentSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        TextButton(
            modifier = Modifier
                .fillMaxSize()
                .padding(5.dp)
                .wrapContentSize(Alignment.Center),

            onClick = {
                onClick(tag)
            }
        ) {
            Text(text = label)
        }
    }
}