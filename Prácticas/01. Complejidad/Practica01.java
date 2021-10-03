import java.util.Arrays;
import java.util.HashSet;

/**
 * Práctica 1 del curso de Estructuras de Datos.
 *
 * @author Emmanuel Cruz Hernández.
 * @version 2.0 Septiembre 2021.
 * @since Laboratorio de Estructuras de Datos 2022-1.
 */


// Alumno Edgar Gante Araujo
// Cuenta: 105001524

public class Practica01 {

    /**
     * Hace la mezcla de dos arreglos ordenados desde la primera posición hasta
     * una posición límite
     *
     * @param array1 el primer arreglo a mezlar
     * @param n      el límite de mezcla del primer arreglo
     * @param array2 el segundo arreglo a mezclar
     * @param m      el límite de mezcla del segundo arreglo.
     * @return un arreglo ordenado de longitud m+n con la mezcla definida.
     */
    public static int[] mergeSortedArray(int[] array1, int n, int[] array2, int m) {
        if (n > array1.length || m > array2.length)
            throw new RuntimeException("Límites no válidos");

        int[] result = new int[n + m];
        int pointer;
        for (pointer = 0; pointer < n; pointer++)
            result[pointer] = array1[pointer];
        for (int i = 0; i < m; i++, pointer++)
            result[pointer] = array2[i];

        // Ordenamiento del arreglo result
        for (int j = 0; j < result.length - 1; j++) {
            for (int k = j + 1; k < result.length; k++) {
                if (result[k] < result[j]) {
                    int aux = result[k];
                    result[k] = result[j];
                    result[j] = aux;
                }
            }
        }

        return result;
    }

    public static int[] mergeSortedArrayOptimizado(int[] array1, int n, int[] array2, int m) {
        if (n > array1.length || m > array2.length)
            throw new RuntimeException("Límites no válidos");

        int[] result = new int[n + m];
        int pointer;
        for (pointer = 0; pointer < n; pointer++)
            result[pointer] = array1[pointer];
        for (int i = 0; i < m; i++, pointer++)
            result[pointer] = array2[i];

        // Ordenamiento del arreglo result
        int i = 0, j = 0, k = 0;

        while (i < n && j < m)
            result[k++] = array1[i] < array2[j] ? array1[i++] : array2[j++];

        while (i < n)
            result[k++] = array1[i++];

        while (j < m)
            result[k++] = array2[j++];

        return result;
    }

    /**
     * Verifica si un tablero contiene los números desde 0 hasta n-1 en cada fila y cada columna.
     *
     * @param board el tablero de nxn que contiene elementos dentro del rango [0, n-1].
     * @return true si el tablero contiene los números desde 0 hasta n-1 en cada fila y columna,
     * false en otro caso.
     */
    public static boolean isValidBoard(int[][] board) {
        int length = board.length;
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                boolean verificador = false;
                // Verifica sobre las filas
                for (int k = 0; k < length; k++) {
                    if (board[i][k] == j) {
                        verificador = true;
                        break;
                    }
                }
                if (!verificador) {
                    return false;
                }
                verificador = false;
                // Verifica sobre las columnas
                for (int k = 0; k < length; k++) {
                    if (board[k][i] == j) {
                        verificador = true;
                        break;
                    }
                }
                if (!verificador) {
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean isValidBoardOptimizado(int[][] board) {
        for (int i = 0; i < board.length; i++) {
            HashSet<Integer> set = new HashSet<>();
            for (int[] ints : board) {
                if (set.contains(ints[i])) return false;
                set.add(ints[i]);
            }
        }
        return true;
    }

    /**
     * Rota position cantidad de veces los elementos de un arreglo
     * hacia el vecino izquierdo.
     *
     * @param num      el arreglo de enteros a rotar.
     * @param position la cantidad de espacios a rotar.
     */
    public static void rotateArray(int[] num, int position) {
        for (int i = 0; i < position; i++) {
            int aux = num[0];
            for (int j = 0; j < num.length - 1; j++) {
                num[j] = num[j + 1];
            }
            num[num.length - 1] = aux;
        }
    }

    public static void rotateArrayOptimizado(int[] num, int position) {
        int[] result = new int[num.length];
        int c = 0;
        for (int i = position; i < num.length; i++) {
            result[c++] = num[i];
        }

        for (int i = 0; i < position; i++) {
            result[c++] = num[i];
        }

        for (int i = 0; i < result.length; i++) {
            num[i] = result[i];
        }
    }

    public static void main(String[] args) {

        String directorio1 = "Examples/ArrayExamples/";
        String directorio2 = "Examples/BoardExamples/";

        // EJEMPLOS DE ACTIVIDAD 1
        System.out.println("\nEJEMPLOS DE ACTIVIDAD 1\n");
        int[] arrayA1 = ArrayReader.readArray(directorio1 + "ArrayA1.txt");
        int[] arrayA2 = ArrayReader.readArray(directorio1 + "ArrayA2.txt");

        // Test algoritmo 1
        long startTime = System.nanoTime();
        int[] resultA = mergeSortedArray(arrayA1, 500, arrayA2, 700);
        // System.out.println("Resultado A: "+Arrays.toString(resultA));
        long endTime = System.nanoTime();
        System.out.println("Tiempo:  " + (endTime - startTime) + " nanosegundos.");
        // Test algoritmo 2
        long startTimeA2 = System.nanoTime();
        int[] resultA2 = mergeSortedArrayOptimizado(arrayA1, 500, arrayA2, 700);
        // System.out.println("Resultado Optimizado: "+Arrays.toString(resultA2));
        long endTimeA2 = System.nanoTime();
        System.out.println("Tiempo:  " + (endTimeA2 - startTimeA2) + " nanosegundos.");

        int[] arrayB1 = ArrayReader.readArray(directorio1 + "ArrayB1.txt");
        int[] arrayB2 = ArrayReader.readArray(directorio1 + "ArrayB2.txt");

        // Test algoritmo 1
        long startTimeB1 = System.nanoTime();
        int[] resultB = mergeSortedArray(arrayB1, 2000, arrayB2, 3500);
        // System.out.println("Resultado B: "+Arrays.toString(resultB));
        long endTimeB1 = System.nanoTime();
        System.out.println("Tiempo:  " + (endTimeB1 - startTimeB1) + " nanosegundos.");

        // Test algoritmo 2
        long startTimeB2 = System.nanoTime();
        int[] resultB2 = mergeSortedArrayOptimizado(arrayB1, 2000, arrayB2, 3500);
        // System.out.println("Resultado Optimizado: "+Arrays.toString(resultB2));
        long endTimeB2 = System.nanoTime();
        System.out.println("Tiempo:  " + (endTimeB2 - startTimeB2) + " nanosegundos.");

        int[] arrayC1 = ArrayReader.readArray(directorio1 + "ArrayC1.txt");
        int[] arrayC2 = ArrayReader.readArray(directorio1 + "ArrayC2.txt");

        // Test algoritmo 1
        long startTimeC1 = System.nanoTime();
        int[] resultC = mergeSortedArray(arrayC1, 4000, arrayC2, 4000);
        long endTimeC1 = System.nanoTime();
        // System.out.println("Resultado C: "+Arrays.toString(resultC));
        System.out.println("Tiempo:  " + (endTimeC1 - startTimeC1) + " nanosegundos.");

        // Test algoritmo 2
        long startTimeC2 = System.nanoTime();
        int[] resultC1 = mergeSortedArrayOptimizado(arrayC1, 4000, arrayC2, 4000);
        long endTimeC2 = System.nanoTime();
        //System.out.println("Resultado Optimizado: "+Arrays.toString(resultC1));
        System.out.println("Tiempo:  " + (endTimeC2 - startTimeC2) + " nanosegundos.");

        int[] arrayD1 = ArrayReader.readArray(directorio1 + "ArrayD1.txt");
        int[] arrayD2 = ArrayReader.readArray(directorio1 + "ArrayD2.txt");
        // Test algoritmo 1
        long startTimeD1 = System.nanoTime();
        int[] resultD1 = mergeSortedArray(arrayD1, 7000, arrayD2, 8000);
        long endTimeD1 = System.nanoTime();
        // System.out.println("Resultado D: "+Arrays.toString(resultD1));
        System.out.println("Tiempo D:  " + (endTimeD1 - startTimeD1) + " nanosegundos.");
        // Test algoritmo 1
        long startTimeD2 = System.nanoTime();
        int[] resultD2 = mergeSortedArrayOptimizado(arrayD1, 7000, arrayD2, 8000);
        long endTimeD2 = System.nanoTime();
        //System.out.println("Resultado Optimizado: "+Arrays.toString(resultD1));
        System.out.println("Tiempo D Optimizado:  " + (endTimeD2 - startTimeD2) + " nanosegundos.");

        int[] arrayE1 = ArrayReader.readArray(directorio1 + "ArrayE1.txt");
        int[] arrayE2 = ArrayReader.readArray(directorio1 + "ArrayE2.txt");
        // Test algoritmo 1
        long startTimeE1 = System.nanoTime();
        int[] resultE1 = mergeSortedArray(arrayE1, 15000, arrayE2, 19000);
        long endTimeE1 = System.nanoTime();
        System.out.println("Tiempo E:  " + (endTimeE1 - startTimeE1) + " nanosegundos.");

        // Test algoritmo 2
        long startTimeE2 = System.nanoTime();
        int[] resultE2 = mergeSortedArrayOptimizado(arrayE1, 15000, arrayE2, 19000);
        long endTimeE2 = System.nanoTime();
        System.out.println("Tiempo E Optimizado:  " + (endTimeE2 - startTimeE2) + " nanosegundos.");

        int[] arrayF1 = ArrayReader.readArray(directorio1 + "ArrayF1.txt");
        int[] arrayF2 = ArrayReader.readArray(directorio1 + "ArrayF2.txt");
        // Test algoritmo 1
        long startTimeF1 = System.nanoTime();
        int[] resultF1 = mergeSortedArray(arrayF1, 30000, arrayF2, 25000);
        long endTimeF1 = System.nanoTime();
        System.out.println("Tiempo F:  " + (endTimeF1 - startTimeF1) + " nanosegundos.");

        // Test algoritmo 2
        long startTimeF2 = System.nanoTime();
        int[] resultF2 = mergeSortedArrayOptimizado(arrayF1, 30000, arrayF2, 25000);
        long endTimeF2 = System.nanoTime();
        System.out.println("Tiempo F Optimizado:  " + (endTimeF2 - startTimeF2) + " nanosegundos.");

        // EJEMPLOS DE ACTIVIDAD 2
        System.out.println("\nEJEMPLOS DE ACTIVIDAD 2\n");
        // Test algoritmo 1
        int[][] boardA = ArrayReader.readMatrix(directorio2 + "BoardA.txt");
        long startTimeBoardA1 = System.nanoTime();
        boolean boardResultA1 = isValidBoard(boardA);
        long endTimeBoardA1 = System.nanoTime();
        System.out.println("El tablero A es válido: " + boardResultA1);
        System.out.println("Tiempo tablero A:  " + (endTimeBoardA1 - startTimeBoardA1) + " nanosegundos.");
        // Test algoritmo 2
        long startTimeBoardA2 = System.nanoTime();
        boolean boardResultA2 = isValidBoardOptimizado(boardA);
        long endTimeBoardA2 = System.nanoTime();
        System.out.println("El tablero A es válido: " + boardResultA2);
        System.out.println("Tiempo tablero A optimizado:  " + (endTimeBoardA2 - startTimeBoardA2) + " nanosegundos.");

        int[][] boardB = ArrayReader.readMatrix(directorio2 + "BoardB.txt");
        // Test algoritmo 1
        long startTimeBoardB1 = System.nanoTime();
        boolean boardResultB1 = isValidBoard(boardB);
        System.out.println("El tablero B es válido: " + boardResultB1);
        long endTimeBoardB1 = System.nanoTime();
        System.out.println("Tiempo tablero B:  " + (endTimeBoardB1 - startTimeBoardB1) + " nanosegundos.");
        // Test algoritmo 2
        long startTimeBoardB2 = System.nanoTime();
        boolean boardResultB2 = isValidBoardOptimizado(boardB);
        long endTimeBoardB2 = System.nanoTime();
        System.out.println("El tablero B es válido: " + boardResultB2);
        System.out.println("Tiempo tablero B optimizado:  " + (endTimeBoardB2 - startTimeBoardB2) + " nanosegundos.");

        int[][] boardC = ArrayReader.readMatrix(directorio2 + "BoardC.txt");
        // Test algoritmo 1
        long startTimeBoardC1 = System.nanoTime();
        boolean boardResultC = isValidBoard(boardC);
        long endTimeBoardC1 = System.nanoTime();
        System.out.println("El tablero C es válido: " + boardResultC);
        System.out.println("Tiempo tablero C:  " + (endTimeBoardC1 - startTimeBoardC1) + " nanosegundos.");

        // Test algoritmo 2
        long startTimeBoardC2 = System.nanoTime();
        boolean boardResultC2 = isValidBoardOptimizado(boardC);
        long endTimeBoardC2 = System.nanoTime();
        System.out.println("El tablero C es válido: " + boardResultC2);
        System.out.println("Tiempo tablero C optimizado:  " + (endTimeBoardC2 - startTimeBoardC2) + " nanosegundos.");

        int[][] boardD = ArrayReader.readMatrix(directorio2 + "BoardD.txt");

        // Test algoritmo 1
        long startTimeBoardD1 = System.nanoTime();
        boolean boardResultD = isValidBoard(boardD);
        long endTimeBoardD1 = System.nanoTime();
        System.out.println("El tablero D es válido: " + boardResultD);
        System.out.println("Tiempo tablero D:  " + (endTimeBoardD1 - startTimeBoardD1) + " nanosegundos.");

        // Test algoritmo 2
        long startTimeBoardD2 = System.nanoTime();
        boolean boardResultD2 = isValidBoardOptimizado(boardD);
        long endTimeBoardD2 = System.nanoTime();
        System.out.println("El tablero D es válido: " + boardResultD2);
        System.out.println("Tiempo tablero D optimizado:  " + (endTimeBoardD2 - startTimeBoardD2) + " nanosegundos.");

        int[][] boardE = ArrayReader.readMatrix(directorio2 + "BoardE.txt");

        // Test algoritmo 1
        long startTimeBoardE1 = System.nanoTime();
        boolean boardResultE = isValidBoard(boardE);
        long endTimeBoardE1 = System.nanoTime();
        System.out.println("El tablero E es válido: " + boardResultE);
        System.out.println("Tiempo tablero E:  " + (endTimeBoardE1 - startTimeBoardE1) + " nanosegundos.");

        // Test algoritmo 2
        long startTimeBoardE2 = System.nanoTime();
        boolean boardResultE2 = isValidBoardOptimizado(boardE);
        long endTimeBoardE2 = System.nanoTime();
        System.out.println("El tablero E es válido: " + boardResultE2);
        System.out.println("Tiempo tablero E optimizado:  " + (endTimeBoardE2 - startTimeBoardE2) + " nanosegundos.");

        int[][] boardF = ArrayReader.readMatrix(directorio2 + "BoardF.txt");
        // Test algoritmo 1
        long startTimeBoardF1 = System.nanoTime();
        boolean boardResultF1 = isValidBoard(boardF);
        long endTimeBoardF1 = System.nanoTime();
        System.out.println("El tablero F es válido: " + boardResultF1);
        System.out.println("Tiempo tablero F:  " + (endTimeBoardF1 - startTimeBoardF1) + " nanosegundos.");

        // Test algoritmo 2
        long startTimeBoardF2 = System.nanoTime();
        boolean boardResultF2 = isValidBoardOptimizado(boardF);
        long endTimeBoardF2 = System.nanoTime();
        System.out.println("El tablero F es válido: " + boardResultF2);
        System.out.println("Tiempo tablero F optimizado:  " + (endTimeBoardF2 - startTimeBoardF2) + " nanosegundos.");

        // EJEMPLOS DE ACTIVIDAD 3
        System.out.println("\nEJEMPLOS DE ACTIVIDAD 3\n");
        // Array A
        long startTimeBoardRA1 = System.nanoTime();
        rotateArray(arrayA1, 500);
        long endTimeBoardRA1 = System.nanoTime();
        System.out.println("Tiempo rotación Array A:  " + (endTimeBoardRA1 - startTimeBoardRA1) + " nanosegundos.");
        long startTimeBoardRA2 = System.nanoTime();
        rotateArrayOptimizado(arrayA1, 500);
        long endTimeBoardRA2 = System.nanoTime();
        System.out.println("Tiempo rotación Array A optimizado:  " + (endTimeBoardRA2 - startTimeBoardRA2) + " nanosegundos.");

        // Array B
        long startTimeBoardRB1 = System.nanoTime();
        rotateArray(arrayB1, 1000);
        long endTimeBoardRB1 = System.nanoTime();
        System.out.println("Tiempo rotación Array B:  " + (endTimeBoardRB1 - startTimeBoardRB1) + " nanosegundos.");

        long startTimeBoardRB2 = System.nanoTime();
        rotateArrayOptimizado(arrayB1, 1000);
        long endTimeBoardRB2 = System.nanoTime();
        System.out.println("Tiempo rotación Array B optimizado:  " + (endTimeBoardRB2 - startTimeBoardRB2) + " nanosegundos.");

        // Array C
        long startTimeBoardRC1 = System.nanoTime();
        rotateArray(arrayC1, 2000);
        long endTimeBoardRC1 = System.nanoTime();
        System.out.println("Tiempo rotación Array C:  " + (endTimeBoardRC1 - startTimeBoardRC1) + " nanosegundos.");

        long startTimeBoardRC2 = System.nanoTime();
        rotateArrayOptimizado(arrayC1, 2000);
        long endTimeBoardRC2 = System.nanoTime();
        System.out.println("Tiempo rotación Array C optimizado:  " + (endTimeBoardRC2 - startTimeBoardRC2) + " nanosegundos.");

        // Array D
        long startTimeBoardRD1 = System.nanoTime();
        rotateArray(arrayD1, 3000);
        long endTimeBoardRD1 = System.nanoTime();
        System.out.println("Tiempo rotación Array D:  " + (endTimeBoardRD1 - startTimeBoardRD1) + " nanosegundos.");

        long startTimeBoardRD2 = System.nanoTime();
        rotateArrayOptimizado(arrayD1, 3000);
        long endTimeBoardRD2 = System.nanoTime();
        System.out.println("Tiempo rotación Array D optimizado:  " + (endTimeBoardRD2 - startTimeBoardRD2) + " nanosegundos.");

        // Array E
        long startTimeBoardRE1 = System.nanoTime();
        rotateArray(arrayE1, 10000);
        long endTimeBoardRE1 = System.nanoTime();
        System.out.println("Tiempo rotación Array E:  " + (endTimeBoardRE1 - startTimeBoardRE1) + " nanosegundos.");

        long startTimeBoardRE2 = System.nanoTime();
        rotateArrayOptimizado(arrayE1, 10000);
        long endTimeBoardRE2 = System.nanoTime();
        System.out.println("Tiempo rotación Array E optimizado:  " + (endTimeBoardRE2 - startTimeBoardRE2) + " nanosegundos.");

        // Array F
        long startTimeBoardRF1 = System.nanoTime();
        rotateArray(arrayF1, 20000);
        long endTimeBoardRF1 = System.nanoTime();
        System.out.println("Tiempo rotación Array F:  " + (endTimeBoardRF1 - startTimeBoardRF1) + " nanosegundos.");

        long startTimeBoardRF2 = System.nanoTime();
        rotateArrayOptimizado(arrayF1, 20000);
        long endTimeBoardRF2 = System.nanoTime();
        System.out.println("Tiempo rotación Array F optimizado:  " + (endTimeBoardRF2 - startTimeBoardRF2) + " nanosegundos.");

        System.out.println("Arreglo A1 rotado 5 veces: " + Arrays.toString(arrayA1));
        System.out.println("Arreglo B1 rotado 0 veces: " + Arrays.toString(arrayB1));
        System.out.println("Arreglo C1 rotado 6 veces: " + Arrays.toString(arrayC1));

        System.out.println("\n\nFIN DE EJEMPLOS\n");
    }
}