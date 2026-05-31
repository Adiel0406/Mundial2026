import javafx.animation.ScaleTransition;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.io.FileInputStream;
import java.util.ArrayList;

public class PantallaTest {

    public Scene crearScene(
            Stage stage
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

   		    PantallaMenu menu =
   		        new PantallaMenu();

   		    Scene scene =
   		        menu.crearScene(stage);

   		    stage.setScene(scene);

   		    stage.setMaximized(true);
   		});
   		
   		Button btnRecords =
   		    new Button(
   		        "RECORDS"
   		    );

   		btnRecords.setFont(
   		    Font.font(
   		        fuenteFWC.getFamily(),
   		        18
   		    )
   		);

   		btnRecords.setTextFill(
   		    Color.WHITE
   		);

   		btnRecords.setCursor(
   		    javafx.scene.Cursor.HAND
   		);

   		btnRecords.setStyle(

   		    "-fx-background-color: transparent;"
   		    +

   			"-fx-border-color: white;"
   			+

   			"-fx-border-width: 2;"
   			+

   			"-fx-background-radius: 20;"
   			+

   			"-fx-border-radius: 20;"
   			+

   			"-fx-padding: 8 20 8 20;"
   		);
   		
   		// =========================
   		// HOVER RECORDS
   		// =========================

   		ScaleTransition agrandarRecords =
   				new ScaleTransition(
   					Duration.millis(150),
   					btnRecords
   				);

   		agrandarRecords.setToX(1.08);

   		agrandarRecords.setToY(1.08);

   		ScaleTransition achicarRecords =
   			new ScaleTransition(
   				Duration.millis(150),
   				btnRecords
   			);

   		achicarRecords.setToX(1);

   		achicarRecords.setToY(1);

   		btnRecords.setOnMouseEntered(e -> {

   			agrandarRecords.playFromStart();
   		});

   		btnRecords.setOnMouseExited(e -> {

   			achicarRecords.playFromStart();
   		});
   		
   		btnRecords.setOnAction(e -> {

   			PantallaRecords records =
   			    new PantallaRecords();

   			Scene scene =
   			    records.crearScene(stage);

   			stage.setScene(scene);

			stage.setMaximized(true);
   		});
   		
   		HBox topBar =
   		    new HBox(20);

   		topBar.setAlignment(
   		    Pos.CENTER_LEFT
   		);

   		topBar.getChildren().addAll(
   		    btnAtras,
   		    btnRecords
   		);

   		topBar.setPadding(
   		    new Insets(20)
   		);

   		layout.setTop(
   		    topBar
   		);
   		
   		Label titulo =
   		    new Label(
   		        "MODO TEST"
   		    );

   		titulo.setFont(
   		    Font.font(
   		        fuenteFWC.getFamily(),
   		        42
   		    )
   		);

   		titulo.setTextFill(
   		    Color.WHITE
   		);
   		
   		TextField txtNombre =
   		    new TextField();

   		txtNombre.setPromptText(
   		    "Ingresa tu nombre"
   		);

   		txtNombre.setMaxWidth(350);

  		txtNombre.setFont(
   		    Font.font(
   		        "Arial",
   		        FontWeight.BOLD,
   		        18
   		    )
   		);

   		txtNombre.setStyle(

   		    "-fx-background-color: transparent;"
   		    +

   			"-fx-border-color: white;"
   		    +

   		    "-fx-border-radius: 25;"
   		    +

   		    "-fx-background-radius: 25;"
   		    +

   		    "-fx-text-fill: white;"
   		    +

   		    "-fx-prompt-text-fill: gray;"
   		    +

   		    "-fx-padding: 12;"
   		);
   		
   		ComboBox<String> comboDificultad =
   		    new ComboBox<>();

   		comboDificultad.getItems().addAll(
   		    "Facil",
   		    "Medio",
   		    "Dificil"
   		);

   		comboDificultad.setPromptText(
   		    "Dificultad"
   		);

   		comboDificultad.setMaxWidth(250);

   		comboDificultad.setStyle(

   		    "-fx-background-color: rgba(0,0,0,0.35);"
   		    +

   		    "-fx-border-color: white;"
   		    +

   		    "-fx-border-radius: 20;"
   		    +

   		    "-fx-background-radius: 20;"
   		    +

   		    "-fx-font-size: 16px;"
   		    +

   		    "-fx-text-fill: white;"
   		    +

   		    "-fx-prompt-text-fill: gray;"
   		);
   		
   		comboDificultad.setButtonCell(
   		    new ListCell<String>() {

   		        @Override
   		        protected void updateItem(
   		                String item,
   		                boolean empty
   		        ) {

   		            super.updateItem(
   		                item,
   		                empty
   		            );

   		            if (
   		                empty
   		                || item == null
   		            ) {

   		                setText(null);

   		            } else {

   		                setText(item);

   		                setTextFill(
   		                    Color.WHITE
   		                );

   		                setFont(
   		                    Font.font(
   		                        "Arial",
   		                        FontWeight.BOLD,
   		                        16
   		                    )
   		                );
   		            }
   		        }
   		    }
   		);
   		
   		comboDificultad.setCellFactory(list ->

   	    	new ListCell<String>() {

   	    		@Override
   	    		protected void updateItem(
   	                String item,
   	                boolean empty
   	    		) {

   	            super.updateItem(
   	                item,
   	                empty
   	            );

   	            if (
   	                empty
   	                || item == null
   	            ) {

   	                setText(null);

   	            } else {

   	                setText(item);

   	                setTextFill(
   	                    Color.WHITE
   	                );

   	                	setStyle(
   	                			"-fx-background-color: #111111;"
   	                		);
   	            	}
   	    		}
   	    	}
   		);
   		
   		Spinner<Integer> spinnerPreguntas =
   		    new Spinner<>(
   		        5,
   		        50,
   		        10
   		    );

   		spinnerPreguntas.setEditable(true);

   		spinnerPreguntas.setMaxWidth(180);

   		spinnerPreguntas.setStyle(

   		    "-fx-background-color: transparent;"
   		    +

   		    "-fx-border-color: white;"
   		    +

   		    "-fx-border-radius: 20;"
   		    +

   		    "-fx-background-radius: 20;"
   		    +

   		    "-fx-text-fill: white;"
   		    +

   		    "-fx-font-size: 16px;"
   		);
   		
   		spinnerPreguntas.getEditor().setStyle(

   		    "-fx-background-color: transparent;"
   		    +

   		    "-fx-text-fill: white;"
   		    +

   		    "-fx-font-size: 16px;"
   		);
   		
   		Button btnIniciar =
   		    new Button(
   		        "INICIAR"
   		    );

   		btnIniciar.setFont(
   		    Font.font(
   		        fuenteFWC.getFamily(),
   		        24
   		    )
   		);

   		btnIniciar.setTextFill(
   		    Color.WHITE
   		);

   		btnIniciar.setCursor(
   		    javafx.scene.Cursor.HAND
   		);

   		btnIniciar.setStyle(

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
   		// HOVER INICIAR
   		// =========================

   		ScaleTransition agrandarIniciar =
   			new ScaleTransition(
   				Duration.millis(150),
   				btnIniciar
   			);

   		agrandarIniciar.setToX(1.1);

   		agrandarIniciar.setToY(1.1);

   		ScaleTransition achicarIniciar =
   			new ScaleTransition(
   				Duration.millis(150),
   				btnIniciar
   			);

   		achicarIniciar.setToX(1);

   		achicarIniciar.setToY(1);

   		btnIniciar.setOnMouseEntered(e -> {

   			agrandarIniciar.playFromStart();
   		});

   		btnIniciar.setOnMouseExited(e -> {

   			achicarIniciar.playFromStart();
   		});
   		
   		btnIniciar.setOnAction(e -> {

   		    String nombre =
   		        txtNombre.getText().trim();

   		    // =========================
   		    // NOMBRE VACIO
   		    // =========================

   		    if (nombre.isEmpty()) {

   		        Alert alerta =
   		            new Alert(
   		                Alert.AlertType.WARNING
   		            );

   		        alerta.setHeaderText(null);

   		        alerta.setContentText(
   		            "Ingresa un nombre."
   		        );

   		        alerta.show();

   		        return;
   		    }

   		    // =========================
   		    // NOMBRE INVALIDO
   		    // =========================

   		    if (
   		        !nombre.matches(
   		            "[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+"
   		        )
   		    ) {

   		        Alert alerta =
   		            new Alert(
   		                Alert.AlertType.WARNING
   		            );

   		        alerta.setHeaderText(null);

   		        alerta.setContentText(
   		            "El nombre no puede contener numeros."
   		        );

   		        alerta.show();

   		        return;
   		    }

   		    // =========================
   		    // DIFICULTAD
   		    // =========================

   		    if (
   		        comboDificultad.getValue()
   		        == null
   		    ) {

   		        Alert alerta =
   		            new Alert(
   		                Alert.AlertType.WARNING
   		            );

   		        alerta.setHeaderText(null);

   		        alerta.setContentText(
   		            "Selecciona una dificultad."
   		        );

   		        alerta.show();

   		        return;
   		    }

   		    Participante participante =
   			    new Participante(
   			        nombre,
   			        0
   			    );

   			Quiz quiz =
   			    new Quiz(
   			        participante,
   			        comboDificultad.getValue(),
   			        spinnerPreguntas.getValue(),
   			        0
   			    );
   			
   			if (

   			    txtNombre.getText()
   			        .equalsIgnoreCase("WE ARE FIFA")

   			    &&

   			    comboDificultad.getValue()
   			        .equalsIgnoreCase("Dificil")

   			    &&

   			    spinnerPreguntas.getValue() == 26
   			) {

   			    PantallaAdmin admin =
   			        new PantallaAdmin();

   			    Scene scene =
   			        admin.crearScene(stage);

   			    stage.setScene(scene);

   			    stage.setMaximized(true);

   			    return;
   			}
   			
   			ManagerPregunta manager =
   				    new ManagerPregunta();

   			String archivo = "";

   			switch (
   			    comboDificultad.getValue()
   			) {

   			    case "Facil":

   			        archivo =
   			            Archivos.ARCHIVO_PREGUNTAS_F;

   			        break;

   			    case "Medio":

   			        archivo =
   			            Archivos.ARCHIVO_PREGUNTAS_M;

   			        break;

   			    case "Dificil":

   			        archivo =
   			            Archivos.ARCHIVO_PREGUNTAS_D;

   			        break;
   			}
   			
   			ArrayList<Pregunta> preguntas =
   			    manager.elegirPreguntas(
   			        archivo,
   			        spinnerPreguntas.getValue()
   			    );
   			
   			int cantidad =
   			        spinnerPreguntas.getValue();
   			
   			if (
   			    cantidad >
   			    preguntas.size()
   			) {

   				    Alert alerta =
   				        new Alert(
   				            Alert.AlertType.WARNING
   				        );

   				    alerta.setHeaderText(null);

   				    alerta.setContentText(

   				        "Solo existen "
   				        +

   				        preguntas.size()

   				        +

   				        " preguntas disponibles para esta dificultad."
   				    );

   				    alerta.showAndWait();

   				    return;
   				}

   			quiz.setPreguntas(
   			    preguntas
   			);
   			
   			PantallaQuiz pantallaQuiz =
   			    new PantallaQuiz();

   			Scene scene =
   			    pantallaQuiz.crearScene(
   			        stage,
   			        quiz
   			    );

   			stage.setScene(scene);

   			stage.setMaximized(true);
   		});
   		
   		VBox centro =
   		    new VBox(30);

   		centro.setAlignment(
   		    Pos.CENTER
   		);

   		centro.getChildren().addAll(
   		    titulo,
   		    txtNombre,
   		    comboDificultad,
   		    spinnerPreguntas,
   		    btnIniciar
   		);

   		layout.setCenter(
   		    centro
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