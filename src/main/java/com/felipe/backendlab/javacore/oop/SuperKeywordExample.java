package com.felipe.backendlab.javacore.oop;

/**
 * La palabra clave super se usa dentro de una subclase para referirse a la clase padre.
 *
 * Se puede usar para:
 * - llamar al constructor de la clase padre: super()
 * - acceder a un atributo de la clase padre
 * - llamar a un método sobrescrito de la clase padre
 */
public class SuperKeywordExample {
    public static void main(String[] args) {
        DogWithSuper dog = new DogWithSuper("Max", "Golden Retriever");
        dog.showInfo();
        dog.eat();
        dog.sleep();
    }
}

class AnimalBase {
    protected String name;

    AnimalBase(String name) {
        this.name = name;
    }

    public void eat() {
        System.out.println(name + " está comiendo.");
    }

    public void sleep() {
        System.out.println(name + " está durmiendo.");
    }
}

class DogWithSuper extends AnimalBase {
    private String breed;

    DogWithSuper(String name, String breed) {
        super(name);
        this.breed = breed;
    }

    @Override
    public void eat() {
        System.out.println("El perro " + super.name + " come croquetas.");
        super.eat();
    }

    @Override
    public void sleep() {
        System.out.println("El perro " + super.name + " duerme en su cama.");
        super.sleep();
    }

    public void showInfo() {
        System.out.println("Perro: " + super.name + ", raza: " + breed);
    }
}
