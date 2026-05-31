import java.util.ArrayList;

public class PreguntaOpMultiple extends Pregunta {
	
	private static final long serialVersionUID = 1L;
	private ArrayList<String> opciones;
	
	public PreguntaOpMultiple(
		    String enunciado,
		    String respuestaCorrecta,
		    String dificultad,
		    int puntuacion,
		    ArrayList<String> opciones
		) {

		    super(
		        enunciado,
		        respuestaCorrecta,
		        dificultad,
		        puntuacion
		    );

		    this.opciones = opciones;
		}

	

	public ArrayList<String> getOpciones() {
		return opciones;
	}



	public void setOpciones(ArrayList<String> opciones) {
		this.opciones = opciones;
	}



	@Override
	public String toString() {

	    return enunciado;
	}



	@Override
	public boolean validarRespuesta(String respuesta) {
		
		return respuestaCorrecta.equalsIgnoreCase(respuesta);
	}


}
