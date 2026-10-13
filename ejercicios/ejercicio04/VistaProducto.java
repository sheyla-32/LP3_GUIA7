import java.util.List;
import java.util.Scanner;

public class VistaProducto {
	private final Scanner sc = new Scanner(System.in);

    public int menu() {
        System.out.println("\n--- MENU ---");
        System.out.println("1. Listar todos");
        System.out.println("2. Agregar");
        System.out.println("3. Buscar por ID");
        System.out.println("4. Eliminar por ID");
        System.out.println("5. Salir");
        return pedirEntero("Opción: ");
    }

    public int pedirEntero(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            try {
                return Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Ingresa un número entero válido");
            }
        }
    }

    private double pedirDecimal(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            try {
                return Double.parseDouble(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Ingresa un número válido");
            }
        }
    }

    public Producto pedirProducto() {
        int id = pedirEntero("ID: ");
        System.out.print("Nombre: ");
        String nombre = sc.nextLine().trim();
        double precio = pedirDecimal("Precio: ");
        return new Producto(id, nombre, precio);
    }

    public void mostrar(String mensaje) {
        System.out.println(mensaje);
    }

    public void mostrarLista(List<Producto> lista) {
        if (lista.isEmpty()) {
            System.out.println("No hay registros");
            return;
        }
        for (Producto p : lista) {
            System.out.println(p);
        }
    }

}
