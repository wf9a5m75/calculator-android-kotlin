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
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.integerResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTag
import androidx.compose.ui.semantics.testTagsAsResourceId
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.calculator.R
import com.example.calculator.model.ValueModel
import com.example.calculator.model.ValueKind
import com.example.calculator.ui.components.ActionButton
import com.example.calculator.ui.components.Display
import com.example.calculator.ui.components.NumberInputButton
import com.example.calculator.ui.components.OperatorButton
import com.example.calculator.ui.components.SpecialInputButton
import com.example.calculator.ui.theme.CalculatorTheme

@OptIn(ExperimentalComposeUiApi::class)
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
            .semantics {
                testTagsAsResourceId = true
            }
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
            },
            viewModel = viewModel,
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
                    .width(buttonSizeDp)
                    .semantics {
                       // testTag = "allClearButton"
                       testTag = "clears_all"
                    }
                ,
                fontSize = buttonFontSp,
                value = ValueModel("AC", ValueKind.ALL_CLEAR),
                onClick = {
                    viewModel?.onActionButtonClick(it)
                },
            )
            Spacer(modifier = Modifier.width(buttonSpaceDp))
            Spacer(modifier = Modifier.width(buttonSizeDp))
//            OperatorButton(
//                modifier = Modifier.width(buttonSizeDp),
//                fontSize = buttonFontSp,
//                value = ValueModel("( )", ValueKind.PARENTHESES),
//                onClick = {
//                    viewModel?.onSpecialButtonClick(it)
//                },
//            )
            Spacer(modifier = Modifier.width(buttonSpaceDp))
            Spacer(modifier = Modifier.width(buttonSizeDp))
//            OperatorButton(
//                modifier = Modifier.width(buttonSizeDp),
//                fontSize = buttonFontSp,
//                value = ValueModel("%", ValueKind.PERCENT),
//                onClick = {
//                    viewModel?.onSpecialButtonClick(it)
//                },
//            )
            Spacer(modifier = Modifier.width(buttonSpaceDp))
            OperatorButton(
                modifier = Modifier
                    .width(buttonSizeDp)
                    .semantics {
                        testTag = "divides"
                    },
                fontSize = buttonFontSp,
                value = ValueModel("/", ValueKind.DIVIDE),
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
                modifier = Modifier
                    .semantics {
                        testTag = "seven"
                    }
                    .width(buttonSizeDp),
                fontSize = buttonFontSp,
                value = ValueModel(7, ValueKind.NUMBER),
                onClick = {
                    viewModel?.onNumberButtonClick(it)
                },
            )
            Spacer(modifier = Modifier.width(buttonSpaceDp))
            NumberInputButton(
                modifier = Modifier
                    .semantics {
                        testTag = "eight"
                    }
                    .width(buttonSizeDp),
                fontSize = buttonFontSp,
                value = ValueModel(8, ValueKind.NUMBER),
                onClick = {
                    viewModel?.onNumberButtonClick(it)
                },
            )
            Spacer(modifier = Modifier.width(buttonSpaceDp))
            NumberInputButton(
                modifier = Modifier
                    .semantics {
                        testTag = "nine"
                    }
                    .width(buttonSizeDp),
                fontSize = buttonFontSp,
                value = ValueModel(9, ValueKind.NUMBER),
                onClick = {
                    viewModel?.onNumberButtonClick(it)
                },
            )
            Spacer(modifier = Modifier.width(buttonSpaceDp))
            OperatorButton(
                modifier = Modifier
                    .semantics {
                        testTag = "multiplies"
                    }
                    .width(buttonSizeDp),
                fontSize = buttonFontSp,
                value = ValueModel("×", ValueKind.MULTIPLY),
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
                modifier = Modifier
                    .semantics {
                        testTag = "four"
                    }
                    .width(buttonSizeDp),
                fontSize = buttonFontSp,
                value = ValueModel(4, ValueKind.NUMBER),
                onClick = {
                    viewModel?.onNumberButtonClick(it)
                },
            )
            Spacer(modifier = Modifier.width(buttonSpaceDp))
            NumberInputButton(
                modifier = Modifier
                    .semantics {
                        testTag = "five"
                    }
                    .width(buttonSizeDp),
                fontSize = buttonFontSp,
                value = ValueModel(5, ValueKind.NUMBER),
                onClick = {
                    viewModel?.onNumberButtonClick(it)
                },
            )
            Spacer(modifier = Modifier.width(buttonSpaceDp))
            NumberInputButton(
                modifier = Modifier
                    .semantics {
                        testTag = "six"
                    }
                    .width(buttonSizeDp),
                fontSize = buttonFontSp,
                value = ValueModel(6, ValueKind.NUMBER),
                onClick = {
                    viewModel?.onNumberButtonClick(it)
                },
            )
            Spacer(modifier = Modifier.width(buttonSpaceDp))
            OperatorButton(
                modifier = Modifier
                    .semantics {
                        testTag = "subtracts"
                    }
                    .width(buttonSizeDp),
                fontSize = buttonFontSp,
                value = ValueModel("-", ValueKind.SUBTRACT),
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
                modifier = Modifier
                    .semantics {
                        testTag = "one"
                    }
                    .width(buttonSizeDp),
                fontSize = buttonFontSp,
                value = ValueModel(1, ValueKind.NUMBER),
                onClick = {
                    viewModel?.onNumberButtonClick(it)
                },
            )
            Spacer(modifier = Modifier.width(buttonSpaceDp))
            NumberInputButton(
                modifier = Modifier
                    .semantics {
                        testTag = "two"
                    }
                    .width(buttonSizeDp),
                fontSize = buttonFontSp,
                value = ValueModel(2, ValueKind.NUMBER),
                onClick = {
                    viewModel?.onNumberButtonClick(it)
                },
            )
            Spacer(modifier = Modifier.width(buttonSpaceDp))
            NumberInputButton(
                modifier = Modifier
                    .semantics {
                        testTag = "three"
                    }
                    .width(buttonSizeDp),
                fontSize = buttonFontSp,
                value = ValueModel(3, ValueKind.NUMBER),
                onClick = {
                    viewModel?.onNumberButtonClick(it)
                },
            )
            Spacer(modifier = Modifier.width(buttonSpaceDp))
            OperatorButton(
                modifier = Modifier
                    .semantics {
                        testTag = "adds"
                    }
                    .width(buttonSizeDp),
                fontSize = buttonFontSp,
                value = ValueModel("+", ValueKind.ADD),
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
                modifier = Modifier
                    .semantics {
                        testTag = "zero"
                    }
                    .width(buttonSizeDp),
                fontSize = buttonFontSp,
                value = ValueModel(0, ValueKind.NUMBER),
                onClick = {
                    viewModel?.onNumberButtonClick(it)
                },
            )
            Spacer(modifier = Modifier.width(buttonSpaceDp))
            Spacer(modifier = Modifier.width(buttonSizeDp))
//            SpecialInputButton(
//                modifier = Modifier.width(buttonSizeDp),
//                fontSize = buttonFontSp,
//                value = ValueModel(".", ValueKind.DOT),
//                onClick = {
//                    viewModel?.onSpecialButtonClick(it)
//                },
//            )
            Spacer(modifier = Modifier.width(buttonSpaceDp))
            SpecialInputButton(
                fontSize = buttonFontSp,
                modifier = Modifier
                    .semantics {
                        testTag = "deletes"
                    }
                    .width(buttonSizeDp),
                value = ValueModel("DEL", ValueKind.DELETE),
                onClick = {
                    viewModel?.onSpecialButtonClick(it)
                },
            )
            Spacer(modifier = Modifier.width(buttonSpaceDp))
            ActionButton(
                modifier = Modifier
                    .semantics {
                        testTag = "equals"
                    }
                    .width(buttonSizeDp),
                fontSize = buttonFontSp,
                value = ValueModel("=", ValueKind.DO_CALCULATE),
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