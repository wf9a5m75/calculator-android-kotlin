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
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.key
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
import com.example.calculator.ui.main.IMainScreenViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.lang.Integer.min

const val EXPRESSION_TEST_TAG = "expression"
const val RESULT_TEST_TAG = "result"


private fun isOperator(c: Char): Boolean {
    return "+-*/".contains(c)
}
private fun characterFilter(input: String): String {
    var prevChar: Char = '!'
    var brackets = 0
    return (input.toCharArray())
        .filter {
            when(it) {
                '0', '1', '2', '3', '4', '5', '6', '7','8', '9'-> {
                    prevChar = it
                    true
                }
                '+', '-', '*', '/' -> {
                    val result = isOperator(prevChar) != isOperator(it)
                    prevChar = it
                    result
                }
                '.' -> {
                    val result = prevChar != it
                    prevChar = it
                    result
                }
                '(' -> {
                    brackets += 1
                    true
                }
                ')' -> {
                    brackets = min(brackets - 1, 0)
                    brackets > 0
                }
                else -> false
            }
        }
        .joinToString("")
        .replace("()", "")
        .replace("..", ".")
        .replace(Regex("^[*/]"), "")
}
@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun Display(
    modifier: Modifier = Modifier
        .fillMaxWidth(),
    viewModel: IMainScreenViewModel,
    formulaFontSize: TextUnit = 30.sp,
    answerFontSize: TextUnit = 30.sp,
    focusRequester: FocusRequester,
) {

    val answerTextProp = viewModel.result.collectAsState()

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
                value = viewModel.expression.collectAsState().value,
                onValueChange = {
                    viewModel.expression.value = characterFilter(it)
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
                        return@onKeyEvent when (it.key) {
                            Key.Enter -> {
                                viewModel.calculate()
                                true
                            }
                            Key.C -> {
                                viewModel.clear()
                                true
                            }
                            else -> false
                        }

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
        viewModel = object : IMainScreenViewModel {
            override var dispatcher: CoroutineDispatcher = Dispatchers.IO
            override val expression: MutableStateFlow<String>
                get() = MutableStateFlow("1+2")
            override val result: StateFlow<String>
                get() = MutableStateFlow("3.0")

            override fun calculate() {
                // Do nothing here
            }

            override fun clear() {
                // Do nothing here
            }
        },
        focusRequester = FocusRequester()
    )
}
