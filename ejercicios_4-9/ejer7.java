public class ejer7 {

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

    public static void main(String[] args) {
        System.out.println(reemplazar("Java es Java", "Java", "Kotlin"));
        System.out.println(reemplazar("aaaaa", "aa", "b"));
        System.out.println(reemplazar("uno dos uno", "uno", "tres"));
    }
}