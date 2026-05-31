import javafx.animation.FadeTransition;
import javafx.animation.ScaleTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.io.FileInputStream;
import java.util.ArrayList;
import javafx.scene.control.ScrollPane;

public class PantallaTourMundial {

    public Scene crearScene(Stage stage) {

        StackPane root =
            new StackPane();
        
        Font fuenteFWC;

        try {

            fuenteFWC =
                Font.loadFont(
                    new FileInputStream(
                        "src/fonts/FWC2026.ttf"
                    ),
                    50
                );

        } catch (Exception e) {

            fuenteFWC =
                Font.font(
                    "Arial",
                    FontWeight.BOLD,
                    50
                );
        }

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
        
        // =========================
        // HOVER
        // =========================

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

            Scene sceneMenu =
                menu.crearScene(stage);

            ScaleTransition zoom =
                new ScaleTransition(
                    Duration.seconds(0.8),
                    root
                );

            zoom.setToX(0.8);

            zoom.setToY(0.8);

            FadeTransition fade =
                new FadeTransition(
                    Duration.seconds(0.8),
                    root
                );

            fade.setToValue(0);

            fade.setOnFinished(ev -> {

                stage.setScene(sceneMenu);

                stage.setMaximized(true);
            });

            zoom.play();

            fade.play();
        });

        // =========================
        // TITULO
        // =========================

        Label titulo =
        	new Label(
            "ELEGIR PAIS"
        );

        titulo.setTextFill(
        	Color.WHITE
        );

        titulo.setFont(
        	fuenteFWC
        );
        
        ManagerPais manager =
        	    new ManagerPais(
        	        Archivos.ARCHIVO_PAISES
        	    );

        ArrayList<Pais> paises =
        	manager.getPaises();
        
        VBox gruposBox =
        	    new VBox(40);

        gruposBox.setAlignment(
            Pos.TOP_CENTER
        );
        
        ScrollPane scroll =
            new ScrollPane(
                gruposBox
        );

        scroll.setFitToWidth(true);

        scroll.setStyle(
            "-fx-background: transparent;"
            +
            "-fx-background-color: transparent;"
        );

        scroll.setHbarPolicy(
            ScrollPane.ScrollBarPolicy.NEVER
        );

        scroll.setVbarPolicy(
            ScrollPane.ScrollBarPolicy.AS_NEEDED
        );
        
        for (char letra = 'A'; letra <= 'L'; letra++) {

            VBox grupoContainer =
                new VBox(15);

            grupoContainer.setAlignment(
                Pos.CENTER
            );

            Label lblGrupo =
                new Label(
                    "GRUPO " + letra
                );

            lblGrupo.setFont(
                Font.font(
                    fuenteFWC.getFamily(),
                    24
                )
            );

            lblGrupo.setTextFill(
                Color.WHITE
            );

            FlowPane fila =
                new FlowPane();

            fila.setHgap(20);

            fila.setVgap(20);

            fila.setAlignment(
                Pos.CENTER
            );

            for (Pais p : paises) {

                if (
                    p.getGrupo()
                    .equals(
                        String.valueOf(letra)
                    )
                ) {

                    fila.getChildren().add(
                        crearBotonPais(
                            stage,
                        	p,
                            fuenteFWC
                        )
                    );
                }
            }

            grupoContainer.getChildren().addAll(
                lblGrupo,
                fila
            );

            gruposBox.getChildren().add(
                grupoContainer
            );
        }

        // =========================
        // TOP BAR
        // =========================

        BorderPane layout =
            new BorderPane();

        StackPane.setMargin(
            layout,
            new Insets(25)
        );

        HBox topBar =
            new HBox(20);

        topBar.setAlignment(
            Pos.CENTER_LEFT
        );

        topBar.getChildren().add(
            btnAtras
        );

        layout.setTop(topBar);

        // =========================
        // CENTRO
        // =========================

        VBox contenido =
        	    new VBox(35);

        contenido.setAlignment(
            Pos.TOP_CENTER
        );

        contenido.getChildren().add(
            titulo
        );

        contenido.setPadding(
            new Insets(25, 0, 0, 0)
        );
        
        contenido.getChildren().add(
        	scroll
        );
        
        VBox.setVgrow(
            scroll,
            Priority.ALWAYS
        );

        layout.setCenter(contenido);

        root.getChildren().add(
            layout
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
    
    private String colorGrupo(
            String grupo
    ) {

        switch (grupo) {

            case "A":
                return "#6BCB77";

            case "B":
                return "#D62828";

            case "C":
                return "#FFD60A";

            case "D":
                return "#14213D";

            case "E":
                return "#F77F00";

            case "F":
                return "#2D6A4F";

            case "G":
                return "#C77DFF";

            case "H":
                return "#1D3557";

            case "I":
                return "#7B2CBF";

            case "J":
                return "#F4ACB7";

            case "K":
                return "#FF4D8D";

            case "L":
                return "#800F2F";
        }

        return "#444444";
    }
    
    private HBox crearBotonPais(
    		Stage stage,
    		Pais pais,
            Font fuenteFWC
    ) {

        HBox card =
            new HBox(15);

        card.setAlignment(
            Pos.CENTER_LEFT
        );

        card.setPadding(
            new Insets(12, 20, 12, 20)
        );

        card.setPrefWidth(320);

        card.setMinWidth(320);

        card.setMaxWidth(320);
        
        card.setPrefHeight(70);

        String color =
            colorGrupo(
                pais.getGrupo()
            );

        card.setStyle(

            "-fx-background-color: "
            + color
            + ";"

            +

            "-fx-background-radius: 20;"
        );

        // =========================
        // BANDERA
        // =========================

        ImageView bandera =
            new ImageView(
                new Image(
                    "images/paises/"
                    +
                    pais.getCodigoBandera()
                    +
                    ".png"
                )
            );

        bandera.setFitWidth(45);

        bandera.setPreserveRatio(true);

        // =========================
        // NOMBRE
        // =========================

        Label nombre =
            new Label(
                pais.getNombre()
            );

        nombre.setTextFill(
            Color.WHITE
        );

        nombre.setFont(
            Font.font(
                fuenteFWC.getFamily(),
                18
            )
        );

        // =========================
        // HOVER
        // =========================

        ScaleTransition agrandar =
            new ScaleTransition(
                Duration.millis(150),
                card
            );

        agrandar.setToX(1.05);

        agrandar.setToY(1.05);

        ScaleTransition achicar =
            new ScaleTransition(
                Duration.millis(150),
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

        card.getChildren().addAll(
            bandera,
            nombre
        );
        
        card.setOnMouseClicked(e -> {

            PantallaPais pantalla =
                new PantallaPais();

            Scene scene =
                pantalla.crearScene(
                    stage,
                    pais
                );

            stage.setScene(scene);

            stage.setMaximized(true);
        });

        return card;
    }
}