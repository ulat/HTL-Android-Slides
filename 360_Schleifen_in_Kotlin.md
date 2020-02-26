# Schleifen in Kotlin

Grunsätzlich gibt es in Kotlin die gleichen Schleifentypen wie in Java.

- while,
- do ... while und
- for - Schleife

Die ```while``` und ```do ... while``` Schleifen weisen die gleiche Syntax wie in Java auf. Unterschiede bestehen bei der ```for```-Schleife:

```kotlin
val list = listOf("a", "b", "c")
for (s in list) {
    print(s)
}
```
Ausgabe: ```abc```

Im Unterschied zu Java, wird für die Iteration mittels ```for```-Schleife ein anderes Schlüsselwort verwendet: ```in```.

Der Typ der Iterationsvariablen wird auch hier automatisch abgeleitet, kann jedoch auch manuell gesetzt werden:
```kotlin
val list = listOf("a", "b", "c")
for (s: String in list) {
    print(s)
}
```
## Iteration über eine ```Map```-Datenstruktur
Mit Kotlin ist es möglich, auch direkt über eine ```map``` zu iterieren:

```kotlin
val map = mapOf(1 to "a",
                2 to "b",
                3 to "c")
for ((key, value) in map) {
    println("$key = $value")
}
```
Diese Syntax kann auch in Kombination mit einer Liste verwendet werden, um zusätzlich zu den Elementen der Liste auch einen Index zu erhalten.
```kotlin
val list = listOf("a", "b", "c")
for ((index, elem) in list.withIndex()) {
    print("$index: $elem")
}
```
## Index Iteration
In Kotlin gibt es keine Entsprechung zur vollständigen ```for```-Schleife aus Java. Benötigt man nur einen Schleifenindex, so kann dieser direkt im Schleifenkopf mittels einer Range-Definition deklariert werden:
```kotlin
for (idx in 1..10) {
    print(idx)
}
```
Ein derartiger Range kann in Kotlin mit _inklusiver_ oder _exklusiver_ oberer Grenze definiert werden.

| inklusive oberer Grenze 	| 1..9 	|
|-----------------------------	|-----------	|
| __exklusive oberer Grenze__ 	| __1 until 9__ 	|
| __Rückwärtsiteration__ | __9 downTo 1__ |
| __Rückwärtsiteration mit Schrittweite__ | __9 downTo 1 step 2__ |

## String Iteration
In Kotlin kann mithilfe der For-Schleife auch direkt über einen String iteriert werden:
```kotlin
for (ch in "abc") {
    print(ch)
}
```
## Beispiel For-Schleife in Java und Kotlin
Betrachten wir als Fallbeispiel folgende Schleife aus Java:
```java
for (char c = '0'; c < '9'; c++) {
    System.out.print(c);
}
```
In Kotlin könnten wir diese Schleife folgendermaßen formulieren:
```kotlin
for (c in '0' until '9') {
    print(c)
}
```
