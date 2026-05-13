import java.util.ArrayList;

public class PreguntaOpMultiple extends Pregunta {
	private ArrayList<String> opciones;

	public PreguntaOpMultiple(Tipo tipo, String nombre, String respuesta, int puntuacion, ArrayList<String> opciones) {
		super(tipo, nombre, respuesta, puntuacion);
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
		return "PreguntaOpMultiple [opciones=" + opciones + "]";
	}
	
	public void mostrarPregunta() {
        System.out.println(this.nombre);
        for (int i = 0; i < opciones.size(); i++) {
            System.out.println((i + 1) + ". " + opciones.get(i));
        }
    }
}
