fun List<Int>.sum(): Int {
    var s = 0
    for (i in this) {
        s += i
    }
    return s
}

fun main(args: Array<String>) {
    val sum = (listOf(1, 2, 3)).sum()
    println(sum)    // 6
}