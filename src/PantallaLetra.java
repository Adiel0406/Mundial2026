import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.ParallelTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.Timeline;

import javafx.application.Platform;

import javafx.geometry.Insets;
import javafx.geometry.Pos;

import javafx.scene.Cursor;
import javafx.scene.Scene;

import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;

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

import javafx.scene.media.MediaPlayer;

import javafx.scene.paint.Color;

import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import javafx.stage.Stage;

import javafx.util.Duration;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PantallaLetra {

    // =============================================================
    // CACHE DE IMAGENES
    // =============================================================

    private static final Map<String, Image> CACHE_IMAGENES =
        new HashMap<>();

    // =============================================================
    // VARIABLES
    // =============================================================

    private Font fuenteFWC;

    private final Cancion cancion;

    private final MediaPlayer reproductor;

    private final PantallaFifaSound pantallaFifaSound;

    private final Stage stage;

    private VBox contenedorLetra;

    private ScrollPane scrollLetra;

    private ImageView btnPlay;

    private ImageView btnPause;

    private Label lblTitulo;

    private Label lblArtistas;

    private int indiceLineaActual = -1;

    // Animación del scroll
    private Timeline animacionScroll;

    // Listener de sincronización
    private javafx.beans.value.ChangeListener<Duration>
        listenerTiempo;

    // =============================================================
    // CONSTRUCTOR
    // =============================================================

    public PantallaLetra(
        Cancion cancion,
        MediaPlayer reproductor,
        PantallaFifaSound pantallaFifaSound,
        Stage stage
    ) {

        this.cancion = cancion;

        this.reproductor = reproductor;

        this.pantallaFifaSound = pantallaFifaSound;

        this.stage = stage;
    }

    // =============================================================
    // CREAR SCENE
    // =============================================================

    public Scene crearScene(Stage stage) {

        cargarFuente();

        BorderPane raiz =
            new BorderPane();

        // =========================================================
        // FONDO
        // =========================================================

        Image fondo =
            cargarImagen(
                "images/FWC/fondo.png",
                1920,
                1080,
                false
            );

        BackgroundImage backgroundImage =
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

        raiz.setBackground(
            new Background(backgroundImage)
        );

        // =========================================================
        // BOTON ATRAS
        // =========================================================

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

            limpiarSincronizacion();

            if (animacionScroll != null) {
                animacionScroll.stop();
            }

            stage.setScene(
                pantallaFifaSound.getEscenaFifaSound()
            );

            stage.setMaximized(true);
        });

        HBox topBar =
            new HBox(btnAtras);

        topBar.setPadding(
            new Insets(25)
        );

        raiz.setTop(topBar);

        // =========================================================
        // PANEL IZQUIERDO
        // =========================================================

        VBox izquierda =
            crearPanelIzquierdo();

        raiz.setLeft(izquierda);

        // =========================================================
        // PANEL DERECHO - LETRA
        // =========================================================

        VBox derecha =
            crearPanelLetra();

        raiz.setCenter(derecha);

        // =========================================================
        // SINCRONIZACION
        // =========================================================

        configurarSincronizacion();

        Scene scene =
            new Scene(raiz);

        Platform.runLater(() -> {

            actualizarBotonReproduccion();

            actualizarLineaActual();

        });

        return scene;
    }

    // =============================================================
    // PANEL IZQUIERDO
    // =============================================================

    private VBox crearPanelIzquierdo() {

        VBox panel =
            new VBox(20);

        panel.setAlignment(
            Pos.TOP_CENTER
        );

        panel.setPrefWidth(480);

        panel.setPadding(
            new Insets(
                70,
                30,
                30,
                50
            )
        );

        // =========================================================
        // PORTADA
        // =========================================================

        Image portada =
            cargarImagen(
                cancion.getRutaImagen(),
                330,
                330,
                true
            );

        ImageView portadaView =
            new ImageView(portada);

        portadaView.setFitWidth(330);

        portadaView.setFitHeight(330);

        portadaView.setPreserveRatio(false);

        // =========================================================
        // TITULO
        // =========================================================

        lblTitulo =
            new Label(
                cancion.getNombre()
            );

        lblTitulo.setFont(
            Font.font(
                fuenteFWC.getFamily(),
                FontWeight.BOLD,
                27
            )
        );

        lblTitulo.setTextFill(
            Color.WHITE
        );

        lblTitulo.setWrapText(true);

        lblTitulo.setMaxWidth(380);

        lblTitulo.setAlignment(
            Pos.CENTER
        );

        // =========================================================
        // ARTISTAS
        // =========================================================

        lblArtistas =
            new Label(
                obtenerArtistas()
            );

        lblArtistas.setFont(
            Font.font(
                "Arial",
                FontWeight.NORMAL,
                16
            )
        );

        lblArtistas.setTextFill(
            Color.rgb(
                220,
                220,
                220
            )
        );

        lblArtistas.setWrapText(true);

        lblArtistas.setMaxWidth(380);

        lblArtistas.setAlignment(
            Pos.CENTER
        );

        // =========================================================
        // BOTON PLAY
        // =========================================================

        btnPlay =
            crearBotonImagen(
                "images/FWC/play.png",
                50
            );

        // =========================================================
        // BOTON PAUSA
        // =========================================================

        btnPause =
            crearBotonImagen(
                "images/FWC/pausa.png",
                50
            );

        // PLAY

        btnPlay.setOnMouseClicked(e -> {

            if (reproductor == null) {
                return;
            }

            reproductor.play();

            actualizarBotonReproduccion();
        });

        // PAUSA

        btnPause.setOnMouseClicked(e -> {

            if (reproductor == null) {
                return;
            }

            reproductor.pause();

            actualizarBotonReproduccion();
        });

        // =========================================================
        // CONTROLES
        // =========================================================

        StackPane controles =
            new StackPane();

        controles.getChildren().addAll(
            btnPlay,
            btnPause
        );

        VBox.setMargin(
            controles,
            new Insets(
                5,
                0,
                0,
                0
            )
        );

        // =========================================================
        // AGREGAR
        // =========================================================

        panel.getChildren().addAll(
            portadaView,
            lblTitulo,
            lblArtistas,
            controles
        );

        return panel;
    }

    // =============================================================
    // PANEL DE LETRA
    // =============================================================

    private VBox crearPanelLetra() {

        VBox panel =
            new VBox();

        panel.setAlignment(
            Pos.TOP_CENTER
        );

        panel.setPadding(
            new Insets(
                80,
                70,
                40,
                20
            )
        );

        panel.setPrefWidth(750);

        // =========================================================
        // TITULO LETRA
        // =========================================================

        Label tituloLetra =
            new Label("LETRA");

        tituloLetra.setFont(
            Font.font(
                fuenteFWC.getFamily(),
                FontWeight.BOLD,
                32
            )
        );

        tituloLetra.setTextFill(
            Color.WHITE
        );

        tituloLetra.setAlignment(
            Pos.CENTER
        );

        // =========================================================
        // CONTENEDOR DE LETRAS
        // =========================================================

        contenedorLetra =
            new VBox(18);

        contenedorLetra.setAlignment(
            Pos.TOP_CENTER
        );

        contenedorLetra.setFillWidth(
            true
        );

        /*
         * El padding inferior de 100 permite que la última
         * línea pueda subir completamente dentro del ScrollPane.
         */

        contenedorLetra.setPadding(
            new Insets(
                20,
                35,
                100,
                35
            )
        );

        // =========================================================
        // CARGAR LETRA
        // =========================================================

        ArrayList<LineaLetra> lineas =
            cancion.getLetraSincronizada();

        if (lineas == null ||
            lineas.isEmpty()) {

            Label sinLetra =
                new Label(
                    "No hay letra sincronizada."
                );

            sinLetra.setFont(
                Font.font(
                    "Arial",
                    FontWeight.NORMAL,
                    22
                )
            );

            sinLetra.setTextFill(
                Color.rgb(
                    180,
                    180,
                    180
                )
            );

            sinLetra.setAlignment(
                Pos.CENTER
            );

            contenedorLetra
                .getChildren()
                .add(sinLetra);

        } else {

            for (
                int i = 0;
                i < lineas.size();
                i++
            ) {

                LineaLetra linea =
                    lineas.get(i);

                Label label =
                    crearLinea(
                        linea.getTexto()
                    );

                label.setUserData(i);

                contenedorLetra
                    .getChildren()
                    .add(label);
            }
        }

        // =========================================================
        // SCROLLPANE
        // =========================================================

        scrollLetra =
            new ScrollPane(
                contenedorLetra
            );

        /*
         * MUY IMPORTANTE:
         *
         * FitToWidth = true
         * FitToHeight = false
         *
         * Así el contenido puede ser mucho más alto
         * que la ventana y podemos desplazarnos hasta
         * el final de la canción.
         */

        scrollLetra.setFitToWidth(
            true
        );

        scrollLetra.setFitToHeight(
            false
        );

        scrollLetra.setHbarPolicy(
            ScrollPane.ScrollBarPolicy.NEVER
        );

        scrollLetra.setVbarPolicy(
            ScrollPane.ScrollBarPolicy.AS_NEEDED
        );

        scrollLetra.setPannable(
            true
        );

        scrollLetra.setStyle(
            "-fx-background-color: transparent;" +
            "-fx-background: transparent;" +
            "-fx-border-color: transparent;"
        );

        VBox.setVgrow(
            scrollLetra,
            Priority.ALWAYS
        );

        // =========================================================
        // AGREGAR AL PANEL
        // =========================================================

        panel.getChildren().addAll(
            tituloLetra,
            scrollLetra
        );

        return panel;
    }

    // =============================================================
    // CREAR LINEA
    // =============================================================

    private Label crearLinea(
        String texto
    ) {

        Label label =
            new Label(texto);

        label.setFont(
            Font.font(
                "Arial",
                FontWeight.NORMAL,
                22
            )
        );

        label.setTextFill(
            Color.rgb(
                210,
                210,
                210
            )
        );

        /*
         * Permite que las líneas muy largas
         * se dividan en varias líneas.
         */

        label.setWrapText(
            true
        );

        label.setMaxWidth(
            Double.MAX_VALUE
        );

        label.setAlignment(
            Pos.CENTER
        );

        label.setPadding(
            new Insets(4)
        );

        return label;
    }

    // =============================================================
    // SINCRONIZACION
    // =============================================================

    private void configurarSincronizacion() {

        if (reproductor == null) {
            return;
        }

        listenerTiempo =
            (observable, anterior, actual) -> {

                actualizarLineaActual();

            };

        reproductor
            .currentTimeProperty()
            .addListener(
                listenerTiempo
            );
    }

    // =============================================================
    // LIMPIAR LISTENER
    // =============================================================

    private void limpiarSincronizacion() {

        if (
            reproductor != null &&
            listenerTiempo != null
        ) {

            reproductor
                .currentTimeProperty()
                .removeListener(
                    listenerTiempo
                );
        }

        listenerTiempo = null;
    }

    // =============================================================
    // BUSCAR LINEA ACTUAL
    // =============================================================

    private void actualizarLineaActual() {

        if (reproductor == null) {
            return;
        }

        if (contenedorLetra == null) {
            return;
        }

        ArrayList<LineaLetra> lineas =
            cancion.getLetraSincronizada();

        if (
            lineas == null ||
            lineas.isEmpty()
        ) {
            return;
        }

        double tiempoActual =
            reproductor
                .getCurrentTime()
                .toSeconds();

        int nuevaLinea = -1;

        for (
            int i = 0;
            i < lineas.size();
            i++
        ) {

            double tiempoLinea =
                lineas
                    .get(i)
                    .getTiempo();

            if (
                tiempoActual >=
                tiempoLinea
            ) {

                nuevaLinea = i;

            } else {

                break;
            }
        }

        // =========================================================
        // SI NO CAMBIO LA LINEA, NO HACER NADA
        // =========================================================

        if (
            nuevaLinea ==
            indiceLineaActual
        ) {
            return;
        }

        indiceLineaActual =
            nuevaLinea;

        actualizarEstiloLineas();

        desplazarAlineaActual();
    }

    // =============================================================
    // ESTILO DE LAS LINEAS
    // =============================================================

    private void actualizarEstiloLineas() {

        if (contenedorLetra == null) {
            return;
        }

        for (
            int i = 0;
            i < contenedorLetra
                .getChildren()
                .size();
            i++
        ) {

            Label label =
                (Label) contenedorLetra
                    .getChildren()
                    .get(i);

            // =====================================================
            // LINEA ACTUAL
            // =====================================================

            if (
                i ==
                indiceLineaActual
            ) {

                label.setOpacity(
                    0.65
                );

                label.setScaleX(
                    0.96
                );

                label.setScaleY(
                    0.96
                );

                label.setFont(
                    Font.font(
                        fuenteFWC.getFamily(),
                        FontWeight.BOLD,
                        28
                    )
                );

                label.setTextFill(
                    Color.WHITE
                );

                // -------------------------------------------------
                // FADE
                // -------------------------------------------------

                FadeTransition fade =
                    new FadeTransition(
                        Duration.millis(350),
                        label
                    );

                fade.setFromValue(
                    0.65
                );

                fade.setToValue(
                    1.0
                );

                // -------------------------------------------------
                // ESCALA
                // -------------------------------------------------

                ScaleTransition scale =
                    new ScaleTransition(
                        Duration.millis(350),
                        label
                    );

                scale.setFromX(
                    0.96
                );

                scale.setFromY(
                    0.96
                );

                scale.setToX(
                    1.0
                );

                scale.setToY(
                    1.0
                );

                // -------------------------------------------------
                // ANIMACION
                // -------------------------------------------------

                ParallelTransition animacion =
                    new ParallelTransition(
                        fade,
                        scale
                    );

                animacion.play();

            }

            // =====================================================
            // DEMAS LINEAS
            // =====================================================

            else {

                label.setFont(
                    Font.font(
                        "Arial",
                        FontWeight.NORMAL,
                        22
                    )
                );

                label.setTextFill(
                    Color.rgb(
                        170,
                        170,
                        170
                    )
                );

                label.setOpacity(
                    1
                );

                label.setScaleX(
                    1
                );

                label.setScaleY(
                    1
                );
            }
        }
    }

    // =============================================================
    // SCROLL AUTOMATICO SUAVE
    // =============================================================

    private void desplazarAlineaActual() {

        if (
            indiceLineaActual < 0
        ) {
            return;
        }

        if (
            contenedorLetra == null ||
            scrollLetra == null
        ) {
            return;
        }

        if (
            indiceLineaActual >=
            contenedorLetra
                .getChildren()
                .size()
        ) {
            return;
        }

        Platform.runLater(() -> {

            javafx.scene.Node nodo =
                contenedorLetra
                    .getChildren()
                    .get(indiceLineaActual);

            /*
             * Esperamos a que JavaFX termine de calcular
             * los tamaños antes de mover el ScrollPane.
             */

            double posicion =
                nodo
                    .getBoundsInParent()
                    .getMinY();

            double alturaContenido =
                contenedorLetra
                    .getBoundsInLocal()
                    .getHeight();

            double alturaVentana =
                scrollLetra
                    .getViewportBounds()
                    .getHeight();

            // =====================================================
            // SI TODO CABE, NO NECESITAMOS SCROLL
            // =====================================================

            if (
                alturaContenido <=
                alturaVentana
            ) {

                return;
            }

            // =====================================================
            // CALCULAR POSICION
            // =====================================================

            double valor =
                posicion /
                (
                    alturaContenido -
                    alturaVentana
                );

            valor =
                Math.max(
                    0,
                    Math.min(
                        1,
                        valor
                    )
                );

            // =====================================================
            // DETENER ANIMACION ANTERIOR
            // =====================================================

            if (
                animacionScroll != null
            ) {

                animacionScroll.stop();
            }

            // =====================================================
            // ANIMACION SUAVE
            // =====================================================

            animacionScroll =
                new Timeline(
                    new KeyFrame(
                        Duration.millis(350),
                        new KeyValue(
                            scrollLetra
                                .vvalueProperty(),
                            valor,
                            Interpolator.EASE_BOTH
                        )
                    )
                );

            animacionScroll.play();
        });
    }

    // =============================================================
    // PLAY / PAUSA
    // =============================================================

    private void actualizarBotonReproduccion() {

        if (
            reproductor == null ||
            btnPlay == null ||
            btnPause == null
        ) {
            return;
        }

        boolean reproduciendo =
            reproductor.getStatus() ==
            MediaPlayer.Status.PLAYING;

        btnPlay.setVisible(
            !reproduciendo
        );

        btnPause.setVisible(
            reproduciendo
        );
    }

    // =============================================================
    // BOTON DE IMAGEN
    // =============================================================

    private ImageView crearBotonImagen(
        String ruta,
        double ancho
    ) {

        ImageView boton =
            new ImageView(
                new Image(ruta)
            );

        boton.setFitWidth(
            ancho
        );

        boton.setPreserveRatio(
            true
        );

        boton.setCursor(
            Cursor.HAND
        );

        // =========================================================
        // HOVER
        // =========================================================

        ScaleTransition agrandar =
            new ScaleTransition(
                Duration.millis(150),
                boton
            );

        agrandar.setToX(
            1.12
        );

        agrandar.setToY(
            1.12
        );

        ScaleTransition achicar =
            new ScaleTransition(
                Duration.millis(150),
                boton
            );

        achicar.setToX(
            1
        );

        achicar.setToY(
            1
        );

        boton.setOnMouseEntered(
            e -> agrandar.playFromStart()
        );

        boton.setOnMouseExited(
            e -> achicar.playFromStart()
        );

        return boton;
    }

    // =============================================================
    // ARTISTAS
    // =============================================================

    private String obtenerArtistas() {

        if (
            cancion.getCantantes() == null ||
            cancion.getCantantes().isEmpty()
        ) {

            return "";
        }

        StringBuilder resultado =
            new StringBuilder();

        for (
            int i = 0;
            i < cancion
                .getCantantes()
                .size();
            i++
        ) {

            Cantante cantante =
                cancion
                    .getCantantes()
                    .get(i);

            resultado.append(
                cantante.getNombre()
            );

            if (
                i <
                cancion
                    .getCantantes()
                    .size() - 1
            ) {

                resultado.append(
                    ", "
                );
            }
        }

        return resultado.toString();
    }

    // =============================================================
    // FUENTE
    // =============================================================

    private void cargarFuente() {

        try {

            fuenteFWC =
                Font.loadFont(
                    new File(
                        "src/fonts/FWC2026.ttf"
                    )
                    .toURI()
                    .toString(),
                    30
                );

            if (
                fuenteFWC == null
            ) {

                fuenteFWC =
                    Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        30
                    );
            }

        } catch (Exception e) {

            fuenteFWC =
                Font.font(
                    "Arial",
                    FontWeight.BOLD,
                    30
                );
        }
    }

    // =============================================================
    // CARGAR IMAGEN
    // =============================================================

    private Image cargarImagen(
        String ruta,
        double ancho,
        double alto,
        boolean backgroundLoading
    ) {

        String uri =
            obtenerUriImagen(ruta);

        String clave =
            uri
            + "|"
            + ancho
            + "|"
            + alto
            + "|"
            + backgroundLoading;

        Image imagen =
            CACHE_IMAGENES.get(
                clave
            );

        if (
            imagen != null
        ) {

            return imagen;
        }

        imagen =
            new Image(
                uri,
                ancho,
                alto,
                false,
                true,
                backgroundLoading
            );

        CACHE_IMAGENES.put(
            clave,
            imagen
        );

        return imagen;
    }

    // =============================================================
    // OBTENER URI
    // =============================================================

    private String obtenerUriImagen(
        String ruta
    ) {

        if (
            ruta == null ||
            ruta.isBlank()
        ) {

            throw new IllegalArgumentException(
                "Ruta de imagen vacía."
            );
        }

        if (
            ruta.startsWith("file:") ||
            ruta.startsWith("http:") ||
            ruta.startsWith("https:")
        ) {

            return ruta;
        }

        File archivo =
            new File(ruta);

        if (
            !archivo.exists()
        ) {

            archivo =
                new File(
                    "src/" + ruta
                );
        }

        if (
            !archivo.exists()
        ) {

            throw new IllegalArgumentException(
                "No se encontró la imagen: "
                + ruta
            );
        }

        return archivo
            .toURI()
            .toString();
    }
}