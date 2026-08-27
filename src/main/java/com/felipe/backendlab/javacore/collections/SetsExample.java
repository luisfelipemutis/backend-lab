package com.felipe.backendlab.javacore.collections;

import java.util.Set;
import java.util.TreeSet;

/**
 * Set
 * <p>
 * Colección - representa un grupo de objetos.
 * Algunas permiten elementos duplicados otras no.
 * Algunas permiten un orden otras no.
 *
 * <p>
 * Caracteristicas:
 * No permite elementos duplicados
 * No maneja ordenamiento
 * <p>
 * Clase concreta: TreeSet (Esta clase implementa la interface Set y esta a su vez implementa collection)
 * <p>
 * Para este ejemplo al utilizar esta clase concreta, si permite el ordenamiento, pero existen otras clases que implmentan a set y no tienen orden.
 *
 */

public class SetsExample {
    public static void main(String[] args) {
        Set<String> conjunto = new TreeSet<>();
        conjunto.add("Carlos");
        conjunto.add("Carlos");
        conjunto.add("Carlos");

        conjunto.add("Andres");
        conjunto.add("Victoria");
        conjunto.add("Becerra");

        System.out.println("Elementos del set");
        conjunto.forEach(System.out::println);

        //Remover elemento
        conjunto.remove("Andres");
        System.out.println("\nNuevos elementos del set");
        conjunto.forEach(System.out::println);
    }
}
