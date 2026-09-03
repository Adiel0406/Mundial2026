import java.util.ArrayList;

import javafx.animation.ScaleTransition;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Duration;

public class PantallaQuiz {

    private int indiceActual = 0;
    private int puntaje = 0;
    
    private int obtenerPuntos(String dificultad) {

        if (dificultad.equalsIgnoreCase("Facil")) {
            return 5;
        }

        if (dificultad.equalsIgnoreCase("Medio")
                || dificultad.equalsIgnoreCase("Normal")) {
            return 10;
        }

        if (dificultad.equalsIgnoreCase("Dificil")) {
            return 15;
        }

        return 0;
    }

    public Scene crearScene(
            Stage stage,
            Quiz quiz
    ) {

        StackPane root =
                new StackPane();

        Image fondo =
                new Image(
                        "images/FWC/fondo.png"
                );

        BackgroundImage bg =
                new BackgroundImage(
                        fondo,
                        BackgroundRepeat.NO_REPEAT,
                        BackgroundRepeat.NO_REPEAT,
                        BackgroundPosition.CENTER,
                        new BackgroundSize(
                                100,
                                100,
                                true,
                                true,
                                false,
                                true
                        )
                );

        root.setBackground(
                new Background(bg)
        );

        BorderPane layout =
                new BorderPane();

        root.getChildren().add(
                layout
        );

        // =========================
        // BOTON ATRAS
        // =========================

        ImageView btnAtras =
                new ImageView(
                        new Image(
                                "images/FWC/atras.png"
                        )
                );

        btnAtras.setFitWidth(55);
        btnAtras.setPreserveRatio(true);

        btnAtras.setCursor(
                javafx.scene.Cursor.HAND
        );

        ScaleTransition agrandar =
                new ScaleTransition(
                        Duration.millis(150),
                        btnAtras
                );

        agrandar.setToX(1.12);
        agrandar.setToY(1.12);

        ScaleTransition achicar =
                new ScaleTransition(
                        Duration.millis(150),
                        btnAtras
                );

        achicar.setToX(1);
        achicar.setToY(1);

        btnAtras.setOnMouseEntered(e -> {
            agrandar.playFromStart();
        });

        btnAtras.setOnMouseExited(e -> {
            achicar.playFromStart();
        });

        btnAtras.setOnMouseClicked(e -> {

            PantallaMenu menu =
                    new PantallaMenu();

            Scene scene =
                    menu.crearScene(stage);

            stage.setScene(scene);

            stage.setMaximized(true);
        });

        // =========================
        // PREGUNTAS
        // =========================

        ArrayList<Pregunta> preguntas =
                quiz.getPreguntas();

        Label titulo =
                new Label(
                        "MODO TEST"
                );

        Label lblPregunta =
                new Label();

        lblPregunta.setWrapText(true);

        lblPregunta.setMaxWidth(900);

        lblPregunta.setTextFill(
                Color.WHITE
        );

        lblPregunta.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        28
                )
        );

        // =========================
        // OPCIONES
        // =========================

        VBox opcionesBox =
                new VBox(20);

        opcionesBox.setAlignment(
                Pos.CENTER
        );

        // =========================
        // RESULTADO DE RESPUESTA
        // =========================

        Label lblResultado =
                new Label();

        lblResultado.setWrapText(true);

        lblResultado.setMaxWidth(900);

        lblResultado.setAlignment(
                Pos.CENTER
        );

        lblResultado.setTextFill(
                Color.WHITE
        );

        lblResultado.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        22
                )
        );
        
        lblResultado.setStyle(
                "-fx-background-color: rgba(0,0,0,0.65);"
                +
                "-fx-background-radius: 15;"
                +
                "-fx-padding: 12 25 12 25;"
        );

        // =========================
        // BOTON SIGUIENTE
        // =========================

        Button btnSiguiente =
                new Button(
                        "SIGUIENTE"
                );

        btnSiguiente.setPrefWidth(250);

        btnSiguiente.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        18
                )
        );

        btnSiguiente.setTextFill(
                Color.WHITE
        );

        btnSiguiente.setStyle(

                "-fx-background-color: rgba(0,0,0,0.6);"
                        +

                        "-fx-border-color: white;"
                        +

                        "-fx-border-radius: 20;"
                        +

                        "-fx-background-radius: 20;"
                        +

                        "-fx-padding: 12;"
        );

        btnSiguiente.setVisible(false);

        // =========================
        // CENTRO
        // =========================

        VBox centro =
                new VBox(30);

        centro.setAlignment(
                Pos.CENTER
        );

        centro.getChildren().addAll(
                titulo,
                lblPregunta,
                opcionesBox,
                lblResultado,
                btnSiguiente
        );

        layout.setCenter(
                centro
        );

        // =========================
        // MOSTRAR PREGUNTA
        // =========================

        Runnable[] mostrarPregunta =
                new Runnable[1];

        mostrarPregunta[0] =
                () -> {

                    // =========================
                    // FIN DEL QUIZ
                    // =========================

                    if (
                            indiceActual >= preguntas.size()
                    ) {

                        quiz.getParticipante()
                                .setPuntaje(puntaje);

                        quiz.setPuntajeFinal(
                                puntaje
                        );

                        Resultado resultado =
                                new Resultado(

                                        quiz.getParticipante()
                                                .getNombre(),

                                        puntaje,

                                        quiz.getDificultad(),

                                        preguntas.size()
                                );

                        ManagerResultado managerResultado =
                                new ManagerResultado();

                        managerResultado.guardarResultado(

                                resultado,

                                Archivos.ARCHIVO_RESULTADOS
                        );

                        Alert fin =
                                new Alert(
                                        Alert.AlertType.INFORMATION
                                );

                        fin.setHeaderText(null);

                        fin.setContentText(

                                "Quiz terminado\n\n"
                                        +

                                        "Puntaje: "
                                        + puntaje
                        );

                        fin.showAndWait();

                        PantallaRecords records =
                                new PantallaRecords();

                        Scene scene =
                                records.crearScene(stage);

                        stage.setScene(scene);

                        stage.setMaximized(true);

                        return;
                    }

                    // =========================
                    // PREGUNTA ACTUAL
                    // =========================

                    Pregunta pregunta =
                            preguntas.get(
                                    indiceActual
                            );

                    lblPregunta.setText(

                            (indiceActual + 1)
                                    +
                                    ". "
                                    +
                                    pregunta.getEnunciado()
                    );

                    // Limpiar opciones anteriores

                    opcionesBox
                            .getChildren()
                            .clear();

                    // Limpiar resultado anterior

                    lblResultado.setText("");

                    // Ocultar botón siguiente

                    btnSiguiente.setVisible(false);

                    // =========================
                    // PREGUNTA MULTIPLE
                    // =========================

                    if (
                            pregunta
                                    instanceof
                                    PreguntaOpMultiple
                    ) {

                        PreguntaOpMultiple op =
                                (PreguntaOpMultiple)
                                        pregunta;

                        for (
                                String opcion
                                        :
                                op.getOpciones()
                        ) {

                            Button btn =
                                    new Button(
                                            opcion
                                    );

                            btn.setPrefWidth(500);

                            btn.setFont(
                                    Font.font(
                                            "Arial",
                                            FontWeight.BOLD,
                                            18
                                    )
                            );

                            btn.setTextFill(
                                    Color.WHITE
                            );

                            btn.setStyle(

                                    "-fx-background-color: rgba(0,0,0,0.4);"
                                            +

                                            "-fx-border-color: white;"
                                            +

                                            "-fx-border-radius: 20;"
                                            +

                                            "-fx-background-radius: 20;"
                                            +

                                            "-fx-padding: 12;"
                            );

                            btn.setOnAction(e -> {

                                // =========================
                                // VALIDAR RESPUESTA
                                // =========================

                                boolean correcta =
                                        pregunta.validarRespuesta(
                                                opcion
                                        );

                                // =========================
                                // BLOQUEAR OPCIONES
                                // =========================

                                for (
                                        javafx.scene.Node nodo
                                                :
                                        opcionesBox.getChildren()
                                ) {

                                    if (
                                            nodo instanceof Button
                                    ) {

                                        nodo.setDisable(true);
                                    }
                                }

                                // =========================
                                // RESPUESTA CORRECTA
                                // =========================

                                if (correcta) {

                                	int puntosGanados =
                                	        obtenerPuntos(quiz.getDificultad());

                                    puntaje +=
                                            puntosGanados;

                                    lblResultado.setText(

                                            "✓ ¡RESPUESTA CORRECTA!\n\n"
                                                    +

                                                    "+"
                                                    +
                                                    puntosGanados
                                                    +
                                                    " puntos"
                                    );

                                    lblResultado.setTextFill(
                                            Color.LIMEGREEN
                                    );

                                } else {

                                    // =========================
                                    // RESPUESTA INCORRECTA
                                    // =========================

                                    lblResultado.setText(

                                            "✗ RESPUESTA INCORRECTA\n\n"
                                                    +

                                                    "La respuesta correcta era: "
                                                    +
                                                    pregunta
                                                            .getRespuestaCorrecta()
                                                    +

                                                    "\n\n"
                                                    +

                                                    "+0 puntos"
                                    );

                                    lblResultado.setTextFill(
                                            Color.RED
                                    );
                                }

                                // =========================
                                // CAMBIAR TEXTO DEL BOTON
                                // =========================

                                if (
                                        indiceActual
                                                ==
                                                preguntas.size() - 1
                                ) {

                                    btnSiguiente.setText(
                                            "FINALIZAR"
                                    );

                                } else {

                                    btnSiguiente.setText(
                                            "SIGUIENTE"
                                    );
                                }

                                btnSiguiente.setVisible(
                                        true
                                );
                            });

                            opcionesBox
                                    .getChildren()
                                    .add(btn);
                        }

                    // =========================
                    // VERDADERO / FALSO
                    // =========================

                    } else if (
                            pregunta
                                    instanceof
                                    PreguntaVF
                    ) {

                        String[] opciones =
                                {
                                        "Verdadero",
                                        "Falso"
                                };

                        for (
                                String op
                                        :
                                opciones
                        ) {

                            Button btn =
                                    new Button(op);

                            btn.setPrefWidth(400);

                            btn.setFont(
                                    Font.font(
                                            "Arial",
                                            FontWeight.BOLD,
                                            18
                                    )
                            );

                            btn.setTextFill(
                                    Color.WHITE
                            );

                            btn.setStyle(

                                    "-fx-background-color: rgba(0,0,0,0.4);"
                                            +

                                            "-fx-border-color: white;"
                                            +

                                            "-fx-border-radius: 20;"
                                            +

                                            "-fx-background-radius: 20;"
                            );

                            btn.setOnAction(e -> {

                                // =========================
                                // VALIDAR RESPUESTA
                                // =========================

                                boolean correcta =
                                        pregunta.validarRespuesta(
                                                op
                                        );

                                // =========================
                                // BLOQUEAR OPCIONES
                                // =========================

                                for (
                                        javafx.scene.Node nodo
                                                :
                                        opcionesBox.getChildren()
                                ) {

                                    if (
                                            nodo instanceof Button
                                    ) {

                                        nodo.setDisable(true);
                                    }
                                }

                                // =========================
                                // RESPUESTA CORRECTA
                                // =========================

                                if (correcta) {

                                    int puntosGanados =
                                            obtenerPuntos(quiz.getDificultad());

                                    puntaje +=
                                            puntosGanados;

                                    lblResultado.setText(

                                            "✓ ¡RESPUESTA CORRECTA!\n\n"
                                                    +

                                                    "+"
                                                    +
                                                    puntosGanados
                                                    +
                                                    " puntos"
                                    );

                                    lblResultado.setTextFill(
                                            Color.LIGHTGREEN
                                    );

                                } else {

                                    // =========================
                                    // RESPUESTA INCORRECTA
                                    // =========================

                                    lblResultado.setText(

                                            "✗ RESPUESTA INCORRECTA\n\n"
                                                    +

                                                    "La respuesta correcta era: "
                                                    +
                                                    pregunta
                                                            .getRespuestaCorrecta()
                                                    +

                                                    "\n\n"
                                                    +

                                                    "+0 puntos"
                                    );

                                    lblResultado.setTextFill(
                                            Color.SALMON
                                    );
                                }

                                // =========================
                                // BOTON SIGUIENTE
                                // =========================

                                if (
                                        indiceActual
                                                ==
                                                preguntas.size() - 1
                                ) {

                                    btnSiguiente.setText(
                                            "FINALIZAR"
                                    );

                                } else {

                                    btnSiguiente.setText(
                                            "SIGUIENTE"
                                    );
                                }

                                btnSiguiente.setVisible(
                                        true
                                );
                            });

                            opcionesBox
                                    .getChildren()
                                    .add(btn);
                        }
                    }
                };

        // =========================
        // BOTON SIGUIENTE
        // =========================

        btnSiguiente.setOnAction(e -> {

            indiceActual++;

            mostrarPregunta[0].run();
        });

        // =========================
        // MOSTRAR PRIMERA PREGUNTA
        // =========================

        mostrarPregunta[0].run();

        // =========================
        // ESCENA
        // =========================

        Scene scene =
                new Scene(
                        root,
                        1280,
                        720
                );

        return scene;
    }
}