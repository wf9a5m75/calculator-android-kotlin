package com.example.calculator.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import com.example.calculator.model.ButtonValue

class CircleButtonColors(
    val background: Color,
    val content: Color,
)


@Composable
fun <T> CircleButton(
    colors: CircleButtonColors,
    value: T,
    modifier: Modifier = Modifier,
    onClick: (value: T) -> Unit = {},
    content: @Composable () -> Unit,
) {
    val buttonColors = ButtonDefaults.buttonColors(
        contentColor = colors.content,
        containerColor = colors.background,
    )
    OutlinedButton(
        onClick = { onClick(value) },
        modifier = modifier
            .aspectRatio(1f),
        shape = CircleShape,
        border = BorderStroke(1.dp, Color.Blue),
        contentPadding = PaddingValues(0.dp),
        colors = buttonColors,
    ) {
        content()
    }
}

@Composable
fun NumberInputButton(
    value: ButtonValue<Int>,
    fontSize: TextUnit,
    modifier: Modifier = Modifier,
) {
    CircleButton(
        modifier = modifier,
        value = value,
        colors = CircleButtonColors(
            background = MaterialTheme.colorScheme.secondary,
            content = MaterialTheme.colorScheme.onSecondary,
        )
    ) {
        Text(
            fontSize = fontSize,
            text = value.value.toString()
        )
    }
}

@Composable
fun SpecialInputButton(
    value: ButtonValue<String>,
    fontSize: TextUnit,
    modifier: Modifier = Modifier,
) {
    CircleButton(
        modifier = modifier,
        value = value,
        colors = CircleButtonColors(
            background = MaterialTheme.colorScheme.secondary,
            content = MaterialTheme.colorScheme.onSecondary,
        )
    ) {
        Text(
            fontSize = fontSize,
            text = value.value,
        )
    }
}

@Composable
fun OperatorButton(
    value: ButtonValue<String>,
    fontSize: TextUnit,
    modifier: Modifier = Modifier,
) {
    CircleButton(
        modifier = modifier,
        value = value,
        colors = CircleButtonColors(
            background = MaterialTheme.colorScheme.primary,
            content = MaterialTheme.colorScheme.onPrimary,
        )
    ) {
        Text(
            fontSize = fontSize,
            text = value.value,
        )
    }
}

@Composable
fun ActionButton(
    value: ButtonValue<String>,
    fontSize: TextUnit,
    modifier: Modifier = Modifier,
) {
    CircleButton(
        modifier = modifier,
        value = value,
        colors = CircleButtonColors(
            background = MaterialTheme.colorScheme.tertiary,
            content = MaterialTheme.colorScheme.onTertiary,
        )
    ) {
        Text(
            fontSize = fontSize,
            text = value.value,
        )
    }
}
