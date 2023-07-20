package com.example.calculator.ui

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.example.calculator.ui.components.BaseButton
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class BaseButtonKtTest {

    @get:Rule
    val composeRule = createComposeRule()

    @Test
    fun should_pass_tag_value_to_onClick() {
        composeRule.apply {
            setContent {
                BaseButton<String>(
                    label = "click here",
                    value = "test value",
                ) { returnValue ->
                    assertEquals(returnValue, "test value")
                }
            }
        }
        composeRule.onNodeWithText("click here")
            .assertIsDisplayed()
            .performClick()
    }
}