package com.example.calculator.ui.components

import android.view.KeyEvent
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
import com.example.calculator.model.KeyEventValue

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun NumberPad(
    modifier: Modifier = Modifier,
    buttonSizeDp: Dp,
    buttonSpaceDp: Dp,
    buttonFontSp: TextUnit,
    onButtonClick: (button: KeyEventValue) -> Unit,
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
                    .testTag("all_clear")
                ,
                fontSize = buttonFontSp,
                value = KeyEventValue("AC", KeyEvent.KEYCODE_ESCAPE),
                onClick = {
                    onButtonClick(it)
                },
            )
            Spacer(modifier = Modifier.width(buttonSpaceDp))
            OperatorButton(
                modifier = Modifier
                    .width(buttonSizeDp)
                    .testTag("parentheses"),
                fontSize = buttonFontSp,
                value = KeyEventValue("(", KeyEvent.KEYCODE_LEFT_BRACKET),
                onClick = {
                    onButtonClick(it)
                },
            )
            Spacer(modifier = Modifier.width(buttonSpaceDp))
            OperatorButton(
                modifier = Modifier
                    .width(buttonSizeDp)
                    .testTag("percent"),
                fontSize = buttonFontSp,
                value = KeyEventValue(")", KeyEvent.KEYCODE_RIGHT_BRACKET),
                onClick = {
                    onButtonClick(it)
                },
            )
            Spacer(modifier = Modifier.width(buttonSpaceDp))
            OperatorButton(
                modifier = Modifier
                    .width(buttonSizeDp)
                    .testTag("divides"),
                fontSize = buttonFontSp,
                value = KeyEventValue("/", KeyEvent.KEYCODE_SLASH),
                onClick = {
                    onButtonClick(it)
                },
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
                    .testTag("seven")
                    .width(buttonSizeDp),
                fontSize = buttonFontSp,
                value = KeyEventValue("7", KeyEvent.KEYCODE_7),
                onClick = {
                    onButtonClick(it)
                },
            )
            Spacer(modifier = Modifier.width(buttonSpaceDp))
            NumberInputButton(
                modifier = Modifier
                    .testTag("eight")
                    .width(buttonSizeDp),
                fontSize = buttonFontSp,
                value = KeyEventValue("8", KeyEvent.KEYCODE_8),
                onClick = {
                    onButtonClick(it)
                },
            )
            Spacer(modifier = Modifier.width(buttonSpaceDp))
            NumberInputButton(
                modifier = Modifier
                    .testTag("nine")
                    .width(buttonSizeDp),
                fontSize = buttonFontSp,
                value = KeyEventValue("9", KeyEvent.KEYCODE_9),
                onClick = {
                    onButtonClick(it)
                },
            )
            Spacer(modifier = Modifier.width(buttonSpaceDp))
            OperatorButton(
                modifier = Modifier
                    .testTag("multiplies")
                    .width(buttonSizeDp),
                fontSize = buttonFontSp,
                value = KeyEventValue("*", KeyEvent.KEYCODE_STAR),
                onClick = {
                    onButtonClick(it)
                },
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
                    .testTag("four")
                    .width(buttonSizeDp),
                fontSize = buttonFontSp,
                value = KeyEventValue("4", KeyEvent.KEYCODE_4),
                onClick = {
                    onButtonClick(it)
                },
            )
            Spacer(modifier = Modifier.width(buttonSpaceDp))
            NumberInputButton(
                modifier = Modifier
                    .testTag("five")
                    .width(buttonSizeDp),
                fontSize = buttonFontSp,
                value = KeyEventValue("5", KeyEvent.KEYCODE_5),
                onClick = {
                    onButtonClick(it)
                },
            )
            Spacer(modifier = Modifier.width(buttonSpaceDp))
            NumberInputButton(
                modifier = Modifier
                    .testTag("six")
                    .width(buttonSizeDp),
                fontSize = buttonFontSp,
                value = KeyEventValue("6", KeyEvent.KEYCODE_6),
                onClick = {
                    onButtonClick(it)
                },
            )
            Spacer(modifier = Modifier.width(buttonSpaceDp))
            OperatorButton(
                modifier = Modifier
                    .testTag("subtracts")
                    .width(buttonSizeDp),
                fontSize = buttonFontSp,
                value = KeyEventValue("-", KeyEvent.KEYCODE_MINUS),
                onClick = {
                    onButtonClick(it)
                },
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
                    .testTag("one")
                    .width(buttonSizeDp),
                fontSize = buttonFontSp,
                value = KeyEventValue("1", KeyEvent.KEYCODE_1),
                onClick = {
                    onButtonClick(it)
                },
            )
            Spacer(modifier = Modifier.width(buttonSpaceDp))
            NumberInputButton(
                modifier = Modifier
                    .testTag("two")
                    .width(buttonSizeDp),
                fontSize = buttonFontSp,
                value = KeyEventValue("2", KeyEvent.KEYCODE_2),
                onClick = {
                    onButtonClick(it)
                },
            )
            Spacer(modifier = Modifier.width(buttonSpaceDp))
            NumberInputButton(
                modifier = Modifier
                    .testTag("three")
                    .width(buttonSizeDp),
                fontSize = buttonFontSp,
                value = KeyEventValue("3", KeyEvent.KEYCODE_3),
                onClick = {
                    onButtonClick(it)
                },
            )
            Spacer(modifier = Modifier.width(buttonSpaceDp))
            OperatorButton(
                modifier = Modifier
                    .testTag("adds")
                    .width(buttonSizeDp),
                fontSize = buttonFontSp,
                value = KeyEventValue("+", KeyEvent.KEYCODE_PLUS),
                onClick = {
                    onButtonClick(it)
                },
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
                    .testTag("zero")
                    .width(buttonSizeDp),
                fontSize = buttonFontSp,
                value = KeyEventValue("0", KeyEvent.KEYCODE_0),
                onClick = {
                    onButtonClick(it)
                },
            )
            Spacer(modifier = Modifier.width(buttonSpaceDp))
            SpecialInputButton(
                modifier = Modifier
                    .testTag("dot")
                    .width(buttonSizeDp),
                fontSize = buttonFontSp,
                value = KeyEventValue(".", KeyEvent.KEYCODE_PERIOD),
                onClick = {
                    onButtonClick(it)
                },
            )
            Spacer(modifier = Modifier.width(buttonSpaceDp))
            SpecialInputButton(
                fontSize = buttonFontSp,
                modifier = Modifier
                    .testTag("deletes")
                    .width(buttonSizeDp),
                value = KeyEventValue("DEL", KeyEvent.KEYCODE_BACK),
                onClick = {
                    onButtonClick(it)
                },
            )
            Spacer(modifier = Modifier.width(buttonSpaceDp))
            ActionButton(
                modifier = Modifier
                    .testTag("equals")
                    .width(buttonSizeDp),
                fontSize = buttonFontSp,
                value = KeyEventValue("=", KeyEvent.KEYCODE_EQUALS),
                onClick = {
                    onButtonClick(it)
                },
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