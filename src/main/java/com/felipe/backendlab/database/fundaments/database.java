package com.felipe.backendlab.database.fundaments;

import java.util.ArrayList;
import java.util.List;

public class database {

    public static void main(String[] args) {

        // Enfoque 1: Datos en memoria - se pierde al cerrar la aplicación
        List<String> clients = new ArrayList<>();

        // Enfoque 2: Guardar en archivo - lento y complicado
        // Código con BufferedWriter y BufferedReader para guardar y leer los datos
        // desde un archivo

        // Enfoque 3. Base de datos
        // Código con JDBC

        System.out.println("Problema: Sin BD, ¿cómo persistir 1,000,000 de clientes?");
    }
}
