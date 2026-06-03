import java.util.ArrayList;
import java.util.Collections;

public class ManagerGrupo {
	
	public void registrarResultado(
	        Partido partido,
	        int golesLocal,
	        int golesVisitante
	) {

	    partido.setGolesLocal(golesLocal);

	    partido.setGolesVisitante(golesVisitante);

	    partido.setJugado(true);
	}
	
	public void actualizarTabla(Grupo grupo) {

	    // RESET

	    for (EquipoGrupo e : grupo.getEquipos()) {

	        e.setPuntos(0);

	        e.setGolesFavor(0);
	        e.setGolesContra(0);

	        e.setVictorias(0);
	        e.setEmpates(0);
	        e.setDerrotas(0);
	    }

	    // RECORRER FECHAS

	    for (FechaMundial fecha : grupo.getFechas()) {

	        for (Partido p : fecha.getPartidos()) {

	            if (!p.isJugado()) {
	                continue;
	            }

	            EquipoGrupo local =
	                    grupo.buscarEquipo(
	                            p.getLocal()
	                    );

	            EquipoGrupo visitante =
	                    grupo.buscarEquipo(
	                            p.getVisitante()
	                    );

	            // GOLES

	            local.setGolesFavor(
	                    local.getGolesFavor()
	                    + p.getGolesLocal()
	            );

	            local.setGolesContra(
	                    local.getGolesContra()
	                    + p.getGolesVisitante()
	            );

	            visitante.setGolesFavor(
	                    visitante.getGolesFavor()
	                    + p.getGolesVisitante()
	            );

	            visitante.setGolesContra(
	                    visitante.getGolesContra()
	                    + p.getGolesLocal()
	            );

	            // RESULTADO

	            if (
	                    p.getGolesLocal()
	                    > p.getGolesVisitante()
	            ) {

	                local.setPuntos(
	                        local.getPuntos() + 3
	                );

	                local.setVictorias(
	                        local.getVictorias() + 1
	                );

	                visitante.setDerrotas(
	                        visitante.getDerrotas() + 1
	                );
	            }

	            else if (
	                    p.getGolesVisitante()
	                    > p.getGolesLocal()
	            ) {

	                visitante.setPuntos(
	                        visitante.getPuntos() + 3
	                );

	                visitante.setVictorias(
	                        visitante.getVictorias() + 1
	                );

	                local.setDerrotas(
	                        local.getDerrotas() + 1
	                );
	            }

	            else {

	                local.setPuntos(
	                        local.getPuntos() + 1
	                );

	                visitante.setPuntos(
	                        visitante.getPuntos() + 1
	                );

	                local.setEmpates(
	                        local.getEmpates() + 1
	                );

	                visitante.setEmpates(
	                        visitante.getEmpates() + 1
	                );
	            }
	        }
	    }
	}
	
	public Partido buscarPartido(
	        Grupo grupo,
	        Pais a,
	        Pais b
	) {

	    for (FechaMundial fecha : grupo.getFechas()) {

	        for (Partido p : fecha.getPartidos()) {

	            boolean condicion1 =

	                    p.getLocal().getNombre()
	                            .equals(a.getNombre())

	                    &&

	                    p.getVisitante().getNombre()
	                            .equals(b.getNombre());

	            boolean condicion2 =

	                    p.getLocal().getNombre()
	                            .equals(b.getNombre())

	                    &&

	                    p.getVisitante().getNombre()
	                            .equals(a.getNombre());

	            if (
	                    condicion1
	                    || condicion2
	            ) {

	                return p;
	            }
	        }
	    }

	    return null;
	}
	
	private int compararEquipos(
	        EquipoGrupo a,
	        EquipoGrupo b
	) {

	    // PUNTOS

	    if (a.getPuntos() != b.getPuntos()) {

	        return b.getPuntos() - a.getPuntos();
	    }

	    // DIFERENCIA DE GOL

	    int diffA =
	            a.getGolesFavor()
	            - a.getGolesContra();

	    int diffB =
	            b.getGolesFavor()
	            - b.getGolesContra();

	    if (diffA != diffB) {

	        return diffB - diffA;
	    }

	    // GOLES A FAVOR

	    if (
	            a.getGolesFavor()
	            != b.getGolesFavor()
	    ) {

	        return
	                b.getGolesFavor()
	                - a.getGolesFavor();
	    }

	    // ESTABILIDAD

	    return a.getPais()
	            .getNombre()
	            .compareTo(
	                    b.getPais().getNombre()
	            );
	}
	
	public void ordenarGrupo(Grupo grupo) {

	    Collections.sort(

	            grupo.getEquipos(),

	            (a, b) -> compararEquipos(a, b)
	    );

	    resolverEmpates(grupo);
	}
	
	private void resolverEmpates(
	        Grupo grupo
	) {

	    ArrayList<EquipoGrupo> equipos =
	            grupo.getEquipos();

	    for (
	            int i = 0;
	            i < equipos.size() - 1;
	            i++
	    ) {

	        EquipoGrupo a =
	                equipos.get(i);

	        EquipoGrupo b =
	                equipos.get(i + 1);

	        // =====================
	        // VERIFICAR EMPATE
	        // =====================

	        boolean mismoPuntaje =

	                a.getPuntos()
	                == b.getPuntos();

	        boolean mismaDG =

	                a.diferenciaGol()
	                == b.diferenciaGol();

	        boolean mismosGF =

	                a.getGolesFavor()
	                == b.getGolesFavor();

	        if (
	                mismoPuntaje
	                &&
	                mismaDG
	                &&
	                mismosGF
	        ) {

	            // =====================
	            // EMPATE ENTRE 2
	            // =====================

	            Partido directo =
	                    buscarPartido(
	                            grupo,
	                            a.getPais(),
	                            b.getPais()
	                    );

	            if (
	                    directo != null
	                    &&
	                    directo.isJugado()
	            ) {

	                boolean ganaB =

	                        (
	                                directo.getLocal()
	                                        .equals(b.getPais())
	                                &&
	                                directo.getGolesLocal()
	                                >
	                                directo.getGolesVisitante()
	                        )

	                        ||

	                        (
	                                directo.getVisitante()
	                                        .equals(b.getPais())
	                                &&
	                                directo.getGolesVisitante()
	                                >
	                                directo.getGolesLocal()
	                        );

	                if (ganaB) {

	                    equipos.set(i, b);

	                    equipos.set(i + 1, a);
	                }
	            }
	        }
	    }
	    
	    resolverTripleEmpate(grupo);
	}
	
	public boolean grupoCompleto(
	        Grupo grupo
	) {

	    for (FechaMundial fecha : grupo.getFechas()) {

	        for (Partido p : fecha.getPartidos()) {

	            if (!p.isJugado()) {

	                return false;
	            }
	        }
	    }

	    return true;
	}
	
	public ArrayList<EquipoGrupo> obtenerClasificados(
	        Grupo grupo
	) {

	    actualizarTabla(grupo);

	    ordenarGrupo(grupo);

	    ArrayList<EquipoGrupo> clasificados =
	            new ArrayList<>();

	    clasificados.add(
	            grupo.getEquipos().get(0)
	    );

	    clasificados.add(
	            grupo.getEquipos().get(1)
	    );

	    return clasificados;
	}
	
	public int obtenerPosicion(
	        Grupo grupo,
	        Pais pais
	) {

	    ordenarGrupo(grupo);

	    for (
	            int i = 0;
	            i < grupo.getEquipos().size();
	            i++
	    ) {

	        if (

	                grupo.getEquipos()
	                        .get(i)
	                        .getPais()
	                        .getNombre()
	                        .equals(
	                                pais.getNombre()
	                        )
	        ) {

	            return i + 1;
	        }
	    }

	    return -1;
	}
	
	private ArrayList<MiniTabla> crearMiniTabla(
	        Grupo grupo,
	        ArrayList<EquipoGrupo> empatados
	) {

	    ArrayList<MiniTabla> mini =
	            new ArrayList<>();

	    // CREAR MINI EQUIPOS

	    for (EquipoGrupo e : empatados) {

	        mini.add(
	                new MiniTabla(e)
	        );
	    }

	    // RECORRER PARTIDOS

	    for (FechaMundial fecha : grupo.getFechas()) {

	        for (Partido p : fecha.getPartidos()) {

	            if (!p.isJugado()) {
	                continue;
	            }

	            EquipoGrupo local = null;
	            EquipoGrupo visitante = null;

	            // BUSCAR SI AMBOS ESTAN EMPATADOS

	            for (EquipoGrupo e : empatados) {

	                if (
	                        e.getPais().getNombre()
	                                .equals(
	                                        p.getLocal().getNombre()
	                                )
	                ) {

	                    local = e;
	                }

	                if (
	                        e.getPais().getNombre()
	                                .equals(
	                                        p.getVisitante().getNombre()
	                                )
	                ) {

	                    visitante = e;
	                }
	            }

	            // SI NO SON ENTRE EMPATADOS

	            if (
	                    local == null
	                    || visitante == null
	            ) {

	                continue;
	            }

	            MiniTabla ml = null;
	            MiniTabla mv = null;

	            for (MiniTabla m : mini) {

	                if (m.getEquipo() == local) {
	                    ml = m;
	                }

	                if (m.getEquipo() == visitante) {
	                    mv = m;
	                }
	            }

	            // GOLES

	            ml.sumarGolesFavor(
	                    ml.getGolesFavor()
	                    + p.getGolesLocal()
	            );

	            ml.sumarGolesContra(
	                    ml.getGolesContra()
	                    + p.getGolesVisitante()
	            );

	            mv.sumarGolesFavor(
	                    mv.getGolesFavor()
	                    + p.getGolesVisitante()
	            );

	            mv.sumarGolesContra(
	                    mv.getGolesContra()
	                    + p.getGolesLocal()
	            );

	            // PUNTOS

	            if (
	                    p.getGolesLocal()
	                    > p.getGolesVisitante()
	            ) {

	                ml.sumarPuntos(
	                        ml.getPuntos() + 3
	                );
	            }

	            else if (
	                    p.getGolesVisitante()
	                    > p.getGolesLocal()
	            ) {

	                mv.sumarPuntos(
	                        mv.getPuntos() + 3
	                );
	            }

	            else {

	                ml.sumarPuntos(
	                        ml.getPuntos() + 1
	                );

	                mv.sumarPuntos(
	                        mv.getPuntos() + 1
	                );
	            }
	        }
	    }

	    return mini;
	}
	
	private void resolverTripleEmpate(
	        Grupo grupo
	) {

	    ArrayList<EquipoGrupo> equipos =
	            grupo.getEquipos();

	    for (
	            int i = 0;
	            i < equipos.size() - 2;
	            i++
	    ) {

	        EquipoGrupo a =
	                equipos.get(i);

	        EquipoGrupo b =
	                equipos.get(i + 1);

	        EquipoGrupo c =
	                equipos.get(i + 2);

	        // =====================
	        // VERIFICAR TRIPLE EMPATE
	        // =====================

	        boolean mismoPuntaje =

	                a.getPuntos()
	                == b.getPuntos()

	                &&

	                b.getPuntos()
	                == c.getPuntos();

	        boolean mismaDG =

	                a.diferenciaGol()
	                == b.diferenciaGol()

	                &&

	                b.diferenciaGol()
	                == c.diferenciaGol();

	        boolean mismosGF =

	                a.getGolesFavor()
	                == b.getGolesFavor()

	                &&

	                b.getGolesFavor()
	                == c.getGolesFavor();

	        if (
	                mismoPuntaje
	                &&
	                mismaDG
	                &&
	                mismosGF
	        ) {

	            // =====================
	            // CREAR LISTA EMPATADOS
	            // =====================

	            ArrayList<EquipoGrupo> empatados =
	                    new ArrayList<>();

	            empatados.add(a);
	            empatados.add(b);
	            empatados.add(c);

	            // =====================
	            // MINI TABLA
	            // =====================

	            ArrayList<MiniTabla> mini =
	                    crearMiniTabla(
	                            grupo,
	                            empatados
	                    );

	            // =====================
	            // ORDENAR MINI TABLA
	            // =====================

	            Collections.sort(

	                    mini,

	                    (m1, m2) -> {

	                        // PUNTOS

	                        if (
	                                m1.getPuntos()
	                                != m2.getPuntos()
	                        ) {

	                            return
	                                    m2.getPuntos()
	                                    - m1.getPuntos();
	                        }

	                        // DIFERENCIA

	                        if (
	                                m1.diferenciaGol()
	                                != m2.diferenciaGol()
	                        ) {

	                            return
	                                    m2.diferenciaGol()
	                                    - m1.diferenciaGol();
	                        }

	                        // GOLES FAVOR

	                        if (
	                                m1.getGolesFavor()
	                                != m2.getGolesFavor()
	                        ) {

	                            return
	                                    m2.getGolesFavor()
	                                    - m1.getGolesFavor();
	                        }

	                        return m1.getEquipo()
	                                .getPais()
	                                .getNombre()
	                                .compareTo(
	                                        m2.getEquipo()
	                                                .getPais()
	                                                .getNombre()
	                                );
	                    }
	            );

	            // =====================
	            // REEMPLAZAR ORDEN
	            // =====================

	            equipos.set(
	                    i,
	                    mini.get(0).getEquipo()
	            );

	            equipos.set(
	                    i + 1,
	                    mini.get(1).getEquipo()
	            );

	            equipos.set(
	                    i + 2,
	                    mini.get(2).getEquipo()
	            );
	        }
	    }
	}
}