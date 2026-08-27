package com.felipe.backendlab.javacore.oop;

// Herencia
public class ClassInheritance {

    public static void main(String[] args) {
        // Ejemplo de herencia
        Animal animal = new Animal();
        animal.eat();
        animal.sleep();

        System.out.println("\nClase hija perro");
        Perro perro = new Perro();
        perro.eat();
        perro.sleep();
        perro.talk();
    }
}

class Animal {
    protected void eat() {
        System.out.println("Como muchas veces al día.");
    }

    protected void sleep() {
        System.out.println("Duermo muchas veces al día");
    }
}

class Perro extends Animal {
    public void talk() {
        System.out.println("Ladrar");
    }

    // Sobreescribir el funcionamineto del método sleep.
    @Override
    protected void sleep() {
        System.out.println("Duermo 15 horas al día.");
        System.out.println("Método clase padre...");
        super.sleep();
    }
}