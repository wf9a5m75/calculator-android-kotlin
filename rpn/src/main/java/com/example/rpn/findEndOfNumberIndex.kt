package com.example.rpn


internal fun findEndOfNumberIndex(equation: String): Int {
    if (equation.isEmpty()) {
        return 0
    }

    var i = 0
    var containDot = false
    if (equation[0] == '+' || equation[0] == '-') {
        i += 1
    }
    while (i < equation.length) {
        when {
            equation[i].isDigit() -> i += 1

            equation[i] == '.' -> {
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