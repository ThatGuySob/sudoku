package ie.tudublin

import java.io.File
import java.io.IOException

fun main() {
    print("Enter text file name: ")
    val filePath = readln() // Reads a line from standard input as String
    val basePath = "C:\\Users\\C23398751\\Downloads\\sudoku\\"
    val file = File(basePath + filePath)

    if (!file.exists()) {
        println("Error: File '$filePath' not found.")
        return
    }
    

    if (!file.exists()) {
        println("Error: File '$filePath' not found.")
        return
    }

    val content: String = try {
        file.readText(Charsets.UTF_8)
    } catch (e: IOException) {
        println("Error reading file: ${e.message}")
        return
    }

    val rows = content.lines().filter { it.isNotBlank() }

    val sudoku = Sudoku(rows)

    println("\n\nStarting grid:\n")
    println(sudoku.toString())

    val solved = sudoku.solve()

    if (solved) {
        println("Solution:\n")
        println(sudoku.toString())
    } else {
        println("Unsolvable!")
    }
    
}
