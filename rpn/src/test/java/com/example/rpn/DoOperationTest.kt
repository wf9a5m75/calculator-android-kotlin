package com.example.rpn

import org.junit.Assert.assertEquals
import org.junit.Test
import java.math.BigDecimal

class DoOperationTest {
    @Test
    fun `3+4`() {
        val leftHandValue = NumberToken(
            value = BigDecimal.valueOf(3),
        )
        val rightHandValue = NumberToken(
            value = BigDecimal.valueOf(4),
        )
        val operator = OperatorToken(
            value = Operator.ADD,
        )

        val result = doOperation(
            leftHandValue = leftHandValue,
            rightHandValue = rightHandValue,
            operator = operator,
        )
        assertEquals("7", result.value.toPlainString())
    }
    @Test
    fun `1_0+2_0`() {
        val leftHandValue = NumberToken(
            value = BigDecimal.valueOf(1.0),
        )
        val rightHandValue = NumberToken(
            value = BigDecimal.valueOf(2.0),
        )
        val operator = OperatorToken(
            value = Operator.ADD,
        )

        val result = doOperation(
            leftHandValue = leftHandValue,
            rightHandValue = rightHandValue,
            operator = operator,
        )
        assertEquals("3.0", result.value.toPlainString())
    }
    @Test
    fun `2181_2+2111_2`() {
        val leftHandValue = NumberToken(
            value = BigDecimal.valueOf(2182.1),
        )
        val rightHandValue = NumberToken(
            value = BigDecimal.valueOf(211.2),
        )
        val operator = OperatorToken(
            value = Operator.ADD,
        )

        val result = doOperation(
            leftHandValue = leftHandValue,
            rightHandValue = rightHandValue,
            operator = operator,
        )
        assertEquals("2393.3", result.value.toPlainString())
    }
    @Test
    fun `0_5-1_3`() {
        val leftHandValue = NumberToken(
            value = BigDecimal.valueOf(0.5),
        )
        val rightHandValue = NumberToken(
            value = BigDecimal.valueOf(-1.3),
        )
        val operator = OperatorToken(
            value = Operator.ADD,
        )

        val result = doOperation(
            leftHandValue = leftHandValue,
            rightHandValue = rightHandValue,
            operator = operator,
        )
        assertEquals("-0.8", result.value.toPlainString())
    }
    @Test
    fun `1_37 ÷ 2_45`() {
        val leftHandValue = NumberToken(
            value = BigDecimal.valueOf(1.37),
        )
        val rightHandValue = NumberToken(
            value = BigDecimal.valueOf(2.45),
        )
        val operator = OperatorToken(
            value = Operator.DIVIDE,
        )

        val result = doOperation(
            leftHandValue = leftHandValue,
            rightHandValue = rightHandValue,
            operator = operator,
        )
        assertEquals("0.5591836734694", result.value.toPlainString())
    }
    @Test(expected = ZeroDivideError::class)
    fun `1 ÷ 0`() {
        val leftHandValue = NumberToken(
            value = BigDecimal.valueOf(1),
        )
        val rightHandValue = NumberToken(
            value = BigDecimal.ZERO
        )
        val operator = OperatorToken(
            value = Operator.DIVIDE,
        )

        doOperation(
            leftHandValue = leftHandValue,
            rightHandValue = rightHandValue,
            operator = operator,
        )
    }
    @Test
    fun `2_3 * 0_222`() {
        val leftHandValue = NumberToken(
            value = BigDecimal.valueOf(2.3),
        )
        val rightHandValue = NumberToken(
            value = BigDecimal.valueOf(0.222)
        )
        val operator = OperatorToken(
            value = Operator.MULTIPLY,
        )
        val result = doOperation(
            leftHandValue = leftHandValue,
            rightHandValue = rightHandValue,
            operator = operator,
        )
        assertEquals("0.5106", result.value.toPlainString())
    }
    @Test
    fun `0 ÷ 0_222`() {
        val leftHandValue = NumberToken(
            value = BigDecimal.valueOf(0),
        )
        val rightHandValue = NumberToken(
            value = BigDecimal.valueOf(0.222)
        )
        val operator = OperatorToken(
            value = Operator.DIVIDE,
        )
        val result = doOperation(
            leftHandValue = leftHandValue,
            rightHandValue = rightHandValue,
            operator = operator,
        )
        assertEquals("0.0000000000000", result.value.toPlainString())
    }
}