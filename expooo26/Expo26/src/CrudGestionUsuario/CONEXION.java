package CrudGestionUsuario;

import java.sql.Connection;
import java.sql.DriverManager;

public class CONEXION {
    private Connection con;
    // Aquí es donde pones el nombre de tu base de datos al final (DB_Power):
    private final String url = "jdbc:mysql://localhost:3306/DB_Power";
    private final String user = "root"; // Tu usuario de Workbench
    private final String pass = "123456789"; // Tu contraseña de Workbench

    public Connection conectar() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url, user, pass);
        } catch (Exception e) {
            System.err.println("Error de conexión: " + e.getMessage());
        }
        return con;
    }
}