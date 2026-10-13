import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class Serial5 {
    public static void main(String[] args) {
        FileOutputStream fos = null;
        ObjectOutputStream salida = null;
        Alumno a;
        Fecha f;

        System.out.println("Iniciando programa...");
        System.out.println("Directorio actual: " + System.getProperty("user.dir"));


        try {
            fos = new FileOutputStream("alumnos.dat");
            salida = new ObjectOutputStream(fos);
            System.out.println("Archivo creado correctamente.");

            f = new Fecha(5, 9, 2011);
            a = new Alumno("12345678A", "Lucas Gonzales", 20, f);
            salida.writeObject(a);
            System.out.println("Objeto 1 escrito."); 

            f = new Fecha(7, 9, 2011);
            a = new Alumno("98765432B", "Anacleto Jimenez", 19, f);
            salida.writeObject(a);
            System.out.println("Objeto 2 escrito."); 

            f = new Fecha(8, 9, 2011);
            a = new Alumno("78234212Z", "Maria Zapata", 21, f);
            salida.writeObject(a);
            System.out.println("Objeto 3 escrito.");


        } catch (FileNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Error de escritura: " + e.getMessage());
        } finally {
            try {
                if (fos != null) fos.close();
                if (salida != null) salida.close();
            } catch (IOException e) {
                System.out.println("Error al cerrar: " + e.getMessage());
            }
        }
    }
}
