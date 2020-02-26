package mypackage

import mypackage.Color.*

enum class Color {
    BLUE, ORANGE, RED, YELLOW, VIOLET, GREEN, INDIGO
}

fun getDescription(color: Color): String =
    when (color) {
        BLUE -> "cold"
        ORANGE -> "mild"
        RED -> "hot"
    }

fun respondToInput(input: String) = when (input) {
    "y", "yes" -> "I'm glad you agree!"
    "n", "no" -> "I am sorry to hear, you disagree!"
    else -> "I do not understand your arguments!"
}

fun mix(c1: Color, c2: Color) =
    when (setOf<Color>(c1, c2)) {
        setOf(RED, YELLOW) -> ORANGE
        setOf(YELLOW, BLUE) -> GREEN
        setOf(BLUE, VIOLET) -> INDIGO
        else -> throw Exception("Dirty Color")
    }
