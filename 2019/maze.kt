private val S = 'S'
private val E = 'E'

fun main(args: Array<String>) {
    val reader = System.`in`.bufferedReader()
    val count = reader.readLine().toInt()

    (1..count).forEach { index ->
        val N = reader.readLine().toInt()
        val insteps = reader.readLine().trim()
        val inPath = seqToPath(insteps)


        val outsteps = mutableListOf<Path>()
        genPath(inPath, Path(mutableListOf(Part(Pair(0, 0), updateCoords(0, 0, invert(insteps[0])), invert(insteps[0])))), outsteps, N)

        val outPath = outsteps.first().parts.map { it.c }.joinToString(separator = "")


        println("Case #$index: $outPath")
    }
}

private fun genPath(inPath: Path, outPath: Path, outsteps: MutableList<Path>, N: Int) {
    if (outsteps.isEmpty()) {
        val directionLimit = N - 1
        val sizeLimit = 2 * directionLimit
        if (outPath.size() < sizeLimit) {
            if (outPath.parts.count { it.c == S } < directionLimit) {
                val from = outPath.parts.last().to
                val to = updateCoords(from, S)
                val part = Part(from, to, S)
                if (!inPath.parts.contains(part)) {
                    val copy = ArrayList(outPath.parts)
                    copy.add(part)
                    genPath(inPath, Path(copy), outsteps, N)
                }
            }
            if (outPath.parts.count { it.c == E } < directionLimit) {
                val from = outPath.parts.last().to
                val to = updateCoords(from, E)
                val part = Part(from, to, E)
                if (!inPath.parts.contains(part)) {
                    val copy = ArrayList(outPath.parts)
                    copy.add(part)
                    genPath(inPath, Path(copy), outsteps, N)
                }
            }
        } else {
            outsteps.add(outPath)
        }
    }
}

private fun updateCoords(coords: Pair<Int, Int>, c: Char): Pair<Int, Int> {
    return updateCoords(coords.first, coords.second, c)
}

private fun updateCoords(x: Int, y: Int, c: Char): Pair<Int, Int> {
    return if (c == S) {
        Pair(x, y + 1)
    } else {
        Pair(x + 1, y)
    }
}

private fun invert(c: Char): Char {
    return if (c == S) {
        E
    } else {
        S
    }
}

private fun seqToPath(seq: String): Path {
    val parts = mutableListOf<Part>()
    var from = Pair(0, 0)
    seq.forEach {
        val to = updateCoords(from, it)
        parts.add(Part(from, to, it))
        from = to
    }
    return Path(parts)
}

private data class Part(val from: Pair<Int, Int>, val to: Pair<Int, Int>, val c: Char)

private data class Path(val parts: MutableList<Part>) {
    fun intersects(other: Path): Boolean {
        return parts.find { other.parts.contains(it) } != null
    }

    fun size() = parts.size
}



//
//EESSSESE
//SEEESSES



