import java.util.ArrayList;

public class Pregunta {
	private Tipo tipo;
	private String nombre, respuesta;
	private int puntuacion;
	private ArrayList<String> opciones;
	public Pregunta(Tipo tipo, String nombre, String respuesta, int puntuacion, ArrayList<String> opciones) {
		super();
		this.tipo = tipo;
		this.nombre = nombre;
		this.respuesta = respuesta;
		this.puntuacion = puntuacion;
		this.opciones = opciones;
	}
	public Tipo getTipo() {
		return tipo;
	}
	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getRespuesta() {
		return respuesta;
	}
	public void setRespuesta(String respuesta) {
		this.respuesta = respuesta;
	}
	public int getPuntuacion() {
		return puntuacion;
	}
	public void setPuntuacion(int puntuacion) {
		this.puntuacion = puntuacion;
	}
	public ArrayList<String> getOpciones() {
		return opciones;
	}
	public void setOpciones(ArrayList<String> opciones) {
		this.opciones = opciones;
	}
	@Override
	public String toString() {
		return "Pregunta [tipo=" + tipo + ", nombre=" + nombre + ", respuesta=" + respuesta + ", puntuacion="
				+ puntuacion + ", opciones=" + opciones + "]";
	}
	
	public boolean esVerdaderoFalso() {
		return this.tipo.getNombre().equalsIgnoreCase("Verdadero o Falso");
	}
	
}
