package com.example.calculator.models

import android.view.KeyEvent
import com.example.rpn.Operator


enum class NumpadButton(
    val label: String,
    val keyCode: Int,
    val testTag: String,
) {
    ALL_CLEAR(
        label = "AC",
        keyCode = KeyEvent.KEYCODE_ESCAPE,
        testTag = "all_clear",
    ),
    LEFT_BRACKET(
        label = com.example.rpn.Operator.LEFT_BRACKET.symbol,
        keyCode = KeyEvent.KEYCODE_LEFT_BRACKET,
        testTag = "left_bracket",
    ),
    RIGHT_BRACKET(
        label = com.example.rpn.Operator.RIGHT_BRACKET.symbol,
        keyCode = KeyEvent.KEYCODE_RIGHT_BRACKET,
        testTag = "right_bracket",
    ),
    ADD(
        label = com.example.rpn.Operator.ADD.symbol,
        keyCode = KeyEvent.KEYCODE_PLUS,
        testTag = "add",
    ),
    SUBTRACT(
        label = com.example.rpn.Operator.SUBTRACT.symbol,
        keyCode = KeyEvent.KEYCODE_MINUS,
        testTag = "subtract",
    ),
    MULTIPLY(
        label = com.example.rpn.Operator.MULTIPLY.symbol,
        keyCode = KeyEvent.KEYCODE_STAR,
        testTag = "multiply",
    ),
    DIVIDE(
        label = com.example.rpn.Operator.DIVIDE.symbol,
        keyCode = KeyEvent.KEYCODE_SLASH,
        testTag = "divide",
    ),
    DELETE(
        label = "DEL",
        keyCode = KeyEvent.KEYCODE_DEL,
        testTag = "delete",
    ),
    EQUAL(
        label = "=",
        keyCode = KeyEvent.KEYCODE_ENTER,
        testTag = "equal",
    ),
    ZERO(
        label = "0",
        keyCode = KeyEvent.KEYCODE_0,
        testTag = "zero",
    ),
    ONE(
        label = "1",
        keyCode = KeyEvent.KEYCODE_1,
        testTag = "one",
    ),
    TWO(
        label = "2",
        keyCode = KeyEvent.KEYCODE_2,
        testTag = "two",
    ),
    THREE(
        label = "3",
        keyCode = KeyEvent.KEYCODE_3,
        testTag = "three",
    ),
    FOUR(
        label = "4",
        keyCode = KeyEvent.KEYCODE_4,
        testTag = "four",
    ),
    FIVE(
        label = "5",
        keyCode = KeyEvent.KEYCODE_5,
        testTag = "five",
    ),
    SIX(
        label = "6",
        keyCode = KeyEvent.KEYCODE_6,
        testTag = "six",
    ),
    SEVEN(
        label = "7",
        keyCode = KeyEvent.KEYCODE_7,
        testTag = "seven",
    ),
    EIGHT(
        label = "8",
        keyCode = KeyEvent.KEYCODE_8,
        testTag = "eight",
    ),
    NINE(
        label = "9",
        keyCode = KeyEvent.KEYCODE_9,
        testTag = "nine",
    ),
    DOT(
        label = ".",
        keyCode = KeyEvent.KEYCODE_PERIOD,
        testTag = "dot",
    ),
}