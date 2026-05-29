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

        return scene;
    }
}