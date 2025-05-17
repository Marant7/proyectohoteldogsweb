
package Config;

/**
 *
 * @author Mario
 */

import java.sql.*;


public class Conexion {
    private static final String URL = "jdbc:mysql://161.132.56.56:3306/dbhotelparaperros";
    private static final String USER = "sa";
    private static final String PASSWORD = "";

    public Connection getConnection() {
        Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Conexión establecida correctamente a la base de datos.");
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Error al establecer la conexión a la base de datos: " + e.getMessage());
        }
        return con;
    }
    
}