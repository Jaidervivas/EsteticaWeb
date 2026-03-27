package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Conexion {
    Connection con;

    public Connection conectar() {
        try {
            Class.forName("org.sqlite.JDBC");
            con = DriverManager.getConnection("jdbc:sqlite:estetica_db.db");
            
            // ESTO CREARÁ LA TABLA AUTOMÁTICAMENTE
            String sql = "CREATE TABLE IF NOT EXISTS citas ("
                    + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + "nombre_completo TEXT NOT NULL,"
                    + "telefono TEXT,"
                    + "servicio TEXT,"
                    + "fecha TEXT,"
                    + "hora TEXT"
                    + ");";
            
            Statement stmt = con.createStatement();
            stmt.execute(sql);
            
        } catch (Exception e) {
            System.err.println("Error de conexión: " + e.getMessage());
        }
        return con;
    }
}