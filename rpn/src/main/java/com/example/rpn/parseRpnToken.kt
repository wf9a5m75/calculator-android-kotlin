package com.example.rpn

import java.util.Stack

internal fun parseRpnToken(expression: String): List<RpnToken> {
    val result = Stack<RpnToken>()

    var i = 0
    while (i < expression.length) {
        val it = expression[i]

        when {
            it == '-'  || it.isDigit() -> {
                // The case for '-(2+3)'
                if ((it == '-') &&
                    (i + 1 < expression.length) &&
                    (!expression[i + 1].isDigit())) {
                    result.push(
                        OperatorToken(
                            value = Operator.SUBTRACT,
                        ),
                    )
                    i += 1
                    continue
                }

                // The cases for '-2.34', '2.34', '2', and '2.0'
                val restOfExpression = expression.substring(i)
                val j = findEndOfNumberIndex(
                    expression = restOfExpression,
                )
                val newNumber = restOfExpression.substring(0, j).toBigDecimal()
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
                val restOfExpression = expression.substring(i)
                val j = findEndOfSubEquationIndex(
                    expression = restOfExpression,
                )
                val subExpression = restOfExpression.substring(0, j)
                    .replace(Regex("^\\("), "")
                    .replace(Regex("\\)$"), "")
                val subResult = rpnCalculate(
                    expression = subExpression,
                ).toBigDecimal()
                println("--->subResult = ${subExpression} => ${subResult}")

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

                if ((i + j < expression.length) && (!isOperator(expression[i + j].toString()))) {
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