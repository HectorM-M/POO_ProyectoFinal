package vista;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HotelApp {
    private JTextField txtNombre;
    private JTextField txtNuevoUsuario;
    private JTextField txtCorreo;
    private JPasswordField txtContraseña;
    private JPasswordField txtNuevaContraseña;
    private JButton btnIniciar;
    private JButton btnCrearCuenta;
    private JPanel panelLogin;


    public HotelApp() {
    }
    

    public JPanel getPanel() {
        return panelLogin;
    }

    // Métodos públicos para acceder a los datos ingresados
    public String getUsuario() {
        return txtNombre.getText();
    }

    public String getContrasena() {
        return new String(txtContraseña.getPassword());
    }

    //Metodo para obtener datos de nueva cuenta
    public String getNuevoUsuario() {

        return txtNuevoUsuario.getText();
    }

    public String getCorreo() {

        return txtCorreo.getText();
    }

    public String getNuevaContrasena() {
        return new String(txtNuevaContraseña.getPassword());
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Login Hotel");
        HotelApp vista = new HotelApp();

        new controlador.LoginController(vista);

        frame.setContentPane(vista.getPanel());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public void setBtnIniciarAction(ActionListener listener) {
        btnIniciar.addActionListener(listener);
    }

    public void setBtnCrearCuentaAction(ActionListener listener) {
        btnCrearCuenta.addActionListener(listener); //
    }

}
