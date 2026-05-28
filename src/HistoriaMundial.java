public class HistoriaMundial {

    private int anio;

    private String campeon;
    private String subcampeon;

    private String resultado;

    private String estadio;
    private String sede;

    private String contexto;

    public HistoriaMundial(
            int anio,
            String campeon,
            String subcampeon,
            String resultado,
            String estadio,
            String sede,
            String contexto
    ) {

        this.anio = anio;
        this.campeon = campeon;
        this.subcampeon = subcampeon;
        this.resultado = resultado;
        this.estadio = estadio;
        this.sede = sede;
        this.contexto = contexto;
    }

    public int getAnio() {
        return anio;
    }

    public String getCampeon() {
        return campeon;
    }

    public String getSubcampeon() {
        return subcampeon;
    }

    public String getResultado() {
        return resultado;
    }

    public String getEstadio() {
        return estadio;
    }

    public String getSede() {
        return sede;
    }

    public String getContexto() {
        return contexto;
    }

    @Override
    public String toString() {

        return anio
                + " - "
                + campeon;
    }
}