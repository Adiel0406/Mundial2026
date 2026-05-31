import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import java.io.FileInputStream;
import javafx.animation.ScaleTransition;
import javafx.util.Duration;
import javafx.scene.image.ImageView;

public class PantallaPais {

    public Scene crearScene(
            Stage stage,
            Pais pais
    ) {
    	StackPane root =
    		new StackPane();

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
    	
    	BorderPane layout =
    		new BorderPane();

    	root.getChildren().add(
    	    layout
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

    	    PantallaTourMundial tour =
    	        new PantallaTourMundial();

    	    Scene scene =
    	        tour.crearScene(stage);

    	    stage.setScene(scene);

    	    stage.setMaximized(true);
    	});
    		
    	Font fuenteFWC;

    	try {

    	    fuenteFWC =
    	        Font.loadFont(
    	            new FileInputStream(
    	                "src/fonts/FWC2026.ttf"
    	            ),
    	            42
    	        );

    	} catch (Exception e) {

    	    fuenteFWC =
    	        Font.font(
    	            "Arial",
    	            FontWeight.BOLD,
    	            42
    	        );
   		}
    	
    	Label titulo =
    	    new Label(
    	        pais.getNombre()
    	    );

    	titulo.setFont(
    	    fuenteFWC
    	);

    	titulo.setTextFill(
    	    Color.WHITE
    	);
    	
    	HBox info =
    	    new HBox(40);

    	info.setAlignment(
    	    Pos.CENTER
    	);

    	// =========================
    	// CONTINENTE
    	// =========================

    	Label continente =
    	    new Label(
    	        "CONTINENTE: "
    	        + pais.getContinente()
    	    );

    	continente.setFont(
    	    Font.font(
    	        fuenteFWC.getFamily(),
    	        18
    	    )
    	);

    	continente.setTextFill(
    	    Color.WHITE
    	);

    	// =========================
    	// MUNDIALES
    	// =========================

    	Label mundiales =
    	    new Label(
    	        "MUNDIALES: "
    	        + pais.getMundiales()
    	    );

    	mundiales.setFont(
    	    Font.font(
    	        fuenteFWC.getFamily(),
    	        18
    	    )
    	);

    	mundiales.setTextFill(
    	    Color.WHITE
    	);

    	// =========================
    	// PARTICIPACIONES
    	// =========================

    	Label participaciones =
    	    new Label(
    	        "PARTICIPACIONES: "
    	        + pais.getParticipantes()
    	    );

    	participaciones.setFont(
    	    Font.font(
    	        fuenteFWC.getFamily(),
    	        18
    	    )
    	);

    	participaciones.setTextFill(
    	    Color.WHITE
    	);

    	info.getChildren().addAll(
    	    continente,
    	    mundiales,
    	    participaciones
    	);
    	
    	BorderPane topPane =
    	    new BorderPane();

    	topPane.setPadding(
    	    new Insets(20)
    	);

    	topPane.setLeft(
    	    btnAtras
    	);

    	VBox infoSuperior =
    	    new VBox(20);

    	infoSuperior.setAlignment(
    	    Pos.CENTER
    	);

    	infoSuperior.getChildren().addAll(
    	    titulo,
    	    info
    	);

    	topPane.setCenter(
    	    infoSuperior
    	);

    	layout.setTop(
    	    topPane
    	);
    	
    	Label tituloCultura =
    	    new Label(
    	        "CULTURA"
    	    );

    	tituloCultura.setFont(
    	    Font.font(
    	        fuenteFWC.getFamily(),
    	        24
    	    )
    	);

    	tituloCultura.setTextFill(
    	    Color.WHITE
    	);

    	Label cultura =
    	    new Label(
    	        pais.getCultura()
    	    );

    	cultura.setWrapText(true);

   		cultura.setTextFill(
   		    Color.WHITE
   		);

   		cultura.setFont(
   		    Font.font(
   		        "Arial",
   		        18
   		    )
   		);

   		cultura.setMaxWidth(350);

   		VBox boxCultura =
   		    new VBox(20);

   		boxCultura.setAlignment(
   		    Pos.TOP_CENTER
   		);

   		boxCultura.getChildren().addAll(
   		    tituloCultura,
   		    cultura
   		);

   		Label tituloFutbol =
   		    new Label(
   		        "FUTBOL"
   		    );

   		tituloFutbol.setFont(
   		    Font.font(
   		        fuenteFWC.getFamily(),
   		        24
   		    )
   		);

   		tituloFutbol.setTextFill(
   		    Color.WHITE
   		);

   		Label futbol =
   		    new Label(
   		        pais.getDatosFutbol()
   		    );

   		futbol.setWrapText(true);

		futbol.setTextFill(
		    Color.WHITE
		);

		futbol.setFont(
   		    Font.font(
   		        "Arial",
   		        18
   		    )
   		);

   		futbol.setMaxWidth(350);

   		VBox boxFutbol =
   		    new VBox(20);

   		boxFutbol.setAlignment(
   		    Pos.TOP_CENTER
   		);

   		boxFutbol.getChildren().addAll(
   		    tituloFutbol,
   		    futbol
   		);
    	
    	HBox centro =
    	    new HBox(80);

    	centro.setAlignment(
    	    Pos.CENTER
    	);

    	centro.setPadding(
    	    new Insets(30)
    	);

    	centro.getChildren().addAll(
    	    boxCultura,
    	    boxFutbol
    	);

    	layout.setCenter(
    	    centro
    	);
    	
    	VBox jugadoresBox =
    		new VBox(25);

    	jugadoresBox.setAlignment(
    	    Pos.CENTER
    	);
    	
    	for (
    	    int i = 0;

    	    i < 3
    	    &&
    	    i < pais.getJugadores().size();

    	    i++
    	) {

    	    Jugador j =
    	        pais.getJugadores().get(i);
    	    
   		    VBox card =
    	        new VBox(10);

    	    card.setAlignment(
    	        Pos.CENTER
    	    );

    	    card.setPadding(
    	        new Insets(12)
    	    );

    	    card.setPrefWidth(380);

    	    card.setStyle(

    	        "-fx-background-color: #1B4332;"
    	        +

    	        "-fx-background-radius: 25;"
    	    );

    	    Label nombre =
    	        new Label(
    	            j.getNombre()
    	        );

    	    nombre.setFont(
    	        Font.font(
    	            fuenteFWC.getFamily(),
    	            24
    	        )
    	    );

    	    nombre.setTextFill(
    	        Color.WHITE
    	    );
    	    
    	    nombre.setWrapText(true);

    	    nombre.setAlignment(
    	        Pos.CENTER
    	    );

    	    nombre.setTextAlignment(
    	        TextAlignment.CENTER
    	    );

    	    HBox datosJugador =
    	        new HBox(25);

    	    datosJugador.setAlignment(
    	        Pos.CENTER
    	    );

    	    // =========================
    	    // NUMERO
    	    // =========================

    	    Label numero =
    	        new Label(
    	            "NUMERO: "
    	            + j.getNumero()
    	        );

    	    numero.setFont(
    	        Font.font(
    	            fuenteFWC.getFamily(),
    	            14
    	        )
    	    );

    	    numero.setTextFill(
    	        Color.WHITE
    	    );

    	    // =========================
    	    // POSICION
    	    // =========================

    	    Label posicion =
    	        new Label(
    	            "POSICION: "
    	            + j.getPosicion()
    	        );

    	    posicion.setFont(
    	        Font.font(
    	            fuenteFWC.getFamily(),
    	            14
    	        )
    	    );

    	    posicion.setTextFill(
    	        Color.WHITE
    	    );

    	    datosJugador.getChildren().addAll(
    	        numero,
    	        posicion
    	    );
    	    
    	    card.getChildren().addAll(
    	        nombre,
    	        datosJugador
    	    );

    	    jugadoresBox.getChildren().add(
    	        card
    	    );
    	}
    	
    	VBox abajo =
    	    new VBox(20);

    	abajo.setAlignment(
    	    Pos.CENTER
    	);

    	Label destacados =
    	    new Label(
    	        "JUGADORES DESTACADOS"
    	    );

    	destacados.setFont(
    	    Font.font(
    	        fuenteFWC.getFamily(),
    	        32
    	    )
    	);

    	destacados.setTextFill(
    	    Color.WHITE
    	);

    	abajo.getChildren().addAll(
    	    destacados,
    	    jugadoresBox
    	);

    	abajo.setPadding(
    	    new Insets(30)
    	);

    	layout.setBottom(
    	    abajo
    	);
    	
    	Scene scene =
    	    new Scene(
    	        root,
    	        1280,
    	        720
    	    );

    	return scene;
    }
}