package ie.tudublin

class Sudoku(rows: List<String>) {
    private val grid = IntArray(81)
    private var iterations = 0
    private val LIMIT = 2_000_000

    init {
        require(rows.size == 9 && rows.all { it.length == 9 }) {
            "Grid must be 9 x 9"
        }
        for (i in 0..8) {
            for (j in 0..8 ) grid[9 * i + j] = rows[i][j] - '0'
        }
    }

    fun solve(): Boolean {
        iterations = 0
        if (!isValidStartingGrid()) return false
        return placeNumber(0)
    }

    private fun isValidStartingGrid(): Boolean {
        // rows
        for (r in 0..8) {
            val seen = BooleanArray(10)
            for (c in 0..8) {
                val v = grid[r * 9 + c]
                if (v != 0) {
                    if (seen[v]) return false
                    seen[v] = true
                }
            }
        }
        // cols
        for (c in 0..8) {
            val seen = BooleanArray(10)
            for (r in 0..8) {
                val v = grid[r * 9 + c]
                if (v != 0) {
                    if (seen[v]) return false
                    seen[v] = true
                }
            }
        }
        // boxes
        for (br in 0..2) {
            for (bc in 0..2) {
                val seen = BooleanArray(10)
                for (dr in 0..2) {
                    for (dc in 0..2) {
                        val idx = (br * 3 + dr) * 9 + (bc * 3 + dc)
                        val v = grid[idx]
                        if (v != 0) {
                            if (seen[v]) return false
                            seen[v] = true
                        }
                    }
                }
            }
        }
        return true
    }

    private fun placeNumber(pos: Int): Boolean {
        if (iterations++ > LIMIT) return false

        if (pos == 81) {
            return true
        }
        if (grid[pos] > 0) {
            return placeNumber(pos + 1)
        }
        for (n in 1..9) {
            if (checkValidity(n, pos % 9, pos / 9)) {
                grid[pos] = n
                if (placeNumber(pos + 1)) return true
                grid[pos] = 0
            }
        }

        return false
    }

    private fun checkValidity(v: Int, x: Int, y: Int): Boolean {
        for (i in 0..8) {
            if (grid[y * 9 + i] == v || grid[i * 9 + x] == v) return false
        }
        val startX = (x / 3) * 3
        val startY = (y / 3) * 3
        for (i in startY until startY + 3) {
            for (j in startX until startX + 3) {
                if (grid[i * 9 + j] == v) return false
            }
        }
        return true
    }

    override fun toString(): String {
        val sb = StringBuilder()
        for (i in 0..8) {
            for (j in 0..8) {
                sb.append(grid[i * 9 + j])
                sb.append(" ")
                if (j == 2 || j == 5) sb.append("| ")
            }
            sb.append("\n")
            if (i == 2 || i == 5) sb.append("------+-------+------\n")
        }
        return sb.toString()
    }
    fun getGrid(): List<List<Int>> =
        (0..8).map { r -> (0..8).map { c -> grid[r * 9 + c] } }
}