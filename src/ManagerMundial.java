import java.io.*;

import javafx.scene.Scene;

public class ManagerMundial {

	public void guardarMundial(
	        Mundial mundial,
	        String archivo
	) {

	    try (

	        ObjectOutputStream oos =
	            new ObjectOutputStream(
	                new FileOutputStream(archivo)
	            )

	    ) {

	        oos.writeObject(mundial);

	    } catch (Exception e) {

	        e.printStackTrace();
	    }
	}
	
	public Mundial cargarMundial(
	        String archivo
	) {

	    try (

	        ObjectInputStream ois =
	            new ObjectInputStream(
	                new FileInputStream(archivo)
	            )

	    ) {

	        return
	            (Mundial)
	            ois.readObject();

	    } catch (Exception e) {

	        e.printStackTrace();
	    }

	    return null;
	}
	
	public void reiniciarMundial(
	        String archivo
	) {

	    File file =
	        new File(archivo);

	    if (file.exists()) {

	        file.delete();
	    }
	}
	
	public Scene getScene() { return getScene(); }
}
