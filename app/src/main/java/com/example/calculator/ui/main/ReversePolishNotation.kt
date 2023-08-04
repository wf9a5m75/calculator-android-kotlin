package com.example.calculator.ui.main

import android.util.Log
import com.example.calculator.errors.RpnError
import com.example.calculator.errors.SyntaxError
import com.example.calculator.errors.ZeroDivideError
import java.util.Stack
import kotlin.math.abs
import kotlin.math.pow

private const val ASC_CODE_0 = 0x30

private sealed class RpnToken(
    val kind: RpnKind,
)

private enum class RpnKind {
    NUMBER,
    OPERATOR,
}

private data class OperatorToken(
    val value: Operator,
): RpnToken(
    kind = RpnKind.OPERATOR,
)

private data class NumberToken(
    val value: Double,
    val containsDot: Boolean,
): RpnToken(
    kind = RpnKind.NUMBER,
) {
    fun append(digit: Int): NumberToken {
        val d = (abs(digit) % 10).toDouble()
        return when(containsDot) {
            false -> {
                NumberToken(
                    value = this.value * 10 + d,
                    containsDot = false,
                )
            }

            true -> {
                val decimal = this.value.toString().split(".")[1]
                val decimalLen = if (decimal == "0") { 0 } else { decimal.length}

                NumberToken(
                    value = this.value + d / 10.0.pow((decimalLen + 1).toDouble()),
                    containsDot = false,
                )
            }
        }
    }

    fun setDot(): NumberToken {
        if (this.containsDot) {
            throw SyntaxError()
        }
        return NumberToken(
            value = this.value,
            containsDot = true,
        )
    }
}

enum class Operator(
    val priority: Int,
    val symbol: String,
) {
    LEFT_BRACKET(
        priority = 1,
        symbol = "(",
    ),
    RIGHT_BRACKET(
        priority = 1,
        symbol = ")",
    ),
    MULTIPLY(
        priority = 2,
        symbol = "*",
    ),
    DIVIDE(
        priority = 2,
        symbol = "/",
    ),
    ADD(
        priority = 3,
        symbol = "+",
    ),
    SUBTRACT(
        priority = 3,
        symbol = "-",
    );

    companion object  {
        fun fromString(symbol: String): Operator {
            return Operator.values().first() { it.symbol == symbol}
        }

    }
}

class ReversePolishNotation {
    companion object {
        fun calculate(expression: String): Double {
            val tokens = parse(expression)
            val rpnTokens = toRPN(tokens)
            Log.d("RPN", "=======================")
            debugOutput(rpnTokens)

            val rpnStack = Stack<RpnToken>()
            while (rpnTokens.isNotEmpty()) {
                debugOutput(rpnStack)
                val token = rpnTokens.removeAt(0)
                when (token.kind) {
                    RpnKind.NUMBER -> {
                        rpnStack.push(token)
                    }

                    RpnKind.OPERATOR -> {
                        val rightHandValue = rpnStack.pop() as NumberToken
                        if (rightHandValue.kind != RpnKind.NUMBER) {
                            throw SyntaxError()
                        }
                        val leftHandValue = rpnStack.pop() as NumberToken
                        if (leftHandValue.kind != RpnKind.NUMBER) {
                            throw SyntaxError()
                        }

                        val newValue = doOperation(
                            leftHandValue = leftHandValue,
                            rightHandValue = rightHandValue,
                            operator = token as OperatorToken,
                        )

                        rpnStack.push(newValue)
                    }

                    else -> {
                        throw RpnError("Unexpected token")
                    }
                }
            }
    //        debugOutput(rpnStack)
            val result = rpnStack.pop()
            if (result.kind != RpnKind.NUMBER) {
                throw SyntaxError()
            }
            return (result as NumberToken).value
        }

        private fun parse(expression: String): ArrayList<RpnToken> {
            val result = ArrayList<RpnToken>()
            var currentNumber = NumberToken(
                value = 0.0,
                containsDot = false,
            )
            var bracketCnt = 0

            expression.forEach {
                when {
                    it.isDigit() -> {
                        val digit = it.code - ASC_CODE_0
                        currentNumber = currentNumber.append(digit)
                    }

                    isOperator(it.toString()) -> {
                        result.add(currentNumber)

                        currentNumber = NumberToken(
                            value = 0.0,
                            containsDot = false,
                        )

                        result.add(
                            OperatorToken(
                                value = Operator.fromString(it.toString())
                            ),
                        )
                    }

                    it == '(' -> {
                        bracketCnt += 1
                    }

                    it == ')' -> {
                        bracketCnt -= 1
                        if (bracketCnt < 0) {
                            throw SyntaxError()
                        }
                    }

                    it == '.' -> {
                        currentNumber = currentNumber.setDot()
                    }
                    else -> {}

                }
            }
            result.add(currentNumber)

            if (bracketCnt != 0) {
                throw SyntaxError()
            }

            return result
        }

        private fun isOperator(target: String): Boolean {
            return target == Operator.ADD.symbol ||
                    target == Operator.SUBTRACT.symbol ||
                    target == Operator.DIVIDE.symbol ||
                    target == Operator.MULTIPLY.symbol
        }

        private fun toRPN(inputs: ArrayList<RpnToken>): ArrayList<RpnToken> {
            val result = ArrayList<RpnToken>()
            val operators = Stack<OperatorToken>()

            for (token in inputs) {
                when (token.kind) {
                    RpnKind.NUMBER -> {
                        result.add(token)
                    }

                    RpnKind.OPERATOR -> {
                        token as OperatorToken
                        if (operators.isEmpty()) {
                            operators.push(token)
                            continue
                        }

                        while (
                            operators.isNotEmpty() &&
                            operators.peek().value.priority >= token.value.priority
                        ) {
                            result.add(operators.pop())
                        }
                        operators.push(token)
                    }

                    else -> {
                        throw RpnError("Unexpected token kind")
                    }
                }
            }

            while (operators.isNotEmpty()) {
                result.add(operators.pop())
            }

            return result
        }

        private fun doOperation(
            leftHandValue: NumberToken,
            rightHandValue: NumberToken,
            operator: OperatorToken,
        ) : NumberToken {
            val newValue = when(operator.value) {
                Operator.ADD -> leftHandValue.value + rightHandValue.value

                Operator.SUBTRACT -> leftHandValue.value - rightHandValue.value

                Operator.DIVIDE -> {
                    if (rightHandValue.value == 0.0) {
                        throw ZeroDivideError()
                    }

                    leftHandValue.value / rightHandValue.value
                }

                Operator.MULTIPLY -> leftHandValue.value * rightHandValue.value

                else -> {
                    throw RpnError("Unexpected operation")
                }
            }
            return NumberToken(
                value = newValue,
                containsDot = newValue % 1 != 0.0,
            )
        }

        private fun debugOutput(results: List<RpnToken>) {

            val buffer = StringBuffer()
            for (result in results) {
                when (result.kind) {
                    RpnKind.NUMBER -> {
                        result as NumberToken
                        buffer.append(result.value)
                    }
                    RpnKind.OPERATOR -> {
                        result as OperatorToken
                        buffer.append(result.value.symbol)
                    }
                    else -> {
                        throw RpnError("Unexpected token kind")
                    }
                }
                buffer.append(result)
            }
            Log.d("RPN", "  -> stack = $buffer")
        }
    }
}