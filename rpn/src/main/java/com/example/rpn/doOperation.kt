package com.example.rpn

import java.math.BigDecimal
import java.math.RoundingMode


internal fun doOperation(
    leftHandValue: NumberToken,
    rightHandValue: NumberToken,
    operator: OperatorToken,
): NumberToken {
    val newValue = when (operator.value) {
        Operator.ADD -> leftHandValue.value.add(rightHandValue.value)

        Operator.SUBTRACT -> leftHandValue.value.subtract(rightHandValue.value)

        Operator.DIVIDE -> {
            if (rightHandValue.value.compareTo(BigDecimal.ZERO) == 0) {
                throw ZeroDivideError()
            }

            leftHandValue.value.divide(rightHandValue.value, 13, RoundingMode.HALF_UP)
        }

        Operator.MULTIPLY -> leftHandValue.value.multiply(rightHandValue.value)

        else -> {
            throw RpnError("Unexpected operation")
        }
    }
    return NumberToken(
        value = newValue,
        containsDot = newValue.isInteger(),
    )
}