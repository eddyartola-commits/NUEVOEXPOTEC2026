package Conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    
    // Se agregó "&allowPublicKeyRetrieval=true" al final de la URL
    private static final String URL = "jdbc:mysql://localhost:3306/DB_Power?useSSL=false&allowPublicKeyRetrieval=true";
    private static final String USER = "root";
    private static final String PASSWORD = "123456789";

    public static Connection conectar() {
        Connection con = null;
        try {
            con = DriverManager.getConnection(URL, USER, PASSWORD);

        } catch (SQLException e) {

            System.out.println("Error: " + e.getMessage());

        }

        return con;
    }
}