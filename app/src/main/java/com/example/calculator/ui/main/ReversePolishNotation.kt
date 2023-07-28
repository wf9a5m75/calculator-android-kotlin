package com.example.calculator.ui.main

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import com.example.calculator.errors.RpnError
import com.example.calculator.errors.ZeroDivideError
import com.example.calculator.model.ValueKind
import com.example.calculator.model.ValueModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import java.util.Stack

data class InputToken(
    val value: ValueModel<*>,
    val containsDot: Boolean,
)


class ReversePolishNotation {

    private var inputs = ArrayList<ValueModel<*>>()

    val formula = mutableStateOf("")
    val answer = mutableStateOf("")
    private var ansNumber = 0.0

    fun isEmpty() = this.inputs.isEmpty()


    fun removeLast() {
        if (this.isEmpty()) {
            return
        }

        inputs.removeLast()
    }

    fun clear() {
        inputs.clear()
        update()
    }

    fun update() {
        if (inputs.isEmpty()) {
            formula.value = ""
            answer.value = "0"
            ansNumber = 0.0
            return
        }
        val buffer = StringBuffer()
        for (input in inputs) {
            buffer.append(input.value)
        }
        formula.value = buffer.toString()
        try {
            ansNumber = calculate()
            answer.value = ansNumber.toString()
        } catch (e: RpnError) {
            ansNumber = 0.0
            answer.value = "Error"
        } catch (e: ZeroDivideError) {
            ansNumber = 0.0
            answer.value = "ZeroDivideError"
        }
    }

    private fun calculate(): Double {
        val tokens = toTokens(inputs)
        val rpnTokens = toRPN(tokens)
//        Log.d("RPN", "=======================")
        debugOutput(rpnTokens)

        val rpnStack = Stack<InputToken>()
        while (!rpnTokens.isEmpty()) {
//            debugOutput(rpnStack)
            val token = rpnTokens.removeAt(0)
            when (token.value.kind) {
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
                        leftHandValue = leftHandValue.value.value as Double,
                        rightHandValue = rightHandValue.value.value as Double,
                        operation = token.value.kind,
                    )
                    rpnStack.push(
                        InputToken(
                            value = ValueModel(
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
        return result.value.value as Double
    }
    private fun debugOutput(results: Stack<InputToken>) {

        val buffer = StringBuffer()
        for (result in results) {
            buffer.append(result.value.value.toString())
        }
        Log.d("RPN", "  -> stack = " + buffer.toString());
    }
    private fun debugOutput(results: ArrayList<InputToken>) {

        val buffer = StringBuffer()
        for (result in results) {
            buffer.append(result.value.value.toString())
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
            when (token.value.kind) {
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
                                (getPriority(operators.peek().value) >= getPriority(token.value))) {
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
                            value = ValueModel(token, ValueKind.NUMBER),
                            containsDot = (token.mod(10.0) != 0.0),
                        )
                    )
                    token = 0.0
                    result.add(
                        InputToken(
                            value = input,
                            containsDot = false,
                        )
                    )
                }

                else -> {}
            }
        }
        result.add(
            InputToken(
                value = ValueModel(token, ValueKind.NUMBER),
                containsDot = (token.mod(10.0) != 0.0),
            )
        )
        return result
    }

    fun append(value: ValueModel<*>) {
        when (value.kind) {
            ValueKind.NUMBER -> {
                inputs.add(value)
            }

            ValueKind.ADD, ValueKind.SUBTRACT,
                ValueKind.DIVIDE, ValueKind.MULTIPLY -> {
                    if (isEmpty()) {
                        return
                    }
                    val last = inputs.last()
                    if (last.kind != ValueKind.NUMBER) {
                        inputs.removeLast()
                    }
                    inputs.add(value)
                }
            else -> {}
        }

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