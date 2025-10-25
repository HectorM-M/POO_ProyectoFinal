package controlador;

import modelo.Conexion;
import vista.MisReservas;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class MisReservasController {
    private MisReservas vista;
    private int idUsuario;

    public MisReservasController(MisReservas vista, int idUsuario) {
        this.vista = vista;
        this.idUsuario = idUsuario;

        cargarReservas();

        vista.getBtnCerrar().addActionListener(e -> {
            SwingUtilities.getWindowAncestor(vista.getPanel()).dispose();
        });
    }

    private void cargarReservas() {
        DefaultTableModel modeloTabla = new DefaultTableModel();
        modeloTabla.addColumn("Nombre Cliente");
        modeloTabla.addColumn("Edad");
        modeloTabla.addColumn("Cantidad");
        modeloTabla.addColumn("Check-In");
        modeloTabla.addColumn("Check-Out");
        modeloTabla.addColumn("Habitación");

        try (Connection con = Conexion.getConexion()) {
            String sql = "SELECT nombre_cliente, edad, cantidad_clientes, check_in, check_out, habitacion FROM reservas WHERE usuario_id = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, idUsuario);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                modeloTabla.addRow(new Object[]{
                        rs.getString("nombre_cliente"),
                        rs.getInt("edad"),
                        rs.getInt("cantidad_clientes"),
                        rs.getString("check_in"),
                        rs.getString("check_out"),
                        rs.getString("habitacion")
                });
            }

            vista.getTablaReservas().setModel(modeloTabla);

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(vista.getPanel(), "Error al cargar reservas: " + e.getMessage());
        }
    }
}
