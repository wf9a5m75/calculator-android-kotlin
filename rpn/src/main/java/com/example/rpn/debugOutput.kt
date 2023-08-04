package com.example.rpn

import android.util.Log

internal fun debugOutput(label: String, results: List<RpnToken>) {

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
        buffer.append(" ")
    }
    println( " $label = $buffer")
}