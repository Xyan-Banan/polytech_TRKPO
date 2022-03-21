package average_in_array

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class AverageInArrayTest {
    lateinit var onesFlatArray: Array<Int>
    lateinit var zeroLenTwoDimArray: Array<Array<Int>>
    lateinit var oneElemTwoDimArray: Array<Array<Int>>
    lateinit var ones3by3TwoDimArray: Array<Array<Int>>

    @BeforeEach
    fun setUp() {
        zeroLenTwoDimArray = generateArray(0)
        oneElemTwoDimArray = generateArray(1)
        ones3by3TwoDimArray = arrayOf(
            arrayOf(1, 1, 1),
            arrayOf(1, 1, 1),
            arrayOf(1, 1, 1)
        )
        onesFlatArray = arrayOf(1, 1, 1)

    }


    @Test
    fun emptyArrayTest() {
        val array = flattenArray(zeroLenTwoDimArray)

        assert(array.isEmpty())
    }

    @Test
    fun oneValueArray() {
        val array = flattenArray(oneElemTwoDimArray)

        assert(array.size == 1)
    }

    @Test
    fun onesArrayFlattenTest() {
        val array = flattenArray(ones3by3TwoDimArray)

        assertEquals(9, array.size)
        assert(array.all { it == 1 })
    }

    @Test
    fun onesArrayAverageTest() {
        val average = average(onesFlatArray)

        assertEquals(1.0, average)
    }
}