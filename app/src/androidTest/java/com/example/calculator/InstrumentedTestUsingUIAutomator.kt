package com.example.calculator

import android.content.Context
import android.content.Intent
import androidx.test.core.app.ApplicationProvider
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SdkSuppress
import androidx.test.uiautomator.By
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.UiSelector
import androidx.test.uiautomator.Until
import com.example.calculator.ui.components.EXPRESSION_TEST_TAG
import com.example.calculator.ui.components.RESULT_TEST_TAG

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
    }

    @Test
    fun testTwoPlusThreeEqualsFive() {
        // Enter an equation: 2 + 3 = ?
        val uiSelector = UiSelector()
        device.findObject(uiSelector.resourceId("two")).click()
        device.findObject(uiSelector.resourceId("adds")).click()
        device.findObject(uiSelector.resourceId("three")).click()
//        device.findObject(uiSelector.resourceId("equals")).click()

        // Verify the result = 5
        val result = device.findObject(uiSelector.resourceId("answer_display"))
        assertEquals("5.0", result.text)
    }

    @Test
    fun testOnePlusTwoByThree() {
        // Enter an equation: 1 + 2 * 3 = ?
        val uiSelector = UiSelector()
        device.findObject(uiSelector.resourceId("one")).click()
        device.findObject(uiSelector.resourceId("adds")).click()
        device.findObject(uiSelector.resourceId("two")).click()
        device.findObject(uiSelector.resourceId("multiplies")).click()
        device.findObject(uiSelector.resourceId("three")).click()
//        device.findObject(uiSelector.resourceId("equals")).click()

        // Verify the result = 7.0
        val formulaRow = device.findObject(uiSelector.resourceId(EXPRESSION_TEST_TAG))
        assertEquals("1+2×3", formulaRow.text)

        val answerRow = device.findObject(uiSelector.resourceId(RESULT_TEST_TAG))
        assertEquals("7.0", answerRow.text)
    }

}