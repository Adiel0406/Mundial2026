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
	
	public static ArrayList<Pregunta> leerPreguntasBin(String dificultad,String archivo) {
        ArrayList<Pregunta> preguntaLeida = new ArrayList<>();
        try (FileInputStream fis = new FileInputStream(Archivos.archivoPreguntasD);
             DataInputStream dis = new DataInputStream(fis)) {
            
            while (dis.available() > 0) {
                String nombreTipo = dis.readUTF();  
                Tipo tipoObjeto = new Tipo(nombreTipo);
                
                String nom = dis.readUTF();
                String res = dis.readUTF();
                int punt = dis.readInt();
                int numOpciones = dis.readInt();
                
                ArrayList<String> ops = new ArrayList<>();
                for (int i = 0; i < numOpciones; i++) {
                    ops.add(dis.readUTF());
                }

                if (nombreTipo.equalsIgnoreCase(Tipo.VERDADERO_FALSO)) {
                    preguntaLeida.add(new PreguntaVF(tipoObjeto, nom, res, punt));
                } else {
                    preguntaLeida.add(new PreguntaOpMultiple(tipoObjeto, nom, res, punt, ops));
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error: No se encontró el archivo de preguntas");
        } catch (IOException e) {
            System.out.println("Error de lectura o escritura en el archivo.");
        }
        return preguntaLeida;
    }
    
    public List<Pregunta> elegirPreguntas(String dificultad, int cantidad, String archivo) throws IllegalArgumentException {
        ArrayList<Pregunta> preguntasLeidas = leerPreguntasBin(dificultad, Archivos.archivoPreguntasD);
        
        if (preguntasLeidas.size() < cantidad) {
            throw new IllegalArgumentException("No hay suficientes preguntas solicitadas");
        }
        
        Collections.shuffle(preguntasLeidas);
        return preguntasLeidas.subList(0, cantidad);
    }


    public int calcularPuntuacionMaxima() {
        int total = 0;
        for (Pregunta p : preguntas) {
            if (p != null) { 
                total += p.getPuntuacion(); 
            }
        }
        return total;
    }


    public List<Pregunta> obtenerVerdaderoFalso() {
        List<Pregunta> filtradas = new ArrayList<>();
        for (Pregunta p : preguntas) {
            if (p instanceof PreguntaVF) {
                filtradas.add(p);
            }
        }
        return filtradas;
    }
    
    public List<Pregunta> obtenerOpcionMultiple() {
        List<Pregunta> filtradas = new ArrayList<>();
        for (Pregunta p : preguntas) {
            if (p instanceof PreguntaOpMultiple) {
                filtradas.add(p);
            }
        }
        return filtradas;
    }
	
}
