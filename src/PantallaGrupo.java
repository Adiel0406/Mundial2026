import java.util.ArrayList;

import javafx.animation.ScaleTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

public class PantallaGrupo {

    private Mundial mundial;

    private Grupo grupo;

    private VBox tablaBox;
    
    private ManagerMundial managerMundial =
    	    new ManagerMundial();

    public PantallaGrupo(
            Mundial mundial,
            Grupo grupo
    ) {

        this.mundial = mundial;

        this.grupo = grupo;
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
        // TITULO
        // =========================

        Label titulo =
            new Label(
                "GRUPO " + grupo.getNombre()
            );

        Font fuente =
            Font.loadFont(

                getClass().getResourceAsStream(
                    "/fonts/FWC2026.ttf"
                ),

                38
            );

        titulo.setFont(
            fuente
        );
        
        titulo.setTextFill(
            Color.web(
                colorGrupo(grupo.getNombre())
            )
        );

        // =========================
        // CONTENEDOR CENTRAL
        // =========================

        VBox contenido =
            new VBox(25);

        contenido.setAlignment(
            Pos.TOP_CENTER
        );

        contenido.setPadding(
            new Insets(30)
        );

        contenido.getChildren().add(
            titulo
        );

        // =========================
        // FECHAS
        // =========================

        ManagerFixture manager =
            new ManagerFixture(
                new ManagerPais(Archivos.ARCHIVO_PAISES)
            );

        for (
            FechaMundial fecha :
            grupo.getFechas()
        ) {

            VBox fechaBox =
                crearFechaBox(
                    fecha,
                    manager
                );

            contenido.getChildren().add(
                fechaBox
            );
        }

        // =========================
        // TABLA
        // =========================

        tablaBox =
            new VBox(10);
        
        tablaBox.setStyle(

            "-fx-background-color: rgba(0,0,0,0.45);"
            +

            "-fx-background-radius: 25;"
            +

            "-fx-border-radius: 25;"
            +

            "-fx-border-color: "
            + colorGrupo(grupo.getNombre())
            + ";"

            +

            "-fx-border-width: 4;"
        );

        tablaBox.setAlignment(
            Pos.CENTER
        );

        actualizarTablaVisual(
            manager
        );

        contenido.getChildren().add(
            tablaBox
        );

        // =========================
        // SCROLL
        // =========================

        ScrollPane scroll =
            new ScrollPane(
                contenido
            );

        scroll.setFitToWidth(true);

        scroll.setStyle(

            "-fx-background: transparent;"
            +

            "-fx-background-color: transparent;"
        );

        root.setCenter(
            scroll
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

            PantallaPartidosMenu pantalla =
                new PantallaPartidosMenu(
                    mundial
                );

            stage.setScene(

                pantalla.crearScene(stage)
            );

            stage.setMaximized(true);
        });

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

    // =====================================
    // CREAR FECHA
    // =====================================

    private VBox crearFechaBox(
            FechaMundial fecha,
            ManagerFixture manager
    ) {

        VBox box =
            new VBox(15);

        box.setAlignment(
            Pos.CENTER
        );

        box.setPadding(
            new Insets(20)
        );

        box.setStyle(

            "-fx-background-color: rgba(0,0,0,0.45);"
            +

            "-fx-background-radius: 25;"
            +

            "-fx-border-color: white;"
            +

            "-fx-border-radius: 25;"
            +

            "-fx-border-width: 2;"
        );

        Label tituloFecha =
            new Label(
                "FECHA " + fecha.getNumero()
            );

        tituloFecha.setTextFill(
            Color.WHITE
        );

        tituloFecha.setFont(

            Font.font(
                "Arial",
                24
            )
        );

        box.getChildren().add(
            tituloFecha
        );

        for (
            Partido partido :
            fecha.getPartidos()
        ) {

            HBox fila =
                crearPartidoBox(
                    partido,
                    manager
                );

            box.getChildren().add(
                fila
            );
        }

        return box;
    }

    // =====================================
    // PARTIDO VISUAL
    // =====================================

    private HBox crearPartidoBox(
            Partido partido,
            ManagerFixture manager
    ) {

        HBox fila =
            new HBox(15);

        fila.setAlignment(
            Pos.CENTER
        );

        // =========================
        // LABELS
        // =========================

        Label local =
            new Label(
                partido.getLocal()
                       .getNombre()
                       .toUpperCase()
            );

        local.setTextFill(
            Color.WHITE
        );

        local.setFont(
            Font.font(
                "Arial",
                18
            )
        );

        Label visitante =
            new Label(
                partido.getVisitante()
                       .getNombre()
                       .toUpperCase()
            );

        visitante.setTextFill(
            Color.WHITE
        );

        visitante.setFont(
            Font.font(
                "Arial",
                18
            )
        );

        // =========================
        // TEXTFIELDS
        // =========================

        TextField golesLocal =
            new TextField();

        golesLocal.setPrefWidth(55);

        golesLocal.setAlignment(
            Pos.CENTER
        );

        golesLocal.setStyle(
            estiloTextField()
        );

        TextField golesVisitante =
            new TextField();

        golesVisitante.setPrefWidth(55);

        golesVisitante.setAlignment(
            Pos.CENTER
        );

        golesVisitante.setStyle(
            estiloTextField()
        );

        // =========================
        // SI YA EXISTE RESULTADO
        // =========================

        if (partido.isJugado()) {

            golesLocal.setText(
                String.valueOf(
                    partido.getGolesLocal()
                )
            );

            golesVisitante.setText(
                String.valueOf(
                    partido.getGolesVisitante()
                )
            );

            golesLocal.setDisable(true);

            golesVisitante.setDisable(true);
        }

        Label guion =
            new Label("-");

        guion.setTextFill(
            Color.WHITE
        );

        // =========================
        // BOTON GUARDAR
        // =========================

        Button guardar =
            crearBoton(
                "GUARDAR"
            );

        // =========================
        // BOTON ELIMINAR
        // =========================

        Button eliminar =
            crearBoton(
                "ELIMINAR"
            );

        eliminar.setStyle(

            "-fx-background-color: #D62828;"
            +

            "-fx-text-fill: white;"
            +

            "-fx-font-size: 14px;"
            +

            "-fx-font-weight: bold;"
            +

            "-fx-background-radius: 15;"
            +

            "-fx-padding: 8 18 8 18;"
            +

            "-fx-cursor: hand;"
        );

        // =========================
        // SI YA ESTABA JUGADO
        // =========================

        if (partido.isJugado()) {

            guardar.setDisable(true);
        }

        // =========================
        // GUARDAR
        // =========================

        guardar.setOnAction(e -> {

            try {

                int gl =
                    Integer.parseInt(
                        golesLocal.getText()
                    );

                int gv =
                    Integer.parseInt(
                        golesVisitante.getText()
                    );

                manager.registrarResultado(
                    partido,
                    gl,
                    gv
                );

                manager.actualizarTabla(
                    grupo
                );

                manager.ordenarGrupo(
                    grupo
                );
                
                ManagerMundial mm =
                	    new ManagerMundial();

               	mm.guardarMundial(
               	    mundial,
               	    Archivos.ARCHIVO_MUNDIAL
               	);

                // =====================
                // BLOQUEAR CAMPOS
                // =====================

                golesLocal.setDisable(true);

                golesVisitante.setDisable(true);

                guardar.setDisable(true);

                // =====================
                // GUARDAR DAT
                // =====================

                managerMundial.guardarMundial(
                    mundial,
                    Archivos.ARCHIVO_MUNDIAL
                );

                actualizarTablaVisual(
                    manager
                );

            } catch (Exception ex) {

                ex.printStackTrace();
            }
        });

        // =========================
        // ELIMINAR
        // =========================

        eliminar.setOnAction(e -> {

            partido.setGolesLocal(0);

            partido.setGolesVisitante(0);

            partido.setJugado(false);

            golesLocal.clear();

            golesVisitante.clear();

            golesLocal.setDisable(false);

            golesVisitante.setDisable(false);

            guardar.setDisable(false);

            manager.actualizarTabla(
                grupo
            );

            manager.ordenarGrupo(
                grupo
            );
            
            ManagerMundial mm =
            	    new ManagerMundial();

           	mm.guardarMundial(
           	    mundial,
           	    Archivos.ARCHIVO_MUNDIAL
           	);

            managerMundial.guardarMundial(
                mundial,
                Archivos.ARCHIVO_MUNDIAL
            );

            actualizarTablaVisual(
                manager
            );
        });

        fila.getChildren().addAll(

            local,
            golesLocal,
            guion,
            golesVisitante,
            visitante,
            guardar,
            eliminar
        );

        return fila;
    }

    // =====================================
    // TABLA VISUAL
    // =====================================

    private void actualizarTablaVisual(
            ManagerFixture manager
    ) {

        manager.actualizarTabla(
            grupo
        );

        manager.ordenarGrupo(
            grupo
        );
        
        ManagerMundial mm =
        	    new ManagerMundial();

       	mm.guardarMundial(
       	    mundial,
       	    Archivos.ARCHIVO_MUNDIAL
       	);

        tablaBox.getChildren().clear();

        // =========================
        // TITULO
        // =========================

        Label titulo =
            new Label(
                "TABLA DE POSICIONES"
            );

        titulo.setTextFill(
            Color.WHITE
        );

        titulo.setFont(

            Font.font(
                "Arial",
                30
            )
        );

        tablaBox.getChildren().add(
            titulo
        );

        // =========================
        // FILAS
        // =========================

        GridPane tabla =
            new GridPane();

        tabla.setHgap(25);

        tabla.setVgap(12);

        tabla.setAlignment(
            Pos.CENTER
        );

        // =========================
        // CABECERAS
        // =========================

        String[] headers = {

            "POS",
            "PAIS",
            "PTS",
            "DG",
            "GF",
            "GC"
        };

        for (int i = 0; i < headers.length; i++) {

            Label h =
                new Label(headers[i]);

            h.setTextFill(
                Color.GOLD
            );

            h.setFont(
                Font.font(
                    "Arial",
                    20
                )
            );

            tabla.add(h, i, 0);
        }

        // =========================
        // FILAS
        // =========================

        int fila = 1;
        
        ManagerTerceros managerTerceros =
                new ManagerTerceros();

        ArrayList<EquipoGrupo> mejoresTerceros =
                managerTerceros.obtenerMejoresTerceros(
                        mundial.getGrupos(),
                        8
                );

        for (EquipoGrupo e : grupo.getEquipos()) {

            Label pos =
                crearDatoTabla(String.valueOf(fila));

            Label pais =
                crearDatoTabla(e.getPais().getNombre().toUpperCase());

            Label pts =
                crearDatoTabla(String.valueOf(e.getPuntos()));

            Label dg =
                crearDatoTabla(String.valueOf(e.diferenciaGol()));

            Label gf =
                crearDatoTabla(String.valueOf(e.getGolesFavor()));

            Label gc =
                crearDatoTabla(String.valueOf(e.getGolesContra()));

            // =========================
            // CLASIFICACIÓN
            // =========================

            boolean esTop2 = fila <= 2;

            boolean esTerceroClasificado =
                    mejoresTerceros.stream()
                            .anyMatch(t ->
                                    t.getPais().equals(e.getPais())
                            );

            boolean clasificado = esTop2 || esTerceroClasificado;

            // =========================
            // ESTILO
            // =========================

            String estilo;

            if (clasificado) {

                estilo =
                    "-fx-background-color: rgba(80,255,120,0.35);"
                    + "-fx-padding: 4 8 4 8;"
                    + "-fx-background-radius: 8;";

            } else {

                estilo =
                    "-fx-background-color: rgba(255,80,80,0.25);"
                    + "-fx-padding: 4 8 4 8;"
                    + "-fx-background-radius: 8;";
            }

            pos.setStyle(estilo);
            pais.setStyle(estilo);
            pts.setStyle(estilo);
            dg.setStyle(estilo);
            gf.setStyle(estilo);
            gc.setStyle(estilo);

            tabla.add(pos, 0, fila);
            tabla.add(pais, 1, fila);
            tabla.add(pts, 2, fila);
            tabla.add(dg, 3, fila);
            tabla.add(gf, 4, fila);
            tabla.add(gc, 5, fila);

            fila++;
        }

        tablaBox.getChildren().add(
            tabla
        );
    }
    
    private Label crearDatoTabla(
            String texto
    ) {

        Label label =
            new Label(texto);

        label.setTextFill(
            Color.WHITE
        );

        label.setFont(

            Font.font(
                "Arial",
                18
            )
        );

        return label;
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
    
    
    private String estiloTextField() {

        return

            "-fx-background-color: rgba(255,255,255,0.15);"
            +

            "-fx-text-fill: white;"
            +

            "-fx-font-size: 18px;"
            +

            "-fx-background-radius: 12;"
            +

            "-fx-border-color: white;"
            +

            "-fx-border-radius: 12;";
    }
    
    private Button crearBoton(
            String texto
    ) {

        Button btn =
            new Button(texto);

        btn.setStyle(

            "-fx-background-color: "
            + colorGrupo(grupo.getNombre())
            + ";"

            +

            "-fx-text-fill: white;"
            +

            "-fx-font-size: 14px;"
            +

            "-fx-font-weight: bold;"
            +

            "-fx-background-radius: 15;"
            +

            "-fx-padding: 8 18 8 18;"
            +

            "-fx-cursor: hand;"
        );

        ScaleTransition agrandar =
            new ScaleTransition(
                Duration.millis(120),
                btn
            );

        agrandar.setToX(1.08);

        agrandar.setToY(1.08);

        ScaleTransition achicar =
            new ScaleTransition(
                Duration.millis(120),
                btn
            );

        achicar.setToX(1);

        achicar.setToY(1);

        btn.setOnMouseEntered(e -> {

            agrandar.playFromStart();
        });

        btn.setOnMouseExited(e -> {

            achicar.playFromStart();
        });

        return btn;
    }
}