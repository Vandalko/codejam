import java.math.MathContext
import java.util.LinkedList
import java.util.Arrays
import kotlin.math.roundToInt
import kotlin.math.roundToLong

fun main(args: Array<String>) {
    println(Math.pow(10.0, 100.0))
    println(Math.pow(10.0, 100.0).toBigDecimal().round(MathContext(0)))
    println(Math.pow(10.0, 100.0).roundToLong())
    println(Math.pow(10.0, 100.0).roundToInt())

    val primeNumbers = sieveOfEratosthenes(10000)
    val letters = arrayOf('A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z')

    val reader = System.`in`.bufferedReader()
    val count = try {
        reader.readLine().toInt()
    } catch (e: NumberFormatException) {
        System.exit(0)
        return
    }

    (1..count).forEach { index ->
//        val (N, L) = reader.readLine().split(' ', limit = 2).map { it.toLong() }
        reader.readLine()
        val seq = reader.readLine().split(' ').map { it.toLong() }.toList()
        val first = seq.first()
        val result =
            primeNumbers.filter { prime -> first > prime && first % prime == 0L && primeNumbers.contains((first / prime).toInt()) }

        result.forEach { candidate ->
            val ourPrimes = mutableListOf(candidate)

            try {
                seq.forEach { num ->
                    ourPrimes.add((num / ourPrimes.last()).toInt())
                }
                val letterMapping = ourPrimes.toSortedSet().toList()

                if (letterMapping.size != letters.size) {
                    throw IllegalStateException("Got ${letterMapping.size} mappings")
                }

                val ourSeq = ourPrimes.map { letters[letterMapping.indexOf(it)] }.joinToString(separator = "")
                letters.forEach { letter ->
                    if (!ourSeq.contains(letter)) {
                        throw IllegalStateException("$letter is missing from $ourSeq")
                    }
                }

                val testPrimes = ourSeq.map { letterMapping[letters.indexOf(it)] }
                val testSeq = mutableListOf<Long>()
                (0 until testPrimes.size - 1).forEach {
                    testSeq.add((testPrimes[it] * testPrimes[it + 1]).toLong())
                }

                if (seq == testSeq) {
                    println("Case #$index: $ourSeq")
                }
            } catch (e: Throwable) {
//                println("Case #$index: $e")
            }
        }
    }

}

private fun sieveOfEratosthenes(n: Int): List<Int> {
    val prime = BooleanArray(n + 1)
    Arrays.fill(prime, true)
    var p = 2
    while (p * p <= n) {
        if (prime[p]) {
            var i = p * 2
            while (i <= n) {
                prime[i] = false
                i += p
            }
        }
        p++
    }
    val primeNumbers = LinkedList<Int>()
    for (i in 2..n) {
        if (prime[i]) {
            primeNumbers.add(i)
        }
    }
    return primeNumbers
}

/*

A * B = 1
B * C = 2
C * D = 3

1/2 = A / C
1/2 * 3 = A * D

 */

