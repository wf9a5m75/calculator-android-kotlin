package com.example.rpn

import java.lang.Error
import java.math.BigDecimal
import java.util.Stack

fun BigDecimal.isInteger() : Boolean {
    return this.stripTrailingZeros().scale() <= 0;
}

fun rpnCalculate(equation: String): String {
    println("=======================")
    println("[equation] $equation")
    val normalizedInput = rpnNormalize(equation)
    println("[normalized] $normalizedInput")

    try {
        val tokens = parseRpnToken(normalizedInput)
        debugOutput("[tokens]", tokens)

        val rpnTokens = toRPN(tokens)
        debugOutput("[rpnTokens]", rpnTokens)

        val rpnStack = Stack<RpnToken>()
        for (i in rpnTokens.indices) {
            val token = rpnTokens[i]
            when (token.kind) {
                RpnKind.NUMBER -> {
                    rpnStack.push(token)
                }

                RpnKind.OPERATOR -> {
                    val rightHandValue = rpnStack.pop() as NumberToken
                    if (rightHandValue.kind != RpnKind.NUMBER) {
                        throw SyntaxError()
                    }
                    val leftHandValue = when (rpnStack.isNotEmpty()) {
                        true -> rpnStack.pop() as NumberToken
                        false -> NumberToken(
                            value = BigDecimal.ZERO,
                        )
                    }
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
            debugOutput("[stack]", rpnStack)
        }

        val result = rpnStack.pop()
        if (result.kind != RpnKind.NUMBER) {
            throw SyntaxError()
        }
        return (result as NumberToken).value.stripTrailingZeros().toPlainString()
    } catch (e : SyntaxError) {
        return "Syntax Error"
    } catch (e : ZeroDivideError) {
        return "Can't divide by zero"
    } catch (e : Error) {
        return "Error"
    }
}