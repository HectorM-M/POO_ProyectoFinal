package modelo;

public class usuario {
    private String nombreUsuario;
    private String contraseña;
    private String correo;
    private String nuevaContrasena;
    private String nuevoUsuario;

    public usuario(String nombreUsuario, String contraseña,  String correo, String nuevaContrasena, String nuevoUsuario) {

        this.nombreUsuario = nombreUsuario;
        this.contraseña = contraseña;
        this.correo = correo;
        this.nuevaContrasena = nuevaContrasena;
        this.nuevoUsuario = nuevoUsuario;
    }

    public String getNombreUsuario()
    {
        return nombreUsuario;
    }

    public String getContraseña()
    {
        return contraseña;
    }

    public String getCorreo()
    {
        return correo;
    }

    public String getNuevoUsuario() {
        return nuevoUsuario;
    }

    public String getNuevoContrasena() {
        return nuevaContrasena;
    }

    // Comparación de credenciales
    public boolean validar(String usuarioIngresado, String contraseñaIngresada) {
        return this.nombreUsuario.equals(usuarioIngresado) &&
                this.contraseña.equals(contraseñaIngresada);
    }
}

