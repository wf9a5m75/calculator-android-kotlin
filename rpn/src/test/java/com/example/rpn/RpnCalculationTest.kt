package com.example.rpn

import org.junit.Assert.assertEquals
import org.junit.Test

class RpnCalculationTest {
    @Test
    fun `2 + 3`() {
        val result = rpnCalculate("2 + 3")
        assertEquals("5", result)
    }
    @Test
    fun `1_5 - 0_75`() {
        val result = rpnCalculate("1.5 - 0.75")
        assertEquals("0.75", result)
    }
    @Test
    fun `4*2_5`() {
        val result = rpnCalculate("4*2.5")
        assertEquals("10", result)
    }
    @Test
    fun `(2+3)*4`() {
        val result = rpnCalculate("(2+3)*4")
        assertEquals("20", result)
    }
    @Test
    fun `10+2*3-4÷2`() {
        val result = rpnCalculate("10+2*3-4/2")
        assertEquals("14", result)
    }
    @Test
    fun `5÷0`() {
        val result = rpnCalculate("5/0")
        assertEquals("Can't divide by zero", result)
    }
    @Test
    fun `123456789 * 987654321`() {
        val result = rpnCalculate("123456789 * 987654321")
        assertEquals("121932631112635269", result)
    }
    @Test
    fun `(2_5 + 3_5) * 2 - 1_5`() {
        val result = rpnCalculate("(2.5 + 3.5) * 2 - 1.5")
        assertEquals("10.5", result)
    }

    // TODO: エラーにするか、5*-2とみなすか、何か考える
//    @Test
//    fun `5*+-2`() {
//        val result = rpnCalculate("5*+-2")
//        assertEquals("7", result)
//    }

    @Test
    fun `((4 + 2) * 3) ÷ (8 - 2)`() {
        val result = rpnCalculate("((4 + 2) * 3) / (8 - 2)")
        assertEquals("3", result)
    }
    @Test
    fun `2_5 * (3 + 4) ÷ 2 - (1_5 + 2)`() {
        val result = rpnCalculate("2.5 * (3 + 4) / 2 - (1.5 + 2)")
        assertEquals("5.25", result)
    }
    @Test
    fun `1_0÷3_0`() {
        val result = rpnCalculate("1.0/3.0")
        assertEquals("0.3333333333333", result)
    }
    @Test
    fun `-5 + (-2) * (-3)`() {
        val result = rpnCalculate("-5 + (-2) * (-3)")
        assertEquals("1", result)
    }
}