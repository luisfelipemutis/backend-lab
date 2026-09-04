package com.felipe.backendlab.playground.zonafit.datos;

import static com.felipe.backendlab.playground.zonafit.conexion.Conexion.getConexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.felipe.backendlab.playground.zonafit.dominio.Cliente;

public class ClienteDAO implements IClienteDAO {

    @Override
    public List<Cliente> getClientes() {
        List<Cliente> clientes = new ArrayList<>();
        PreparedStatement ps; // Preparar la sentencia SQL para obtener todos los clientes
        ResultSet rs; // Ejecutar la consulta y obtener los resultados
        Connection con = getConexion(); // Obtener la conexión a la base de datos

        String sql = "SELECT * FROM cliente ORDER BY id";

        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setId(rs.getInt("id"));
                cliente.setNombre(rs.getString("nombre"));
                cliente.setApellido(rs.getString("apellido"));
                cliente.setMembresia(rs.getInt("membresia"));
                clientes.add(cliente);
            }
        } catch (Exception e) {
            System.out.println("Error al obtener los clientes: " + e.getMessage());
        } finally {
            try {
                if (con != null) {
                    con.close(); // Cerrar la conexión a la base de datos
                }
            } catch (Exception e) {
                System.out.println("Error al cerrar la conexión: " + e.getMessage());
            }
        }

        // Implementación del método para obtener todos los clientes
        return clientes;
    }

    @Override
    public Cliente getClienteById(int id) {
        PreparedStatement ps; // Preparar la sentencia SQL para obtener un cliente por su ID
        ResultSet rs; // Ejecutar la consulta y obtener los resultados
        Connection con = getConexion(); // Obtener la conexión a la base de datos

        String sql = "SELECT * FROM cliente WHERE id = ?";

        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, id); // Establecer el parámetro del ID en la consulta
            rs = ps.executeQuery();
            if (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setId(rs.getInt("id"));
                cliente.setNombre(rs.getString("nombre"));
                cliente.setApellido(rs.getString("apellido"));
                cliente.setMembresia(rs.getInt("membresia"));
                return cliente; // Retornar el cliente encontrado
            }
        } catch (Exception e) {
            System.out.println("Error al obtener el cliente por ID: " + e.getMessage());
        } finally {
            try {
                if (con != null) {
                    con.close(); // Cerrar la conexión a la base de datos
                }
            } catch (Exception e) {
                System.out.println("Error al cerrar la conexión: " + e.getMessage());
            }
        }
        return null;
    }

    @Override
    public boolean addCliente(Cliente cliente) {
        PreparedStatement ps;
        Connection con = getConexion();

        String sql = "INSERT INTO cliente (nombre, apellido, membresia) "
                + "VALUES (?, ?, ?)";

        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, cliente.getNombre());
            ps.setString(2, cliente.getApellido());
            ps.setInt(3, cliente.getMembresia());
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (Exception e) {
            System.out.println("Error al agregar el cliente: " + e.getMessage());
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
                System.out.println("Error al cerrar la conexión: " + e.getMessage());
            }
        }
        return false;
    }

    @Override
    public boolean updateCliente(Cliente cliente) {
        PreparedStatement ps;
        Connection con = getConexion();
        String sql = "UPDATE cliente SET nombre = ?, apellido = ?, membresia = ? WHERE id = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, cliente.getNombre());
            ps.setString(2, cliente.getApellido());
            ps.setInt(3, cliente.getMembresia());
            ps.setInt(4, cliente.getId());
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (Exception e) {
            System.out.println("Error al actualizar el cliente: " + e.getMessage());
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
                System.out.println("Error al cerrar la conexión: " + e.getMessage());
            }
        }
        return false;
    }

    @Override
    public boolean deleteCliente(int id) {
        PreparedStatement ps;
        Connection con = getConexion();
        String sql = "DELETE FROM cliente WHERE id = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (Exception e) {
            System.out.println("Error al eliminar el cliente: " + e.getMessage());
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
                System.out.println("Error al cerrar la conexión: " + e.getMessage());
            }
        }
        return false;
    }
}
