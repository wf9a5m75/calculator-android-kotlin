package com.example.rpn

fun rpnNormalize(input: String): String {
    var prevChar: Char = '0'
    var hasDot = false
    return (input.toCharArray())
        .filter {
            when(it) {
                '0', '1', '2', '3', '4', '5', '6', '7','8', '9', '(', ')'-> {
                    prevChar = it
                    true
                }
                '+', '-', '*', '/' -> {
                    val result = when {
                        prevChar.isDigit() -> true

                        prevChar == ')' -> true

                        // i.e. 12*-12, 12/+12
                        (prevChar == '*' || prevChar == '/' || prevChar == '(') &&
                                (it == '+' || it == '-') -> true

                        // i.e. 12.1+-92
                        prevChar == '+' && it == '-' -> true

                        // i,e, +12.1+-23
                        prevChar == '-' && it == '+' -> true
                        else -> false
                    }
                    prevChar = it
                    hasDot = false
                    result
                }
                '.' -> {
                    val result = !hasDot
                    hasDot = true
                    result
                }
                else -> false
            }
        }
        .joinToString("")
        .replace("()", "")
        .replace("..", ".")
        .replace(Regex("^[*/]"), "")
}