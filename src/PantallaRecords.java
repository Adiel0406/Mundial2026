import javafx.geometry.Insets;
import javafx.geometry.Pos;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


import javafx.scene.layout.*;

import javafx.scene.paint.Color;

import javafx.scene.text.Font;

import javafx.stage.Stage;
import javafx.animation.ScaleTransition;

import java.util.ArrayList;
import java.util.Comparator;

import javafx.util.Duration;

public class PantallaRecords {

    public Scene crearScene(Stage stage) {

        // =========================
        // ROOT
        // =========================

        BorderPane root =
            new BorderPane();

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
        // LISTA
        // =========================

        ListView<Resultado> lista =
            new ListView<>();

        lista.setPrefWidth(650);

        lista.setPrefHeight(520);

        lista.setStyle(

            "-fx-background-color: rgba(0,0,0,0.45);"
            +

            "-fx-control-inner-background: rgba(0,0,0,0.45);"
            +

            "-fx-background-radius: 30;"
            +

            "-fx-border-radius: 30;"
            +

            "-fx-border-color: white;"
            +

            "-fx-border-width: 3;"
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
        // ORDENAR
        // =========================

        resultados.sort(

            Comparator.comparingInt(
                Resultado::getPuntaje
            ).reversed()
        );
        
        lista.getItems().addAll(
            resultados
        );

        // =========================
        // MOSTRAR
        // =========================
        
        lista.setCellFactory(param -> {

            return new javafx.scene.control.ListCell<Resultado>() {

                @Override
                protected void updateItem(
                    Resultado item,
                    boolean empty
                ) {

                    super.updateItem(item, empty);

                    if (
                        empty || item == null
                    ) {

                        setGraphic(null);

                        setText(null);

                    } else {

                        HBox fila =
                            new HBox(25);

                        fila.setAlignment(
                            Pos.CENTER_LEFT
                        );

                        fila.setPadding(
                            new Insets(15)
                        );

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

                        Region espacio =
                            new Region();

                        HBox.setHgrow(
                            espacio,
                            Priority.ALWAYS
                        );

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

                        fila.getChildren().addAll(

                            posicion,
                            jugador,
                            espacio,
                            puntaje
                        );

                        setGraphic(fila);

                        setStyle(

                            "-fx-background-color: rgba(255,255,255,0.08);"
                            +

                            "-fx-background-radius: 20;"
                            +

                            "-fx-padding: 5 10 5 10;"
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

            PantallaTest test =
                new PantallaTest();

            Scene scene =
                test.crearScene(stage);

            stage.setScene(scene);
            
            stage.setMaximized(true);
        });

        // =========================
        // TOP
        // =========================

        VBox centro =
            new VBox(20);

        centro.setAlignment(
            Pos.CENTER
        );

        centro.getChildren().addAll(

            titulo,
            lista
        );

        // =========================
        // BOTON
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
}