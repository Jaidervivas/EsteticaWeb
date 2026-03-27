package model;

import config.Conexion;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CitaDAO {
    Conexion conectar = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    public List<Cita> listarCitas() {
        List<Cita> lista = new ArrayList<>();
        String sql = "SELECT * FROM citas ORDER BY fecha, hora ASC";
        try {
            con = conectar.conectar();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Cita c = new Cita();
                c.setId(rs.getInt("id"));
                c.setNombre(rs.getString("nombre_completo"));
                c.setTelefono(rs.getString("telefono"));
                c.setServicio(rs.getString("servicio"));
                c.setFecha(rs.getString("fecha"));
                c.setHora(rs.getString("hora"));
                lista.add(c);
            }
        } catch (SQLException e) {
            System.out.println("Error Listar: " + e.getMessage());
        } finally {
            cerrarRecursos(); // Cerramos siempre
        }
        return lista;
    }

    public boolean registrarCita(Cita cita) {
        String sql = "INSERT INTO citas (nombre_completo, telefono, servicio, fecha, hora) VALUES (?,?,?,?,?)";
        try {
            con = conectar.conectar();
            ps = con.prepareStatement(sql);
            ps.setString(1, cita.getNombre());
            ps.setString(2, cita.getTelefono());
            ps.setString(3, cita.getServicio());
            ps.setString(4, cita.getFecha());
            ps.setString(5, cita.getHora());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Error Registrar: " + e.getMessage());
            return false;
        } finally {
            cerrarRecursos();
        }
    }

    public boolean eliminarCita(int id) {
        String sql = "DELETE FROM citas WHERE id = ?";
        try {
            con = conectar.conectar();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            int resultado = ps.executeUpdate(); // Orden de ejecución real
            return resultado > 0;
        } catch (SQLException e) {
            System.out.println("Error Eliminar: " + e.getMessage());
            return false;
        } finally {
            cerrarRecursos();
        }
    }
    public boolean modificarCita(Cita cita) {
    String sql = "UPDATE citas SET nombre_completo=?, telefono=?, servicio=?, fecha=?, hora=? WHERE id=?";
    try {
        con = conectar.conectar();
        ps = con.prepareStatement(sql);
        ps.setString(1, cita.getNombre());
        ps.setString(2, cita.getTelefono());
        ps.setString(3, cita.getServicio());
        ps.setString(4, cita.getFecha());
        ps.setString(5, cita.getHora());
        ps.setInt(6, cita.getId()); // Usamos el ID para saber cuál fila cambiar
        
        int resultado = ps.executeUpdate();
        return resultado > 0;
    } catch (SQLException e) {
        System.out.println("Error al Modificar: " + e.getMessage());
        return false;
    } finally {
        cerrarRecursos(); // Usando el método de limpieza que creamos antes
    }
}

    // Método extra para asegurar que NADA quede abierto
    private void cerrarRecursos() {
        try {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
            if (con != null) con.close();
        } catch (SQLException e) {
            System.out.println("Error al cerrar: " + e.getMessage());
        }
    }
}