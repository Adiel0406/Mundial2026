import java.util.ArrayList;

import javafx.animation.ScaleTransition;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Duration;

public class PantallaQuiz {
	
	private int indiceActual = 0;

    private int puntaje = 0;
	
	public Scene crearScene(
	        Stage stage,
	        Quiz quiz
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
   		
   		ArrayList<Pregunta> preguntas =
   			    quiz.getPreguntas();
   		
   		Label titulo =
   		    new Label(
   		        "MODO TEST"
   		    );
   		
   		Label lblPregunta =
   			    new Label();
   		
   		lblPregunta.setWrapText(true);

   		lblPregunta.setMaxWidth(900);

   		lblPregunta.setTextFill(
   		    Color.WHITE
   		);

   		lblPregunta.setFont(
   		    Font.font(
   		        "Arial",
   		        FontWeight.BOLD,
   		        28
   		    )
   		);
   		
   		VBox opcionesBox =
   		    new VBox(20);

   		opcionesBox.setAlignment(
   		    Pos.CENTER
   		);
   		
   		VBox centro =
   		    new VBox(40);

   		centro.setAlignment(
   		    Pos.CENTER
   		);

   		centro.getChildren().addAll(
   		    titulo,
   		    lblPregunta,
   		    opcionesBox
   		);

   		layout.setCenter(
   		    centro
   		);
   		
   		Runnable[] mostrarPregunta =
   			new Runnable[1];

   		mostrarPregunta[0] =
   		    () -> {
   		    	if (
   		    		    indiceActual >= preguntas.size()
   		    		) {

   		    		    Resultado resultado =
   		    		        new Resultado(

   		    		            quiz.getParticipante().getNombre(),
   		    		            puntaje,
   		    		            quiz.getDificultad(),
   		    		            preguntas.size()
   		    		        );

   		    		    ManagerResultado managerResultado =
   		    		        new ManagerResultado();

   		    		    managerResultado.guardarResultado(

   		    		        resultado,

   		    		        Archivos.ARCHIVO_RESULTADOS
   		    		    );

   		    		    Alert fin =
   		    		        new Alert(
   		    		            Alert.AlertType.INFORMATION
   		    		        );

   		    		    fin.setHeaderText(null);

   		    		    fin.setContentText(

   		    		        "Quiz terminado\n\n"
   		    		        +

   		    		        "Puntaje: "
   		    		        + puntaje
   		    		    );

   		    		    fin.showAndWait();

   		    		    PantallaRecords records =
   		    		        new PantallaRecords();

   		    		    Scene scene =
   		    		        records.crearScene(stage);

   		    		    stage.setScene(scene);

   		    		    stage.setMaximized(true);

   		    		    return;
   		    		}
   		    	
   		    	Pregunta pregunta =
   		    		    preguntas.get(
   		    		        indiceActual
   		    		    );

   		    		lblPregunta.setText(

   		    		    (indiceActual + 1)
   		    		    +

   		    		    ". "

   		    		    +

   		    		    pregunta.getEnunciado()
   		    		);
   		    		
   		    		opcionesBox.getChildren().clear();
   		    		
   		    		if (
   		    			    pregunta
   		    			    instanceof
   		    			    PreguntaOpMultiple
   		    			) {
   		    			PreguntaOpMultiple op =
   		    			    (PreguntaOpMultiple)
   		    			        pregunta;
   		    			
   		    			for (
   		    				    String opcion
   		    				    :
   		    				    op.getOpciones()
   		    				) {
   		    				
   		    				Button btn =
   		    				    new Button(
   		    				        opcion
   		    				    );

   		    				btn.setPrefWidth(500);

   		    				btn.setFont(
   		    				    Font.font(
   		    				        "Arial",
   		    				        FontWeight.BOLD,
   		    				        18
   		    				    )
   		    				);

   		    				btn.setTextFill(
   		    				    Color.WHITE
   		    				);

   		    				btn.setStyle(

   		    				    "-fx-background-color: rgba(0,0,0,0.4);"
   		    				    +

   		    				    "-fx-border-color: white;"
   		    				    +

   		    				    "-fx-border-radius: 20;"
   		    				    +

   		    				    "-fx-background-radius: 20;"
   		    				    +

   		    				    "-fx-padding: 12;"
   		    				);
   		    				
   		    				btn.setOnAction(e -> {

   		    				    if (
   		    				        pregunta.validarRespuesta(
   		    				            opcion
   		    				        )
   		    				    ) {

   		    				        puntaje +=
   		    				            pregunta.getPuntuacion();
   		    				    }

   		    				    indiceActual++;

   		    				    mostrarPregunta[0].run();
   		    				});
   		    				
   		    				opcionesBox
   		    			    .getChildren()
   		    			    .add(btn);
   		    			}
   		    		} else if (
   		    			    pregunta
   		    			    instanceof
   		    			    PreguntaVF
   		    			) {
   		    			String[] opciones =
   		    			    {
   		    			        "Verdadero",
   		    			        "Falso"
   		    			    };

   		    			for (
   		    			    String op
   		    			    :
   		    			    opciones
   		    			) {

   		    			    Button btn =
   		    			        new Button(op);

   		    			    btn.setPrefWidth(400);

   		    			    btn.setStyle(

   		    			        "-fx-background-color: rgba(0,0,0,0.4);"
   		    			        +

   		    			        "-fx-border-color: white;"
   		    			        +

   		    			        "-fx-border-radius: 20;"
   		    			        +

   		    			        "-fx-background-radius: 20;"
   		    			    );

   		    			    btn.setTextFill(
   		    			        Color.WHITE
   		    			    );

   		    			    btn.setOnAction(e -> {

   		    			        if (
   		    			            pregunta.validarRespuesta(op)
   		    			        ) {

   		    			            puntaje +=
   		    			                pregunta.getPuntuacion();
   		    			        }

   		    			        indiceActual++;

   		    			        mostrarPregunta[0].run();
   		    			    });

   		    			    opcionesBox
   		    			        .getChildren()
   		    			        .add(btn);
   		    			}
   		    		}
   		};
   		
   		mostrarPregunta[0].run();
   		
   		Scene scene =
   			    new Scene(
   			        root,
   			        1280,
   			        720
   			    );

   			return scene;
	}
}
