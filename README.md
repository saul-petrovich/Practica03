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


## 02 Operaciones e indices en StringBuilder

```java
StringBuilder sb = new StringBuilder("ABCDE");
sb.delete(1, 4);
sb.insert(1, "xyz");
sb.reverse();
sb.replace(1, 4, "-");
System.out.println(sb);
System.out.println(sb.length());
System.out.println(sb.capacity());
```

### Contenido tras cada modificacion

- new StringBuilder("ABCDE") ---> ABCDE
- sb.delete(1, 4) ----> AE
- sb.insert(1, "xyz") -----> AxyzE
- sb.reverse() ------> EzyxA
- sb.replace(1, 4, "-") ------> E-

### Salida

```
E-A
3
21
```

### Explicacion

- **delete(1, 4):** el rango es [1, 4), o sea incluye la posición 1 y excluye la 4, borra las posiciones 1, 2 y 3, es decir 3 caracteres (4 - 1).
- **length() vs capacity():** length() es el número de caracteres que tiene ahora (3). capacity() es el espacio reservado antes de tener que crecer (21 = 5 + 16 de la creación).


## 03 Referencias compartidas

### Codigo

```java
StringBuilder a = new StringBuilder("Hola");
StringBuilder b = a;
StringBuilder c = new StringBuilder("Hola");

a.append(" mundo");
b.delete(0, 5);

System.out.println(a);
System.out.println(b);
System.out.println(c);
System.out.println(a == b);
System.out.println(a.equals(b));
System.out.println(a.equals(c));
```

### Salidas

```
mundo
mundo
Hola
true
true
false
```

### Como afecta cada modificacion

- b = a=b copia la referencia de a, asi que ambas apuntan al mismo objeto .
- c = new StringBuilder("Hola"): crea un objeto distinto.
- a.append: "mundo" modifica el objeto de a "Hola mundo" b tambien lo ve porque apunta al mismo objeto.
- b.delete:(0, 5): borra "Hola " del objeto de b, que queda como "mundo", a tambien lo ve.
- c nunca cambia, porque ninguna operacion se hizo a traves de c.

### Esquema

Estado inicial (tras las tres primeras lineas):

```
  Variables                       Objetos

 -----
   a ------------>
 -----                     ------------------
 -----                      StringBuilder #1  
   b  ---------->             "Hola"       
 -----                     ------------------

 -----                    ------------------
   c  ------------>         StringBuilder #2 
 -----                          "Hola"       
                          ------------------
```

Estado final:

```
  Variables                     Objetos
 

 -----
   a  ----------->
 -----
                           ------------------
 -----                        StringBuilder #1        
   b  ----------->                "mundo"      
 -----                     ------------------





 -----                    ------------------
   c  ---------->          StringBuilder #2 
 -----                         "Hola"       
                          ------------------
```


## Ejercicio 4

```java
public static String comprimir(String texto) {
    StringBuilder resultado = new StringBuilder();

    int i = 0;
    while (i < texto.length()) {
        char actual = texto.charAt(i);
        int contador = 1;
        int j = i + 1;

        while (j < texto.length() && texto.charAt(j) == actual) {
            contador++;
            j++;
        }

        resultado.append(actual);
        resultado.append(contador);
        i = j;
    }

    return resultado.toString();
}
```


## Ejercicio 5

```java
public static boolean esPalindromo(String texto) {
    int izquierda = 0;
    int derecha = texto.length() - 1;
    while (izquierda < derecha) {
        char a = normalizar(texto.charAt(izquierda));
        char b = normalizar(texto.charAt(derecha));
        if (!esSignificativo(a)) {
            izquierda++;
        } else if (!esSignificativo(b)) {
            derecha--;
        } else if (a != b) {
            return false;
        } else {
            izquierda++;
            derecha--;
        }
    }
    return true;
}

private static char normalizar(char c) {
    String origen  = "ABCDEFGHIJKLMNOPQRSTUVWXYZµÖàé¥ ‚¡¢£";
    String destino = "abcdefghijklmnopqrstuvwxyzaeiou¤aeiou";
    int posicion = origen.indexOf(c);
    return posicion < 0 ? c : destino.charAt(posicion);
}

private static boolean esSignificativo(char c) {
    return (c >= 'a' && c <= 'z')
            || (c >= '0' && c <= '9')
            || c == '¤';
}
```

