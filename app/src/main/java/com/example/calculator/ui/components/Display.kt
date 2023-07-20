package com.example.calculator.ui.components


import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout

@Preview
@Composable
fun Display(
    modifier: Modifier = Modifier
        .fillMaxWidth()
        .height(70.dp),
    formula: String = "(formula)",
    answer: String = "(answer)",
    formulaFontSize: TextUnit = 30.sp,
    answerFontSize: TextUnit = 30.sp,
) {
    ConstraintLayout(modifier = modifier) {
        val (formulaText, answerText) = createRefs()

        Text(
            text = formula,
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
            text = answer,
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
