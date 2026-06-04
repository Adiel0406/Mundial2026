import javafx.animation.FadeTransition;
import javafx.animation.ScaleTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Duration;

import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

import java.io.FileInputStream;
import java.util.ArrayList;
import javafx.scene.shape.Rectangle;

public class PantallaEstadios {

    public Scene crearScene(Stage stage) {

        BorderPane root =
            new BorderPane();
        
        StackPane mainRoot = new StackPane();

        mainRoot.getChildren().add(root);

        // =========================
        // FONDO
        // =========================

        BackgroundImage bg =
            new BackgroundImage(

                new Image(
                    "images/FWC/fondo.png"
                ),

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
        // FUENTE FWC
        // =========================

        Font fuenteFWC;

        try {

            fuenteFWC =
                Font.loadFont(

                    new FileInputStream(
                        "src/fonts/FWC2026.ttf"
                    ),

                    34
                );

        } catch (Exception e) {

            fuenteFWC =
                Font.font(
                    "Arial",
                    FontWeight.BOLD,
                    34
                );
        }

        // =========================
        // TITULO
        // =========================

        Label titulo =
            new Label(
                "ESTADIOS"
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

        btnAtras.setOnMouseEntered(
            e -> agrandar.playFromStart()
        );

        btnAtras.setOnMouseExited(
            e -> achicar.playFromStart()
        );

        // =========================
        // VOLVER
        // =========================

        btnAtras.setOnMouseClicked(e -> {

            PantallaMenu menu =
                new PantallaMenu();

            Scene scene =
                menu.crearScene(stage);

            ScaleTransition zoom =
                new ScaleTransition(
                    Duration.seconds(0.8),
                    root
                );

            zoom.setToX(0.9);

            zoom.setToY(0.9);

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
        
        // =========================
        // OVERLAY
        // =========================

        StackPane overlay =
        	new StackPane();

        overlay.setStyle(
        	"-fx-background-color: rgba(0,0,0,0.82);"
        );

        overlay.setVisible(false);

        // =========================
        // CONTENEDOR PRINCIPAL
        // =========================

        HBox panel =
        	new HBox(35);

        panel.setAlignment(Pos.CENTER);

        panel.setPadding(
        	new Insets(40)
        );

        // =========================
        // IMAGEN GRANDE
        // =========================

        ImageView imgDetalle =
        	new ImageView();

        imgDetalle.setFitWidth(520);

        imgDetalle.setPreserveRatio(true);

        // =========================
        // PANEL DERECHO
        // =========================

        VBox info =
        		new VBox(8);

        info.setMaxHeight(320);

        info.setPrefHeight(320);

        info.setPadding(
        	new Insets(20)
        );

        info.setStyle(
        	"-fx-background-color: rgba(0,0,0,0.92);" +
        	"-fx-background-radius: 30;"
        );

        // =========================
        // TEXTOS
        // =========================

        Label lblNombre =
        	new Label();

        lblNombre.setFont(fuenteFWC);

        lblNombre.setTextFill(Color.WHITE);

        lblNombre.setWrapText(true);

        lblNombre.setMaxWidth(420);

        Label lblPais =
        	new Label();

        lblPais.setFont(
        	Font.font("Arial", FontWeight.BOLD, 18)
        );

        Label lblCiudad =
        	new Label();

        lblCiudad.setFont(
        	Font.font("Arial", 17)
        );

        lblCiudad.setTextFill(Color.WHITE);

        Label lblEquipo =
        	new Label();

        lblEquipo.setFont(
        	Font.font("Arial", 17)
        );

        lblEquipo.setTextFill(Color.WHITE);

        Label lblInfraestructura =
        	new Label();

        lblInfraestructura.setFont(
        	Font.font("Arial", 15)
        );

        lblInfraestructura.setTextFill(
        	Color.LIGHTGRAY
        );

        lblInfraestructura.setWrapText(true);

        // =========================
        // AGREGAR INFO
        // =========================

        info.getChildren().addAll(
        	lblNombre,
        	lblPais,
        	lblCiudad,
        	lblEquipo,
        	lblInfraestructura
        );

        // =========================
        // AGREGAR PANEL
        // =========================

        panel.getChildren().addAll(
        	imgDetalle,
        	info
        );

        overlay.getChildren().add(panel);

        // =========================
        // AGREGAR ROOT
        // =========================

        mainRoot.getChildren().add(overlay);

        // =========================
        // CERRAR
        // =========================

        overlay.setOnMouseClicked(e -> {

        	FadeTransition fade =
        		new FadeTransition(
        			Duration.millis(220),
        			overlay
        		);

        	fade.setFromValue(1);

        	fade.setToValue(0);

        	fade.setOnFinished(ev -> {
        		overlay.setVisible(false);
        	});

        	fade.play();
        });

        // =========================
        // TOP BAR
        // =========================

        BorderPane topBar =
            new BorderPane();

        topBar.setPadding(
            new Insets(20, 30, 20, 30)
        );

        topBar.setLeft(btnAtras);

        topBar.setCenter(titulo);

        BorderPane.setAlignment(
            titulo,
            Pos.CENTER
        );

        root.setTop(topBar);
        
        // =========================
        // CONTENEDOR PRINCIPAL
        // =========================

        VBox contenido =
        	new VBox(40);

        contenido.setPadding(
        	new Insets(40)
        );
        
        ManagerEstadio manager =
            new ManagerEstadio();

        ArrayList<Estadio> estadios =
            manager.cargarEstadios(
                Archivos.ARCHIVO_ESTADIOS
            );

        ArrayList<Estadio> canada =
            manager.buscarPorPais(
                estadios,
                "Canada"
            );

        ArrayList<Estadio> mexico =
            manager.buscarPorPais(
                estadios,
                "Mexico"
            );

        ArrayList<Estadio> usa =
            manager.buscarPorPais(
                estadios,
                "Estados Unidos"
            );

        // =========================
        // CANADA
        // =========================

        Label lblCanada =
        	new Label("CANADA");

        lblCanada.setFont(fuenteFWC);

        lblCanada.setTextFill(Color.WHITE);

        StackPane panelCanada =
        	new StackPane(lblCanada);
        
        HBox filaCanada =
            new HBox(30);

        filaCanada.setAlignment(Pos.CENTER);

        for (Estadio e : canada) {

            filaCanada.getChildren().add(

                crearBotonEstadio(

                    e,

                    overlay,

                    imgDetalle,

                    lblNombre,

                    lblPais,

                    lblCiudad,

                    lblEquipo,

                    lblInfraestructura
                )
            );
        }

        panelCanada.setPrefHeight(120);

        panelCanada.setStyle(
        	"-fx-background-color: #C62828;" +
        	"-fx-background-radius: 25;"
        );

        // =========================
        // MEXICO
        // =========================

        Label lblMexico =
        	new Label("MEXICO");

        lblMexico.setFont(fuenteFWC);

        lblMexico.setTextFill(Color.WHITE);

        StackPane panelMexico =
        	new StackPane(lblMexico);
        
        HBox filaMexico =
            new HBox(30);

        filaMexico.setAlignment(Pos.CENTER);

        for (Estadio e : mexico) {

            filaMexico.getChildren().add(

                crearBotonEstadio(

                    e,

                    overlay,

                    imgDetalle,

                    lblNombre,

                    lblPais,

                    lblCiudad,

                    lblEquipo,

                    lblInfraestructura
                )
            );
        }

        panelMexico.setPrefHeight(120);

        panelMexico.setStyle(
        	"-fx-background-color: #1B5E20;" +
        	"-fx-background-radius: 25;"
        );

        // =========================
        // USA
        // =========================

        Label lblUSA =
        	new Label("ESTADOS UNIDOS");

        lblUSA.setFont(fuenteFWC);

        lblUSA.setTextFill(Color.WHITE);

        StackPane panelUSA =
        	new StackPane(lblUSA);
        
        VBox contenedorUSA =
            new VBox(25);

        contenedorUSA.setAlignment(Pos.CENTER);

        HBox filaActual =
            null;

        for (int i = 0; i < usa.size(); i++) {

            if (i % 3 == 0) {

                filaActual =
                    new HBox(30);

       	        filaActual.setAlignment(
       	            Pos.CENTER
       	        );

       	        contenedorUSA
       	            .getChildren()
       	            .add(filaActual);
       	    }

            filaActual.getChildren().add(

            	crearBotonEstadio(

            		usa.get(i),

                    overlay,

                    imgDetalle,

                    lblNombre,

                    lblPais,

                    lblCiudad,

                    lblEquipo,

                    lblInfraestructura
                )
            );
       	}

        panelUSA.setPrefHeight(120);

        panelUSA.setStyle(
        	"-fx-background-color: #0D47A1;" +
        	"-fx-background-radius: 25;"
        );

        // =========================
        // AGREGAR
        // =========================

        contenido.getChildren().addAll(

            panelCanada,
            filaCanada,

            panelMexico,
            filaMexico,

            panelUSA,
            contenedorUSA
        );

        // =========================
        // SCROLL
        // =========================

        ScrollPane scroll =
        	new ScrollPane(contenido);

        scroll.setFitToWidth(true);

        scroll.setVbarPolicy(
        	ScrollPane.ScrollBarPolicy.AS_NEEDED
        );

        scroll.setHbarPolicy(
        	ScrollPane.ScrollBarPolicy.NEVER
        );

        scroll.setPannable(true);

        scroll.setStyle(
        	"-fx-background: transparent;" +
        	"-fx-background-color: transparent;"
        );

        root.setCenter(scroll);

        // =========================
        // SCENE
        // =========================

        Scene scene =
            new Scene(
                mainRoot,
                1280,
                720
            );

        // =========================
        // ANIMACION ENTRADA
        // =========================

        ScaleTransition zoom =
            new ScaleTransition(
                Duration.seconds(0.8),
                root
            );

        zoom.setFromX(1.2);

        zoom.setFromY(1.2);

        zoom.setToX(1);

        zoom.setToY(1);

        FadeTransition fade =
            new FadeTransition(
                Duration.seconds(0.8),
                root
            );

        fade.setFromValue(0);

        fade.setToValue(1);

        zoom.play();

        fade.play();

        return scene;
    }
    
    public VBox crearBotonEstadio(
            Estadio estadio,

            StackPane overlay,

            ImageView imgDetalle,

            Label lblNombre,

            Label lblPais,

            Label lblCiudad,

            Label lblEquipo,

            Label lblInfraestructura
    ) {

        String path =
            "file:src/images/estadios/"
            + estadio.getCodigo()
            + ".jpg";

        ImageView img =
            new ImageView(
                new Image(path)
            );

        img.setFitWidth(240);

        img.setFitHeight(150);

        img.setPreserveRatio(false);

        img.setSmooth(true);

        // =========================
        // BORDES REDONDEADOS
        // =========================

        Rectangle clip =
            new Rectangle(
                260,
                170
            );

        clip.setArcWidth(30);

        clip.setArcHeight(30);

        img.setClip(clip);

        // =========================
        // BOTON IMAGEN
        // =========================

        StackPane boton =
            new StackPane(img);

        boton.setPrefSize(260, 170);

        boton.setMaxSize(260, 170);

        boton.setCursor(
            javafx.scene.Cursor.HAND
        );
        
        Rectangle clipBoton =
            new Rectangle(
                260,
                170
            );

        clipBoton.setArcWidth(30);

        clipBoton.setArcHeight(30);

        boton.setClip(clipBoton);

        // =========================
        // HOVER
        // =========================

        ScaleTransition agrandar =
            new ScaleTransition(
                Duration.millis(150),
                boton
            );

        agrandar.setToX(1.05);

        agrandar.setToY(1.05);

        ScaleTransition achicar =
            new ScaleTransition(
                Duration.millis(150),
                boton
            );

        achicar.setToX(1);

        achicar.setToY(1);

        boton.setOnMouseEntered(
            e -> agrandar.playFromStart()
        );

        boton.setOnMouseExited(
            e -> achicar.playFromStart()
        );

        // =========================
        // FUENTE FWC
        // =========================

        Font fuenteFWC;

        try {

            fuenteFWC =
                Font.loadFont(

                    new FileInputStream(
                        "src/fonts/FWC2026.ttf"
                    ),

                    14
                );

        } catch (Exception e) {

            fuenteFWC =
                Font.font(
                    "Arial",
                    FontWeight.BOLD,
                    14
                );
        }

     // =========================
     // NOMBRE PEQUEÑO
     // =========================

     Label lblNombrePequeno =
         new Label(
             estadio.getNombre()
         );

     lblNombrePequeno.setFont(
         fuenteFWC
     );

     lblNombrePequeno.setTextFill(
         Color.WHITE
     );

     lblNombrePequeno.setWrapText(true);

     lblNombrePequeno.setMaxWidth(260);

     lblNombrePequeno.setAlignment(
         Pos.CENTER
     );

     // =========================
     // CONTENEDOR FINAL
     // =========================

     VBox contenedor =
         new VBox(12);

     contenedor.setAlignment(
         Pos.CENTER
     );

     contenedor.getChildren().addAll(
         boton,
         lblNombrePequeno
     );
        
     // =========================
     // CLICK
     // =========================

     boton.setOnMouseClicked(e -> {

         imgDetalle.setImage(
             new Image(path)
         );

         lblNombre.setText(
             estadio.getNombre()
         );

         // =========================
         // COLOR PAIS
         // =========================

         Color colorPais =
             Color.WHITE;

         if (
             estadio.getPais()
             .equalsIgnoreCase("Canada")
         ) {

             colorPais =
                 Color.RED;
         }

         else if (
             estadio.getPais()
             .equalsIgnoreCase("Mexico")
         ) {

             colorPais =
                 Color.LIMEGREEN;
         }

         else {

             colorPais =
                 Color.DODGERBLUE;
         }

         lblPais.setText(
             "Pais: "
             + estadio.getPais()
         );

         lblPais.setTextFill(
             colorPais
         );

         lblCiudad.setText(
             "Ciudad: "
             + estadio.getCiudad()
         );

         lblEquipo.setText(
             "Equipo local: "
             + estadio.getEquipo()
         );

         lblInfraestructura.setText(
             estadio.getInfraestructura()
         );

         overlay.setOpacity(0);

         overlay.setVisible(true);

         FadeTransition fade =
             new FadeTransition(
                 Duration.millis(250),
                 overlay
             );

         fade.setFromValue(0);

         fade.setToValue(1);

         fade.play();
     });
     
     boton.setStyle(
        "-fx-background-radius: 30;" +
        "-fx-border-radius: 30;"
    );

    return contenedor;
    }
}