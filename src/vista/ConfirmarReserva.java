package vista;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class ConfirmarReserva {
    private JPanel confirmarReserva;
    private JLabel lblDatosReserva;
    private JButton btnVerReservas;
    private JButton btnVolverLogin;

    private String nombre;
    private int edad;
    private int cantidad;
    private String checkIn;
    private String checkOut;
    private String habitacion;

    public ConfirmarReserva(String nombre, int edad, int cantidad, String checkIn, String checkOut, String habitacion) {
        this.nombre = nombre;
        this.edad = edad;
        this.cantidad = cantidad;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.habitacion = habitacion;

        // Mostrar los datos en la etiqueta
        lblDatosReserva.setText("<html>Nombre: " + nombre +
                "<br>Edad: " + edad +
                "<br>Cantidad de personas: " + cantidad +
                "<br>Check-In: " + checkIn +
                "<br>Check-Out: " + checkOut +
                "<br>Habitación: " + habitacion + "</html>");

        btnVerReservas.addActionListener(this::mostrarReserva);
        btnVolverLogin.addActionListener(this::volverALogin);
    }

    private void mostrarReserva(ActionEvent e) {
        // Simplemente podemos mostrar un JOptionPane con la reserva
        JOptionPane.showMessageDialog(confirmarReserva,
                "Reserva:\n" +
                        "Nombre: " + nombre + "\n" +
                        "Edad: " + edad + "\n" +
                        "Cantidad: " + cantidad + "\n" +
                        "Check-In: " + checkIn + "\n" +
                        "Check-Out: " + checkOut + "\n" +
                        "Habitación: " + habitacion,
                "Mi Reserva",
                JOptionPane.INFORMATION_MESSAGE);
    }

    private void volverALogin(ActionEvent e) {
        HotelApp login  = new HotelApp();
        new controlador.LoginController(login);

        JFrame frame = new JFrame("Login Hotel");
        frame.setContentPane(login.getPanel());
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



