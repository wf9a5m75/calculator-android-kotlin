package com.example.calculator.ui.components

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalTextInputService
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.viewModelScope
import com.example.calculator.ui.main.MainScreenViewModel
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

@Preview
@Composable
fun Display(
    modifier: Modifier = Modifier
        .fillMaxWidth()
        .height(70.dp),
    viewModel: MainScreenViewModel? = null,
    formulaFontSize: TextUnit = 30.sp,
    answerFontSize: TextUnit = 30.sp,
) {
    var formulaTxt by rememberSaveable {
        mutableStateOf(viewModel?.formula?.value ?: "")
    }
    val answerTxt by remember { viewModel?.answer ?: mutableStateOf("") }
//    val allowKeys = remember {
//        setOf(*("0123456789+-*/=().".split("").toTypedArray()))
//    }
//    val characterFilter = fun(input: String): String {
//        return (input.toCharArray())
//            .filter { allowKeys.contains(it.toString()) }
//            .joinToString("")
//    }

    ConstraintLayout(modifier = modifier) {
        val (formulaText, answerText) = createRefs()

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(formulaText) {
                    top.linkTo(parent.top)
                    bottom.linkTo(answerText.top)
                }
        ) {
            Text(
                text = formulaTxt,
                modifier = Modifier
                    .fillMaxWidth()
            )
//            CompositionLocalProvider(
//                LocalTextInputService provides null
//            ) {
//                BasicTextField(
//                    value = formulaTxt,
//                    modifier = Modifier
//                        .fillMaxWidth(),
//                    textStyle = LocalTextStyle.current.copy(
//                        textAlign = TextAlign.Right,
//                        fontSize = formulaFontSize,
//                        background = Color.Transparent,
//                    ),
//                    onValueChange = {
//                        formulaTxt = characterFilter(it)
//                    },
//                    keyboardOptions = KeyboardOptions(
//                        imeAction = ImeAction.None,
//                        keyboardType = KeyboardType.Number,
//                    ),
//                )
//            }
        }
        Text(
            text = answerTxt,
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(answerText) {
                    bottom.linkTo(parent.bottom)
                },
            fontSize = answerFontSize,
            textAlign = TextAlign.Right,
        )
    }
}
