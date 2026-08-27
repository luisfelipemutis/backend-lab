package com.felipe.backendlab.javacore.exceptions;

/**
 * JAVA EXCEPTIONS
 *
 * <p>
 * Existen 2 clases de excepciones:
 * 1. Excepción en tiempo de compilación: Errores como por ejemplo no colocar punto y coma, al iniciar la app
 * sale el error y la app no llega a inicializarse.
 * 2. Excepción en tiempo de ejecución: Error que se muestra cuando la app esta ejecutandose, si no se maneja
 * con try catch la app finaliza abrutamente.
 * <p>
 *
 */

public class JavaExceptions {

    public static void main(String[] args) {
        int valor1 = 10, valor2 = 0;
        try {
            var result = valor1 / valor2;
            System.out.println("result: " + result);
        } catch (Exception e) {
            System.out.println("Ocurrio un error: " + e);
        }
    }
}
