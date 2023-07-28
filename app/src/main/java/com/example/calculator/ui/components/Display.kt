package com.example.calculator.ui.components


import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.calculator.ui.main.MainScreenViewModel

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
    val formulaTxt by remember { viewModel?.formula ?: mutableStateOf("") }
    val answerTxt by remember { viewModel?.answer ?: mutableStateOf("") }
    ConstraintLayout(modifier = modifier) {
        val (formulaText, answerText) = createRefs()

        Text(
            text = formulaTxt,
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(formulaText) {
                    top.linkTo(parent.top)
                    bottom.linkTo(answerText.top)
                },
            fontSize = formulaFontSize,
            textAlign = TextAlign.Right,
        )
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
//    LaunchedEffect(viewModel?.formulaTxt){
//
//    }
}
