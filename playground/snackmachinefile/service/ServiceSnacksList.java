package com.felipe.backendlab.playground.snackmachinefile.service;

import java.util.ArrayList;
import java.util.List;

import com.felipe.backendlab.playground.snackmachinefile.domain.Snack;

public class ServiceSnacksList implements IServiceSnacks {

    private static final List<Snack> snacks;

    // Bloque static inicializador.
    static {
        snacks = new ArrayList<>(); // Es como un constructor para inicializar la lista...
        snacks.add(new Snack("Papas", 80));
        snacks.add(new Snack("Refresco", 50));
        snacks.add(new Snack("Sandwich", 120));
    }

    public void addSnacks(Snack s) {
        snacks.add(s);
    }

    public void showSnacks() {
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

    public List<Snack> getSnacks() {
        return snacks;
    }
}
