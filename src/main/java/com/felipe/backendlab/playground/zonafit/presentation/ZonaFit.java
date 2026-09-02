package com.felipe.backendlab.playground.zonafit.presentation;

import com.felipe.backendlab.playground.zonafit.datos.IClienteDAO;

import java.util.List;
import java.util.Scanner;

import com.felipe.backendlab.playground.zonafit.datos.ClienteDAO;
import com.felipe.backendlab.playground.zonafit.dominio.Cliente;

public class ZonaFit {
    public static void main(String[] args) {
        zonaFitApp();
    }

    public static void zonaFitApp() {
        IClienteDAO clienteDao = new ClienteDAO();
        Scanner sc = new Scanner(System.in);

        System.out.println("*** Bienvenidos a zona fit (GYM) ***");
        boolean exit = false;

        while (!exit) {
            try {
                int option = showMenu(sc);
                exit = excecuteOptions(sc, option, clienteDao);
            } catch (Exception e) {
                System.out.println("Ocurrió un error: " + e.getMessage());
            }
        }
    }

    public static int showMenu(Scanner sc) {
        System.out.println("""
                \nMenu
                1. Listar clientes
                2. Buscar cliente
                3. Agregar cliente
                4. Actualizar cliente
                5. Eliminar cliente
                6. Salir
                Elije una opción:
                \s""");
        return Integer.parseInt(sc.nextLine());
    }

    public static boolean excecuteOptions(Scanner sc, int option, IClienteDAO clienteDao) {
        switch (option) {
            case 1 -> listClients(clienteDao);
            case 2 -> searchClientById(sc, clienteDao);
            case 3 -> addClient(sc, clienteDao);
            case 4 -> updateClient(sc, clienteDao);
            case 5 -> deleteClient(sc, clienteDao);
            case 6 -> {
                System.out.println("Saliendo...");
                return true;
            }
            default -> System.out.println("Opción no válida");
        }
        return false;
    }

    public static void listClients(IClienteDAO clienteDao) {
        System.out.println("--- Listado de clientes ---");
        List<Cliente> clients = clienteDao.getClientes();
        clients.forEach(System.out::println);
    }

    public static void searchClientById(Scanner sc, IClienteDAO clienteDao) {
        System.out.print("ID del cliente a buscar: ");
        int id = Integer.parseInt(sc.nextLine());
        Cliente cliente = clienteDao.getClienteById(id);
        if (cliente != null) {
            System.out.println("Cliente encontrado: " + cliente);
        } else {
            System.out.println("No se encontró un cliente con el ID proporcionado: " + id);
        }
    }

    public static void addClient(Scanner sc, IClienteDAO clienteDao) {
        System.out.println("Agregar cliente:");
        System.out.print("Nombre: ");
        String nombre = sc.nextLine();
        System.out.print("Apellido: ");
        String apellido = sc.nextLine();
        System.out.print("Membresia: ");
        int membresia = Integer.parseInt(sc.nextLine());

        Cliente cliente = new Cliente(nombre, apellido, membresia);
        boolean result = clienteDao.addCliente(cliente);
        if (result) {
            System.out.println("Cliente agregado exitosamente: " + cliente);
        } else {
            System.out.println("No se logro agregar el cliente: " + cliente);
        }
    }

    public static void updateClient(Scanner sc, IClienteDAO clienteDao) {
        System.out.println("Actualizar cliente:");
        System.out.print("ID del cliente a actualizar: ");
        int id = Integer.parseInt(sc.nextLine());
        Cliente existingClient = clienteDao.getClienteById(id);
        if (existingClient == null) {
            System.out.println("El id del cliente ingresado no existe.");
            return;
        }

        System.out.print("Nuevo nombre: ");
        String nombre = sc.nextLine();
        System.out.print("Nuevo apellido: ");
        String apellido = sc.nextLine();
        System.out.print("Nueva membresia: ");
        int membresia = Integer.parseInt(sc.nextLine());

        Cliente cliente = new Cliente(id, nombre, apellido, membresia);
        boolean result = clienteDao.updateCliente(cliente);
        if (result) {
            System.out.println("Cliente actualizado exitosamente: " + cliente);
        } else {
            System.out.println("No se logro actualizar el cliente: " + cliente);
        }
    }

    public static void deleteClient(Scanner sc, IClienteDAO clienteDao) {
        System.out.println("Eliminar cliente:");
        System.out.print("ID del cliente a eliminar: ");
        int id = Integer.parseInt(sc.nextLine());
        Cliente existingClient = clienteDao.getClienteById(id);
        if (existingClient == null) {
            System.out.println("El id del cliente ingresado no existe.");
            return;
        }

        boolean result = clienteDao.deleteCliente(id);
        if (result) {
            System.out.println("Cliente eliminado exitosamente.");
        } else {
            System.out.println("No se logro eliminar el cliente.");
        }
    }
}
