package controlador;

import vista.HotelApp;
import vista.Reservas;
import vista.ConfirmarReserva;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;


import javax.swing.*;

public class ReservasController {
    private Reservas vista;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");


    public ReservasController(Reservas vista) {
        this.vista = vista;


        // solo para probar que si funciona el botonn
        this.vista.getBtnContinuar().addActionListener(e -> {
            System.out.println("✅ Botón Continuar presionado");
            abrirConfirmacion();


        });
    }

    private void abrirConfirmacion() {
        try {
            // aca tenemos datos de la vista de reservas
            String nombre = vista.getNombreCliente().trim();
            String edadTexto = vista.getEdadCliente() + ""; // Convertimos a String temporal
            String cantidadTexto = vista.getCantidadClientes() + "";
            String checkInStr = vista.getCheckIn().trim();
            String checkOutStr = vista.getCheckOut().trim();
            String habitacion = vista.getHabitacion();

            // Validamos datos
            if (nombre.isEmpty() || !nombre.matches("^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+$")) {
                JOptionPane.showMessageDialog(vista.getPanel(),
                        "El nombre solo puede contener letras y espacios.",
                        "Nombre inválido",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }

            // 2️⃣ Validar edad > 18
            int edad;
            try {
                edad = Integer.parseInt(vista.getEdadCliente() + "");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(vista.getPanel(),
                        "La edad debe ser un número válido.",
                        "Edad inválida",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }

            if (edad < 18) {
                JOptionPane.showMessageDialog(vista.getPanel(),
                        "Debe ser mayor de 18 años para reservar.",
                        "Edad no permitida",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }

            // 3️⃣ Validar cantidad de clientes
            int cantidad;
            try {
                cantidad = Integer.parseInt(vista.getCantidadClientes() + "");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(vista.getPanel(),
                        "La cantidad de clientes debe ser un número válido.",
                        "Cantidad inválida",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }

            if (cantidad <= 0) {
                JOptionPane.showMessageDialog(vista.getPanel(),
                        "La cantidad de clientes debe ser mayor a 0.",
                        "Cantidad inválida",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }

            // 4️⃣ Validar fechas
            LocalDate hoy = LocalDate.now();
            LocalDate maxFecha = hoy.plusMonths(12);
            LocalDate checkIn, checkOut;

            try {
                checkIn = LocalDate.parse(checkInStr, formatter);
                checkOut = LocalDate.parse(checkOutStr, formatter);
            } catch (DateTimeParseException ex) {
                JOptionPane.showMessageDialog(vista.getPanel(),
                        "Formato de fecha inválido. Use YYYY-MM-DD.",
                        "Fecha inválida",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }

            if (checkIn.isBefore(hoy)) {
                JOptionPane.showMessageDialog(vista.getPanel(),
                        "La fecha de Check-In no puede ser anterior al día de hoy.",
                        "Fecha inválida",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }

            if (checkOut.isAfter(maxFecha)) {
                JOptionPane.showMessageDialog(vista.getPanel(),
                        "La fecha de Check-Out no puede ser más de 12 meses en el futuro.",
                        "Fecha fuera de rango",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }

            if (checkOut.isBefore(checkIn)) {
                JOptionPane.showMessageDialog(vista.getPanel(),
                        "La fecha de Check-Out no puede ser antes de la fecha de Check-In.",
                        "Fechas inválidas",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }
                // Abrir la vista de login con su controlador
                HotelApp loginVista = new HotelApp();
                new LoginController(loginVista);
            //aca nuestra instancia
            ConfirmarReserva confirmacion = new ConfirmarReserva();

            // Mostrar ventana de confirmación
            JFrame frame = new JFrame("Confirmación de reserva");
            frame.setContentPane(confirmacion.getPanel());
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);

            // ✅ Cerrar la ventana actual
            SwingUtilities.getWindowAncestor(vista.getPanel()).dispose();
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(vista.getPanel(),
                    "Ocurrió un error al procesar la reserva: " + ex.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);

        }

    }
}