package BaseDatos;

import Conexion.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

public class DB_Rutinas {

    private Connection con;

    public DB_Rutinas() {
        con = Conexion.conectar();
    }

    public ResultSet obtenerRutinasPorNivel(String nivel) {
        ResultSet rs = null;
        try {
            String sql = "SELECT id_rutina, nombre FROM Rutinas WHERE nivel = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, nivel);
            rs = ps.executeQuery();
        } catch (SQLException e) {
            System.out.println("Error al obtener rutinas: " + e.getMessage());
        }
        return rs;
    }

    public String obtenerImagenRutina(int idRutina) {
        String imagen = "";
        try {
            String sql = "SELECT imagen FROM Rutinas WHERE id_rutina = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, idRutina);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                imagen = rs.getString("imagen");
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener imagen: " + e.getMessage());
        }
        return imagen;
    }

    public boolean guardarRutina(String nombre, String imagen, String nivel) {
        String sql = "INSERT INTO Rutinas(nombre, imagen, nivel) VALUES (?, ?, ?)";
        try {
            Connection con = Conexion.conectar();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, nombre);
            ps.setString(2, imagen);
            ps.setString(3, nivel);
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            System.out.println("Error al guardar rutina: " + e.getMessage());
            return false;
        }
    }

    public boolean actualizarImagenRutina(int idRutina, String nombre, String imagen) {
    String sql = "UPDATE Rutinas SET nombre = ?, imagen = ? WHERE id_rutina = ?";
    try {
        Connection con = Conexion.conectar();
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, nombre);
        ps.setString(2, imagen);
        ps.setInt(3, idRutina);
        ps.executeUpdate();
        return true;
    } catch (Exception e) {
        System.out.println("Error: " + e.getMessage());
        return false;
    }
    }

    public ResultSet obtenerTodasLasRutinas() {
        ResultSet rs = null;
        try {
            String sql = "SELECT id_rutina, imagen FROM Rutinas ORDER BY id_rutina";
            PreparedStatement ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
        } catch (SQLException e) {
            System.out.println("Error al obtener todas las rutinas: " + e.getMessage());
        }
        return rs;
    }
}
