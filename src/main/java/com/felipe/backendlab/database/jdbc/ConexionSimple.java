package com.felipe.backendlab.database.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConexionSimple {

    public static Connection conectar() throws Exception {
        // 1. Credenciales de conexión
        String url = "jdbc:mysql://localhost:3306/zona_fit_db";
        String usuario = "root";
        String contraseña = "root";

        // 2. Registrar driver (JDBC)
        Class.forName("com.mysql.cj.jdbc.Driver");

        // 3. Crear y retornar conexión
        Connection con = DriverManager.getConnection(url, usuario, contraseña);

        return con;
    }

    public static void main(String[] args) throws Exception {
        Connection con = conectar();
        if (con != null) {
            System.out.println("Conexión establecida correctamente.");
        }
        con.close();
    }
}
