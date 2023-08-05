package com.example.rpn


internal fun findEndOfSubEquationIndex(equation: String): Int {
    if (!equation.startsWith('(')) {
        throw RpnError("The given expression must start with '('")
    }

    var i = 1
    var leftBracket = 1
    var rightBracket = 0
    while (i < equation.length) {
        when(equation[i]) {
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