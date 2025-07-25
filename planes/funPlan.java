package planes;
import java.util.InputMismatchException;
import java.util.Scanner;



public class funPlan {
    static Scanner sc = new Scanner(System.in);
    static crudplanNut crud = new crudplanNut();

    public static void main(String[] args) {
        menuPlanNutricional();
    }
//////////// menu administradorPlan////
    public static void menuPlanNutricional() {
        int opcion;
        do {
            System.out.println("\n=== MENÚ PLANES NUTRICIONALES ===");
            System.out.println("1. Registrar nuevo plan");
            System.out.println("2. Ver todos los planes");
            System.out.println("3. Consultar plan por ID");
            System.out.println("4. Eliminar Plan");
            System.out.println("0. Salir");
            System.out.print("Selecciona una opción: ");

            try {
                opcion = sc.nextInt();
                sc.nextLine();

                switch (opcion) {
                    case 1:
                        altaPlan();
                        break;
                    case 2:
                        crud.consulta();
                        break;
                    case 3:
                        int idConsulta = pedirIdValido();
                        crud.buscarPorId(idConsulta);
                        break;
                    case 4:
                        bajaPlan();
                        break;
                    case 0:
                        System.out.println("¡Hasta luego!");
                        break;
                    default:
                        System.out.println("Opción inválida.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Por favor, ingresa solo números.");
                sc.nextLine();
                opcion = -1;
            }
        } while (opcion != 0);
    }

    public static int pedirIdValido() {
        int id = -1;
        while (true) {
            System.out.print("Ingresa el ID del plan a consultar (solo números): ");
            try {
                id = sc.nextInt();
                sc.nextLine();
                if (id > 0) return id;
                System.out.println("El ID debe ser un número positivo.");
            } catch (InputMismatchException e) {
                System.out.println("❌ Entrada inválida. Por favor, ingresa solo números enteros.");
                sc.nextLine();
            }
        }
    }

    public static void altaPlan() {
        String nombre, objetivo, recomendaciones, descripcion;
        char estatus = 'A';

        System.out.println("***************************");
        System.out.println("Alta de plan nutricional");

        nombre = leerCadenaNoVacia("Nombre: ");
        objetivo = leerCadenaNoVacia("Objetivo: ");
        recomendaciones = leerCadenaNoVacia("Recomendaciones: ");
        descripcion = leerCadenaNoVacia("Descripción: ");

        PlanNutricional nuevoPlan = new PlanNutricional(nombre, objetivo, recomendaciones, descripcion);
        nuevoPlan.setEstatus(estatus);
        crud.insertar(nuevoPlan);

        System.out.println("Plan nutricional registrado correctamente.");
        System.out.println(nuevoPlan.toString());
    }

    public static void bajaPlan() {
        System.out.println("***************************");
        System.out.println("Baja de plan nutricional");

        int id = pedirIdValido("ID del plan (solo números): ");
        crud.bajaLogica(id);
        System.out.println("Plan dado de baja correctamente.");
    }

    public static void actualizarDescripcion() {
        System.out.println("***************************");
        System.out.println("Actualizar descripción");

        int id = pedirIdValido("ID del plan: ");
        String descripcion = leerCadenaNoVacia("Nueva descripción: ");
        
        crud.actualizarDescripcion(id, descripcion);
    }

    public static void verPlanes() {
        System.out.println("***************************");
        System.out.println("Planes nutricionales activos:");
        crud.consulta();
    }

    private static String leerCadenaNoVacia(String mensaje) {
        String input;
        do {
            System.out.print(mensaje);
            input = sc.nextLine();
        } while (input.trim().isEmpty());
        return input;
    }

    private static int pedirIdValido(String mensaje) {
        while (true) {
            try {
                System.out.print(mensaje);
                int id = sc.nextInt();
                sc.nextLine();
                if (id > 0) return id;
                System.out.println("El ID debe ser positivo.");
            } catch (InputMismatchException e) {
                System.out.println("Error: Debe ingresar un número entero.");
                sc.nextLine();
            }
        }
    }
}