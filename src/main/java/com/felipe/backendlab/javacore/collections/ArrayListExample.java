package com.felipe.backendlab.javacore.collections;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Array list
 * <p>
 * Colección - representa un grupo de objetos.
 * Algunas permiten elementos duplicados otras no.
 * Algunas permiten un orden otras no.
 *
 * <p>
 * Caracteristicas:
 * Permite elementos duplicados
 * Maneja un orden
 * <p>
 * Clase concreta: ArrayList (Esta clase implementa la interface List)
 *
 */

public class ArrayListExample {
    public static void main(String[] args) {
        List list = new ArrayList();
        list.add("Monday");
        list.add("Tuesday");
        list.add("Wednesday");
        list.add("Thursday");
        list.add("Friday");
        list.add("Saturday");
        list.add("Sunday");

        for (Object obj : list) {
            System.out.println("Day of the week: " + obj);
        }

        // Manejo de genericos - fijando tipo de dato a la lista.
        System.out.println("\n\nlista de días de la semana dos.");
        List<String> list2 = new ArrayList<>();
        list2.add("Monday");
        list2.add("Tuesday");
        list2.add("Wednesday");

        for (int i = 0; i < list2.size(); i++) {
            System.out.println("Day of the week: " + list2.get(i));
        }

        // Funciones lambda (Función anonima de un código muy compacto).
        // se recomienda que el contenido sea muy compacto..
        System.out.println("\n\nFor each con función lambda.");
        list.forEach(element -> {
            System.out.println("Day of the week: " + element);
        });


        // Objetivo de la programación funcional y la expresión lambda: es la simplificación del código y sea mas legible.
        System.out.println("\n\nFor each mas simplificada utilizando método :: de referencia.");
        list.forEach(System.out::println);

        // Creación de list con método asList.
        List<String> list3 = Arrays.asList("1", "2", "3");
        list3.forEach(System.out::println);
    }
}
