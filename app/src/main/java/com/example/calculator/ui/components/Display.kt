package com.example.calculator.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.platform.LocalTextInputService
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTagsAsResourceId
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.rpn.rpnNormalize
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

const val EXPRESSION_TEST_TAG = "expression"
const val RESULT_TEST_TAG = "result"

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun Display(
    modifier: Modifier = Modifier
        .fillMaxWidth(),
    formulaFontSize: TextUnit = 30.sp,
    answerFontSize: TextUnit = 30.sp,
    focusRequester: FocusRequester,
    answerValue: StateFlow<String>,
    expressionValue: MutableStateFlow<String>,
    onKeyEvent: (key: androidx.compose.ui.input.key.KeyEvent) -> Boolean,
) {

    val answerTextProp = answerValue.collectAsState()
    val expressionProp = expressionValue.collectAsState()

    ConstraintLayout(
        modifier = modifier
        .semantics {
            testTagsAsResourceId = true
        }
    ) {
        val (expressionDisplay,
            resultDisplay) = createRefs()

        CompositionLocalProvider(
          LocalTextInputService provides null
        ) {
            BasicTextField(
                value = expressionProp.value,
                onValueChange = {
                    expressionValue.value = rpnNormalize(it)
                },
                modifier = Modifier
                    .focusRequester(focusRequester)
                    .fillMaxWidth()
                    .constrainAs(expressionDisplay) {
                        top.linkTo(parent.top)
                        bottom.linkTo(resultDisplay.top)
                    }
                    .testTag(EXPRESSION_TEST_TAG)
                    .onKeyEvent {
                        return@onKeyEvent onKeyEvent(it)
                    }
                ,
                textStyle = TextStyle(
                    fontSize = formulaFontSize,
                    textAlign = TextAlign.Right,
                )
            )
        }
        
        Text(
            text = answerTextProp.value,
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(resultDisplay) {
                    bottom.linkTo(parent.bottom)
                }
                .testTag(RESULT_TEST_TAG),
            fontSize = answerFontSize,
            textAlign = TextAlign.Right,
        )
    }
}

@Preview(
    showBackground = true,
)
@Composable
private fun DisplayPreview() {
    Display(
        focusRequester = FocusRequester(),
        answerValue = MutableStateFlow("1+2"),
        expressionValue = MutableStateFlow("3"),
        onKeyEvent = { false },
    )
}
