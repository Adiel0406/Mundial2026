import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public class Quiz {
	
	private ArrayList<Pregunta> preguntas;
	private String nombre,dificultad;
	
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
	
	public static ArrayList<Pregunta> leerPreguntasBin(
			String archivo
		) {
		
        ArrayList<Pregunta> preguntaLeida = new ArrayList<>();
        
        try (FileInputStream fis = new FileInputStream(archivo);
             DataInputStream dis = new DataInputStream(fis)) {
            
            while (dis.available() > 0) {
                String nombreTipo = dis.readUTF();  
                Tipo tipoObjeto =
                        Tipo.valueOf(nombreTipo);
                
                String nom = dis.readUTF();
                String res = dis.readUTF();
                int punt = dis.readInt();
                int numOpciones = dis.readInt();
                
                ArrayList<String> ops = new ArrayList<>();
                for (int i = 0; i < numOpciones; i++) {
                    ops.add(dis.readUTF());
                }

                if (tipoObjeto == Tipo.VERDADERO_FALSO) {

                    preguntaLeida.add(
                            new PreguntaVF(
                                    tipoObjeto,
                                    nom,
                                    res,
                                    punt
                            )
                    );

                } else {

                    preguntaLeida.add(
                            new PreguntaOpMultiple(
                                    tipoObjeto,
                                    nom,
                                    res,
                                    punt,
                                    ops
                            )
                    );
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error: No se encontró el archivo de preguntas");
        } catch (IOException e) {
            System.out.println("Error de lectura o escritura en el archivo.");
        }
        return preguntaLeida;
    }
    
	public List<Pregunta> elegirPreguntas(
	        String dificultad,
	        int cantidad
	) throws IllegalArgumentException {

	    String archivo;

	    // ================= SELECCIONAR ARCHIVO

	    if (dificultad.equalsIgnoreCase("Facil")) {

	        archivo = Archivos.archivoPreguntasF;

	    } else if (
	            dificultad.equalsIgnoreCase("Medio")
	    ) {

	        archivo = Archivos.archivoPreguntasM;

	    } else {

	        archivo = Archivos.archivoPreguntasD;
	    }

	    // ================= LEER PREGUNTAS

	    ArrayList<Pregunta> preguntasLeidas =
	            leerPreguntasBin(archivo);

	    // ================= VALIDAR CANTIDAD

	    if (preguntasLeidas.size() < cantidad) {

	        throw new IllegalArgumentException(
	                "No hay suficientes preguntas"
	        );
	    }

	    // ================= MEZCLAR

	    Collections.shuffle(preguntasLeidas);

	    return preguntasLeidas.subList(
	            0,
	            cantidad
	    );
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
    
    private int puntuacionActual;
    
    private int preguntaActual;
	
}
