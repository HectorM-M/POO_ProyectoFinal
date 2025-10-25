package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class usuario {
    private String nombreUsuario;
    private String contrasena;
    private String correo;

    public usuario(String nombreUsuario, String contrasena, String correo) {
        this.nombreUsuario = nombreUsuario;
        this.contrasena = contrasena;
        this.correo = correo;
    }

    public String getNombreUsuario() { return nombreUsuario; }
    public String getContrasena() { return contrasena; }
    public String getCorreo() { return correo; }

    // 🔹 Guardar nuevo usuario
    public boolean guardar() throws SQLException {
        if (existeUsuarioOCorreo(nombreUsuario, correo)) {
            return false; // usuario o correo ya existe
        }

        String sql = "INSERT INTO usuarios (nombre_usuario, correo, contrasena) VALUES (?, ?, ?)";
        try (Connection conn = Conexion.getConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nombreUsuario);
            stmt.setString(2, correo);
            stmt.setString(3, contrasena);
            stmt.executeUpdate();
            System.out.println("✅ Usuario guardado: " + nombreUsuario);
            return true;
        }
    }

    // Verificar si usuario o correo ya existen
    public static boolean existeUsuarioOCorreo(String nombreUsuario, String correo) {
        String sql = "SELECT * FROM usuarios WHERE nombre_usuario = ? OR correo = ?";
        try (Connection conn = Conexion.getConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nombreUsuario);
            stmt.setString(2, correo);
            ResultSet rs = stmt.executeQuery();
            return rs.next(); // true si existe
        } catch (SQLException e) {
            e.printStackTrace();
            return true; // asumimos que existe para no duplicar
        }
    }

    // 🔹 Verificar credenciales (inicio de sesión)
    public static boolean verificar(String usuario, String contrasena) {
        String sql = "SELECT * FROM usuarios WHERE nombre_usuario = ? AND contrasena = ?";
        try (Connection conn = Conexion.getConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, usuario);
            stmt.setString(2, contrasena);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            System.out.println("❌ Error al verificar credenciales: " + e.getMessage());
            return false;
        }
    }
}

