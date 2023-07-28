package com.example.calculator.ui.main

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.calculator.errors.CalculatorError
import com.example.calculator.errors.MainScreenError
import com.example.calculator.model.ValueModel
import com.example.calculator.model.ValueKind
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.Stack
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor() : ViewModel() {
    companion object {
        const val TAG = "MainScreen"
    }

    var dispatcher: CoroutineDispatcher = Dispatchers.IO
    private val rpn = ReversePolishNotation()

    val formula: State<String>
        get() = rpn.formula

    val answer: State<String>
        get() = rpn.answer

    /**
     * 数字キーが押された
     */
    fun onNumberButtonClick(button: ValueModel<Int>) {
        viewModelScope.launch {
            internalOnNumberButtonClick(button)
        }
    }

    private suspend fun internalOnNumberButtonClick(button: ValueModel<Int>) = withContext(dispatcher) {
        if (button.kind != ValueKind.NUMBER) {
            throw MainScreenError("Invalid input value")
        }

        rpn.append(button)
        rpn.update()
    }

    fun onOperatorButtonClick(button: ValueModel<String>) {
        viewModelScope.launch {
            internalOnOperatorButtonClick(button)
        }
    }

    private suspend fun internalOnOperatorButtonClick(button: ValueModel<String>) = withContext(dispatcher) {
        rpn.append(button)
        rpn.update()
    }


    fun onSpecialButtonClick(button: ValueModel<String>) {
        viewModelScope.launch {
            internalOnSpecialButtonClick(button)
        }
    }

    private suspend fun internalOnSpecialButtonClick(button: ValueModel<String>) = withContext(dispatcher) {

        when(button.kind) {
            ValueKind.DELETE -> {
                if (rpn.isEmpty()) {
                    rpn.clear()
                    return@withContext
                }
                rpn.removeLast()
                rpn.update()
            }

            else -> {}
        }
    }

    fun onActionButtonClick(button: ValueModel<String>) {
        viewModelScope.launch {
            internalOnActionButtonClick(button)
        }
    }
    private suspend fun internalOnActionButtonClick(button: ValueModel<String>) = withContext(dispatcher) {
        when(button.kind) {
            ValueKind.ALL_CLEAR -> {
                rpn.clear()
            }

            ValueKind.DO_CALCULATE -> {

                // 現在の答えを次の式の初期値とする
            }

            else -> {
                throw MainScreenError("Unexpected value")
            }
        }
    }

    private fun updateCalculate(): Int {
        return 0
    }

}