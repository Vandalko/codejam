import kotlin.math.abs
import kotlin.math.max

fun main(args: Array<String>) {
    val reader = System.`in`.bufferedReader()
    val count = reader.readLine().toInt()

    (1..count).forEach { index ->
        val nk = reader.readLine().split(' ').map { it.toInt() }
        val N = nk[0]
        val K = nk[1]
        val C = reader.readLine().split(' ').mapTo(ArrayList()) { it.toInt() }
        val D = reader.readLine().split(' ').mapTo(ArrayList()) { it.toInt() }

        var result = 0
        while (true) {
            var maxC = -2
            var maxD = -2
            var maxCI = -2
            var maxDI = -2
            (0 until N).forEach { i ->
                if (C[i] > maxC) {
                    maxC = C[i]
                    maxCI = i
                }
                if (D[i] > maxD) {
                    maxD = D[i]
                    maxDI = i
                }

                if (abs(maxC - maxD) <= K) {
                    return@forEach
                }
            }

            if (maxC < 0 || maxD < 0) {
                break
            } else if (abs(maxC - maxD) <= K) {
                (0..max(maxCI, maxDI)).forEach { i ->
                    C[i] = -1
                    D[i] = -1
                }
                result += max(maxCI, maxDI) + 1
            } else if (maxC > maxD) {
                C[maxCI] = -1
            } else if (maxD > maxC) {
                D[maxDI] = -1
            }
        }


//        while (true) {
//            var maxC = -2
//            var maxD = -2
//            var maxCI = -2
//            var maxDI = -2
//            (0 until N).forEach { i ->
//                if (C[i] > maxC) {
//                    maxC = C[i]
//                    maxCI = i
//                }
//                if (D[i] > maxD) {
//                    maxD = D[i]
//                    maxDI = i
//                }
//            }
//            if (maxC < 0 || maxD < 0) {
//                break
//            } else if (abs(maxC - maxD) <= K) {
//                (0..max(maxCI, maxDI)).forEach { i ->
//                    if (C[i] >= 0 && D[i] >= 0) {
//                        result += 1
//                    }
//                }
//            }
//            if (maxC > maxD) {
//                C[maxCI] = -1
//            } else if (maxD > maxC) {
//                D[maxDI] = -1
//            } else {
//                C[maxCI] = -1
//                D[maxDI] = -1
//            }
//        }

        println("Case #$index: $result")
    }
}
