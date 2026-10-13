import java.io.Serializable;

public class Fecha implements Serializable{
	private int dia;
    private int mes;
    private int ano;
    public Fecha(int dia, int mes, int ano) {
        this.dia = dia;
        this.mes = mes;
        this.ano = ano;
    }
    public Fecha() {
    }
    public int getAño() {
        return ano;
    }
    public void setAño(int ano) {
        this.ano = ano;
    }
    public int getDia() {
        return dia;
    }
    public void setDia(int dia) {
        this.dia = dia;
    }
    public int getMes() {
        return mes;
    }
    public void setMes(int mes) {
        this.mes = mes;
    }
}
