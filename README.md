# Pr ctica03

**Alumno:** Petrovich Tovar Diego Saul

**Materia:** Introducci¢n a Ciencias de la Computaci¢n

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

**String Pool:** zona de memoria de la JVM donde se guarda una sola copia de cada literal para reutilizarla. Es seguro compartirlos porque los `String` son inmutables. Por eso `a` y `c` son el mismo objeto. `new String(...)` y las concatenaciones hechas en ejecuci¢n crean objetos fuera del pool.
- **`==` vs `equals()`:** `==` compara referencias (si son el mismo objeto); `equals()` compara el contenido (si el texto es igual). Por eso `a == d` es `false` pero `a.equals(d)` es `true`.
- **`concat()`:** no modifica el `String` original, porque es inmutable; devuelve un `String` nuevo. Por eso `a` sigue valiendo `"Java"` y `b` pasa a valer `"Java 21"` solo porque se reasign¢.
