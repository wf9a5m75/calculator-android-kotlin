package com.example.rpn

import org.junit.Assert.assertEquals
import org.junit.Test

class RpnNormalizeTest {
    @Test
    fun `1 + 2`() {
        val result = rpnNormalize("1 + 2")
        assertEquals("1+2", result)
    }
    @Test
    fun `2+    1_ + 2`() {
        val result = rpnNormalize("2+    1. + 2")
        assertEquals("2+1.+2", result)
    }
    @Test
    fun `+++++3---2`() {
        val result = rpnNormalize("+++++3---2")
        assertEquals("+3-2", result)
    }
    @Test
    fun `1+2*3`() {
        val result = rpnNormalize("1+2*3")
        assertEquals("1+2*3", result)
    }
    @Test
    fun `1+2_0÷3`() {
        val result = rpnNormalize("1+   2.0/3")
        assertEquals("1+2.0/3", result)
    }
    @Test
    fun `1_____3`() {
        val result = rpnNormalize("1......3")
        assertEquals("1.3", result)
    }
    @Test
    fun `1___3___2`() {
        val result = rpnNormalize("1...3...2")
        assertEquals("1.32", result)
    }
    @Test
    fun `1___3_*__2`() {
        val result = rpnNormalize("1...3.*..2")
        assertEquals("1.3*.2", result)
    }
    @Test
    fun `1  (  2`() {
        val result = rpnNormalize("1  (  2")
        assertEquals("1(2", result)
    }
    @Test
    fun `1  )  2`() {
        val result = rpnNormalize("1  )  2")
        assertEquals("1)2", result)
    }
    @Test
    fun `1  *÷  2`() {
        val result = rpnNormalize("1  */  2")
        assertEquals("1*2", result)
    }
    @Test
    fun `2_5 * (3 + 4) ÷ 2 - (1_5 + 2)`() {
        val result = rpnNormalize("2.5 * (3 + 4) / 2 - (1.5 + 2)")
        assertEquals("2.5*(3+4)/2-(1.5+2)", result)
    }
    @Test
    fun `2_32+1_2÷`() {
        val result = rpnNormalize("2.32+1.2/")
        assertEquals("2.32+1.2", result)
    }
    @Test
    fun `2_32+1_2÷+-`() {
        val result = rpnNormalize("2.32+1.2/+-")
        assertEquals("2.32+1.2", result)
    }
}