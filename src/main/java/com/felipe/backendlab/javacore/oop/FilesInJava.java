package com.felipe.backendlab.javacore.oop;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class FilesInJava {

    public static void main(String[] args) {
        String fileName = "mi_archivo.txt";
        //createNewFile(fileName);
        //readFile(fileName);
        //readAllFile(fileName);
        writeFile(fileName);
    }

    // Método para crear un archivo.
    private static void createNewFile(String fileName) {
        File file = new File(fileName);

        try {
            if (!file.exists()) {
                // Creamos el archivo.
                var salida = new PrintWriter(new FileWriter(fileName));
                // Se guarda el archivo en disco duro
                salida.close();
                System.out.println("Se ha creado el archivo: " + fileName);
            } else {
                System.out.println("El archivo ya existe!");
            }
        } catch (IOException e) {
            System.out.println("Error al crear archivo: " + e.getMessage());
        }
    }

    // Método para leer el archivo.
    private static void readFile(String fileName) {
        File file = new File(fileName);
        try {
            System.out.println("Contenido del archivo: ");
            //Abrir el archivo para lectura
            BufferedReader br = new BufferedReader(new FileReader(fileName));

            // Leer linea a linea del archivo
            String line = br.readLine();
            //Leemos todas las lineas.
            while (line != null) {
                System.out.println(line);
                // Antes de terminar de ciclo, se mueve a la siguiente linea.
                line = br.readLine();
            }
            // Cerrar el arhivo.
            br.close();
        } catch (Exception e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }
    }

    // Método para leer el archivo con método:
    private static void readAllFile(String fileName) {
        try {
            // leer todas las lineas del archivo
            List<String> lines = Files.readAllLines(Paths.get(fileName));
            System.out.println("Contenido del archivo");

            for (String line : lines) {
                System.out.println(line);
            }
        } catch (Exception e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void writeFile(String fileName) {
        boolean anexar;
        File file = new File(fileName);

        try {
            anexar = file.exists();
            // El parametro anexar indica si va crear/anexar nuevo texto.
            var salida = new PrintWriter(new FileWriter(fileName, anexar));
            String sms = "Nuevo\nComentario";
            salida.println(sms);
            salida.close();
            System.out.println("Se agrego contenido al archivo..");
        } catch (IOException e) {
            System.out.println("Error al escribir en el archivo: " + e.getMessage());
        }
    }
}

