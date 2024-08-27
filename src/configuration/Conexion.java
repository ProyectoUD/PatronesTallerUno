package configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.io.File;

/**
 * Clase Conexion para manejar la conexión con una base de datos SQLite.
 * La base de datos se almacenará en la carpeta raíz del proyecto.
 * @author cparra
 */
public class Conexion {

    private static final String DB_NAME = "BD.db";
    private static final String URL = "jdbc:sqlite:" + getDatabasePath();
    private Connection conexion = null;

    // Método para obtener la ruta del archivo de la base de datos en la carpeta raíz del proyecto
    private static String getDatabasePath() {
        String projectRoot = new File("").getAbsolutePath();  // Obtiene la ruta absoluta del proyecto
        return projectRoot + File.separator + DB_NAME;  // Concatena la ruta del proyecto con el nombre de la base de datos
    }

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

