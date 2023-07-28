package com.example.calculator.ui.main

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import com.example.calculator.errors.RpnError
import com.example.calculator.errors.ZeroDivideError
import com.example.calculator.model.ValueKind
import com.example.calculator.model.ValueModel
import java.util.Stack

data class InputToken(
    val elements: ValueModel<*>,
    val containsDot: Boolean,
)


class ReversePolishNotation {


    val formula = mutableStateOf("")
    val answer = mutableStateOf("")




    private fun calculate(): Double {
        val tokens = toTokens(inputs)
        val rpnTokens = toRPN(tokens)
//        Log.d("RPN", "=======================")
        debugOutput(rpnTokens)

        val rpnStack = Stack<InputToken>()
        while (!rpnTokens.isEmpty()) {
//            debugOutput(rpnStack)
            val token = rpnTokens.removeAt(0)
            when (token.elements.kind) {
                ValueKind.NUMBER -> {
                    rpnStack.push(token)
                }

                ValueKind.ADD,
                ValueKind.MULTIPLY,
                ValueKind.DIVIDE,
                ValueKind.SUBTRACT -> {
                    val rightHandValue = rpnStack.pop()
                    val leftHandValue = rpnStack.pop()
                    val value = doOperation(
                        leftHandValue = leftHandValue.elements.value as Double,
                        rightHandValue = rightHandValue.elements.value as Double,
                        operation = token.elements.kind,
                    )
                    rpnStack.push(
                        InputToken(
                            elements = ValueModel(
                                value = value,
                                kind = ValueKind.NUMBER,
                            ),
                            containsDot = (value % 10.0 != 0.0)
                        )
                    )
                }

                else -> {
                    throw RpnError("Unexpected token")
                }
            }
        }
//        debugOutput(rpnStack)
        val result = rpnStack.pop()
        return result.elements.value as Double
    }
    private fun debugOutput(results: Stack<InputToken>) {

        val buffer = StringBuffer()
        for (result in results) {
            buffer.append(result.elements.value.toString())
        }
        Log.d("RPN", "  -> stack = " + buffer.toString());
    }
    private fun debugOutput(results: ArrayList<InputToken>) {

        val buffer = StringBuffer()
        for (result in results) {
            buffer.append(result.elements.value.toString())
        }
        Log.d("RPN", "----> buffer = " + buffer.toString());
    }

    private fun doOperation(leftHandValue: Double, rightHandValue: Double, operation: ValueKind) : Double {
        return when(operation) {
            ValueKind.ADD -> leftHandValue + rightHandValue

            ValueKind.SUBTRACT -> leftHandValue - rightHandValue

            ValueKind.DIVIDE -> {
                if (rightHandValue == 0.0) {
                    throw ZeroDivideError()
                }

                leftHandValue / rightHandValue
            }

            ValueKind.MULTIPLY -> leftHandValue * rightHandValue

            else -> {
                throw RpnError("Unexpected operation")
            }
        }
    }

    private fun toRPN(inputs: ArrayList<InputToken>): ArrayList<InputToken> {
        val result = ArrayList<InputToken>()
        val operators = Stack<InputToken>()

        for (token in inputs) {
            when (token.elements.kind) {
                ValueKind.NUMBER -> {
                    result.add(token)
                }

                ValueKind.ADD,
                ValueKind.SUBTRACT,
                ValueKind.DIVIDE,
                ValueKind.MULTIPLY -> {
                    if (!operators.isEmpty()) {
                        while (true) {
                            if ((!operators.isEmpty()) &&
                                (getPriority(operators.peek().elements) >= getPriority(token.elements))) {
                                result.add(operators.pop())
                            } else {
                                break
                            }
                        }
                    }
                    operators.push(token)
                }

                else -> {}
            }
        }

        while (!operators.isEmpty()) {
            result.add(operators.pop())
        }

        return result
    }

    private fun toTokens(inputs: ArrayList<ValueModel<*>>): ArrayList<InputToken> {
        val result = ArrayList<InputToken>()
        var token = 0.0

        for (input in inputs) {
            when (input.kind) {
                ValueKind.NUMBER -> {
                    val num = (input.value as Int).toDouble()
                    token = token * 10 + num
                }


                ValueKind.ADD,
                ValueKind.SUBTRACT,
                ValueKind.DIVIDE,
                ValueKind.MULTIPLY -> {
                    result.add(
                        InputToken(
                            elements = ValueModel(token, ValueKind.NUMBER),
                            containsDot = (token.mod(10.0) != 0.0),
                        )
                    )
                    token = 0.0
                    result.add(
                        InputToken(
                            elements = input,
                            containsDot = false,
                        )
                    )
                }

                else -> {}
            }
        }
        result.add(
            InputToken(
                elements = ValueModel(token, ValueKind.NUMBER),
                containsDot = (token.mod(10.0) != 0.0),
            )
        )
        return result
    }

    private fun getPriority(value: ValueModel<*>): Int {
        return when (value.kind) {
            ValueKind.MULTIPLY -> 2
            ValueKind.DIVIDE -> 2
            ValueKind.SUBTRACT -> 1
            ValueKind.ADD -> 1
            else -> {
                throw RpnError("Unexpected value")
            }
        }
    }


    private fun appendNumber(input: InputToken) {
//        if (input.value.kind != ValueKind.NUMBER) {
//            throw RpnError("Unexpected value")
//        }
//        val last = stack.peek()
//
//        // 計算式が0の場合で、0が入力された場合は追加しない
//        if ((ansNumber == 0) &&
//            (input.value.value == 0)) {
//            return
//        }
//        stack.push(input)
    }


}