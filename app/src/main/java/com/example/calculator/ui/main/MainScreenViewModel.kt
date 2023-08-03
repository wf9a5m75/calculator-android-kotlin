package com.example.calculator.ui.main

import android.app.Activity
import android.content.Context
import android.util.Log
import android.view.KeyEvent
import android.view.inputmethod.BaseInputConnection
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.calculator.errors.MainScreenError
import com.example.calculator.model.ValueKind
import com.example.calculator.model.ValueModel
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

    override var dispatcher: CoroutineDispatcher = Dispatchers.IO
    private val rpn = ReversePolishNotation()

    override val expression: MutableStateFlow<String> = MutableStateFlow("")
    override val result: StateFlow<String>
        get() = rpn.result

    override fun calculate() {
        viewModelScope.launch {
            Log.d(TAG, "---->calculate!")

        }
    }

    override fun clear() {
        Log.d(TAG, "---->clear!")
    }

}