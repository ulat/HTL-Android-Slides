// Iterator loops in Kotlin

fun test_loops() {
    val list = listOf("a", "b", "c")
    for (s in list) {
        print(s)
    }

    // with explicit type definition
    for (s: String in list) {
        print(s)
    }

    // iterating a map datastructure
    val map = mapOf(1 to "a",
        2 to "b",
        3 to "c")
    for ((key, value) in map) {
        println("$key = $value")
    }

    // list iteration with index
    for ((index, elem) in list.withIndex()) {
        print("$index: $elem")
    }

    // for loop with range
    for (idx in 1..10) {
        print(idx)
    }

    // iterating over string
    for (ch in "abc") {
        print(ch)
    }

    // exclusive upper bound range
    for (c in '0' until '9') {
        print(c)
    }
}

