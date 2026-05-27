import javafx.scene.image.Image;

public class Recurso {

	public static Image cargarBandera(String codigo) {

	    var stream = Recurso.class.getResourceAsStream(
	        "/images/paises/" + codigo + ".png"
	    );

	    if (stream == null) {

	        System.out.println(
	            "No existe la bandera: " + codigo
	        );

	        return null;
	    }

	    return new Image(stream);
	}

    public static Image cargarImagen(String carpeta, String nombre) {

        return new Image(
            Recurso.class.getResourceAsStream(
                "/images/" + carpeta + "/" + nombre
            )
        );
    }
}