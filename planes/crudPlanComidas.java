package planes;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import Conexion.conn;

public class crudPlanComidas {

    public void consulta() {
        verTodasLasComidas();
    }

    // Ver todas las comidas registradas
    public void verTodasLasComidas() {
        String sql = "SELECT * FROM plan_comidas";

        try (Connection con = conn.conectarBD();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            if (!rs.isBeforeFirst()) {
                System.out.println("No hay comidas registradas aún.");
                return;
            }

            while (rs.next()) {
                PlanComidas comida = extraerComida(rs);
                System.out.println(comida);
                System.out.println("--------------------------------------");
            }

        } catch (SQLException e) {
            System.out.println("Error al consultar las comidas: " + e.getMessage());
        }
    }

    // Buscar comidas por ID de plan
    public void buscarComidasPorIdPlan(int idPlan) {
        String sql = "SELECT * FROM plan_comidas WHERE idPlan = ?";

        try (Connection con = conn.conectarBD();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idPlan);
            ResultSet rs = ps.executeQuery();

            if (!rs.isBeforeFirst()) {
                System.out.println("No hay comidas registradas para ese plan.");
                return;
            }

            System.out.println("\nComidas del plan con ID " + idPlan + ":");
            while (rs.next()) {
                PlanComidas comida = extraerComida(rs);
                System.out.println(comida);
            }

        } catch (SQLException e) {
            System.out.println("❌ Error al buscar comidas: " + e.getMessage());
        }
    }

    // Insertar nueva comida
    public void insertar(PlanComidas comida) {
        String sql = "INSERT INTO plan_comidas (idPlan, dia_semana, momento, descripcion, calorias, hora_recomendada) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection con = conn.conectarBD();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, comida.getIdPlan());
            ps.setString(2, comida.getDiaSemana());
            ps.setString(3, comida.getMomento());
            ps.setString(4, comida.getDescripcion());
            ps.setInt(5, comida.getCaloriasAprox());
            ps.setString(6, comida.getHoraRecomendada());

            ps.executeUpdate();
            System.out.println("✅ Comida registrada exitosamente.");

        } catch (SQLException e) {
            System.out.println(" Error al insertar comida: " + e.getMessage());
        }
    }

    // Baja lógica de comida
    public void eliminarComida(int idComida) {
        String sql = "DELETE FROM plan_comidas WHERE id = ?";

        try (Connection con = conn.conectarBD();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idComida);
            int filas = ps.executeUpdate();

            if (filas > 0) {
                System.out.println("✅ Comida eliminada correctamente.");
            } else {
                System.out.println("⚠ No se encontró la comida con ese ID.");
            }

        } catch (SQLException e) {
            System.out.println(" Error al eliminar comida: " + e.getMessage());
        }
    }

    // Actualizar descripción de comida
    public void actualizarDescripcion(int idComida, String nuevaDescripcion) {
        String sql = "UPDATE plan_comidas SET descripcion = ? WHERE id = ?";

        try (Connection con = conn.conectarBD();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, nuevaDescripcion);
            ps.setInt(2, idComida);

            int filas = ps.executeUpdate();

            if (filas > 0) {
                System.out.println("Descripción actualizada correctamente.");
            } else {
                System.out.println(" No se encontró la comida con ese ID.");
            }

        } catch (SQLException e) {
            System.out.println(" Error al actualizar descripción: " + e.getMessage());
        }
    }

    // Método auxiliar para construir un objeto PlanComidas desde ResultSet
    private PlanComidas extraerComida(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        int idPlan = rs.getInt("idPlan");
        String diaSemana = rs.getString("dia_semana");
        String momento = rs.getString("momento");
        String descripcion = rs.getString("descripcion");
        int calorias = rs.getInt("calorias");
        String hora = rs.getString("hora_recomendada");

        return new PlanComidas(id, idPlan, diaSemana, momento, descripcion, calorias, hora);
    }
}
