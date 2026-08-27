package com.felipe.backendlab.javacore.oop;

public class StaticAndDynamicContextExId {

    public static void main(String[] args) {
        Person1 persona = new Person1("Luis", "Mutis");
        System.out.println(Person1.getCountPerson());
        Person1 persona2 = new Person1("Andres", "Hurt");
        Person1 persona3 = new Person1("Felipe", "behind");
    }
}

class Person1 {

    private static int countPerson = 0;
    private String name;
    private String lastName;
    private int id;

    public Person1(String name, String lastName) {
        this.name = name;
        this.lastName = lastName;
        // Se incrementa el valor en el contado y después se asigna el valor.
        this.id = ++countPerson;
        showPerson();
    }

    private void showPerson() {
        System.out.println("id: " + this.id + " Name: " + this.name + " lastName: " + this.lastName);
    }

    // Los métodos de una varible de contexto estatico el método también debe ser estatico.
    public static int getCountPerson() {
        return countPerson;
    }

    public static void setCountPerson(int countPerson) {
        Person1.countPerson = countPerson;
    }
}
