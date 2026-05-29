public class Estadio {

    private String pais;

    private String nombre;

    private String ciudad;

    private String equipo;

    private int capacidad;

    private String infraestructura;

	public Estadio(String pais, String nombre, String ciudad, String equipo, int capacidad, String infraestructura) {
		super();
		this.pais = pais;
		this.nombre = nombre;
		this.ciudad = ciudad;
		this.equipo = equipo;
		this.capacidad = capacidad;
		this.infraestructura = infraestructura;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public String getEquipo() {
		return equipo;
	}

	public void setEquipo(String equipo) {
		this.equipo = equipo;
	}

	public int getCapacidad() {
		return capacidad;
	}

	public void setCapacidad(int capacidad) {
		this.capacidad = capacidad;
	}

	public String getInfraestructura() {
		return infraestructura;
	}

	public void setInfraestructura(String infraestructura) {
		this.infraestructura = infraestructura;
	}

	@Override
	public String toString() {
		return "Estadio [pais=" + pais + ", nombre=" + nombre + ", ciudad=" + ciudad + ", equipo=" + equipo
				+ ", capacidad=" + capacidad + ", infraestructura=" + infraestructura + "]";
	}
}