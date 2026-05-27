import java.io.Serializable;

public abstract class Pregunta implements Serializable{
	private static final long serialVersionUID = 1L;
	protected String enunciado;
    protected String respuestaCorrecta;
    protected String dificultad;
    protected int puntuacion;
    
	public Pregunta(String enunciado, String respuestaCorrecta, String dificultad, int puntuacion) {
		
		this.enunciado = enunciado;
		this.respuestaCorrecta = respuestaCorrecta;
		this.dificultad = dificultad;
		this.puntuacion = puntuacion;
	}

	public String getEnunciado() {
		return enunciado;
	}



	public void setEnunciado(String enunciado) {
		this.enunciado = enunciado;
	}



	public String getRespuestaCorrecta() {
		return respuestaCorrecta;
	}



	public void setRespuestaCorrecta(String respuestaCorrecta) {
		this.respuestaCorrecta = respuestaCorrecta;
	}



	public String getDificultad() {
		return dificultad;
	}



	public void setDificultad(String dificultad) {
		this.dificultad = dificultad;
	}



	public int getPuntuacion() {
		return puntuacion;
	}



	public void setPuntuacion(int puntuacion) {
		this.puntuacion = puntuacion;
	}


	@Override
	public String toString() {
		return "Pregunta [enunciado=" + enunciado + ", respuestaCorrecta=" + respuestaCorrecta + ", dificultad="
				+ dificultad + ", puntuacion=" + puntuacion + "]";
	}

	public abstract boolean validarRespuesta(String respuesta);
    
}
