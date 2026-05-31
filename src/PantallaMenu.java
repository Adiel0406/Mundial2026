import javafx.animation.FadeTransition;
import javafx.animation.ScaleTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.layout.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import java.io.FileInputStream;
import javafx.scene.shape.Rectangle;

public class PantallaMenu {

    public Scene crearScene(Stage stage) {

    	BorderPane root =
    		    new BorderPane();

        // FONDO

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
        
        StackPane btnTourMundial =
                crearBotonModo(

                    "Tour Mundial",

                    "Descubre todos los paises del la FWC 2026.",

                    "images/menu/tour_mundial.png"
                );
        
        btnTourMundial.setOnMouseClicked(e -> {

            PantallaTourMundial tour =
                new PantallaTourMundial();

            Scene nuevaScene =
                tour.crearScene(stage);

            ScaleTransition zoom =
                new ScaleTransition(
                    Duration.seconds(0.9),
                    root
                );

            zoom.setToX(1.25);

            zoom.setToY(1.25);

            FadeTransition fade =
                new FadeTransition(
                    Duration.seconds(0.9),
                    root
                );

            fade.setToValue(0);

            fade.setOnFinished(ev -> {

                stage.setScene(
                    nuevaScene
                );

                stage.setMaximized(true);
            });

            zoom.play();

            fade.play();
        });
        
        StackPane btnTest =
                crearBotonModo(

           	        "Test",

           	        "Demuestra tus conocimientos del fútbol.",

           	        "images/menu/test.png"
           	    );
        
        btnTest.setOnMouseClicked(e -> {

            PantallaTest test =
                new PantallaTest();

            Scene scene =
                test.crearScene(stage);

            // =========================
            // TRANSICION
            // =========================

            ScaleTransition zoom =
                new ScaleTransition(
                    Duration.seconds(0.8),
                    root
                );

            zoom.setToX(1.2);

            zoom.setToY(1.2);

            FadeTransition fade =
                new FadeTransition(
                    Duration.seconds(0.8),
                    root
                );

            fade.setToValue(0);

            fade.setOnFinished(ev -> {

                stage.setScene(scene);

                stage.setMaximized(true);
            });

            zoom.play();

            fade.play();
        });
        
        StackPane btnFixture =
        		crearBotonModo(

            		"Fixture",

            		"Analiza los partidos del mundial colocando los resultados.",

            		"images/menu/fixture.png"
        		);
        
        StackPane btnHistoria =
        		crearBotonModo(

        			"Historia",

        			"Revive todas las finales de los mundiales.",

        			"images/menu/historia.png"
        		);
        
        StackPane btnEstadios =
        		crearBotonModo(

        			"Estadios",

        			"Observa la pasion de todos los estadios de la FWC.",

        			"images/menu/estadios.png"
        		);
        
        StackPane btnFifaSound =
        		crearBotonModo(

        			"Fifa Sound",

        			"Escucha como se siente el mundial desde cerca.",

        			"images/menu/fifa_sound.png"
        		);
        
        GridPane grid =
        	    new GridPane();

        grid.setAlignment(
            Pos.CENTER
        );

        grid.setHgap(35);

        grid.setVgap(35);
        
        grid.add(btnTourMundial, 0, 0);
        grid.add(btnFixture, 1, 0);
        grid.add(btnEstadios, 2, 0);

        grid.add(btnTest, 0, 1);
        grid.add(btnHistoria, 1, 1);
        grid.add(btnFifaSound, 2, 1);
        
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

       		    PantallaInicio inicio =
       		        new PantallaInicio();

       		    Scene sceneInicio =
       		        inicio.crearScene(stage);

       		    // =========================
       		    // ANIMACION
       		    // =========================

       		    ScaleTransition zoom =
       		        new ScaleTransition(
       		            Duration.seconds(1.1),
       		            root
       		        );

       		    zoom.setToX(0.8);

       		    zoom.setToY(0.8);

       		    FadeTransition fade =
       		        new FadeTransition(
       		            Duration.seconds(1.1),
       		            root
       		        );

       		    fade.setToValue(0);

       		    fade.setOnFinished(ev -> {

       		        stage.setScene(
       		            sceneInicio
       		        );

       		        stage.setMaximized(true);
       		    });

       		    zoom.play();

       		    fade.play();
        });
        		
        // =========================
        // CABECERA
        // =========================

        ImageView cabecera =
            new ImageView(
                new Image(
                    "images/FWC/we_are_26.png"
                )
            );

        cabecera.setFitWidth(220);

        cabecera.setPreserveRatio(true);
        
        HBox topBar =
        	    new HBox(15);

        topBar.getChildren().addAll(
            btnAtras,
            cabecera
        );

        topBar.setAlignment(
            Pos.CENTER_LEFT
        );
        
        topBar.setPadding(
        	    new Insets(20)
        	);
        		
        Scene scene =
            new Scene(
                root,
                1280,
                720
            );
		
        root.setTop(topBar);
        
        root.setCenter(
        	    grid
        	);

        return scene;
    }
    
    public StackPane crearBotonModo(

            String titulo,
            String descripcion,
            String imagenPath
    ) {

        // =========================
        // CARD BASE
        // =========================

        StackPane card =
            new StackPane();

        card.setPrefSize(360, 260);

        card.setMaxSize(360, 260);
        
        Rectangle clip =
        	new Rectangle(
        		360,
        	    260
        	);

        clip.setArcWidth(40);

        clip.setArcHeight(40);

        card.setClip(clip);

        card.setStyle(

        	"-fx-background-radius: 30;"
        );

        card.setCursor(
            javafx.scene.Cursor.HAND
        );

        // =========================
        // IMAGEN
        // =========================

        ImageView imagen =
        	new ImageView(
            new Image(imagenPath)
        );

        imagen.setFitWidth(390);

        imagen.setPreserveRatio(true);
        
        imagen.setTranslateY(18);

        // =========================
        // PANEL SUPERIOR
        // =========================

        VBox info =
            new VBox(6);

        info.setPadding(
            new Insets(20)
        );

        info.setAlignment(
            Pos.TOP_LEFT
        );

        info.setMaxWidth(
            Double.MAX_VALUE
        );

        info.setStyle(

        	"-fx-background-color: "
        	+

        	"linear-gradient("
        	+

        	"to bottom,"
        	+

        	"rgba(0,0,0,0.92) 0%,"
        	+

        	"rgba(0,0,0,0.65) 45%,"
        	+

        	"rgba(0,0,0,0.00) 100%"
        	+

        	");"
        );
        
        info.setPrefHeight(95);

        // =========================
        // FUENTE
        // =========================

        Font fuenteFWC;

        try {

            fuenteFWC =
                Font.loadFont(

                    new FileInputStream(
                        "src/fonts/FWC2026.ttf"
                    ),

                    28
                );

        } catch (Exception e) {

            fuenteFWC =
                Font.font(
                    "Arial",
                    FontWeight.BOLD,
                    28
                );
        }

        // =========================
        // TITULO
        // =========================

        Label lblTitulo =
            new Label(titulo);

        lblTitulo.setFont(
            fuenteFWC
        );

        lblTitulo.setTextFill(
            Color.WHITE
        );

        // =========================
        // DESCRIPCION
        // =========================

        Label lblDescripcion =
            new Label(descripcion);

        lblDescripcion.setFont(
            Font.font(
                "Arial",
                14
            )
        );

        lblDescripcion.setTextFill(
            Color.LIGHTGRAY
        );

        lblDescripcion.setWrapText(true);

        // =========================
        // AGREGAR INFO
        // =========================

        info.getChildren().addAll(

            lblTitulo,
            lblDescripcion
        );

        // =========================
        // HOVER
        // =========================

        ScaleTransition agrandar =
            new ScaleTransition(
                Duration.millis(180),
                card
            );

        agrandar.setToX(1.05);

        agrandar.setToY(1.05);

        ScaleTransition achicar =
            new ScaleTransition(
                Duration.millis(180),
                card
            );

        achicar.setToX(1);

        achicar.setToY(1);

        card.setOnMouseEntered(e -> {

            agrandar.playFromStart();
        });

        card.setOnMouseExited(e -> {

            achicar.playFromStart();
        });

        // =========================
        // AGREGAR TODO
        // =========================

        card.getChildren().addAll(

            imagen,
            info
        );
        
        card.setStyle(

       	    "-fx-background-color: black;"
       	    +
            "-fx-background-radius: 30;"
        );

        StackPane.setAlignment(
            info,
            Pos.TOP_CENTER
        );

        return card;
    }
}