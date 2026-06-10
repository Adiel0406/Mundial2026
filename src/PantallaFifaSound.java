import javafx.animation.FadeTransition;
import javafx.animation.RotateTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
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

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;
import java.util.Collections;

import javafx.scene.Cursor;



public class PantallaFifaSound {
	
	private static MediaPlayer reproductor;

	private static ArrayList<Cancion> playlist;

	private static int indicePlaylist = 0;
	
	private static ImageView discoView;

	private static ImageView portadaView;

	private static RotateTransition rotacionDisco;
	
	private static Label lblCancionActual;
	
	private static Slider barraProgreso;
	
	private static ImageView btnPause;
	private static ImageView btnPlayControl;
	private static ImageView btnSiguiente;
	private static ImageView btnAnterior;
	
	private static Label lblTiempo;
	
	private static Button btnVideo;
	private static Button btnLetra;

	private static HBox contenedorPrincipal;
	private static VBox panelMusica;

	private static VBox panelDerecho;
	
	private static boolean modoExpandido = false;

	private static String modoActual = "";

	private static StackPane izquierda;
	
	private static VBox panelLetra;
	
	private static ArrayList<Label> labelsLetra;
	
	private static ScrollPane scrollLetra;
	
	private static Font fuenteFWClabel;
	
	private static TranslateTransition animacionLetra;

    public Scene crearScene(Stage stage) {

        BorderPane root =
            new BorderPane();

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

        // hover

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

        btnAtras.setOnMouseClicked(e -> {

            PantallaMenu menu =
                new PantallaMenu();

            Scene scene =
                menu.crearScene(stage);

            stage.setScene(scene);

            stage.setMaximized(true);
        });

        HBox topBar =
            new HBox(btnAtras);

        topBar.setPadding(
            new Insets(25)
        );

        root.setTop(topBar);

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

                    20
                );

        } catch (Exception e) {

            fuenteFWC =
                Font.font(
                    "Arial",
                    FontWeight.BOLD,
                    20
                );
        }
        
        fuenteFWClabel = fuenteFWC;

        // =========================
        // MANAGER
        // =========================

        ManagerCancion manager =
            new ManagerCancion();

        ArrayList<Cancion> canciones =
            manager.cargarCanciones(
                Archivos.ARCHIVO_CANCIONES
            );

        // =========================
        // PLAYLIST
        // =========================

        playlist =
        	new ArrayList<>(canciones);

        Collections.shuffle(playlist);

        // =========================
        // PANEL IZQUIERDO
        // =========================

        VBox listaCanciones =
            new VBox(18);

        listaCanciones.setPadding(
            new Insets(20)
        );

        listaCanciones.setPrefWidth(600);

        // =========================
        // CANCIONES
        // =========================

        for (Cancion c : canciones) {

            VBox contenido =
                new VBox(12);

            contenido.setPadding(
                new Insets(15)
            );

            contenido.setStyle(
                "-fx-background-color: #244A7A;" +
                "-fx-background-radius: 0 0 18 18;"
            );

            // =========================
            // CANTANTES
            // =========================

            for (
                Cantante cantante
                : c.getCantantes()
            ) {

                Label lblNombre =
                    new Label(
                        cantante.getNombre()
                    );

                lblNombre.setFont(
                    fuenteFWC
                );

                lblNombre.setTextFill(
                    Color.WHITE
                );

                Label lblDatos =
                    new Label(
                        cantante.getPais()
                        + " • "
                        + cantante.getEdad()
                        + " años"
                    );

                lblDatos.setFont(
                    Font.font("Arial", 15)
                );

                lblDatos.setTextFill(
                    Color.LIGHTGRAY
                );

                VBox bloqueCantante =
                    new VBox(
                        2,
                        lblNombre,
                        lblDatos
                    );

                contenido.getChildren().add(
                    bloqueCantante
                );
            }

            // =========================
            // TITLED PANE
            // =========================

            TitledPane panel =
                new TitledPane();

            // =========================
            // BOTON REPRODUCIR
            // =========================

            ImageView btnPlay =
            	new ImageView(

            		new Image(
            			"images/FWC/reproducir.png"
            			)
            		);

            btnPlay.setFitWidth(24);

            btnPlay.setPreserveRatio(true);

            btnPlay.setCursor(
            	Cursor.HAND
            );

            // hover

            ScaleTransition agrandarPlay =
            	new ScaleTransition(
            		Duration.millis(150),
            		btnPlay
            	);

            agrandarPlay.setToX(1.15);
            agrandarPlay.setToY(1.15);

            ScaleTransition achicarPlay =
            	new ScaleTransition(
            		Duration.millis(150),
            		btnPlay
            	);

            achicarPlay.setToX(1);
            achicarPlay.setToY(1);

            btnPlay.setOnMouseEntered(
            	e -> agrandarPlay.playFromStart()
            );

            btnPlay.setOnMouseExited(
            	e -> achicarPlay.playFromStart()
            );
            
            btnPlay.setOnMouseClicked(e -> {

                try {

                    // =========================
                    // CAMBIAR PLAYLIST
                    // =========================

                    indicePlaylist =
                        playlist.indexOf(c);

                    // =========================
                    // DETENER ACTUAL
                    // =========================

                    if (reproductor != null) {

                        reproductor.stop();
                        reproductor.dispose();
                    }
                    
                    // =========================
                    // CAMBIAR PORTADA
                    // =========================

                    try {

                    	portadaView.setImage(

                    		new Image(
                    			new FileInputStream(
                    				c.getRutaImagen()
                    			)
                    		)
                    	);

                    } catch (Exception ex) {

                    	ex.printStackTrace();
                    }

                    // =========================
                    // NUEVA CANCION
                    // =========================

                    indicePlaylist =
                        playlist.indexOf(c);

                    reproducirCancion();

                    reproductor.setVolume(0.6);

                    System.out.println(
                        "Reproduciendo manualmente: "
                        + c.getNombre()
                    );

                    // =========================
                    // CONTINUAR AUTOMATICO
                    // =========================

                    reproductor.setOnEndOfMedia(() -> {

                        indicePlaylist++;

                        reproducirCancion();
                    });

                } catch (Exception ex) {

                    ex.printStackTrace();
                }

                e.consume();
            });
            
            Label tituloCancion =
                new Label(
                    c.getNombre()
                );

            tituloCancion.setFont(
                fuenteFWC
            );

            tituloCancion.setTextFill(
                Color.WHITE
            );

            Region espacio =
                new Region();

            HBox.setHgrow(
                espacio,
                Priority.ALWAYS
            );

            HBox header =
                new HBox(
                    12,
                    tituloCancion,
                    espacio,
                    btnPlay
                );

            header.setAlignment(
                javafx.geometry.Pos.CENTER_LEFT
            );

           	panel.setGraphic(
           	    header
           	);

            panel.setContent(
                contenido
            );

            panel.setExpanded(false);

            panel.setFont(
                fuenteFWC
            );
            
            panel.setExpanded(false);

            panel.setAnimated(true);

            panel.setCollapsible(true);

            listaCanciones.getChildren().add(
                panel
            );
        }

        // =========================
        // SCROLL
        // =========================

        ScrollPane scroll =
            new ScrollPane(
                listaCanciones
            );

        scroll.setFitToWidth(true);

        scroll.setHbarPolicy(
            ScrollPane.ScrollBarPolicy.NEVER
        );

        scroll.setStyle(
            "-fx-background: transparent;" +
            "-fx-background-color: transparent;"
        );

        // =========================
        // PANEL IZQUIERDO FINAL
        // =========================

        izquierda =
        	    new StackPane(scroll);

        izquierda.setPadding(
            new Insets(20)
        );

        izquierda.setPrefWidth(600);

        // =========================
        // TITULO CANCION
        // =========================

        lblCancionActual =
        	new Label("NO SONG");

        lblCancionActual.setFont(
        	Font.font(
        		fuenteFWC.getFamily(),
        		34
        	)
        );

        lblCancionActual.setTextFill(
        	Color.WHITE
        );

        lblCancionActual.setWrapText(true);

        lblCancionActual.setMaxWidth(500);

        // =========================
        // DISCO
        // =========================

        discoView =
        	new ImageView(

        		new Image(
        			"images/imagenes_musica/disco.png"
        		)
        	);

        discoView.setFitWidth(340);
        
        discoView.setOpacity(0.95);

        discoView.setPreserveRatio(true);

        // =========================
        // PORTADA
        // =========================

        portadaView =
        	new ImageView();

        portadaView.setFitWidth(300);

        portadaView.setFitHeight(300);
        
        portadaView.setTranslateX(-100);

        portadaView.setPreserveRatio(false);

        // bordes redondos visuales

        portadaView.setStyle(
        	"-fx-background-radius: 18;"
        );

        // =========================
        // ROTACION
        // =========================

        rotacionDisco =
        	new RotateTransition(
        		Duration.seconds(8),
        		discoView
        	);
        
        rotacionDisco.setFromAngle(0);
        rotacionDisco.setToAngle(360);

        rotacionDisco.setCycleCount(
        	RotateTransition.INDEFINITE
        );
        
        rotacionDisco.setInterpolator(
            javafx.animation.Interpolator.LINEAR
        );

        // =========================
        // STACK
        // =========================

        StackPane musicaVisual =
        	new StackPane(
        		discoView,
        		portadaView
        	);

        musicaVisual.setPrefWidth(700);
        
        // =========================
        // BOTONES CONTROL
        // =========================

        btnAnterior =
        	new ImageView(
        		new Image(
        			"images/FWC/anterior.png"
        		)
        	);

        btnPause =
        	new ImageView(
        		new Image(
        			"images/FWC/pausa.png"
        		)
        	);

        btnPlayControl =
        	new ImageView(
        		new Image(
        			"images/FWC/play.png"
        		)
        	);

        btnSiguiente =
        	new ImageView(
        		new Image(
        			"images/FWC/siguiente.png"
        		)
        	);
        
        btnAnterior.setFitWidth(42);
        btnPause.setFitWidth(42);
        btnPlayControl.setFitWidth(42);
        btnSiguiente.setFitWidth(42);

        btnAnterior.setPreserveRatio(true);
        btnPause.setPreserveRatio(true);
        btnPlayControl.setPreserveRatio(true);
        btnSiguiente.setPreserveRatio(true);
        
        btnAnterior.setCursor(Cursor.HAND);
        btnPause.setCursor(Cursor.HAND);
        btnPlayControl.setCursor(Cursor.HAND);
        btnSiguiente.setCursor(Cursor.HAND);
        
        efectoHover(btnAnterior);
        efectoHover(btnPause);
        efectoHover(btnPlayControl);
        efectoHover(btnSiguiente);
        
     	// =========================
        // BARRA PROGRESO
        // =========================
        
        barraProgreso =
            new Slider();

        barraProgreso.setMin(0);

        barraProgreso.setMax(100);
       	
       	barraProgreso.setMinWidth(300);
       	barraProgreso.setPrefWidth(300);
       	barraProgreso.setMaxWidth(300);
       	
       	barraProgreso.setStyle(

       	    "-fx-control-inner-background: #FFFFFF;" +
       	    "-fx-base: #FFFFFF;" +
       	    "-fx-accent: #FFFFFF;"
       	);
       	
       	// =========================
        // TIEMPO
        // =========================
       	
       	lblTiempo =
       	    new Label("0:00 / 0:00");

       	lblTiempo.setFont(
       	    Font.font("Arial", 15)
       	);

       	lblTiempo.setTextFill(
       	    Color.WHITE
       	);
    
       	// =========================
       	// DERECHA
       	// =========================

       	

       	// =========================
       	// COLORES
       	// =========================

       	
       	// =========================
       	// FUENTES
       	// =========================

       	

       	// =========================
       	// WRAP TEXT
       	// =========================

       	

   		// =========================
   		// PANEL LETRA
   		// =========================

       	panelLetra = new VBox(20);

       	panelLetra.setAlignment(
       	    Pos.CENTER_LEFT
       	);

       	scrollLetra = new ScrollPane(panelLetra);
       	
       	scrollLetra.setHbarPolicy(
       	    ScrollPane.ScrollBarPolicy.NEVER
       	);

       	scrollLetra.setVbarPolicy(
       	    ScrollPane.ScrollBarPolicy.NEVER
       	);
       	
       	scrollLetra.setPannable(false);
       	
       	scrollLetra.setPrefHeight(500);
       	scrollLetra.setFitToWidth(true);

       	scrollLetra.setStyle(
       	    "-fx-background: transparent;" +
       	    "-fx-background-color: transparent;" +
       	    "-fx-border-color: transparent;"
       	);

       	panelDerecho = new VBox(scrollLetra);

       	panelDerecho.setAlignment(
       	    Pos.CENTER
       	);

       	panelDerecho.setPrefWidth(550);

       	panelDerecho.setVisible(false);

   		panelDerecho.setManaged(false);

       	// =========================
       	// STACK PLAY / PAUSE
       	// =========================

       	StackPane playPause =
       		new StackPane(
       			btnPlayControl,
       			btnPause
       		);
       	
       	btnVideo =
       	    new Button("VIDEO MUSICAL");

       	btnLetra =
       	    new Button("LETRA");
       	
       	btnVideo.setFont(
       	    Font.font("Arial", FontWeight.BOLD, 14)
       	);

       	btnLetra.setFont(
       	    Font.font("Arial", FontWeight.BOLD, 14)
       	);
       	
       	String estiloBoton =

       	    "-fx-background-color: transparent;" +

       	    "-fx-border-color: white;" +

       	    "-fx-border-width: 2;" +

       	    "-fx-background-radius: 25;" +

       	    "-fx-border-radius: 25;" +

       	    "-fx-text-fill: white;" +

       	    "-fx-padding: 8 18 8 18;";

       	btnVideo.setStyle(estiloBoton);

       	btnLetra.setStyle(estiloBoton);
       	
       	btnVideo.setOnAction(e -> {

       	    manejarModo("VIDEO");
       	});

       	btnLetra.setOnAction(e -> {

       	    manejarModo("LETRA");
       	});
       	
       	btnVideo.setCursor(Cursor.HAND);

       	btnLetra.setCursor(Cursor.HAND);
       	
       	hoverBoton(btnVideo);

       	hoverBoton(btnLetra);

       	// al iniciar:
       	// si ya reproduce -> mostrar pausa

       	btnPlayControl.setVisible(false);

       	HBox controles =
       	    new HBox(
       	        35,
       	        btnVideo,
       	        btnAnterior,
       	        playPause,
       	        btnSiguiente,
       	        btnLetra
       	    );

       	controles.setAlignment(
       	    Pos.CENTER
       	);

       	panelMusica =
       	    new VBox(
       	        30,
       	        lblCancionActual,
       	        musicaVisual,
       	        barraProgreso,
       	        lblTiempo,
       	        controles
       	    );

       	panelMusica.setAlignment(
       	    Pos.CENTER
       	);

       	contenedorPrincipal =
       	    new HBox(
       	        40,
       	        panelMusica,
       	        panelDerecho
       	    );

       	contenedorPrincipal.setAlignment(
       	    Pos.CENTER
       	);
       	
       	StackPane derecha =
       	    new StackPane(
       	        contenedorPrincipal
       	    );

        derecha.setPadding(
        	new Insets(20)
        );
        
        // iniciar
        reproducirCancion();

        // =========================
        // LAYOUT CENTRAL
        // =========================

        HBox centro =
            new HBox(
                izquierda,
                derecha
            );

        HBox.setHgrow(
            derecha,
            Priority.ALWAYS
        );

        root.setCenter(centro);

        // =========================
        // SCENE
        // =========================

        Scene scene =
            new Scene(
                root,
                1280,
                720
            );
        
        scene.addEventFilter(
        	    javafx.scene.input.KeyEvent.KEY_PRESSED,
        	    e -> {

            switch (e.getCode()) {

            	case SPACE -> {

            		e.consume();

            		if (reproductor == null)
            			return;

            		MediaPlayer.Status estado =
            			reproductor.getStatus();

            		if (
            			estado
            			== MediaPlayer.Status.PLAYING
            		) {

            			reproductor.pause();

            			btnPause.setVisible(false);

            			btnPlayControl.setVisible(true);

            			rotacionDisco.pause();

            		} else {

            			reproductor.play();

            			btnPlayControl.setVisible(false);

            			btnPause.setVisible(true);

            			rotacionDisco.play();
            		}
            	}
            	default -> {
            	}
            	}
        	}
       	);
        
        scene.getStylesheets().add(
            "styles/fifaSound.css"
        );

        // =========================
        // ANIMACION
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
    
    public static void reproducirCancion() {

        try {

            if (indicePlaylist >= playlist.size()) {

                Collections.shuffle(playlist);

                indicePlaylist = 0;
            }

            Cancion actual =
                playlist.get(indicePlaylist);
            
            if (labelsLetra != null) {
                cargarLetra();
            }
            
            lblCancionActual.setText(
                actual.getNombre().toUpperCase()
            );
            
            // =========================
            // CAMBIAR PORTADA
            // =========================

            try {

                portadaView.setImage(

                    new Image(
                        new FileInputStream(
                            actual.getRutaImagen()
                        )
                    )
                );

            } catch (Exception e) {

                e.printStackTrace();
            }

            Media media =
                new Media(

                    new File(
                        actual.getRutaAudio()
                    )
                    .toURI()
                    .toString()
                );

            if (reproductor != null) {

                reproductor.stop();
                reproductor.dispose();
            }

            reproductor =
                new MediaPlayer(media);

            reproductor.setVolume(0.6);

            reproductor.setCycleCount(1);

            reproductor.setOnError(() -> {

                System.out.println(
                    reproductor.getError()
                );
            });

            reproductor.setOnReady(() -> {
            	
            	btnPlayControl.setVisible(false);

            	btnPause.setVisible(true);

            	rotacionDisco.play();

                System.out.println(
                    "Reproduciendo: "
                    + actual.getNombre()
                );
                
                barraProgreso.setMax(
                    reproductor.getTotalDuration().toSeconds()
                );

                reproductor.play();
            });
            
            reproductor.currentTimeProperty().addListener(
                (obs, oldTime, newTime) -> {

                    barraProgreso.setValue(
                        newTime.toSeconds()
                    );

                    double tiempoActual =
                        newTime.toSeconds();

                    double total =
                        reproductor
                        .getTotalDuration()
                        .toSeconds();

                    lblTiempo.setText(

                        formatoTiempo(tiempoActual)

                        + " / "

                        + formatoTiempo(total)
                    );
                    
                    actualizarLetra(
                    	tiempoActual
                    );
                    
                }
            );
            
            barraProgreso.setOnMouseReleased(e -> {

                reproductor.seek(
                    Duration.seconds(
                        barraProgreso.getValue()
                    )
                );
            });
            
            btnPause.setOnMouseClicked(e -> {

                if (reproductor != null) {

                    reproductor.pause();

                    // cambiar botones

                    btnPause.setVisible(false);

                    btnPlayControl.setVisible(true);

                    // detener disco

                    rotacionDisco.pause();
                }
            });
            
            btnPlayControl.setOnMouseClicked(e -> {

                if (reproductor != null) {

                    reproductor.play();

                    // cambiar botones

                    btnPlayControl.setVisible(false);

                    btnPause.setVisible(true);

                    // continuar disco

                    rotacionDisco.play();
                }
            });
            
            btnSiguiente.setOnMouseClicked(e -> {

                indicePlaylist++;

                reproducirCancion();
            });
            
            btnAnterior.setOnMouseClicked(e -> {

                if (
                    reproductor.getCurrentTime()
                    .toSeconds() > 3
                ) {

                    reproductor.seek(
                        Duration.ZERO
                    );

                } else {

                    indicePlaylist--;

                    if (indicePlaylist < 0) {

                        indicePlaylist =
                            playlist.size() - 1;
                    }

                    reproducirCancion();
                }
            });

            reproductor.setOnEndOfMedia(() -> {

                indicePlaylist++;

                reproducirCancion();
            });

        } catch (Exception e) {

            e.printStackTrace();
        }
    }
    
    private static void efectoHover(ImageView btn) {

        ScaleTransition agrandar =
            new ScaleTransition(
                Duration.millis(120),
                btn
            );

        agrandar.setToX(1.12);
        agrandar.setToY(1.12);

        ScaleTransition volver =
            new ScaleTransition(
                Duration.millis(120),
                btn
            );

        volver.setToX(1);
        volver.setToY(1);

        btn.setOnMouseEntered(
            e -> agrandar.playFromStart()
        );

        btn.setOnMouseExited(
            e -> volver.playFromStart()
        );
    }
    
    private static String formatoTiempo(double segundos) {

        int min =
            (int) segundos / 60;

        int seg =
            (int) segundos % 60;

        return String.format(
            "%d:%02d",
            min,
            seg
        );
    }
    
    private static void moverPanelIzquierda() {

        TranslateTransition mover =
            new TranslateTransition(
                Duration.millis(450),
                panelMusica
            );

        mover.setToX(-200);

        mover.play();
    }
    
    private static void hoverBoton(Button btn) {

        ScaleTransition agrandar =
            new ScaleTransition(
                Duration.millis(120),
                btn
            );

        agrandar.setToX(1.08);
        agrandar.setToY(1.08);

        ScaleTransition volver =
            new ScaleTransition(
                Duration.millis(120),
                btn
            );

        volver.setToX(1);
        volver.setToY(1);

        btn.setOnMouseEntered(
            e -> agrandar.playFromStart()
        );

        btn.setOnMouseExited(
            e -> volver.playFromStart()
        );
    }
    
    private static void manejarModo(String modo) {

        System.out.println("Modo: " + modo);

        // =========================
        // CERRAR
        // =========================

        if (
            modoExpandido
            &&
            modoActual.equals(modo)
        ) {

            volverModoNormal();

            return;
        }

        // =========================
        // ABRIR
        // =========================

        modoExpandido = true;

        modoActual = modo;

        panelDerecho.setVisible(true);

        panelDerecho.setManaged(true);

        // =========================
        // VIDEO
        // =========================

        if (modo.equals("VIDEO")) {

            btnLetra.setDisable(true);

            btnVideo.setDisable(false);

            panelDerecho.getChildren().clear();

        }

        // =========================
        // LETRA
        // =========================

        else {

            btnVideo.setDisable(true);

            btnLetra.setDisable(false);

            panelDerecho.getChildren().clear();

            panelDerecho.getChildren().add(
                scrollLetra
            );

            if (reproductor != null) {

                cargarLetra();
            }
        }

        // =========================
        // OCULTAR LISTA
        // =========================

        izquierda.setVisible(false);

        izquierda.setManaged(false);

        // =========================
        // MOVER REPRODUCTOR
        // =========================

        moverPanelIzquierda();
    }
    
    private static void volverModoNormal() {

        modoExpandido = false;

        modoActual = "";

        // mostrar lista

        izquierda.setVisible(true);

        izquierda.setManaged(true);

        // habilitar botones

        btnVideo.setDisable(false);

        btnLetra.setDisable(false);
        
        panelDerecho.setVisible(false);

        panelDerecho.setManaged(false);

        // mover al centro

        TranslateTransition volver =
            new TranslateTransition(
                Duration.millis(450),
                panelMusica
            );

        volver.setToX(0);

        volver.play();
    }
    
    private static void cargarLetra() {

        panelLetra.getChildren().clear();

        labelsLetra = new ArrayList<>();

        Cancion actual =
            playlist.get(indicePlaylist);

        for (LineaLetra linea :
                actual.getLetraSincronizada()) {

            Label lbl =
                new Label(
                    linea.getTexto()
                );

            lbl.setWrapText(true);

            lbl.setMaxWidth(500);

            lbl.setTextFill(Color.GRAY);

            lbl.setFont(
                Font.font(
                    fuenteFWClabel.getFamily(),
                    16
                )
            );

            labelsLetra.add(lbl);

            panelLetra.getChildren().add(lbl);
        }
    }
    
    private static void actualizarLetra(
            double tiempoActual) {

        Cancion actual =
            playlist.get(indicePlaylist);

        ArrayList<LineaLetra> letra =
            actual.getLetraSincronizada();

        int indiceActual = -1;

        for (int i = 0; i < letra.size(); i++) {

            if (
                tiempoActual >=
                letra.get(i).getTiempo()
            ) {

                indiceActual = i;
            }
        }

        if (indiceActual < 0)
            return;

        for (int i = 0; i < labelsLetra.size(); i++) {

            Label lbl =
                labelsLetra.get(i);

            if (i == indiceActual) {

                lbl.setTextFill(
                    Color.WHITE
                );

                lbl.setFont(
                    Font.font(
                        fuenteFWClabel.getFamily(),
                        28
                    )
                );

            } else {

                lbl.setTextFill(
                    Color.GRAY
                );

                lbl.setFont(
                    Font.font(
                        fuenteFWClabel.getFamily(),
                        16
                    )
                );
            }
        }

        centrarLineaActual(
            indiceActual
        );
    }
    
    private static void centrarLineaActual(
            int indiceActual) {

        if (
            indiceActual < 0
            ||
            indiceActual >= labelsLetra.size()
        ) {
            return;
        }

        Label lblActual =
            labelsLetra.get(
                indiceActual
            );

        double centroPanel =
            panelDerecho.getHeight() / 2;

        double posicionLabel =
            lblActual.getBoundsInParent()
                     .getMinY();

        double altoLabel =
            lblActual.getHeight();

        double destino =

            centroPanel

            -

            posicionLabel

            -

            altoLabel / 2;

        if (
            animacionLetra != null
        ) {

            animacionLetra.stop();
        }

        animacionLetra =
            new TranslateTransition(
                Duration.millis(400),
                panelLetra
            );

        animacionLetra.setToY(
            destino
        );

        animacionLetra.play();
    }
}