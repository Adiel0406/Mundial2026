import java.io.*;
import java.util.ArrayList;

public class ManagerEstadio {

    public ArrayList<Estadio>
    cargarEstadios(
            String archivo
    ) {

        ArrayList<Estadio>
            estadios = new ArrayList<>();

        try (

            BufferedReader br =
                new BufferedReader(
                    new FileReader(archivo)
                )

        ) {

            String linea;

            while (
                (linea = br.readLine())
                != null
            ) {

            	if (
            		    linea.startsWith("=")
            		    || linea.isBlank()
            		) {

            		    continue;
            		}
            	String pais =
            	    linea;

            	String nombre =
            	    br.readLine();
            	
            	String codigo =
            		br.readLine();

            	String ciudad =
            	    br.readLine();

            	String equipo =
            	    br.readLine();

            	int capacidad =
            	    Integer.parseInt(
            	        br.readLine()
            	    );

            	String infraestructura =
            	    br.readLine();
            	
            	Estadio estadio =
            		    new Estadio(
            		        pais,
            		        nombre,
            		        codigo,
            		        ciudad,
            		        equipo,
            		        capacidad,
            		        infraestructura
            		    );
            		
            	estadios.add(estadio);
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return estadios;
    }
    
    public ArrayList<Estadio>
    buscarPorPais(
            ArrayList<Estadio> estadios,
            String pais
    ) {

        ArrayList<Estadio> encontrados =
            new ArrayList<>();

        for (Estadio e : estadios) {

            if (
                e.getPais()
                .equalsIgnoreCase(pais)
            ) {

                encontrados.add(e);
            }
        }

        return encontrados;
    }
    
    public Estadio buscarEstadio(
            ArrayList<Estadio> estadios,
            String nombre
    ) {

        for (Estadio e : estadios) {

            if (
                e.getNombre()
                .equalsIgnoreCase(nombre)
            ) {

                return e;
            }
        }

        return null;
    }
}