import java.io.Serializable;
import java.util.ArrayList;

public class FechaMundial implements Serializable{

    private static final long serialVersionUID = 1L;
	private int numero;
    private ArrayList<Partido> partidos;

    public FechaMundial(int numero) {
        this.numero = numero;
        partidos = new ArrayList<>();
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public ArrayList<Partido> getPartidos() {
        return partidos;
    }

    public void setPartidos(ArrayList<Partido> partidos) {
        this.partidos = partidos;
    }

    @Override
    public String toString() {
        return "FechaMundial [numero=" + numero +
               ", partidos=" + partidos + "]";
    }
}