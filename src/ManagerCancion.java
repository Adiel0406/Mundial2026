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
                (linea = br.readLine())
                != null
            ) {

                if (
                    linea.startsWith("=")
                    || linea.isBlank()
                ) {

                    continue;
                }

                // =====================
                // NOMBRE
                // =====================

                String nombre = linea;

                // =====================
                // ARTISTAS
                // =====================

                br.readLine();

                String lineaArtistas =
                    br.readLine();

                ArrayList<String> artistas =
                    new ArrayList<>();

                String[] artistasArray =
                    lineaArtistas.split(",");

                for (
                    String a
                    : artistasArray
                ) {

                    artistas.add(a.trim());
                }

                // =====================
                // PAISES
                // =====================

                br.readLine();

                String lineaPaises =
                    br.readLine();

                ArrayList<String> paises =
                    new ArrayList<>();

                String[] paisesArray =
                    lineaPaises.split(",");

                for (
                    String p
                    : paisesArray
                ) {

                    paises.add(p.trim());
                }

                // =====================
                // LETRA
                // =====================

                br.readLine();

                StringBuilder letra =
                    new StringBuilder();

                while (
                    !(linea = br.readLine())
                        .equals("FIN")
                ) {

                    letra.append(linea)
                         .append("\n");
                }

                Cancion cancion =
                    new Cancion(
                        nombre,
                        artistas,
                        paises,
                        letra.toString()
                    );

                canciones.add(cancion);
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return canciones;
    }
}