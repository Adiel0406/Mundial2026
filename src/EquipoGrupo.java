import java.io.Serializable;

public class EquipoGrupo implements Serializable{

    private static final long serialVersionUID = 1L;

	private Pais pais;

    private int puntos;

    private int golesFavor;
    private int golesContra;

    private int victorias;
    private int empates;
    private int derrotas;

    public EquipoGrupo(Pais pais) {

        this.pais = pais;

        puntos = 0;

        golesFavor = 0;
        golesContra = 0;

        victorias = 0;
        empates = 0;
        derrotas = 0;
    }

    public Pais getPais() {
        return pais;
    }

    public void setPais(Pais pais) {
        this.pais = pais;
    }

    public int getPuntos() {
        return puntos;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }

    public int getGolesFavor() {
        return golesFavor;
    }

    public void setGolesFavor(int golesFavor) {
        this.golesFavor = golesFavor;
    }

    public int getGolesContra() {
        return golesContra;
    }

    public void setGolesContra(int golesContra) {
        this.golesContra = golesContra;
    }

    public int getVictorias() {
        return victorias;
    }

    public void setVictorias(int victorias) {
        this.victorias = victorias;
    }

    public int getEmpates() {
        return empates;
    }

    public void setEmpates(int empates) {
        this.empates = empates;
    }

    public int getDerrotas() {
        return derrotas;
    }

    public void setDerrotas(int derrotas) {
        this.derrotas = derrotas;
    }

    public int diferenciaGol() {

        return golesFavor - golesContra;
    }

    @Override
    public String toString() {

        return pais.getNombre()
                + " | "
                + puntos
                + " pts | DG: "
                + diferenciaGol();
    }
}
