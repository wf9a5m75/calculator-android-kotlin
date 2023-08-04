package com.example.rpn

import org.junit.Assert.*
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class FindEndOfSubEquationIndexTest {
    @Test
    fun simpleCase1() {
        val expression = "(1+2)"
        val endIdx = findEndOfSubEquationIndex(expression)
        val result = expression.substring(0, endIdx)
        assertEquals("(1+2)",result)
    }

    @Test
    fun simpleCase2() {
        val expression = "((1+2))"
        val endIdx = findEndOfSubEquationIndex(expression)
        val result = expression.substring(0, endIdx)
        assertEquals("((1+2))", result)
    }

    @Test
    fun simpleCase3() {
        val expression = "(1+2)(2+3)"
        val endIdx = findEndOfSubEquationIndex(expression)
        val result = expression.substring(0, endIdx)
        assertEquals("(1+2)", result)
    }

    @Test
    fun simpleCase4() {
        val expression = "((1+2)(2+3))"
        val endIdx = findEndOfSubEquationIndex(expression)
        val result = expression.substring(0, endIdx)
        assertEquals("((1+2)(2+3))", result)
    }


    @Test
    fun incompleteExpression1() {
        val expression = "(1+2"
        val endIdx = findEndOfSubEquationIndex(expression)
        val result = expression.substring(0, endIdx)
        assertEquals("(1+2", result)
    }
}