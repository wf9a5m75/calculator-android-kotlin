package com.example.calculator.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.platform.ComposeView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.calculator.ui.theme.CalculatorTheme
import kotlinx.coroutines.launch

class MainFragment: Fragment() {

    val viewModel by activityViewModels<MainScreenViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View = ComposeView(requireContext())

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view as ComposeView
        view.setContent {
            CalculatorTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    MainScreen(
                        viewModel,
                    )
                }
            }
        }

//        viewLifecycleOwner.lifecycleScope.launch {
//            repeatOnLifecycle(Lifecycle.State.STARTED) {
//                launch {
//                    viewModel.formulaEvent.collect {
//                        when (it) {
//                            is MainScreenViewModel.FormulaEvent.Append -> {
//
//
//                            }
//                        }
//                    }
//                }
//            }
//        }
    }
}