public class ejer8 {

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

    public static void main(String[] args) {
        System.out.println(invertirPalabras("Java es poderoso"));
        System.out.println(invertirPalabras("uno dos tres"));
        System.out.println(invertirPalabras("Hola"));
        System.out.println(invertirPalabras("Java es genial"));
        
    }
}