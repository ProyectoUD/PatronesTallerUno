package controlador;

import dao.ClienteDAO;
import dao.ProductoDAO;
import modelo.Cliente;
import modelo.Producto;
import vista.Vista;

import java.util.List;

/**
 * Clase principal para manejar el men� CRUD de Cliente y Producto.
 * @author cparra
 */
public class ControladorComidaRapida {

    private static final Vista vista = new Vista();
    private static final ClienteDAO clienteDAO = new ClienteDAO();
    private static final ProductoDAO productoDAO = new ProductoDAO();

    public static void main(String[] args) {
        int opcion;

        do {
            mostrarMenu();
            opcion = vista.ingresarInt("Seleccione una opci�n: ");
            vista.mostrarMensaje("");  // Para un salto de l�nea

            switch (opcion) {
                case 1:
                    crearCliente();
                    break;
                case 2:
                    leerClientes();
                    break;
                case 3:
                    actualizarCliente();
                    break;
                case 4:
                    eliminarCliente();
                    break;
                case 5:
                    crearProducto();
                    break;
                case 6:
                    leerProductos();
                    break;
                case 7:
                    actualizarProducto();
                    break;
                case 8:
                    eliminarProducto();
                    break;
                case 9:
                    vista.mostrarMensaje("Saliendo del sistema...");
                    break;
                default:
                    vista.mostrarError("Opci�n no v�lida. Intente nuevamente.");
            }
        } while (opcion != 9);
    }

    private static void mostrarMenu() {
        vista.mostrarMensaje("---- COMIDAS RAPIDAS ----");
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

    // M�todos para CRUD de Cliente
    private static void crearCliente() {
        String nombre = vista.ingresarString("Ingrese el nombre del cliente: ");
        String telefono = vista.ingresarString("Ingrese el n�mero de tel�fono del cliente: ");
        String direccion = vista.ingresarString("Ingrese la direcci�n del cliente: ");

        Cliente cliente = new Cliente(nombre, telefono, direccion);
        if (clienteDAO.crear(cliente)) {
            vista.mostrarMensaje("Cliente creado exitosamente.");
        } else {
            vista.mostrarError("Error al crear el cliente.");
        }
    }

    private static void leerClientes() {
        List<Cliente> clientes = clienteDAO.consultar();
        if (clientes.isEmpty()) {
            vista.mostrarMensaje("No hay clientes registrados.");
        } else {
            vista.mostrarMensaje("---- Lista de Clientes ----");
            for (Cliente cliente : clientes) {
                vista.mostrarMensaje("Nombre: " + cliente.getNombre());
                vista.mostrarMensaje("Tel�fono: " + cliente.getNumeroTelefono());
                vista.mostrarMensaje("Direcci�n: " + cliente.getDireccion());
                vista.mostrarMensaje("----------------------------");
            }
        }
    }

    private static void actualizarCliente() {
        String id = vista.ingresarString("Ingrese el ID del cliente a actualizar: ");
        Cliente cliente = clienteDAO.buscarPorId(id);

        if (cliente != null) {
            String nuevoNombre = vista.ingresarString("Ingrese el nuevo nombre: ");
            String nuevoTelefono = vista.ingresarString("Ingrese el nuevo n�mero de tel�fono: ");
            String nuevaDireccion = vista.ingresarString("Ingrese la nueva direcci�n: ");

            cliente.setNombre(nuevoNombre);
            cliente.setNumeroTelefono(nuevoTelefono);
            cliente.setDireccion(nuevaDireccion);

            if (clienteDAO.actualizar(cliente)) {
                vista.mostrarMensaje("Cliente actualizado exitosamente.");
            } else {
                vista.mostrarError("Error al actualizar el cliente.");
            }
        } else {
            vista.mostrarError("Cliente no encontrado.");
        }
    }

    private static void eliminarCliente() {
        String id = vista.ingresarString("Ingrese el ID del cliente a eliminar: ");
        if (clienteDAO.eliminar(id)) {
            vista.mostrarMensaje("Cliente eliminado exitosamente.");
        } else {
            vista.mostrarError("Cliente no encontrado.");
        }
    }

    // M�todos para CRUD de Producto
    private static void crearProducto() {
        String nombre = vista.ingresarString("Ingrese el nombre del producto: ");
        Double precio = vista.ingresarDouble("Ingrese el precio del producto: ");

        Producto producto = new Producto(nombre, precio);
        if (productoDAO.crear(producto)) {
            vista.mostrarMensaje("Producto creado exitosamente.");
        } else {
            vista.mostrarError("Error al crear el producto.");
        }
    }

    private static void leerProductos() {
        List<Producto> productos = productoDAO.consultar();
        if (productos.isEmpty()) {
            vista.mostrarMensaje("No hay productos registrados.");
        } else {
            vista.mostrarMensaje("---- Lista de Productos ----");
            for (Producto producto : productos) {
                vista.mostrarMensaje("Nombre: " + producto.getNombre());
                vista.mostrarMensaje("Precio: " + producto.getPrecio());
                vista.mostrarMensaje("----------------------------");
            }
        }
    }

    private static void actualizarProducto() {
        String id = vista.ingresarString("Ingrese el ID del producto a actualizar: ");
        Producto producto = productoDAO.buscarPorId(id);

        if (producto != null) {
            String nuevoNombre = vista.ingresarString("Ingrese el nuevo nombre: ");
            Double nuevoPrecio = vista.ingresarDouble("Ingrese el nuevo precio: ");

            producto.setNombre(nuevoNombre);
            producto.setPrecio(nuevoPrecio);

            if (productoDAO.actualizar(producto)) {
                vista.mostrarMensaje("Producto actualizado exitosamente.");
            } else {
                vista.mostrarError("Error al actualizar el producto.");
            }
        } else {
            vista.mostrarError("Producto no encontrado.");
        }
    }

    private static void eliminarProducto() {
        String id = vista.ingresarString("Ingrese el ID del producto a eliminar: ");
        if (productoDAO.eliminar(id)) {
            vista.mostrarMensaje("Producto eliminado exitosamente.");
        } else {
            vista.mostrarError("Producto no encontrado.");
        }
    }
}

