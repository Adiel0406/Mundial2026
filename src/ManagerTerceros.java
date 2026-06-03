import java.util.ArrayList;
import java.util.Collections;

public class ManagerTerceros {

    public ArrayList<EquipoGrupo> obtenerMejoresTerceros(
            ArrayList<Grupo> grupos,
            int cantidad
    ) {

        ArrayList<EquipoGrupo> terceros =
                new ArrayList<>();

        // =========================
        // OBTENER TERCEROS
        // =========================

        for (Grupo g : grupos) {

            ArrayList<EquipoGrupo> equipos =
                    g.getEquipos();

            if (equipos.size() >= 3) {

                terceros.add(equipos.get(2));
            }
        }

        // =========================
        // ORDENAR TERCEROS
        // =========================

        Collections.sort(

                terceros,

                (a, b) -> {

                    if (a.getPuntos() != b.getPuntos()) {
                        return b.getPuntos() - a.getPuntos();
                    }

                    if (a.diferenciaGol() != b.diferenciaGol()) {
                        return b.diferenciaGol() - a.diferenciaGol();
                    }

                    if (a.getGolesFavor() != b.getGolesFavor()) {
                        return b.getGolesFavor() - a.getGolesFavor();
                    }

                    return a.getPais()
                            .getNombre()
                            .compareTo(
                                    b.getPais().getNombre()
                            );
                }
        );

        // =========================
        // LIMITE SEGURO
        // =========================

        ArrayList<EquipoGrupo> mejores =
                new ArrayList<>();

        int limite =
                Math.min(cantidad, terceros.size());

        for (int i = 0; i < limite; i++) {

            mejores.add(terceros.get(i));
        }

        return mejores;
    }
}