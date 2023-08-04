package com.example.rpn

import java.util.Stack


internal fun toRPN(inputs: List<RpnToken>): List<RpnToken> {
    val result = ArrayList<RpnToken>()
    val operators = Stack<OperatorToken>()

    for (token in inputs) {
        when (token.kind) {
            RpnKind.NUMBER -> {
                result.add(token)
            }

            RpnKind.OPERATOR -> {
                token as OperatorToken
                while (true) {
                    if (operators.isEmpty()) {
                        operators.push(token)
                        break
                    } else if (operators.peek().value.priority < token.value.priority) {
                        operators.push(token)
                        break
                    } else {
                        result.add(operators.pop())
                    }
                }
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