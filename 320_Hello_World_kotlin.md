# Das klassische HelloWorld in Kotlin

Als erstes Projekt erstellen wir das klassische _HelloWorld_:
```kotlin
fun main() {
    val name = "Kotlin"
    println("Hello, $name!")
}
```
An diesem Beispiel sehen wir bereits, dass es in Kotlin nicht erforderlich ist, eine Klasse zu definieren.

In Java müsste man zB statische Hilfsfunktionen in eine eigene Klasse packen. Dies ist in Kotlin nicht erforderlich, da Funktionen direkt im Package erstellt werden können.

Die ```main```-Funktion kann in Kotlin mit oder ohne Aufrufparameter generiert werden.

Das obige Beispiel mit Aufrufparameter:
```kotlin
fun main(args: Array<String>) {
    val name = if (args.size > 0) args[0] else "Kotlin"
    println("Hello, $name!")
}
```
Kotlin verwendet _String-Templates_. Das bedeutet, es können Variablen direkt in Strings geschrieben werden: ```"Hello, $VARIABLE_NAME"```. Dadurch wird die Variable im String durch den entsprechenden Wert ersetzt. Es können auch umfassendere Anweisungen direkt in einen String gepackt werden: ```"...${functionCall(..)}..."```. Der Aufruf muss nun jedoch innerhalb der geschweiften Klammern stehen.

Wir können unsere Main-Methode adaptieren:
```kotlin
fun main(args: Array<String>) {    
    println("Hello, ${args.getOrNull(0)}")
}
```
Nun wird der erste Aufrufparameter ausgegeben, sofern mindestens ein Wert im Array enthalten ist. Ist das Array leer, so liefert der Aufruf ```null``` zurück.

Doch was passiert, wenn diese Aufrufe selbst ein ```println``` Statement beinhalten?
```kotlin
fun foo(): String {
  println("Calculating foo...")
  return "foo"
}

fun main(args: Array<String>) {
  println("First ${foo()}, second ${foo()}")
}
```
Die Ausgabe von obigem Code sieht folgendermaßen aus:
```
Calculating foo...
Calculating foo...
First foo, second foo
```
Die Funktion wird innerhalb des Strings einfach zweimal aufgerufen. Es erfolgt kein internes caching der Aufrufe oder Ähnliches.
