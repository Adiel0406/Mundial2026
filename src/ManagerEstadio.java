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
    
    public void mostrarDetalles(
            Estadio estadio
    ) {

        if (estadio == null) {

            System.out.println(
                "Estadio no encontrado."
            );

            return;
        }

        System.out.println(
            "================================"
        );

        System.out.println(
            estadio.getNombre()
        );

        System.out.println(
            "================================"
        );

        System.out.println(
            "Pais: "
            + estadio.getPais()
        );

        System.out.println(
            "Ciudad: "
            + estadio.getCiudad()
        );

        System.out.println(
            "Equipo: "
            + estadio.getEquipo()
        );

        System.out.println(
            "Capacidad: "
            + estadio.getCapacidad()
        );

        System.out.println();

        System.out.println(
            "Infraestructura:"
        );

        System.out.println(
            estadio.getInfraestructura()
        );

        System.out.println(
            "================================"
        );
    }
}