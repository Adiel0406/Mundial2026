import java.util.ArrayList;

public class Pais {
	private String nombre,continente;
	private String codigoBandera;
	private int mundiales,participantes;
	private String cultura, datosFutbol;
	private ArrayList<Jugador> jugadores;
	
	public Pais(String nombre, String continente, String codigoBandera, int mundiales, int participantes,
			String cultura, String datosFutbol, ArrayList<Jugador> jugadores) {
		
		this.nombre = nombre;
		this.continente = continente;
		this.codigoBandera = codigoBandera;
		this.mundiales = mundiales;
		this.participantes = participantes;
		this.cultura = cultura;
		this.datosFutbol = datosFutbol;
		this.jugadores = jugadores;
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

	public String getCodigoBandera() {
		return codigoBandera;
	}

	public void setCodigoBandera(String codigoBandera) {
		this.codigoBandera = codigoBandera;
	}

	@Override
	public String toString() {
		return "Pais [nombre=" + nombre + ", continente=" + continente + ", codigoBandera=" + codigoBandera
				+ ", mundiales=" + mundiales + ", participantes=" + participantes + ", cultura=" + cultura
				+ ", datosFutbol=" + datosFutbol + ", jugadores=" + jugadores + "]";
	}
	
	public void agregarJugador(Jugador jugador) {

	    jugadores.add(jugador);
	}
	
}
