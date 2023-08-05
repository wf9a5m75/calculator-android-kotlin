package com.example.rpn

import java.util.Stack

internal fun parseRpnToken(equation: String): List<RpnToken> {
    val result = Stack<RpnToken>()

    var i = 0
    while (i < equation.length) {
        val it = equation[i]

        when {
            it == '-'  || it.isDigit() -> {
                // The case for '-(2+3)'
                if ((it == '-') &&
                    (i + 1 < equation.length) &&
                    (!equation[i + 1].isDigit())) {
                    result.push(
                        OperatorToken(
                            value = Operator.SUBTRACT,
                        ),
                    )
                    i += 1
                    continue
                }

                // The cases for '-2.34', '2.34', '2', and '2.0'
                val restOfEquation = equation.substring(i)
                val j = findEndOfNumberIndex(
                    equation = restOfEquation,
                )
                val newNumber = restOfEquation.substring(0, j).toBigDecimal()
                if (result.isNotEmpty() && result.peek().kind == RpnKind.NUMBER) {
                    result.add(
                        OperatorToken(
                            value = Operator.ADD,
                        ),
                    )
                }
                result.add(
                    NumberToken(
                        value = newNumber,
                        containsDot = !newNumber.isInteger(),
                    )
                )

                i += j
            }

            isOperator(it.toString()) -> {
                result.push(
                    OperatorToken(
                        value = Operator.fromString(it.toString())
                    ),
                )
                i += 1
            }

            it == '(' -> {
                val restOfEquation = equation.substring(i)
                val j = findEndOfSubEquationIndex(
                    equation = restOfEquation,
                )
                val subEquation = restOfEquation.substring(0, j)
                    .replace(Regex("^\\("), "")
                    .replace(Regex("\\)$"), "")
                val subResult = rpnCalculate(
                    equation = subEquation,
                ).toBigDecimal()
                println("--->subResult = ${subEquation} => ${subResult}")

                if (result.isNotEmpty() && result.peek().kind == RpnKind.NUMBER) {
                    result.add(
                        OperatorToken(
                            value = Operator.MULTIPLY,
                        ),
                    )
                }

                result.push(
                    NumberToken(
                        value = subResult,
                        containsDot = subResult.isInteger(),
                    )
                )

                if ((i + j < equation.length) && (!isOperator(equation[i + j].toString()))) {
                    result.push(
                        OperatorToken(
                            value = Operator.MULTIPLY,
                        ),
                    )
                }
                i += j
            }

            else -> {
                throw RpnError("Unexpected token")
            }
        }
    }

    return result
}