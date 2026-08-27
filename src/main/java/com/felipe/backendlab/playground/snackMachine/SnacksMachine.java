package com.felipe.backendlab.playground.snackMachine;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SnacksMachine {

    public static void main(String[] args) {
        maquinaSnacks();
    }

    private static void maquinaSnacks() {
        boolean exit = false;
        var sc = new Scanner(System.in);

        // Cración de la lista productos tipo snack.
        List<Snack> products = new ArrayList<>();
        System.out.println("*** Maquina de Snacks ***");
        Snacks.showSnacks(); // Mostrar snaks disponibles.

        while (!exit) {
            try {
                var option = showMenu(sc);
                exit = executeOptions(option, sc, products);
            } catch (Exception e) {
                System.out.println("Ocurrio un error: " + e.getMessage());
            } finally {
                System.out.println();//Imprime un salto de linea con cada interación.
            }
        }
    }

    private static int showMenu(Scanner sc) {
        System.out.println("""
                Menu:
                1. Comprar snack
                2. Mostrar ticket
                3. Agregar nuevo snack
                4. salir
                Elige una opción:
                \s""");
        // Leemos y retornamos la opción seleccionada.
        return Integer.parseInt(sc.nextLine());
    }

    private static boolean executeOptions(int option, Scanner sc, List<Snack> products) {
        boolean exit = false;
        switch (option) {
            case 1 -> buySnack(sc, products);
            case 2 -> showTicket(products);
            case 3 -> addNewSnack(sc);
            case 4 -> {
                System.out.println("Regresa pronto!");
                exit = true;
            }
            default -> System.out.println("Opción invalida: " + option);
        }
        return exit;
    }

    private static void buySnack(Scanner sc, List<Snack> products) {
        System.out.print("Que snack quieres comprar (id)?"); // con print se pide el valor en la misma linea sin hacer salto de linea.
        var idSnack = Integer.parseInt(sc.nextLine());
        // validar que el snack exista en la lista de snacks
        var snackFound = false;
        for (var snack : Snacks.getSnacks()) {
            if (idSnack == snack.getIdSnack()) {
                products.add(snack);
                System.out.println("Ok, Snack agregado: " + snack);
                snackFound = true;
                break;
            }
        }

        if (!snackFound)
            System.out.println("Id de snack no encontrado: " + idSnack);
    }

    private static void showTicket(List<Snack> products) {
        var ticket = "*** Ticket de Venta ***";
        var total = 0.0;

        for (Snack snack : products) {
            // salto de linea + un tabulador - mostrar mejor la información.
            ticket += "\n\t-" + snack.getName() + " - $" + snack.getPrice();
            total += snack.getPrice();
        }
        ticket += "\n\tTotal -> $" + total;
        System.out.println(ticket);
    }

    private static void addNewSnack(Scanner sc) {
        System.out.print("Nombre del snack: ");
        var nameSnack = sc.nextLine();
        System.out.print("Precio del snack:");
        var price = Double.parseDouble(sc.nextLine());
        Snacks.addSnacks(new Snack(nameSnack, price));
        System.out.println("Tu snack se ha agregado correctamente");
        Snacks.showSnacks();
    }
}
