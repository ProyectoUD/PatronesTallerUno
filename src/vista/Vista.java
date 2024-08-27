package vista;

import java.util.Scanner;

/**
 * Clase Vista que permite la interacción con el usuario a través de la consola.
 * Métodos para ingresar y mostrar datos.
 * @author cparra
 */
public class Vista {

    private final Scanner scanner = new Scanner(System.in);
    
    // Método para ingresar un String
    public String ingresarString(String mensaje) {
        System.out.print(mensaje);
        return scanner.nextLine();
    }
    
    // Método para ingresar un int
    public int ingresarInt(String mensaje) {
        System.out.print(mensaje);
        while (!scanner.hasNextInt()) {
            System.out.println("Error: Por favor ingrese un número entero válido.");
            scanner.next(); // Consume la entrada incorrecta
        }
        int value = scanner.nextInt();
        scanner.nextLine(); // Consumir el salto de línea
        return value;
    }
    
    // Método para ingresar un double
    public double ingresarDouble(String mensaje) {
        System.out.print(mensaje);
        while (!scanner.hasNextDouble()) {
            System.out.println("Error: Por favor ingrese un número decimal válido.");
            scanner.next(); // Consume la entrada incorrecta
        }
        double value = scanner.nextDouble();
        scanner.nextLine(); // Consumir el salto de línea
        return value;
    }
    
    
    // Método para imprimir un mensaje en la consola
    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }
    
    // Método para imprimir un mensaje de error en la consola
    public void mostrarError(String mensaje) {
        System.err.println("Error: " + mensaje);
    }
}
