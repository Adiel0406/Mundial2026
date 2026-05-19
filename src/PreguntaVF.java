
public class PreguntaVF extends Pregunta {

	public PreguntaVF(Tipo tipo, String nombre, String respuesta, int puntuacion) {
		super(tipo, nombre, respuesta, puntuacion);
	}
	
	public void mostrarPregunta() {
		System.out.println(this.nombre);
        System.out.println("1. Verdadero");
        System.out.println("2. Falso");
	}
}
