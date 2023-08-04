package com.example.rpn


internal fun findEndOfSubEquationIndex(expression: String): Int {
    if (!expression.startsWith('(')) {
        throw RpnError("The given expression must start with '('")
    }

    var i = 1
    var leftBracket = 1
    var rightBracket = 0
    while (i < expression.length) {
        when(expression[i]) {
            '(' -> leftBracket += 1
            ')' -> rightBracket += 1
            else -> {}
        }
        if (leftBracket == rightBracket) {
            return i + 1
        }
        i += 1
    }
    return i
}