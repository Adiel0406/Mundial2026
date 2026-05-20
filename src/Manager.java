import java.util.*;
import java.io.*;

public class Manager{

	public static ArrayList<Pais> leerPaisTxt(String archivo) {
    	ArrayList<Pais> lista = new ArrayList<>();

    	try (FileReader fr = new FileReader(archivo);
    		BufferedReader br = new BufferedReader(fr)) {
        	String linea;

        	while ((linea = br.readLine()) != null) {
            	String[] datos = linea.split(";");

            	String nombre = datos[0];
            	String continente = datos[1];
            	int mundiales = Integer.parseInt(datos[2]);
            	int participaciones = Integer.parseInt(datos[3]);
            	String cultura = datos[4];
            	String datosFutbol = datos[5];

            	ArrayList<Jugador> jugadores = new ArrayList<>();

            	for (int i = 6; i < datos.length; i++) {
            	    String[] j = datos[i].split(",");

            	    if (j.length == 3) {
            	        jugadores.add(
            	            new Jugador(
            	                j[0],
            	                j[1],
            	                Integer.parseInt(j[2])
            	            )
            	        );
            	    }
            	}

            	Pais p = new Pais(nombre, continente, mundiales, participaciones, cultura, datosFutbol, jugadores);
            	lista.add(p);
        	}

    	} catch (Exception e) {
        	e.printStackTrace();
    	}

    	return lista;
	}
}
