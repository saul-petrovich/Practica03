public class ejer9 {

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

    public static void main(String[] args) {
        System.out.println(validar("{a[(b+c)]}"));
        System.out.println(validar("{[()]}"));
        System.out.println(validar("a+b"));
        System.out.println(validar("([)]"));
        System.out.println(validar("((a+b)"));
    }
}