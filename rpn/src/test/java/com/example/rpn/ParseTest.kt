package com.example.rpn

import org.junit.Test
import java.math.BigDecimal


/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */

class ParseTest {

    @Test
    fun `1+2`() {
        val expression = "1+2"
        val tokens = parseRpnToken(expression)
        val expect = listOf(
            NumberToken(
                value = BigDecimal.valueOf(1),
            ),
            OperatorToken(
                value = com.example.rpn.Operator.ADD,
            ),
            NumberToken(
                value = BigDecimal.valueOf(2),
            ),
        )

        assert(expect.contentEquals(tokens))
    }

    @Test
    fun `1+2*3`() {
        val expression = "1+2*3"
        val tokens = parseRpnToken(expression)
        val expect = listOf(
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

        assert(expect.contentEquals(tokens))
    }

    @Test
    fun `(1+2)`() {
        val expression = "(1+2)"
        val tokens = parseRpnToken(expression)
        val expect = listOf(
            NumberToken(
                value = BigDecimal.valueOf(3),
            ),
        )

        assert(expect.contentEquals(tokens))
    }

    @Test
    fun `2*(1+2)`() {
        val expression = "2*(1+2)"
        val tokens = parseRpnToken(expression)
        val expect = listOf(
            NumberToken(
                value = BigDecimal.valueOf(2),
                containsDot = false,
            ),
            OperatorToken(
                value = com.example.rpn.Operator.MULTIPLY,
            ),
            NumberToken(
                value = BigDecimal.valueOf(3),
            ),
        )

        assert(expect.contentEquals(tokens))
    }
    @Test
    fun `2(1+2)`() {
        val expression = "2(1+2)"
        val tokens = parseRpnToken(expression)
        val expect = listOf(
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

        assert(expect.contentEquals(tokens))
    }
    @Test
    fun `2(1+2)2`() {
        val expression = "2(1+2)2"
        val tokens = parseRpnToken(expression)
        val expect = listOf(
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

        assert(expect.contentEquals(tokens))
    }
    @Test
    fun `(1+2)-2`() {
        val expression = "(1+2)-2"
        val tokens = parseRpnToken(expression)
        val expect = listOf(
            NumberToken(
                value = BigDecimal.valueOf(3),
            ),
            OperatorToken(
                value = com.example.rpn.Operator.ADD,
            ),
            NumberToken(
                value = BigDecimal.valueOf(-2),
            ),
        )

        assert(expect.contentEquals(tokens))
    }
    @Test
    fun `2(3)-4`() {
        val expression = "2(3)-4"
        val tokens = parseRpnToken(expression)
        val expect = listOf(
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

        assert(expect.contentEquals(tokens))

    }

    @Test
    fun `2*(3)*-4`() {
        val expression = "2*(3)*-4"
        val tokens = parseRpnToken(expression)
        val expect = listOf(
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
                value = BigDecimal.valueOf(-4),
            ),
        )

        assert(expect.contentEquals(tokens))

    }


    @Test
    fun `(-3)`() {
        val expression = "(-3)"
        val tokens = parseRpnToken(expression)
        val expect = listOf(
            NumberToken(
                value = BigDecimal.valueOf(-3),
            ),
        )

        assert(expect.contentEquals(tokens))
    }

    @Test
    fun `((-2*-3)+6)÷12`() {
        val expression = "((-2*-3)+6)/12"
        val tokens = parseRpnToken(expression)
        val expect = listOf(
            NumberToken(
                value = BigDecimal.valueOf(12),
            ),
            OperatorToken(
                value = Operator.DIVIDE
            ),
            NumberToken(
                value = BigDecimal.valueOf(12),
            ),
        )

        assert(expect.contentEquals(tokens))
    }
}