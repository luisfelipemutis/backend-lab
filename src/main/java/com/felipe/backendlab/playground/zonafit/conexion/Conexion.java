package com.felipe.backendlab.playground.zonafit.conexion;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexion {

    public static void main(String[] args) {
        Connection conexion = Conexion.getConexion();

        if (conexion != null) {
            System.out.println("Conexión exitosa a la base de datos.");
        } else {
            System.out.println("Error al conectar a la base de datos.");
        }
    }

    public static Connection getConexion() {
        Connection conexion = null;
        String baseDatos = "zona_fit_db";

        String url = "jdbc:mysql://localhost:3306/" + baseDatos;
        String user = "root";
        String password = "root";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexion = DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            System.out.println("Error al conectar a la base de datos: " + e.getMessage());
        }

        return conexion;
    }

}
