package com.example.calculator.ui.main

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.calculator.errors.MainScreenError
import com.example.calculator.errors.RpnError
import com.example.calculator.model.ValueKind
import com.example.calculator.model.ValueModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor() : ViewModel() {
    companion object {
        const val TAG = "MainScreen"
    }

    var dispatcher: CoroutineDispatcher = Dispatchers.IO
    private val rpn = ReversePolishNotation()

    private var inputs = ArrayList<ValueModel<*>>()
    val formula = mutableStateOf("")

    fun isEmpty() = this.inputs.isEmpty()

    private var ansNumber = 0.0

    private val internalAnswer = mutableStateOf("")

    val answer: State<String>
        get() = internalAnswer

    /**
     * 数字キーが押された
     */
    fun onNumberButtonClick(button: ValueModel<Int>) {
        viewModelScope.launch {
            internalOnNumberButtonClick(button)
        }
    }

    private suspend fun internalOnNumberButtonClick(button: ValueModel<Int>) =
        withContext(dispatcher) {
            if (button.kind != ValueKind.NUMBER) {
                throw MainScreenError("Invalid input value")
            }

            append(button)
            rpn.update()
        }

    private fun append(value: ValueModel<*>) {
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
                    removeLast()
                }
                inputs.add(value)
            }
            else -> {}
        }

    }

    fun onOperatorButtonClick(button: ValueModel<String>) {
        viewModelScope.launch {
            internalOnOperatorButtonClick(button)
        }
    }

    private suspend fun internalOnOperatorButtonClick(button: ValueModel<String>) =
        withContext(dispatcher) {
            append(button)
            rpn.update()
        }

    fun onSpecialButtonClick(button: ValueModel<String>) {
        viewModelScope.launch {
            internalOnSpecialButtonClick(button)
        }
    }

    private suspend fun internalOnSpecialButtonClick(button: ValueModel<String>) =
        withContext(dispatcher) {

            when (button.kind) {
                ValueKind.DELETE -> {
                    if (isEmpty()) {
                        clear()
                        return@withContext
                    }
                    removeLast()
                    updateCalculate()
                }

                else -> {}
            }
        }
    private fun removeLast() {
        if (this.isEmpty()) {
            return
        }
        inputs.removeLast()
        updateCalculate()
    }
    
    private fun clear() {
        inputs.clear()
        updateCalculate()
    }

    fun onActionButtonClick(button: ValueModel<String>) {
        viewModelScope.launch {
            internalOnActionButtonClick(button)
        }
    }

    private suspend fun internalOnActionButtonClick(button: ValueModel<String>) =
        withContext(dispatcher) {
            when (button.kind) {
                ValueKind.ALL_CLEAR -> clear()

                ValueKind.DO_CALCULATE -> {

                    // 現在の答えを次の式の初期値とする
                }

                else -> {
                    throw MainScreenError("Unexpected value")
                }
            }
        }

    private fun updateCalculate() {
        if (inputs.isEmpty()) {
            formula.value = ""
            internalAnswer.value = "0"
            ansNumber = 0.0
            return
        }
        val buffer = StringBuffer()
        for (input in inputs) {
            buffer.append(input.value)
        }
        formula.value = buffer.toString()

        try {
            ansNumber = rpn.calculate(buffer.toString())
            internalAnswer.value = ansNumber.toString()
        } catch (e: RpnError) {
            ansNumber = 0.0
            internalAnswer.value = "Error"
        }
    }

}