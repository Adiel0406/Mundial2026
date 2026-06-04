import javafx.animation.FadeTransition;
import javafx.animation.ScaleTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
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

import javafx.scene.shape.Rectangle;

public class PantallaHistoria {

    public Scene crearScene(Stage stage) {

    	StackPane root = new StackPane();

    	BorderPane contenido = new BorderPane();

    	root.getChildren().add(contenido);

        // =========================
        // FONDO
        // =========================

        BackgroundImage bg = new BackgroundImage(
            new Image("images/FWC/fondo.png"),
            BackgroundRepeat.NO_REPEAT,
            BackgroundRepeat.NO_REPEAT,
            BackgroundPosition.CENTER,
            new BackgroundSize(
                100, 100, true, true, false, true
            )
        );

        contenido.setBackground(new Background(bg));

        // =========================
        // TITULO SUPERIOR
        // =========================

        Font fuenteFWC;

        try {
            fuenteFWC = Font.loadFont(
                new FileInputStream("src/fonts/FWC2026.ttf"),
                32
            );
        } catch (Exception e) {
            fuenteFWC = Font.font("Arial", FontWeight.BOLD, 32);
        }

        Label titulo = new Label("TODOS LOS CAMPEONES");
        titulo.setFont(fuenteFWC);
        titulo.setTextFill(Color.WHITE);

        HBox topTitle = new HBox(titulo);
        topTitle.setAlignment(Pos.CENTER);
        topTitle.setPadding(new Insets(20));

        // =========================
        // BOTON ATRAS
        // =========================

        ImageView btnAtras = new ImageView(
            new Image("images/FWC/atras.png")
        );

        btnAtras.setFitWidth(55);
        btnAtras.setPreserveRatio(true);
        btnAtras.setCursor(javafx.scene.Cursor.HAND);

        // hover
        ScaleTransition agrandar = new ScaleTransition(
            Duration.millis(150),
            btnAtras
        );
        agrandar.setToX(1.12);
        agrandar.setToY(1.12);

        ScaleTransition achicar = new ScaleTransition(
            Duration.millis(150),
            btnAtras
        );
        achicar.setToX(1);
        achicar.setToY(1);

        btnAtras.setOnMouseEntered(e -> agrandar.playFromStart());
        btnAtras.setOnMouseExited(e -> achicar.playFromStart());

        btnAtras.setOnMouseClicked(e -> {

            PantallaMenu menu = new PantallaMenu();

            Scene scene = menu.crearScene(stage);

            ScaleTransition zoom = new ScaleTransition(
                Duration.seconds(0.8),
                contenido
            );

            zoom.setToX(0.9);
            zoom.setToY(0.9);

            FadeTransition fade = new FadeTransition(
                Duration.seconds(0.8),
                contenido
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
        // TOP BAR (ATRAS + TITULO)
        // =========================

        BorderPane topBar = new BorderPane();

        topBar.setPadding(new Insets(20, 30, 20, 30));

        // =========================
        // IZQUIERDA
        // =========================

        topBar.setLeft(btnAtras);

        BorderPane.setAlignment(
            btnAtras,
            Pos.CENTER_LEFT
        );

        // =========================
        // CENTRO
        // =========================

        topBar.setCenter(titulo);

        BorderPane.setAlignment(
            titulo,
            Pos.CENTER
        );

        // =========================
        // AGREGAR ARRIBA
        // =========================

        contenido.setTop(topBar);
        
        ManagerHistoria manager =
        	    new ManagerHistoria();

        	ArrayList<HistoriaMundial> historias =
        	    manager.cargarHistorias(
        	        Archivos.ARCHIVO_HISTORIA
        	    );

        	// =========================
        	// CONTENEDOR HORIZONTAL
        	// =========================

        	HBox timeline = new HBox(90);

        	timeline.setAlignment(Pos.CENTER_LEFT);

        	timeline.setPadding(
        	    new Insets(80, 60, 80, 60)
        	);

        	// =========================
        	// FUENTE FWC
        	// =========================

        	try {

        	    fuenteFWC = Font.loadFont(
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
        	// CREAR AÑOS
        	// =========================
        	
        	StackPane overlay = new StackPane();

	        overlay.setStyle(
	            "-fx-background-color: rgba(0,0,0,0.75);"
	        );

	        overlay.setVisible(false);

	        // =========================
	        // CONTENEDOR PRINCIPAL
	        // =========================

	        HBox panel = new HBox(50);

	        panel.setAlignment(Pos.CENTER);

	        panel.setPadding(new Insets(40));

	        // =========================
	        // IMAGEN IZQUIERDA
	        // =========================

	        ImageView imgDetalle = new ImageView();

	        imgDetalle.setFitWidth(320);

	        imgDetalle.setPreserveRatio(true);

	        // =========================
	        // INFO DERECHA
	        // =========================

	        VBox info = new VBox(20);

	        info.setPrefWidth(500);

	        info.setPadding(new Insets(30));

	        info.setStyle(
	            "-fx-background-color: rgba(0,0,0,0.88);" +
	            "-fx-background-radius: 25;"
	        );

	        // =========================
	        // TEXTOS
	        // =========================

	        Label lblCampeon = new Label();

	        lblCampeon.setFont(
	            fuenteFWC
	        );

	        lblCampeon.setTextFill(Color.WHITE);

	        lblCampeon.setWrapText(true);

	        lblCampeon.setMaxWidth(420);

	        Label lblPartido = new Label();

	        lblPartido.setFont(
	            Font.font("Arial", 22)
	        );

	        lblPartido.setTextFill(Color.WHITE);

	        Label lblEstadio = new Label();

	        lblEstadio.setFont(
	            Font.font("Arial", 18)
	        );

	        lblEstadio.setTextFill(Color.LIGHTGRAY);

	        Label lblMundial = new Label();

	        lblMundial.setFont(
	            Font.font("Arial", 18)
	        );

	        lblMundial.setTextFill(Color.LIGHTGRAY);

	        Label lblDetalle = new Label();

	        lblDetalle.setFont(
	            Font.font("Arial", 17)
	        );

	        lblDetalle.setTextFill(Color.WHITE);

	        lblDetalle.setWrapText(true);

	        // =========================
	        // AGREGAR INFO
	        // =========================

	        info.getChildren().addAll(
	            lblCampeon,
	            lblPartido,
	            lblEstadio,
	            lblMundial,
	            lblDetalle
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
	        // AGREGAR AL ROOT
	        // =========================

	        root.getChildren().add(overlay);
	        
	        overlay.setOnMouseClicked(e -> {

	            FadeTransition fade =
	                new FadeTransition(
	                    Duration.millis(200),
	                    overlay
	                );

	            fade.setFromValue(1);

	            fade.setToValue(0);

	            fade.setOnFinished(ev -> {
	                overlay.setVisible(false);
	            });

	            fade.play();
	        });

        	int index = 0;

        	for (HistoriaMundial h : historias) {

        	    VBox bloque = new VBox(15);

        	    bloque.setAlignment(Pos.CENTER);

        	    // =========================
        	    // IMAGEN BOTON
        	    // =========================

        	    String path =
        	        "images/historia/"
        	        + h.getAnio()
        	        + ".jpg";

        	    ImageView img = new ImageView(
        	        new Image(path)
        	    );

        	    img.setFitWidth(165);

        	    img.setPreserveRatio(true);

        	    // =========================
        	    // BORDES REDONDEADOS
        	    // =========================

        	    Rectangle clip =
        	        new Rectangle(
        	            165,
        	            230
        	        );

        	    clip.setArcWidth(30);

        	    clip.setArcHeight(30);

        	    img.setClip(clip);

        	    // =========================
        	    // BOTON
        	    // =========================

        	    StackPane boton =
        	        new StackPane(img);

        	    boton.setPrefSize(165, 230);

        	    boton.setMaxSize(165, 230);

        	    boton.setStyle(
        	        "-fx-background-radius: 30;"
        	    );
        	    
        	    boton.setOnMouseClicked(e -> {

    	            // =========================
    	            // IMAGEN GRANDE
    	            // =========================

    	            imgDetalle.setImage(
    	                new Image(path)
    	            );

    	            // =========================
    	            // DATOS
    	            // =========================

    	            lblCampeon.setText(
    	                h.getCampeon()
    	            );

    	            lblPartido.setText(
    	                h.getCampeon()
    	                + " "
    	                + h.getResultado()
    	                + " "
    	                + h.getSubcampeon()
    	            );

    	            lblEstadio.setText(
    	                "Estadio: "
    	                + h.getEstadio()
    	            );

    	            lblMundial.setText(
    	            	"Mundial: "
    	                + h.getSede()
    	                + " "
    	                + h.getAnio()
    	            );

    	            lblDetalle.setText(
    	                h.getContexto()
    	            );

    	            // =========================
    	            // MOSTRAR OVERLAY
    	            // =========================

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

        	    // =========================
        	    // HOVER
        	    // =========================

        	    ScaleTransition agrandar1 =
        	        new ScaleTransition(
        	            Duration.millis(150),
        	            boton
        	        );

        	    agrandar1.setToX(1.08);
        	    agrandar1.setToY(1.08);

        	    ScaleTransition achicar1 =
        	        new ScaleTransition(
        	            Duration.millis(150),
        	            boton
        	        );

        	    achicar1.setToX(1);
        	    achicar1.setToY(1);

        	    boton.setOnMouseEntered(
        	        e -> agrandar1.playFromStart()
        	    );

        	    boton.setOnMouseExited(
        	        e -> achicar1.playFromStart()
        	    );

        	    // =========================
        	    // AÑO
        	    // =========================

        	    Label lblAnio =
        	        new Label(
        	            String.valueOf(h.getAnio())
        	        );

        	    lblAnio.setFont(fuenteFWC);

        	    lblAnio.setTextFill(Color.WHITE);

        	    // =========================
        	    // LINEA
        	    // =========================

        	    Region linea =
        	        new Region();

        	    linea.setPrefSize(220, 5);

        	    linea.setStyle(
        	        "-fx-background-color: white;" +
        	        "-fx-background-radius: 10;"
        	    );

        	    // =========================
        	    // INTERCALAR ARRIBA/ABAJO
        	    // =========================

        	    if (index % 2 == 0) {

        	        bloque.getChildren().addAll(
        	            boton,
        	            lblAnio,
        	            linea
        	        );

        	    } else {

        	        bloque.getChildren().addAll(
        	            lblAnio,
        	            linea,
        	            boton
        	        );
        	    }

        	    timeline.getChildren().add(bloque);

        	    // =========================
        	    // GUERRA MUNDIAL
        	    // =========================

        	    if (h.getAnio() == 1938) {

        	        VBox guerra =
        	            new VBox(15);

        	        guerra.setAlignment(Pos.CENTER);

        	        Label lblGuerra =
        	            new Label(
        	                "GUERRA MUNDIAL"
        	            );

        	        lblGuerra.setFont(
        	            Font.font(
        	                "Arial",
        	                FontWeight.BOLD,
        	                20
        	            )
        	        );

        	        lblGuerra.setTextFill(
        	            Color.LIGHTGRAY
        	        );

        	        Region lineaGuerra =
        	            new Region();

        	        lineaGuerra.setPrefSize(420, 5);

        	        lineaGuerra.setStyle(
        	            "-fx-background-color: rgba(255,255,255,0.35);" +
        	            "-fx-background-radius: 10;"
        	        );

        	        guerra.getChildren().addAll(
        	            lblGuerra,
        	            lineaGuerra
        	        );

        	        timeline.getChildren().add(guerra);
        	    }

        	    index++;
        	}

        	// =========================
        	// SCROLL HORIZONTAL
        	// =========================

        	ScrollPane scroll =
        	    new ScrollPane();

        	scroll.setContent(timeline);

        	scroll.setPannable(true);

        	scroll.setFitToHeight(true);

        	scroll.setHbarPolicy(
        	    ScrollPane.ScrollBarPolicy.AS_NEEDED
        	);

        	scroll.setVbarPolicy(
        	    ScrollPane.ScrollBarPolicy.NEVER
        	);

        	scroll.setStyle(
        	    "-fx-background: transparent;" +
        	    "-fx-background-color: transparent;"
        	);

        	// importante
        	timeline.setMinWidth(Region.USE_PREF_SIZE);

        	contenido.setCenter(scroll);
        
        // =========================
        // SCENE
        // =========================

        Scene scene = new Scene(root, 1280, 720);

       	// =========================
       	// ANIMACION ENTRADA
       	// =========================

       	ScaleTransition zoom = new ScaleTransition(
       		Duration.seconds(0.8),
       		contenido
        );

        zoom.setFromX(1.2);
        zoom.setFromY(1.2);
        zoom.setToX(1);
        zoom.setToY(1);

        FadeTransition fade = new FadeTransition(
            Duration.seconds(0.8),
            contenido
        );

        fade.setFromValue(0);
        fade.setToValue(1);

        zoom.play();
        fade.play();

        return scene;
    }
}