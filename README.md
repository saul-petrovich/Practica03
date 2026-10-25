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


## Ejercicio 6

```java
public static String eliminarDuplicados(String texto) {
    StringBuilder resultado = new StringBuilder();
    for (int i = 0; i < texto.length(); i++) {
        char actual = texto.charAt(i);
        if (!contiene(resultado, actual)) resultado.append(actual);
    }
    return resultado.toString();
}

private static boolean contiene(StringBuilder texto, char buscado) {
    for (int i = 0; i < texto.length(); i++) {
        if (texto.charAt(i) == buscado) return true;
    }
    return false;
}
```

**¨C¢mo detecto los caracteres que ya aparecieron?**

Uso el propio `StringBuilder` `resultado` como memoria. Ah¡ s¢lo hay caracteres que ya aparecieron, sin repetir. Antes de agregar un car cter, el m‚todo `contiene` lo busca en `resultado`, letra por letra. Si lo encuentra, ya hab¡a aparecido y no lo agrego. Si no lo encuentra, es su primera aparici¢n y lo agrego al final, as¡ se respeta el orden original.

**¨Cu ntos recorridos realiza?**

Hay un recorrido principal del texto original, de izquierda a derecha, una sola vez. Dentro de ese recorrido, por cada car cter se hace otro recorrido corto sobre `resultado` para buscarlo. Ese recorrido corto nunca es m s largo que el n£mero de caracteres distintos que ya se guardaron.



## Ejercicio 7

```java
public static String reemplazar(String texto, String buscar, String reemplazo) {
    if (buscar.length() == 0) return texto;
    StringBuilder resultado = new StringBuilder();
    int i = 0;
    while (i < texto.length()) {
        if (coincide(texto, buscar, i)) {
            resultado.append(reemplazo);
            i += buscar.length();
        } else {
            resultado.append(texto.charAt(i));
            i++;
        }
    }
    return resultado.toString();
}

private static boolean coincide(String texto, String buscar, int inicio) {
    if (buscar.length() > texto.length() - inicio) return false;
    for (int j = 0; j < buscar.length(); j++) {
        if (texto.charAt(inicio + j) != buscar.charAt(j)) return false;
    }
    return true;
}
```

**¨C¢mo maneja el m‚todo el caso en que `buscar` est  vac¡o?**

Si `buscar` est  vac¡o, el m‚todo devuelve el texto sin cambios (`if (buscar.length() == 0) return texto;`). Lo hice as¡ porque una cadena vac¡a "aparece" en todas las posiciones del texto, as¡ que reemplazarla no tiene sentido y adem s har¡a que el ciclo no avanzara de forma £til.




## Ejercicio 8

```java
public static String invertirPalabras(String texto) {
    StringBuilder resultado = new StringBuilder();
    int derecha = texto.length() - 1;
    int izquierda = 0;

    while (derecha >= 0) {
        int fin = derecha;
        while (derecha >= 0 && texto.charAt(derecha) != ' ') derecha--;
        for (int k = derecha + 1; k <= fin; k++) {
            resultado.append(texto.charAt(k));
        }

        while (izquierda < texto.length() && texto.charAt(izquierda) != ' ') izquierda++;

        while (izquierda < texto.length() && texto.charAt(izquierda) == ' ') {
            resultado.append(' ');
            izquierda++;
        }

        while (derecha >= 0 && texto.charAt(derecha) == ' ') derecha--;
    }
    return resultado.toString();
}
```



## Ejercicio 9

```java
public static boolean validar(String expresion) {
    StringBuilder pendientes = new StringBuilder();
    for (int i = 0; i < expresion.length(); i++) {
        char c = expresion.charAt(i);
        if (esApertura(c)) {
            pendientes.append(c);
        } else if (esCierre(c)) {
            if (pendientes.length() == 0) return false;
            int ultima = pendientes.length() - 1;
            if (!corresponden(pendientes.charAt(ultima), c)) return false;
            pendientes.deleteCharAt(ultima);
        }
    }
    return pendientes.length() == 0;
}

private static boolean esApertura(char c) {
    return c == '(' || c == '[' || c == '{';
}

private static boolean esCierre(char c) {
    return c == ')' || c == ']' || c == '}';
}

private static boolean corresponden(char apertura, char cierre) {
    return (apertura == '(' && cierre == ')')
        || (apertura == '[' && cierre == ']')
        || (apertura == '{' && cierre == '}');
}
```


# Ejercicio 10: Editor de texto

## EditorTexto.java

```java
public class EditorTexto {
    private StringBuilder contenido;
    private StringBuilder anterior;

    public EditorTexto() {
        contenido = new StringBuilder();
        anterior = null;
    }

    public void escribir(String texto) {
        StringBuilder nuevo = new StringBuilder(contenido.toString());
        nuevo.append(texto);
        guardarSiCambio(nuevo);
    }

    public void insertar(int posicion, String texto) {
        if (!valido(posicion, posicion)) return;
        StringBuilder nuevo = new StringBuilder(contenido.toString());
        nuevo.insert(posicion, texto);
        guardarSiCambio(nuevo);
    }

    public void eliminar(int inicio, int fin) {
        if (!valido(inicio, fin)) return;
        StringBuilder nuevo = new StringBuilder(contenido.toString());
        nuevo.delete(inicio, fin);
        guardarSiCambio(nuevo);
    }

    public void reemplazar(int inicio, int fin, String texto) {
        if (!valido(inicio, fin)) return;
        StringBuilder nuevo = new StringBuilder(contenido.toString());
        nuevo.replace(inicio, fin, texto);
        guardarSiCambio(nuevo);
    }

    public void deshacer() {
        if (anterior == null) return;
        contenido = anterior;
        anterior = null;
    }

    public String obtenerTexto() {
        return contenido.toString();
    }

    private boolean valido(int inicio, int fin) {
        return inicio >=



# Tarea 03.2

## Ejercicio 11: Recorrer un arreglo de numeros

```java
public class Ejercicio11 {

    public static void main(String[] args) {
        int[] numeros = {8, 3, 10, 5, 4};

        int suma = 0;
        int mayor = numeros[0];

        for (int i = 0; i < numeros.length; i++) {
            suma = suma + numeros[i];

            if (numeros[i] > mayor) {
                mayor = numeros[i];
            }
        }

        double promedio = suma / 5.0;

        System.out.println("suma = " + suma);
        System.out.println("promedio = " + promedio);
        System.out.println("mayor = " + mayor);
    }
}
```

Salida:

```
suma = 30
promedio = 6.0
mayor = 10
```

## Ejercicio 12: Suma de cadenas binarias

```java
public class Ejercicio12 {

    public String addBinary(String a, String b) {
        int largo = a.length();
        if (b.length() > largo) {
            largo = b.length();
        }

        int[] resultado = new int[largo + 1];
        int i = a.length() - 1;
        int j = b.length() - 1;
        int acarreo = 0;

        for (int k = largo; k >= 0; k--) {
            int suma = acarreo;

            if (i >= 0) {
                suma = suma + (a.charAt(i) - '0');
                i--;
            }
            if (j >= 0) {
                suma = suma + (b.charAt(j) - '0');
                j--;
            }

            resultado[k] = suma % 2;
            acarreo = suma / 2;
        }

        int inicio = 1;
        if (resultado[0] == 1) {
            inicio = 0;
        }

        String texto = "";
        for (int p = inicio; p < resultado.length; p++) {
            texto = texto + resultado[p];
        }
        return texto;
    }

    public static void main(String[] args) {
        Ejercicio12 e = new Ejercicio12();
        System.out.println(e.addBinary("11", "1"));
        System.out.println(e.addBinary("1010", "1011"));
    }
}
```

Salida:

```
100
10101
```
