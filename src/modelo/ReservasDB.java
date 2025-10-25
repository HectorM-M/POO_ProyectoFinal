package modelo;

import java.sql.*;

public class ReservasDB {
    private static final String URL = "jdbc:mysql://localhost:3306/proyecto_db";
    private static final String USER = "root"; // tu usuario de MySQL
    private static final String PASS = "";     // tu contraseña de MySQL

    public static void guardarReserva(String nombre, int edad, int cantidad, String checkIn, String checkOut, String habitacion, int usuarioId) throws SQLException {
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = DriverManager.getConnection(URL, USER, PASS);
            String sql = "INSERT INTO reservas(nombre_cliente, edad, cantidad_clientes, check_in, check_out, habitacion, usuario_id, creado_en) VALUES (?, ?, ?, ?, ?, ?, ?, NOW())";
            ps = con.prepareStatement(sql);
            ps.setString(1, nombre);
            ps.setInt(2, edad);
            ps.setInt(3, cantidad);
            ps.setString(4, checkIn);
            ps.setString(5, checkOut);
            ps.setString(6, habitacion);
            ps.setInt(7, usuarioId);

            ps.executeUpdate();
        } finally {
            if (ps != null) ps.close();
            if (con != null) con.close();
        }
    }
}
