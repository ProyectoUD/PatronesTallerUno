package controlador;

import modelo.Cliente;
import modelo.Producto;
import vista.Vista;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase principal para manejar el menú CRUD de Cliente y Producto.
 * @author cparra
 */
public class CalidadSoftware {

    private static final Vista vista = new Vista();
    private static final List<Cliente> clientes = new ArrayList<>();
    private static final List<Producto> productos = new ArrayList<>();

    public static void main(String[] args) {
        int opcion;

        do {
            mostrarMenu();
            opcion = vista.ingresarInt("Seleccione una opción: ");
            vista.mostrarMensaje("");  // Para un salto de línea

            switch (opcion) {
                case 1:
                    /*crearCliente();*/
                    break;
                case 2:
                    /*leerClientes();*/
                    break;
                case 3:
                    /*actualizarCliente();*/
                    break;
                case 4:
                    /*eliminarCliente();*/
                    break;
                case 5:
                    /*crearProducto();*/
                    break;
                case 6:
                    /*leerProductos();*/
                    break;
                case 7:
                    /*actualizarProducto();*/
                    break;
                case 8:
                    /*eliminarProducto();*/
                    break;
                case 9:
                    vista.mostrarMensaje("Saliendo del sistema...");
                    break;
                default:
                    vista.mostrarError("Opción no válida. Intente nuevamente.");
            }
        } while (opcion != 9);
    }

    private static void mostrarMenu() {
        vista.mostrarMensaje("---- MENÚ CRUD ----");
        vista.mostrarMensaje("1. Crear Cliente");
        vista.mostrarMensaje("2. Leer Clientes");
        vista.mostrarMensaje("3. Actualizar Cliente");
        vista.mostrarMensaje("4. Eliminar Cliente");
        vista.mostrarMensaje("5. Crear Producto");
        vista.mostrarMensaje("6. Leer Productos");
        vista.mostrarMensaje("7. Actualizar Producto");
        vista.mostrarMensaje("8. Eliminar Producto");
        vista.mostrarMensaje("9. Salir");
    }
}