# Konstante in Kotlin
Für Konstante basierend auf primitiven Datentypen verwenden wir in Kotlin das Schlüsselwort `const`. Sollte man auf die Erstellung der getter-Methoden durch den Kompiler verzichten wollen, so können wir dieses Verhalten durch die Annotation `@JvMField` erreichen. Dadurch wird nur das Property generiert.

Wenn in Java eine Konstante mit primitiven Typ oder vom Typ String deklariert wird und ihr Wert zur Compile-Zeit feststeht, so ersetzt der Compiler die Konstante im gesamten Code durch den tatsächlichen Wert. Diese Konstante wird in Java als `compile-time constant` bezeichnet.

In Kotlin wird jede Variable, die mit dem Schlüsselwort `const` modifiziert wird, zur compile-time Konstanten. Auch der Kotlin-Kompiler ersetzt die Konstanten durch den tatsächlichen Wert im gesamten Sourcecode.

Die Annotation `@JvmField` annotiert ein Kotlin Property als Java Feld und macht es öffentlich, wenn das Property ebenfalls öffentlich ist.

```Kotlin
@JvmField
val prop = MyClass()
```

... ergibt den gleichen Code wie (Java):

```Java
public static final MyClass prop = new MyClass();
```

Dieses Feld kann dann von Java aus verwendet werden, was für einige Frameworks erforderlich ist.

_Durch die Annotation `@JvmField` werden keine getter (bzw. setter bei mutable properties) Methoden generiert._

# Generics in Kotlin
Genauso wie in Java können wir auch in Kotlin Klassen oder Interfaces als _Generics_ definieren. Wir geben also keinen konkreten Typen an. Diese Konkretisierung des Typs erfolgt dann erst bei der Initialisierung eines Objekts bzw. der Implementierung des Interfaces.

Dieses Verhalten ist in Kotlin genauso.

```kotlin
interface List<E> {
    fun get(index: Int): E
}

fun foo(ints: List<Int>) { ... }
fun bar(strings: List<String>) { ... }
```

Wir können jedoch auch die Funktion selbst _generic_ machen. Wir definieren einen Typ-Paramter und verwenden diesen Typ-Parameter in der Deklaration der Funktion und im Body der Funktion.

```kotlin
fun<T> List<T>.filter(predicate: (T) -> Boolean): List<T> {
    val destination = ArrayList<T>()
    for (element in this) {
        if (predicate(element)) destination.add(element)
    }
    return destination
}
```

_In diesem Setting wissen wir jedoch nicht, ob `T` nullable ist oder nicht. Wir können jedoch explizit auch nullable Typen als Argumente für Generics verwenden._

# OOP - Design Entscheidungen
Warum wurde für Kotlin der Standard `final` und `public` gewählt? Das sieht auf den ersten Blick widersprüchlich aus, da `public` ein Maximum an Sichtbarkeit gewährleistet, während `final` ein Minimum an Veränderbarkeit darstellt.

Mit diesem Schritt soll sowohl Applikationsentwicklern, wie auch Entwicklern von Bibliotheken entgegen gekommen werden. Der Entwickler einer Bibliothek möchte die Sichtbarkeit so gut wie möglich einschränken können. Änderungen an Bibliotheken können schwer rückgängig gemacht werden, da andere Systeme davon abhängen. Wurde also in einer Bibliothek ein Teil unbeabsichtigt veröffentlicht, ist es schwierig dies wieder rückgängig zu machen.

Mithilfe des Standards `final` wird nebenbei auch das smart-casting erleichtert.
