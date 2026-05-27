import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class Estilo {

    public static void estilizarBoton(Button boton) {

        boton.setStyle("""
            -fx-background-color: black;
            -fx-text-fill: white;
            -fx-font-size: 16px;
            -fx-background-radius: 10;
        """);
    }

    public static void estilizarTitulo(Label label) {

        label.setStyle("""
            -fx-text-fill: white;
            -fx-font-size: 28px;
            -fx-font-weight: bold;
        """);
    }
}