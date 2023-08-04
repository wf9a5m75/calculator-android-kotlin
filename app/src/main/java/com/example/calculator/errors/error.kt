package com.example.calculator.errors

sealed class CalculatorError : Error()
class MainScreenError(override val message: String?) : CalculatorError()
class RpnError(override val message: String?) : CalculatorError()
class ZeroDivideError : CalculatorError()
class SyntaxError : CalculatorError()