package com.felipe.backendlab.javacore.oop;

public class ClassPolymorphism {

    // Método polimorfico
    static void makeSound(Animal1 animal) {
        animal.makeSound();
    }

    public static void main(String[] args) {

        // objeto de la clase padre (Animal)
        Animal1 animal1 = new Animal1();
        System.out.println(animal1);

        makeSound(animal1);

        Perro1 perro = new Perro1();
        makeSound(perro);

        Cat1 cat1 = new Cat1();
        makeSound(cat1);
    }
}

class Animal1 {

    protected void makeSound() {
        System.out.println("El animal hace un sonido.");
    }

    @Override
    public String toString() {
        return "Hola aminal...";
    }
}

class Perro1 extends Animal1 {
    @Override
    protected void makeSound() {
        System.out.println("El perro ladra.");
    }
}

class Cat1 extends Animal1 {
    @Override
    protected void makeSound() {
        System.out.println("El cat maulla.");
    }
}