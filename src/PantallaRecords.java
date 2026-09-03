import javafx.geometry.Insets;
import javafx.geometry.Pos;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.scene.layout.*;

import javafx.scene.paint.Color;

import javafx.scene.text.Font;

import javafx.stage.Stage;
import javafx.animation.ScaleTransition;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

import javafx.util.Duration;

public class PantallaRecords {

    public Scene crearScene(Stage stage) {

        // =========================
        // ROOT
        // =========================

        BorderPane root =
            new BorderPane();


        // =========================
        // FONDO
        // =========================

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


        // =========================
        // TITULO
        // =========================

        Label titulo =
            new Label(
                "RECORDS"
            );

        Font fuenteFWC =
            Font.loadFont(

                getClass().getResourceAsStream(
                    "/fonts/FWC2026.ttf"
                ),

                42
            );

        titulo.setFont(
            fuenteFWC
        );

        titulo.setTextFill(
            Color.WHITE
        );


        // =========================
        // CARGAR RESULTADOS
        // =========================

        ManagerResultado manager =
            new ManagerResultado();

        ArrayList<Resultado> resultados =
            manager.cargarResultados(
                Archivos.ARCHIVO_RESULTADOS
            );


        // =========================
        // CONSERVAR SOLO EL MEJOR
        // RESULTADO DE CADA JUGADOR
        // =========================

        Map<String, Resultado> mejoresResultados =
            new HashMap<>();

        for (Resultado resultado : resultados) {

            String jugador =
                resultado.getJugador();

            if (
                !mejoresResultados.containsKey(jugador)
                ||
                resultado.getPuntaje()
                >
                mejoresResultados
                    .get(jugador)
                    .getPuntaje()
            ) {

                mejoresResultados.put(
                    jugador,
                    resultado
                );
            }
        }


        // =========================
        // CONVERTIR MAP A LISTA
        // =========================

        ArrayList<Resultado> mejores =
            new ArrayList<>(
                mejoresResultados.values()
            );


        // =========================
        // ORDENAR DE MAYOR A MENOR
        // =========================

        mejores.sort(

            Comparator.comparingInt(
                Resultado::getPuntaje
            ).reversed()
        );


        // =========================
        // LISTA
        // =========================

        ListView<Resultado> lista =
            new ListView<>();

        lista.setPrefWidth(1500);

        lista.setPrefHeight(450);


        // Quitamos el borde propio de ListView
        // porque el contenedor tendrá el borde.

        lista.setStyle(

            "-fx-background-color: transparent;"
            +

            "-fx-control-inner-background: transparent;"
            +

            "-fx-background-insets: 0;"
        );


        lista.getItems().addAll(
            mejores
        );


        // =========================
        // ENCABEZADOS
        // =========================

        Label encabezadoPosicion =
            crearEncabezado(
                "#",
                70
            );

        Label encabezadoJugador =
            crearEncabezado(
                "JUGADOR",
                380
            );

        Label encabezadoPuntaje =
            crearEncabezado(
                "PUNTAJE",
                250
            );

        Label encabezadoDificultad =
            crearEncabezado(
                "DIFICULTAD",
                300
            );

        Label encabezadoPreguntas =
            crearEncabezado(
                "PREGUNTAS",
                220
            );


        HBox encabezados =
            new HBox();

        encabezados.setAlignment(
            Pos.CENTER_LEFT
        );

        encabezados.setPadding(
            new Insets(
                10,
                20,
                10,
                20
            )
        );

        encabezados.getChildren().addAll(

            encabezadoPosicion,
            encabezadoJugador,
            encabezadoPuntaje,
            encabezadoDificultad,
            encabezadoPreguntas
        );


        // =========================
        // CELDAS
        // =========================

        lista.setCellFactory(param -> {

            return new ListCell<Resultado>() {

                @Override
                protected void updateItem(
                    Resultado item,
                    boolean empty
                ) {

                    super.updateItem(
                        item,
                        empty
                    );


                    if (
                        empty
                        ||
                        item == null
                    ) {

                        setGraphic(null);

                        setText(null);

                    } else {

                        // =========================
                        // POSICION
                        // =========================

                        Label posicion =
                            new Label(
                                "#" + (getIndex() + 1)
                            );

                        posicion.setTextFill(
                            Color.GOLD
                        );

                        posicion.setFont(
                            Font.font(
                                "Arial",
                                24
                            )
                        );

                        posicion.setPrefWidth(
                            70
                        );


                        // =========================
                        // JUGADOR
                        // =========================

                        Label jugador =
                            new Label(
                                item.getJugador()
                            );

                        jugador.setTextFill(
                            Color.WHITE
                        );

                        jugador.setFont(
                            Font.font(
                                "Arial",
                                22
                            )
                        );

                        jugador.setPrefWidth(
                            380
                        );


                        // =========================
                        // PUNTAJE
                        // =========================

                        Label puntaje =
                            new Label(
                                item.getPuntaje()
                                +
                                " pts"
                            );

                        puntaje.setTextFill(
                            Color.LIGHTGREEN
                        );

                        puntaje.setFont(
                            Font.font(
                                "Arial",
                                22
                            )
                        );

                        puntaje.setPrefWidth(
                            250
                        );


                        // =========================
                        // DIFICULTAD
                        // =========================

                        Label dificultad =
                            new Label(
                                item.getDificultad()
                            );

                        dificultad.setTextFill(
                            Color.WHITE
                        );

                        dificultad.setFont(
                            Font.font(
                                "Arial",
                                22
                            )
                        );

                        dificultad.setPrefWidth(
                            300
                        );


                        // =========================
                        // PREGUNTAS
                        // =========================

                        Label preguntas =
                            new Label(
                                String.valueOf(
                                    item.getCantidadPreguntas()
                                )
                            );

                        preguntas.setTextFill(
                            Color.WHITE
                        );

                        preguntas.setFont(
                            Font.font(
                                "Arial",
                                22
                            )
                        );

                        preguntas.setPrefWidth(
                            220
                        );


                        // =========================
                        // FILA
                        // =========================

                        HBox fila =
                            new HBox();

                        fila.setAlignment(
                            Pos.CENTER_LEFT
                        );

                        fila.setPadding(
                            new Insets(15)
                        );

                        fila.getChildren().addAll(

                            posicion,
                            jugador,
                            puntaje,
                            dificultad,
                            preguntas
                        );


                        setGraphic(
                            fila
                        );


                        // =========================
                        // ESTILO DE LA FILA
                        // =========================

                        setStyle(

                            "-fx-background-color:"
                            +
                            "rgba(255,255,255,0.08);"
                            +

                            "-fx-background-radius: 20;"
                            +

                            "-fx-padding:"
                            +
                            "5 10 5 10;"
                        );
                    }
                }
            };
        });


        // =========================
        // BOTON ATRAS
        // =========================

        ImageView btnAtras =
            new ImageView(
                new Image(
                    "images/FWC/atras.png"
                )
            );

        btnAtras.setFitWidth(
            55
        );

        btnAtras.setPreserveRatio(
            true
        );

        btnAtras.setCursor(
            javafx.scene.Cursor.HAND
        );


        // =========================
        // ANIMACION BOTON
        // =========================

        ScaleTransition agrandar =
            new ScaleTransition(
                Duration.millis(150),
                btnAtras
            );

        agrandar.setToX(
            1.12
        );

        agrandar.setToY(
            1.12
        );


        ScaleTransition achicar =
            new ScaleTransition(
                Duration.millis(150),
                btnAtras
            );

        achicar.setToX(
            1
        );

        achicar.setToY(
            1
        );


        btnAtras.setOnMouseEntered(e -> {

            agrandar.playFromStart();

        });


        btnAtras.setOnMouseExited(e -> {

            achicar.playFromStart();

        });


        // =========================
        // ACCION BOTON ATRAS
        // =========================

        btnAtras.setOnMouseClicked(e -> {

            PantallaTest test =
                new PantallaTest();

            Scene scene =
                test.crearScene(stage);

            stage.setScene(
                scene
            );

            stage.setMaximized(
                true
            );
        });


        // =========================
        // BARRA SUPERIOR
        // =========================

        HBox topBar =
            new HBox();

        topBar.setAlignment(
            Pos.CENTER_LEFT
        );

        topBar.setPadding(
            new Insets(20)
        );

        topBar.getChildren().add(
            btnAtras
        );


        // =========================
        // TABLA COMPLETA
        // =========================

        VBox tabla =
            new VBox();

        tabla.setAlignment(
            Pos.TOP_CENTER
        );

        tabla.setMaxWidth(
            Double.MAX_VALUE
        );

        tabla.setMaxHeight(
            Double.MAX_VALUE
        );

        tabla.setStyle(

            "-fx-background-color:"
            +
            "rgba(0,0,0,0.45);"
            +

            "-fx-background-radius: 30;"
            +

            "-fx-border-radius: 30;"
            +

            "-fx-border-color: white;"
            +

            "-fx-border-width: 3;"
        );


        tabla.getChildren().addAll(

            encabezados,
            lista
        );


        // =========================
        // CENTRO
        // =========================

        VBox centro =
            new VBox(20);

        centro.setAlignment(
            Pos.CENTER
        );

        centro.setPadding(
            new Insets(
                0,
                0,
                20,
                0
            )
        );

        centro.getChildren().addAll(

            titulo,
            tabla
        );


        VBox.setVgrow(
            tabla,
            Priority.ALWAYS
        );


        // =========================
        // ROOT
        // =========================

        root.setCenter(
            centro
        );

        root.setTop(
            topBar
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


    // =====================================================
    // METODO PARA CREAR ENCABEZADOS
    // =====================================================

    private Label crearEncabezado(
        String texto,
        double ancho
    ) {

        Label label =
            new Label(
                texto
            );

        label.setTextFill(
            Color.WHITE
        );

        label.setFont(
            Font.font(
                "Arial",
                18
            )
        );

        label.setPrefWidth(
            ancho
        );

        return label;
    }
}