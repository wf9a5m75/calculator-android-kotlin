package com.example.rpn


internal fun findEndOfNumberIndex(expression: String): Int {
    if (expression.isEmpty()) {
        return 0
    }

    var i = 0
    var containDot = false
    if (expression[0] == '+' || expression[0] == '-') {
        i += 1
    }
    while (i < expression.length) {
        when {
            expression[i].isDigit() -> i += 1

            expression[i] == '.' -> {
                if (containDot) {
                    throw SyntaxError()
                }
                containDot = true
                i += 1
            }

            else -> {
                break
            }
        }
    }
    return i
}