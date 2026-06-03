public class MiniTabla {

    private EquipoGrupo equipo;

    private int puntos;

    private int golesFavor;

    private int golesContra;

    public MiniTabla(
            EquipoGrupo equipo
    ) {

        this.equipo = equipo;
    }

    public EquipoGrupo getEquipo() {
        return equipo;
    }

    public int getPuntos() {
        return puntos;
    }

    public int getGolesFavor() {
        return golesFavor;
    }

    public int getGolesContra() {
        return golesContra;
    }

    public int diferenciaGol() {

        return golesFavor - golesContra;
    }

    // =========================
    // METODOS FALTANTES
    // =========================

    public void sumarPuntos(
            int puntos
    ) {

        this.puntos += puntos;
    }

    public void sumarGolesFavor(
            int goles
    ) {

        this.golesFavor += goles;
    }

    public void sumarGolesContra(
            int goles
    ) {

        this.golesContra += goles;
    }
}