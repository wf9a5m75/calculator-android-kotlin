package com.example.calculator.ui.main

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
    val equation: MutableStateFlow<String>
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

    override val equation = MutableStateFlow("")

    private val internalResult = MutableStateFlow<String>("")
    override val result: StateFlow<String>
        get() = internalResult

    override fun calculate() {
        viewModelScope.launch {
            processCalculation(
                equation = equation.value,
            )
        }
    }
    private suspend fun processCalculation(
        equation: String,
    ) = withContext(dispatcher) {
        try {
            val result = com.example.rpn.rpnCalculate(
                equation = equation,
            )
            internalResult.value = result
        } catch (e: com.example.rpn.CalculatorError) {
            internalResult.value = e.toString()
        }
    }

    override fun clear() {
        Log.d(TAG, "---->clear!")
        equation.value = ""
        internalResult.value = ""
    }

}