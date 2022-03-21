package average_in_array

import kotlin.random.Random

fun generateArray(N: Int) = Array(N) { Array(N) { Random.nextInt(10, 100) } }

inline fun <reified T> flattenArray(array: Array<Array<T>>) = array.flatten().toTypedArray()
inline fun <reified T> flattenArray2(array: Array<Array<T>>) = array.flatMap { it.asIterable() }.toTypedArray()
inline fun <reified T> flattenArray3(array: Array<Array<T>>): Array<T> {
    val N = array.size
    return Array<T>(N * N) { array[it / N][it % N] }
}

fun <T : Int> average(array: Array<T>) = array.average()
fun <T : Int> average2(array: Array<T>) = array.sum().toDouble() / array.size
fun <T : Int> average3(array: Array<T>) = array.fold(0.0) { acc, i -> acc + i } / array.size
fun <T : Int> average4(array: Array<T>): Double {
    var sum = 0.0
    array.forEach {
        sum += it
    }
    return sum / array.size
}

fun main(args: Array<String>) {
    if (args.isEmpty()) return

    val N = args[0].toIntOrNull() ?: return

    val twoDimArray = generateArray(N)
    println(twoDimArray.joinToString("\n") { it.joinToString() })
    val array = flattenArray3(twoDimArray)
    println(array.joinToString())
    val average = average(array)
    println(average)
}