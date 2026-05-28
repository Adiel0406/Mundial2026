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
}