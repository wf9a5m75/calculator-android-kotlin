package com.example.calculator.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTagsAsResourceId
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.calculator.models.NumpadButton

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun NumberPad(
    modifier: Modifier = Modifier,
    buttonSizeDp: Dp,
    buttonSpaceDp: Dp,
    buttonFontSp: TextUnit,
    onButtonClick: (button: NumpadButton) -> Unit,
) {

    ConstraintLayout(
        modifier = modifier.fillMaxSize()
            .semantics {
                testTagsAsResourceId = true
            }
            .fillMaxWidth()
            .wrapContentHeight(),
    ) {
        val (
            row1,
            row2,
            row3,
            row4,
            row5,
        ) = createRefs()

        Row(
            modifier = Modifier
                .constrainAs(row1) {
                    top.linkTo(parent.top)
                    bottom.linkTo(row2.top, margin = buttonSpaceDp)
                }
                .fillMaxWidth()
                .wrapContentHeight(),
        ) {

            ActionButton(
                modifier = Modifier
                    .width(buttonSizeDp)
                    .testTag(NumpadButton.ALL_CLEAR.testTag)
                ,
                fontSize = buttonFontSp,
                value = NumpadButton.ALL_CLEAR,
                onClick = onButtonClick,
            )
            Spacer(modifier = Modifier.width(buttonSpaceDp))
            OperatorButton(
                modifier = Modifier
                    .width(buttonSizeDp)
                    .testTag(NumpadButton.LEFT_BRACKET.testTag),
                fontSize = buttonFontSp,
                value = NumpadButton.LEFT_BRACKET,
                onClick = onButtonClick,
            )
            Spacer(modifier = Modifier.width(buttonSpaceDp))
            OperatorButton(
                modifier = Modifier
                    .width(buttonSizeDp)
                    .testTag(NumpadButton.RIGHT_BRACKET.testTag),
                fontSize = buttonFontSp,
                value = NumpadButton.RIGHT_BRACKET,
                onClick = onButtonClick,
            )
            Spacer(modifier = Modifier.width(buttonSpaceDp))
            OperatorButton(
                modifier = Modifier
                    .width(buttonSizeDp)
                    .testTag(NumpadButton.DIVIDE.testTag),
                fontSize = buttonFontSp,
                value = NumpadButton.DIVIDE,
                onClick = onButtonClick,
            )
        }
        Row(
            modifier = Modifier
                .constrainAs(row2) {
                    top.linkTo(row1.bottom)
                    bottom.linkTo(row3.top, margin = buttonSpaceDp)
                }
                .fillMaxWidth()
                .wrapContentHeight()
        ) {
            NumberInputButton(
                modifier = Modifier
                    .testTag(NumpadButton.SEVEN.testTag)
                    .width(buttonSizeDp),
                fontSize = buttonFontSp,
                value = NumpadButton.SEVEN,
                onClick = onButtonClick,
            )
            Spacer(modifier = Modifier.width(buttonSpaceDp))
            NumberInputButton(
                modifier = Modifier
                    .testTag(NumpadButton.EIGHT.testTag)
                    .width(buttonSizeDp),
                fontSize = buttonFontSp,
                value = NumpadButton.EIGHT,
                onClick = onButtonClick,
            )
            Spacer(modifier = Modifier.width(buttonSpaceDp))
            NumberInputButton(
                modifier = Modifier
                    .testTag(NumpadButton.NINE.testTag)
                    .width(buttonSizeDp),
                fontSize = buttonFontSp,
                value = NumpadButton.NINE,
                onClick = onButtonClick,
            )
            Spacer(modifier = Modifier.width(buttonSpaceDp))
            OperatorButton(
                modifier = Modifier
                    .testTag(NumpadButton.MULTIPLY.testTag)
                    .width(buttonSizeDp),
                fontSize = buttonFontSp,
                value = NumpadButton.MULTIPLY,
                onClick = onButtonClick,
            )
        }
        Row(
            modifier = Modifier
                .constrainAs(row3) {
                    top.linkTo(row2.bottom)
                    bottom.linkTo(row4.top, margin = buttonSpaceDp)
                }
                .fillMaxWidth()
                .wrapContentHeight()
        ) {
            NumberInputButton(
                modifier = Modifier
                    .testTag(NumpadButton.FOUR.testTag)
                    .width(buttonSizeDp),
                fontSize = buttonFontSp,
                value = NumpadButton.FOUR,
                onClick = onButtonClick,
            )
            Spacer(modifier = Modifier.width(buttonSpaceDp))
            NumberInputButton(
                modifier = Modifier
                    .testTag(NumpadButton.FIVE.testTag)
                    .width(buttonSizeDp),
                fontSize = buttonFontSp,
                value = NumpadButton.FIVE,
                onClick = onButtonClick,
            )
            Spacer(modifier = Modifier.width(buttonSpaceDp))
            NumberInputButton(
                modifier = Modifier
                    .testTag(NumpadButton.SIX.testTag)
                    .width(buttonSizeDp),
                fontSize = buttonFontSp,
                value = NumpadButton.SIX,
                onClick = onButtonClick,
            )
            Spacer(modifier = Modifier.width(buttonSpaceDp))
            OperatorButton(
                modifier = Modifier
                    .testTag(NumpadButton.SUBTRACT.testTag)
                    .width(buttonSizeDp),
                fontSize = buttonFontSp,
                value = NumpadButton.SUBTRACT,
                onClick = onButtonClick,
            )
        }
        Row(
            modifier = Modifier
                .constrainAs(row4) {
                    top.linkTo(row3.bottom)
                    bottom.linkTo(row5.top, margin = buttonSpaceDp)
                }
                .fillMaxWidth()
                .wrapContentHeight()
        ) {
            NumberInputButton(
                modifier = Modifier
                    .testTag(NumpadButton.ONE.testTag)
                    .width(buttonSizeDp),
                fontSize = buttonFontSp,
                value = NumpadButton.ONE,
                onClick = onButtonClick,
            )
            Spacer(modifier = Modifier.width(buttonSpaceDp))
            NumberInputButton(
                modifier = Modifier
                    .testTag(NumpadButton.TWO.testTag)
                    .width(buttonSizeDp),
                fontSize = buttonFontSp,
                value = NumpadButton.TWO,
                onClick = onButtonClick,
            )
            Spacer(modifier = Modifier.width(buttonSpaceDp))
            NumberInputButton(
                modifier = Modifier
                    .testTag(NumpadButton.THREE.testTag)
                    .width(buttonSizeDp),
                fontSize = buttonFontSp,
                value = NumpadButton.THREE,
                onClick = onButtonClick,
            )
            Spacer(modifier = Modifier.width(buttonSpaceDp))
            OperatorButton(
                modifier = Modifier
                    .testTag(NumpadButton.ADD.testTag)
                    .width(buttonSizeDp),
                fontSize = buttonFontSp,
                value = NumpadButton.ADD,
                onClick = onButtonClick,
            )
        }

        Row(
            modifier = Modifier
                .constrainAs(row5) {
                    top.linkTo(row4.bottom)
                    bottom.linkTo(parent.bottom, margin = buttonSpaceDp)
                }
                .fillMaxWidth()
        ) {
            NumberInputButton(
                modifier = Modifier
                    .testTag(NumpadButton.ZERO.testTag)
                    .width(buttonSizeDp),
                fontSize = buttonFontSp,
                value = NumpadButton.ZERO,
                onClick = onButtonClick,
            )
            Spacer(modifier = Modifier.width(buttonSpaceDp))
            SpecialInputButton(
                modifier = Modifier
                    .testTag(NumpadButton.DOT.testTag)
                    .width(buttonSizeDp),
                fontSize = buttonFontSp,
                value = NumpadButton.DOT,
                onClick = onButtonClick,
            )
            Spacer(modifier = Modifier.width(buttonSpaceDp))
            SpecialInputButton(
                fontSize = buttonFontSp,
                modifier = Modifier
                    .testTag(NumpadButton.DELETE.testTag)
                    .width(buttonSizeDp),
                value = NumpadButton.DELETE,
                onClick = onButtonClick,
            )
            Spacer(modifier = Modifier.width(buttonSpaceDp))
            ActionButton(
                modifier = Modifier
                    .testTag(NumpadButton.EQUAL.testTag)
                    .width(buttonSizeDp),
                fontSize = buttonFontSp,
                value = NumpadButton.EQUAL,
                onClick = onButtonClick,
            )
        }
    }
}

@Preview
@Composable
fun NumberPadPreview() {
    NumberPad(
        buttonSizeDp = Dp(80f),
        buttonSpaceDp = Dp(10f),
        buttonFontSp = 30.sp,
        onButtonClick = {
          // Do nothing here
        },
    )
}