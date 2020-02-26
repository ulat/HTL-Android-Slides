fun max(a: Int, b: Int): Int = if (a > b) a else b

fun display(a: Int, b: Int): Unit {
    println(max(a, b))
}

fun printList(): Unit {
    println(listOf('a', 'b', 'c').joinToString(separator = "", prefix = "(", postfix = ")"))
}

// Funktion mit Default-Werten
fun displaySeparator(character: Char = '*', size: Int = 10) {
    repeat(size) {
        print(character)
    }
}