package Rutinas;
// ===============================
// Clase FuncionesRutinas.java
// ===============================
// Contiene todas las funciones de interacción con el usuario para rutinas

import java.util.ArrayList;
import java.util.Scanner;

import Usuario.FuncionesUsuario;

public class FuncionesRutinas {
    static Scanner sc = new Scanner(System.in);
    
    // ===============================
    // Menú principal de rutinas
    // ===============================
    public static void menuRutinas() {
        int opcion;
        do {
            System.out.println("\n--- MENÚ RUTINAS ---");
            System.out.println("1. Ver todas las rutinas");
            System.out.println("2. Buscar por objetivo");
            System.out.println("3. Buscar por dificultad");
            System.out.println("4. Buscar por días a la semana");
            System.out.println("5. Buscar por nombre");
            System.out.println("6. Volver al menú principal");
            
            opcion = FuncionesUsuario.validarNumero(1, 6);
            
            switch (opcion) {
                case 1:
                    verTodasLasRutinas();
                    break;
                case 2:
                    buscarPorObjetivo();
                    break;
                case 3:
                    buscarPorDificultad();
                    break;
                case 4:
                    buscarPorDias();
                    break;
                case 5:
                    buscarPorNombre();
                    break;
                case 6:
                    System.out.println("Volviendo al menú principal...");
                    break;
            }
        } while (opcion != 6);
    }
    
    // ===============================
    // Ver todas las rutinas
    // ===============================
    public static void verTodasLasRutinas() {
        ArrayList<Rutinas> rutinas = CrudRutinas.obtenerRutinas();
        if (rutinas.isEmpty()) {
            System.out.println("No hay rutinas disponibles.");
            return;
        }
        
        System.out.println("\n--- TODAS LAS RUTINAS ---");
        for (Rutinas r : rutinas) {
            System.out.println(r);
        }
        
        verDetalleRutina();
    }
    
    // ===============================
    // Buscar por objetivo
    // ===============================
    public static void buscarPorObjetivo() {
        System.out.println("\nSeleccione el objetivo:");
        System.out.println("1. Tonificar");
        System.out.println("2. Cardio");
        System.out.println("3. Perder grasa");
        System.out.println("4. Aumentar masa muscular");
        System.out.println("5. Resistencia");
        
        int opcion = FuncionesUsuario.validarNumero(1, 5);
        String objetivo = "";
        
        switch (opcion) {
            case 1: objetivo = "Tonificar"; break;
            case 2: objetivo = "Cardio"; break;
            case 3: objetivo = "Perder grasa"; break;
            case 4: objetivo = "Aumentar masa muscular"; break;
            case 5: objetivo = "Resistencia"; break;
        }
        
        ArrayList<Rutinas> rutinas = CrudRutinas.obtenerRutinasPorObjetivo(objetivo);
        if (rutinas.isEmpty()) {
            System.out.println("No hay rutinas con ese objetivo.");
            return;
        }
        
        System.out.println("\n--- RUTINAS DE " + objetivo.toUpperCase() + " ---");
        for (Rutinas r : rutinas) {
            System.out.println(r);
        }
        
        verDetalleRutina();
    }
    
    // ===============================
    // Buscar por dificultad
    // ===============================
    public static void buscarPorDificultad() {
        System.out.println("\nSeleccione la dificultad:");
        System.out.println("1. Baja");
        System.out.println("2. Estándar");
        System.out.println("3. Avanzada");
        
        int opcion = FuncionesUsuario.validarNumero(1, 3);
        String dificultad = "";
        
        switch (opcion) {
            case 1: dificultad = "Baja"; break;
            case 2: dificultad = "Estándar"; break;
            case 3: dificultad = "Avanzada"; break;
        }
        
        ArrayList<Rutinas> rutinas = CrudRutinas.obtenerRutinasPorDificultad(dificultad);
        if (rutinas.isEmpty()) {
            System.out.println("No hay rutinas con esa dificultad.");
            return;
        }
        
        System.out.println("\n--- RUTINAS DE DIFICULTAD " + dificultad.toUpperCase() + " ---");
        for (Rutinas r : rutinas) {
            System.out.println(r);
        }
        
        verDetalleRutina();
    }
    
    // ===============================
    // Buscar por días a la semana
    // ===============================
    public static void buscarPorDias() {
        System.out.print("\nIngrese el número de días a la semana (1-7): ");
        int dias = FuncionesUsuario.validarNumero(1, 7);
        
        ArrayList<Rutinas> rutinas = CrudRutinas.obtenerRutinasPorDias(dias);
        if (rutinas.isEmpty()) {
            System.out.println("No hay rutinas con esa cantidad de días.");
            return;
        }
        
        System.out.println("\n--- RUTINAS DE " + dias + " DÍAS POR SEMANA ---");
        for (Rutinas r : rutinas) {
            System.out.println(r);
        }
        
        verDetalleRutina();
    }
    
    // ===============================
    // Buscar por nombre
    // ===============================
    public static void buscarPorNombre() {
        System.out.print("\nIngrese el nombre o parte del nombre de la rutina: ");
        sc.nextLine(); // Limpiar buffer
        String nombre = sc.nextLine();
        
        ArrayList<Rutinas> rutinas = CrudRutinas.buscarRutinaPorNombre(nombre);
        if (rutinas.isEmpty()) {
            System.out.println("No se encontraron rutinas con ese nombre.");
            return;
        }
        
        System.out.println("\n--- RESULTADOS DE BÚSQUEDA ---");
        for (Rutinas r : rutinas) {
            System.out.println(r);
        }
        
        verDetalleRutina();
    }
    
    // ===============================
    // Ver detalle de una rutina específica
    // ===============================
    public static void verDetalleRutina() {
        System.out.println("\n¿Desea ver los detalles de una rutina? (1.Sí / 2.No)");
        int opcion = FuncionesUsuario.validarNumero(1, 2);
        
        if (opcion == 1) {
            System.out.print("Ingrese el ID de la rutina: ");
            int id = FuncionesUsuario.validarNumero(1, Integer.MAX_VALUE);
            
            mostrarDetalleRutina(id);
        }
    }
    
    // ===============================
    // Mostrar detalle completo de una rutina
    // ===============================
    public static void mostrarDetalleRutina(int rutinaId) {
        // Obtener la rutina
        ArrayList<Rutinas> todas = CrudRutinas.obtenerRutinas();
        Rutinas rutinaSeleccionada = null;
        
        for (Rutinas r : todas) {
            if (r.getIdRutina() == rutinaId) {
                rutinaSeleccionada = r;
                break;
            }
        }
        
        if (rutinaSeleccionada == null) {
            System.out.println("Rutina no encontrada.");
            return;
        }
        
        // Mostrar información básica
        System.out.println("\n--- DETALLES DE LA RUTINA ---");
        System.out.println("Nombre: " + rutinaSeleccionada.getNombreRutina());
        System.out.println("Objetivo: " + rutinaSeleccionada.getObjetivoRutina());
        System.out.println("Dificultad: " + rutinaSeleccionada.getDificultadRutina());
        System.out.println("Días por semana: " + rutinaSeleccionada.getDiasPorSemana());
        System.out.println("Duración estimada: " + rutinaSeleccionada.getHorasEstimadas() + " horas");
        System.out.println("Descripción: " + rutinaSeleccionada.getDescripcionRutina());
        
        // Obtener y mostrar los ejercicios por día
        ArrayList<EjercicioRutina> ejercicios = CrudRutinas.obtenerEjerciciosRutina(rutinaId);
        
        if (ejercicios.isEmpty()) {
            System.out.println("\nEsta rutina no tiene ejercicios asignados.");
            return;
        }
        
        System.out.println("\n--- EJERCICIOS POR DÍA ---");
        
        String[] dias = {"Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado", "Domingo"};
        
        for (String dia : dias) {
            boolean tieneEjercicios = false;
            
            // Verificar si hay ejercicios para este día
            for (EjercicioRutina e : ejercicios) {
                if (e.getDiaSemana().equalsIgnoreCase(dia)) {
                    if (!tieneEjercicios) {
                        System.out.println("\n" + dia.toUpperCase() + ":");
                        tieneEjercicios = true;
                    }
                    System.out.println("- " + e);
                }
            }
        }
        
        // Si es admin, mostrar opciones adicionales
        if (FuncionesUsuario.getUsuarioActual() != null && 
            FuncionesUsuario.getUsuarioActual().getRol().equalsIgnoreCase("admin")) {
            System.out.println("\nOpciones de administrador:");
            System.out.println("1. Editar rutina");
            System.out.println("2. Eliminar rutina");
            System.out.println("3. Volver");
            
            int op = FuncionesUsuario.validarNumero(1, 3);
            
            switch (op) {
                case 1:
                    menuEditarRutina(rutinaSeleccionada);
                    break;
                case 2:
                    System.out.println("¿Está seguro que desea eliminar esta rutina? (1.Sí / 2.No)");
                    int confirmar = FuncionesUsuario.validarNumero(1, 2);
                    if (confirmar == 1) {
                        CrudRutinas.eliminarRutina(rutinaId);
                        System.out.println("Rutina eliminada correctamente.");
                    }
                    break;
                case 3:
                    break;
            }
        }
    }
    
    // ===============================
    // Menú de gestión de rutinas para admin
    // ===============================
    public static void menuGestionRutinas() {
        int opcion;
        do {
            System.out.println("\n--- GESTIÓN DE RUTINAS (ADMIN) ---");
            System.out.println("1. Ver todas las rutinas");
            System.out.println("2. Agregar nueva rutina");
            System.out.println("3. Editar rutina existente");
            System.out.println("4. Eliminar rutina");
            System.out.println("5. Volver al menú admin");
            
            opcion = FuncionesUsuario.validarNumero(1, 5);
            
            switch (opcion) {
                case 1:
                    verTodasLasRutinasAdmin();
                    break;
                case 2:
                    agregarNuevaRutina();
                    break;
                case 3:
                    editarRutinaExistente();
                    break;
                case 4:
                    eliminarRutinaAdmin();
                    break;
                case 5:
                    System.out.println("Volviendo al menú admin...");
                    break;
            }
        } while (opcion != 5);
    }
    
    // ===============================
    // Ver todas las rutinas (versión admin)
    // ===============================
    public static void verTodasLasRutinasAdmin() {
        ArrayList<Rutinas> rutinas = CrudRutinas.obtenerRutinas();
        if (rutinas.isEmpty()) {
            System.out.println("No hay rutinas disponibles.");
            return;
        }
        
        System.out.println("\n--- TODAS LAS RUTINAS ---");
        for (Rutinas r : rutinas) {
            System.out.println(r);
        }
        
        System.out.println("\n¿Desea ver los detalles de una rutina? (1.Sí / 2.No)");
        int opcion = FuncionesUsuario.validarNumero(1, 2);
        
        if (opcion == 1) {
            System.out.print("Ingrese el ID de la rutina: ");
            int id = FuncionesUsuario.validarNumero(1, Integer.MAX_VALUE);
            mostrarDetalleRutina(id);
        }
    }
    
    // ===============================
    // Agregar nueva rutina
    // ===============================
    public static void agregarNuevaRutina() {
        Rutinas nuevaRutina = new Rutinas();
        sc.nextLine(); // Limpiar buffer
        
        System.out.println("\n--- AGREGAR NUEVA RUTINA ---");
        
        // Nombre
        System.out.print("Nombre de la rutina: ");
        nuevaRutina.setNombreRutina(sc.nextLine());
        
        // Descripción
        System.out.print("Descripción: ");
        nuevaRutina.setDescripcionRutina(sc.nextLine());
        
        // Objetivo
        System.out.println("Seleccione el objetivo:");
        System.out.println("1. Tonificar");
        System.out.println("2. Cardio");
        System.out.println("3. Perder grasa");
        System.out.println("4. Aumentar masa muscular");
        System.out.println("5. Resistencia");
        int opObjetivo = FuncionesUsuario.validarNumero(1, 5);
        switch (opObjetivo) {
            case 1: nuevaRutina.setObjetivoRutina("Tonificar"); break;
            case 2: nuevaRutina.setObjetivoRutina("Cardio"); break;
            case 3: nuevaRutina.setObjetivoRutina("Perder grasa"); break;
            case 4: nuevaRutina.setObjetivoRutina("Aumentar masa muscular"); break;
            case 5: nuevaRutina.setObjetivoRutina("Resistencia"); break;
        }
        
        // Dificultad
        System.out.println("Seleccione la dificultad:");
        System.out.println("1. Baja");
        System.out.println("2. Estándar");
        System.out.println("3. Avanzada");
        int opDificultad = FuncionesUsuario.validarNumero(1, 3);
        switch (opDificultad) {
            case 1: nuevaRutina.setDificultadRutina("Baja"); break;
            case 2: nuevaRutina.setDificultadRutina("Estándar"); break;
            case 3: nuevaRutina.setDificultadRutina("Avanzada"); break;
        }
        
        // Días por semana
        System.out.print("Días por semana (1-7): ");
        nuevaRutina.setDiasPorSemana(FuncionesUsuario.validarNumero(1, 7));
        
        // Horas estimadas
        System.out.print("Duración estimada por sesión (horas): ");
        while (!sc.hasNextDouble()) {
            System.out.println("Por favor ingrese un número válido.");
            sc.next();
        }
        nuevaRutina.setHorasEstimadas(sc.nextDouble());
        
        // Insertar la rutina en la base de datos
        int idRutina = CrudRutinas.insertarRutina(nuevaRutina);
        
        if (idRutina != -1) {
            System.out.println("\n¿Desea agregar ejercicios a esta rutina? (1.Sí / 2.No)");
            int agregarEjercicios = FuncionesUsuario.validarNumero(1, 2);
            
            if (agregarEjercicios == 1) {
                agregarEjerciciosARutina(idRutina);
            }
            
            System.out.println("Rutina agregada exitosamente con ID: " + idRutina);
        } else {
            System.out.println("Error al agregar la rutina.");
        }
    }
    
    // ===============================
    // Agregar ejercicios a una rutina
    // ===============================
    public static void agregarEjerciciosARutina(int rutinaId) {
        String[] dias = {"Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado", "Domingo"};
        
        for (String dia : dias) {
            System.out.println("\n¿Desea agregar ejercicios para el " + dia + "? (1.Sí / 2.No)");
            int agregarDia = FuncionesUsuario.validarNumero(1, 2);
            
            if (agregarDia == 1) {
                boolean continuar = true;
                while (continuar) {
                    EjercicioRutina ejercicio = new EjercicioRutina();
                    ejercicio.setRutinaId(rutinaId);
                    ejercicio.setDiaSemana(dia);
                    
                    sc.nextLine(); // Limpiar buffer
                    System.out.print("Nombre del ejercicio: ");
                    ejercicio.setNombreEjercicio(sc.nextLine());
                    
                    System.out.print("Número de series: ");
                    ejercicio.setSeries(FuncionesUsuario.validarNumero(1, Integer.MAX_VALUE));
                    
                    System.out.print("Repeticiones por serie: ");
                    ejercicio.setRepeticiones(FuncionesUsuario.validarNumero(1, Integer.MAX_VALUE));
                    
                    // Insertar ejercicio
                    CrudRutinas.insertarEjercicioRutina(ejercicio);
                    
                    System.out.println("Ejercicio agregado correctamente.");
                    System.out.println("¿Desea agregar otro ejercicio para el " + dia + "? (1.Sí / 2.No)");
                    continuar = FuncionesUsuario.validarNumero(1, 2) == 1;
                }
            }
        }
    } // ===============================
    // Menú para editar rutina (completo)
    // ===============================
    public static void menuEditarRutina(Rutinas rutina) {
        int opcion;
        do {
            System.out.println("\n--- EDITAR RUTINA: " + rutina.getNombreRutina() + " ---");
            System.out.println("1. Editar información básica");
            System.out.println("2. Gestionar ejercicios");
            System.out.println("3. Ver rutina actual");
            System.out.println("4. Guardar cambios y volver");
            System.out.println("5. Volver sin guardar");
            
            opcion = FuncionesUsuario.validarNumero(1, 5);
            
            switch (opcion) {
                case 1:
                    editarInformacionBasica(rutina);
                    break;
                case 2:
                    menuGestionEjercicios(rutina);
                    break;
                case 3:
                    mostrarDetalleRutina(rutina.getIdRutina());
                    break;
                case 4:
                    CrudRutinas.actualizarRutina(rutina);
                    System.out.println("Cambios guardados correctamente.");
                    return;
                case 5:
                    System.out.println("Cambios no guardados.");
                    return;
            }
        } while (true);
    }
    
    // ===============================
    // Editar información básica de rutina
    // ===============================
    public static void editarInformacionBasica(Rutinas rutina) {
        System.out.println("\n--- EDITAR INFORMACIÓN BÁSICA ---");
        System.out.println("Deje en blanco los campos que no desea cambiar");
        
        sc.nextLine(); // Limpiar buffer
        
        // Nombre
        System.out.print("Nombre actual: " + rutina.getNombreRutina() + "\nNuevo nombre: ");
        String nuevoNombre = sc.nextLine();
        if (!nuevoNombre.isEmpty()) {
            rutina.setNombreRutina(nuevoNombre);
        }
        
        // Descripción
        System.out.print("\nDescripción actual: " + rutina.getDescripcionRutina() + "\nNueva descripción: ");
        String nuevaDesc = sc.nextLine();
        if (!nuevaDesc.isEmpty()) {
            rutina.setDescripcionRutina(nuevaDesc);
        }
        
        // Objetivo
        System.out.println("\nObjetivo actual: " + rutina.getObjetivoRutina());
        System.out.println("¿Cambiar objetivo? (1.Sí / 2.No)");
        if (FuncionesUsuario.validarNumero(1, 2) == 1) {
            System.out.println("1. Tonificar\n2. Cardio\n3. Perder grasa\n4. Aumentar masa muscular\n5. Resistencia");
            int op = FuncionesUsuario.validarNumero(1, 5);
            String[] objetivos = {"Tonificar", "Cardio", "Perder grasa", "Aumentar masa muscular", "Resistencia"};
            rutina.setObjetivoRutina(objetivos[op-1]);
        }
        
        // Dificultad
        System.out.println("\nDificultad actual: " + rutina.getDificultadRutina());
        System.out.println("¿Cambiar dificultad? (1.Sí / 2.No)");
        if (FuncionesUsuario.validarNumero(1, 2) == 1) {
            System.out.println("1. Baja\n2. Estándar\n3. Avanzada");
            int op = FuncionesUsuario.validarNumero(1, 3);
            String[] dificultades = {"Baja", "Estándar", "Avanzada"};
            rutina.setDificultadRutina(dificultades[op-1]);
        }
        
        // Días por semana
        System.out.print("\nDías actuales: " + rutina.getDiasPorSemana() + "\nNuevos días (1-7): ");
        String diasInput = sc.nextLine();
        if (!diasInput.isEmpty()) {
            try {
                int dias = Integer.parseInt(diasInput);
                if (dias >= 1 && dias <= 7) {
                    rutina.setDiasPorSemana(dias);
                }
            } catch (NumberFormatException e) {
                System.out.println("Número de días no válido. No se cambió.");
            }
        }
        
        // Horas estimadas
        System.out.print("\nDuración actual: " + rutina.getHorasEstimadas() + " horas\nNueva duración: ");
        String horasInput = sc.nextLine();
        if (!horasInput.isEmpty()) {
            try {
                double horas = Double.parseDouble(horasInput);
                rutina.setHorasEstimadas(horas);
            } catch (NumberFormatException e) {
                System.out.println("Valor no válido. No se cambió.");
            }
        }
    }
    
    // ===============================
    // Menú para gestionar ejercicios
    // ===============================
    public static void menuGestionEjercicios(Rutinas rutina) {
        int opcion;
        do {
            System.out.println("\n--- GESTIÓN DE EJERCICIOS ---");
            System.out.println("1. Agregar ejercicios a un día");
            System.out.println("2. Eliminar ejercicios de un día");
            System.out.println("3. Ver todos los ejercicios");
            System.out.println("4. Volver");
            
            opcion = FuncionesUsuario.validarNumero(1, 4);
            
            switch (opcion) {
                case 1:
                    agregarEjerciciosADia(rutina.getIdRutina());
                    break;
                case 2:
                    eliminarEjerciciosDeDia(rutina.getIdRutina());
                    break;
                case 3:
                    mostrarEjerciciosRutina(rutina.getIdRutina());
                    break;
                case 4:
                    return;
            }
        } while (true);
    }
    
    // ===============================
    // Agregar ejercicios a un día específico
    // ===============================
    public static void agregarEjerciciosADia(int rutinaId) {
        String[] dias = {"Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado", "Domingo"};
        
        System.out.println("\nSeleccione el día:");
        for (int i = 0; i < dias.length; i++) {
            System.out.println((i+1) + ". " + dias[i]);
        }
        int diaOp = FuncionesUsuario.validarNumero(1, dias.length);
        String diaSeleccionado = dias[diaOp-1];
        
        boolean continuar = true;
        while (continuar) {
            EjercicioRutina ejercicio = new EjercicioRutina();
            ejercicio.setRutinaId(rutinaId);
            ejercicio.setDiaSemana(diaSeleccionado);
            
            sc.nextLine(); // Limpiar buffer
            System.out.print("Nombre del ejercicio: ");
            ejercicio.setNombreEjercicio(sc.nextLine());
            
            System.out.print("Número de series: ");
            ejercicio.setSeries(FuncionesUsuario.validarNumero(1, Integer.MAX_VALUE));
            
            System.out.print("Repeticiones por serie: ");
            ejercicio.setRepeticiones(FuncionesUsuario.validarNumero(1, Integer.MAX_VALUE));
            
            CrudRutinas.insertarEjercicioRutina(ejercicio);
            System.out.println("Ejercicio agregado correctamente.");
            
            System.out.println("¿Agregar otro ejercicio para el " + diaSeleccionado + "? (1.Sí / 2.No)");
            continuar = FuncionesUsuario.validarNumero(1, 2) == 1;
        }
    }
    
    // ===============================
    // Eliminar ejercicios de un día
    // ===============================
    public static void eliminarEjerciciosDeDia(int rutinaId) {
        String[] dias = {"Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado", "Domingo"};
        
        System.out.println("\nSeleccione el día:");
        for (int i = 0; i < dias.length; i++) {
            System.out.println((i+1) + ". " + dias[i]);
        }
        int diaOp = FuncionesUsuario.validarNumero(1, dias.length);
        String diaSeleccionado = dias[diaOp-1];
        
        // Mostrar ejercicios del día seleccionado
        ArrayList<EjercicioRutina> ejercicios = CrudRutinas.obtenerEjerciciosRutina(rutinaId);
        boolean tieneEjercicios = false;
        
        System.out.println("\nEjercicios para " + diaSeleccionado + ":");
        for (EjercicioRutina e : ejercicios) {
            if (e.getDiaSemana().equalsIgnoreCase(diaSeleccionado)) {
                System.out.println("- " + e);
                tieneEjercicios = true;
            }
        }
        
        if (!tieneEjercicios) {
            System.out.println("No hay ejercicios para este día.");
            return;
        }
        
        System.out.println("¿Está seguro que desea eliminar TODOS los ejercicios del " + diaSeleccionado + "? (1.Sí / 2.No)");
        int confirmar = FuncionesUsuario.validarNumero(1, 2);
        if (confirmar == 1) {
            CrudRutinas.eliminarEjerciciosPorDia(rutinaId, diaSeleccionado);
            System.out.println("Ejercicios eliminados correctamente.");
        }
    }
    
    // ===============================
    // Mostrar todos los ejercicios de una rutina
    // ===============================
    public static void mostrarEjerciciosRutina(int rutinaId) {
        ArrayList<EjercicioRutina> ejercicios = CrudRutinas.obtenerEjerciciosRutina(rutinaId);
        
        if (ejercicios.isEmpty()) {
            System.out.println("Esta rutina no tiene ejercicios asignados.");
            return;
        }
        
        System.out.println("\n--- EJERCICIOS DE LA RUTINA ---");
        String[] dias = {"Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado", "Domingo"};
        
        for (String dia : dias) {
            boolean tieneEjercicios = false;
            
            for (EjercicioRutina e : ejercicios) {
                if (e.getDiaSemana().equalsIgnoreCase(dia)) {
                    if (!tieneEjercicios) {
                        System.out.println("\n" + dia.toUpperCase() + ":");
                        tieneEjercicios = true;
                    }
                    System.out.println("- " + e);
                }
            }
        }
    }
    
    // ===============================
    // Eliminar rutina (admin)
    // ===============================
    public static void eliminarRutinaAdmin() {
        ArrayList<Rutinas> rutinas = CrudRutinas.obtenerRutinas();
        if (rutinas.isEmpty()) {
            System.out.println("No hay rutinas disponibles para eliminar.");
            return;
        }
        
        System.out.println("\n--- RUTINAS DISPONIBLES ---");
        for (Rutinas r : rutinas) {
            System.out.println(r);
        }
        
        System.out.print("\nIngrese el ID de la rutina a eliminar: ");
        int id = FuncionesUsuario.validarNumero(1, Integer.MAX_VALUE);
        
        // Verificar que la rutina existe
        boolean existe = false;
        for (Rutinas r : rutinas) {
            if (r.getIdRutina() == id) {
                existe = true;
                break;
            }
        }
        
        if (!existe) {
            System.out.println("No existe una rutina con ese ID.");
            return;
        }
        
        System.out.println("¿Está seguro que desea eliminar esta rutina? (1.Sí / 2.No)");
        int confirmar = FuncionesUsuario.validarNumero(1, 2);
        if (confirmar == 1) {
            CrudRutinas.eliminarRutina(id);
            System.out.println("Rutina eliminada correctamente.");
        }
    }
    
    // ===============================
    // Editar rutina existente (menú admin)
    // ===============================
    public static void editarRutinaExistente() {
        ArrayList<Rutinas> rutinas = CrudRutinas.obtenerRutinas();
        if (rutinas.isEmpty()) {
            System.out.println("No hay rutinas disponibles para editar.");
            return;
        }
        
        System.out.println("\n--- RUTINAS DISPONIBLES ---");
        for (Rutinas r : rutinas) {
            System.out.println(r);
        }
        
        System.out.print("\nIngrese el ID de la rutina a editar: ");
        int id = FuncionesUsuario.validarNumero(1, Integer.MAX_VALUE);
        
        // Buscar la rutina
        Rutinas rutinaAEditar = null;
        for (Rutinas r : rutinas) {
            if (r.getIdRutina() == id) {
                rutinaAEditar = r;
                break;
            }
        }
        
        if (rutinaAEditar == null) {
            System.out.println("No existe una rutina con ese ID.");
            return;
        }
        
        menuEditarRutina(rutinaAEditar);
    }
}