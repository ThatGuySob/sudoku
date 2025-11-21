package ie.tudublin

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertEquals

class SudokuTest {

    // Easy solvable Sudoku
    @Test
    fun testEasySudoku() {
        val rows = listOf(
            "530070000",
            "600195000",
            "098000060",
            "800060003",
            "400803001",
            "700020006",
            "060000280",
            "000419005",
            "000080079"
        )
        val sudoku = Sudoku(rows)
        val solved = sudoku.solve()
        assertTrue(solved, "The easy Sudoku should be solvable")
        assertEquals(5, sudoku.getGrid()[0][0])
        assertEquals(9, sudoku.getGrid()[8][8])
    }

    // medium solvable Sudoku
    @Test
    fun testMediumSudoku() {
        val rows = listOf(
            "200080300",
            "060070084",
            "030500209",
            "000105408",
            "000000000",
            "402706000",
            "301007040",
            "720040060",
            "004010003"
        )
        val sudoku = Sudoku(rows)
        val solved = sudoku.solve()
        assertTrue(solved, "The medium Sudoku should be solvable")
        assertEquals(2, sudoku.getGrid()[0][0])
        assertEquals(3, sudoku.getGrid()[8][8])
    }

    // Unsolvable Sudoku
    @Test
    fun testUnsolvableSudoku() {
        val rows = listOf(
            "111111111", 
            "111111111",
            "111111111",
            "111111111",
            "111111111",
            "111111111",
            "111111111",
            "111111111",
            "111111000"
        )
        val sudoku = Sudoku(rows)
        val solved = sudoku.solve()
        assertFalse(solved, "This Sudoku should be unsolvable")
    }
}
