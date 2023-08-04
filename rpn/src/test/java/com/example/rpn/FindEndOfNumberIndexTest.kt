package com.example.rpn

import org.junit.Assert.assertEquals
import org.junit.Test

class FindEndOfNumberIndexTest {

    @Test
    fun `1_2`() {
        val result = findEndOfNumberIndex("1.2")
        assertEquals(3, result)
    }
    @Test(expected = SyntaxError::class)
    fun `1__2`() {
        findEndOfNumberIndex("1..2")
    }
    @Test
    fun `1_222+2_3`() {
        val result = findEndOfNumberIndex("1.222+2.3")
        assertEquals(5, result)
    }
}