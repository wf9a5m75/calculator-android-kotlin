package com.example.calculator.ui

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.example.calculator.MainActivity
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class NumberButtonKtTest {

    @get:Rule
    val composeRule = createComposeRule()

    @Test
    fun should_pass_tag_value_to_onClick() {
        composeRule.apply {
            setContent {
                NumberButton(
                    label = "click here",
                    tag = "test value",
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