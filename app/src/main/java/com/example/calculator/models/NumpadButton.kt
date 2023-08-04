package com.example.calculator.models

import android.view.KeyEvent
import com.example.rpn.Operator


enum class NumpadButton(
    val label: String,
    val keyCode: Int,
) {
    ALL_CLEAR(
        label = "AC",
        keyCode = KeyEvent.KEYCODE_C,
    ),
    LEFT_BRACKET(
        label = com.example.rpn.Operator.LEFT_BRACKET.symbol,
        keyCode = KeyEvent.KEYCODE_LEFT_BRACKET,
    ),
    RIGHT_BRACKET(
        label = com.example.rpn.Operator.RIGHT_BRACKET.symbol,
        keyCode = KeyEvent.KEYCODE_RIGHT_BRACKET,
    ),
    ADD(
        label = com.example.rpn.Operator.ADD.symbol,
        keyCode = KeyEvent.KEYCODE_PLUS,
    ),
    SUBTRACT(
        label = com.example.rpn.Operator.SUBTRACT.symbol,
        keyCode = KeyEvent.KEYCODE_MINUS,
    ),
    MULTIPLY(
        label = com.example.rpn.Operator.MULTIPLY.symbol,
        keyCode = KeyEvent.KEYCODE_STAR,
    ),
    DIVIDE(
        label = com.example.rpn.Operator.DIVIDE.symbol,
        keyCode = KeyEvent.KEYCODE_SLASH,
    ),
    DELETE(
        label = "DEL",
        keyCode = KeyEvent.KEYCODE_DEL,
    ),
    EQUAL(
        label = "=",
        keyCode = KeyEvent.KEYCODE_ENTER,
    ),
    ZERO(
        label = "0",
        keyCode = KeyEvent.KEYCODE_0,
    ),
    ONE(
        label = "1",
        keyCode = KeyEvent.KEYCODE_1,
    ),
    TWO(
        label = "2",
        keyCode = KeyEvent.KEYCODE_2,
    ),
    THREE(
        label = "3",
        keyCode = KeyEvent.KEYCODE_3,
    ),
    FOUR(
        label = "4",
        keyCode = KeyEvent.KEYCODE_4,
    ),
    FIVE(
        label = "5",
        keyCode = KeyEvent.KEYCODE_5,
    ),
    SIX(
        label = "6",
        keyCode = KeyEvent.KEYCODE_6,
    ),
    SEVEN(
        label = "7",
        keyCode = KeyEvent.KEYCODE_7,
    ),
    EIGHT(
        label = "8",
        keyCode = KeyEvent.KEYCODE_8,
    ),
    NINE(
        label = "9",
        keyCode = KeyEvent.KEYCODE_9,
    ),
    DOT(
        label = ".",
        keyCode = KeyEvent.KEYCODE_PERIOD,
    ),
}