
public class Cantante {
	private String nombre;
	private String pais;
	private int edad;
	
	public Cantante(String nombre, String pais, int edad) {
		
		this.nombre = nombre;
		this.pais = pais;
		this.edad = edad;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}
	
	public int getEdad() {
		return edad;
	}
	
	public void setEdad(int edad) {
		this.edad = edad;
	}

	@Override
	public String toString() {
		return "Cantante [nombre=" + nombre + ", pais=" + pais + ", edad=" + edad + "]";
	}
}
