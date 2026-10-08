package datos;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import modelo.Cliente;

/**
 *
 * @author Luis Mutis
 */
public class ClienteDAO {

    // CRUD - create - read - update - delete
    private static final String SQL_SELECT = "SELECT id_cliente, nombre, apellido, imei, telefono, saldo FROM clientes";
    private static final String SQL_INSERT = "INSERT INTO clientes(nombre, apellido, imei, telefono, saldo) VALUES (?, ?, ?, ?, ?)";
    private static final String SQL_SELECT_BY_ID = "SELECT id_cliente, nombre, apellido, imei, telefono, saldo FROM clientes WHERE id_cliente = ?";
    private static final String SQL_UPDATE = "UPDATE clientes SET nombre=?, apellido=?, imei=?, telefono=?, saldo=? WHERE id_cliente=?";
    private static final String SQL_DELTE = "DELETE FROM clientes WHERE id_cliente = ?";

    public List<Cliente> listar() {
        List<Cliente> clientes = new ArrayList<>();
        try (Connection conn = Conexion.getConecction(); PreparedStatement stmt = conn.prepareStatement(SQL_SELECT); ResultSet rs = stmt.executeQuery()) {
            // Se itera los clientes de la base de datos.
            while (rs.next()) {
                Cliente cliente = new Cliente(
                        rs.getInt("id_cliente"),
                        rs.getString("nombre"),
                        rs.getString("apellido"),
                        rs.getString("imei"),
                        rs.getString("telefono"),
                        rs.getDouble("saldo")
                );
                clientes.add(cliente);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        return clientes;
    }

    public int insert(Cliente cliente) {
        int rows = 0;
        try (Connection conn = Conexion.getConecction(); PreparedStatement stmt = conn.prepareStatement(SQL_INSERT)) {
            stmt.setString(1, cliente.getNombre());
            stmt.setString(2, cliente.getApellido());
            stmt.setString(3, cliente.getImei());
            stmt.setString(4, cliente.getTelefono());
            stmt.setDouble(5, cliente.getSaldo());

            // Se ejecuta la consulta
            rows = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        return rows;
    }

    public Cliente findClienteById(Cliente cliente) {
        try (Connection conn = Conexion.getConecction(); PreparedStatement stmt = conn.prepareStatement(SQL_SELECT_BY_ID)) {
            stmt.setInt(1, cliente.getIdCliente());
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    cliente.setNombre(rs.getString("nombre"));
                    cliente.setApellido(rs.getString("apellido"));
                    cliente.setImei(rs.getString("imei"));
                    cliente.setTelefono(rs.getString("telefono"));
                    cliente.setSaldo(rs.getDouble("saldo"));
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        return cliente;
    }

    public int actualizar(Cliente cliente) {
        int rows = 0;
        try (Connection conn = Conexion.getConecction(); PreparedStatement stmt = conn.prepareStatement(SQL_UPDATE)) {
            stmt.setString(1, cliente.getNombre());
            stmt.setString(2, cliente.getApellido());
            stmt.setString(3, cliente.getImei());
            stmt.setString(4, cliente.getTelefono());
            stmt.setDouble(5, cliente.getSaldo());
            stmt.setInt(6, cliente.getIdCliente());

            // Ejecutar la consulta
            rows = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        return rows;
    }

    public int eliminar(Cliente cliente) {
        int rows = 0;
        try (Connection conn = Conexion.getConecction(); PreparedStatement stmt = conn.prepareStatement(SQL_DELTE);) {
            stmt.setInt(1, cliente.getIdCliente());

            // Se ejecuta la eliminacion.
            rows = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        return rows;
    }
}
