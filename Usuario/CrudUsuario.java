package Usuario;
// ===============================
// Clase CrudUsuario.java
// ===============================
// Encargada de conectarse a la base de datos y realizar operaciones CRUD

import java.sql.*;
import java.util.ArrayList;

import Conexion.conn;

public class CrudUsuario {

    // ===============================
    // Insertar nuevo usuario
    // ===============================
    public static void insertarUsuario(Usuario u) {
        try (Connection con = conn.conectarBD()) {
            String sql = "INSERT INTO usuarios (usuario, contrasenia, correo, edad, rol) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, u.getUsuario());
            ps.setString(2, u.getContrasenia());
            ps.setString(3, u.getCorreo());
            ps.setFloat(4, u.getEdad());
            ps.setString(5, u.getRol());
            ps.executeUpdate();
            System.out.println("Usuario registrado exitosamente.");
        } catch (SQLException e) {
            System.err.println("Error al registrar: " + e.getMessage());
        }
    }

    // ===============================
    // Obtener todos los usuarios
    // ===============================
    public static ArrayList<Usuario> obtenerUsuarios() {
        ArrayList<Usuario> lista = new ArrayList<>();
        try (Connection con = conn.conectarBD()) {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM usuarios");
            while (rs.next()) {
                Usuario u = new Usuario(
                    rs.getInt("id"),
                    rs.getString("usuario"),
                    rs.getString("contrasenia"),
                    rs.getString("correo"),
                    rs.getFloat("edad"),
                    rs.getString("rol")
                );
                lista.add(u);
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener usuarios: " + e.getMessage());
        }
        return lista;
    }

    // ===============================
    // Buscar usuario por nombre
    // ===============================
    public static Usuario buscarUsuario(String nombre) {
        try (Connection con = conn.conectarBD()) {
            String sql = "SELECT * FROM usuarios WHERE usuario = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, nombre);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Usuario(
                    rs.getInt("id"),
                    rs.getString("usuario"),
                    rs.getString("contrasenia"),
                    rs.getString("correo"),
                    rs.getFloat("edad"),
                    rs.getString("rol")
                );
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar: " + e.getMessage());
        }
        return null;
    }

    // ===============================
    // Actualizar usuario
    // ===============================
    public static void actualizarUsuario(Usuario u) {
        try (Connection con = conn.conectarBD()) {
            String sql = "UPDATE usuarios SET usuario=?, contrasenia=?, correo=?, edad=?, rol=? WHERE id=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, u.getUsuario());
            ps.setString(2, u.getContrasenia());
            ps.setString(3, u.getCorreo());
            ps.setFloat(4, u.getEdad());
            ps.setString(5, u.getRol());
            ps.setInt(6, u.getId());
            ps.executeUpdate();
            System.out.println("Usuario actualizado correctamente.");
        } catch (SQLException e) {
            System.err.println("Error al actualizar: " + e.getMessage());
        }
    }

    // ===============================
    // Eliminar usuario por ID
    // ===============================
    public static void eliminarUsuario(int id) {
        try (Connection con = conn.conectarBD()) {
            String sql = "DELETE FROM usuarios WHERE id = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("Usuario eliminado correctamente.");
        } catch (SQLException e) {
            System.err.println("Error al eliminar: " + e.getMessage());
        }
    }
}
