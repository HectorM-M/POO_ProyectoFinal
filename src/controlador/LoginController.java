package controlador;
import vista.HotelApp;
import vista.Reservas;

import javax.swing.*;

public class LoginController {
    private HotelApp vista;

    public LoginController(HotelApp vista) {
        this.vista = vista;
        this.vista.setBtnIniciarAction(e -> verificarCredenciales());
        this.vista.setBtnCrearCuentaAction(e -> crearCuenta());


}

    private void verificarCredenciales() {
        String usuario = vista.getUsuario();
        String contrasena = vista.getContrasena();

        if (usuario.equals("admin") && contrasena.equals("123456")) {
            Reservas reservas = new Reservas();
            new controlador.ReservasController(reservas);

            JFrame frame = new JFrame("Panel Reservas");
            frame.setContentPane(reservas.getPanel());
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);

            SwingUtilities.getWindowAncestor(vista.getPanel()).dispose();
        } else {
            JOptionPane.showMessageDialog(vista.getPanel(),
                    "Usuario o contraseña incorrectos.",
                    "Error de inicio de sesión",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
        private void crearCuenta() {
            //obtenemos datos del nuevio usuario

                String nuevoUsuario = vista.getNuevoUsuario();
                String correo = vista.getCorreo();
                String nuevaContrasena = vista.getNuevaContrasena();

                if (validarDatos(nuevoUsuario, nuevaContrasena, correo)) {
                    JOptionPane.showMessageDialog(vista.getPanel(),
                            "Cuenta registrada, Bienvenido " + nuevoUsuario + "!",
                            "Éxito",
                            JOptionPane.INFORMATION_MESSAGE);
                    Reservas reservas = new Reservas();
                    new ReservasController(reservas); // conecta el listener

                    controlador.ReservasController reservasController = new controlador.ReservasController(reservas);
                    JFrame frame = new JFrame("Panel Reservas");
                    frame.setContentPane(reservas.getPanel());
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    frame.pack();
                    frame.setLocationRelativeTo(null);
                    frame.setVisible(true);

                    // 🔹 Cerramos la ventana actual
                    SwingUtilities.getWindowAncestor(vista.getPanel()).dispose();

                } else {
                    JOptionPane.showMessageDialog(vista.getPanel(),
                            "Datos inválidos, intente de nuevo",
                            "ERROR",
                            JOptionPane.ERROR_MESSAGE);
                }
            }


    private boolean validarDatos(String nombre, String contrasena, String correo) {
        // Nombre: solo letras (mayúsculas o minúsculas)
        if (!nombre.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+")) {
            JOptionPane.showMessageDialog(vista.getPanel(),
                    "El nombre solo puede contener letras.",
                    "Error en nombre",
                    JOptionPane.WARNING_MESSAGE);
            return false;
        }

        // validacion ebe contener '@' y un dominio simple
        if (!correo.matches("^[\\w\\.-]+@[\\w\\.-]+\\.[a-zA-Z]{2,}$")) {
            JOptionPane.showMessageDialog(vista.getPanel(),
                    "El correo electrónico no es válido. Debe contener '@' y un dominio.",
                    "Error en correo",
                    JOptionPane.WARNING_MESSAGE);
            return false;
        }

        // Contraseña: solo números
        if (!contrasena.matches("\\d+")) {
            JOptionPane.showMessageDialog(vista.getPanel(),
                    "La contraseña solo puede contener números.",
                    "Error en contraseña",
                    JOptionPane.WARNING_MESSAGE);
            return false;
        }

        return true;
    }


    public static void main(String[] args) {
        JFrame frame = new JFrame("Login Hotel");
        HotelApp app = new HotelApp();
        frame.setContentPane(app.getPanel());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);


    }
}

