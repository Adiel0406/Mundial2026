public class LineaLetra {

    private double tiempo;

    private String texto;

    public LineaLetra(
            double tiempo,
            String texto
    ) {

        this.tiempo = tiempo;
        this.texto = texto;
    }

    public double getTiempo() {
        return tiempo;
    }

    public String getTexto() {
        return texto;
    }
}