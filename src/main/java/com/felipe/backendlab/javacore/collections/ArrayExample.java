package com.felipe.backendlab.javacore.collections;

/*
 *
 * Array
 *
 * Permite almacenar un conjunto de elementos del mismo tipo en una sola variable, en lugar de
 * declarar variables separadas para cada elemento.
 *
 * El tamaño de un array es fijo y no puede cambiarse una vez que se ha creado.
 *
 * */

public class ArrayExample {

    public static void main(String[] args) {
        // Ejemplos de inicializacion
        int[] numbers = {10, 20, 30, 40, 50}; // Inicialización con valores
        String[] names = new String[5]; // Inicialización con tamaño fijo
        String[] cars = new String[]{"Volvo", "BMW", "Ford", "Mazda"}; // Inicialización con valores y tamaño fijo

        // Ejemplo de acceso a elementos
        System.out.println("Primer elemento del array numbers: " + numbers[0]);

        // Longitud de un array
        System.out.println("Longitud del array numbers: " + numbers.length);

        // Iteración sobre un array
        System.out.println("\nIteración con 'regular for':");
        for (int i = 0; i < cars.length; i++) {
            System.out.println("Car " + (i + 1) + ": " + cars[i]);
        }

        // Iteracion con for-each
        System.out.println("\nIteración con for-each:");
        for (String car : cars) {
            System.out.println("Car: " + car);
        }


        System.out.println("\n\nArray multidimensional:");
        // Array multidimensional
        // Array que contiene otros arrays como elementos.

        /*
         * [1, 4, 2
         * 3, 6, 8]
         * */

        // Length para validar el numero de items en cada fila
        int[][] myNumbers = {{1, 4, 2}, {3, 6, 8, 5, 2}};

        System.out.println("Rows: " + myNumbers.length);             // 2
        System.out.println("Cols in row 0: " + myNumbers[0].length); // 3
        System.out.println("Cols in row 1: " + myNumbers[1].length); // 5


        // Recorrer matriz
        System.out.println("\n\nRecorrer matriz con for anidado:");
        for (int row = 0; row < myNumbers.length; row++) {
            for (int col = 0; col < myNumbers[row].length; col++) {
                System.out.println("myNumbers[" + row + "][" + col + "] = " + myNumbers[row][col]);
            }
        }

        System.out.println("\n\nRecorrer matriz con for-each anidado:");
        for (int[] row : myNumbers) {
            for (int num : row) {
                System.out.println(num);
            }
        }

    }

}
