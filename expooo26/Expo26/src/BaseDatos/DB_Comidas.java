package BaseDatos;

import Conexion.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DB_Comidas {

    private Connection con;

    public DB_Comidas() {
        con = Conexion.conectar();
    }

    public ResultSet obtenerComidasPorNivel(String nivel) {
        ResultSet rs = null;
        try {
            String sql = "SELECT id_comida, nombre FROM Comidas WHERE nivel = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, nivel);
            rs = ps.executeQuery();
        } catch (SQLException e) {
            System.out.println("Error al obtener comidas: " + e.getMessage());
        }
        return rs;
    }

    public String obtenerImagenComida(int idComida) {
        String imagen = "";
        try {
            String sql = "SELECT imagen FROM Comidas WHERE id_comida = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, idComida);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                imagen = rs.getString("imagen");
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener imagen: " + e.getMessage());
        }
        return imagen;
    }

    public boolean guardarComida(String nombre, String imagen, String nivel) {
        String sql = "INSERT INTO Comidas(nombre, imagen, nivel) VALUES (?, ?, ?)";
        try {
            Connection con = Conexion.conectar();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, nombre);
            ps.setString(2, imagen);
            ps.setString(3, nivel);
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            System.out.println("Error al guardar comida: " + e.getMessage());
            return false;
        }
    }

public boolean actualizarImagenComida(int idComida, String nombre, String imagen) {
    String sql = "UPDATE Comidas SET nombre = ?, imagen = ? WHERE id_comida = ?";
    try {
        Connection con = Conexion.conectar();
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, nombre);
        ps.setString(2, imagen);
        ps.setInt(3, idComida);
        ps.executeUpdate();
        return true;
    } catch (Exception e) {
        System.out.println("Error: " + e.getMessage());
        return false;
    }
}

    public ResultSet obtenerTodasLasComidas() {
        ResultSet rs = null;
        try {
            String sql = "SELECT id_comida, imagen FROM Comidas ORDER BY id_comida";
            PreparedStatement ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
        } catch (SQLException e) {
            System.out.println("Error al obtener todas las comidas: " + e.getMessage());
        }
        return rs;
    }
}
