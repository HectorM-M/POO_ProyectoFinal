package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

    private static final String URL = "jdbc:mysql://localhost:3306/proyecto_db";
    private static final String USER = "root"; // tu usuario de MySQL
    private static final String PASSWORD = ""; // tu contraseña si tienes una

    // Devuelve una nueva conexión cada vez que se llama
    public static Connection getConexion() {
        try {
            Connection conexion = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("✅ Conexión establecida con la base de datos.");
            return conexion;
        } catch (SQLException e) {
            System.out.println("❌ Error al conectar con la base de datos: " + e.getMessage());
            return null;
        }
    }
}
