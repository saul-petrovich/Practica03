# Practica03


**Materia:** Introduccion a Ciencias de la Computacion

## 01 Referencias e inmutabilidad de String

```java
public class Ejercicio1 {
    public static void main(String[] args) {
        String a = "Java";
        String b = new String("Java");
        String c = "Ja" + "va";
        String d = "Ja";
        d += "va";
        System.out.println(a == b);
        System.out.println(a == c);
        System.out.println(a == d);
        System.out.println(a.equals(d));
        b = b.concat(" 21");
        System.out.println(a);
        System.out.println(b);
    }
}
```

### Qué imprime

```
false
true
false
true
Java
Java 21
```
- a y c apuntan al mismo objeto del String Pool. c es "Ja" + "va", dos literales que el compilador une antes de ejecutar
- b apunta a un objeto distinto, creado con new String("Java") fuera del pool. Tiene el mismo texto que a, pero no es el mismo objeto
- d apunta a un objeto distinto, creado en tiempo de ejecución con d += "va". También tiene el texto "Java", pero fuera del pool
- Después de b = b.concat(" 21"), b pasa a apuntar a un objeto nuevo "Java 21". El objeto anterior de `b` ya no tiene ninguna variable que lo referencie, y a no cambia

**String Pool:** zona de memoria donde se guarda una sola copia de cada literal para reutilizarla. Es seguro compartirlos porque los String son inmutables, new String y las concatenaciones hechas en ejecucion crean objetos fuera del pool.
- **== vs equals():** == compara referencias (si son el mismo objeto); equals() compara el contenido (si el texto es igual). Por eso a == d es false pero a.equals(d) es true.
- **concat():** no modifica el String original, porque es inmutable; devuelve un String nuevo. Por eso a sigue valiendo Java y b pasa a valer Java 21 solo porque se reasigno.
