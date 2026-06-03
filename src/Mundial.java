import java.io.Serializable;
import java.util.ArrayList;

public class Mundial implements Serializable{
	private static final long serialVersionUID = 1L;

	private ArrayList<Grupo> grupos;

	private Pais campeon;
	
	public Mundial() {

	    grupos = new ArrayList<>();
	}

	public ArrayList<Grupo> getGrupos() {
		return grupos;
	}

	public void setGrupos(ArrayList<Grupo> grupos) {
		this.grupos = grupos;
	}

	public Pais getCampeon() {
		return campeon;
	}

	public void setCampeon(Pais campeon) {
		this.campeon = campeon;
	}

	@Override
	public String toString() {
		return "Mundial [grupos=" + grupos + "]";
	}
	
	public Pais obtenerGanador(
	        Partido partido
	) {

	    if (!partido.isJugado()) {

	        return null;
	    }

	    // GANA NORMAL

	    if (
	        partido.getGolesLocal()
	        >
	        partido.getGolesVisitante()
	    ) {

	        return partido.getLocal();
	    }

	    if (
	        partido.getGolesVisitante()
	        >
	        partido.getGolesLocal()
	    ) {

	        return partido.getVisitante();
	    }

	    // EMPATE -> PENALES

	    if (
	        partido.getPenalesLocal()
	        >
	        partido.getPenalesVisitante()
	    ) {

	        return partido.getLocal();
	    }

	    return partido.getVisitante();
	}
}
