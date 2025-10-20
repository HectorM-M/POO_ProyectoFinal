package vista;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Reservas {
    private JPanel reservas;
    private JTextField txtNombreCliente;
    private JTextField txtEdadCliente;
    private JTextField txtCantidadClientes;
    private JTextField textCheckIn;
    private JTextField txtCheckOut;
    private JComboBox cmbHabitacion;
    private JButton btnContinuar;
    private JButton btnRegresar;

    public Reservas() {
        cmbHabitacion.addItem("Habitación Simple");
        cmbHabitacion.addItem("Habitación Doble");

        btnRegresar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }
    //public para acceder a los datos
    public String getNombreCliente() {
        return txtNombreCliente.getText();
    }

    public int getEdadCliente() {
        return Integer.parseInt(txtEdadCliente.getText());
    }
    public int getCantidadClientes() {
        return Integer.parseInt(txtCantidadClientes.getText());
    }

    public String getCheckIn() {
        return textCheckIn.getText();
    }
    public String getCheckOut() {
        return  txtCheckOut.getText();
    }

    public String getHabitacion() {
        return cmbHabitacion.getSelectedItem() != null ?
                cmbHabitacion.getSelectedItem().toString() : "";    }

    public JPanel getPanel() {
        return reservas;
    }

    public JButton getBtnContinuar() {
        return btnContinuar;
    }
    public JButton getBtnRegresar() {
        return btnRegresar;
    }

    public void setPanel(JPanel panelReservas) {}
}
