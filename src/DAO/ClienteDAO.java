package dao;

import modelo.Cliente;
import configuration.Conexion;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase DAO para realizar operaciones CRUD sobre la entidad Cliente.
 * @author cparra
 */
public class ClienteDAO {

    private final Conexion conexion;

    public ClienteDAO() {
        this.conexion = new Conexion();
    }

    public boolean crearCliente(Cliente cliente) {
        String sql = "INSERT INTO clientes (nombre, numeroTelefono, direccion) VALUES (?, ?, ?)";

        try (Connection conn = conexion.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, cliente.getNombre());
            pstmt.setString(2, cliente.getNumeroTelefono());
            pstmt.setString(3, cliente.getDireccion());

            pstmt.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.out.println("Error al crear el cliente: " + e.getMessage());
            return false;
        }
    }

    public List<Cliente> leerClientes() {
        String sql = "SELECT * FROM clientes";
        List<Cliente> listaClientes = new ArrayList<>();

        try (Connection conn = conexion.conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Cliente cliente = new Cliente(
                        rs.getString("nombre"),
                        rs.getString("numeroTelefono"),
                        rs.getString("direccion")
                );
                cliente.setId(rs.getString("id"));
                listaClientes.add(cliente);
            }

        } catch (SQLException e) {
            System.out.println("Error al leer los clientes: " + e.getMessage());
        }

        return listaClientes;
    }

    public boolean actualizarCliente(Cliente cliente) {
        String sql = "UPDATE clientes SET nombre = ?, numeroTelefono = ?, direccion = ? WHERE id = ?";

        try (Connection conn = conexion.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, cliente.getNombre());
            pstmt.setString(2, cliente.getNumeroTelefono());
            pstmt.setString(3, cliente.getDireccion());
            pstmt.setString(4, cliente.getId());

            pstmt.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.out.println("Error al actualizar el cliente: " + e.getMessage());
            return false;
        }
    }

    public boolean eliminarCliente(String id) {
        String sql = "DELETE FROM clientes WHERE id = ?";

        try (Connection conn = conexion.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, id);

            pstmt.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.out.println("Error al eliminar el cliente: " + e.getMessage());
            return false;
        }
    }

    public Cliente buscarClientePorId(String id) {
        String sql = "SELECT * FROM clientes WHERE id = ?";

        try (Connection conn = conexion.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, id);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    Cliente cliente = new Cliente(
                            rs.getString("nombre"),
                            rs.getString("numeroTelefono"),
                            rs.getString("direccion")
                    );
                    cliente.setId(rs.getString("id"));
                    return cliente;
                }
            }

        } catch (SQLException e) {
            System.out.println("Error al buscar el cliente: " + e.getMessage());
        }

        return null;
    }
}
