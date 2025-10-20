package vista;

import controlador.LoginController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ConfirmarReserva {
    private JPanel confirmarReserva;
    private JLabel lblDatosReserva;
    private JButton btnFinalizarReserva;

    public ConfirmarReserva() {
        btnFinalizarReserva.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(confirmarReserva,
                        "Reserva completada correctamente.",
                        "Éxito",
                        JOptionPane.INFORMATION_MESSAGE);
                volverALogin();
            }
        });
    }

    private void volverALogin() {
        // Abrir ventana de login

        HotelApp login  = new HotelApp(); // tu clase vista de login
        new LoginController(login);
        JFrame frame = new JFrame("Login Hotel");
        frame.setContentPane(login.getPanel()); // getPanel() devuelve el JPanel de Login
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        // Cerrar ventana actual de ConfirmarReserva
        SwingUtilities.getWindowAncestor(confirmarReserva).dispose();
    }

    public JPanel getPanel() {
        return confirmarReserva;
    }
}



