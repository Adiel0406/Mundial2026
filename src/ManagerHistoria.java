import java.io.*;
import java.util.ArrayList;

public class ManagerHistoria {

    public ArrayList<HistoriaMundial>
    cargarHistorias(
            String archivo
    ) {

        ArrayList<HistoriaMundial>
            historias = new ArrayList<>();

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

                int anio =
                    Integer.parseInt(linea);

                String campeon =
                    br.readLine();

                String subcampeon =
                    br.readLine();

                String resultado =
                    br.readLine();

                String estadio =
                    br.readLine();

                String sede =
                    br.readLine();

                String contexto =
                    br.readLine();

                HistoriaMundial h =
                    new HistoriaMundial(
                        anio,
                        campeon,
                        subcampeon,
                        resultado,
                        estadio,
                        sede,
                        contexto
                    );

                historias.add(h);
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return historias;
    }
    
    public HistoriaMundial buscarPorAnio(
            ArrayList<HistoriaMundial> historias,
            int anio
    ) {

        for (HistoriaMundial h : historias) {

            if (h.getAnio() == anio) {

                return h;
            }
        }

        return null;
    }
    
    public void mostrarCampeones(
            ArrayList<HistoriaMundial> historias
    ) {

        for (HistoriaMundial h : historias) {

            System.out.println(
                h.getAnio()
                + " - "
                + h.getCampeon()
            );
        }
    }
    
    public int contarTitulos(
            ArrayList<HistoriaMundial> historias,
            String pais
    ) {

        int cantidad = 0;

        for (HistoriaMundial h : historias) {

            if (
                h.getCampeon()
                .equalsIgnoreCase(pais)
            ) {

                cantidad++;
            }
        }

        return cantidad;
    }
    
    public void mostrarFinal(
            HistoriaMundial h
    ) {

        System.out.println(
            "MUNDIAL "
            + h.getAnio()
        );

        System.out.println(
            h.getCampeon()
            + " "
            + h.getResultado()
            + " "
            + h.getSubcampeon()
        );

        System.out.println(
            "Estadio: "
            + h.getEstadio()
        );

        System.out.println(
            "Sede: "
            + h.getSede()
        );

        System.out.println(
            "Contexto:"
        );

        System.out.println(
            h.getContexto()
        );
    }
}