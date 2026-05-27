import java.io.Serializable;

public class Resultado implements Serializable {

	private static final long serialVersionUID = 1L;
	private String jugador;
    private int puntaje;
    private String dificultad;
    private int cantidadPreguntas;
    
	public Resultado(String jugador, int puntaje, String dificultad, int cantidadPreguntas) {
		super();
		this.jugador = jugador;
		this.puntaje = puntaje;
		this.dificultad = dificultad;
		this.cantidadPreguntas = cantidadPreguntas;
	}

	public String getJugador() {
		return jugador;
	}

	public void setJugador(String jugador) {
		this.jugador = jugador;
	}

	public int getPuntaje() {
		return puntaje;
	}

	public void setPuntaje(int puntaje) {
		this.puntaje = puntaje;
	}

	public String getDificultad() {
		return dificultad;
	}

	public void setDificultad(String dificultad) {
		this.dificultad = dificultad;
	}
	

	public int getCantidadPreguntas() {
		return cantidadPreguntas;
	}

	public void setCantidadPreguntas(int cantidadPreguntas) {
		this.cantidadPreguntas = cantidadPreguntas;
	}

	@Override
	public String toString() {
		return "Resultado [jugador=" + jugador + ", puntaje=" + puntaje + ", dificultad=" + dificultad
				+ ", cantidadPreguntas=" + cantidadPreguntas + "]";
	}
	
}