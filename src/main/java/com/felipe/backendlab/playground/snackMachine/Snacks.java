package com.felipe.backendlab.playground.snackmachine;

import java.util.ArrayList;
import java.util.List;

public class Snacks {

    private static final List<Snack> snacks;

    // Bloque static inicializador.
    static {
        snacks = new ArrayList<>(); // Es como un constructor para inicializar la lista...
        snacks.add(new Snack("Papas", 80));
        snacks.add(new Snack("Refresco", 50));
        snacks.add(new Snack("Sandwich", 120));
    }

    public static void addSnacks(Snack s) {
        snacks.add(s);
    }

    public static void showSnacks() {
        StringBuilder strSnacks = new StringBuilder();
        if (snacks != null) {
            for (Snack s : snacks) {
                strSnacks.append(s.toString()).append("\n");
            }
        } else {
            strSnacks.append("No hay snacks disponibles\n");
        }
        System.out.println("--- Snacks en el inventario ---");
        System.out.println(strSnacks);
    }

    public static List<Snack> getSnacks() {
        return snacks;
    }
}
