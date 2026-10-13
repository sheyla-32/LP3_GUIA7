public class Personaje {
	private String nombre;
    private int vida, ataque, defensa, alcance;

    public Personaje(String nombre, int vida, int ataque, int defensa, int alcance) {
        this.nombre = nombre;
        setVida(vida);
        setAtaque(ataque);
        setDefensa(defensa);
        setAlcance(alcance);
    }

    private int validar(String campo, int valor) {
        if (valor <= 0) {
            throw new IllegalArgumentException(campo + " debe ser mayor que 0");
        }
        return valor;
    }

    public String getNombre() { return nombre; }
    public int getVida() { return vida; }
    public int getAtaque() { return ataque; }
    public int getDefensa() { return defensa; }
    public int getAlcance() { return alcance; }

    public void setVida(int vida) { this.vida = validar("Vida", vida); }
    public void setAtaque(int ataque) { this.ataque = validar("Ataque", ataque); }
    public void setDefensa(int defensa) { this.defensa = validar("Defensa", defensa); }
    public void setAlcance(int alcance) { this.alcance = validar("Alcance", alcance); }
}
