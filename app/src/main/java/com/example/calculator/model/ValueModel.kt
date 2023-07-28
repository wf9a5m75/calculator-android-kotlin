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
    DO_CALCULATE,
    DELETE,
    DOT,
}

data class ValueModel<T>(
    var value: T,
    val kind: ValueKind,
)

// https://proandroiddev.com/using-previewparameters-and-providing-composables-to-jetpack-compose-previews-5b1f5a8fe192
//fun interface NumberButton {
////    @Composable
//    fun config(): ButtonConfig<ButtonValue<Int>>
//}
