package configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Clase Conexion para manejar la conexión con una base de datos SQLite.
 * @author cparra
 */
public class Conexion {

    private static final String URL = "jdbc:sqlite:C:\\Users\\cparra\\Documents\\NetBeansProjects\\CalidadSoftware\\BaseDatosSQLite\\BD.db";
    private Connection conexion = null;

    // Método para abrir la conexión
    public Connection conectar() {
        try {
            if (conexion == null || conexion.isClosed()) {
                conexion = DriverManager.getConnection(URL);
                System.out.println("Conexión establecida con la base de datos SQLite.");
            }
        } catch (SQLException e) {
            System.err.println("Error al conectar con la base de datos: " + e.getMessage());
        }
        return conexion;
    }

    // Método para cerrar la conexión
    public void cerrar() {
        try {
            if (conexion != null && !conexion.isClosed()) {
                conexion.close();
                System.out.println("Conexión cerrada con la base de datos SQLite.");
            }
        } catch (SQLException e) {
            System.err.println("Error al cerrar la conexión: " + e.getMessage());
        }
    }
}

