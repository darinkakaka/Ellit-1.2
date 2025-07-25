// ===============================
// Clase Inicio.java
// ===============================
// Esta clase contiene el punto de entrada principal del sistema ELLIT
// Se encarga de iniciar el login y redirigir al menú correspondiente

import Usuario.FuncionesUsuario;

public class Inicio {
    public static void limpiarPantalla(){
        try {
            if (System.getProperty("os.name").contains("Windows"))
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            else
                new ProcessBuilder("clear").inheritIO().start().waitFor();
        } catch (Exception e) {
            return;
        }
    }

    public static void main(String[] args) {
        limpiarPantalla();
        // ===============================
        // Bienvenida al sistema
        // ===============================
        System.out.println("=====================================");
        System.out.println("      BIENVENIDO A ELLIT");
        System.out.println("=====================================");

        // Llamamos a la función login del sistema
        // Esta función está en FuncionesUsuario.java y maneja tanto login como registro
        FuncionesUsuario.login();
        
    }
}
