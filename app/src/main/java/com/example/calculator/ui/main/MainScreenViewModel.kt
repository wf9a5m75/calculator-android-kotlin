package com.example.calculator.ui.main

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.calculator.errors.CalculatorError
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface IMainScreenViewModel {
    var dispatcher: CoroutineDispatcher;
    val expression: MutableStateFlow<String>
    val result: StateFlow<String>

    fun calculate()
    fun clear()
}

@HiltViewModel
class MainScreenViewModel @Inject constructor() : ViewModel(), IMainScreenViewModel {
    companion object {
        const val TAG = "MainScreen"
    }

    override var dispatcher = Dispatchers.IO

    override val expression = MutableStateFlow("")

    private val internalResult = MutableStateFlow<String>("")
    override val result: StateFlow<String>
        get() = internalResult

    override fun calculate() {
        viewModelScope.launch {
            processCalculation(
                expression = expression.value,
            )
        }
    }
    private suspend fun processCalculation(
        expression: String,
    ) = withContext(dispatcher) {
        try {
            val result = ReversePolishNotation.calculate(
                expression = expression
            )
            if (result % 1 == 0.0) {
                internalResult.value = result.toInt().toString()
            } else {
                internalResult.value = result.toString()
            }
        } catch (e: CalculatorError) {
            internalResult.value = e.toString()
        }
    }

    override fun clear() {
        Log.d(TAG, "---->clear!")
        expression.value = ""
        internalResult.value = ""
    }

}