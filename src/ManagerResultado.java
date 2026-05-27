import java.util.*;
import java.io.*;

public class ManagerResultado {

    @SuppressWarnings("unchecked")
    public ArrayList<Resultado> cargarResultados(
            String archivo
    ) {

        ArrayList<Resultado> resultados =
            new ArrayList<>();

        File file = new File(archivo);

        if (!file.exists()) {

            return resultados;
        }

        try (

            ObjectInputStream ois =
                new ObjectInputStream(
                    new FileInputStream(file)
                )

        ) {

            resultados =
                (ArrayList<Resultado>)
                    ois.readObject();

        } catch (Exception e) {

            e.printStackTrace();
        }

        return resultados;
    }

    public void guardarResultado(
            Resultado resultado,
            String archivo
    ) {

        ArrayList<Resultado> resultados =
            cargarResultados(archivo);

        resultados.add(resultado);

        try (

            ObjectOutputStream oos =
                new ObjectOutputStream(
                    new FileOutputStream(archivo)
                )

        ) {

            oos.writeObject(resultados);

        } catch (Exception e) {

            e.printStackTrace();
        }
    }
}