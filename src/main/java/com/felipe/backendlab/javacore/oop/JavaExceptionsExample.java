package com.felipe.backendlab.javacore.oop;

public class JavaExceptionsExample {
    public static void main(String[] args) {
        try {
            var result = Aritmetic.divide(10, 1);
            System.out.println("result: " + result);
        } catch (Exception e) {
            System.out.println("result: " + e);
        } finally {
            // El bloque finally se va ejecutar independiente si se ingresa en el catch.
            System.out.println("finally block - The division by zero was verifed");
        }
    }
}

class Aritmetic {
    public static int divide(int numerator, int denominator) {
        if (denominator == 0)
            throw new RuntimeException("denominator cannot be zero");
        return numerator / denominator;
    }
}
