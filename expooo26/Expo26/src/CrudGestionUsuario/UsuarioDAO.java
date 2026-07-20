package CrudGestionUsuario;

import Conexion.Conexion;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {
    Conexion conectar = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    // VER / LISTAR USUARIOS
    public List<Usuario> listar() {
        List<Usuario> datos = new ArrayList<>();
        String sql = "SELECT * FROM Login";
        try {
            con = conectar.conectar();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Usuario u = new Usuario();
                u.setId_login(rs.getInt("id_login"));
                u.setRol(rs.getString("Rol"));
                u.setNombre(rs.getString("Nombre"));
                u.setCorreo(rs.getString("Correo"));
                u.setContrasena(rs.getString("Contrasena"));
                datos.add(u);
            }
        } catch (Exception e) {
            System.out.println("Error al listar: " + e.getMessage());
        }
        return datos;
    }

    // REGISTRAR / AGREGAR USUARIO
    public int agregar(Usuario u) {
       String sql = "INSERT INTO Login(Rol, Nombre, Correo, Contrasena) VALUES(?,?,?,?)";
        try {
            con = conectar.conectar();
            
            // 🚨 Añadimos Statement.RETURN_GENERATED_KEYS para pedirle a MySQL el ID que va a crear
            ps = con.prepareStatement(sql, java.sql.Statement.RETURN_GENERATED_KEYS);
            
            ps.setString(1, u.getRol());
            ps.setString(2, u.getNombre());
            ps.setString(3, u.getCorreo());
            ps.setString(4, u.getContrasena());
            ps.executeUpdate();
            
            // 🌟 Capturamos el ID autoincremental y se lo metemos al objeto 'u' en vivo
            try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    u.setId_login(generatedKeys.getInt(1));
                }
            }
            
        } catch (Exception e) {
            System.out.println("Error al agregar: " + e.getMessage());
        }
        return 1;
    }

    // ACTUALIZAR / MODIFICAR USUARIO
    public int actualizar(Usuario u) {
        String sql = "UPDATE Login SET Rol=?, Nombre=?, Correo=?, Contrasena=? WHERE id_login=?";
        try {
            con = conectar.conectar();
            ps = con.prepareStatement(sql);
            ps.setString(1, u.getRol());
            ps.setString(2, u.getNombre());
            ps.setString(3, u.getCorreo());
            ps.setString(4, u.getContrasena());
            ps.setInt(5, u.getId_login());
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error al actualizar: " + e.getMessage());
        }
        return 1;
    }

    // ELIMINAR USUARIO
    public void eliminar(int id) {
        String sql = "DELETE FROM Login WHERE id_login = ?";
        try {
            con = conectar.conectar();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error al eliminar: " + e.getMessage());
        }
    }
}