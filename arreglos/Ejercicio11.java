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