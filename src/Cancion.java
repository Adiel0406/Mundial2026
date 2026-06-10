import java.util.ArrayList;

public class Cancion {

    private String nombre;

    private ArrayList<Cantante> cantantes;

    private ArrayList<LineaLetra> letraSincronizada;

    private String rutaAudio;

    private String rutaImagen;

    private double duracion;

    public Cancion(
            String nombre,
            ArrayList<Cantante> cantantes,
            ArrayList<LineaLetra> letraSincronizada,
            String rutaAudio,
            String rutaImagen,
            double duracion
    ) {

        this.nombre = nombre;

        this.cantantes = cantantes;

        this.letraSincronizada = letraSincronizada;

        this.rutaAudio = rutaAudio;

        this.rutaImagen = rutaImagen;

        this.duracion = duracion;
    }

    public String getNombre() {
        return nombre;
    }

    public ArrayList<Cantante> getCantantes() {
        return cantantes;
    }

    public ArrayList<LineaLetra> getLetraSincronizada() {
        return letraSincronizada;
    }

    public String getRutaAudio() {
        return rutaAudio;
    }

    public String getRutaImagen() {
        return rutaImagen;
    }

    public double getDuracion() {
        return duracion;
    }

    @Override
    public String toString() {

        return nombre
                + " | "
                + cantantes;
    }
}