import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class ControladorProducto {
	 private final File archivo = new File("productos.txt");
	    private final VistaProducto vista;

	    public ControladorProducto(VistaProducto vista) {
	        this.vista = vista;
	    }

	    public List<Producto> leerRegistros() throws IOException {
	        List<Producto> lista = new ArrayList<>();
	        if (!archivo.exists()) {
	            return lista;
	        }
	        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
	            String linea;
	            while ((linea = br.readLine()) != null) {
	                String[] d = linea.split(";");
	                lista.add(new Producto(Integer.parseInt(d[0]), d[1], Double.parseDouble(d[2])));
	            }
	        }
	        return lista;
	    }

	    public boolean agregarRegistro(Producto p) throws IOException {
	        if (buscarRegistro(p.getId()) != null) {
	            return false;
	        }
	        try (PrintWriter pw = new PrintWriter(new FileWriter(archivo, true))) {
	            pw.println(p.getId() + ";" + p.getNombre() + ";" + p.getPrecio());
	        }
	        return true;
	    }

	    public Producto buscarRegistro(int id) throws IOException {
	        for (Producto p : leerRegistros()) {
	            if (p.getId() == id) {
	                return p;
	            }
	        }
	        return null;
	    }

	    public boolean eliminarRegistro(int id) throws IOException {
	        List<Producto> lista = leerRegistros();
	        boolean eliminado = lista.removeIf(p -> p.getId() == id);
	        if (eliminado) {
	            try (PrintWriter pw = new PrintWriter(new FileWriter(archivo))) {
	                for (Producto p : lista) {
	                    pw.println(p.getId() + ";" + p.getNombre() + ";" + p.getPrecio());
	                }
	            }
	        }
	        return eliminado;
	    }

	    public void iniciar() {
	        try {
	            vista.mostrarLista(leerRegistros());
	        } catch (IOException | RuntimeException e) {
	            vista.mostrar("Error al leer el archivo: " + e.getMessage());
	        }

	        int opcion;
	        do {
	            opcion = vista.menu();
	            try {
	                switch (opcion) {
	                    case 1:
	                        vista.mostrarLista(leerRegistros());
	                        break;
	                    case 2:
	                        boolean agregado = agregarRegistro(vista.pedirProducto());
	                        vista.mostrar(agregado ? "Producto agregado" : "Ya existe un producto con ese ID");
	                        break;
	                    case 3:
	                        Producto encontrado = buscarRegistro(vista.pedirEntero("ID: "));
	                        vista.mostrar(encontrado != null ? encontrado.toString() : "Producto no encontrado");
	                        break;
	                    case 4:
	                        boolean eliminado = eliminarRegistro(vista.pedirEntero("ID: "));
	                        vista.mostrar(eliminado ? "Producto eliminado" : "Producto no encontrado");
	                        break;
	                    case 5:
	                        vista.mostrar("Hasta luego");
	                        break;
	                    default:
	                        vista.mostrar("Opción no válida");
	                }
	            } catch (IOException | RuntimeException e) {
	                vista.mostrar("Error de archivo: " + e.getMessage());
	            }
	        } while (opcion != 5);
	    }
}
