

fun main(args: Array<String>) {
    val reader = System.`in`.bufferedReader()
    val count = reader.readLine().toInt()

    (1..count).forEach { index ->
        val pq = reader.readLine().split(' ').map { it.toInt() }
        val Q = pq[1]
        val people = mutableListOf<Square>()
        (1..pq[0]).forEach { _ ->
            val person = reader.readLine().trim().split(' ')
            assert(person.size == 3)
            val x = person[0].toInt()
            val y = person[1].toInt()
            val d = person[2][0]
            when (d) {
                'S' -> people.add(Square(0, 0, Q, y - 1))
                'N' -> people.add(Square(0, y + 1, Q, Q))
                'W' -> people.add(Square(0, 0, x - 1, Q))
                'E' -> people.add(Square(x + 1, 0, Q, Q))
            }
        }
        assert(people.size == pq[0])
        val heatmap = Array(Q + 1) { IntArray(Q + 1) }
        people.forEach { person ->
            (person.l..person.r).forEach { x ->
                (0..Q).forEach { i ->
                    if (i >= person.b && i <= person.t) {
                        heatmap[x][i] += 1
                    }
                }
            }
        }

        var maxX = 0
        var maxY = 0
        var max = 0
        (0..Q).forEach { x ->
            (0..Q).forEach { y ->
                if (heatmap[x][y] > max) {
                    max = heatmap[x][y]
                    maxX = x
                    maxY = y
                }
            }
        }

        println("Case #$index: $maxX $maxY")
    }
}

private data class Square(val l: Int, val b: Int, val r: Int, val t: Int)
