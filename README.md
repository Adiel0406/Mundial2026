
# 🏆 FIFA World Cup 2026 Simulator

## 📌 Descripción

Simulador de la Copa Mundial de la FIFA 2026 desarrollado en Java con JavaFX. El proyecto permite gestionar los grupos, registrar resultados de los partidos, calcular las tablas de posiciones y determinar qué selecciones clasifican a la siguiente fase.

El sistema incorpora criterios de desempate para ordenar correctamente a las selecciones, incluyendo el enfrentamiento directo y la resolución de empates múltiples mediante una mini tabla.

## ✨ Funcionalidades

- 🌎 Gestión de los 12 grupos del Mundial (A–L).
- ⚽ Registro y administración de resultados de los partidos.
- 📊 Actualización automática de las tablas de posiciones.
- 🥇 Ordenamiento de selecciones según los criterios de desempate.
- 🤝 Resolución de empates entre dos o más selecciones mediante enfrentamientos directos y mini tablas.
- 🎟️ Clasificación automática de los dos primeros equipos de cada grupo.
- ⭐ Selección de los ocho mejores terceros.
- 🟢 Identificación visual de las selecciones clasificadas.
- 🔴 Identificación visual de las selecciones eliminadas.
- 💾 Guardado y recuperación de los datos del torneo.
- 🎨 Interfaz gráfica desarrollada con JavaFX.

## 🛠️ Tecnologías utilizadas

- **Java:** lenguaje de programación principal.
- **JavaFX:** desarrollo de la interfaz gráfica.
- **Programación orientada a objetos (POO):** organización del código en clases y responsabilidades.
- **Colecciones de Java:** gestión y ordenamiento de equipos y grupos.
- **Persistencia de archivos:** almacenamiento y recuperación de los datos del torneo.
- **Git y GitHub:** control de versiones y colaboración.

## 📋 Criterios de desempate

El simulador utiliza los siguientes criterios para ordenar a las selecciones que terminan con la misma cantidad de puntos:

1. Mayor cantidad de puntos.
2. Resultado del enfrentamiento directo entre las selecciones empatadas.
3. Mayor diferencia de goles.
4. Mayor cantidad de goles a favor.

Cuando existe un empate entre tres o más selecciones, se utiliza una mini tabla que considera los resultados de los partidos disputados exclusivamente entre los equipos empatados.

> **Nota:** Los criterios implementados corresponden a la lógica de desempate definida en el proyecto. Para una reproducción reglamentaria completa del Mundial 2026, deben verificarse e incorporarse todos los criterios oficiales aplicables.

## 📁 Estructura del proyecto

La organización puede variar según la configuración del proyecto en el IDE.

```text
TutorMundial/
├── .settings/
│   ├── org.eclipse.core.resources.prefs
│   └── org.eclipse.jdt.core.prefs
├── src/
│   ├── fonts/
│   │   └── FWC2026.ttf
│   ├── images/
│   │   ├── FWC/
│   │   │   ├── anterior.png
│   │   │   ├── atras.png
│   │   │   ├── fondo.png
│   │   │   ├── logo_FWC.png
│   │   │   ├── pausa.png
│   │   │   ├── play.png
│   │   │   ├── reproducir.png
│   │   │   ├── siguiente.png
│   │   │   └── we_are_26.png
│   │   ├── estadios/
│   │   │   ├── can1.jpg
│   │   │   ├── can2.jpg
│   │   │   ├── mex1.jpg
│   │   │   ├── mex2.jpg
│   │   │   ├── mex3.jpg
│   │   │   ├── usa1.jpg
│   │   │   ├── usa2.jpg
│   │   │   ├── usa3.jpg
│   │   │   ├── usa4.jpg
│   │   │   ├── usa5.jpg
│   │   │   ├── usa6.jpg
│   │   │   ├── usa7.jpg
│   │   │   ├── usa8.jpg
│   │   │   ├── usa9.jpg
│   │   │   ├── usa10.jpg
│   │   │   └── usa11.jpg
│   │   ├── historia/
│   │   │   ├── 1930.jpg
│   │   │   ├── 1934.jpg
│   │   │   ├── 1938.jpg
│   │   │   ├── 1950.jpg
│   │   │   ├── 1954.jpg
│   │   │   ├── 1958.jpg
│   │   │   ├── 1962.jpg
│   │   │   ├── 1966.jpg
│   │   │   ├── 1970.jpg
│   │   │   ├── 1974.jpg
│   │   │   ├── 1978.jpg
│   │   │   ├── 1982.jpg
│   │   │   ├── 1986.jpg
│   │   │   ├── 1990.jpg
│   │   │   ├── 1994.jpg
│   │   │   ├── 1998.jpg
│   │   │   ├── 2002.jpg
│   │   │   ├── 2006.jpg
│   │   │   ├── 2010.jpg
│   │   │   ├── 2014.jpg
│   │   │   ├── 2018.jpg
│   │   │   └── 2022.jpg
│   │   ├── imagenes_musica/
│   │   │   ├── blessings.jpg
│   │   │   ├── champion.jpg
│   │   │   ├── dai_dai.jpg
│   │   │   ├── disco.png
│   │   │   ├── echo.jpg
│   │   │   ├── energy.jpg
│   │   │   ├── game_time.jpg
│   │   │   ├── goals.jpg
│   │   │   ├── illuminate.jpg
│   │   │   ├── in_the_stars.jpg
│   │   │   ├── lighter.jpg
│   │   │   ├── love_always_wins.jpg
│   │   │   ├── mi_mexico_lindo.jpg
│   │   │   ├── no_place_like_home.jpg
│   │   │   ├── partidazo.jpg
│   │   │   ├── por_ella.jpg
│   │   │   ├── show_me.jpg
│   │   │   ├── siir_siir.jpg
│   │   │   └── three_nations.jpg
│   │   ├── menu/
│   │   │   ├── estadios.png
│   │   │   ├── fifa_sound.png
│   │   │   ├── fixture.png
│   │   │   ├── historia.png
│   │   │   ├── test.png
│   │   │   └── tour_mundial.png
│   │   └── paises/
│   │       ├── alg.png
│   │       ├── arg.png
│   │       ├── aus.png
│   │       ├── aut.png
│   │       ├── bel.png
│   │       ├── bih.png
│   │       ├── bra.png
│   │       ├── can.png
│   │       ├── civ.png
│   │       ├── cod.png
│   │       ├── col.png
│   │       ├── cpv.png
│   │       ├── cro.png
│   │       ├── cuw.png
│   │       ├── cze.png
│   │       ├── ecu.png
│   │       ├── egy.png
│   │       ├── eng.png
│   │       ├── esp.png
│   │       ├── fra.png
│   │       ├── ger.png
│   │       ├── gha.png
│   │       ├── hai.png
│   │       ├── irn.png
│   │       ├── irq.png
│   │       ├── jor.png
│   │       ├── jpn.png
│   │       ├── kor.png
│   │       ├── ksa.png
│   │       ├── mar.png
│   │       ├── mex.png
│   │       ├── ned.png
│   │       ├── nor.png
│   │       ├── nzl.png
│   │       ├── pan.png
│   │       ├── par.png
│   │       ├── por.png
│   │       ├── qat.png
│   │       ├── rsa.png
│   │       ├── sco.png
│   │       ├── sen.png
│   │       ├── sui.png
│   │       ├── swe.png
│   │       ├── tun.png
│   │       ├── tur.png
│   │       ├── uru.png
│   │       ├── usa.png
│   │       └── uzb.png
│   ├── sounds/
│   │   ├── fifa_sound/
│   │   │   ├── blessings.mp3
│   │   │   ├── champion.mp3
│   │   │   ├── dai_dai.mp3
│   │   │   ├── echo.mp3
│   │   │   ├── energy.mp3
│   │   │   ├── game_time.mp3
│   │   │   ├── goals.mp3
│   │   │   ├── illuminate.mp3
│   │   │   ├── in_the_stars.mp3
│   │   │   ├── lighter.mp3
│   │   │   ├── love_always_wins.mp3
│   │   │   ├── mi_mexico_lindo.mp3
│   │   │   ├── no_place_like_home.mp3
│   │   │   ├── partidazo.mp3
│   │   │   ├── por_ella.mp3
│   │   │   ├── show_me.mp3
│   │   │   ├── siir_siir.mp3
│   │   │   └── three_nations.mp3
│   │   └── inicio/
│   │       ├── ambiente_inicio.mp3
│   │       └── inicio.mp3
│   ├── styles/
│   │   └── fifaSound.css
│   ├── Archivos.java
│   ├── Cancion.java
│   ├── Cantante.java
│   ├── EquipoGrupo.java
│   ├── Estadio.java
│   ├── FechaMundial.java
│   ├── Grupo.java
│   ├── HistoriaMundial.java
│   ├── Jugador.java
│   ├── LineaLetra.java
│   ├── LlaveEliminatoria.java
│   ├── Main.java
│   ├── ManagerCancion.java
│   ├── ManagerEstadio.java
│   ├── ManagerFixture.java
│   ├── ManagerGrupo.java
│   ├── ManagerHistoria.java
│   ├── ManagerMundial.java
│   ├── ManagerPais.java
│   ├── ManagerPregunta.java
│   ├── ManagerResultado.java
│   ├── ManagerTerceros.java
│   ├── MiniTabla.java
│   ├── Mundial.java
│   ├── Pais.java
│   ├── PantallaAdmin.java
│   ├── PantallaAgregarPregunta.java
│   ├── PantallaEstadios.java
│   ├── PantallaFifaSound.java
│   ├── PantallaGrupo.java
│   ├── PantallaHistoria.java
│   ├── PantallaInicio.java
│   ├── PantallaLetra.java
│   ├── PantallaMenu.java
│   ├── PantallaPais.java
│   ├── PantallaPartidosMenu.java
│   ├── PantallaQuiz.java
│   ├── PantallaRecords.java
│   ├── PantallaTest.java
│   ├── PantallaTourMundial.java
│   ├── Participante.java
│   ├── Partido.java
│   ├── Pregunta.java
│   ├── PreguntaOpMultiple.java
│   ├── PreguntaVF.java
│   ├── Quiz.java
│   └── Resultado.java
├── bin/                         # Archivos compilados y recursos copiados
├── canciones.txt
├── estadios.txt
├── historia.txt
├── mundial.dat
├── paises.txt
├── preguntasD.dat
├── preguntasF.dat
├── preguntasM.dat
├── resultados.dat
├── .classpath
├── .gitattributes
├── .gitignore
├── .project
└── README.md
```

## ⚙️ Requisitos

- JDK compatible con la versión de JavaFX utilizada.
- JavaFX SDK correctamente configurado.
- IDE compatible, como IntelliJ IDEA, Eclipse o Visual Studio Code.
- Git para clonar el repositorio y colaborar.

## 🚀 Instalación y ejecución

### 1. Clonar el repositorio

```bash
git clone https://github.com/Adiel0406/TutorMundial.git
```

### 2. Abrir el proyecto

Abre la carpeta del proyecto en tu IDE y verifica que la configuración del JDK y de JavaFX sea correcta.

### 3. Configurar los recursos

Comprueba que las imágenes, las fuentes y los archivos de datos estén ubicados en las rutas esperadas por el programa.

### 4. Ejecutar la aplicación

Ejecuta la clase principal del proyecto, que contiene el método `main` o la configuración de inicio de JavaFX.

## 💾 Persistencia de datos

El simulador permite conservar los resultados registrados y la información de los grupos mediante los mecanismos de almacenamiento implementados en el proyecto.

Se recomienda realizar una copia de seguridad de los archivos de datos antes de modificar su estructura o reiniciar el torneo.

## 🤝 Colaboración

El proyecto puede desarrollarse de forma colaborativa mediante Git y GitHub.

Para contribuir:

1. Clona el repositorio.
2. Crea una rama para tus cambios.
3. Realiza las modificaciones necesarias.
4. Guarda tus cambios mediante commits.
5. Envía un Pull Request para revisar e integrar tus aportes.

## 🎯 Objetivo del proyecto

Desarrollar un simulador funcional del Mundial 2026 que permita aplicar conceptos de programación orientada a objetos, gestión de colecciones, ordenamiento de datos, algoritmos de desempate, persistencia y diseño de interfaces gráficas.

## 👨‍💻 Autores

Proyecto académico desarrollado de manera colaborativa.

*Adiel Jose Imaña Castillo
*Miguel Mateo Ramos Rodas

## 📄 Licencia

Proyecto académico. La licencia y las condiciones de distribución quedan sujetas a lo que establezcan sus autores.
```
