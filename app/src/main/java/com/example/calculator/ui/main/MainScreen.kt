package com.example.calculator.ui.main

import android.app.Activity
import android.content.Context
import android.content.res.Configuration
import android.view.KeyEvent
import android.view.inputmethod.BaseInputConnection
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.key
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTagsAsResourceId
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.calculator.ui.components.Display
import com.example.calculator.ui.components.NumberPad
import com.example.calculator.ui.theme.CalculatorTheme
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

fun simulateKeyPress(context: Context, key: Int) {
    val a = context as Activity
    a.window.decorView.rootView
    val inputConnection = BaseInputConnection(
        a.window.decorView.rootView,
        true
    )
    val downEvent = KeyEvent(KeyEvent.ACTION_DOWN, key)
    val upEvent = KeyEvent(KeyEvent.ACTION_UP, key)
    inputConnection.sendKeyEvent(downEvent)
    inputConnection.sendKeyEvent(upEvent)
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun MainScreen(
    viewModel: IMainScreenViewModel
) {
    val buttonSpace = 10f
    val buttonSpaceDp = Dp(buttonSpace)
    val screenWidth = LocalConfiguration.current.screenWidthDp

    val numOfButtonsInRow = 4
    val numOfSpaces = numOfButtonsInRow + 1
    val buttonSize = (screenWidth - buttonSpace * numOfSpaces) / numOfButtonsInRow
    val buttonSizeDp = Dp(buttonSize)
    val buttonFontSp = (buttonSize * 0.3).sp

    val context = LocalContext.current
    val focusRequester = remember { FocusRequester() }

    var backHandlingEnabled by remember { mutableStateOf(true) }
    BackHandler(backHandlingEnabled) {
        viewModel.clear()
        backHandlingEnabled = false
    }

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
            numberPad,
        ) = createRefs()

        Display(
            formulaFontSize = 40.sp,
            answerFontSize = 50.sp,
            modifier = Modifier.constrainAs(display) {
                top.linkTo(parent.top, margin = buttonSpaceDp)
                bottom.linkTo(numberPad.top, margin = buttonSpaceDp)
            },
            focusRequester = focusRequester,
            resultValue = viewModel.result,
            equationValue = viewModel.equation,
            onKeyEvent = {
                backHandlingEnabled = viewModel.equation.value.isNotEmpty()

                when (it.key) {
                    Key.Enter -> {
                        viewModel.calculate()
                        true
                    }

                    else -> false
                }
            }
        )

        NumberPad(
            modifier = Modifier.constrainAs(numberPad) {
                bottom.linkTo(parent.bottom, margin = buttonSpaceDp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            },
            buttonSizeDp = buttonSizeDp,
            buttonFontSp = buttonFontSp,
            buttonSpaceDp = buttonSpaceDp,
            onButtonClick = {
                simulateKeyPress(context, it.keyCode)
            },
        )
    }
    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }
}


@Preview(
    device = Devices.PIXEL_4,
    showBackground = true,
    showSystemUi = true,
)
@Preview(
    device = Devices.PIXEL_XL,
    showBackground = true,
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
private fun MainScreenPreview() {
    CalculatorTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            MainScreen(viewModel = object : IMainScreenViewModel {
                override var dispatcher: CoroutineDispatcher = Dispatchers.IO
                override val equation: MutableStateFlow<String>
                    get() = MutableStateFlow("1+2")
                override val result: StateFlow<String>
                    get() = MutableStateFlow("3.0")

                override fun calculate() {
                    // Do nothing here
                }

                override fun clear() {
                    // Do nothing here
                }
            })
        }
    }
}