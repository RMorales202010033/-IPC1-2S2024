package clase.pkg2;

/**
 *
 * @author Rodolfo Morales - 202010033
 */
public class Clase2 {

    public static void main(String[] args) {
        // ARREGLOS UNIDIMENSIONALE
        System.out.println("-------------------- VECTORES -----------------");
        // Creaci[on arreglo unidimensional de numeros enteros con 8 elementos
        int[] notas = new int[8]; // [0,0,0,0,0,0,0,0]
        int index = 0;
//        for(int value : notas){
//            System.out.println("El valor en el indice " + index + " es: " + value);
//            index++;
//        }
        // Visualizar el contenido del arreglo unidimensional
        // nombre_de_nuestro_vector.length -> La funcion length nos retornara el largo de nuestro vector (En este caso seria 8)
        for (int i = 0; i < notas.length; i++) {
            System.out.println("El valor en el indice " + i + " es: " + notas[i]);
        }
        System.out.println("---------------------------------------------");
        double[] promedios = new double[10];
        // Visualizar el contenido del arreglo unidimensional con un for-each
        for (double value : promedios) {
            System.out.println("El valor en el indice " + index + " es: " + value);
            index++;
        }
        // Asignacion de valores a los elementos del arreglo
        for (int i = 0; i < 10; i++) {
            promedios[i] = index + 10;
            index += 5;
        }
        System.out.println("---------------------------------------------");
        index = 0;
        for (double value : promedios) {
            System.out.println("El valor en el indice " + index + " es: " + value);
            index++;
        }
        System.out.println("---------------------------------------------");
        index = 0;
        promedios[0] = 1000.0;
        // Visualizar valores del vector usando for-each
        for (double value : promedios) {
            System.out.println("El valor en el indice " + index + " es: " + value);
            index++;
        }
        System.out.println("---------------------------------------------");
        index = 0;
        // Otra forma de declarar un arreglo unidimensional
        char[] letras = {'a', 'b', 'c', 'd', 'e'};
        for (char letra : letras) {
            System.out.println("El valor en el indice " + index + " es: " + letra);
            index++;
        }

        // ARREGLOS BIDIMIENSIONALES
        System.out.println("-------------------- MATRICES -----------------");
        // Creacion matriz 2x2
        int[][] matriz = new int[2][2];
        // Asignar valores a los elementos de la matriz usando bucles for
        int contador = 1;
        // El primer for nos sirve para el manejo de filas
        // El segundo for nos sirve para el manejo de columnas
        for (int i = 0; i < matriz.length; i++) { // matriz.length -> 2 filas
            for (int j = 0; j < matriz[i].length; j++) { // matriz[i].length -> 2 columnas
                matriz[i][j] = contador;
                contador++;
            }
        }

        System.out.println("-------------------- Insercion por Filas -> Columnas -----------------");
        // Imprimir matriz usando for-each
        for (int[] fila : matriz) {
            for (int columna : fila) {
                System.out.print(columna + " | ");
            }
            System.out.println("");
        }
        /*
            [
                [1,2], // fila 0
                [3,4], // fila 1
            ]
         */

        System.out.println("-------------------- Insercion por Columnas -> Filas -----------------");
        int[][] matriz2 = new int[2][2];
        int contador2 = 1;
        for (int i = 0; i < matriz2[0].length; i++) {
            for (int j = 0; j < matriz2.length; j++) {
                matriz2[j][i] = contador2;
                contador2++;
            }
        }
        /*
            [
                [1,3], // fila 0
                [2,4], // fila 1
            ]
         */
        for (int[] fila : matriz2) {
            for (int columna : fila) {
                System.out.print(columna + " | ");
            }
            System.out.println("");
        }

        int[][] matriz3 = new int[10][5];
        int contador3 = 0;
        for (int i = 0; i < matriz3.length; i++) {
            for (int j = 0; j < matriz3[i].length; j++) {
                matriz3[i][j] = contador3;
                System.out.print(matriz3[i][j] + " | ");
                contador3++;
            }
            System.out.println("");
        }

    }

}
