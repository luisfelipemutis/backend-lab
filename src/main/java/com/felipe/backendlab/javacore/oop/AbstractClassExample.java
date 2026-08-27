package com.felipe.backendlab.javacore.oop;

/**
 * ABSTRACT CLASS
 *
 * Una abstract class es una clase que no puede ser instanciada directamente
 * y que sirve como base para otras clases mediante herencia.
 *
 * Puede contener:
 * - Atributos.
 * - Constructores.
 * - Métodos concretos con implementación.
 * - Métodos abstractos sin implementación.
 *
 * Sintaxis:
 *
 *     public abstract class Animal {
 *
 *         public abstract void makeSound();
 *
 *         public void sleep() {
 *             System.out.println("Sleeping...");
 *         }
 *     }
 *
 * Una clase hija utiliza extends para heredar de una abstract class:
 *
 *     public class Dog extends Animal {
 *
 *         @Override
 *         public void makeSound() {
 *             System.out.println("Woof");
 *         }
 *     }
 *
 * CARACTERÍSTICAS:
 * - No se puede crear una instancia directamente con new.
 * - Puede tener métodos abstractos y métodos concretos.
 * - Una clase hija concreta debe implementar todos los métodos abstractos
 *   heredados, salvo que la clase hija también sea abstract.
 *
 * CUÁNDO UTILIZAR:
 * - Cuando varias clases comparten comportamiento y estado.
 * - Cuando existe una relación clara de herencia entre las clases.
 * - Cuando queremos proporcionar comportamiento común y obligar a las
 *   clases hijas a implementar determinadas operaciones.
 *
 * CUÁNDO NO UTILIZAR:
 * - Cuando solamente necesitamos definir un contrato sin comportamiento
 *   compartido; en ese caso una interface puede ser más apropiada.
 * - Cuando la relación entre las clases no representa realmente una
 *   relación "is-a".
 * - No utilizar herencia únicamente para reutilizar código.
 *
 * BUENAS PRÁCTICAS:
 * - Utilizar abstract class cuando exista una relación conceptual clara
 *   entre la clase base y sus especializaciones.
 * - Mantener la clase abstracta enfocada en una responsabilidad coherente.
 * - Evitar jerarquías de herencia excesivamente profundas.
 * - Preferir composición cuando la relación de herencia no sea necesaria.
 * - Utilizar @Override al implementar o sobrescribir métodos heredados.
 *
 * CONCEPTOS RELACIONADOS:
 * - Abstraction
 * - Inheritance
 * - Polymorphism
 * - Method Overriding
 * - Interfaces
 * - Composition
 */
public class AbstractClassExample {

    public static void main(String[] args) {
        Animal2 animal = new Perro2();
        animal.makeSound();
        animal = new Gato2();
        animal.makeSound();
    }
}

abstract class Animal2 {

    public abstract void makeSound();

    public void sleep() {
        System.out.println("Sleeping...");
    }
}

class Perro2 extends Animal2 {
    @Override
    public void makeSound() {
        System.out.println("Ladrar...");
    }
}

class Gato2 extends Animal2 {
    @Override
    public void makeSound() {
        System.out.println("Aullar...");
    }
}