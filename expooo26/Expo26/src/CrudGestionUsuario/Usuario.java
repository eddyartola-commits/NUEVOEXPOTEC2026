package CrudGestionUsuario;

public class Usuario {
    private int id_login;
    private String rol;
    private String nombre;
    private String correo;
    private String contrasena;

    public Usuario() {}

    public Usuario(int id_login, String rol, String nombre, String correo, String contrasena) {
        this.id_login = id_login;
        this.rol = rol;
        this.nombre = nombre;
        this.correo = correo;
        this.contrasena = contrasena;
    }

    // Getters y Setters
    public int getId_login() { return id_login; }
    public void setId_login(int id_login) { this.id_login = id_login; }

    public String getRol() { return rol; }
    public void setRol(String rol) { this.rol = rol; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }

    public String getContrasena() { return contrasena; }
    public void setContrasena(String contrasena) { this.contrasena = contrasena; }
}