package com.felipe.backendlab.playground.snackmachinefile.service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import com.felipe.backendlab.playground.snackmachinefile.domain.Snack;

public class ServiceSnacksFiles implements IServiceSnacks {

    private final String FILE_NAME = "snacks.txt";
    private List<Snack> snacks = new ArrayList<>();

    public ServiceSnacksFiles() { // Se adiciona un constructor
        // Crear el archivo
        File file = new File(FILE_NAME);
        boolean fileExists = false;

        try {
            fileExists = file.exists();
            if (fileExists) {
                this.snacks = getAllSnacks();
            } else {
                // Se crea el arhcivo
                PrintWriter pw = new PrintWriter(new FileWriter(FILE_NAME));
                pw.close();
                System.out.println("Se ha creado el archivo...");
            }
        } catch (Exception e) {
            System.out.println("Error creando el archivo: " + e.getMessage());
        }
        if (!fileExists)
            loadInitialSnacks();
    }

    private void loadInitialSnacks() {
        this.addSnacks(new Snack("Papas", 82.2));
        this.addSnacks(new Snack("Refresco", 60.0));
        this.addSnacks(new Snack("Sandwich", 150.7));
    }

    private List<Snack> getAllSnacks() {
        List<Snack> listSnacks = new ArrayList<>();
        try {
            List<String> lines = Files.readAllLines(Paths.get(FILE_NAME));
            for (String line : lines) {
                String[] lineSnack = line.split(","); // parse para obtener todos los valores de la lista.
                Snack snack = new Snack(lineSnack[1], Double.parseDouble(lineSnack[2]));
                listSnacks.add(snack);
            }
        } catch (IOException e) {
            System.out.println("Error obteniendo snacks: " + e.getMessage());
        }
        return listSnacks;
    }

    @Override
    public void addSnacks(Snack snack) {
        // Se adiciona el nuevo snack en lista en memoria.
        this.snacks.add(snack);
        // Se adiciona el nuevo snack en el archivo.
        this.addSnackInFile(snack);
    }

    private void addSnackInFile(Snack snack) {
        boolean anexar;
        File file = new File(FILE_NAME);
        try {
            anexar = file.exists();
            PrintWriter pw = new PrintWriter(new FileWriter(FILE_NAME, anexar));
            pw.println(snack.writingSnack());
            pw.close();
        } catch (IOException e) {
            System.out.println("Error al agregar snack: " + e.getMessage());
        }
    }

    @Override
    public void showSnacks() {
        StringBuilder sb = new StringBuilder();
        if (!this.snacks.isEmpty()) {
            for (Snack snack : this.snacks) {
                sb.append(snack).append("\n");
            }
        } else {
            sb.append("No hay snacks diponibles...").append("\n");
        }
        System.out.println("--- Snacks en el inventario ---");
        System.out.println(sb);
    }

    @Override
    public List<Snack> getSnacks() {
        return this.snacks;
    }
}
