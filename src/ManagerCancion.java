import java.io.*;
import java.util.ArrayList;

public class ManagerCancion {

	public ArrayList<Cancion> cargarCanciones(
	        String archivo
	) {

	    ArrayList<Cancion> canciones =
	        new ArrayList<>();

	    try (

	        BufferedReader br =
	            new BufferedReader(
	                new FileReader(archivo)
	            )

	    ) {

	        String linea;

	        while (
	            (linea = br.readLine()) != null
	        ) {

	            // =====================
	            // SALTAR VACIOS
	            // =====================

	            if (
	                linea.isBlank()
	                || linea.startsWith("=")
	            ) {

	                continue;
	            }

	            // =====================
	            // NOMBRE
	            // =====================

	            String nombre = linea;

	            // =====================
	            // RUTA AUDIO
	            // =====================

	            String rutaAudio =
	                br.readLine();

	            while (
	                rutaAudio != null
	                && rutaAudio.isBlank()
	            ) {

	                rutaAudio = br.readLine();
	            }

	            // =====================
	            // RUTA IMAGEN
	            // =====================

	            String rutaImagen =
	                br.readLine();

	            while (
	                rutaImagen != null
	                && rutaImagen.isBlank()
	            ) {

	                rutaImagen = br.readLine();
	            }

	            // =====================
	            // DURACION
	            // =====================

	            String lineaDuracion =
	                br.readLine();

	            while (
	                lineaDuracion != null
	                && lineaDuracion.isBlank()
	            ) {

	                lineaDuracion =
	                    br.readLine();
	            }

	            double duracion =
	                Double.parseDouble(
	                    lineaDuracion
	                );

	            // =====================
	            // CANTIDAD CANTANTES
	            // =====================

	            String lineaCantidad =
	                br.readLine();

	            while (
	                lineaCantidad != null
	                && lineaCantidad.isBlank()
	            ) {

	                lineaCantidad =
	                    br.readLine();
	            }

	            int cantidadCantantes =
	                Integer.parseInt(
	                    lineaCantidad
	                );

	         // =====================
	         // CANTANTES
	         // =====================

	         ArrayList<Cantante> cantantes =
	             new ArrayList<>();

	         for (
	             int i = 0;
	             i < cantidadCantantes;
	             i++
	         ) {

	             String nombreCantante =
	                 br.readLine();

	             // saltar vacios
	             while (
	                 nombreCantante != null
	                 && nombreCantante.isBlank()
	             ) {

	                 nombreCantante =
	                     br.readLine();
	             }

	             String pais =
	                 br.readLine();

	             while (
	                 pais != null
	                 && pais.isBlank()
	             ) {

	                 pais =
	                     br.readLine();
	             }

	             String lineaEdad =
	                 br.readLine();

	             while (
	                 lineaEdad != null
	                 && lineaEdad.isBlank()
	             ) {

	                 lineaEdad =
	                     br.readLine();
	             }

	             int edad =
	                 Integer.parseInt(
	                     lineaEdad
	                 );

	             Cantante cantante =
	                 new Cantante(
	                     nombreCantante,
	                     pais,
	                     edad
	                 );

	             cantantes.add(cantante);
	         }

	      // =====================
	      // LETRA
	      // =====================

	      ArrayList<LineaLetra>
	          letraSincronizada =
	              new ArrayList<>();

	      while (
	          (linea = br.readLine()) != null
	      ) {

	          if (
	              linea.equals("FIN")
	          ) {

	              break;
	          }

	          if (
	              linea.isBlank()
	          ) {

	              continue;
	          }

	          // =====================
	          // SI NO TIENE TIEMPO
	          // =====================

	          if (
	              !linea.startsWith("[")
	              || !linea.contains("]")
	          ) {

	              letraSincronizada.add(

	                  new LineaLetra(
	                      0,
	                      linea
	                  )
	              );

	              continue;
	          }

	          try {

	              int cierre =
	                  linea.indexOf("]");

	              String tiempoTexto =
	                  linea.substring(
	                      1,
	                      cierre
	                  );

	              String texto =
	                  linea.substring(
	                      cierre + 1
	                  );

	              String[] partes =
	                  tiempoTexto.split(":");

	              double minutos =
	                  Double.parseDouble(
	                      partes[0]
	                  );

	              double segundos =
	                  Double.parseDouble(
	                      partes[1]
	                  );

	              double tiempo =
	                  (minutos * 60)
	                  + segundos;

	              letraSincronizada.add(

	                  new LineaLetra(
	                      tiempo,
	                      texto
	                  )
	              );

	          } catch (Exception ex) {

	              System.out.println(
	                  "Linea invalida: "
	                  + linea
	              );
	          }
	      }

	            // =====================
	            // CREAR CANCION
	            // =====================

	            Cancion cancion =
	                new Cancion(
	                    nombre,
	                    cantantes,
	                    letraSincronizada,
	                    rutaAudio,
	                    rutaImagen,
	                    duracion
	                );

	            canciones.add(cancion);
	        }

	    } catch (Exception e) {

	        e.printStackTrace();
	    }

	    return canciones;
	}

    // =========================
    // BUSCAR POR NOMBRE
    // =========================

    public Cancion buscarCancion(
            ArrayList<Cancion> canciones,
            String nombre
    ) {

        for (Cancion c : canciones) {

            if (
                c.getNombre()
                 .equalsIgnoreCase(nombre)
            ) {

                return c;
            }
        }

        return null;
    }

    // =========================
    // BUSCAR POR PAIS
    // =========================

    public ArrayList<Cancion> buscarPorPais(
            ArrayList<Cancion> canciones,
            String pais
    ) {

        ArrayList<Cancion> resultado =
            new ArrayList<>();

        for (Cancion c : canciones) {

            for (
                Cantante cantante
                : c.getCantantes()
            ) {

                if (
                    cantante.getPais()
                    .equalsIgnoreCase(pais)
                ) {

                    resultado.add(c);

                    break;
                }
            }
        }

        return resultado;
    }

    // =========================
    // BUSCAR POR ARTISTA
    // =========================

    public ArrayList<Cancion> buscarPorArtista(
            ArrayList<Cancion> canciones,
            String artista
    ) {

        ArrayList<Cancion> resultado =
            new ArrayList<>();

        for (Cancion c : canciones) {

            for (
                Cantante cantante
                : c.getCantantes()
            ) {

                if (
                    cantante.getNombre()
                    .equalsIgnoreCase(artista)
                ) {

                    resultado.add(c);

                    break;
                }
            }
        }

        return resultado;
    }

    // =========================
    // EXISTE CANCION
    // =========================

    public boolean existeCancion(
            ArrayList<Cancion> canciones,
            String nombre
    ) {

        return buscarCancion(
            canciones,
            nombre
        ) != null;
    }

    // =========================
    // ORDENAR
    // =========================

    public void ordenarCancionesAlf(
            ArrayList<Cancion> canciones
    ) {

        canciones.sort((a, b) ->

            a.getNombre()
             .compareTo(
                 b.getNombre()
             )
        );
    }
}