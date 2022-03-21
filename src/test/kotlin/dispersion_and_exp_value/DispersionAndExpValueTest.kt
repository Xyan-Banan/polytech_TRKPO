package dispersion_and_exp_value

import org.junit.jupiter.api.Test
import java.lang.StringBuilder
import java.util.*
import kotlin.math.absoluteValue
import kotlin.math.pow
import kotlin.random.Random
import kotlin.test.assertEquals

internal class DispersionAndExpValueTest {


    @Test
    fun getWordsEmptyText() {
        val text = ""

        val words = getWords(text)

        assert(words.isEmpty())
    }

    @Test
    fun getWords() {
        val text = "a b c, ddddd-ee. \n qwerty \"Hello, world!\""

        val words = getWords(text)

        assertEquals(listOf("a", "b", "c", "ddddd-ee", "qwerty", "hello", "world"), words)
    }

    @Test
    fun expectedValueOneValue() {
        val text = StringBuilder().run {
            repeat(Random.nextInt(10)) {
                append("a")
            }
            toString()
        }

        val words = listOf(text)
        val probabilities = getProbabilities(words)

        assertEquals(text.length.toDouble(), expectedValue(probabilities))
    }

    @Test
    fun expectedValue_3_50() {
        val text = "aaa bbbb"

        val words = getWords(text)
        val probabilities = getProbabilities(words)

        assertEquals(3.5, expectedValue(probabilities))
    }
    @Test
    fun expectedValue_3_75() {
        val text = "aaa bbbb cccc"

        val words = getWords(text)
        val probabilities = getProbabilities(words)

        assertEquals(3 + 2/3.0, expectedValue(probabilities))
    }

    @Test
    fun probabilitiesHalf() {
        val text = "aaa bbbb"

        val words = getWords(text)
        val probabilities = getProbabilities(words)

        assertEquals(2, probabilities.size)
        probabilities.forEach {
            assertEquals(0.5, it.value)
        }
    }

    @Test
    fun probabilitiesOneThird() {
        val text = "aaa bbbb cccc"

        val words = getWords(text)
        val probabilities = getProbabilities(words)

        assertEquals(2, probabilities.size)
        assertEquals(1/3.0, probabilities[3])
    }


    @Test
    fun dispersionTest_0() {
        val text = "aaa"

        val words = getWords(text)
        val probabilities = getProbabilities(words)
        val dispersion = dispersion(probabilities)

        assertEquals(0.0, dispersion)
    }

    @Test
    fun dispersionTest_0_25() {
        val text = "aaa bbbb"

        val words = getWords(text)
        val probabilities = getProbabilities(words)
        val dispersion = dispersion(probabilities)

        assertEquals(0.25, dispersion)
    }

    @Test
    fun dispersionTest_0_22() {
        val text = "aaa bbbb cccc"

        val words = getWords(text)
        val probabilities = getProbabilities(words)
        val dispersion = dispersion(probabilities)


        assert((2/9.0 - dispersion).absoluteValue < 10.0.pow(-10))
    }

    @Test
    fun dispersionTest_0_1875() {
        val text = "aaa bbbb cccc dddd"

        val words = getWords(text)
        val probabilities = getProbabilities(words)
        val dispersion = dispersion(probabilities)


        assertEquals(0.1875, dispersion)
    }

    @Test
    fun dispersionTest_0_33() {
        val text = "aaa bbbb ccccc"

        val words = getWords(text)
        val probabilities = getProbabilities(words)
        val dispersion = dispersion(probabilities)


        assert((2/3.0 - dispersion).absoluteValue < 10.0.pow(-10))
    }
}