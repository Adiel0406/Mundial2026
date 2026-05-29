import javafx.application.Application;

import javafx.geometry.Pos;

import javafx.scene.Scene;

import javafx.scene.control.Button;
import javafx.scene.control.Label;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.scene.input.KeyCode;

import javafx.scene.layout.*;

import javafx.scene.paint.Color;

import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import javafx.stage.Stage;
import javafx.animation.ScaleTransition;

import javafx.animation.Timeline;

import java.io.File;

import javafx.animation.KeyFrame;

import javafx.animation.KeyValue;

import javafx.util.Duration;

import javafx.scene.effect.DropShadow;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.animation.*;

public class PantallaInicio
extends Application {
	
	private MediaPlayer musica;
	private MediaPlayer musicaBoton;
	
    public Scene crearScene(
            Stage stage
    ) {

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

        // =========================
        // ROOT
        // =========================

        VBox root =
            new VBox(25);

        root.setAlignment(
            Pos.CENTER
        );

        root.setBackground(
            new Background(bg)
        );
        
        // =========================
        // NUEVA PANTALLA
        // =========================

        PantallaMenu menu =
        	    new PantallaMenu();

        Scene sceneMenu =
        	    menu.crearScene(stage);

        // =========================
        // LOGO
        // =========================

        ImageView logo =
            new ImageView(
                new Image(
                	"images/FWC/logo_FWC.png"
                )
            );

        logo.setFitWidth(150);

        logo.setPreserveRatio(true);

        // =========================
        // BOTON
        // =========================

        Button btnEntrar =
            new Button(
                "!Entrar!"
            );

        btnEntrar.setFont(
            Font.font(
                "Arial",
                FontWeight.BOLD,
                22
            )
        );

        btnEntrar.setTextFill(
            Color.WHITE
        );
        
        btnEntrar.setCursor(
        	    javafx.scene.Cursor.HAND
        	);

        btnEntrar.setStyle(

            "-fx-background-color: transparent;"
            +

            "-fx-border-color: white;"
            +

            "-fx-border-width: 2;"
            +

            "-fx-background-radius: 30;"
            +

            "-fx-border-radius: 30;"
            +

            "-fx-padding: 10 35 10 35;"
            
            
        );
        
        // =========================
        // HOVER ANIMATION
        // =========================

        ScaleTransition agrandar =
        		new ScaleTransition(
        				Duration.millis(150),
        				btnEntrar
        				);

        agrandar.setToX(1.12);
        agrandar.setToY(1.12);

        ScaleTransition achicar =
        	new ScaleTransition(
        		Duration.millis(150),
        		btnEntrar
        	);

    	achicar.setToX(1);
    	achicar.setToY(1);

    	btnEntrar.setOnMouseEntered(e -> {

    		agrandar.playFromStart();
    	});

    	btnEntrar.setOnMouseExited(e -> {

    		achicar.playFromStart();
    	});
    	
    	// =========================
    	// GLOW EFFECT
    	// =========================

    	DropShadow glow =
    	    new DropShadow();

    	glow.setColor(
    	    Color.WHITE
    	);

    	glow.setRadius(10);

    	btnEntrar.setEffect(glow);

    	// =========================
    	// ANIMACION GLOW
    	// =========================

    	Timeline brillo =
    	    new Timeline(

    	        new KeyFrame(
    	            Duration.seconds(0),

    	            new KeyValue(
    	                glow.radiusProperty(),
    	                10
    	            )
    	        ),

    	        new KeyFrame(
    	            Duration.seconds(1.2),

    	            new KeyValue(
    	                glow.radiusProperty(),
    	                30
    	            )
    	        )
    	    );

    	brillo.setAutoReverse(true);

    	brillo.setCycleCount(
    	    Timeline.INDEFINITE
    	);

    	brillo.play();
        
        btnEntrar.setCursor(
        	    javafx.scene.Cursor.HAND
        );
        
        btnEntrar.setOnAction(e -> {

            // =========================
            // SONIDO BOTON
            // =========================

            musicaBoton.seek(Duration.ZERO);

            musicaBoton.play();

            // =========================
            // ZOOM
            // =========================

            ScaleTransition zoom =
                new ScaleTransition(
                    Duration.seconds(1.2),
                    root
                );

            zoom.setToX(1.25);

            zoom.setToY(1.25);

            // =========================
            // DESVANECER
            // =========================

            FadeTransition fade =
                new FadeTransition(
                    Duration.seconds(1.2),
                    root
                );

            fade.setToValue(0);

            // =========================
            // BAJAR MUSICA
            // =========================

            Timeline bajarVolumen =
                new Timeline(

                    new KeyFrame(
                        Duration.seconds(1.2),

                        new KeyValue(
                            musica.volumeProperty(),
                            0
                        )
                    )
                );

            // =========================
            // CUANDO TERMINE
            // =========================

            fade.setOnFinished(ev -> {

                musica.stop();

                stage.setScene(
                    sceneMenu
                );
                
                stage.setMaximized(true);
            });

            // =========================
            // INICIAR
            // =========================

            zoom.play();

            fade.play();

            bajarVolumen.play();
        });

        // =========================
        // TEXTO ESC
        // =========================

        Label salir =
            new Label(
                " Presiona ESC para salir"
            );

        salir.setFont(
            Font.font(
                "Arial",
                14
            )
        );

        salir.setTextFill(
            Color.GRAY
        );

        // =========================
        // AGREGAR
        // =========================

        root.getChildren().addAll(
            logo,
            btnEntrar,
            salir
        );
        
        // =========================
        // MUSICA DE FONDO
        // =========================

        Media media =
        	    new Media(
        	        new File(
        	            "src/sounds/inicio/ambiente_inicio.mp3"
        	        )
        	        .toURI()
        	        .toString()
        	    );

        	musica =
        	    new MediaPlayer(media);

        	musica.setVolume(0.35);

        	musica.setCycleCount(
        	    MediaPlayer.INDEFINITE
        	);

        	musica.play();
        	
        Media mediaBoton =
        	new Media(
        	new File(
        		"src/sounds/inicio/inicio.mp3"
        		)
        		.toURI()
        		.toString()
        		    );

        	musicaBoton =
        		new MediaPlayer(mediaBoton);

        	musicaBoton.setVolume(0.6);	
        
        // =========================
        // SCENE
        // =========================

        Scene scene =
            new Scene(
                root,
                1280,
                720
            );

        // =========================
        // ESC PARA SALIR
        // =========================

        scene.setOnKeyPressed(e -> {

            if (
                e.getCode()
                == KeyCode.ESCAPE
            ) {

                stage.close();
            }
        });

        // =========================
        // STAGE
        // =========================

        stage.setTitle(
            "FWC 2026"
        );

        return scene;
    }

    @Override
    public void start(
            Stage stage
    ) {

        Scene scene =
            crearScene(stage);

        stage.setTitle(
            "FWC 2026"
        );

        stage.setScene(scene);

        stage.setMaximized(true);

        stage.show();
    }
    
    public static void main(
            String[] args
    ) {

        launch(args);
    }
}