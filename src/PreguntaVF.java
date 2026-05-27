
public class PreguntaVF extends Pregunta {

	private static final long serialVersionUID = 1L;

	public PreguntaVF(String enunciado, String respuestaCorrecta, String dificultad, int puntuacion) {
		super(enunciado, respuestaCorrecta, dificultad, puntuacion);
	}

	@Override
	public boolean validarRespuesta(String respuesta) {
		return respuestaCorrecta.equalsIgnoreCase(respuesta);
	}
}
