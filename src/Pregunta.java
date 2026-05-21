

public abstract class Pregunta {
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

	public String getNombre() {
	    return nombre;
	}
	
	public Tipo getTipo() {
	    return tipo;
	}
	
	@Override
	public String toString() {

	    return nombre;
	}

	public abstract void mostrarPregunta();
    
	public boolean validarRespuesta(String entradaUsuario) {

	    return this.respuesta.equalsIgnoreCase(
	            entradaUsuario.trim()
	    );
	}
    
}
