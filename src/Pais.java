import java.util.ArrayList;

public class Pais {
	private String nombre,continente;
	private int mundiales,participantes;
	private String cultura, datosFutbol;
	private ArrayList<Jugador> jugadores;

	public Pais(String nombre, String continente, int mundiales, int participantes,
            String cultura, String datosFutbol, ArrayList<Jugador> jugadores) {

		this.nombre = nombre;
		this.continente = continente;
		this.mundiales = mundiales;
		this.participantes = participantes;
		this.cultura = cultura;
		this.datosFutbol = datosFutbol;
		this.jugadores = jugadores;

		if (jugadores.size() != 3) {
			throw new IllegalArgumentException("Un país debe tener exactamente 3 jugadores");
		}
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getContinente() {
		return continente;
	}
	public void setContinente(String continente) {
		this.continente = continente;
	}
	public int getMundiales() {
		return mundiales;
	}
	public void setMundiales(int mundiales) {
		this.mundiales = mundiales;
	}
	public int getParticipantes() {
		return participantes;
	}
	public void setParticipantes(int participantes) {
		this.participantes = participantes;
	}
	public String getCultura() {
		return cultura;
	}
	public void setCultura(String cultura) {
		this.cultura = cultura;
	}
	public ArrayList<Jugador> getJugadores() {
		return jugadores;
	}
	public void setJugadores(ArrayList<Jugador> jugadores) {
		this.jugadores = jugadores;
	}
	
	public String getDatosFutbol() {
		return datosFutbol;
	}

	public void setDatosFutbol(String datosFutbol) {
		this.datosFutbol = datosFutbol;
	}

	@Override
	public String toString() {
		return nombre + " (" + continente + ") - Mundiales: " + mundiales +
       					", Participaciones: " + participantes +
       					"\nCultura: " + cultura +
       					"\nDatos Futbolisticos: " + datosFutbol +
       					"\nJugadores: " + jugadores;
	}
	
}
