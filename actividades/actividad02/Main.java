import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String cadena;

        // try-with-resources: el PrintWriter se cierra solo al terminar
        try (PrintWriter salida = new PrintWriter(new FileWriter("datos.txt", true))) {
            System.out.println("Introduce texto. Para acabar introduce la cadena FIN:");
            cadena = sc.nextLine();
            while (!cadena.equalsIgnoreCase("FIN")) {
                salida.println(cadena);
                cadena = sc.nextLine();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
