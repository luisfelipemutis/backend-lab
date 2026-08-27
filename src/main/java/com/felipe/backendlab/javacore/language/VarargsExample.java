package com.felipe.backendlab.javacore.language;

import java.util.Arrays;

/**
 * VARARGS
 * <p>
 * Permite que un método reciba una cantidad variable de argumentos
 * del mismo tipo.
 * <p>
 * Internamente, Java trata los varargs como un array.
 * <p>
 * Sintaxis:
 * <p>
 * tipo... nombre
 * <p>
 * Ejemplo:
 * <p>
 * public static int sum(int... numbers)
 * <p>
 * El compilador permite invocarlo como:
 * <p>
 * sum();
 * sum(1);
 * sum(1, 2);
 * sum(1, 2, 3);
 * <p>
 * Conceptos relacionados:
 * - Arrays
 * - Métodos
 * - Sobrecarga
 * - Generics
 */
public class VarargsExample {

    public static void main(String[] args) {
        showNums(1, 11, 3);
        showSeveralParams("Hola Mundo", 1, 2, 3, 4, 5, 6);
    }

    static void showNums(int... nums) {
        int total = 0;
        for (int num : nums) {
            total += num;
        }
        System.out.println("Total: " + total);
    }

    static void showSeveralParams(String title, int... params) {
        System.out.println("title: " + title);
        System.out.println("params: " + Arrays.toString(params));
    }
}
