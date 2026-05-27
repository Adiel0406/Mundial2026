import java.util.ArrayList;

public class Quiz {

	private Participante participante;
	private ArrayList<Pregunta> preguntas;
	private String dificultad;
	private int cantidadPreguntas;
	private int puntajeFinal;

    public Quiz(Participante participante, String dificultad, int cantidadPreguntas,
			int puntajeFinal) {
		
		this.participante = participante;
		this.preguntas = new ArrayList<Pregunta>();
		this.dificultad = dificultad;
		this.cantidadPreguntas = cantidadPreguntas;
		this.puntajeFinal = puntajeFinal;
	}

    public Participante getParticipante() {
		return participante;
	}



	public void setParticipante(Participante participante) {
		this.participante = participante;
	}



	public ArrayList<Pregunta> getPreguntas() {
		return preguntas;
	}



	public void setPreguntas(ArrayList<Pregunta> preguntas) {
		this.preguntas = preguntas;
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



	public int getPuntajeFinal() {
		return puntajeFinal;
	}



	public void setPuntajeFinal(int puntajeFinal) {
		this.puntajeFinal = puntajeFinal;
	}

	@Override
	public String toString() {
		return "Quiz [participante=" + participante + ", preguntas=" + preguntas + ", dificultad=" + dificultad
				+ ", cantidadPreguntas=" + cantidadPreguntas + ", puntajeFinal=" + puntajeFinal + "]";
	}

	public void calcularPuntuacion() {

	    int total = 0;

	    for (Pregunta p : preguntas) {

	        if (p != null) {

	            total += p.getPuntuacion();
	        }
	    }

	    puntajeFinal = total;
	}

}