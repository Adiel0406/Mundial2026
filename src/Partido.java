import java.io.Serializable;

public class Partido implements Serializable {

    private static final long serialVersionUID = 1L;
	private Pais local;
    private Pais visitante;

    private int golesLocal;
    private int golesVisitante;

    private boolean jugado;
    
    private int penalesLocal;
    private int penalesVisitante;

    public Partido(
            Pais local,
            Pais visitante
    ) {

        this.local = local;
        this.visitante = visitante;

        golesLocal = 0;
        golesVisitante = 0;

        jugado = false;
        penalesLocal = 0;
        penalesVisitante = 0;
    }

    public Pais getLocal() {
        return local;
    }

    public void setLocal(Pais local) {
        this.local = local;
    }

    public Pais getVisitante() {
        return visitante;
    }

    public void setVisitante(Pais visitante) {
        this.visitante = visitante;
    }

    public int getGolesLocal() {
        return golesLocal;
    }

    public void setGolesLocal(int golesLocal) {
        this.golesLocal = golesLocal;
    }

    public int getGolesVisitante() {
        return golesVisitante;
    }

    public void setGolesVisitante(int golesVisitante) {
        this.golesVisitante = golesVisitante;
    }

    public boolean isJugado() {
        return jugado;
    }

    public void setJugado(boolean jugado) {
        this.jugado = jugado;
    }
    
    public int getPenalesLocal() {
        return penalesLocal;
    }

    public void setPenalesLocal(
            int penalesLocal
    ) {

        this.penalesLocal = penalesLocal;
    }

    public int getPenalesVisitante() {
        return penalesVisitante;
    }

    public void setPenalesVisitante(
            int penalesVisitante
    ) {

        this.penalesVisitante = penalesVisitante;
    }

    @Override
    public String toString() {

        String texto =

            local.getNombre()
            + " "
            + golesLocal
            + " - "
            + golesVisitante
            + " "
            + visitante.getNombre();

        if (
            golesLocal == golesVisitante
            && jugado
        ) {

            texto +=
                " (Penales "
                + penalesLocal
                + "-"
                + penalesVisitante
                + ")";
        }

        return texto;
    }
}
