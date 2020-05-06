/***
Implementiere die Funktion isValidIdentifier. Ein valider Identifier weist folgende Merkmale auf:
- nicht leerer String
- beginnt mit einem Buchstaben oder _
- beinhaltet nur Buchstaben, _ und Ziffern.


fun isValidIdentifier(s: String): Boolean {
    TODO()
}

fun main(args: Array<String>) {
    println(isValidIdentifier("name"))   // true
    println(isValidIdentifier("_name"))  // true
    println(isValidIdentifier("_12"))    // true
    println(isValidIdentifier(""))       // false
    println(isValidIdentifier("012"))    // false
    println(isValidIdentifier("no$"))    // false
}
 */