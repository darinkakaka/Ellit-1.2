package Menu;


import Usuario.FuncionesUsuario;
import java.util.Scanner;

public class menus {

    static Scanner sc = new Scanner(System.in);

    // ===============================
    // Menú principal para usuarios cliente
    // ===============================
    public static void menuUsuario() {
        int op = 0;
        do {
            System.out.println("\n--- Menú Usuario ---");
            System.out.println("1. Ver perfil");
            System.out.println("2. Comentarios");
            System.out.println("3. Rutinas");
            System.out.println("4. Planes Nutricionales");
            System.out.println("5. Entrenadores");
            System.out.println("6. Tienda");
            System.out.println("7. Cerrar sesión");

            op = FuncionesUsuario.validarNumero(1, 7);

            switch (op) {
                case 1:
                    FuncionesUsuario.verPerfil();
                    break;
                case 7:
                    FuncionesUsuario.cerrarSesion();
                    break;
                default:
                    System.out.println("Esta opción aún no está disponible.");
            }
        } while (op != 7);
    }

    // ===============================
    // Menú del administrador
    // ===============================
    public static void menuAdmin() {
        int op = 0;
        do {
            System.out.println("\n--- Menú Admin ---");
            System.out.println("1. Gestionar usuarios");
            System.out.println("2. Gestionar comentarios");
            System.out.println("3. Gestionar rutinas");
            System.out.println("4. Gestionar planes nutricionales");
            System.out.println("5. Gestionar entrenadores");
            System.out.println("6. Gestionar tienda");
            System.out.println("7. Entrar a menú normal");
            System.out.println("8. Cerrar sesión");

            op = FuncionesUsuario.validarNumero(1, 8);

            switch (op) {
                case 1:
                    FuncionesUsuario.menuGestionUsuarios();
                    break;
                case 7:
                    menuNormalParaAdmin();
                    break;
                case 8:
                    FuncionesUsuario.cerrarSesion();
                    break;
                default:
                    System.out.println("Esta opción aún no está disponible.");
            }
        } while (op != 8);
    }

    // ===============================
    // Menú normal que también ve el admin
    // ===============================
    public static void menuNormalParaAdmin() {
        int op = 0;
        do {
            System.out.println("\n--- Menú Usuario (Admin) ---");
            System.out.println("1. Ver perfil");
            System.out.println("2. Comentarios");
            System.out.println("3. Rutinas");
            System.out.println("4. Planes Nutricionales");
            System.out.println("5. Entrenadores");
            System.out.println("6. Tienda");
            System.out.println("7. Cerrar sesión");
            System.out.println("8. Volver a menú admin");

            op = FuncionesUsuario.validarNumero(1, 8);

            switch (op) {
                case 1:
                    FuncionesUsuario.verPerfil();
                    break;
                case 7:
                    FuncionesUsuario.cerrarSesion();
                    break;
                case 8:
                    menuAdmin();
                    break;
                default:
                    System.out.println("Esta opción aún no está disponible.");
            }
        } while (op != 7 && op != 8);
    }
       
}
