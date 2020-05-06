# Variablen in Kotlin

In Kotlin können wir Variablen mit den Schlüsselwörtern ```val``` (_read only Variable - Konstante_) und ```var``` (_"echte" Variable_) deklarieren.

![Variable und Konstanten in Kotlin](assets/330_Variablen_in_Kotlin-249de19f.png)

|Java | Kotlin|
| --- | --- |
|```final``` | ```val```|
| _Kontante_ | _read only Variable_ |

Die Typangabe können wir bei der Deklaration von Variablen weglassen. Der Compiler leitet diese automatisch ab.

```kotlin
val greeting = "Hi!" // gleich zu:
val greeting = "Hi!": String
```

__Warum ist folgender Code nicht ausführbar?__

```kotlin
var string = 1
string = "abc"
```

__Können *val-Werte* verändert werden?__

Ja! Mit ```val``` erzeugen wir einfach eine Referenz auf einen Speicherbereich. Ändert sich der Inhalt im Speicher, ändert sich natürlich auch der Wert, auf den ```val``` referenziert.

```kotlin
val languages = mutableListOf("Java")
languages.add("Kotlin")
```

![](assets/330_Variablen_in_Kotlin-8d8ddb12.png)

__Doch warum funktioniert folgender Code nicht?__

```kotlin
val languages = listOf("Java")
languages.add("Kotlin")
```

_Dies ist nicht möglich, da man in Kotlin eine ```readonly``` Liste nicht verändern kann._

Mit Kotlin sollten wir Variablen nach Möglichkeit mit ```val``` und veränderlichen Typen deklarieren, da dieses Muster näher am Stil der funktionalen Programmierung und damit an der Kernidee von Kotlin liegt.
