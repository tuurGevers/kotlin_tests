package gps

fun gps(s: Int, x: DoubleArray): Int {
    var dist: Double = 0.0
    var maxSpeed:Int = 0
    for (i in x) {
        if (x[x.size - 1] != i) {
            var nextD: Double = 0.0

            nextD = x[x.indexOfFirst { it == i } + 1]

            dist = nextD - i
            val speed: Int = ((3600 * dist) / s).toInt()
            if (speed>maxSpeed) maxSpeed=speed


        }

    }
   return maxSpeed
}

fun gpsBest(s: Int, x: DoubleArray) = x.asSequence().zipWithNext { a, b -> (b - a) * 3600 / s }.maxOrNull()?.toInt() ?: 0

fun main() {
    val x: DoubleArray = doubleArrayOf(0.0, 0.19, 0.5, 0.75, 1.0, 1.25, 1.5, 1.75, 2.0, 2.25)
    println(gps(15, x))
    println(gpsBest(15,x))
}