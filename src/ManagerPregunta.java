import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class ManagerPregunta {
	
	@SuppressWarnings("unchecked")
	public ArrayList<Pregunta> cargarPreguntas(
	        String archivo
	) {

	    ArrayList<Pregunta> preguntas =
	        new ArrayList<>();

	    try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivo))) {

	        preguntas =
	            (ArrayList<Pregunta>)
	                ois.readObject();

	    } catch (Exception e) {

	        e.printStackTrace();
	    }

	    return preguntas;
	}
	
	public ArrayList<Pregunta> elegirPreguntas(
	        String archivo,
	        int cantidad
	) {

	    ArrayList<Pregunta> preguntas =
	        cargarPreguntas(archivo);

	    Collections.shuffle(preguntas);

	    ArrayList<Pregunta> elegidas =
	        new ArrayList<>();

	    for (
	        int i = 0;
	        i < cantidad && i < preguntas.size();
	        i++
	    ) {

	        elegidas.add(
	            preguntas.get(i)
	        );
	    }

	    return elegidas;
	}
	
	public void guardarLista(
	        ArrayList<Pregunta> preguntas,
	        String archivo
	) {

	    try (

	        ObjectOutputStream oos =
	            new ObjectOutputStream(
	                new FileOutputStream(archivo)
	            )

	    ) {

	        oos.writeObject(preguntas);

	    } catch (Exception e) {

	        e.printStackTrace();
	    }
	}
	
	public void guardarPregunta(
	        Pregunta pregunta,
	        String archivo
	) {

	    ArrayList<Pregunta> preguntas =
	        cargarPreguntas(archivo);

	    preguntas.add(pregunta);

	    guardarLista(
	        preguntas,
	        archivo
	    );
	}
	
	public ArrayList<Pregunta> elegirPreguntasPorDificultad(
	        String archivo,
	        String dificultad,
	        int cantidad
	) {

	    ArrayList<Pregunta> todas =
	        cargarPreguntas(archivo);

	    ArrayList<Pregunta> filtradas =
	        new ArrayList<>();

	    for (Pregunta p : todas) {

	        if (
	            p.getDificultad()
	            .equalsIgnoreCase(dificultad)
	        ) {

	            filtradas.add(p);
	        }
	    }

	    Collections.shuffle(filtradas);

	    ArrayList<Pregunta> elegidas =
	        new ArrayList<>();

	    for (
	        int i = 0;

	        i < cantidad
	        &&
	        i < filtradas.size();

	        i++
	    ) {

	        elegidas.add(
	            filtradas.get(i)
	        );
	    }

	    return elegidas;
	}
	
}
