package com.example.calculator

import androidx.compose.ui.test.assertTextContains
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.filters.SdkSuppress
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

    @Test
    fun oneDivideByThree() {
        // Enter an equation: 1 / 3 = ?
        composeTestRule.onNodeWithTag("one").performClick()
        composeTestRule.onNodeWithTag("divides").performClick()
        composeTestRule.onNodeWithTag("three").performClick()
        composeTestRule.onNodeWithTag("equals").performClick()

        // Verify the result = 0.3333333333333
        composeTestRule.onNodeWithTag(EQUATION_ROW_TEST_TAG).assertTextEquals("1/3")
        composeTestRule.onNodeWithTag(RESULT_ROW_TEST_TAG)
            .assertTextContains("0.333333", substring = true)
    }
}