import java.util.ArrayList;

public class Cancion {

    private String nombre;

    private ArrayList<String> artistas;
    private ArrayList<String> paises;

    private String letra;

    public Cancion(
            String nombre,
            ArrayList<String> artistas,
            ArrayList<String> paises,
            String letra
    ) {

        this.nombre = nombre;
        this.artistas = artistas;
        this.paises = paises;
        this.letra = letra;
    }

    public String getNombre() {
        return nombre;
    }

    public ArrayList<String> getArtistas() {
        return artistas;
    }

    public ArrayList<String> getPaises() {
        return paises;
    }

    public String getLetra() {
        return letra;
    }

    @Override
    public String toString() {

        return nombre
                + " | "
                + artistas;
    }
    
    public String resumen() {

        return nombre
                + " - "
                + artistas;
    }
}