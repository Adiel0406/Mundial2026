import java.util.ArrayList;

public class Pregunta {
	protected Tipo tipo;
    protected String nombre;
    protected String respuesta;
    protected int puntuacion;
    
	public Pregunta(Tipo tipo, String nombre, String respuesta, int puntuacion) {
		this.tipo = tipo;
		this.nombre = nombre;
		this.respuesta = respuesta;
		this.puntuacion = puntuacion;
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

	@Override
	public String toString() {
		return "Pregunta [tipo=" + tipo + ", nombre=" + nombre + ", respuesta=" + respuesta + ", puntuacion="
				+ puntuacion + "]";
	}
    
}
