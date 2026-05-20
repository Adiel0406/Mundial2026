import java.util.ArrayList;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Main extends Application {

    private final int WIDTH = 1000;
    private final int HEIGHT = 800;

    private Font fuente;

    // ================= LISTA PAISES =================

    private ArrayList<Pais> paises;

    // ================= AREA INFORMACION =================

    private TextArea txtInfo;

    // ================= SELECCIONES =================

    private final String[][] selecciones = {

            // codigo , nombre real

            {"mex", "Mexico"},
            {"rsa", "Sudafrica"},
            {"kor", "Corea del Sur"},
            {"cze", "Chequia"},

            {"can", "Canada"},
            {"bih", "Bosnia y Herzegovina"},
            {"qat", "Catar"},
            {"sui", "Suiza"},

            {"bra", "Brasil"},
            {"mar", "Marruecos"},
            {"hai", "Haiti"},
            {"sco", "Escocia"},

            {"usa", "Estados Unidos"},
            {"par", "Paraguay"},
            {"aus", "Australia"},
            {"tur", "Turquia"},

            {"ger", "Alemania"},
            {"cuw", "Curazao"},
            {"civ", "Costa de Marfil"},
            {"ecu", "Ecuador"},

            {"ned", "Paises Bajos"},
            {"jpn", "Japon"},
            {"swe", "Suecia"},
            {"tun", "Tunez"},

            {"bel", "Belgica"},
            {"egy", "Egipto"},
            {"irn", "Iran"},
            {"nzl", "Nueva Zelanda"},

            {"esp", "España"},
            {"cpv", "Cabo Verde"},
            {"ksa", "Arabia Saudita"},
            {"uru", "Uruguay"},

            {"fra", "Francia"},
            {"sen", "Senegal"},
            {"irq", "Irak"},
            {"nor", "Noruega"},

            {"arg", "Argentina"},
            {"alg", "Argelia"},
            {"aut", "Austria"},
            {"jor", "Jordania"},

            {"por", "Portugal"},
            {"cod", "RD Congo"},
            {"uzb", "Uzbekistan"},
            {"col", "Colombia"},

            {"eng", "Inglaterra"},
            {"cro", "Croacia"},
            {"gha", "Ghana"},
            {"pan", "Panama"}
    };

    public static void main(String[] args) {

        launch(args);
    }

    @Override
    public void start(Stage stage) {

        // ================= LEER TXT =================

        paises = Manager.leerPaisTxt("paises.txt");

        // ================= FUENTE =================

        fuente = Font.loadFont(
                getClass().getResourceAsStream(
                        "/fonts/FWC2026.ttf"
                ),
                22
        );

        if (fuente == null) {

            System.out.println(
                    "No se pudo cargar la fuente"
            );

            fuente = Font.font("Arial", 22);
        }

        // ================= ESCENAS =================

        Scene menuScene = crearMenu(stage);

        Scene tutorScene =
                crearTutor(stage, menuScene);

        VBox rootMenu =
                (VBox) menuScene.getRoot();

        Button btnIniciar =
                (Button) rootMenu
                        .getChildren()
                        .get(1);

        btnIniciar.setOnAction(e -> {

            stage.setScene(tutorScene);
        });

        // ================= STAGE =================

        stage.setTitle(
                "Tutor Mundial 2026"
        );

        stage.setScene(menuScene);

        stage.show();
    }

    // =================================================
    // MENU
    // =================================================

    private Scene crearMenu(Stage stage) {

        VBox panMenu = new VBox(25);

        panMenu.setAlignment(Pos.CENTER);

        panMenu.setPadding(
                new Insets(20)
        );

        panMenu.setStyle("""
                -fx-background-color:
                linear-gradient(
                    to bottom,
                    #0a0a0a,
                    #202020
                );
                """);

        Label lblTitulo = new Label(
                "MENU TUTOR MUNDIAL"
        );

        aplicarFuente(lblTitulo);

        lblTitulo.setStyle("""
                -fx-text-fill: white;
                """);

        Button btnIniciar =
                new Button(
                        "Iniciar Tutor"
                );

        Button btnTest =
                new Button(
                        "Iniciar Test"
                );

        Button btnSalir =
                new Button(
                        "Salir"
                );

        aplicarFuente(btnIniciar);

        aplicarFuente(btnTest);

        aplicarFuente(btnSalir);

        btnIniciar.setPrefWidth(250);

        btnTest.setPrefWidth(250);

        btnSalir.setPrefWidth(250);

        estilizarBoton(
                btnIniciar,
                "#2196F3"
        );

        estilizarBoton(
                btnTest,
                "#4CAF50"
        );

        estilizarBoton(
                btnSalir,
                "#F44336"
        );

        btnSalir.setOnAction(e -> {

            System.exit(0);
        });

        panMenu.getChildren().addAll(

                lblTitulo,
                btnIniciar,
                btnTest,
                btnSalir
        );

        return new Scene(
                panMenu,
                WIDTH,
                HEIGHT
        );
    }

    // =================================================
    // TUTOR
    // =================================================

    private Scene crearTutor(
            Stage stage,
            Scene menuScene
    ) {

        VBox panTutor = new VBox(20);

        panTutor.setAlignment(
                Pos.TOP_CENTER
        );

        panTutor.setPadding(
                new Insets(20)
        );

        panTutor.setStyle("""
                -fx-background-color:
                linear-gradient(
                    to bottom,
                    #121212,
                    #202020
                );
                """);

        // ================= LABEL =================

        Label lblSeleccion =
                new Label(
                        "Seleccione una selección"
                );

        aplicarFuente(lblSeleccion);

        lblSeleccion.setStyle("""
                -fx-text-fill: white;
                """);

        // ================= FLOWPANE =================

        FlowPane panPaises =
                new FlowPane();

        panPaises.setAlignment(
                Pos.CENTER
        );

        panPaises.setHgap(15);

        panPaises.setVgap(15);

        // ================= TEXT AREA =================

        txtInfo = new TextArea();

        txtInfo.setEditable(false);

        txtInfo.setWrapText(true);

        txtInfo.setPrefHeight(250);

        txtInfo.setFont(fuente);

        txtInfo.setStyle("""
                -fx-control-inner-background:
                #101010;

                -fx-text-fill: white;

                -fx-highlight-fill: #444;

                -fx-font-size: 14px;
                """);

        // ================= CREAR BOTONES =================

        for (String[] datos : selecciones) {

            Button boton =
                    crearBotonSeleccion(
                            datos[0],
                            datos[1]
                    );

            panPaises.getChildren()
                    .add(boton);
        }

        // ================= SCROLL =================

        ScrollPane scroll =
                new ScrollPane(
                        panPaises
                );

        scroll.setFitToWidth(true);

        scroll.setPrefHeight(450);

        scroll.setStyle("""
                -fx-background:
                transparent;

                -fx-background-color:
                transparent;
                """);

        // ================= VOLVER =================

        Button btnVolver =
                new Button("Volver");

        aplicarFuente(btnVolver);

        estilizarBoton(
                btnVolver,
                "#9C27B0"
        );

        btnVolver.setPrefWidth(220);

        btnVolver.setOnAction(e -> {

            stage.setScene(menuScene);
        });

        // ================= AGREGAR =================

        panTutor.getChildren().addAll(

                lblSeleccion,
                scroll,
                txtInfo,
                btnVolver
        );

        return new Scene(
                panTutor,
                WIDTH,
                HEIGHT
        );
    }

    // =================================================
    // CREAR BOTON
    // =================================================

    private Button crearBotonSeleccion(
            String codigo,
            String nombrePais
    ) {

        var stream =
                getClass()
                        .getResourceAsStream(
                                "/images/"
                                        + codigo
                                        + ".png"
                        );

        if (stream == null) {

            System.out.println(
                    "No existe imagen: "
                            + codigo
            );

            return new Button(
                    codigo.toUpperCase()
            );
        }

        Image imagen =
                new Image(stream);

        ImageView imgView =
                new ImageView(imagen);

        imgView.setFitWidth(100);

        imgView.setFitHeight(80);

        imgView.setPreserveRatio(true);

        Button boton =
                new Button(
                        codigo.toUpperCase()
                );

        aplicarFuente(boton);

        boton.setGraphic(imgView);

        boton.setContentDisplay(
                ContentDisplay.TOP
        );

        boton.setAlignment(Pos.CENTER);

        boton.setPrefSize(140, 140);

        estilizarBotonPais(boton);

        // ================= HOVER =================

        boton.setOnMouseEntered(e -> {

            boton.setScaleX(1.05);

            boton.setScaleY(1.05);
        });

        boton.setOnMouseExited(e -> {

            boton.setScaleX(1);

            boton.setScaleY(1);
        });

        // ================= EVENTO =================

        boton.setOnAction(e -> {

            mostrarInformacionPais(
                    nombrePais
            );
        });

        return boton;
    }

    // =================================================
    // MOSTRAR INFORMACION
    // =================================================

    private void mostrarInformacionPais(
            String nombrePais
    ) {

        for (Pais p : paises) {

            if (
                    p.getNombre()
                            .equalsIgnoreCase(
                                    nombrePais
                            )
            ) {

                String info =

                        "PAIS: "
                                + p.getNombre()

                                + "\n\n"

                                + "CONTINENTE: "
                                + p.getContinente()

                                + "\n"

                                + "MUNDIALES: "
                                + p.getMundiales()

                                + "\n"

                                + "PARTICIPACIONES: "
                                + p.getParticipantes()

                                + "\n\n"

                                + "CULTURA:\n"
                                + p.getCultura()

                                + "\n\n"

                                + "DATOS FUTBOLISTICOS:\n"
                                + p.getDatosFutbol()

                                + "\n\n"

                                + "JUGADORES:\n";

                for (Jugador j :
                        p.getJugadores()) {

                    info +=

                            "- "
                                    + j.getNombre()

                                    + " | "

                                    + j.getPosicion()

                                    + " | #"

                                    + j.getNumero()

                                    + "\n";
                }

                txtInfo.setText(info);

                return;
            }
        }

        txtInfo.setText(
                "No se encontro informacion"
        );
    }

    // =================================================
    // FUENTE GENERAL
    // =================================================

    private void aplicarFuente(
            Labeled componente
    ) {

        componente.setFont(fuente);
    }

    // =================================================
    // ESTILO BOTONES MENU
    // =================================================

    private void estilizarBoton(
            Button boton,
            String color
    ) {

        boton.setStyle("""
                -fx-background-color: %s;

                -fx-text-fill: white;

                -fx-font-family: "FWC2026";

                -fx-font-size: 16px;

                -fx-background-radius: 12;

                -fx-padding: 12;
                """.formatted(color));

        boton.setOnMouseEntered(e -> {

            boton.setScaleX(1.05);

            boton.setScaleY(1.05);
        });

        boton.setOnMouseExited(e -> {

            boton.setScaleX(1);

            boton.setScaleY(1);
        });
    }

    // =================================================
    // BOTONES PAISES
    // =================================================

    private void estilizarBotonPais(
            Button boton
    ) {

        boton.setStyle("""
                -fx-background-color: black;

                -fx-text-fill: white;

                -fx-font-family: "FWC2026";

                -fx-font-size: 14px;

                -fx-background-radius: 12;

                -fx-padding: 8;
                """);
    }
}