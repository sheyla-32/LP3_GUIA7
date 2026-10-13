import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Gestor {
	private final String archivo = "personajes.txt";
    private final List<Personaje> lista = new ArrayList<>();

    public Gestor() {
        if (new File(archivo).exists()) {
            cargar();
        } else {
            lista.add(new Personaje("Caballero", 4, 2, 4, 2));
            lista.add(new Personaje("Guerrero", 2, 4, 2, 4));
            lista.add(new Personaje("Arquero", 2, 4, 1, 8));
            guardar();
        }
    }

    private void cargar() {
        try (Scanner sc = new Scanner(new File(archivo))) {
            while (sc.hasNextLine()) {
                String[] d = sc.nextLine().split(",");
                lista.add(new Personaje(d[0], Integer.parseInt(d[1]), Integer.parseInt(d[2]),
                        Integer.parseInt(d[3]), Integer.parseInt(d[4])));
            }
        } catch (FileNotFoundException e) {
            System.out.println("No se pudo leer el archivo");
        }
    }

    private void guardar() {
        try (PrintWriter pw = new PrintWriter(archivo)) {
            for (Personaje p : lista) {
                pw.println(p.getNombre() + "," + p.getVida() + "," + p.getAtaque() + ","
                        + p.getDefensa() + "," + p.getAlcance());
            }
        } catch (FileNotFoundException e) {
            System.out.println("No se pudo guardar el archivo");
        }
    }

    private Personaje buscar(String nombre) {
        for (Personaje p : lista) {
            if (p.getNombre().equalsIgnoreCase(nombre)) {
                return p;
            }
        }
        return null;
    }

    public void anadir(String nombre, int vida, int ataque, int defensa, int alcance) {
        if (buscar(nombre) != null) {
            System.out.println(nombre + " ya existe");
            return;
        }
        try {
            lista.add(new Personaje(nombre, vida, ataque, defensa, alcance));
            guardar();
            System.out.println(nombre + " agregado");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    public void modificar(String nombre, int vida, int ataque, int defensa, int alcance) {
        Personaje p = buscar(nombre);
        if (p == null) {
            System.out.println(nombre + " no existe");
            return;
        }
        try {
            p.setVida(vida);
            p.setAtaque(ataque);
            p.setDefensa(defensa);
            p.setAlcance(alcance);
            guardar();
            System.out.println(nombre + " modificado");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    public void borrar(String nombre) {
        Personaje p = buscar(nombre);
        if (p == null) {
            System.out.println(nombre + " no existe");
            return;
        }
        lista.remove(p);
        guardar();
        System.out.println(nombre + " borrado");
    }

    public void mostrar() {
        mostrar(lista);
    }

    private void mostrar(List<Personaje> personajes) {
        System.out.printf("%-10s %5s %5s %5s %5s%n", "Nombre", "Vida", "Atq", "Def", "Alc");
        for (Personaje p : personajes) {
            System.out.printf("%-10s %5d %5d %5d %5d%n", p.getNombre(), p.getVida(),
                    p.getAtaque(), p.getDefensa(), p.getAlcance());
        }
    }

    public static void main(String[] args) {
        Gestor g = new Gestor();
        g.mostrar();
        g.anadir("Mago", 3, 5, 1, 6);
        g.anadir("Mago", 1, 1, 1, 1);
        g.anadir("Ladron", 0, 3, 1, 2);
        g.modificar("Mago", 3, 6, 1, 6);
        g.borrar("Arquero");
        g.mostrar();
    } 
}
