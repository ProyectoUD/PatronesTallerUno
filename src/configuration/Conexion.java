package configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
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
                
                // Crear las tablas si no existen
                crearTablas();
            }
        } catch (SQLException e) {
            System.err.println("Error al conectar con la base de datos: " + e.getMessage());
        }
        return conexion;
    }

    // Método para crear las tablas si no existen
    private void crearTablas() {
        String sqlClientes = "CREATE TABLE IF NOT EXISTS clientes (" +
                             "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                             "nombre TEXT NOT NULL, " +
                             "numeroTelefono TEXT NOT NULL, " +
                             "direccion TEXT NOT NULL);";

        String sqlProductos = "CREATE TABLE IF NOT EXISTS productos (" +
                              "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                              "nombre TEXT NOT NULL, " +
                              "precio REAL NOT NULL);";

        try (Statement stmt = conexion.createStatement()) {
            stmt.execute(sqlClientes);
            stmt.execute(sqlProductos);
            System.out.println("Tablas 'clientes' y 'productos' creadas o ya existen.");
        } catch (SQLException e) {
            System.err.println("Error al crear las tablas: " + e.getMessage());
        }
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


