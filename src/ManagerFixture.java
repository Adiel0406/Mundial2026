import java.util.ArrayList;

public class ManagerFixture {
	
	private ManagerPais managerPais;
	
	public ManagerFixture(
            ManagerPais managerPais
    ) {

        this.managerPais = managerPais;
    }
	
	public Grupo crearGrupo(
	        String letra,
	        String pais1,
	        String pais2,
	        String pais3,
	        String pais4
	) {

	    Grupo grupo = new Grupo(letra);

	    Pais p1 = managerPais.buscarPais(pais1);
	    Pais p2 = managerPais.buscarPais(pais2);
	    Pais p3 = managerPais.buscarPais(pais3);
	    Pais p4 = managerPais.buscarPais(pais4);

	    grupo.agregarEquipo(
	        new EquipoGrupo(p1)
	    );

	    grupo.agregarEquipo(
	        new EquipoGrupo(p2)
	    );

	    grupo.agregarEquipo(
	        new EquipoGrupo(p3)
	    );

	    grupo.agregarEquipo(
	        new EquipoGrupo(p4)
	    );

	    // ======================
	    // FECHA 1
	    // ======================

	    FechaMundial fecha1 =
	        new FechaMundial(1);

	    fecha1.getPartidos().add(
	        new Partido(p1, p2)
	    );

	    fecha1.getPartidos().add(
	        new Partido(p3, p4)
	    );

	    // ======================
	    // FECHA 2
	    // ======================

	    FechaMundial fecha2 =
	        new FechaMundial(2);

	    fecha2.getPartidos().add(
	        new Partido(p1, p3)
	    );

	    fecha2.getPartidos().add(
	        new Partido(p2, p4)
	    );

	    // ======================
	    // FECHA 3
	    // ======================

	    FechaMundial fecha3 =
	        new FechaMundial(3);

	    fecha3.getPartidos().add(
	        new Partido(p1, p4)
	    );

	    fecha3.getPartidos().add(
	        new Partido(p2, p3)
	    );

	    grupo.getFechas().add(fecha1);
	    grupo.getFechas().add(fecha2);
	    grupo.getFechas().add(fecha3);

	    return grupo;
	}
	
	public ArrayList<Grupo> crearGrupos() {

	    ArrayList<Grupo> grupos =
	        new ArrayList<>();

	    grupos.add(
	        crearGrupo(
	            "A",
	            "Mexico",
	            "Sudafrica",
	            "Corea del Sur",
	            "Chequia"
	        )
	    );

	    grupos.add(
	        crearGrupo(
	            "B",
	            "Canada",
	            "Bosnia y Herzegovina",
	            "Catar",
	            "Suiza"
	        )
	    );

	    grupos.add(
	        crearGrupo(
	            "C",
	            "Brasil",
	            "Marruecos",
	            "Haiti",
	            "Escocia"
	        )
	    );
	    
	    grupos.add(
		     crearGrupo(
		        "D",
		        "Estados Unidos",
		        "Paraguay",
		        "Australia",
		        "Turquia"
		    )
		);
	    
	    grupos.add(
			 crearGrupo(
			    "E",
			    "Alemania",
			    "Curazao",
			    "Costa de Marfil",
			    "Ecuador"
			)
	    );
	    
	    grupos.add(
	    	crearGrupo(
				"F",
				"Paises Bajos",
				"Japon",
				"Suecia",
				"Tunez"
			)
		);
	    
	    grupos.add(
		    crearGrupo(
		    	"G",
				"Belgica",
				"Egipto",
				"Iran",
				"Nueva Zelanda"
			)
		);
	    
	    grupos.add(
			crearGrupo(
			    "H",
				"España",
				"Cabo Verde",
				"Arabia Saudita",
				"Uruguay"
			)
		);
	    
	    grupos.add(
			crearGrupo(
				"I",
				"Francia",
				"Senegal",
				"Irak",
				"Noruega"
			)
		);
	    
	    grupos.add(
			crearGrupo(
				"J",
				"Argentina",
				"Argelia",
				"Austria",
				"Jordania"
			)
		);
	    
	    grupos.add(
			crearGrupo(
				"K",
				"Portugal",
				"RD Congo",
				"Uzbekistan",
				"Colombia"
			)
		);
	    
	    grupos.add(
			crearGrupo(
				"L",
				"Inglaterra",
				"Croacia",
				"Ghana",
				"Panama"
			)
		);

	    return grupos;
	}
	
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

	    // REINICIAR ESTADISTICAS

	    for (EquipoGrupo e : grupo.getEquipos()) {

	        e.setPuntos(0);
	        e.setGolesFavor(0);
	        e.setGolesContra(0);
	        e.setVictorias(0);
	        e.setEmpates(0);
	        e.setDerrotas(0);
	    }

	    // RECORRER PARTIDOS
	    
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

		        } else if (
		            p.getGolesLocal()
		            < p.getGolesVisitante()
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

		        } else {

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
	
	public void ordenarGrupo(Grupo grupo) {

	    grupo.getEquipos().sort((a, b) -> {

	        // =====================
	        // 1. PUNTOS
	        // =====================

	        if (
	            b.getPuntos()
	            != a.getPuntos()
	        ) {

	            return
	                b.getPuntos()
	                - a.getPuntos();
	        }

	        // =====================
	        // 2. ENFRENTAMIENTO DIRECTO
	        // =====================

	        Partido directo =
	            buscarPartido(
	                grupo,
	                a.getPais(),
	                b.getPais()
	            );

	        if (
	            directo != null
	            && directo.isJugado()
	        ) {

	            boolean ganaA =

	                directo.getLocal()
	                    .getNombre()
	                    .equals(
	                        a.getPais().getNombre()
	                    )

	                &&

	                directo.getGolesLocal()
	                >
	                directo.getGolesVisitante();

	            boolean ganaB =

	                directo.getVisitante()
	                    .getNombre()
	                    .equals(
	                        a.getPais().getNombre()
	                    )

	                &&

	                directo.getGolesVisitante()
	                >
	                directo.getGolesLocal();

	            if (ganaA) {

	                return -1;
	            }

	            if (ganaB) {

	                return 1;
	            }
	        }

	        // =====================
	        // 3. DIFERENCIA DE GOL
	        // =====================

	        int diffA =
	            a.getGolesFavor()
	            - a.getGolesContra();

	        int diffB =
	            b.getGolesFavor()
	            - b.getGolesContra();

	        if (diffB != diffA) {

	            return diffB - diffA;
	        }

	        // =====================
	        // 4. GOLES A FAVOR
	        // =====================

	        return
	            b.getGolesFavor()
	            - a.getGolesFavor();
	    });
	}
	
	public ArrayList<EquipoGrupo> obtenerMejoresTerceros(
	        ArrayList<Grupo> grupos
	) {

	    ArrayList<EquipoGrupo> terceros =
	        new ArrayList<>();

	    for (Grupo g : grupos) {

	        ordenarGrupo(g);

	        terceros.add(
	            g.getEquipos().get(2)
	        );
	    }

	    // ordenar terceros

	    terceros.sort((a, b) -> {

	        if (
	            b.getPuntos()
	            != a.getPuntos()
	        ) {

	            return
	                b.getPuntos()
	                - a.getPuntos();
	        }

	        int diffA =
	            a.getGolesFavor()
	            - a.getGolesContra();

	        int diffB =
	            b.getGolesFavor()
	            - b.getGolesContra();

	        return diffB - diffA;
	    });

	    ArrayList<EquipoGrupo> mejores =
	        new ArrayList<>();

	    for (
	    	    int i = 0;
	    	    i < 8 && i < terceros.size();
	    	    i++
	    	) {

	    	    mejores.add(
	    	        terceros.get(i)
	    	    );
	    	}

	    return mejores;
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
	
	public ArrayList<EquipoGrupo> obtenerClasificados(
	        ArrayList<Grupo> grupos
	) {

	    ArrayList<EquipoGrupo> clasificados =
	        new ArrayList<>();

	    // PRIMEROS Y SEGUNDOS

	    for (Grupo g : grupos) {

	        actualizarTabla(g);

	        ordenarGrupo(g);

	        clasificados.add(
	            g.getEquipos().get(0)
	        );

	        clasificados.add(
	            g.getEquipos().get(1)
	        );
	    }

	    // MEJORES TERCEROS

	    ArrayList<EquipoGrupo> terceros =
	        obtenerMejoresTerceros(grupos);

	    clasificados.addAll(terceros);

	    return clasificados;
	}
}
