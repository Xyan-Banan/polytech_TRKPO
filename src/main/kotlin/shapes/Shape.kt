package shapes

import kotlin.math.pow
import kotlin.math.sqrt

interface Shape {
    fun getArea(): Double
}

data class Circle(private val radius: Double) : Shape {
    override fun getArea(): Double = Math.PI * radius.pow(2.0)
}

data class Triangle constructor(private val side1: Double, private val side2: Double, private val side3: Double) :
    Shape {
    override fun getArea(): Double {
        val p = (side1 + side2 + side3) / 2
        return sqrt(p * (p - side1) * (p - side2) * (p - side3))
    }
}

data class Rectangle(private val height: Double, private val width: Double) : Shape {
    override fun getArea(): Double = height * width
}

fun main() {
    val shapes = listOf(
        Circle(3.0),
        Triangle(3.0,4.0,5.0),
        Rectangle(4.0,5.0)
    )
    val biggest = shapes.maxByOrNull { it.getArea() }
    println(if(biggest != null) "$biggest area = ${biggest.getArea()}" else "empty array")
}