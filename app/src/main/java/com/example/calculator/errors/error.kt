package com.example.calculator.errors

import androidx.compose.ui.res.stringResource

sealed class CalculatorError : Error()
class MainScreenError(override val message: String?) : CalculatorError()
class RpnError(override val message: String?) : CalculatorError()
class ZeroDivideError : CalculatorError()