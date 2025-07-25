package Usuario;
// ===============================
// Clase FuncionesUsuario.java
// ===============================
// Contiene todas las funciones de interacción con el usuario (login, registro, modificar datos, etc.)

import java.util.ArrayList;
import java.util.Scanner;

import Menu.menus;

public class FuncionesUsuario {
    static Scanner sc = new Scanner(System.in);
    static Usuario usuarioActual = null;

    // ===============================
    // Función de login
    // ===============================
   public static void login() {
    int opcion;
    do {
        System.out.println("¿Ya tienes una cuenta? (1. Sí | 2. No)");
        opcion = validarNumero(1, 2);

        if (opcion == 2) {
            registro();
            break;
        }

        boolean sesionIniciada = false;

        while (!sesionIniciada) {
            System.out.print("Usuario: ");
            String nombre = sc.next();
            System.out.print("Contraseña: ");
            String contrasenia = sc.next();

            Usuario u = CrudUsuario.buscarUsuario(nombre);
            if (u != null && u.getContrasenia().equals(contrasenia)) {
                usuarioActual = u;
                System.out.println("¡Bienvenido, " + u.getUsuario() + "!");
                sesionIniciada = true;

                if (u.getRol().equalsIgnoreCase("admin")) {
                    menus.menuAdmin();
                } else {
                    menus.menuUsuario();
                }

            } else {
                System.out.println(" Usuario no encontrado o contraseña incorrecta.");
                System.out.println("¿Deseas intentarlo de nuevo (1) o registrarte (2)?");
                int reintentar = validarNumero(1, 2);

                if (reintentar == 2) {
                    registro();
                    break;
                }
            }
        }

    } while (opcion != 1);
}


    // ===============================
    // Registro de nuevo usuario
    // ===============================
    public static void registro() {
        Usuario u = new Usuario();
        sc.nextLine(); // limpiar buffer

        System.out.print("Nombre de usuario: ");
        u.setUsuario(sc.nextLine());

        System.out.print("Correo: ");
        u.setCorreo(sc.nextLine());

        System.out.print("Contraseña: ");
        u.setContrasenia(sc.nextLine());

        System.out.print("Edad: ");
        while (!sc.hasNextFloat()) {
            System.out.println("Por favor introduce un número válido.");
            sc.nextLine();
        }
        u.setEdad(sc.nextFloat());

        u.setRol("cliente"); // todos inician como cliente

        CrudUsuario.insertarUsuario(u);
        System.out.println("Registro exitoso, ahora inicia sesión.");
        login(); // vuelve a llamar login para que inicie sesión
    }

    // ===============================
    // Ver perfil del usuario
    // ===============================
    public static void verPerfil() {
        System.out.println(usuarioActual);
        System.out.println("¿Deseas modificar algún dato? (1.Sí / 2.No)");
        int op = validarNumero(1, 2);

        if (op == 1) {
            modificarDatosUsuario();
        }
    }

    // ===============================
    // Modificar datos como cliente
    // ===============================
    public static void modificarDatosUsuario() {
        System.out.println("¿Qué deseas modificar?");
        System.out.println("1. Usuario\n2. Contraseña\n3. Correo\n4. Edad\n5. Cancelar");
        int opcion = validarNumero(1, 5);

        switch (opcion) {
            case 1:
                System.out.print("Nuevo usuario: ");
                usuarioActual.setUsuario(sc.next());
                break;
            case 2:
                System.out.print("Nueva contraseña: ");
                usuarioActual.setContrasenia(sc.next());
                break;
            case 3:
                System.out.print("Nuevo correo: ");
                usuarioActual.setCorreo(sc.next());
                break;
            case 4:
                System.out.print("Nueva edad: ");
                while (!sc.hasNextFloat()) {
                    System.out.println("Introduce una edad válida:");
                    sc.nextLine();
                }
                usuarioActual.setEdad(sc.nextFloat());
                break;
            case 5:
                return;
        }

        CrudUsuario.actualizarUsuario(usuarioActual);
    }

    // ===============================
    // Función del admin para modificar usuarios
    // ===============================
    public static void adminModificaUsuario() {
        ArrayList<Usuario> lista = CrudUsuario.obtenerUsuarios();

        for (Usuario u : lista) {
            System.out.println("ID: " + u.getId() + " | Usuario: " + u.getUsuario() + " | Rol: " + u.getRol());
        }

        System.out.print("Introduce el ID del usuario a modificar: ");
        int id = sc.nextInt();
        Usuario u = null;

        for (Usuario us : lista) {
            if (us.getId() == id) {
                u = us;
                break;
            }
        }

        if (u == null) {
            System.out.println("Usuario no encontrado.");
            return;
        }

        System.out.println("¿Qué deseas modificar?");
        System.out.println("1. Usuario\n2. Contraseña\n3. Correo\n4. Edad\n5. Rol\n6. Cancelar");
        int opcion = validarNumero(1, 6);

        switch (opcion) {
            case 1:
                System.out.print("Nuevo usuario: ");
                u.setUsuario(sc.next());
                break;
            case 2:
                System.out.print("Nueva contraseña: ");
                u.setContrasenia(sc.next());
                break;
            case 3:
                System.out.print("Nuevo correo: ");
                u.setCorreo(sc.next());
                break;
            case 4:
                System.out.print("Nueva edad: ");
                u.setEdad(sc.nextFloat());
                break;
            case 5:
                System.out.println("Selecciona nuevo rol:\n1. Admin\n2. Cliente");
                int rol = validarNumero(1, 2);
                u.setRol(rol == 1 ? "admin" : "cliente");
                break;
            case 6:
                return;
        }

        CrudUsuario.actualizarUsuario(u);
    }

    // ===============================
    // Validar solo opciones numéricas válidas
    // ===============================
    public static int validarNumero(int min, int max) {
        int op;
        while (true) {
            if (sc.hasNextInt()) {
                op = sc.nextInt();
                if (op >= min && op <= max) {
                    return op;
                } else {
                    System.out.println("Opción fuera de rango.");
                }
            } else {
                System.out.println("Solo números.");
                sc.next();
            }
        }
    }

    // ===============================
    // Getter del usuario actual logueado
    // ===============================
    public static Usuario getUsuarioActual() {
        return usuarioActual;
    }

    // ===============================
    // Cerrar sesión
    // ===============================
    public static void cerrarSesion() {
        usuarioActual = null;
        System.out.println("Sesión cerrada.");
        login(); // Regresar al login
    }
    // ===============================
// Submenú para gestionar usuarios (Admin)
// ===============================
public static void menuGestionUsuarios() {
    int opcion;
    do {
        System.out.println("\n--- Gestión de Usuarios ---");
        System.out.println("1. Ver usuarios");
        System.out.println("2. Editar usuario");
        System.out.println("3. Eliminar usuario");
        System.out.println("4. Agregar nuevo usuario");
        System.out.println("5. Volver al menú admin");

        opcion = validarNumero(1, 5);

        switch (opcion) {
            case 1:
                mostrarTodosLosUsuarios();
                break;
            case 2:
                adminModificaUsuario();
                break;
            case 3:
                eliminarUsuarioPorAdmin();
                break;
            case 4:
                registrarUsuarioPorAdmin();
                break;
            case 5:
                System.out.println("Volviendo al menú admin...");
                break;
        }
    } while (opcion != 5);
}

// ===============================
// Mostrar todos los usuarios (para ver o consultar)
// ===============================
public static void mostrarTodosLosUsuarios() {
    ArrayList<Usuario> lista = CrudUsuario.obtenerUsuarios();
    for (Usuario u : lista) {
        System.out.println("ID: " + u.getId() + " | Usuario: " + u.getUsuario() +
                " | Correo: " + u.getCorreo() + " | Edad: " + u.getEdad() + " | Rol: " + u.getRol());
    }
}

// ===============================
// Eliminar usuario
// ===============================
public static void eliminarUsuarioPorAdmin() {
    mostrarTodosLosUsuarios();
    System.out.print("Introduce el ID del usuario a eliminar: ");
    int id = validarNumero(1, Integer.MAX_VALUE);

    System.out.println("¿Estás seguro que deseas eliminar este usuario? (1. Sí | 2. No)");
    int confirmar = validarNumero(1, 2);
    if (confirmar == 1) {
        CrudUsuario.eliminarUsuario(id);
    } else {
        System.out.println("Eliminación cancelada.");
    }
}

// ===============================
// Registrar nuevo usuario como admin
// ===============================
public static void registrarUsuarioPorAdmin() {
    Usuario u = new Usuario();
    sc.nextLine(); // limpiar buffer

    System.out.print("Nombre de usuario: ");
    u.setUsuario(sc.nextLine());

    System.out.print("Correo: ");
    u.setCorreo(sc.nextLine());

    System.out.print("Contraseña: ");
    u.setContrasenia(sc.nextLine());

    System.out.print("Edad: ");
    while (!sc.hasNextFloat()) {
        System.out.println("Introduce una edad válida:");
        sc.nextLine();
    }
    u.setEdad(sc.nextFloat());

    System.out.println("Rol del nuevo usuario:");
    System.out.println("1. Admin\n2. Cliente");
    int rol = validarNumero(1, 2);
    u.setRol(rol == 1 ? "admin" : "cliente");

    CrudUsuario.insertarUsuario(u);
    System.out.println(" Usuario registrado por el administrador.");
}

}
