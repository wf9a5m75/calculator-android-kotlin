package com.example.rpn

sealed class CalculatorError : Error()
class RpnError(override val message: String?) : CalculatorError()
class ZeroDivideError : CalculatorError()
class SyntaxError : CalculatorError()