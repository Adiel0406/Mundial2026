import java.util.ArrayList;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Main extends Application {

    private final int WIDTH = 1000;
    private final int HEIGHT = 800;
    
    private int indicePregunta = 0;

    private int puntuacionTotal = 0;
    
    private Font fuente;

    
    private ArrayList<Pais> paises;

    // =================================================
    // SELECCIONES
    // =================================================

    private final String[][] selecciones = {

            {"mex", "Mexico"},
            {"rsa", "Sudafrica"},
            {"kor", "Corea del Sur"},
            {"cze", "Chequia"},

            {"can", "Canada"},
            {"bih", "Bosnia y Herzegovina"},
            {"qat", "Catar"},
            {"sui", "Suiza"},

            {"bra", "Brasil"},
            {"mar", "Marruecos"},
            {"hai", "Haiti"},
            {"sco", "Escocia"},

            {"usa", "Estados Unidos"},
            {"par", "Paraguay"},
            {"aus", "Australia"},
            {"tur", "Turquia"},

            {"ger", "Alemania"},
            {"cuw", "Curazao"},
            {"civ", "Costa de Marfil"},
            {"ecu", "Ecuador"},

            {"ned", "Paises Bajos"},
            {"jpn", "Japon"},
            {"swe", "Suecia"},
            {"tun", "Tunez"},

            {"bel", "Belgica"},
            {"egy", "Egipto"},
            {"irn", "Iran"},
            {"nzl", "Nueva Zelanda"},

            {"esp", "España"},
            {"cpv", "Cabo Verde"},
            {"ksa", "Arabia Saudita"},
            {"uru", "Uruguay"},

            {"fra", "Francia"},
            {"sen", "Senegal"},
            {"irq", "Irak"},
            {"nor", "Noruega"},

            {"arg", "Argentina"},
            {"alg", "Argelia"},
            {"aut", "Austria"},
            {"jor", "Jordania"},

            {"por", "Portugal"},
            {"cod", "RD Congo"},
            {"uzb", "Uzbekistan"},
            {"col", "Colombia"},

            {"eng", "Inglaterra"},
            {"cro", "Croacia"},
            {"gha", "Ghana"},
            {"pan", "Panama"}
    };

    public static void main(String[] args) {

        launch(args);
    }

    @Override
    public void start(Stage stage) {

        // =================================================
        // LEER TXT
        // =================================================

        paises = Manager.leerPaisTxt("paises.txt");

        // =================================================
        // FUENTE
        // =================================================

        fuente = Font.loadFont(
                getClass().getResourceAsStream("/fonts/FWC2026.ttf"), 22);

        if (fuente == null) {

            System.out.println("No se pudo cargar la fuente");

            fuente = Font.font("Arial", 22);
        }

        // =================================================
        // ESCENAS
        // =================================================

        Scene menuScene = crearMenu(stage);

        Scene tutorScene = crearTutor(stage, menuScene);
        
        Scene testScene = crearTest(stage, menuScene);

        VBox rootMenu = (VBox) menuScene.getRoot();

        Button btnIniciar =
                		(Button) rootMenu
                        .getChildren()
                        .get(1);

        btnIniciar.setOnAction(e -> {
            stage.setScene(tutorScene);
        });
        
        Button btnTest =
                (Button) rootMenu
                        .getChildren()
                        .get(2);

        btnTest.setOnAction(e -> {
            stage.setScene(testScene);
        });

        // =================================================
        // STAGE
        // =================================================

        stage.setTitle("Tutor Mundial 2026");
        stage.setScene(menuScene);
        stage.show();
    }

    // =================================================
    // MENU
    // =================================================

    private Scene crearMenu(Stage stage) {

        VBox panMenu = new VBox(25);

        panMenu.setAlignment(Pos.CENTER);

        panMenu.setPadding(new Insets(20));

        panMenu.setStyle("""
                -fx-background-color:
                linear-gradient(
                    to bottom,
                    #0a0a0a,
                    #202020
                );
                """);

        Label lblTitulo = new Label(
                "MENU TUTOR MUNDIAL"
        );

        aplicarFuente(lblTitulo);

        lblTitulo.setStyle("""
                -fx-text-fill: white;
                """);

        Button btnIniciar =
                new Button("Iniciar Tutor");

        Button btnTest =
                new Button("Iniciar Test");

        Button btnSalir =
                new Button("Salir");

        aplicarFuente(btnIniciar);

        aplicarFuente(btnTest);

        aplicarFuente(btnSalir);

        btnIniciar.setPrefWidth(250);

        btnTest.setPrefWidth(250);

        btnSalir.setPrefWidth(250);

        estilizarBoton(
                btnIniciar,
                "#2196F3"
        );

        estilizarBoton(
                btnTest,
                "#4CAF50"
        );

        estilizarBoton(
                btnSalir,
                "#F44336"
        );

        btnSalir.setOnAction(e -> {
            System.exit(0);
        });

        panMenu.getChildren().addAll(
                lblTitulo,
                btnIniciar,
                btnTest,
                btnSalir
        );

        return new Scene(
                panMenu,
                WIDTH,
                HEIGHT
        );
    }

    // =================================================
    // TUTOR
    // =================================================

    private Scene crearTutor(
            Stage stage,
            Scene menuScene
    ) {

        VBox panTutor = new VBox(20);

        panTutor.setAlignment(Pos.TOP_CENTER);

        panTutor.setPadding(new Insets(20));

        panTutor.setStyle("""
                -fx-background-color:
                linear-gradient(
                    to bottom,
                    #121212,
                    #202020
                );
                """);
            
    // =================================================
    // LABEL
    // =================================================

    Label lblSeleccion =
            new Label(
                    "Seleccione una selección"
            );

    aplicarFuente(lblSeleccion);

    lblSeleccion.setStyle("""
            -fx-text-fill: white;
            """);

    // =================================================
    // FLOWPANE
    // =================================================

    FlowPane panPaises =
            new FlowPane();

    panPaises.setAlignment(Pos.CENTER);

    panPaises.setHgap(15);

    panPaises.setVgap(15);

    // =================================================
    // CREAR BOTONES
    // =================================================

    for (String[] datos : selecciones) {

        Button boton =
                crearBotonSeleccion(
                        datos[0],
                        datos[1]
                );

        panPaises.getChildren().add(boton);
    }

    // =================================================
    // SCROLL
    // =================================================

    ScrollPane scroll =
            new ScrollPane(
                    panPaises
            );

    scroll.setFitToWidth(true);

    scroll.setPrefHeight(600);

    scroll.setStyle("""
            -fx-background:
            transparent;

            -fx-background-color:
            transparent;
            """);

    // =================================================
    // BOTON VOLVER
    // =================================================

    Button btnVolver =
            new Button("Volver");

    aplicarFuente(btnVolver);

    estilizarBoton(
            btnVolver,
            "#9C27B0"
    );

    btnVolver.setPrefWidth(220);

    btnVolver.setOnAction(e -> {
        stage.setScene(menuScene);
    });

    // =================================================
    // AGREGAR
    // =================================================

    panTutor.getChildren().addAll(
            lblSeleccion,
            scroll,
            btnVolver
    );

    return new Scene(
            panTutor,
            WIDTH,
            HEIGHT
    );
}
   // =================================================
   // QUIZ
   // =================================================     
	    private Scene crearEscenaQuiz(
	            Stage stage,
	            Scene menuScene,
	            Quiz quiz
	    ) {

            indicePregunta = 0;

            puntuacionTotal = 0;

            VBox root = new VBox(20);

            root.setAlignment(Pos.CENTER);

            root.setPadding(new Insets(20));

            root.setStyle("""
                    -fx-background-color: #121212;
                    """);

            // ================= LABEL PREGUNTA

            Label lblPregunta = new Label();

            aplicarFuente(lblPregunta);

            lblPregunta.setWrapText(true);

            lblPregunta.setStyle("""
                    -fx-text-fill: white;
                    """);

            // ================= LABEL PUNTAJE

            Label lblPuntaje = new Label();

            aplicarFuente(lblPuntaje);

            lblPuntaje.setStyle("""
                    -fx-text-fill: gold;
                    """);

            // ================= CONTENEDOR OPCIONES

            VBox opcionesBox = new VBox(10);

            opcionesBox.setAlignment(Pos.CENTER);

            // ================= BOTONES

            Button btnSiguiente =
                    new Button("Siguiente");

            Button btnCancelar =
                    new Button("Cancelar Quiz");

            aplicarFuente(btnSiguiente);

            aplicarFuente(btnCancelar);

            estilizarBoton(btnSiguiente, "#4CAF50");

            estilizarBoton(btnCancelar, "#F44336");

            // =================================================
            // FUNCION PARA MOSTRAR PREGUNTA
            // =================================================

            final Runnable[] cargarPregunta = new Runnable[1];
            cargarPregunta[0] = () -> {

                // SI TERMINÓ EL QUIZ

                if (indicePregunta >=
                        quiz.getPreguntas().size()) {

                    Alert alerta = new Alert(
                            Alert.AlertType.INFORMATION
                    );

                    alerta.setHeaderText(
                            "Quiz terminado"
                    );

                    alerta.setContentText(
                            "Puntuación final: "
                                    + puntuacionTotal
                    );

                    alerta.showAndWait();

                    stage.setScene(menuScene);

                    return;
                }

                // OBTENER PREGUNTA

                Pregunta pregunta =
                        quiz.getPreguntas()
                                .get(indicePregunta);

                // MOSTRAR TEXTO

                lblPregunta.setText(
                        pregunta.getNombre()
                );

                lblPuntaje.setText(
                        "Puntaje: "
                                + pregunta.getPuntuacion()
                );

                opcionesBox.getChildren().clear();

                ToggleGroup grupo =
                        new ToggleGroup();

                // =================================================
                // PREGUNTA VF
                // =================================================

                if (pregunta instanceof PreguntaVF) {

                    RadioButton rbV =
                            new RadioButton(
                                    "Verdadero"
                            );

                    RadioButton rbF =
                            new RadioButton(
                                    "Falso"
                            );

                    aplicarFuente(rbV);

                    aplicarFuente(rbF);
                    
                    rbV.setStyle("-fx-text-fill: white;");
                    rbF.setStyle("-fx-text-fill: white;");

                    rbV.setToggleGroup(grupo);

                    rbF.setToggleGroup(grupo);

                    opcionesBox.getChildren().addAll(
                            rbV,
                            rbF
                    );
                }

                // =================================================
                // OPCION MULTIPLE
                // =================================================

                else if (
                        pregunta instanceof
                                PreguntaOpMultiple
                ) {

                    PreguntaOpMultiple pom =
                            (PreguntaOpMultiple)
                                    pregunta;

                    for (String op :
                            pom.getOpciones()) {

                        RadioButton rb =
                                new RadioButton(op);

                        aplicarFuente(rb);

                        rb.setToggleGroup(grupo);

                        opcionesBox.getChildren()
                                .add(rb);
                    }
                }

                // =================================================
                // BOTON SIGUIENTE
                // =================================================

                btnSiguiente.setOnAction(e -> {

                    if (
                            grupo.getSelectedToggle()
                                    == null
                    ) {

                        Alert alert =
                                new Alert(
                                        Alert.AlertType.WARNING
                                );

                        alert.setHeaderText(
                                "Seleccione una respuesta"
                        );

                        alert.show();

                        return;
                    }

                    RadioButton seleccionado =
                            (RadioButton)
                                    grupo.getSelectedToggle();

                    // VALIDAR RESPUESTA

                    if (
                            pregunta.validarRespuesta(
                                    seleccionado.getText()
                            )
                    ) {

                        puntuacionTotal +=
                                pregunta.getPuntuacion();
                    }

                    // SIGUIENTE PREGUNTA

                    indicePregunta++;

                    cargarPregunta[0].run();
                });
            };

            // =================================================
            // CANCELAR QUIZ
            // =================================================

            btnCancelar.setOnAction(e -> {

                indicePregunta = 0;

                puntuacionTotal = 0;

                stage.setScene(menuScene);
            });

            // CARGAR PRIMERA PREGUNTA

            cargarPregunta[0].run();

            // AGREGAR COMPONENTES

            root.getChildren().addAll(
                    lblPregunta,
                    lblPuntaje,
                    opcionesBox,
                    btnSiguiente,
                    btnCancelar
            );

            return new Scene(
                    root,
                    WIDTH,
                    HEIGHT
            );
        }
        
    
    private Scene crearTest(
            Stage stage,
            Scene menuScene
    	) {

        // =================================================
        // PANEL PRINCIPAL
        // =================================================

        VBox root = new VBox(25);

        root.setAlignment(Pos.CENTER);

        root.setPadding(new Insets(30));

        root.setStyle("""
                -fx-background-color:
                linear-gradient(
                    to bottom,
                    #101010,
                    #202020
                );
                """);

        // =================================================
        // TITULO
        // =================================================

        Label lblTitulo =
                new Label("CONFIGURAR TEST");

        aplicarFuente(lblTitulo);

        lblTitulo.setStyle("""
                -fx-text-fill: white;
                -fx-font-size: 28px;
                """);

        // =================================================
        // NOMBRE
        // =================================================

        Label lblNombre =
                new Label("Ingrese su nombre");

        aplicarFuente(lblNombre);

        lblNombre.setStyle("""
                -fx-text-fill: white;
                """);

        TextField txtNombre =
                new TextField();

        txtNombre.setMaxWidth(300);

        txtNombre.setPromptText(
                "Nombre del jugador"
        );

        txtNombre.setStyle("""
                -fx-font-size: 16px;
                -fx-background-radius: 10;
                -fx-padding: 10;
                """);

        // =================================================
        // DIFICULTAD
        // =================================================

        Label lblDificultad =
                new Label("Seleccione dificultad");

        aplicarFuente(lblDificultad);

        lblDificultad.setStyle("""
                -fx-text-fill: white;
                """);

        ToggleGroup grupoDificultad =
                new ToggleGroup();

        RadioButton rbFacil =
                new RadioButton("Facil");

        RadioButton rbMedio =
                new RadioButton("Medio");

        RadioButton rbDificil =
                new RadioButton("Dificil");

        aplicarFuente(rbFacil);
        aplicarFuente(rbMedio);
        aplicarFuente(rbDificil);

        rbFacil.setToggleGroup(
                grupoDificultad
        );

        rbMedio.setToggleGroup(
                grupoDificultad
        );

        rbDificil.setToggleGroup(
                grupoDificultad
        );
        
        rbFacil.setUserData("Facil");
        rbMedio.setUserData("Medio");
        rbDificil.setUserData("Dificil");
        
        rbFacil.setStyle("""
                -fx-text-fill: white;
                """);

        rbMedio.setStyle("""
                -fx-text-fill: white;
                """);

        rbDificil.setStyle("""
                -fx-text-fill: white;
                """);

        HBox panelDificultad =
                new HBox(20);

        panelDificultad.setAlignment(
                Pos.CENTER
        );

        panelDificultad.getChildren()
                .addAll(
                        rbFacil,
                        rbMedio,
                        rbDificil
                );

        // =================================================
        // SPINNER
        // =================================================

        Label lblPreguntas =
                new Label(
                        "Cantidad de preguntas"
                );

        aplicarFuente(lblPreguntas);

        lblPreguntas.setStyle("""
                -fx-text-fill: white;
                """);

        Spinner<Integer> spinner =
                new Spinner<>(5, 30, 10);

        spinner.setEditable(true);

        spinner.setMaxWidth(120);

        spinner.setStyle("""
                -fx-font-size: 16px;
                """);

        // =================================================
        // MENSAJE ERROR
        // =================================================

        Label lblError =
                new Label();

        lblError.setStyle("""
                -fx-text-fill: red;
                -fx-font-size: 16px;
                """);

        // =================================================
        // BOTONES
        // =================================================

        Button btnIniciar =
                new Button("Iniciar Test");

        Button btnVolver =
                new Button("Volver");

        aplicarFuente(btnIniciar);

        aplicarFuente(btnVolver);

        estilizarBoton(
                btnIniciar,
                "#4CAF50"
        );

        estilizarBoton(
                btnVolver,
                "#9C27B0"
        );

        btnIniciar.setPrefWidth(240);

        btnVolver.setPrefWidth(240);

        // =================================================
        // EVENTO INICIAR
        // =================================================

        btnIniciar.setOnAction(e -> {

            try {

                // ================= NOMBRE

                String nombre =
                        txtNombre.getText().trim();

                if (nombre.isEmpty()) {

                    throw new Exception(
                            "Ingrese un nombre"
                    );
                }

                if (
                        nombre.matches("\\d+")
                ) {

                    throw new Exception(
                            "El nombre no puede ser solo números"
                    );
                }

                // ================= DIFICULTAD

                if (
                        grupoDificultad.getSelectedToggle()
                                == null
                ) {

                    throw new Exception(
                            "Seleccione dificultad"
                    );
                }

                String dificultad =
                        grupoDificultad
                                .getSelectedToggle()
                                .getUserData()
                                .toString();

                // ================= CANTIDAD

                int cantidad =
                        spinner.getValue();

                // ================= CREAR QUIZ

                Quiz quiz = new Quiz(
                        new ArrayList<>(),
                        nombre,
                        dificultad
                );

                // ================= OBTENER PREGUNTAS

                ArrayList<Pregunta> preguntas =
                        new ArrayList<>(
                                quiz.elegirPreguntas(
                                        dificultad,
                                        cantidad
                                )
                        );

                quiz.setPreguntas(
                        preguntas
                );

                // ================= CREAR ESCENA

                Scene escenaQuiz =
                        crearEscenaQuiz(
                                stage,
                                menuScene,
                                quiz
                        );

                // ================= CAMBIAR ESCENA

                stage.setScene(
                        escenaQuiz
                );

            } catch (Exception ex) {

                Alert alert =
                        new Alert(
                                Alert.AlertType.ERROR
                        );

                alert.setHeaderText(
                        "Error"
                );

                alert.setContentText(
                        ex.getMessage()
                );

                alert.show();
            }
        });

        // =================================================
        // EVENTO VOLVER
        // =================================================

        btnVolver.setOnAction(e -> {

            // ================= LIMPIAR NOMBRE

            txtNombre.clear();

            // ================= LIMPIAR RADIO BUTTONS

            grupoDificultad.selectToggle(null);

            // ================= REINICIAR SPINNER

            spinner.getValueFactory().setValue(10);

            // ================= LIMPIAR ERROR

            lblError.setText("");

            // ================= VOLVER

            stage.setScene(menuScene);
        });

        // =================================================
        // AGREGAR TODO
        // =================================================

        root.getChildren().addAll(

                lblTitulo,

                lblNombre,
                txtNombre,

                lblDificultad,
                panelDificultad,

                lblPreguntas,
                spinner,

                lblError,

                btnIniciar,
                btnVolver
        );

        return new Scene(
                root,
                WIDTH,
                HEIGHT
        );
    }
    // =================================================
    // CREAR BOTON
    // =================================================

    private Button crearBotonSeleccion(
            String codigo,
            String nombrePais
    ) {

        var stream =
                getClass().getResourceAsStream("/images/" + codigo + ".png");

        if (stream == null) {

            System.out.println(
                    "No existe imagen: " + codigo
            );

            return new Button( codigo.toUpperCase());
        }

        Image imagen =
                new Image(stream);

        ImageView imgView =
                new ImageView(imagen);

        imgView.setFitWidth(100);

        imgView.setFitHeight(80);

        imgView.setPreserveRatio(true);

        Button boton =
                new Button(
                        codigo.toUpperCase()
                );

        aplicarFuente(boton);

        boton.setGraphic(imgView);

        boton.setContentDisplay(
                ContentDisplay.TOP
        );

        boton.setAlignment(Pos.CENTER);

        boton.setPrefSize(140, 140);

        estilizarBotonPais(boton);

        // =================================================
        // HOVER
        // =================================================

        boton.setOnMouseEntered(e -> {

            boton.setScaleX(1.05);

            boton.setScaleY(1.05);
        });

        boton.setOnMouseExited(e -> {

            boton.setScaleX(1);

            boton.setScaleY(1);
        });

        // =================================================
        // EVENTO
        // =================================================

        boton.setOnAction(e -> {

            mostrarInformacionPais(
                    nombrePais
            );
        });

        return boton;
    }

    // =================================================
    // MOSTRAR INFORMACION
    // =================================================

    private void mostrarInformacionPais(
            String nombrePais
    ) {

        for (Pais p : paises) {

            if (
                    p.getNombre()
                            .equalsIgnoreCase(nombrePais)
            ) {

                // =========================================
                // NUEVA VENTANA
                // =========================================

                Stage ventana = new Stage();

                VBox root = new VBox(20);

                root.setPadding(new Insets(20));

                root.setAlignment(Pos.TOP_CENTER);

                root.setStyle("""
                        -fx-background-color:
                        linear-gradient(
                            to bottom,
                            #111111,
                            #202020
                        );
                        """);

                // =========================================
                // TITULO
                // =========================================

                Label lblNombre = new Label(
                        p.getNombre().toUpperCase()
                );

                lblNombre.setFont(
                        Font.font(
                                fuente.getFamily(),
                                40
                        )
                );

                lblNombre.setStyle("""
                        -fx-text-fill: white;
                        """);

                // =========================================
                // DATOS
                // =========================================

                Label lblDatos = new Label(

                        "Continente: "
                                + p.getContinente()

                                + "   |   "

                                + "Mundiales: "
                                + p.getMundiales()

                                + "   |   "

                                + "Participaciones: "
                                + p.getParticipantes()
                );

                lblDatos.setFont(
                        Font.font(
                                fuente.getFamily(),
                                18
                        )
                );

                lblDatos.setStyle("""
                        -fx-text-fill: #cccccc;
                        """);

                // =========================================
                // CENTRO
                // =========================================

                HBox contenedorCentral =
                        new HBox(20);

                contenedorCentral.setAlignment(
                        Pos.CENTER
                );

                // =========================================
                // CULTURA
                // =========================================

                VBox panelCultura = new VBox(10);

                panelCultura.setPrefWidth(300);

                Label lblCulturaTitulo =
                        new Label("CULTURA");

                lblCulturaTitulo.setFont(
                        Font.font(
                                fuente.getFamily(),
                                24
                        )
                );

                lblCulturaTitulo.setStyle("""
                        -fx-text-fill: #4CAF50;
                        """);

                Label lblCultura =
                        new Label(p.getCultura());

                lblCultura.setWrapText(true);

                lblCultura.setStyle("""
                        -fx-text-fill: white;
                        -fx-font-size: 16px;
                        """);

                panelCultura.getChildren().addAll(
                        lblCulturaTitulo,
                        lblCultura
                );

                // =========================================
                // FUTBOL
                // =========================================

                VBox panelFutbol =
                        new VBox(10);

                panelFutbol.setPrefWidth(300);

                Label lblFutbolTitulo =
                        new Label(
                                "FUTBOL"
                        );

                lblFutbolTitulo.setFont(
                        Font.font(
                                fuente.getFamily(),
                                24
                        )
                );

                lblFutbolTitulo.setStyle("""
                        -fx-text-fill: #2196F3;
                        """);

                Label lblFutbol =
                        new Label(
                                p.getDatosFutbol()
                        );

                lblFutbol.setWrapText(true);

                lblFutbol.setStyle("""
                        -fx-text-fill: white;
                        -fx-font-size: 16px;
                        """);

                panelFutbol.getChildren().addAll(
                        lblFutbolTitulo,
                        lblFutbol
                );

                // =========================================
                // AGREGAR PANELES
                // =========================================

                contenedorCentral.getChildren()
                        .addAll(
                                panelCultura,
                                panelFutbol
                        );

                // =========================================
                // JUGADORES
                // =========================================

                VBox panelJugadores =
                        new VBox(8);

                panelJugadores.setAlignment(
                        Pos.CENTER
                );

                Label lblJugadores =
                        new Label(
                                "JUGADORES DESTACADOS"
                        );

                lblJugadores.setFont(
                        Font.font(
                                fuente.getFamily(),
                                24
                        )
                );

                lblJugadores.setStyle("""
                        -fx-text-fill: #FF9800;
                        """);

                panelJugadores.getChildren()
                        .add(lblJugadores);

                for (Jugador j : p.getJugadores()) {

                    Label jugador =
                            new Label(

                                    j.getNombre()

                                            + " | "

                                            + j.getPosicion()

                                            + " | #"

                                            + j.getNumero()
                            );

                    jugador.setStyle("""
                            -fx-text-fill: white;
                            -fx-font-size: 16px;
                            """);

                    panelJugadores.getChildren()
                            .add(jugador);
                }

                // =========================================
                // BOTON CERRAR
                // =========================================

                Button btnCerrar =
                        new Button("Cerrar");

                aplicarFuente(btnCerrar);

                estilizarBoton(
                        btnCerrar,
                        "#F44336"
                );

                btnCerrar.setOnAction(e -> {

                    ventana.close();
                });

                // =========================================
                // AGREGAR TODO
                // =========================================

                root.getChildren().addAll(

                        lblNombre,
                        lblDatos,
                        contenedorCentral,
                        panelJugadores,
                        btnCerrar
                );

                Scene scene = new Scene(
                        root,
                        950,
                        700
                );

                ventana.setTitle(
                        p.getNombre()
                );

                ventana.setScene(scene);

                ventana.show();

                return;
            }
        }
    }

    // =================================================
    // FUENTE GENERAL
    // =================================================

    private void aplicarFuente(
            Labeled componente
    ) {

        componente.setFont(fuente);
    }

    // =================================================
    // ESTILO BOTONES MENU
    // =================================================

    private void estilizarBoton(
            Button boton,
            String color
    ) {

        boton.setStyle("""
                -fx-background-color: %s;

                -fx-text-fill: white;

                -fx-font-family: "FWC2026";

                -fx-font-size: 16px;

                -fx-background-radius: 12;

                -fx-padding: 12;
                """.formatted(color));

        boton.setOnMouseEntered(e -> {

            boton.setScaleX(1.05);

            boton.setScaleY(1.05);
        });

        boton.setOnMouseExited(e -> {

            boton.setScaleX(1);

            boton.setScaleY(1);
        });
    }

    // =================================================
    // ESTILO BOTONES PAISES
    // =================================================

    private void estilizarBotonPais(
            Button boton
    ) {

        boton.setStyle("""
                -fx-background-color: black;

                -fx-text-fill: white;

                -fx-font-family: "FWC2026";

                -fx-font-size: 14px;

                -fx-background-radius: 12;

                -fx-padding: 8;
                """);
    }
}