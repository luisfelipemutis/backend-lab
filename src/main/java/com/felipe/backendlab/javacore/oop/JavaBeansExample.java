package com.felipe.backendlab.javacore.oop;

import java.io.Serializable;

/**
 * JAVA BEANS
 *
 * <p>
 * Clase normal que debe cumplir un estandar.
 * <p>
 * 1. Debe tener 1 constructor publico y vació. (Puede tener mas, pero si o si uno vació y publico)
 * 2. Debe cumplir con el concepto de encapsulamiento, atributos privados y métodos accesores para obtener y editar.
 * 3. Debe implementar la interface serializable - No tiene ningún método - Permite que los objetos sean serializados a disco o red.
 *
 */

public class JavaBeansExample {
    public static void main(String[] args) {
        UsuarioBean user = new UsuarioBean();
        user.setName("Felipe");
        user.setLastName("Mutis");

        Usuario.insertUser(user);
    }


}

class UsuarioBean implements Serializable {
    private String name;
    private String lastName;

    public UsuarioBean() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}

class Usuario {

    // La clase JavaBean se puede utilizar para evitar una mala practica, compartiendo
    // solo el objeto y después pasando los parametros a la consulta por los métodos accesores.
    public static void insertUser(UsuarioBean user) {
        String sql = "insert into usuario values (nombre, apellido) values ( " + user.getName() + ", " + user.getLastName() + ")";
        System.out.println("Usuario insertado con exito: " + sql);
    }
}