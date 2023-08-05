package com.example.calculator

import androidx.compose.ui.test.SemanticsNodeInteraction
import androidx.compose.ui.test.assertTextContains
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.filters.SdkSuppress
import com.example.calculator.models.NumpadButton
import com.example.calculator.ui.components.EQUATION_ROW_TEST_TAG
import com.example.calculator.ui.components.RESULT_ROW_TEST_TAG
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@SdkSuppress(minSdkVersion = 24)
@LargeTest
class InstrumentedTestUsingEspresso {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    private fun findButton(numberButton: NumpadButton): SemanticsNodeInteraction {
        return findElement(numberButton.testTag)
    }
    private fun findElement(testTag: String): SemanticsNodeInteraction {
        return composeTestRule.onNodeWithTag(testTag)
    }
    @Test
    fun test_1_divide_3() {
        // Enter an equation: 1 / 3 = ?
        findButton(NumpadButton.ONE).performClick()
        findButton(NumpadButton.DIVIDE).performClick()
        findButton(NumpadButton.THREE).performClick()
        findButton(NumpadButton.EQUAL).performClick()

        // Verify the result = 0.3333333333333
        findElement(EQUATION_ROW_TEST_TAG).assertTextEquals("1/3")
        findElement(RESULT_ROW_TEST_TAG).assertTextContains("0.333333", substring = true)
    }

    @Test
    fun test_2_plus_3() {
        // Enter an equation: 2 + 3 = ?
        findButton(NumpadButton.TWO).performClick()
        findButton(NumpadButton.ADD).performClick()
        findButton(NumpadButton.THREE).performClick()
        findButton(NumpadButton.EQUAL).performClick()

        // Verify the result = 5
        findElement(EQUATION_ROW_TEST_TAG).assertTextEquals("2+3")
        findElement(RESULT_ROW_TEST_TAG).assertTextContains("5")
    }

    @Test
    fun test_1_5_minus_0_75() {
        // Enter an equation: 1.5 - 0.75
        findButton(NumpadButton.ONE).performClick()
        findButton(NumpadButton.DOT).performClick()
        findButton(NumpadButton.FIVE).performClick()
        findButton(NumpadButton.SUBTRACT).performClick()
        findButton(NumpadButton.DOT).performClick()
        findButton(NumpadButton.SEVEN).performClick()
        findButton(NumpadButton.FIVE).performClick()
        findButton(NumpadButton.EQUAL).performClick()

        // Verify the result = 0.75
        findElement(EQUATION_ROW_TEST_TAG).assertTextEquals("1.5-.75")
        findElement(RESULT_ROW_TEST_TAG).assertTextContains("0.75")
    }
}