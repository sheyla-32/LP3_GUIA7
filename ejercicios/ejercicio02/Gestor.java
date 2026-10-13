import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Comparator;

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
    
    public void actualizarAtributo(String nombre, String atributo, int valor) {
        Personaje p = buscar(nombre);
        if (p == null) {
            System.out.println(nombre + " no existe");
            return;
        }
        try {
            switch (atributo) {
                case "vida": p.setVida(valor); break;
                case "ataque": p.setAtaque(valor); break;
                case "defensa": p.setDefensa(valor); break;
                case "alcance": p.setAlcance(valor); break;
                default:
                    System.out.println("Atributo no valido");
                    return;
            }
            guardar();
            System.out.println(nombre + ": " + atributo + " = " + valor);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    public void ordenarPor(String atributo) {
        Comparator<Personaje> c;
        switch (atributo) {
            case "vida": c = Comparator.comparingInt(Personaje::getVida); break;
            case "ataque": c = Comparator.comparingInt(Personaje::getAtaque); break;
            case "defensa": c = Comparator.comparingInt(Personaje::getDefensa); break;
            case "alcance": c = Comparator.comparingInt(Personaje::getAlcance); break;
            default:
                System.out.println("Atributo no valido");
                return;
        }
        List<Personaje> copia = new ArrayList<>(lista);
        copia.sort(c.reversed());
        mostrar(copia);
    }

    public void estadisticas() {
        if (lista.isEmpty()) {
            System.out.println("No hay personajes");
            return;
        }
        System.out.println("Total de personajes: " + lista.size());
        System.out.printf("Promedio vida: %.2f%n", lista.stream().mapToInt(Personaje::getVida).average().orElse(0));
        System.out.printf("Promedio ataque: %.2f%n", lista.stream().mapToInt(Personaje::getAtaque).average().orElse(0));
        System.out.printf("Promedio defensa: %.2f%n", lista.stream().mapToInt(Personaje::getDefensa).average().orElse(0));
        System.out.printf("Promedio alcance: %.2f%n", lista.stream().mapToInt(Personaje::getAlcance).average().orElse(0));
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
