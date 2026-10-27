
# 🏆 FIFA World Cup 2026

## 📌 Descripción

Tutor completo sobre la FIFA World Cup 2026. De la cual se muestran detalles de los 12 países clasificados, un test, todos los estadios que participan en el mundial, la historia de todos los campeones, un simulador de fase de grupos y todas las canciones de la FIFA World Cup 2026.

El sistema esta desarrollado en base a los conocimientos aprendidos en JavaFX y Java. El IDE utilizado es el Eclipse y maneja varias formas de persistencia de datos. Funciona con un menú interactivo y varias opciones a realizar.

**El proyecto logro el primer lugar de la Work-Shop en la Universidad Católica Boliviana en la categoría de tercer semestre.**

## ✨ Funcionalidades

- Gestión de los 48 paises del Mundial.
- Observar los datos de los países clasificados.
- Quiz interactivo para ver cuanto sabe de futbol.
- Panel de Admin para modificar las preguntas en el registro.
- Registro y administración de resultados de los partidos.
- Vista de todos los estadios que participan en el Mundial.
- Un pequeño museo de todos los campeones del mundo.
- Un apartado en donde se ven todas las canciones del Mundial.
- Un sector donde se ven las letras sincronizadas
- Guardado y recuperación de los datos mediante persistencia.
- Interfaz gráfica desarrollada con JavaFX.

## 🛠️ Tecnologías utilizadas

- **Java:** lenguaje de programación principal.
- **JavaFX:** desarrollo de la interfaz gráfica.
- **Programación orientada a objetos (POO):** organización del código en clases y responsabilidades.
- **Colecciones de Java:** gestión y ordenamiento de equipos y grupos.
- **Persistencia de archivos:** almacenamiento y recuperación de los datos del torneo.
- **Git y GitHub:** control de versiones y colaboración.
- **Canva:** imágenes editadas y bien elaboradas.
- **Eclipse:** IDE utilizado para programar el proyecto 

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

Alcanzar a mas personas este conocimiento del futbol. Es uno de los eventos mas importantes en el mundo y varias personas desconocen la historia de este. A modo de que sirva también como un recuerdo de lo que fue la Copa Mundial de la FIFA 2026.  

## 👨‍💻 Autores

Proyecto académico desarrollado de manera colaborativa.

- Adiel Jose Imaña Castillo
- Miguel Mateo Ramos Rodas

## 📄 Licencia

Proyecto académico. La licencia y las condiciones de distribución quedan sujetas a lo que establezcan sus autores.
```
