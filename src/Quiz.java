import java.io.DataInputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Quiz {

    private ArrayList<Pregunta> preguntas;

    private String nombre;

    private String dificultad;

    // =================================================
    // CONSTRUCTOR
    // =================================================

    public Quiz(
            ArrayList<Pregunta> preguntas,
            String nombre,
            String dificultad
    ) {

        this.preguntas = preguntas;
        this.nombre = nombre;
        this.dificultad = dificultad;
    }

    // =================================================
    // GETTERS Y SETTERS
    // =================================================

    public ArrayList<Pregunta> getPreguntas() {

        return preguntas;
    }

    public void setPreguntas(
            ArrayList<Pregunta> preguntas
    ) {

        this.preguntas = preguntas;
    }

    public String getNombre() {

        return nombre;
    }

    public void setNombre(String nombre) {

        this.nombre = nombre;
    }

    public String getDificultad() {

        return dificultad;
    }

    public void setDificultad(
            String dificultad
    ) {

        this.dificultad = dificultad;
    }

    // =================================================
    // TO STRING
    // =================================================

    @Override
    public String toString() {

        return "Quiz [preguntas=" + preguntas
                + ", nombre=" + nombre
                + ", dificultad=" + dificultad + "]";
    }

    // =================================================
    // LEER PREGUNTAS BINARIAS
    // =================================================

    public static ArrayList<Pregunta> leerPreguntasBin(
            String archivo
    ) {

        ArrayList<Pregunta> preguntasLeidas =
                new ArrayList<>();

        try (

                FileInputStream fis =
                        new FileInputStream(archivo);

                DataInputStream dis =
                        new DataInputStream(fis)

        ) {

            while (true) {

                // =========================================
                // LEER TIPO
                // =========================================

                String nombreTipo =
                        dis.readUTF();

                Tipo tipoObjeto =
                        Tipo.valueOf(nombreTipo);

                // =========================================
                // LEER DATOS GENERALES
                // =========================================

                String nombrePregunta =
                        dis.readUTF();

                String respuesta =
                        dis.readUTF();

                int puntuacion =
                        dis.readInt();

                // =========================================
                // PREGUNTA VF
                // =========================================

                if (
                        tipoObjeto
                                == Tipo.VERDADERO_FALSO
                ) {

                    PreguntaVF preguntaVF =
                            new PreguntaVF(

                                    tipoObjeto,

                                    nombrePregunta,

                                    respuesta,

                                    puntuacion
                            );

                    preguntasLeidas.add(
                            preguntaVF
                    );
                }

                // =========================================
                // PREGUNTA OPCION MULTIPLE
                // =========================================

                else if (
                        tipoObjeto
                                == Tipo.OPCION_MULTIPLE
                ) {

                    int cantidadOpciones =
                            dis.readInt();

                    ArrayList<String> opciones =
                            new ArrayList<>();

                    for (
                            int i = 0;
                            i < cantidadOpciones;
                            i++
                    ) {

                        opciones.add(
                                dis.readUTF()
                        );
                    }

                    PreguntaOpMultiple preguntaM =
                            new PreguntaOpMultiple(

                                    tipoObjeto,

                                    nombrePregunta,

                                    respuesta,

                                    puntuacion,

                                    opciones
                            );

                    preguntasLeidas.add(
                            preguntaM
                    );
                }
            }
        }

        // =============================================
        // FIN DEL ARCHIVO
        // =============================================

        catch (EOFException e) {

            System.out.println(
                    "Lectura completada correctamente."
            );
        }

        // =============================================
        // ARCHIVO NO ENCONTRADO
        // =============================================

        catch (FileNotFoundException e) {

            System.out.println(
                    "No se encontró el archivo: "
                            + archivo
            );
        }

        // =============================================
        // ERROR GENERAL
        // =============================================

        catch (Exception e) {

            System.out.println(
                    "Error leyendo archivo binario."
            );

            e.printStackTrace();
        }

        return preguntasLeidas;
    }

    // =================================================
    // ELEGIR PREGUNTAS
    // =================================================

    public List<Pregunta> elegirPreguntas(

            String dificultad,

            int cantidad

    ) throws IllegalArgumentException {

        String archivo;

        // =============================================
        // SELECCIONAR ARCHIVO
        // =============================================

        if (
                dificultad.equalsIgnoreCase(
                        "Facil"
                )
        ) {

            archivo =
                    Archivos.archivoPreguntasF;
        }

        else if (
                dificultad.equalsIgnoreCase(
                        "Medio"
                )
        ) {

            archivo =
                    Archivos.archivoPreguntasM;
        }

        else {

            archivo =
                    Archivos.archivoPreguntasD;
        }

        // =============================================
        // LEER PREGUNTAS
        // =============================================

        ArrayList<Pregunta> preguntasLeidas =
                leerPreguntasBin(archivo);

        // =============================================
        // VALIDAR CANTIDAD
        // =============================================

        if (
                preguntasLeidas.size()
                        < cantidad
        ) {

            throw new IllegalArgumentException(

                    "No hay suficientes preguntas disponibles."
            );
        }

        // =============================================
        // MEZCLAR
        // =============================================

        Collections.shuffle(
                preguntasLeidas
        );

        // =============================================
        // RETORNAR SUBLISTA
        // =============================================

        return preguntasLeidas.subList(
                0,
                cantidad
        );
    }

    // =================================================
    // CALCULAR PUNTUACION MAXIMA
    // =================================================

    public int calcularPuntuacionMaxima() {

        int total = 0;

        for (Pregunta p : preguntas) {

            if (p != null) {

                total += p.getPuntuacion();
            }
        }

        return total;
    }

    // =================================================
    // OBTENER VF
    // =================================================

    public List<Pregunta> obtenerVerdaderoFalso() {

        List<Pregunta> filtradas =
                new ArrayList<>();

        for (Pregunta p : preguntas) {

            if (p instanceof PreguntaVF) {

                filtradas.add(p);
            }
        }

        return filtradas;
    }

    // =================================================
    // OBTENER MULTIPLE
    // =================================================

    public List<Pregunta> obtenerOpcionMultiple() {

        List<Pregunta> filtradas =
                new ArrayList<>();

        for (Pregunta p : preguntas) {

            if (
                    p instanceof PreguntaOpMultiple
            ) {

                filtradas.add(p);
            }
        }

        return filtradas;
    }
}