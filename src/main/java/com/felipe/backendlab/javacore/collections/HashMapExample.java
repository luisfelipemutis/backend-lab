package com.felipe.backendlab.javacore.collections;

import java.util.HashMap;
import java.util.Map;

/**
 * HashMap
 * <p>
 * Colección - representa un grupo de objetos.
 * Algunas permiten elementos duplicados otras no.
 * Algunas permiten un orden otras no.
 *
 * <p>
 * Caracteristicas:
 * Diccionario
 * LLave - valor
 * No tiene un orden definido
 * No permite valores duplicados
 *
 * <p>
 * Clase concreta: HashMap (Esta clase implementa la interface map)
 *
 */

public class HashMapExample {
    public static void main(String[] args) {
        Map<String, String> dictionary = new HashMap<>();
        dictionary.put("Nombre", "Forlan");
        dictionary.put("apellido", "Male");
        dictionary.put("edad", "31");
        dictionary.put("edad", "31"); // No se guardan valores duplicados.

        System.out.println("Valores del mapa: ");
        dictionary.entrySet().forEach(System.out::println);

        dictionary.put("edad", "35"); // Se modifica el valor de la llave existente
        dictionary.remove("edad");
        System.out.println("\nValores del mapa modificado: ");
        dictionary.entrySet().forEach(System.out::println);

        // Iterar sobre elementos del mapa por separado.
        System.out.println("\nIterando los elmentos (llave/valor)");
        dictionary.forEach((key, value) -> System.out.println(key + ": " + value));
    }
}
