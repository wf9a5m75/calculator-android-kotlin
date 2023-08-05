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
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTag
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

const val EQUATION_ROW_TEST_TAG = "equation_display"
const val RESULT_ROW_TEST_TAG = "answer_display"

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun Display(
    modifier: Modifier = Modifier
        .fillMaxWidth(),
    formulaFontSize: TextUnit = 30.sp,
    answerFontSize: TextUnit = 30.sp,
    focusRequester: FocusRequester,
    resultValue: StateFlow<String>,
    equationValue: MutableStateFlow<String>,
    onKeyEvent: (key: androidx.compose.ui.input.key.KeyEvent) -> Boolean,
) {

    val answerTextProp = resultValue.collectAsState()
    val equationProp = equationValue.collectAsState()

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
                value = equationProp.value,
                onValueChange = {
                    equationValue.value = rpnNormalize(it, true)
                },
                modifier = Modifier
                    .focusRequester(focusRequester)
                    .fillMaxWidth()
                    .constrainAs(expressionDisplay) {
                        top.linkTo(parent.top)
                        bottom.linkTo(resultDisplay.top)
                    }
                    .semantics {
                        testTag = EQUATION_ROW_TEST_TAG
                        contentDescription = EQUATION_ROW_TEST_TAG
                    }
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
                .semantics {
                    testTag = RESULT_ROW_TEST_TAG
                    contentDescription = RESULT_ROW_TEST_TAG
                },
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
        resultValue = MutableStateFlow("1+2"),
        equationValue = MutableStateFlow("3"),
        onKeyEvent = { false },
    )
}
