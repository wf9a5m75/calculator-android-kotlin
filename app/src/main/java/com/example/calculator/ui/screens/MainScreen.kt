package com.example.calculator.ui.screens

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
fun MainScreen() {
    val buttonSpace = integerResource(id = R.integer.button_space_dp).toFloat()
    val buttonSpaceDp = Dp(buttonSpace)
    val screenWidth = LocalConfiguration.current.screenWidthDp
    val screenHeight = LocalConfiguration.current.screenHeightDp

    val buttonSize = (screenWidth - buttonSpace * 5) / 4
    val buttonSizeDp = Dp(buttonSize)
    val buttonFontSp = 30.sp


    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .padding(buttonSpaceDp),
    ) {
        val (display,
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
            )
            Spacer(modifier = Modifier.width(buttonSpaceDp))
            OperatorButton(
                modifier = Modifier.width(buttonSizeDp),
                fontSize = buttonFontSp,
                value = ButtonValue("()", ValueKind.PARENTHESES),
            )
            Spacer(modifier = Modifier.width(buttonSpaceDp))
            OperatorButton(
                modifier = Modifier.width(buttonSizeDp),
                fontSize = buttonFontSp,
                value = ButtonValue("%", ValueKind.PERCENT),
            )
            Spacer(modifier = Modifier.width(buttonSpaceDp))
            OperatorButton(
                modifier = Modifier.width(buttonSizeDp),
                fontSize = buttonFontSp,
                value = ButtonValue("/", ValueKind.DIVIDE),
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
            )
            Spacer(modifier = Modifier.width(buttonSpaceDp))
            NumberInputButton(
                modifier = Modifier.width(buttonSizeDp),
                fontSize = buttonFontSp,
                value = ButtonValue(8, ValueKind.NUMBER),
            )
            Spacer(modifier = Modifier.width(buttonSpaceDp))
            NumberInputButton(
                modifier = Modifier.width(buttonSizeDp),
                fontSize = buttonFontSp,
                value = ButtonValue(9, ValueKind.NUMBER),
            )
            Spacer(modifier = Modifier.width(buttonSpaceDp))
            OperatorButton(
                modifier = Modifier.width(buttonSizeDp),
                fontSize = buttonFontSp,
                value = ButtonValue("X", ValueKind.MULTIPLY),
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
            )
            Spacer(modifier = Modifier.width(buttonSpaceDp))
            NumberInputButton(
                modifier = Modifier.width(buttonSizeDp),
                fontSize = buttonFontSp,
                value = ButtonValue(5, ValueKind.NUMBER),
            )
            Spacer(modifier = Modifier.width(buttonSpaceDp))
            NumberInputButton(
                modifier = Modifier.width(buttonSizeDp),
                fontSize = buttonFontSp,
                value = ButtonValue(6, ValueKind.NUMBER),
            )
            Spacer(modifier = Modifier.width(buttonSpaceDp))
            OperatorButton(
                modifier = Modifier.width(buttonSizeDp),
                fontSize = buttonFontSp,
                value = ButtonValue("-", ValueKind.SUBTRACT),
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
            )
            Spacer(modifier = Modifier.width(buttonSpaceDp))
            NumberInputButton(
                modifier = Modifier.width(buttonSizeDp),
                fontSize = buttonFontSp,
                value = ButtonValue(2, ValueKind.NUMBER),
            )
            Spacer(modifier = Modifier.width(buttonSpaceDp))
            NumberInputButton(
                modifier = Modifier.width(buttonSizeDp),
                fontSize = buttonFontSp,
                value = ButtonValue(3, ValueKind.NUMBER),
            )
            Spacer(modifier = Modifier.width(buttonSpaceDp))
            OperatorButton(
                modifier = Modifier.width(buttonSizeDp),
                fontSize = buttonFontSp,
                value = ButtonValue("+", ValueKind.ADD),
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
            )
            Spacer(modifier = Modifier.width(buttonSpaceDp))
            SpecialInputButton(
                modifier = Modifier.width(buttonSizeDp),
                fontSize = buttonFontSp,
                value = ButtonValue(".", ValueKind.DOT),
            )
            Spacer(modifier = Modifier.width(buttonSpaceDp))
            SpecialInputButton(
                fontSize = buttonFontSp,
                modifier = Modifier.width(buttonSizeDp),
                value = ButtonValue("DEL", ValueKind.DELETE),
            )
            Spacer(modifier = Modifier.width(buttonSpaceDp))
            ActionButton(
                modifier = Modifier.width(buttonSizeDp),
                fontSize = buttonFontSp,
                value = ButtonValue("=", ValueKind.DO_CALCULATE),
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