package com.felipe.backendlab.javacore.controlflow;

/**
 * FOR
 * <p>
 * El for es una estructura de control utilizada para repetir un bloque de código
 * mientras se cumpla una condición.
 * <p>
 * En Java existen 2 formas principales de for:
 * <p>
 * 1. For tradicional
 * <p>
 * for (inicialización; condición; actualización) {
 * // código
 * }
 * <p>
 * Se utiliza principalmente cuando necesitamos controlar explícitamente
 * el índice, la condición de terminación o el incremento/decremento.
 * <p>
 * Ejemplo:
 * <p>
 * for (int i = 0; i < 10; i++) {
 * System.out.println(i);
 * }
 * <p>
 * 2. Enhanced for / for-each
 * <p>
 * for (tipo variable : colección) {
 * // código
 * }
 * <p>
 * Se utiliza para recorrer arrays o elementos de objetos Iterable cuando
 * no necesitamos controlar directamente el índice.
 * <p>
 * Ejemplo:
 * <p>
 * for (String name : names) {
 * System.out.println(name);
 * }
 * <p>
 */

public class ForExample {

    public static void main(String[] args) {
        int[] ages = {11, 12, 13, 14, 15};

        for (int i = 0; i < ages.length; i++) {
            System.out.println("age1: " + ages[i]);
        }
        System.out.println("\n");
        for (int age : ages) {
            System.out.println("age2: " + age);
        }

        for (; ; ) {
            System.out.println("Ciclo infinito");
            break;
        }
    }
}
