package com.example.rpn

internal fun isOperator(target: String): Boolean {
    return target == Operator.ADD.symbol ||
            target == Operator.SUBTRACT.symbol ||
            target == Operator.DIVIDE.symbol ||
            target == Operator.MULTIPLY.symbol
}