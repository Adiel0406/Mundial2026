import java.io.Serializable;
import java.util.ArrayList;

public class FaseFinal implements Serializable{

    private static final long serialVersionUID = 1L;

	private String nombre;

    private ArrayList<Partido> partidos;

    public FaseFinal(
            String nombre
    ) {

        this.nombre = nombre;

        partidos = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ArrayList<Partido> getPartidos() {
        return partidos;
    }

    public void setPartidos(
            ArrayList<Partido> partidos
    ) {

        this.partidos = partidos;
    }

    public void agregarPartido(
            Partido partido
    ) {

        partidos.add(partido);
    }

    @Override
    public String toString() {

        return nombre + " " + partidos;
    }
}