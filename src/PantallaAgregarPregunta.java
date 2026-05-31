import java.util.ArrayList;

import javafx.geometry.Insets;
import javafx.geometry.Pos;

import javafx.scene.Scene;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import javafx.scene.paint.Color;

import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import javafx.stage.Stage;

public class PantallaAgregarPregunta {

    public Scene crearScene(Stage stage) {

        // =========================
        // ROOT
        // =========================

        BorderPane root =
            new BorderPane();

        root.setStyle(

            "-fx-background-color: linear-gradient(to bottom, #111111, #000000);"
        );

        // =========================
        // TITULO
        // =========================

        Label titulo =
            new Label(
                "AGREGAR PREGUNTA"
            );

        titulo.setFont(
            Font.font(
                "Arial",
                FontWeight.BOLD,
                32
            )
        );

        titulo.setTextFill(
            Color.WHITE
        );

        // =========================
        // ENUNCIADO
        // =========================

        Label lblPregunta =
            new Label(
                "Pregunta"
            );

        lblPregunta.setTextFill(
            Color.WHITE
        );

        TextArea txtPregunta =
            new TextArea();

        txtPregunta.setPrefRowCount(3);

        txtPregunta.setWrapText(true);

        // =========================
        // DIFICULTAD
        // =========================

        Label lblDificultad =
            new Label(
                "Dificultad"
            );

        lblDificultad.setTextFill(
            Color.WHITE
        );

        ComboBox<String> comboDificultad =
            new ComboBox<>();

        comboDificultad.getItems().addAll(

            "Facil",
            "Medio",
            "Dificil"
        );

        // =========================
        // TIPO
        // =========================

        Label lblTipo =
            new Label(
                "Tipo"
            );

        lblTipo.setTextFill(
            Color.WHITE
        );

        ComboBox<String> comboTipo =
            new ComboBox<>();

        comboTipo.getItems().addAll(

            "VF",
            "OP"
        );

        // =========================
        // RESPUESTA CORRECTA
        // =========================

        Label lblCorrecta =
            new Label(
                "Respuesta Correcta"
            );

        lblCorrecta.setTextFill(
            Color.WHITE
        );

        // =========================
        // COMBO VF
        // =========================

        ComboBox<String> comboVF =
            new ComboBox<>();

        comboVF.getItems().addAll(

            "Verdadero",
            "Falso"
        );

        comboVF.setVisible(false);

        // =========================
        // TXT RESPUESTA OP
        // =========================

        TextField txtCorrecta =
            new TextField();

        txtCorrecta.setPromptText(
            "Respuesta correcta para OP"
        );

        txtCorrecta.setVisible(false);

        // =========================
        // OPCIONES
        // =========================

        Label lblOpciones =
            new Label(
                "Opciones"
            );

        lblOpciones.setTextFill(
            Color.WHITE
        );

        TextField txtOp1 =
            new TextField();

        txtOp1.setPromptText(
            "Opcion 1"
        );

        TextField txtOp2 =
            new TextField();

        txtOp2.setPromptText(
            "Opcion 2"
        );

        TextField txtOp3 =
            new TextField();

        txtOp3.setPromptText(
            "Opcion 3"
        );

        TextField txtOp4 =
            new TextField();

        txtOp4.setPromptText(
            "Opcion 4"
        );

        VBox boxOpciones =
            new VBox(10);

        boxOpciones.getChildren().addAll(

            lblOpciones,
            txtOp1,
            txtOp2,
            txtOp3,
            txtOp4
        );

        boxOpciones.setVisible(false);

        // =========================
        // CAMBIO SEGUN TIPO
        // =========================

        comboTipo.setOnAction(e -> {

            if (

                comboTipo.getValue()
                    .equals("OP")
            ) {

                boxOpciones.setVisible(true);

                txtCorrecta.setVisible(true);

                comboVF.setVisible(false);

            } else {

                boxOpciones.setVisible(false);

                txtCorrecta.setVisible(false);

                comboVF.setVisible(true);
            }
        });

        // =========================
        // BOTON GUARDAR
        // =========================

        Button btnGuardar =
            new Button(
                "Guardar"
            );

        // =========================
        // BOTON ATRAS
        // =========================

        Button btnAtras =
            new Button(
                "Atras"
            );

        btnAtras.setOnAction(e -> {

            PantallaAdmin admin =
                new PantallaAdmin();

            Scene scene =
                admin.crearScene(stage);

            stage.setScene(scene);
        });

        // =========================
        // GUARDAR PREGUNTA
        // =========================

        btnGuardar.setOnAction(e -> {

            try {

                String enunciado =
                    txtPregunta.getText();

                String dificultad =
                    comboDificultad.getValue();

                String tipo =
                    comboTipo.getValue();

                String correcta;

                // =====================
                // RESPUESTA
                // =====================

                if (
                    tipo.equals("VF")
                ) {

                    correcta =
                        comboVF.getValue();

                } else {

                    correcta =
                        txtCorrecta.getText();
                }

                Pregunta pregunta;

                // =====================
                // PREGUNTA VF
                // =====================

                if (
                    tipo.equals("VF")
                ) {

                    pregunta =
                        new PreguntaVF(

                            enunciado,

                            correcta,

                            dificultad,

                            10
                        );

                }

                // =====================
                // PREGUNTA OP
                // =====================

                else {

                    ArrayList<String> opciones =
                        new ArrayList<>();
                    
                    opciones.add(
                        txtOp1.getText()
                    );

                    opciones.add(
                        txtOp2.getText()
                    );

                    opciones.add(
                        txtOp3.getText()
                    );

                    opciones.add(
                        txtOp4.getText()
                    );
                    
                    if (

                    	!opciones.contains(correcta)
                    ) {

                      	Alert error =
                       		new Alert(
                       			Alert.AlertType.ERROR
                       	    );

                        error.setHeaderText(null);

                        error.setContentText(

                        "La respuesta correcta debe estar entre las opciones"
                    );

                    error.showAndWait();

                        return;
                    }

                    pregunta =
                        new PreguntaOpMultiple(

                            enunciado,

                            correcta,

                            dificultad,

                            10,

                            opciones
                        );
                }

                // =====================
                // GUARDAR ARCHIVO
                // =====================

                ManagerPregunta manager =
                    new ManagerPregunta();

                if (
                    dificultad.equals("Facil")
                ) {

                    manager.guardarPregunta(

                        pregunta,

                        Archivos.ARCHIVO_PREGUNTAS_F
                    );
                }

                else if (
                    dificultad.equals("Medio")
                ) {

                    manager.guardarPregunta(

                        pregunta,

                        Archivos.ARCHIVO_PREGUNTAS_M
                    );
                }

                else {

                    manager.guardarPregunta(

                        pregunta,

                        Archivos.ARCHIVO_PREGUNTAS_D
                    );
                }

                Alert ok =
                    new Alert(
                        Alert.AlertType.INFORMATION
                    );

                ok.setHeaderText(null);

                ok.setContentText(
                    "Pregunta guardada"
                );

                ok.showAndWait();

            } catch (Exception ex) {

                Alert error =
                    new Alert(
                        Alert.AlertType.ERROR
                    );

                error.setHeaderText(null);

                error.setContentText(
                    "Error al guardar"
                );

                error.showAndWait();
            }
        });

        // =========================
        // FORMULARIO
        // =========================

        VBox formulario =
            new VBox(15);

        formulario.setAlignment(
            Pos.CENTER
        );

        formulario.setPadding(
            new Insets(30)
        );

        formulario.getChildren().addAll(

            titulo,

            lblPregunta,
            txtPregunta,

            lblDificultad,
            comboDificultad,

            lblTipo,
            comboTipo,

            lblCorrecta,
            comboVF,
            txtCorrecta,

            boxOpciones
        );

        // =========================
        // BOTONES
        // =========================

        HBox botones =
            new HBox(20);

        botones.setAlignment(
            Pos.CENTER
        );

        botones.getChildren().addAll(

            btnGuardar,
            btnAtras
        );

        // =========================
        // ROOT
        // =========================

        root.setCenter(
            formulario
        );

        root.setBottom(
            botones
        );

        BorderPane.setMargin(

            botones,

            new Insets(20)
        );

        // =========================
        // SCENE
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