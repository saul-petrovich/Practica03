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