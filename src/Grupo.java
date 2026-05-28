import java.io.Serializable;
import java.util.ArrayList;

public class Grupo implements Serializable{

    private static final long serialVersionUID = 1L;

	private String nombre;

    private ArrayList<EquipoGrupo> equipos;
    private ArrayList<FechaMundial> fechas;

    public Grupo(
            String nombre
    ) {

        this.nombre = nombre;

        equipos = new ArrayList<>();
        fechas = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ArrayList<EquipoGrupo> getEquipos() {
        return equipos;
    }

    public void setEquipos(
            ArrayList<EquipoGrupo> equipos
    ) {

        this.equipos = equipos;
    }

    public void agregarEquipo(
            EquipoGrupo equipo
    ) {

        equipos.add(equipo);
    }
    

    public ArrayList<FechaMundial> getFechas() {
		return fechas;
	}

	public void setFechas(ArrayList<FechaMundial> fechas) {
		this.fechas = fechas;
	}

	@Override
	public String toString() {
		return "Grupo [nombre=" + nombre + ", equipos=" + equipos + ", fechas=" + fechas
				+ "]";
	}
	
	public EquipoGrupo buscarEquipo(
	        Pais pais
	) {

	    for (EquipoGrupo e : equipos) {

	        if (
	            e.getPais().getNombre()
	            .equals(pais.getNombre())
	        ) {

	            return e;
	        }
	    }

	    return null;
	}
}