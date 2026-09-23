package com.felipe.backendlab.javacore.language;


/*
 * ENUMS (Enumeraciones)
 *
 * Clase especial que representa un grupo de constantes, permite definir un conjunto de constantes con nombres.
 *
 * Se puede agregar un constructor, este sera privado y se ejecutara al momento de crear cada constante.
 *
 * Ejemplo:
 *
 * enum Color {
 *     ROJO,
 *     VERDE,
 *     AZUL
 * }
 *
 * Conceptos relacionados:
 * - Clases
 * - Objetos
 */

public class EnumsExample {
    public static void main(String[] args) {
        for (Level item : Level.values()) {
            System.out.println(item + " - " + item.getDescription());
        }
    }
}

enum Level {
    // Cada constante tendra su descripcion, que se pasara al constructor de la enumeracion.
    LOW("Low level"),
    MEDIUM("Medium level"),
    HIGH("High level");

    private final String description;

    // Constructor privado, se ejecuta al momento de crear cada constante.
    Level(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
