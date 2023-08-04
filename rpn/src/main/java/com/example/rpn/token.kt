package com.example.rpn

import java.math.BigDecimal
import kotlin.math.abs

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
    ADD(
        priority = 2,
        symbol = "+",
    ),
    SUBTRACT(
        priority = 2,
        symbol = "-",
    ),
    MULTIPLY(
        priority = 3,
        symbol = "*",
    ),
    DIVIDE(
        priority = 3,
        symbol = "/",
    );

    companion object {
        fun fromString(symbol: String): Operator {
            return Operator.values().first { it.symbol == symbol }
        }

    }
}


internal fun List<RpnToken>.contentEquals(other: List<RpnToken>): Boolean {
    if (this.size != other.size) {
        return false
    }
    for (i in this.indices) {
        if (!this[i].isEqual(other[i])) {
            return false
        }
    }
    return true
}

internal sealed class RpnToken(
    val kind: RpnKind,
) {
    fun isEqual(other: RpnToken): Boolean {
        if (this.kind != other.kind) {
            return false
        }
        return when (this.kind) {
            RpnKind.NUMBER -> {
                this as NumberToken
                other as NumberToken
                this.value.compareTo(other.value) == 0
            }

            RpnKind.OPERATOR -> {
                this as OperatorToken
                other as OperatorToken
                this.value.symbol == other.value.symbol &&
                        this.value.priority == other.value.priority
            }

            else -> {
                throw RpnError("Unknown kind is specified")
            }
        }
    }
}

internal enum class RpnKind {
    NUMBER,
    OPERATOR,
}

internal data class OperatorToken(
    val value: Operator,
) : RpnToken(
    kind = RpnKind.OPERATOR,
)

internal data class NumberToken(
    val value: BigDecimal,
    val containsDot: Boolean = false,
) : RpnToken(
    kind = RpnKind.NUMBER,
)