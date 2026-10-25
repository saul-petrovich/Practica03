public class ejer5 {

    
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
        String origen  = "ABCDEFGHIJKLMNOPQRSTUVWXYZÁÉÍÓÚÑáéíóú";
        String destino = "abcdefghijklmnopqrstuvwxyzaeiouñaeiou";
        int posicion = origen.indexOf(c);
        return posicion < 0 ? c : destino.charAt(posicion);
    }

    private static boolean esSignificativo(char c) {
        return (c >= 'a' && c <= 'z')
                || (c >= '0' && c <= '9')
                || c == 'ñ';
    }

    public static void main(String[] args) {
        System.out.println(esPalindromo("Anita lava la tina"));       
        System.out.println(esPalindromo("¿Acaso hubo búhos acá?"));   
        System.out.println(esPalindromo("Java es genial"));           
        System.out.println(esPalindromo("Somos"));                    
    }
}