import java.util.*;
import java.io.*;

public class ManagerPais{
	
	private ArrayList<Pais> paises;

	public ManagerPais(String archivo) {
	    paises = cargarPaises(archivo);
	}

	public static ArrayList<Pais> cargarPaises(String archivo) {
    	ArrayList<Pais> lista = new ArrayList<>();

    	try (FileReader fr = new FileReader(archivo);
    		BufferedReader br = new BufferedReader(fr)) {
        	String linea;

        	while ((linea = br.readLine()) != null) {
            	String[] datos = linea.split(";");

            	String nombre = datos[0];
            	String continente = datos[1];
            	String codigo = datos[2];
            	int mundiales = Integer.parseInt(datos[3]);
            	int participaciones = Integer.parseInt(datos[4]);
            	String cultura = datos[5];
            	String datosFutbol = datos[6];

            	ArrayList<Jugador> jugadores = new ArrayList<>();

            	for (int i = 7; i < datos.length; i++) {
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

            	Pais p = new Pais(nombre, continente, codigo, mundiales, participaciones, cultura, datosFutbol, jugadores);
            	lista.add(p);
        	}

    	} catch (Exception e) {
        	e.printStackTrace();
    	}

    	return lista;
	}
	
	public Pais buscarPais(String nombre) {
		
		for (Pais p : paises) {
			if (p.getNombre().equals(nombre)) {
				return p;
			}
		}
		
		return null;
	}
	
	public Pais buscarPorCodigo(String codigo) {
		
		for (Pais p : paises) {
			if (p.getCodigoBandera().equals(codigo)) {
				return p;
			}
		}
		
		return null;
	}
}
