package com.example.rpn

import org.junit.Test
import java.math.BigDecimal


/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */

class ToRpnTest {

    @Test
    fun `1+2`() {
        // An equation : 1 + 2 = ?
        val tokens = listOf(
            NumberToken(
                value = BigDecimal.valueOf(1),
            ),
            OperatorToken(
                value = com.example.rpn.Operator.ADD,
            ),
            NumberToken(
                value = BigDecimal.valueOf(2),
            )
        )
        val rpnExpression = toRPN(tokens)

        val expect = listOf(
            NumberToken(
                value = BigDecimal.valueOf(1),
            ),
            NumberToken(
                value = BigDecimal.valueOf(2),
            ),
            OperatorToken(
                value = com.example.rpn.Operator.ADD,
            ),
        )

        assert(expect.contentEquals(rpnExpression))
    }

    @Test
    fun `1+2*3`() {
        // An equation: 1+2*3
        val tokens = listOf(
            NumberToken(
                value = BigDecimal.valueOf(1),
            ),
            OperatorToken(
                value = com.example.rpn.Operator.ADD,
            ),
            NumberToken(
                value = BigDecimal.valueOf(2),
            ),
            OperatorToken(
                value = com.example.rpn.Operator.MULTIPLY,
            ),
            NumberToken(
                value = BigDecimal.valueOf(3),
            )
        )
        val rpnExpression = toRPN(tokens)
        val expect = listOf(
            NumberToken(
                value = BigDecimal.valueOf(1),
            ),
            NumberToken(
                value = BigDecimal.valueOf(2),
            ),
            NumberToken(
                value = BigDecimal.valueOf(3),
            ),
            OperatorToken(
                value = com.example.rpn.Operator.MULTIPLY,
            ),
            OperatorToken(
                value = com.example.rpn.Operator.ADD,
            ),
        )

        assert(expect.contentEquals(rpnExpression))
    }

    @Test
    fun `(1+2)`() {
        // An equation: (1+2)
        val tokens = listOf(
            NumberToken(
                value = BigDecimal.valueOf(3),
            ),
        )
        val rpnExpression = toRPN(tokens)
        val expect = listOf(
            NumberToken(
                value = BigDecimal.valueOf(3),
            ),
        )

        assert(expect.contentEquals(rpnExpression))
    }

    @Test
    fun `2*(1+2)`() {
        // An equation: 2*(1+2)
        val token = listOf(
            NumberToken(
                value = BigDecimal.valueOf(2),
            ),
            OperatorToken(
                value = com.example.rpn.Operator.MULTIPLY,
            ),
            NumberToken(
                value = BigDecimal.valueOf(3),
            ),
        )
        val rpnExpression = toRPN(token)
        val expect = listOf(
            NumberToken(
                value = BigDecimal.valueOf(2),
            ),
            NumberToken(
                value = BigDecimal.valueOf(3),
            ),
            OperatorToken(
                value = com.example.rpn.Operator.MULTIPLY,
            ),
        )

        assert(expect.contentEquals(rpnExpression))
    }

    @Test
    fun `2(1+2)2`() {
        // An equation: 2(1+2)2
        val tokens = listOf(
            NumberToken(
                value = BigDecimal.valueOf(2),
            ),
            OperatorToken(
                value = com.example.rpn.Operator.MULTIPLY,
            ),
            NumberToken(
                value = BigDecimal.valueOf(3),
            ),
            OperatorToken(
                value = com.example.rpn.Operator.MULTIPLY,
            ),
            NumberToken(
                value = BigDecimal.valueOf(2),
            ),
        )
        val rpnExpression = toRPN(tokens)
        val expect = listOf(
            NumberToken(
                value = BigDecimal.valueOf(2),
            ),
            NumberToken(
                value = BigDecimal.valueOf(3),
            ),
            OperatorToken(
                value = com.example.rpn.Operator.MULTIPLY,
            ),
            NumberToken(
                value = BigDecimal.valueOf(2),
            ),
            OperatorToken(
                value = com.example.rpn.Operator.MULTIPLY,
            ),
        )

        assert(expect.contentEquals(rpnExpression))
    }

    @Test
    fun `2(3)-4`() {
        // An equation: 2(3)-4
        val tokens = listOf(
            NumberToken(
                value = BigDecimal.valueOf(2),
            ),
            OperatorToken(
                value = com.example.rpn.Operator.MULTIPLY,
            ),
            NumberToken(
                value = BigDecimal.valueOf(3),
            ),
            OperatorToken(
                value = com.example.rpn.Operator.ADD,
            ),
            NumberToken(
                value = BigDecimal.valueOf(-4),
            ),
        )
        val rpnExpression = toRPN(tokens)
        val expect = listOf(
            NumberToken(
                value = BigDecimal.valueOf(2),
            ),
            NumberToken(
                value = BigDecimal.valueOf(3),
            ),
            OperatorToken(
                value = com.example.rpn.Operator.MULTIPLY,
            ),
            NumberToken(
                value = BigDecimal.valueOf(-4),
            ),
            OperatorToken(
                value = com.example.rpn.Operator.ADD,
            ),
        )

        assert(expect.contentEquals(rpnExpression))
    }
}