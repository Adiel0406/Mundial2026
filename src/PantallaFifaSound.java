import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.animation.ScaleTransition;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.util.Duration;

public class PantallaFifaSound {

    // ============================================================
    // CONFIGURACIÓN
    // ============================================================

    private static final double ANCHO_LISTA = 650;

    private Font fuenteFWC;

    // ============================================================
    // REPRODUCTOR
    // ============================================================

    private MediaPlayer reproductor;

    private ArrayList<Cancion> playlist;

    private int indicePlaylist = 0;

    // ============================================================
    // ELEMENTOS DEL REPRODUCTOR
    // ============================================================

    private ImageView discoView;
    private ImageView portadaView;

    private RotateTransition rotacionDisco;

    private Label lblCancionActual;
    private Label lblTiempo;

    private Slider barraProgreso;

    // ============================================================
    // CONTROLES
    // ============================================================

    private ImageView btnAnterior;
    private ImageView btnPlay;
    private ImageView btnPause;
    private ImageView btnSiguiente;

    private Button btnLetra;

    // ============================================================
    // ESCENA
    // ============================================================

    private Scene escenaFifaSound;

    private Stage stageActual;

    // ============================================================
    // CACHÉ DE IMÁGENES
    // ============================================================

    private final Map<String, Image> cacheImagenes =
        new HashMap<>();

    // ============================================================
    // CONTROL DE BARRA
    // ============================================================

    private boolean usuarioMoviendoBarra = false;

    // ============================================================
    // CREAR ESCENA
    // ============================================================

    public Scene crearScene(Stage stage) {

        stageActual = stage;

        // --------------------------------------------------------
        // FUENTE
        // --------------------------------------------------------

        cargarFuente();

        // --------------------------------------------------------
        // RAÍZ
        // --------------------------------------------------------

        BorderPane raiz =
            new BorderPane();

        // --------------------------------------------------------
        // FONDO
        // --------------------------------------------------------

        Image imagenFondo =
            cargarImagen(
                "images/FWC/fondo.png",
                1920,
                1080,
                true
            );

        BackgroundImage fondo =
            new BackgroundImage(
                imagenFondo,
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

        raiz.setBackground(
            new Background(fondo)
        );

        // --------------------------------------------------------
        // BOTÓN ATRÁS
        // --------------------------------------------------------

        ImageView btnAtras =
            crearBotonAtras(stage);

        HBox barraSuperior =
            new HBox(
                btnAtras
            );

        barraSuperior.setAlignment(
            Pos.TOP_LEFT
        );

        barraSuperior.setPadding(
            new Insets(
                25,
                0,
                0,
                25
            )
        );

        raiz.setTop(
            barraSuperior
        );

        // --------------------------------------------------------
        // LISTA
        // --------------------------------------------------------

        VBox contenido =
            crearLista();

        // --------------------------------------------------------
        // REPRODUCTOR DERECHO
        // --------------------------------------------------------

        VBox reproductorDerecho =
            crearReproductorDerecho(stage);

        // --------------------------------------------------------
        // BORDERPANE
        // --------------------------------------------------------

        raiz.setLeft(
            contenido
        );

        raiz.setCenter(
            reproductorDerecho
        );

        // --------------------------------------------------------
        // CREAR UNA SOLA SCENE
        // --------------------------------------------------------

        escenaFifaSound =
            new Scene(
                raiz,
                1280,
                720
            );

        // --------------------------------------------------------
        // REPRODUCIR DESPUÉS DE CREAR LA INTERFAZ
        // --------------------------------------------------------

        Platform.runLater(() -> {

            if (playlist != null
                && !playlist.isEmpty()
                && reproductor == null) {

                reproducirCancion();
            }
        });

        return escenaFifaSound;
    }

    // ============================================================
    // OBTENER ESCENA ACTUAL
    // ============================================================

    public Scene getEscenaFifaSound() {

        return escenaFifaSound;
    }

    // ============================================================
    // BOTÓN ATRÁS
    // ============================================================

    private ImageView crearBotonAtras(
        Stage stage
    ) {

        ImageView btnAtras =
            new ImageView(
                cargarImagen(
                    "images/FWC/atras.png",
                    55,
                    55,
                    false
                )
            );

        btnAtras.setFitWidth(
            55
        );

        btnAtras.setPreserveRatio(
            true
        );

        btnAtras.setCursor(
            Cursor.HAND
        );

        // --------------------------------------------------------
        // HOVER ORIGINAL
        // --------------------------------------------------------

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

        // --------------------------------------------------------
        // VOLVER AL MENÚ
        // --------------------------------------------------------

        btnAtras.setOnMouseClicked(e -> {

            liberarReproductor();

            PantallaMenu menu =
                new PantallaMenu();

            Scene scene =
                menu.crearScene(stage);

            stage.setScene(
                scene
            );

            stage.setMaximized(
                true
            );
        });

        return btnAtras;
    }

    // ============================================================
    // CREAR LISTA COMPLETA
    // ============================================================

    private VBox crearLista() {

        VBox lista =
            new VBox(12);

        lista.setAlignment(
            Pos.TOP_LEFT
        );

        // --------------------------------------------------------
        // CARGAR CANCIONES
        // --------------------------------------------------------

        ManagerCancion manager =
            new ManagerCancion();

        ArrayList<Cancion> canciones =
            manager.cargarCanciones(
                Archivos.ARCHIVO_CANCIONES
            );

        // --------------------------------------------------------
        // PLAYLIST
        // --------------------------------------------------------

        playlist =
            new ArrayList<>(
                canciones
            );

        // --------------------------------------------------------
        // CREAR FILAS
        // --------------------------------------------------------

        for (Cancion cancion : canciones) {

            lista.getChildren().add(
                crearFilaCancion(
                    cancion
                )
            );
        }

        // --------------------------------------------------------
        // SCROLL
        // --------------------------------------------------------

        ScrollPane scroll =
            new ScrollPane(
                lista
            );

        scroll.setFitToWidth(
            true
        );

        scroll.setHbarPolicy(
            ScrollPane.ScrollBarPolicy.NEVER
        );

        scroll.setVbarPolicy(
            ScrollPane.ScrollBarPolicy.AS_NEEDED
        );

        scroll.setPrefWidth(
            ANCHO_LISTA
        );

        scroll.setStyle(
            "-fx-background-color: transparent;"
            + "-fx-background: transparent;"
            + "-fx-border-color: transparent;"
        );

        // --------------------------------------------------------
        // TÍTULO
        // --------------------------------------------------------

        Label titulo =
            new Label(
                "FIFA SOUND"
            );

        titulo.setTextFill(
            Color.WHITE
        );

        titulo.setFont(
            Font.font(
                fuenteFWC.getFamily(),
                FontWeight.BOLD,
                32
            )
        );

        // --------------------------------------------------------
        // CONTENEDOR
        // --------------------------------------------------------

        VBox contenido =
            new VBox(
                15,
                titulo,
                scroll
            );

        contenido.setAlignment(
            Pos.TOP_LEFT
        );

        contenido.setPadding(
            new Insets(
                25,
                0,
                20,
                35
            )
        );

        contenido.setPrefWidth(
            ANCHO_LISTA + 50
        );

        contenido.setMaxWidth(
            ANCHO_LISTA + 50
        );

        return contenido;
    }

    // ============================================================
    // CREAR FILA DE CANCIÓN
    // ============================================================

    private HBox crearFilaCancion(
        Cancion cancion
    ) {

        // --------------------------------------------------------
        // PORTADA
        // --------------------------------------------------------

        ImageView portada =
            new ImageView();

        portada.setFitWidth(
            75
        );

        portada.setFitHeight(
            75
        );

        portada.setPreserveRatio(
            false
        );

        // --------------------------------------------------------
        // MINIATURA EN SEGUNDO PLANO
        // --------------------------------------------------------

        cargarMiniatura(
            cancion.getRutaImagen(),
            portada
        );

        // --------------------------------------------------------
        // NOMBRE
        // --------------------------------------------------------

        Label nombre =
            new Label(
                cancion.getNombre()
            );

        nombre.setTextFill(
            Color.WHITE
        );

        nombre.setFont(
            Font.font(
                "Arial",
                FontWeight.BOLD,
                22
            )
        );

        nombre.setWrapText(
            true
        );

        // --------------------------------------------------------
        // ARTISTAS
        // --------------------------------------------------------

        Label artistas =
            new Label(
                obtenerNombresArtistas(
                    cancion
                )
            );

        artistas.setTextFill(
            Color.LIGHTGRAY
        );

        artistas.setFont(
            Font.font(
                "Arial",
                15
            )
        );

        artistas.setWrapText(
            true
        );

        // --------------------------------------------------------
        // INFORMACIÓN
        // --------------------------------------------------------

        VBox informacion =
            new VBox(
                5,
                nombre,
                artistas
            );

        informacion.setAlignment(
            Pos.CENTER_LEFT
        );

        informacion.setMaxWidth(
            500
        );

        // --------------------------------------------------------
        // FILA
        // --------------------------------------------------------

        HBox fila =
            new HBox(
                18,
                portada,
                informacion
            );

        fila.setAlignment(
            Pos.CENTER_LEFT
        );

        fila.setMinHeight(
            95
        );

        fila.setPrefWidth(
            ANCHO_LISTA - 20
        );

        fila.setMaxWidth(
            ANCHO_LISTA - 20
        );

        fila.setPadding(
            new Insets(
                10,
                15,
                10,
                10
            )
        );

        // --------------------------------------------------------
        // ESTILO
        // --------------------------------------------------------

        String estiloNormal =
            "-fx-background-color: rgba(0,0,0,0.35);"
            + "-fx-background-radius: 10;"
            + "-fx-cursor: hand;";

        String estiloHover =
            "-fx-background-color: rgba(255,255,255,0.18);"
            + "-fx-background-radius: 10;"
            + "-fx-cursor: hand;";

        fila.setStyle(
            estiloNormal
        );

        // --------------------------------------------------------
        // HOVER
        // --------------------------------------------------------

        fila.setOnMouseEntered(
            e -> fila.setStyle(
                estiloHover
            )
        );

        fila.setOnMouseExited(
            e -> fila.setStyle(
                estiloNormal
            )
        );

        // --------------------------------------------------------
        // SELECCIONAR CANCIÓN
        // --------------------------------------------------------

        fila.setOnMouseClicked(e -> {

            seleccionarCancion(
                cancion
            );

            e.consume();
        });

        return fila;
    }

    // ============================================================
    // SELECCIONAR CANCIÓN
    // ============================================================

    private void seleccionarCancion(
        Cancion cancion
    ) {

        if (playlist == null
            || playlist.isEmpty()) {

            return;
        }

        int posicion =
            playlist.indexOf(
                cancion
            );

        if (posicion < 0) {
            return;
        }

        indicePlaylist =
            posicion;

        reproducirCancion();
    }

    // ============================================================
    // CREAR REPRODUCTOR DERECHO
    // ============================================================

    private VBox crearReproductorDerecho(
        Stage stage
    ) {

        // --------------------------------------------------------
        // PORTADA
        // --------------------------------------------------------

        portadaView =
            new ImageView();

        portadaView.setFitWidth(
            300
        );

        portadaView.setFitHeight(
            300
        );

        portadaView.setPreserveRatio(
            false
        );

        // Mantiene el diseño original
        portadaView.setTranslateX(
            -100
        );

        // --------------------------------------------------------
        // DISCO
        // --------------------------------------------------------

        Image imagenDisco =
            cargarImagen(
                "images/imagenes_musica/disco.png",
                340,
                340,
                true
            );

        discoView =
            new ImageView(
                imagenDisco
            );

        discoView.setFitWidth(
            340
        );

        discoView.setPreserveRatio(
            true
        );

        discoView.setOpacity(
            0.95
        );

        // --------------------------------------------------------
        // ROTACIÓN
        // --------------------------------------------------------

        rotacionDisco =
            new RotateTransition(
                Duration.seconds(8),
                discoView
            );

        rotacionDisco.setFromAngle(
            0
        );

        rotacionDisco.setToAngle(
            360
        );

        rotacionDisco.setCycleCount(
            RotateTransition.INDEFINITE
        );

        rotacionDisco.setInterpolator(
            Interpolator.LINEAR
        );

        // --------------------------------------------------------
        // DISCO + PORTADA
        // --------------------------------------------------------

        StackPane visualMusica =
            new StackPane(
                discoView,
                portadaView
            );

        visualMusica.setPrefSize(
            500,
            340
        );

        // --------------------------------------------------------
        // NOMBRE CANCIÓN
        // --------------------------------------------------------

        lblCancionActual =
            new Label(
                "NO SONG"
            );

        lblCancionActual.setTextFill(
            Color.WHITE
        );

        lblCancionActual.setFont(
            Font.font(
                fuenteFWC.getFamily(),
                FontWeight.BOLD,
                30
            )
        );

        lblCancionActual.setWrapText(
            true
        );

        lblCancionActual.setMaxWidth(
            500
        );

        lblCancionActual.setAlignment(
            Pos.CENTER
        );

        // --------------------------------------------------------
        // BARRA DE PROGRESO
        // --------------------------------------------------------

        barraProgreso =
            new Slider(
                0,
                100,
                0
            );

        barraProgreso.setPrefWidth(
            400
        );

        barraProgreso.setMinWidth(
            400
        );

        barraProgreso.setMaxWidth(
            400
        );

        // --------------------------------------------------------
        // TIEMPO
        // --------------------------------------------------------

        lblTiempo =
            new Label(
                "0:00 / 0:00"
            );

        lblTiempo.setTextFill(
            Color.WHITE
        );

        lblTiempo.setFont(
            Font.font(
                "Arial",
                15
            )
        );

        // --------------------------------------------------------
        // CONTROLES
        // --------------------------------------------------------

        btnAnterior =
            crearControl(
                "images/FWC/anterior.png"
            );

        btnPlay =
            crearControl(
                "images/FWC/play.png"
            );

        btnPause =
            crearControl(
                "images/FWC/pausa.png"
            );

        btnSiguiente =
            crearControl(
                "images/FWC/siguiente.png"
            );

        btnPlay.setVisible(
            true
        );

        btnPause.setVisible(
            false
        );

        // --------------------------------------------------------
        // PLAY / PAUSA
        // --------------------------------------------------------

        StackPane playPause =
            new StackPane(
                btnPlay,
                btnPause
            );

        // --------------------------------------------------------
        // BOTÓN LETRA
        // --------------------------------------------------------

        btnLetra =
            new Button(
                "LETRA"
            );

        btnLetra.setFont(
            Font.font(
                "Arial",
                FontWeight.BOLD,
                14
            )
        );

        btnLetra.setTextFill(
            Color.WHITE
        );

        btnLetra.setCursor(
            Cursor.HAND
        );

        btnLetra.setStyle(
            "-fx-background-color: transparent;"
            + "-fx-border-color: white;"
            + "-fx-border-width: 2;"
            + "-fx-background-radius: 25;"
            + "-fx-border-radius: 25;"
            + "-fx-padding: 8 20 8 20;"
        );

        hoverBoton(
            btnLetra
        );

        // --------------------------------------------------------
        // ABRIR LETRA
        // --------------------------------------------------------

        btnLetra.setOnAction(e -> {

            if (reproductor == null) {
                return;
            }

            if (playlist == null
                || playlist.isEmpty()) {

                return;
            }

            Cancion actual =
                playlist.get(
                    indicePlaylist
                );

            PantallaLetra pantallaLetra =
                new PantallaLetra(
                    actual,
                    reproductor,
                    this,
                    stage
                );

            Scene scene =
                pantallaLetra.crearScene(
                    stage
                );

            stage.setScene(
                scene
            );

            stage.setMaximized(
                true
            );
        });

        // --------------------------------------------------------
        // CONTROLES
        // --------------------------------------------------------

        HBox controles =
            new HBox(
                30,
                btnAnterior,
                playPause,
                btnSiguiente,
                btnLetra
            );

        controles.setAlignment(
            Pos.CENTER
        );

        // --------------------------------------------------------
        // PANEL DERECHO
        // --------------------------------------------------------

        VBox panel =
            new VBox(
                18,
                visualMusica,
                lblCancionActual,
                barraProgreso,
                lblTiempo,
                controles
            );

        panel.setAlignment(
            Pos.CENTER
        );

        panel.setPadding(
            new Insets(
                20,
                30,
                30,
                20
            )
        );

        panel.setPrefWidth(
            650
        );

        HBox.setHgrow(
            panel,
            Priority.ALWAYS
        );

        // --------------------------------------------------------
        // PLAY
        // --------------------------------------------------------

        btnPlay.setOnMouseClicked(e -> {

            if (reproductor == null) {
                return;
            }

            reproductor.play();

            btnPlay.setVisible(
                false
            );

            btnPause.setVisible(
                true
            );

            if (rotacionDisco != null) {
                rotacionDisco.play();
            }
        });

        // --------------------------------------------------------
        // PAUSA
        // --------------------------------------------------------

        btnPause.setOnMouseClicked(e -> {

            if (reproductor == null) {
                return;
            }

            reproductor.pause();

            btnPause.setVisible(
                false
            );

            btnPlay.setVisible(
                true
            );

            if (rotacionDisco != null) {
                rotacionDisco.pause();
            }
        });

        // --------------------------------------------------------
        // SIGUIENTE
        // --------------------------------------------------------

        btnSiguiente.setOnMouseClicked(e -> {

            if (playlist == null
                || playlist.isEmpty()) {

                return;
            }

            indicePlaylist++;

            if (indicePlaylist >=
                playlist.size()) {

                indicePlaylist = 0;
            }

            reproducirCancion();
        });

        // --------------------------------------------------------
        // ANTERIOR / REINICIAR
        // --------------------------------------------------------

        btnAnterior.setOnMouseClicked(e -> {

            if (reproductor == null) {
                return;
            }

            double tiempo =
                reproductor
                    .getCurrentTime()
                    .toSeconds();

            // Más de 3 segundos:
            // reiniciar canción

            if (tiempo > 3) {

                reproductor.seek(
                    Duration.ZERO
                );

                return;
            }

            // Menos de 3 segundos:
            // canción anterior

            if (playlist == null
                || playlist.isEmpty()) {

                return;
            }

            indicePlaylist--;

            if (indicePlaylist < 0) {

                indicePlaylist =
                    playlist.size() - 1;
            }

            reproducirCancion();
        });

        // --------------------------------------------------------
        // BARRA - PRESIONAR
        // --------------------------------------------------------

        barraProgreso.setOnMousePressed(
            e -> {

                usuarioMoviendoBarra =
                    true;
            }
        );

        // --------------------------------------------------------
        // BARRA - SOLTAR
        // --------------------------------------------------------

        barraProgreso.setOnMouseReleased(
            e -> {

                if (reproductor == null) {

                    usuarioMoviendoBarra =
                        false;

                    return;
                }

                reproductor.seek(
                    Duration.seconds(
                        barraProgreso.getValue()
                    )
                );

                usuarioMoviendoBarra =
                    false;
            }
        );

        return panel;
    }

    // ============================================================
    // CREAR CONTROL DE IMAGEN
    // ============================================================

    private ImageView crearControl(
        String ruta
    ) {

        ImageView imagen =
            new ImageView(
                cargarImagen(
                    ruta,
                    42,
                    42,
                    false
                )
            );

        imagen.setFitWidth(
            42
        );

        imagen.setPreserveRatio(
            true
        );

        imagen.setCursor(
            Cursor.HAND
        );

        efectoHover(
            imagen
        );

        return imagen;
    }

    // ============================================================
    // REPRODUCIR CANCIÓN
    // ============================================================

    private void reproducirCancion() {

        if (playlist == null
            || playlist.isEmpty()) {

            return;
        }

        // --------------------------------------------------------
        // ASEGURAR ÍNDICE
        // --------------------------------------------------------

        if (indicePlaylist >=
            playlist.size()) {

            indicePlaylist = 0;
        }

        if (indicePlaylist < 0) {

            indicePlaylist =
                playlist.size() - 1;
        }

        // --------------------------------------------------------
        // CANCIÓN
        // --------------------------------------------------------

        Cancion actual =
            playlist.get(
                indicePlaylist
            );

        // --------------------------------------------------------
        // DETENER REPRODUCTOR ANTERIOR
        // --------------------------------------------------------

        liberarReproductor();

        // --------------------------------------------------------
        // REINICIAR DISCO
        // --------------------------------------------------------

        if (rotacionDisco != null) {

            rotacionDisco.stop();
        }

        if (discoView != null) {

            discoView.setRotate(
                0
            );
        }

        // --------------------------------------------------------
        // PORTADA
        // --------------------------------------------------------

        try {

            portadaView.setImage(
                cargarImagen(
                    actual.getRutaImagen(),
                    300,
                    300,
                    true
                )
            );

        } catch (Exception e) {

            portadaView.setImage(
                null
            );
        }

        // --------------------------------------------------------
        // NOMBRE
        // --------------------------------------------------------

        lblCancionActual.setText(
            actual
                .getNombre()
                .toUpperCase()
        );

        // --------------------------------------------------------
        // RESETEAR PROGRESO
        // --------------------------------------------------------

        barraProgreso.setValue(
            0
        );

        barraProgreso.setMax(
            100
        );

        lblTiempo.setText(
            "0:00 / 0:00"
        );

        // --------------------------------------------------------
        // ESTADO PLAY / PAUSA
        // --------------------------------------------------------

        btnPlay.setVisible(
            false
        );

        btnPause.setVisible(
            true
        );

        // --------------------------------------------------------
        // AUDIO
        // --------------------------------------------------------

        File archivoAudio =
            resolverArchivo(
                actual.getRutaAudio()
            );

        if (archivoAudio == null
            || !archivoAudio.exists()) {

            System.out.println(
                "Audio no encontrado: "
                + actual.getRutaAudio()
            );

            btnPlay.setVisible(
                true
            );

            btnPause.setVisible(
                false
            );

            return;
        }

        try {

            Media media =
                new Media(
                    archivoAudio
                        .toURI()
                        .toString()
                );

            MediaPlayer player =
                new MediaPlayer(
                    media
                );

            reproductor =
                player;

            player.setVolume(
                0.6
            );

            // ----------------------------------------------------
            // MEDIA LISTA
            // ----------------------------------------------------

            player.setOnReady(() -> {

                if (reproductor != player) {
                    return;
                }

                double duracion =
                    player
                        .getTotalDuration()
                        .toSeconds();

                if (!Double.isFinite(duracion)
                    || duracion <= 0) {

                    duracion = 1;
                }

                barraProgreso.setMax(
                    duracion
                );

                lblTiempo.setText(
                    "0:00 / "
                    + formatoTiempo(
                        duracion
                    )
                );

                player.play();

                if (rotacionDisco != null) {

                    rotacionDisco.play();
                }

                btnPlay.setVisible(
                    false
                );

                btnPause.setVisible(
                    true
                );
            });

            // ----------------------------------------------------
            // PROGRESO
            // ----------------------------------------------------

            player.currentTimeProperty()
                .addListener(
                    (obs, anterior, tiempo) -> {

                        if (reproductor != player) {
                            return;
                        }

                        if (usuarioMoviendoBarra) {
                            return;
                        }

                        double actualSegundos =
                            tiempo.toSeconds();

                        double total =
                            player
                                .getTotalDuration()
                                .toSeconds();

                        if (!Double.isFinite(
                                actualSegundos)) {

                            actualSegundos = 0;
                        }

                        if (!Double.isFinite(total)
                            || total < 0) {

                            total = 0;
                        }

                        barraProgreso.setValue(
                            Math.min(
                                actualSegundos,
                                barraProgreso.getMax()
                            )
                        );

                        lblTiempo.setText(
                            formatoTiempo(
                                actualSegundos
                            )
                            + " / "
                            + formatoTiempo(
                                total
                            )
                        );
                    }
                );

            // ----------------------------------------------------
            // ERROR
            // ----------------------------------------------------

            player.setOnError(() -> {

                if (reproductor != player) {
                    return;
                }

                System.out.println(
                    "Error de reproducción: "
                    + player.getError()
                );

                btnPlay.setVisible(
                    true
                );

                btnPause.setVisible(
                    false
                );
            });

            // ----------------------------------------------------
            // TERMINÓ
            // ----------------------------------------------------

            player.setOnEndOfMedia(() -> {

                if (reproductor != player) {
                    return;
                }

                indicePlaylist++;

                if (indicePlaylist >=
                    playlist.size()) {

                    indicePlaylist = 0;
                }

                reproducirCancion();
            });

        } catch (Exception e) {

            e.printStackTrace();

            btnPlay.setVisible(
                true
            );

            btnPause.setVisible(
                false
            );
        }
    }

    // ============================================================
    // LIBERAR REPRODUCTOR
    // ============================================================

    private void liberarReproductor() {

        if (reproductor != null) {

            try {

                reproductor.stop();

            } catch (Exception ignored) {
            }

            try {

                reproductor.dispose();

            } catch (Exception ignored) {
            }

            reproductor = null;
        }
    }

    // ============================================================
    // HOVER IMAGEVIEW
    // ============================================================

    private void efectoHover(
        ImageView imagen
    ) {

        ScaleTransition agrandar =
            new ScaleTransition(
                Duration.millis(120),
                imagen
            );

        agrandar.setToX(
            1.12
        );

        agrandar.setToY(
            1.12
        );

        ScaleTransition volver =
            new ScaleTransition(
                Duration.millis(120),
                imagen
            );

        volver.setToX(
            1
        );

        volver.setToY(
            1
        );

        imagen.setOnMouseEntered(
            e -> agrandar.playFromStart()
        );

        imagen.setOnMouseExited(
            e -> volver.playFromStart()
        );
    }

    // ============================================================
    // HOVER BOTÓN LETRA
    // ============================================================

    private void hoverBoton(
        Button boton
    ) {

        ScaleTransition agrandar =
            new ScaleTransition(
                Duration.millis(120),
                boton
            );

        agrandar.setToX(
            1.08
        );

        agrandar.setToY(
            1.08
        );

        ScaleTransition volver =
            new ScaleTransition(
                Duration.millis(120),
                boton
            );

        volver.setToX(
            1
        );

        volver.setToY(
            1
        );

        boton.setOnMouseEntered(
            e -> agrandar.playFromStart()
        );

        boton.setOnMouseExited(
            e -> volver.playFromStart()
        );
    }

    // ============================================================
    // OBTENER ARTISTAS
    // ============================================================

    private String obtenerNombresArtistas(
        Cancion cancion
    ) {

        StringBuilder nombres =
            new StringBuilder();

        ArrayList<Cantante> cantantes =
            cancion.getCantantes();

        if (cantantes == null
            || cantantes.isEmpty()) {

            return "";
        }

        for (
            int i = 0;
            i < cantantes.size();
            i++
        ) {

            nombres.append(
                cantantes
                    .get(i)
                    .getNombre()
            );

            if (
                i <
                cantantes.size() - 1
            ) {

                nombres.append(
                    ", "
                );
            }
        }

        return nombres.toString();
    }

    // ============================================================
    // FORMATO TIEMPO
    // ============================================================

    private String formatoTiempo(
        double segundos
    ) {

        if (!Double.isFinite(segundos)
            || segundos < 0) {

            segundos = 0;
        }

        int minutos =
            (int) segundos / 60;

        int segundosRestantes =
            (int) segundos % 60;

        return String.format(
            "%d:%02d",
            minutos,
            segundosRestantes
        );
    }

    // ============================================================
    // CARGAR FUENTE
    // ============================================================

    private void cargarFuente() {

        try {

            fuenteFWC =
                Font.loadFont(
                    new File(
                        "src/fonts/FWC2026.ttf"
                    )
                    .toURI()
                    .toString(),
                    20
                );

        } catch (Exception e) {

            fuenteFWC = null;
        }

        if (fuenteFWC == null) {

            fuenteFWC =
                Font.font(
                    "Arial",
                    FontWeight.BOLD,
                    20
                );
        }
    }

    // ============================================================
    // CARGAR MINIATURA
    // ============================================================

    private void cargarMiniatura(
        String ruta,
        ImageView vista
    ) {

        if (ruta == null
            || ruta.isBlank()) {

            return;
        }

        File archivo =
            resolverArchivo(
                ruta
            );

        if (archivo == null
            || !archivo.exists()) {

            return;
        }

        String clave =
            archivo
                .getAbsolutePath()
            + "|75|75";

        Image imagen =
            cacheImagenes.get(
                clave
            );

        if (imagen != null) {

            vista.setImage(
                imagen
            );

            return;
        }

        Image miniatura =
            new Image(
                archivo
                    .toURI()
                    .toString(),
                75,
                75,
                false,
                true,
                true
            );

        cacheImagenes.put(
            clave,
            miniatura
        );

        vista.setImage(
            miniatura
        );
    }

    // ============================================================
    // CARGAR IMAGEN OPTIMIZADA
    // ============================================================

    private Image cargarImagen(
        String ruta,
        double ancho,
        double alto,
        boolean backgroundLoading
    ) {

        if (ruta == null
            || ruta.isBlank()) {

            throw new RuntimeException(
                "Ruta de imagen vacía"
            );
        }

        // --------------------------------------------------------
        // URL
        // --------------------------------------------------------

        if (
            ruta.startsWith("file:")
            || ruta.startsWith("http:")
            || ruta.startsWith("https:")
        ) {

            String clave =
                ruta
                + "|"
                + ancho
                + "|"
                + alto;

            Image imagen =
                cacheImagenes.get(
                    clave
                );

            if (imagen != null) {
                return imagen;
            }

            imagen =
                new Image(
                    ruta,
                    ancho,
                    alto,
                    false,
                    true,
                    backgroundLoading
                );

            cacheImagenes.put(
                clave,
                imagen
            );

            return imagen;
        }

        // --------------------------------------------------------
        // ARCHIVO
        // --------------------------------------------------------

        File archivo =
            resolverArchivo(
                ruta
            );

        if (archivo == null
            || !archivo.exists()) {

            throw new RuntimeException(
                "Imagen no encontrada: "
                + ruta
            );
        }

        String clave =
            archivo
                .getAbsolutePath()
            + "|"
            + ancho
            + "|"
            + alto
            + "|"
            + backgroundLoading;

        Image imagen =
            cacheImagenes.get(
                clave
            );

        if (imagen != null) {
            return imagen;
        }

        imagen =
            new Image(
                archivo
                    .toURI()
                    .toString(),
                ancho,
                alto,
                false,
                true,
                backgroundLoading
            );

        cacheImagenes.put(
            clave,
            imagen
        );

        return imagen;
    }

    // ============================================================
    // RESOLVER RUTA
    // ============================================================

    private File resolverArchivo(
        String ruta
    ) {

        if (ruta == null
            || ruta.isBlank()) {

            return null;
        }

        // --------------------------------------------------------
        // RUTA DIRECTA
        // --------------------------------------------------------

        File archivo =
            new File(
                ruta
            );

        if (archivo.exists()) {
            return archivo;
        }

        // --------------------------------------------------------
        // DENTRO DE SRC
        // --------------------------------------------------------

        archivo =
            new File(
                "src/" + ruta
            );

        if (archivo.exists()) {
            return archivo;
        }

        // --------------------------------------------------------
        // SI YA EMPIEZA CON SRC
        // --------------------------------------------------------

        if (ruta.startsWith("src/")) {

            archivo =
                new File(
                    ruta
                );

            if (archivo.exists()) {
                return archivo;
            }
        }

        return null;
    }
}