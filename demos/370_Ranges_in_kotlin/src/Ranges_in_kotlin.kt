// checks if char is lower or uppercase letter
fun isLetter(c: Char) = c in 'a'..'z' || c in 'A'..'Z'

// not in
fun isNotDigit(c: Char) = c !in '0'..'9'

// in combined with when
fun recognizeChar(c: Char) = when(c) {
    in '0'..'9' -> "It's a digit!"
    in 'a'..'z', in 'A'..'Z' -> "It's a letter"
    else -> "Unknown char"
}