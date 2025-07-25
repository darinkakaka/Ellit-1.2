package Usuario;
// ===============================
// Clase Usuario.java
// ===============================
// Aquí se definen los atributos del usuario, constructores, getters/setters y toString
// Esta clase solo modela los datos, no contiene lógica de negocio

public class Usuario {

    // ===============================
    // Atributos
    // ===============================
    private int id;
    private String usuario;
    private String contrasenia;
    private String correo;
    private float edad;
    private String rol;

    // ===============================
    // Constructor vacío
    // ===============================
    public Usuario() {}

    // ===============================
    // Constructor con parámetros
    // ===============================
    public Usuario(int id, String usuario, String contrasenia, String correo, float edad, String rol) {
        this.id = id;
        this.usuario = usuario;
        this.contrasenia = contrasenia;
        this.correo = correo;
        this.edad = edad;
        this.rol = rol;
    }

    // ===============================
    // Getters y Setters
    // ===============================
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public float getEdad() {
        return edad;
    }

    public void setEdad(float edad) {
        this.edad = edad;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    // ===============================
    // Método toString()
    // ===============================
    @Override
    public String toString() {
        return "\n--- Perfil de Usuario ---\n" +
               "Nombre de usuario: " + usuario + "\n" +
               "Correo: " + correo + "\n" +
               "Edad: " + edad;
        // Nota: no se muestra id ni rol al cliente
    }
}
