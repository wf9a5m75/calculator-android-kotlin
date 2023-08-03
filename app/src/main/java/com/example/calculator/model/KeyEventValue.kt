package com.example.calculator.model

enum class ValueKind {
    NUMBER,
    ALL_CLEAR,
    PARENTHESES,
    PERCENT,
    DIVIDE,
    MULTIPLY,
    SUBTRACT,
    ADD,
    EQUALS,
    DELETE,
    DOT,
}

data class ValueModel<T>(
    var value: T,
    val kind: ValueKind,
)
