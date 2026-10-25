public class ejer4 {

    public static String comprimir(String texto) {

        if (texto.length() == 0) {
            return "";
        }

        StringBuilder sb = new StringBuilder();
        int inicio = 0;

        while (inicio < texto.length()) {
            int j = inicio + 1;

            while (j < texto.length() && texto.charAt(inicio) == texto.charAt(j)) {
                j++;
            }

            sb.append(texto.charAt(inicio));
            sb.append(j - inicio);

            inicio = j;
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(comprimir("aaabbc"));
        System.out.println(comprimir("xxxxyzz"));
        System.out.println(comprimir("abcd"));
        System.out.println(comprimir(""));
    }
}