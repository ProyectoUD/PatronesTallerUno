package dao;

import DAO.IDAO;
import modelo.Producto;
import configuracion.Conexion;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase DAO para realizar operaciones CRUD sobre la entidad Producto.
 * @author cparra
 */
public class ProductoDAO implements IDAO<Producto>{

    private final Conexion conexion;

    public ProductoDAO() {
        this.conexion = new Conexion();
    }

    @Override
    public boolean crear(Producto producto) {
        String sql = "INSERT INTO productos (nombre, precio) VALUES (?, ?)";

        try (Connection conn = conexion.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, producto.getNombre());
            pstmt.setDouble(2, producto.getPrecio());

            pstmt.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.out.println("Error al crear el producto: " + e.getMessage());
            return false;
        }
    }

    @Override
    public List<Producto> consultar() {
        String sql = "SELECT * FROM productos";
        List<Producto> listaProductos = new ArrayList<>();

        try (Connection conn = conexion.conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Producto producto = new Producto(
                        rs.getString("nombre"),
                        rs.getDouble("precio")
                );
                producto.setId(rs.getString("id"));
                listaProductos.add(producto);
            }

        } catch (SQLException e) {
            System.out.println("Error al leer los productos: " + e.getMessage());
        }

        return listaProductos;
    }

    @Override
    public boolean actualizar(Producto producto) {
        String sql = "UPDATE productos SET nombre = ?, precio = ? WHERE id = ?";

        try (Connection conn = conexion.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, producto.getNombre());
            pstmt.setDouble(2, producto.getPrecio());
            pstmt.setString(3, producto.getId());

            pstmt.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.out.println("Error al actualizar el producto: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean eliminar(String id) {
        String sql = "DELETE FROM productos WHERE id = ?";

        try (Connection conn = conexion.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, id);

            pstmt.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.out.println("Error al eliminar el producto: " + e.getMessage());
            return false;
        }
    }

    @Override
    public Producto buscarPorId(String id) {
        String sql = "SELECT * FROM productos WHERE id = ?";

        try (Connection conn = conexion.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, id);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    Producto producto = new Producto(
                            rs.getString("nombre"),
                            rs.getDouble("precio")
                    );
                    producto.setId(rs.getString("id"));
                    return producto;
                }
            }

        } catch (SQLException e) {
            System.out.println("Error al buscar el producto: " + e.getMessage());
        }

        return null;
    }
}
