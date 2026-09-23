package com.felipe.backendlab.javacore.language;

/*
 * Bloques de inicializacion
 *
 * Los bloques de inicializacion son bloques de codigo que se ejecutan antes del constructor de la clase.
 *
 * Staticos: Se ejecutan una sola vez cuando la clase es cargada por el ClassLoader.
 * Dinamicos: Se ejecutan cada vez que se crea un objeto de la clase.
 *
 * */

public class InitializerBlock {
    public static void main(String[] args) {
        Person1 person1 = new Person1();
        System.out.println("Person1: " + person1);
        System.out.println("-------------------------");
        Person1 person2 = new Person1();
        System.out.println("Person2: " + person2);
    }
}

class Person1 {
    private final int idPerson;
    private static int countPerson;

    static {
        System.out.println("Ejecutando bloque estatico");
        ++Person1.countPerson;
    }

    {
        System.out.println("Ejecutando bloque dinamico");
        this.idPerson = Person1.countPerson++;
    }

    public Person1() {
        System.out.println("Ejecutando constructor");
    }

    @Override
    public String toString() {
        return "Person1{" +
                "idPerson=" + idPerson +
                '}';
    }
}

