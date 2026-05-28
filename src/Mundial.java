import java.io.Serializable;
import java.util.ArrayList;

public class Mundial implements Serializable{
	private static final long serialVersionUID = 1L;

	private ArrayList<Grupo> grupos;

	private FaseFinal dieciseisavos;
	private FaseFinal octavos;
	private FaseFinal cuartos;
	private FaseFinal semifinal;
	private FaseFinal finalMundial;

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

	public FaseFinal getDieciseisavos() {
		return dieciseisavos;
	}

	public void setDieciseisavos(FaseFinal dieciseisavos) {
		this.dieciseisavos = dieciseisavos;
	}

	public FaseFinal getOctavos() {
		return octavos;
	}

	public void setOctavos(FaseFinal octavos) {
		this.octavos = octavos;
	}

	public FaseFinal getCuartos() {
		return cuartos;
	}

	public void setCuartos(FaseFinal cuartos) {
		this.cuartos = cuartos;
	}

	public FaseFinal getSemifinal() {
		return semifinal;
	}

	public void setSemifinal(FaseFinal semifinal) {
		this.semifinal = semifinal;
	}

	public FaseFinal getFinalMundial() {
		return finalMundial;
	}

	public void setFinalMundial(FaseFinal finalMundial) {
		this.finalMundial = finalMundial;
	}

	public Pais getCampeon() {
		return campeon;
	}

	public void setCampeon(Pais campeon) {
		this.campeon = campeon;
	}

	@Override
	public String toString() {
		return "Mundial [grupos=" + grupos + ", dieciseisavos=" + dieciseisavos + ", octavos=" + octavos + ", cuartos="
				+ cuartos + ", semifinal=" + semifinal + ", finalMundial=" + finalMundial + ", campeon=" + campeon
				+ "]";
	}
}
