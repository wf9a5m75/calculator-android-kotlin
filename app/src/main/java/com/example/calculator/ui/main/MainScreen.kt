package com.example.calculator.ui.main

import android.content.res.Configuration
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.integerResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.calculator.R
import com.example.calculator.model.ButtonValue
import com.example.calculator.model.ValueKind
import com.example.calculator.ui.components.ActionButton
import com.example.calculator.ui.components.Display
import com.example.calculator.ui.components.NumberInputButton
import com.example.calculator.ui.components.OperatorButton
import com.example.calculator.ui.components.SpecialInputButton
import com.example.calculator.ui.theme.CalculatorTheme

@Composable
fun MainScreen(
    viewModel: MainScreenViewModel? = null
) {
    val buttonSpace = integerResource(id = R.integer.button_space_dp).toFloat()
    val buttonSpaceDp = Dp(buttonSpace)
    val screenWidth = LocalConfiguration.current.screenWidthDp

    val numOfButtonsInRow = integerResource(id = R.integer.number_of_buttons_in_row)
    val numOfSpaces = numOfButtonsInRow + 1
    val buttonSize = (screenWidth - buttonSpace * numOfSpaces) / numOfButtonsInRow
    val buttonSizeDp = Dp(buttonSize)
    val buttonFontSp = 30.sp


    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .padding(buttonSpaceDp),
    ) {
        val (
            display,
            row1,
            row2,
            row3,
            row4,
            row5,
        ) = createRefs()

        Display(
            formulaFontSize = 60.sp,
            answerFontSize = 30.sp,
            modifier = Modifier.constrainAs(display) {
                top.linkTo(parent.top, margin = buttonSpaceDp)
                bottom.linkTo(row1.top, margin = buttonSpaceDp)
            }, formula = "1+2*3", answer = "7"
        )
        Row(
            modifier = Modifier
                .constrainAs(row1) {
                    bottom.linkTo(row2.top, margin = buttonSpaceDp)
                }
                .fillMaxWidth()
                .wrapContentHeight(),
        ) {

            ActionButton(
                modifier = Modifier
                    .width(buttonSizeDp),
                fontSize = buttonFontSp,
                value = ButtonValue("AC", ValueKind.ALL_CLEAR),
                onClick = {
                    viewModel?.onActionButtonClick(it)
                },
            )
            Spacer(modifier = Modifier.width(buttonSpaceDp))
            OperatorButton(
                modifier = Modifier.width(buttonSizeDp),
                fontSize = buttonFontSp,
                value = ButtonValue("()", ValueKind.PARENTHESES),
                onClick = {
                    viewModel?.onSpecialButtonClick(it)
                },
            )
            Spacer(modifier = Modifier.width(buttonSpaceDp))
            OperatorButton(
                modifier = Modifier.width(buttonSizeDp),
                fontSize = buttonFontSp,
                value = ButtonValue("%", ValueKind.PERCENT),
                onClick = {
                    viewModel?.onSpecialButtonClick(it)
                },
            )
            Spacer(modifier = Modifier.width(buttonSpaceDp))
            OperatorButton(
                modifier = Modifier.width(buttonSizeDp),
                fontSize = buttonFontSp,
                value = ButtonValue("/", ValueKind.DIVIDE),
                onClick = {
                    viewModel?.onOperatorButtonClick(it)
                },
            )
        }
        Row(
            modifier = Modifier
                .constrainAs(row2) {
                    bottom.linkTo(row3.top, margin = buttonSpaceDp)
                }
                .fillMaxWidth()
                .wrapContentHeight()
        ) {
            NumberInputButton(
                modifier = Modifier.width(buttonSizeDp),
                fontSize = buttonFontSp,
                value = ButtonValue(7, ValueKind.NUMBER),
                onClick = {
                    viewModel?.onNumberButtonClick(it)
                },
            )
            Spacer(modifier = Modifier.width(buttonSpaceDp))
            NumberInputButton(
                modifier = Modifier.width(buttonSizeDp),
                fontSize = buttonFontSp,
                value = ButtonValue(8, ValueKind.NUMBER),
                onClick = {
                    viewModel?.onNumberButtonClick(it)
                },
            )
            Spacer(modifier = Modifier.width(buttonSpaceDp))
            NumberInputButton(
                modifier = Modifier.width(buttonSizeDp),
                fontSize = buttonFontSp,
                value = ButtonValue(9, ValueKind.NUMBER),
                onClick = {
                    viewModel?.onNumberButtonClick(it)
                },
            )
            Spacer(modifier = Modifier.width(buttonSpaceDp))
            OperatorButton(
                modifier = Modifier.width(buttonSizeDp),
                fontSize = buttonFontSp,
                value = ButtonValue("X", ValueKind.MULTIPLY),
                onClick = {
                    viewModel?.onOperatorButtonClick(it)
                },
            )
        }
        Row(
            modifier = Modifier
                .constrainAs(row3) {
                    bottom.linkTo(row4.top, margin = buttonSpaceDp)
                }
                .fillMaxWidth()
                .wrapContentHeight()
        ) {
            NumberInputButton(
                modifier = Modifier.width(buttonSizeDp),
                fontSize = buttonFontSp,
                value = ButtonValue(4, ValueKind.NUMBER),
                onClick = {
                    viewModel?.onNumberButtonClick(it)
                },
            )
            Spacer(modifier = Modifier.width(buttonSpaceDp))
            NumberInputButton(
                modifier = Modifier.width(buttonSizeDp),
                fontSize = buttonFontSp,
                value = ButtonValue(5, ValueKind.NUMBER),
                onClick = {
                    viewModel?.onNumberButtonClick(it)
                },
            )
            Spacer(modifier = Modifier.width(buttonSpaceDp))
            NumberInputButton(
                modifier = Modifier.width(buttonSizeDp),
                fontSize = buttonFontSp,
                value = ButtonValue(6, ValueKind.NUMBER),
                onClick = {
                    viewModel?.onNumberButtonClick(it)
                },
            )
            Spacer(modifier = Modifier.width(buttonSpaceDp))
            OperatorButton(
                modifier = Modifier.width(buttonSizeDp),
                fontSize = buttonFontSp,
                value = ButtonValue("-", ValueKind.SUBTRACT),
                onClick = {
                    viewModel?.onOperatorButtonClick(it)
                },
            )
        }
        Row(
            modifier = Modifier
                .constrainAs(row4) {
                    bottom.linkTo(row5.top, margin = buttonSpaceDp)
                }
                .fillMaxWidth()
                .wrapContentHeight()
        ) {
            NumberInputButton(
                modifier = Modifier.width(buttonSizeDp),
                fontSize = buttonFontSp,
                value = ButtonValue(1, ValueKind.NUMBER),
                onClick = {
                    viewModel?.onNumberButtonClick(it)
                },
            )
            Spacer(modifier = Modifier.width(buttonSpaceDp))
            NumberInputButton(
                modifier = Modifier.width(buttonSizeDp),
                fontSize = buttonFontSp,
                value = ButtonValue(2, ValueKind.NUMBER),
                onClick = {
                    viewModel?.onNumberButtonClick(it)
                },
            )
            Spacer(modifier = Modifier.width(buttonSpaceDp))
            NumberInputButton(
                modifier = Modifier.width(buttonSizeDp),
                fontSize = buttonFontSp,
                value = ButtonValue(3, ValueKind.NUMBER),
                onClick = {
                    viewModel?.onNumberButtonClick(it)
                },
            )
            Spacer(modifier = Modifier.width(buttonSpaceDp))
            OperatorButton(
                modifier = Modifier.width(buttonSizeDp),
                fontSize = buttonFontSp,
                value = ButtonValue("+", ValueKind.ADD),
                onClick = {
                    viewModel?.onOperatorButtonClick(it)
                },
            )
        }

        Row(
            modifier = Modifier
                .constrainAs(row5) {
                    bottom.linkTo(parent.bottom, margin = buttonSpaceDp)
                }
                .fillMaxWidth()
        ) {
            NumberInputButton(
                modifier = Modifier.width(buttonSizeDp),
                fontSize = buttonFontSp,
                value = ButtonValue(0, ValueKind.NUMBER),
                onClick = {
                    viewModel?.onNumberButtonClick(it)
                },
            )
            Spacer(modifier = Modifier.width(buttonSpaceDp))
            SpecialInputButton(
                modifier = Modifier.width(buttonSizeDp),
                fontSize = buttonFontSp,
                value = ButtonValue(".", ValueKind.DOT),
                onClick = {
                    viewModel?.onSpecialButtonClick(it)
                },
            )
            Spacer(modifier = Modifier.width(buttonSpaceDp))
            SpecialInputButton(
                fontSize = buttonFontSp,
                modifier = Modifier.width(buttonSizeDp),
                value = ButtonValue("DEL", ValueKind.DELETE),
                onClick = {
                    viewModel?.onSpecialButtonClick(it)
                },
            )
            Spacer(modifier = Modifier.width(buttonSpaceDp))
            ActionButton(
                modifier = Modifier.width(buttonSizeDp),
                fontSize = buttonFontSp,
                value = ButtonValue("=", ValueKind.DO_CALCULATE),
                onClick = {
                    viewModel?.onActionButtonClick(it)
                },
            )
        }
    }
}


@Preview(
    device = Devices.PIXEL_XL,
    showBackground = true,
)
@Preview(
    device = Devices.PIXEL_XL, showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun MainScreenPreview() {
    CalculatorTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            MainScreen()
        }
    }
}