package dispersion_and_exp_value

import kotlin.math.pow

fun getWords(text: String): List<String> {
    return text
        .split(Regex("[ \n.]+"))
        .asSequence()
        .filter { it.isNotBlank() }
        .map { it.lowercase().replace(Regex("[^a-zа-я0-9|-]"), "") }
        .toList()
}

fun expectedValue(probabilities: Map<Int, Double>) = probabilities.map { it.key * it.value }.sum()
fun dispersion(probabilities: Map<Int, Double>): Double {
    return probabilities.map { it.key * it.key * it.value }.sum() - expectedValue(probabilities).pow(2)
}

fun dispersionSlower(probabilities: Map<Int, Double>): Double {
    return expectedValue(probabilities.mapKeys { it.key * it.key }) - expectedValue(probabilities).pow(2)
}

fun getProbabilities(words: List<String>): Map<Int, Double> {
    val counts = words.groupingBy { it.length }.eachCount()
    return counts.mapValues { it.value / words.size.toDouble() }.toSortedMap()
}

fun main() {
//    val text = File("src/main/kotlin/dispersion_and_exp_value/text.txt").readText()
    val text = ""
    val words = getWords(text)
    val probabilities = getProbabilities(words)
    println(words.size)
    println(probabilities)
    println(expectedValue(probabilities))
    println(dispersion(probabilities))
    println(dispersionSlower(probabilities))
    println(dispersion(probabilities) == dispersionSlower(probabilities))
}
