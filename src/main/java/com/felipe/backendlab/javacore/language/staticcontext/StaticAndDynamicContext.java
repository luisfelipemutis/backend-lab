package com.felipe.backendlab.javacore.language.staticcontext;

public class StaticAndDynamicContext {

    /*
     *
     * Contexto estatico: pertenece a la clase, se crea una sola vez y se comparte entre todos los objetos de la clase.
     *  No puede acceder a variables de contexto dinamico, ya que no sabe a que objeto se refiere.
     *
     * Contexto dinamico: pertenece al objeto, se crea una vez por cada objeto y no se comparte entre los objetos de la clase.
     *  Puede acceder a variables de contexto estatico, ya que estas pertenecen a la clase y no al objeto.
     *
     * */

    public static void main(String[] args) {
        // Creación de objeto persona.
        Person persona = new Person("Luis", "Mutis");
        System.out.println("Variable estatica: " + Person.count);
        Person persona2 = new Person("Luis2", "Mutis2");

        // desde el contexto dinamico se puede acceder al contexto estatico..
        // Acceder a travez del objeto no es una buena practica.
        System.out.println("Variable estatica2: " + persona2.count);
    }
}

class Person {

    // Count pertenece al contexto estatico
    // Se crea a nivel de clase
    static int count = 0;

    // Estos atributos pertenecen al contexto dinamico
    // Se crean a nivel de objeto
    private String name;
    private String lastName;

    public Person(String name, String lastName) {
        this.name = name;
        this.lastName = lastName;
        count++;
    }
}