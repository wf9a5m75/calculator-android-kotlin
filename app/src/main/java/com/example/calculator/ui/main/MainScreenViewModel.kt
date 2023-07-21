package com.example.calculator.ui.main

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.calculator.errors.CalculatorError
import com.example.calculator.errors.MainScreenError
import com.example.calculator.model.ButtonValue
import com.example.calculator.model.ValueKind
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor() : ViewModel() {
    companion object {
        const val TAG = "MainScreen"
    }
    var answerState = mutableStateOf(0)

    fun onNumberButtonClick(button: ButtonValue<Int>)  {
        if (button.kind != ValueKind.NUMBER) {
            throw MainScreenError("Invalid input value")
        }
        Log.d(TAG, "---->num = ${button.value}")
    }
    fun onOperatorButtonClick(button: ButtonValue<String>) {

    }
    fun onSpecialButtonClick(button: ButtonValue<String>) {

    }
    fun onActionButtonClick(button: ButtonValue<String>) {

    }
}