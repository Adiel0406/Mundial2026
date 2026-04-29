import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import java.util.Collections;
public class Quiz {
	private ArrayList<Pregunta> preguntas;
	String nombre,dificultad;
	public Quiz(ArrayList<Pregunta> preguntas, String nombre, String dificultad) {
		super();
		this.preguntas = preguntas;
		this.nombre = nombre;
		this.dificultad = dificultad;
	}
	public ArrayList<Pregunta> getPreguntas() {
		return preguntas;
	}
	public void setPreguntas(ArrayList<Pregunta> preguntas) {
		this.preguntas = preguntas;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDificultad() {
		return dificultad;
	}
	public void setDificultad(String dificultad) {
		this.dificultad = dificultad;
	}
	@Override
	public String toString() {
		return "Quiz [preguntas=" + preguntas + ", nombre=" + nombre + ", dificultad=" + dificultad + "]";
	}
	
	public static ArrayList<Pregunta> leerPreguntasBin(String archivo){
		ArrayList<Pregunta> preguntaLeida = new ArrayList<Pregunta>();
		try(FileInputStream fis = new FileInputStream(archivo);
			DataInputStream dis = new DataInputStream(fis)){
			
			while(dis.available()>0) {
				String nombreTipo = dis.readUTF();
	            String descTipo = dis.readUTF();  
	            Tipo tipoObjeto = new Tipo(nombreTipo, descTipo);
				String nom = dis.readUTF();
				String res = dis.readUTF();
				int punt = dis.readInt();
				int numOpciones = dis.readInt();
	            ArrayList<String> ops = new ArrayList<>();
	            for(int i = 0; i < numOpciones; i++) {
	                ops.add(dis.readUTF());
	            }
				preguntaLeida.add(new Pregunta(tipoObjeto, nom, res, punt, ops));
			}
		} catch(FileNotFoundException e) {
			System.out.println("no hay archivo");
		} catch(IOException e) {
			System.out.println("error al leer");
		}
		return preguntaLeida;
	}
	
	//funcion de randomizacion
	public List<Pregunta> elegirPreguntas(String dificultad, String archivo) {
		ArrayList<Pregunta> preguntasd = leerPreguntasBin(archivo);
		Collections.shuffle(preguntasd);
		List<Pregunta> seleccionadas = preguntasd.subList(0, 5);
		return seleccionadas;
	}
	
}
