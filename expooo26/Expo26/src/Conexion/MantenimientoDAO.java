package Conexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MantenimientoDAO {

    public String obtenerCorreosAdministradores() {
        StringBuilder listaCorreos = new StringBuilder();
        
        // 🌟 SQL: Busca exclusivamente a los usuarios de la tabla Login con rol 'Administrador'
        String query = "SELECT Correo FROM Login WHERE rol = 'Administrador'"; 

        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                // Mapeamos con la "C" mayúscula exacta de tu columna MySQL
                String email = rs.getString("Correo"); 
                if (email != null) {
                    listaCorreos.append(email).append(",");
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener correos en MantenimientoDAO: " + e.getMessage());
        }

        // Quitamos la última coma sobrante si se añadieron registros
        if (listaCorreos.length() > 0) {
            listaCorreos.setLength(listaCorreos.length() - 1);
        }

        return listaCorreos.toString();
    }
}