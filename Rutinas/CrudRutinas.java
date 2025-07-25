package Rutinas;
// ===============================
// Clase CrudRutinas.java
// ===============================
// Encargada de conectarse a la base de datos y realizar operaciones CRUD para rutinas

import java.sql.*;
import java.util.ArrayList;

import Conexion.conn;

public class CrudRutinas {

    // ===============================
    // Insertar nueva rutina
    // ===============================
    public static int insertarRutina(Rutinas r) {
        int idGenerado = -1;
        try (Connection con = conn.conectarBD()) {
            String sql = "INSERT INTO rutinas (nombreRutina, descripcionRutina, objetivoRutina, dificultadRutina, diasPorSemana, horasEstimadas) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, r.getNombreRutina());
            ps.setString(2, r.getDescripcionRutina());
            ps.setString(3, r.getObjetivoRutina());
            ps.setString(4, r.getDificultadRutina());
            ps.setInt(5, r.getDiasPorSemana());
            ps.setDouble(6, r.getHorasEstimadas());
            ps.executeUpdate();
            
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                idGenerado = rs.getInt(1);
            }
            System.out.println("Rutina registrada exitosamente.");
        } catch (SQLException e) {
            System.err.println("Error al registrar rutina: " + e.getMessage());
        }
        return idGenerado;
    }

    // ===============================
    // Insertar ejercicio para rutina
    // ===============================
    public static void insertarEjercicioRutina(EjercicioRutina e) {
        try (Connection con = conn.conectarBD()) {
            String sql = "INSERT INTO ejercicios_rutina (rutinaId, diaSemana, nombreEjercicio, series, repeticiones) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, e.getRutinaId());
            ps.setString(2, e.getDiaSemana());
            ps.setString(3, e.getNombreEjercicio());
            ps.setInt(4, e.getSeries());
            ps.setInt(5, e.getRepeticiones());
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.err.println("Error al registrar ejercicio: " + ex.getMessage());
        }
    }

    // ===============================
    // Obtener todas las rutinas
    // ===============================
    public static ArrayList<Rutinas> obtenerRutinas() {
        ArrayList<Rutinas> lista = new ArrayList<>();
        try (Connection con = conn.conectarBD()) {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM rutinas");
            while (rs.next()) {
                Rutinas r = new Rutinas(
                    rs.getInt("idRutina"),
                    rs.getString("nombreRutina"),
                    rs.getString("descripcionRutina"),
                    rs.getString("objetivoRutina"),
                    rs.getString("dificultadRutina"),
                    rs.getInt("diasPorSemana"),
                    rs.getDouble("horasEstimadas")
                );
                lista.add(r);
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener rutinas: " + e.getMessage());
        }
        return lista;
    }

    // ===============================
    // Obtener rutinas por objetivo
    // ===============================
    public static ArrayList<Rutinas> obtenerRutinasPorObjetivo(String objetivo) {
        ArrayList<Rutinas> lista = new ArrayList<>();
        try (Connection con = conn.conectarBD()) {
            String sql = "SELECT * FROM rutinas WHERE objetivoRutina = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, objetivo);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Rutinas r = new Rutinas(
                    rs.getInt("idRutina"),
                    rs.getString("nombreRutina"),
                    rs.getString("descripcionRutina"),
                    rs.getString("objetivoRutina"),
                    rs.getString("dificultadRutina"),
                    rs.getInt("diasPorSemana"),
                    rs.getDouble("horasEstimadas")
                );
                lista.add(r);
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener rutinas por objetivo: " + e.getMessage());
        }
        return lista;
    }

    // ===============================
    // Obtener rutinas por dificultad
    // ===============================
    public static ArrayList<Rutinas> obtenerRutinasPorDificultad(String dificultad) {
        ArrayList<Rutinas> lista = new ArrayList<>();
        try (Connection con = conn.conectarBD()) {
            String sql = "SELECT * FROM rutinas WHERE dificultadRutina = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, dificultad);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Rutinas r = new Rutinas(
                    rs.getInt("idRutina"),
                    rs.getString("nombreRutina"),
                    rs.getString("descripcionRutina"),
                    rs.getString("objetivoRutina"),
                    rs.getString("dificultadRutina"),
                    rs.getInt("diasPorSemana"),
                    rs.getDouble("horasEstimadas")
                );
                lista.add(r);
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener rutinas por dificultad: " + e.getMessage());
        }
        return lista;
    }

    // ===============================
    // Obtener rutinas por días por semana
    // ===============================
    public static ArrayList<Rutinas> obtenerRutinasPorDias(int dias) {
        ArrayList<Rutinas> lista = new ArrayList<>();
        try (Connection con = conn.conectarBD()) {
            String sql = "SELECT * FROM rutinas WHERE diasPorSemana = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, dias);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Rutinas r = new Rutinas(
                    rs.getInt("idRutina"),
                    rs.getString("nombreRutina"),
                    rs.getString("descripcionRutina"),
                    rs.getString("objetivoRutina"),
                    rs.getString("dificultadRutina"),
                    rs.getInt("diasPorSemana"),
                    rs.getDouble("horasEstimadas")
                );
                lista.add(r);
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener rutinas por días: " + e.getMessage());
        }
        return lista;
    }

    // ===============================
    // Buscar rutina por nombre
    // ===============================
    public static ArrayList<Rutinas> buscarRutinaPorNombre(String nombre) {
        ArrayList<Rutinas> lista = new ArrayList<>();
        try (Connection con = conn.conectarBD()) {
            String sql = "SELECT * FROM rutinas WHERE nombreRutina LIKE ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, "%" + nombre + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Rutinas r = new Rutinas(
                    rs.getInt("idRutina"),
                    rs.getString("nombreRutina"),
                    rs.getString("descripcionRutina"),
                    rs.getString("objetivoRutina"),
                    rs.getString("dificultadRutina"),
                    rs.getInt("diasPorSemana"),
                    rs.getDouble("horasEstimadas")
                );
                lista.add(r);
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar rutina por nombre: " + e.getMessage());
        }
        return lista;
    }

    // ===============================
    // Obtener ejercicios de una rutina
    // ===============================
    public static ArrayList<EjercicioRutina> obtenerEjerciciosRutina(int rutinaId) {
        ArrayList<EjercicioRutina> lista = new ArrayList<>();
        try (Connection con = conn.conectarBD()) {
            String sql = "SELECT * FROM ejercicios_rutina WHERE rutinaId = ? ORDER BY diaSemana";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, rutinaId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                EjercicioRutina e = new EjercicioRutina(
                    rs.getInt("id"),
                    rs.getInt("rutinaId"),
                    rs.getString("diaSemana"),
                    rs.getString("nombreEjercicio"),
                    rs.getInt("series"),
                    rs.getInt("repeticiones")
                );
                lista.add(e);
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener ejercicios de rutina: " + e.getMessage());
        }
        return lista;
    }

    // ===============================
    // Eliminar rutina por ID
    // ===============================
    public static void eliminarRutina(int id) {
        try (Connection con = conn.conectarBD()) {
            // Primero eliminamos los ejercicios asociados
            String sqlEjercicios = "DELETE FROM ejercicios_rutina WHERE rutinaId = ?";
            PreparedStatement psEjercicios = con.prepareStatement(sqlEjercicios);
            psEjercicios.setInt(1, id);
            psEjercicios.executeUpdate();
            
            // Luego eliminamos la rutina
            String sqlRutina = "DELETE FROM rutinas WHERE idRutina = ?";
            PreparedStatement psRutina = con.prepareStatement(sqlRutina);
            psRutina.setInt(1, id);
            psRutina.executeUpdate();
            
            System.out.println("Rutina eliminada correctamente.");
        } catch (SQLException e) {
            System.err.println("Error al eliminar rutina: " + e.getMessage());
        }
    }

    // ===============================
    // Actualizar rutina
    // ===============================
    public static void actualizarRutina(Rutinas r) {
        try (Connection con = conn.conectarBD()) {
            String sql = "UPDATE rutinas SET nombreRutina=?, descripcionRutina=?, objetivoRutina=?, dificultadRutina=?, diasPorSemana=?, horasEstimadas=? WHERE idRutina=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, r.getNombreRutina());
            ps.setString(2, r.getDescripcionRutina());
            ps.setString(3, r.getObjetivoRutina());
            ps.setString(4, r.getDificultadRutina());
            ps.setInt(5, r.getDiasPorSemana());
            ps.setDouble(6, r.getHorasEstimadas());
            ps.setInt(7, r.getIdRutina());
            ps.executeUpdate();
            System.out.println("Rutina actualizada correctamente.");
        } catch (SQLException e) {
            System.err.println("Error al actualizar rutina: " + e.getMessage());
        }
    }

    // ===============================
    // Eliminar ejercicios de una rutina por día
    // ===============================
    public static void eliminarEjerciciosPorDia(int rutinaId, String dia) {
        try (Connection con = conn.conectarBD()) {
            String sql = "DELETE FROM ejercicios_rutina WHERE rutinaId = ? AND diaSemana = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, rutinaId);
            ps.setString(2, dia);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al eliminar ejercicios: " + e.getMessage());
        }
    }
}