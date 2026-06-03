import java.io.Serializable;

public class Jugador implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String nombre, posicion;
 	private int numero;
	
 	public Jugador(String nombre, String posicion, int numero) {
		this.nombre = nombre;
		this.posicion = posicion;
		this.numero = numero;
	}
 	public String getNombre() {
		return nombre;
 	}
 	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
 	public String getPosicion() {
		return posicion;
 	}
 	public void setPosicion(String posicion) {
		this.posicion = posicion;
 	}
 	public int getNumero() {
		return numero;
 	}
 	public void setNumero(int numero) {
		this.numero = numero;
 	}
 	@Override
 	public String toString() {
		return "Jugador [nombre=" + nombre + ", posicion=" + posicion + ", numero=" + numero + "]";
 	}
 
}
