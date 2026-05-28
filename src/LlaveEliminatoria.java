import java.util.ArrayList;

public class LlaveEliminatoria {
	private String fase;
	private ArrayList<Partido> partidos;
	
	public LlaveEliminatoria(String fase) {
		super();
		this.fase = fase;
		this.partidos = new ArrayList<Partido>();
	}

	public String getFase() {
		return fase;
	}

	public void setFase(String fase) {
		this.fase = fase;
	}

	public ArrayList<Partido> getPartidos() {
		return partidos;
	}

	public void setPartidos(ArrayList<Partido> partidos) {
		this.partidos = partidos;
	}

	@Override
	public String toString() {
		return "LlaveEliminatoria [fase=" + fase + ", partidos=" + partidos + "]";
	}
}
