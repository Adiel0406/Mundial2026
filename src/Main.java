/*import java.util.ArrayList;
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
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;

public class Main extends Application {

    private final int WIDTH = 1300;
    private final int HEIGHT = 660;
    
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

        paises = ManagerPais.leerPaisTxt("paises.txt");

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

        Scene principalScene =
                crearPantallaPrincipal(
                        stage,
                        tutorScene,
                        testScene
                );

        // =================================================
        // STAGE
        // =================================================

        stage.setTitle("");
        stage.setScene(menuScene);
        stage.setMaximized(true);
        stage.show();
    }
    
    // =================================================
    // MENU
    // =================================================

    private Scene crearMenu(Stage stage) {

        VBox root = new VBox(40);

        root.setAlignment(Pos.CENTER);

        root.setPadding(new Insets(20));

        aplicarFondo(root);

        // =========================================
        // LOGO
        // =========================================

        Image logo = new Image(
                getClass().getResourceAsStream(
                        "/images/logo_FWC.png"
                )
        );

        ImageView logoView = new ImageView(logo);
        
        logoView.setOpacity(0.95);

        logoView.setFitWidth(150);

        logoView.setPreserveRatio(true);

        // =========================================
        // BOTON ENTRAR
        // =========================================

        Button btnEntrar =
                new Button("!Entrar!");

        btnEntrar.setPrefWidth(280);

        btnEntrar.setPrefHeight(65);

        btnEntrar.setStyle("""
            -fx-background-color: rgba(255,255,255,0.10);

            -fx-border-color: rgba(255,255,255,0.55);

            -fx-border-width: 2;

            -fx-background-radius: 35;

            -fx-border-radius: 35;

            -fx-text-fill: white;

            -fx-font-size: 20px;

            -fx-font-family: "Arial";

            -fx-font-weight: bold;

            -fx-letter-spacing: 1px;
        """);
        
        btnEntrar.setOnMouseEntered(e -> {

            btnEntrar.setStyle("""
                -fx-background-color: rgba(255,255,255,0.20);

                -fx-border-color: white;

                -fx-border-width: 2;

                -fx-background-radius: 35;

                -fx-border-radius: 35;

                -fx-text-fill: white;

                -fx-font-size: 20px;

                -fx-font-family: "Arial";

                -fx-font-weight: bold;
            """);

            btnEntrar.setScaleX(1.05);

            btnEntrar.setScaleY(1.05);
        });

        btnEntrar.setOnMouseExited(e -> {

            btnEntrar.setStyle("""
                -fx-background-color: rgba(255,255,255,0.10);
                -fx-border-color: rgba(255,255,255,0.55);
                -fx-border-width: 2;
                -fx-background-radius: 35;
                -fx-border-radius: 35;
                -fx-text-fill: white;
                -fx-font-size: 20px;
                -fx-font-family: "Arial";
                -fx-font-weight: bold;
            """);

            btnEntrar.setScaleX(1);

            btnEntrar.setScaleY(1);
        });

        // =========================================
        // ESCENA PRINCIPAL
        // =========================================

        btnEntrar.setOnAction(e -> {

        	Scene menuScene = crearMenu(stage);

        	Scene tutorScene = crearTutor(stage, menuScene);

        	Scene testScene = crearTest(stage, menuScene);

        	Scene principalScene =
        	        crearPantallaPrincipal(
        	                stage,
        	                tutorScene,
        	                testScene
        	        );

            stage.setScene(principalScene);
        });

        // =========================================
        // AGREGAR
        // =========================================

        root.getChildren().addAll(
                logoView,
                btnEntrar
        );

        Scene scene = new Scene(
                root,
                WIDTH,
                HEIGHT
        );

        // =========================================
        // CERRAR CON ESC
        // =========================================

        scene.setOnKeyPressed(e -> {

            if (e.getCode() == KeyCode.ESCAPE) {

                System.exit(0);
            }
        });

        return scene;
    }
    
    private Scene crearPantallaPrincipal(
            Stage stage,
            Scene tutorScene,
            Scene testScene
    ) {
    	
    	FlowPane panelBotones = new FlowPane();

    	panelBotones.setAlignment(Pos.CENTER);

    	panelBotones.setHgap(25);

    	panelBotones.setVgap(25);

        VBox root = new VBox(30);

        root.setAlignment(Pos.CENTER);

        root.setPadding(new Insets(25));

        aplicarFondo(root);

        // =========================================
        // CARDS
        // =========================================

        Button btnTour = crearBotonMenu(
                "TOUR MUNDIAL",
                "Explora las selecciones clasificadas",
                "tour_mundial.png"
        );

        Button btnTest = crearBotonMenu(
                "TEST",
                "Demuestra cuánto sabes del Mundial",
                "test.png"
        );

        Button btnMercancia = crearBotonMenu(
                "MERCANCIA",
                "Visualiza productos oficiales FIFA",
                "mercancia.png"
        );

        Button btnFixture = crearBotonMenu(
                "FIXTURE",
                "Consulta partidos y enfrentamientos",
                "fixture.png"
        );

        Button btnEstadios = crearBotonMenu(
                "ESTADIOS",
                "Conoce las sedes del Mundial 2026",
                "estadios.png"
        );

        Button btnSound = crearBotonMenu(
                "FIFA SOUND",
                "Escucha canciones y ambiente FIFA",
                "fifa_sound.png"
        );
        
        btnTour.setOnAction(e -> {
            stage.setScene(tutorScene);
        });

        btnTest.setOnAction(e -> {
            stage.setScene(testScene);
        });

        // =========================================
        // AGREGAR GRID
        // =========================================

        panelBotones.getChildren().addAll(
                btnTour,
                btnTest,
                btnMercancia,
                btnFixture,
                btnEstadios,
                btnSound
        );

        // =========================================
        // AGREGAR ROOT
        // =========================================

        root.getChildren().add(panelBotones);

        return new Scene(
                root,
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

        aplicarFondo(panTutor);
            
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
    private Scene crearEscenaQuiz(Stage stage, Scene menuScene, Quiz quiz) {

        indicePregunta = 0;
        puntuacionTotal = 0;

        VBox root = new VBox(20);
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(20));
        root.setStyle("-fx-background-color: #121212;");

        // ================= LABEL PREGUNTA
        Label lblPregunta = new Label();
        aplicarFuente(lblPregunta);
        lblPregunta.setWrapText(true);
        lblPregunta.setStyle("-fx-text-fill: white;");

        // ================= LABEL PUNTAJE
        Label lblPuntaje = new Label();
        aplicarFuente(lblPuntaje);
        lblPuntaje.setStyle("-fx-text-fill: gold;");

        // ================= CONTENEDOR OPCIONES
        VBox opcionesBox = new VBox(10);
        opcionesBox.setAlignment(Pos.CENTER);

        // ================= BOTONES
        Button btnSiguiente = new Button("Siguiente");
        Button btnCancelar = new Button("Cancelar Quiz");
        aplicarFuente(btnSiguiente);
        aplicarFuente(btnCancelar);
        estilizarBoton(btnSiguiente, "#4CAF50");
        estilizarBoton(btnCancelar, "#F44336");

        // Arreglo para guardar el grupo de botones sin perder la referencia
        final ToggleGroup[] grupoActual = new ToggleGroup[1];

        // =================================================
        // FUNCION PARA MOSTRAR PREGUNTA
        // =================================================
        Runnable cargarPregunta = () -> {

            // SI TERMINÓ EL QUIZ
        	if (indicePregunta >= quiz.getPreguntas().size()) {
                Alert alerta = new Alert(Alert.AlertType.INFORMATION);
                alerta.setTitle("Quiz Finalizado");
                alerta.setHeaderText("¡Felicidades! Has completado el juego.");

                // Usamos StringBuilder para construir el reporte dinámicamente
                StringBuilder resumen = new StringBuilder();
                resumen.append("RESUMEN DEL QUIZ:\n");
                resumen.append("======================================\n\n");

                // Recorremos todas las preguntas del quiz actual
                for (int i = 0; i < quiz.getPreguntas().size(); i++) {
                    Pregunta p = quiz.getPreguntas().get(i);
                    resumen.append((i + 1)).append(". ").append(p.getNombre()).append("\n");
                    resumen.append("   Respuesta correcta: ").append(p.getRespuesta()).append("\n");
                    resumen.append("   Valor: ").append(p.getPuntuacion()).append(" pts\n\n");
                }

                resumen.append("======================================\n");
                resumen.append("PUNTAJE TOTAL OBTENIDO: ").append(puntuacionTotal).append(" puntos.");

                alerta.setContentText(resumen.toString());
                
                // Evita que el texto largo se corte en la pantalla
                alerta.getDialogPane().setMinHeight(javafx.scene.layout.Region.USE_PREF_SIZE);

                alerta.showAndWait();
                
                // Regresamos al menú
                stage.setScene(menuScene);
                return;
            }

            // OBTENER PREGUNTA
            Pregunta pregunta = quiz.getPreguntas().get(indicePregunta);

            // MOSTRAR TEXTO
            lblPregunta.setText(pregunta.getNombre());
            lblPuntaje.setText("Puntaje: " + pregunta.getPuntuacion());

            opcionesBox.getChildren().clear();
            grupoActual[0] = new ToggleGroup();
            ToggleGroup grupo = grupoActual[0];

            // =================================================
            // PREGUNTA VF
            // =================================================
            if (pregunta instanceof PreguntaVF) {
                RadioButton rbV = new RadioButton("Verdadero");
                RadioButton rbF = new RadioButton("Falso");
                aplicarFuente(rbV);
                aplicarFuente(rbF);
                rbV.setStyle("-fx-text-fill: white;");
                rbF.setStyle("-fx-text-fill: white;");
                rbV.setToggleGroup(grupo);
                rbF.setToggleGroup(grupo);
                opcionesBox.getChildren().addAll(rbV, rbF);
            }
            // =================================================
            // OPCION MULTIPLE
            // =================================================
            else if (pregunta instanceof PreguntaOpMultiple) {
                PreguntaOpMultiple pom = (PreguntaOpMultiple) pregunta;
                if (pom.getOpciones() != null) {
                    for (String op : pom.getOpciones()) {
                        RadioButton rb = new RadioButton(op);
                        aplicarFuente(rb);
                        // ¡ESTO ERA LO QUE FALTABA EN TU CÓDIGO!
                        rb.setStyle("-fx-text-fill: white;"); 
                        rb.setToggleGroup(grupo);
                        opcionesBox.getChildren().add(rb);
                    }
                }
            } 
            // =================================================
            // MODO DE EMERGENCIA (Si las preguntas se crearon mal)
            // =================================================
            else {
                Label lblError = new Label("ERROR: La pregunta no es VF ni Múltiple. Revisa tu clase Quiz o Manager.");
                lblError.setStyle("-fx-text-fill: red; -fx-font-weight: bold;");
                RadioButton rbForzar = new RadioButton("Forzar avance");
                rbForzar.setStyle("-fx-text-fill: white;");
                rbForzar.setToggleGroup(grupo);
                opcionesBox.getChildren().addAll(lblError, rbForzar);
            }
        };

        // =================================================
        // BOTON SIGUIENTE 
        // =================================================
        btnSiguiente.setOnAction(e -> {
            ToggleGroup grupo = grupoActual[0];
            
            if (grupo == null || grupo.getSelectedToggle() == null) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setHeaderText("Seleccione una respuesta");
                alert.show();
                return;
            }

            try {
                Pregunta preguntaActual = quiz.getPreguntas().get(indicePregunta);
                RadioButton seleccionado = (RadioButton) grupo.getSelectedToggle();
                
                // VALIDAR RESPUESTA
                if (preguntaActual.validarRespuesta(seleccionado.getText())) {
                    puntuacionTotal += preguntaActual.getPuntuacion();
                }
            } catch (Exception ex) {
                // Si tu lógica de validar respuesta da error, lo ignoramos para que no se congele el juego
                System.out.println("Error oculto al validar respuesta: " + ex.getMessage());
            }

            // SIGUIENTE PREGUNTA
            indicePregunta++;
            cargarPregunta.run();
        });

        // =================================================
        // CANCELAR QUIZ
        // =================================================
        btnCancelar.setOnAction(e -> {
            indicePregunta = 0;
            puntuacionTotal = 0;
            stage.setScene(menuScene);
        });

        // CARGAR PRIMERA PREGUNTA
        cargarPregunta.run();

        // AGREGAR COMPONENTES AL PANEL
        root.getChildren().addAll(lblPregunta, lblPuntaje, opcionesBox, btnSiguiente, btnCancelar);

        return new Scene(root, WIDTH, HEIGHT);
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

        aplicarFondo(root);

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
    
    private Button crearBotonMenu(
            String titulo,
            String descripcion,
            String imagenNombre
    ) {

        // =================================================
        // IMAGEN
        // =================================================

        Image imagen = new Image(
                getClass().getResourceAsStream(
                        "/images/menu/" + imagenNombre
                )
        );

        ImageView imgView = new ImageView(imagen);

        imgView.setFitWidth(320);
        imgView.setFitHeight(220);
        imgView.setPreserveRatio(false);
        
        Rectangle clip = new Rectangle(320, 220);

        clip.setArcWidth(40);
        clip.setArcHeight(40);

        imgView.setClip(clip);

        // =================================================
        // DEGRADADO
        // =================================================

        VBox overlay = new VBox(8);

        overlay.setAlignment(Pos.TOP_LEFT);

        overlay.setPadding(new Insets(18));

        overlay.setPrefSize(320, 220);

        overlay.setStyle("""
        	    -fx-background-color:
        	        linear-gradient(
        	            to bottom,
        	            rgba(0,0,0,0.85),
        	            rgba(0,0,0,0.45),
        	            rgba(0,0,0,0.05)
        	        );

        	    -fx-background-radius: 40;
        	    """);

        // =================================================
        // TITULO
        // =================================================

        Label lblTitulo = new Label(titulo);

        lblTitulo.setFont(
                Font.font(
                        fuente.getFamily(),
                        22
                )
        );

        lblTitulo.setStyle("""
                -fx-text-fill: white;
                """);

        // =================================================
        // DESCRIPCION
        // =================================================

        Label lblDescripcion = new Label(descripcion);

        lblDescripcion.setWrapText(true);

        lblDescripcion.setStyle("""
                -fx-text-fill: #dddddd;
                -fx-font-size: 14px;
                -fx-font-family: "Arial";
                """);

        overlay.getChildren().addAll(
                lblTitulo,
                lblDescripcion
        );

        // =================================================
        // STACK
        // =================================================

        StackPane contenido = new StackPane();
        
        contenido.setStyle("""
        	    -fx-background-radius: 40;
        	    -fx-border-radius: 40;
        	    """);

        contenido.getChildren().addAll(
                imgView,
                overlay
        );

        // =================================================
        // BOTON
        // =================================================

        Button boton = new Button();

        boton.setGraphic(contenido);

        boton.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);

        boton.setStyle("""
        	    -fx-background-color: transparent;
        	    -fx-padding: 0;
        	    -fx-background-radius: 40;
        	    """);

        boton.setPrefSize(320, 220);

        // =================================================
        // HOVER
        // =================================================

        boton.setOnMouseEntered(e -> {

        	boton.setScaleX(1.05);
        	boton.setScaleY(1.05);

        	boton.setTranslateY(-3);
        });

        boton.setOnMouseExited(e -> {

            boton.setScaleX(1);
            boton.setScaleY(1);
            
            boton.setTranslateY(0);
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

                aplicarFondo(root);

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

                ventana.setTitle("");

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
 // =================================================
 // APLICAR FONDO
 // =================================================

    private void aplicarFondo(VBox panel) {

        Image fondo = new Image(
                getClass().getResourceAsStream(
                        "/images/fondo.png"
                )
        );

        BackgroundSize bgSize = new BackgroundSize(
                100,
                100,
                true,
                true,
                true,
                true
        );

        BackgroundImage bgImage =
                new BackgroundImage(
                        fondo,
                        BackgroundRepeat.NO_REPEAT,
                        BackgroundRepeat.NO_REPEAT,
                        BackgroundPosition.CENTER,
                        bgSize
                );

        panel.setBackground(
                new Background(bgImage)
        );
    }
} */

public class Main {

    public static void main(
            String[] args
    ) {

        PantallaInicio.main(args);
    }
}
