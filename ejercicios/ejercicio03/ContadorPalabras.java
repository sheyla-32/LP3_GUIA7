import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JFileChooser;

public class ContadorPalabras {
	public static void main(String[] args) {
        JFileChooser selector = new JFileChooser();
        File archivo = null;

        while (archivo == null) {
            if (selector.showOpenDialog(null) != JFileChooser.APPROVE_OPTION) {
                System.out.println("No se eligió ningún archivo");
                return;
            }
            File f = selector.getSelectedFile();
            if (f.isFile() && f.canRead()) {
                archivo = f;
            } else {
                System.out.println("Archivo inválido, elige otro");
            }
        }

        int lineas = 0, palabras = 0, caracteres = 0;
        Map<String, Integer> frecuencia = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                lineas++;
                caracteres += linea.length();
                StringBuilder palabra = new StringBuilder();
                for (char c : (linea + " ").toCharArray()) {
                    if (Character.isLetterOrDigit(c)) {
                        palabra.append(c);
                    } else if (palabra.length() > 0) {
                        palabras++;
                        frecuencia.merge(palabra.toString().toLowerCase(), 1, Integer::sum);
                        palabra.setLength(0);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
            return;
        }

        System.out.println("Líneas: " + lineas);
        System.out.println("Palabras: " + palabras);
        System.out.println("Caracteres: " + caracteres);
        System.out.printf("Promedio de palabras por línea: %.2f%n", lineas == 0 ? 0 : (double) palabras / lineas);
        System.out.println("Palabras más frecuentes:");
        frecuencia.entrySet().stream()
                .sorted((a, b) -> b.getValue().equals(a.getValue())
                        ? a.getKey().compareTo(b.getKey())
                        : b.getValue() - a.getValue())
                .limit(5)
                .forEach(e -> System.out.println("  " + e.getKey() + ": " + e.getValue()));
    }
}
