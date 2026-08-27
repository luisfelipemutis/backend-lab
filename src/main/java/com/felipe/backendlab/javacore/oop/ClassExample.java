package com.felipe.backendlab.javacore.oop;

public class ClassExample {

    public static void main(String[] args) {
        System.out.println("Hello World");
        System.out.println("*** Creación de clase y objetos persona ***");

        Persona persona1 = new Persona("Luis", "Mutis");
        persona1.showPerson();
    }
}


class Persona {

    private String nombre;
    private String apellido;
    private String email;
    private String celular;

    public Persona() {
    }

    public Persona(String nombre, String apellido) {
        this.nombre = nombre;
        this.apellido = apellido;
    }

    public void showPerson() {
        System.out.println("Nombre: " + this.nombre);
        System.out.println("Apellido: " + this.apellido);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
}