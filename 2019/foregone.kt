import kotlin.math.roundToLong

fun main(args: Array<String>) {
    val reader = System.`in`.bufferedReader()
    val count = reader.readLine().toInt()

    (1..count).forEach { index ->
        val number = reader.readLine().toLong()

        var a = 1L
        var b = number - a
        while (b > 0 && a < number) {
            var fourInd = containsFour(a)
            if (fourInd >= 0) {
                val oldA = a
                a += Math.pow(10.toDouble(), fourInd.toDouble()).roundToLong()
                if (a >= number) {
                    a = oldA + 1
                }
                b = number - a
            } else {
                fourInd = containsFour(b)
                if (fourInd >= 0) {
                    val oldB = b
                    b -= Math.pow(10.toDouble(), fourInd.toDouble()).roundToLong()
                    if (b <= 0) {
                        b = oldB - 1
                    }
                    a = number - b
                } else {
                    break
                }
            }
        }

        if (a + b != number) {
            throw IllegalStateException("$a + $b != $number")
        }

        println("Case #$index: $a $b")
    }


}


private fun containsFour(num: Long): Int {
    var index = -1
    var test = num
    while (test > 0) {
        index++
        if (test % 10 == 4L) {
            return index
        }
        test /= 10
    }
    return -1
}
