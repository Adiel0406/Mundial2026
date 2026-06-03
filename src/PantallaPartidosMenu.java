import javafx.animation.ScaleTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;

public class PantallaPartidosMenu {

    private Mundial mundial;

    public PantallaPartidosMenu(
            Mundial mundial
    ) {

        this.mundial = mundial;
    }

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
        // FUENTE
        // =========================

        Font fuenteFWC =
            Font.loadFont(

                getClass().getResourceAsStream(
                    "/fonts/FWC2026.ttf"
                ),

                42
            );

        // =========================
        // TITULO
        // =========================

        Label titulo =
            new Label(
                "FIXTURE"
            );

        titulo.setFont(
            fuenteFWC
        );

        titulo.setTextFill(
            Color.WHITE
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
        	Cursor.HAND
        	);

        // =========================
        // ANIMACIONES
        // =========================

        ScaleTransition agrandarBtn =
        	new ScaleTransition(
        		Duration.millis(150),
        		btnAtras
        		);

        agrandarBtn.setToX(1.12);

        agrandarBtn.setToY(1.12);

        ScaleTransition achicarBtn =
        	new ScaleTransition(
        		Duration.millis(150),
        		btnAtras
        		);

        achicarBtn.setToX(1);

        achicarBtn.setToY(1);

        btnAtras.setOnMouseEntered(e -> {

        	agrandarBtn.playFromStart();
        });

        btnAtras.setOnMouseExited(e -> {

        	achicarBtn.playFromStart();
        });
        
        btnAtras.setOnMouseClicked(e -> {

            PantallaMenu pantalla =
                new PantallaMenu();

            Scene scene =
                pantalla.crearScene(stage);

            stage.setScene(scene);

            stage.setMaximized(true);
        });
        
        // =========================
        // TOP BAR
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

        root.setTop(
        	topBar
        );

        // =========================
        // GRID DE GRUPOS
        // =========================

        GridPane grid =
            new GridPane();

        grid.setHgap(25);

        grid.setVgap(25);

        grid.setAlignment(
            Pos.CENTER
        );

        ArrayList<Grupo> grupos =
            mundial.getGrupos();

        int columna = 0;
        int fila = 0;

        for (Grupo grupo : grupos) {

            StackPane card =
                crearCardGrupo(
                    grupo,
                    stage
                );

            grid.add(
                card,
                columna,
                fila
            );

            columna++;

            if (columna == 3) {

                columna = 0;

                fila++;
            }
        }

        // =========================
        // CENTRO
        // =========================

        VBox centro =
            new VBox(40);

        centro.setAlignment(
            Pos.CENTER);

        centro.getChildren().addAll(

            titulo,
            grid
        );

        root.setCenter(
            centro
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

        stage.setMaximized(true);

        return scene;
    }

    // ===================================
    // CARD VISUAL DE CADA GRUPO
    // ===================================

    private StackPane crearCardGrupo(
            Grupo grupo,
            Stage stage
    ) {

        StackPane card =
            new StackPane();

        card.setPrefSize(
            220,
            120
        );

        card.setCursor(
            Cursor.HAND
        );

        card.setStyle(

            "-fx-background-color: "
            + colorGrupo(grupo.getNombre())
            + ";"

            +

            "-fx-border-color: white;"
            +

            "-fx-border-width: 3;"
            +

            "-fx-background-radius: 25;"
            +

            "-fx-border-radius: 25;"
            +

            "-fx-effect: dropshadow(gaussian, rgba(255,255,255,0.30), 18, 0, 0, 0);"
        );

        Label texto =
            new Label(
                "GRUPO " + grupo.getNombre()
            );

        if (
            grupo.getNombre().equals("C")
            ||
            grupo.getNombre().equals("J")
        ) {

            texto.setTextFill(
                Color.BLACK
            );

        } else {

            texto.setTextFill(
                Color.WHITE
            );
        }

        texto.setFont(

            Font.font(
                "Arial",
                28
            )
        );

        card.getChildren().add(
            texto
        );

        // =========================
        // ANIMACION
        // =========================

        ScaleTransition agrandar =
            new ScaleTransition(
                Duration.millis(150),
                card
            );

        agrandar.setToX(1.06);

        agrandar.setToY(1.06);

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

        // =========================
        // ENTRAR AL GRUPO
        // =========================

        card.setOnMouseClicked(e -> {

            PantallaGrupo pantalla =
                new PantallaGrupo(
                    mundial,
                    grupo
                );

            Scene scene =
                pantalla.crearScene(stage);

            stage.setScene(scene);

            stage.setMaximized(true);
        });

        return card;
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
}