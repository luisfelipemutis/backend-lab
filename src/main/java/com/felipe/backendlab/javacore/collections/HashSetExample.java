package com.felipe.backendlab.javacore.collections;

import java.util.HashSet;
import java.util.Set;

/**
 * HashSet
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
 * Clase concreta: HashSet (Esta clase implementa la interface Set y esta a su vez implementa collection)
 *
 */

public class HashSetExample {

    public static void main(String[] args) {

        Set<String> hashSet = new HashSet<>();

        hashSet.add("Lunes");
        hashSet.add("Martes");
        hashSet.add("Miércoles");
        hashSet.add("Jueves");
        hashSet.add("Viernes");

        System.out.println("Elementos del HashSet:");
        for (Object elemento : hashSet) {
            System.out.println(elemento);
        }

        // Intentar agregar un elemento duplicado
        boolean agregado = hashSet.add("Elemento 3");
        System.out.println("\nIntento de agregar 'Elemento 3' nuevamente: " + (agregado ? "Agregado" : "No agregado"));

        // Remover un elemento
        boolean removido = hashSet.remove("Elemento 2");
        System.out.println("\nIntento de remover 'Elemento 2': " + (removido ? "Removido" : "No encontrado"));

        System.out.println("\nElementos del HashSet después de las operaciones:");
        for (Object elemento : hashSet) {
            System.out.println(elemento);
        }
    }

}
