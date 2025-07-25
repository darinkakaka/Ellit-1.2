package Conexion;
// ===============================
// Clase conn.java
// ===============================
// Se encarga de conectar y desconectar la base de datos MySQL local

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class conn {

    // ===============================
    // Método para conectar a la BD
    // ===============================
    public static Connection conectarBD() {
        Connection conexion = null;
        String host = "jdbc:mysql://localhost:3306/";
        String usuario = "root";
        String contraseña = "";
        String bd = "ellit";

        try {
            conexion = DriverManager.getConnection(host + bd, usuario, contraseña);
            // System.out.println("Conexión exitosa a la base de datos."); // puedes eliminar este println
        } catch (SQLException error) {
            System.out.println("Error en la conexión");
            System.out.println(error.getMessage());
            error.printStackTrace(); // Para ver en qué parte ocurrió el error
        }

        return conexion;
    }

    // ===============================
    // Método para cerrar conexión
    // ===============================
    public static void desconectar(Connection conexion) {
        try {
            if (conexion != null && !conexion.isClosed()) {
                conexion.close();
                // System.out.println("Conexión cerrada.");
            }
        } catch (SQLException error) {
            System.out.println("Error al cerrar conexión: " + error.getMessage());
        }
    }
}
