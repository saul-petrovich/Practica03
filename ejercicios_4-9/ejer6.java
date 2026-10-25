public class ejer6 {

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

    public static void main(String[] args) {
        System.out.println(eliminarDuplicados("programacion"));
        System.out.println(eliminarDuplicados("mississippi"));
        System.out.println(eliminarDuplicados("aabbcc"));
        System.out.println(eliminarDuplicados("123123456"));
    }
}