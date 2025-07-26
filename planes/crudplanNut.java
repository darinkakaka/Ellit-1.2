package planes;


 import java.util.ArrayList;
import java.util.List;
import java.sql.*;
import Conexion.conn;


 public class crudplanNut {
    // Método para mostrar todos los planes (consulta)
    public void consulta() {
        verTodosLosPlanes();
    }

    // Mostrar todos los planes con sus comidas
    public void verTodosLosPlanes( ) {
        String sqlPlanes = "SELECT * FROM plan_nutricional";

        try (Connection con = conn.conectarBD();
     Statement stmt = con.createStatement();
     ResultSet rsPlanes = stmt.executeQuery(sqlPlanes)) {

            if (!rsPlanes.isBeforeFirst()) {
                System.out.println("No hay planes registrados aún.");
                return;
            }

            while (rsPlanes.next()) {
                int id = rsPlanes.getInt("id");
                String nombre = rsPlanes.getString("nombre");
                String objetivo = rsPlanes.getString("objetivo");
                String recomendaciones = rsPlanes.getString("recomendaciones");
                String descripcion = rsPlanes.getString("descripcion");

                PlanNutricional plan = new PlanNutricional(id, nombre, objetivo, recomendaciones, descripcion);
                List<PlanComidas> comidas = obtenerComidasPorPlan(id);
                plan.setComidas(comidas);

                System.out.println(plan);
                for (PlanComidas comida : comidas) {
                    System.out.println("    - " + comida);
                }
                System.out.println("--------------------------------------");
            }

        } catch (SQLException e) {
            System.out.println("Error al consultar los planes: " + e.getMessage());
        }
    }

    // Buscar un plan específico por ID
   public void buscarPorId(int idBuscar) {
    String sql = "SELECT * FROM plan_nutricional WHERE id = ?";

    try (Connection con = conn.conectarBD();
         PreparedStatement ps = con.prepareStatement(sql)) {

        ps.setInt(1, idBuscar);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            String nombre = rs.getString("nombre");
            String objetivo = rs.getString("objetivo");
            String recomendaciones = rs.getString("recomendaciones");
            String descripcion = rs.getString("descripcion");

            PlanNutricional plan = new PlanNutricional(idBuscar, nombre, objetivo, recomendaciones, descripcion);
            List<PlanComidas> comidas = obtenerComidasPorPlan(idBuscar);
            plan.setComidas(comidas);

            System.out.println("\n Plan encontrado:");
            System.out.println(plan);
            for (PlanComidas comida : comidas) {
                System.out.println("    - " + comida);
            }

        } else {
            System.out.println(" No se encontró un plan con ese ID.");
        }

    } catch (SQLException e) {
        System.out.println("Error al buscar el plan: " + e.getMessage());
    }
}

    // Obtener comidas por ID de plan
   private List<PlanComidas> obtenerComidasPorPlan(int idPlan) {
    List<PlanComidas> comidas = new ArrayList<>();
    String sql = "SELECT * FROM plan_comidas WHERE idPlan = ?";

    try (Connection con = conn.conectarBD();
         PreparedStatement ps = con.prepareStatement(sql)) {

        ps.setInt(1, idPlan);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            int idComida = rs.getInt("id");
            int idPlanBD = rs.getInt("idPlan");
            String diaSemana = rs.getString("dia_semana");
            String momento = rs.getString("momento");
            String descripcion = rs.getString("descripcion");
            int caloriasAprox = rs.getInt("calorias");
            String hora = rs.getString("hora_recomendada");

            PlanComidas comida = new PlanComidas(
                idComida,
                idPlanBD,
                diaSemana,
                momento,
                descripcion,
                caloriasAprox,
                hora
            );

            comidas.add(comida);
        }


        } catch (SQLException e) {
            System.out.println("Error al obtener comidas: " + e.getMessage());
        }

        return comidas;
    }
     public static void insertar(PlanNutricional plan) {
        Connection con = conn.conectarBD();
        String sql = "INSERT INTO plan_nutricional (nombre, objetivo, recomendaciones, descripcion, estatus) VALUES (?, ?, ?, ?, ?)";

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, plan.getNombre());
            ps.setString(2, plan.getObjetivo());
            ps.setString(3, plan.getRecomendaciones());
            ps.setString(4, plan.getDescripcion());
            ps.setString(5, String.valueOf(plan.getEstatus()));
            ps.executeUpdate();

            System.out.println("✅ Plan guardado exitosamente en la base de datos");

        } catch (SQLException e) {
            System.out.println(" Error al insertar el plan: " + e.getMessage());
        }
    }
    public void bajaLogica(int idPlan) {
    String sql = "UPDATE plan_nutricional SET estatus = 'I' WHERE id = ?";

    try (Connection con = conn.conectarBD();
         PreparedStatement ps = con.prepareStatement(sql)) {

        ps.setInt(1, idPlan);
        int filasAfectadas = ps.executeUpdate();

        if (filasAfectadas > 0) {
            System.out.println(" El plan fue dado de baja lógicamente.");
        } else {
            System.out.println("⚠ No se encontró un plan con ese ID.");
        }

    } catch (SQLException e) {
        System.out.println("❌ Error al dar de baja lógicamente: " + e.getMessage());
    }

}



// Método para actualizar la descripción de un plan por su ID
public void actualizarDescripcion(int id, String nuevaDescripcion) {
    String sql = "UPDATE plan_nutricional SET descripcion = ? WHERE id = ?";

    try (Connection con = conn.conectarBD();
         PreparedStatement ps = con.prepareStatement(sql)) {

        ps.setString(1, nuevaDescripcion);
        ps.setInt(2, id);

        int filasAfectadas = ps.executeUpdate();

        if (filasAfectadas > 0) {
            System.out.println("escripción actualizada correctamente.");
        } else {
            System.out.println("No se encontró un plan con ese ID.");
        }

    } catch (SQLException e) {
        System.out.println("❌ Error al actualizar la descripción: " + e.getMessage());
    }
}

}

    

