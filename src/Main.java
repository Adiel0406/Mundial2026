import java.util.*;

public class Main {
    public static void main(String[] args) {

        // 📂 Ruta del archivo (ajústala si es necesario)
        String ruta = "paises.txt";

        // 📥 Leer países
        ArrayList<Pais> paises = Manager.leerPaisTxt(ruta);

        // ✅ Verificar que se cargaron.
        if (paises.isEmpty()) {
            System.out.println("No se cargaron países.");
            return;
        }

        System.out.println("=== LISTA DE PAISES ===\n");

        // 🔍 Mostrar todos los países
        for (Pais p : paises) {
            System.out.println(p);
            System.out.println("------------------------");
        }

        // 🧪 PRUEBAS EXTRA CON UN PAÍS
        System.out.println("\n=== PRUEBAS ===");

        Pais prueba = paises.get(0);

        System.out.println("\nPaís seleccionado: " + prueba.getNombre());

        // 📌 Mostrar jugadores
        System.out.println("Jugadores actuales:");
        for (Jugador j : prueba.getJugadores()) {
            System.out.println("- " + j);
        }

        // ❌ Intentar agregar un jugador extra (debe fallar)
        System.out.println("\nIntentando agregar un 4to jugador...");
        prueba.agregarJugador(new Jugador("JugadorExtra", "Portero", 1));

        // 🗑 Eliminar un jugador
        System.out.println("\nEliminando jugador con número 10...");
        prueba.eliminarJugador(10);

        // 🔄 Mostrar jugadores después de eliminar
        System.out.println("\nJugadores después de eliminar:");
        for (Jugador j : prueba.getJugadores()) {
            System.out.println("- " + j);
        }

        // ➕ Intentar agregar nuevamente
        System.out.println("\nAgregando nuevo jugador...");
        prueba.agregarJugador(new Jugador("NuevoJugador", "Portero", 1));

        // 🔄 Mostrar resultado final
        System.out.println("\nJugadores finales:");
        for (Jugador j : prueba.getJugadores()) {
            System.out.println("- " + j);
        }
    }
}