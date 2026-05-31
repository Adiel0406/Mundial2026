import java.util.ArrayList;

import javafx.geometry.Insets;
import javafx.geometry.Pos;

import javafx.scene.Scene;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import javafx.scene.layout.*;

import javafx.scene.paint.Color;

import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import javafx.stage.Stage;

public class PantallaAdmin {
	public Scene crearScene(Stage stage) {
		
		ManagerPregunta manager =
		    new ManagerPregunta();
		
		ArrayList<Pregunta> faciles =
		    manager.cargarPreguntas(
		        Archivos.ARCHIVO_PREGUNTAS_F
		    );

		ArrayList<Pregunta> medias =
		    manager.cargarPreguntas(
		        Archivos.ARCHIVO_PREGUNTAS_M
		    );

		ArrayList<Pregunta> dificiles =
		    manager.cargarPreguntas(
		        Archivos.ARCHIVO_PREGUNTAS_D
		    );
		
		BorderPane root =
		    new BorderPane();

		root.setStyle(

		    "-fx-background-color: linear-gradient(to bottom, #111111, #000000);"
		);
		
		Label titulo =
		    new Label("ADMIN PANEL");

		titulo.setFont(
		    Font.font(
		        "Arial",
		        FontWeight.BOLD,
		        34
		    )
		);

		titulo.setTextFill(
		    Color.WHITE
		);
		
		ListView<Pregunta> listaFacil =
		    new ListView<>();

		listaFacil.getItems().addAll(
		    faciles
		);
		
		ListView<Pregunta> listaMedio =
		    new ListView<>();

		listaMedio.getItems().addAll(
		    medias
		);
		
		ListView<Pregunta> listaDificil =
		    new ListView<>();

		listaDificil.getItems().addAll(
		    dificiles
		);
		
		listaFacil.setPrefSize(300, 500);

		listaMedio.setPrefSize(300, 500);

		listaDificil.setPrefSize(300, 500);
		
		Label lblF =
		    new Label("FACIL");

		Label lblM =
		    new Label("MEDIO");

		Label lblD =
		    new Label("DIFICIL");
		
		VBox colF =
		    new VBox(10);

		colF.setAlignment(Pos.CENTER);

		colF.getChildren().addAll(
		    lblF,
		    listaFacil
		);
		
		VBox colM =
		    new VBox(10);

		colM.setAlignment(Pos.CENTER);

		colM.getChildren().addAll(
		    lblM,
		    listaMedio
		);
		
		VBox colD =
		    new VBox(10);

		colD.setAlignment(Pos.CENTER);

		colD.getChildren().addAll(
		    lblD,
		    listaDificil
		);
		
		HBox centro =
		    new HBox(30);

		centro.setAlignment(Pos.CENTER);

		centro.getChildren().addAll(
		    colF,
		    colM,
		    colD
		);
		
		Button btnEliminar =
		    new Button(
		        "Eliminar Pregunta"
		    );
		btnEliminar.setOnAction(e -> {
			Pregunta seleccionada = null;

			String archivo = "";
			
			if (

			    listaFacil.getSelectionModel()
			        .getSelectedItem()

			    != null
			) {

			    seleccionada =
			        listaFacil.getSelectionModel()
			            .getSelectedItem();

			    faciles.remove(seleccionada);

			    listaFacil.getItems()
			        .remove(seleccionada);

			    archivo =
			        Archivos.ARCHIVO_PREGUNTAS_F;

			    manager.guardarLista(
			        faciles,
			        archivo
			    );
			}
			else if (

			    listaMedio.getSelectionModel()
			        .getSelectedItem()

			    != null
			) {

			    seleccionada =
			        listaMedio.getSelectionModel()
			            .getSelectedItem();

			    medias.remove(seleccionada);

			    listaMedio.getItems()
			        .remove(seleccionada);

			    archivo =
			        Archivos.ARCHIVO_PREGUNTAS_M;

			    manager.guardarLista(
			        medias,
			        archivo
			    );
			}
			else if (

			    listaDificil.getSelectionModel()
			        .getSelectedItem()

			    != null
			) {

			    seleccionada =
			        listaDificil.getSelectionModel()
			            .getSelectedItem();

			    dificiles.remove(seleccionada);

			    listaDificil.getItems()
			        .remove(seleccionada);

			    archivo =
			        Archivos.ARCHIVO_PREGUNTAS_D;

			    manager.guardarLista(
			        dificiles,
			        archivo
			    );
			}
		});
		
		Button btnAgregar =
		    new Button(
		        "Agregar Pregunta"
		    );
		
		btnAgregar.setOnAction(e -> {

		    PantallaAgregarPregunta agregar =
		        new PantallaAgregarPregunta();

		    Scene scene =
		        agregar.crearScene(stage);

		    stage.setScene(scene);
		});
		
		Button btnAtras =
		    new Button("Atras");
		
		btnAtras.setOnAction(e -> {

		    PantallaTest test =
		        new PantallaTest();

		    Scene scene =
		        test.crearScene(stage);

		    stage.setScene(scene);
		});
		
		HBox botones =
		    new HBox(20);

		botones.setAlignment(
		    Pos.CENTER
		);

		botones.getChildren().addAll(
		    btnAgregar,
		    btnEliminar,
		    btnAtras
		);
		
		VBox top =
		    new VBox(20);

		top.setAlignment(Pos.CENTER);

		top.setPadding(
		    new Insets(20)
		);

		top.getChildren().add(
		    titulo
		);
		
		root.setTop(top);

		root.setCenter(centro);

		root.setBottom(botones);

		BorderPane.setMargin(
		    botones,
		    new Insets(20)
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
