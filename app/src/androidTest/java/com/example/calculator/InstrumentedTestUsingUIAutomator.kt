package com.example.calculator

import android.content.Context
import android.content.Intent
import androidx.test.core.app.ApplicationProvider
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SdkSuppress
import androidx.test.uiautomator.By
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.UiObject
import androidx.test.uiautomator.UiSelector
import androidx.test.uiautomator.Until
import com.example.calculator.models.NumpadButton
import com.example.calculator.ui.components.EQUATION_ROW_TEST_TAG
import com.example.calculator.ui.components.RESULT_ROW_TEST_TAG
import org.junit.After

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Before

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */

private const val LAUNCH_TIMEOUT = 5000L
private const val CALC_APP = "com.example.calculator"

@RunWith(AndroidJUnit4::class)
@SdkSuppress(minSdkVersion = 24)
class InstrumentedTestUsingUIAutomator {

    private lateinit var device: UiDevice
    private lateinit var uiSelector: UiSelector

    @Before
    fun startMainActivityFromHomeScreen() {

        // Initialize UiDevice instance
        device = UiDevice.getInstance(
            InstrumentationRegistry.getInstrumentation(),
        )

        // Launch the app
        val context = ApplicationProvider.getApplicationContext<Context>()
        val intent = context.packageManager.getLaunchIntentForPackage(
            CALC_APP,
        ).also {
            // Clear out any previous instances
            it?.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        }
        context.startActivity(intent)

//        assertTrue(device.hasObject(By.pkg(CALC_APP)))

        // Wait for the app to appear
        device.wait(
            Until.hasObject(By.pkg(CALC_APP).depth(0)),
            LAUNCH_TIMEOUT,
        )
        uiSelector = UiSelector()
    }

    @After
    fun clear() {
        findButton(NumpadButton.ALL_CLEAR).click()
    }

    private fun findButton(button: NumpadButton): UiObject {
        return device.findObject(uiSelector.resourceId(button.testTag))
    }

    private fun getText(resourceId: String): String {
        return device.findObject(uiSelector.resourceId(resourceId)).text
    }

    @Test
    fun test_2_plus_3() {
        // Enter an equation: 2 + 3 = ?
        findButton(NumpadButton.TWO).click()
        findButton(NumpadButton.ADD).click()
        findButton(NumpadButton.THREE).click()
        findButton(NumpadButton.EQUAL).click()

        // Verify the result
        val result = getText(RESULT_ROW_TEST_TAG)
        assertEquals("5", result)
        val equation = getText(EQUATION_ROW_TEST_TAG)
        assertEquals("2+3", equation)
    }

    @Test
    fun test_1_plus_2_by_3() {
        // Enter an equation: 1 + 2 * 3 = ?
        findButton(NumpadButton.ONE).click()
        findButton(NumpadButton.ADD).click()
        findButton(NumpadButton.TWO).click()
        findButton(NumpadButton.MULTIPLY).click()
        findButton(NumpadButton.THREE).click()
        findButton(NumpadButton.EQUAL).click()

        // Verify the result
        val result = getText(RESULT_ROW_TEST_TAG)
        assertEquals("7", result)
        val equation = getText(EQUATION_ROW_TEST_TAG)
        assertEquals("1+2*3", equation)
    }

}