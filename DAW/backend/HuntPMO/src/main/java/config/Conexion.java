package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    public static Connection getConexion() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/HuntPMO";
        String usuario = "root";
        String password = "mysql";

        return DriverManager.getConnection(url, usuario, password);
    }
}
