/**
 *
 * @author Mario
 */
package ModeloDAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.CallableStatement;

public class PagoDAO {
    private Connection conectar() {
        // Conectar a la base de datos de pagos
        Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://161.132.56.56:3306/PagosDB", "sa", "");
        } catch (Exception e) {
            System.out.println("Error de conexión: " + e.getMessage());
        }
        return con;
    }

    public boolean registrarPago(int id_reserva, int id_cliente, float monto_total) {
        Connection con = conectar();
        if (con != null) {
            try {
                CallableStatement cs = con.prepareCall("{CALL registrarPago(?, ?, ?)}");
                cs.setInt(1, id_reserva);
                cs.setInt(2, id_cliente);
                cs.setFloat(3, monto_total);
                cs.execute();
                return true;
            } catch (Exception e) {
                System.out.println("Error al registrar el pago: " + e.getMessage());
            } finally {
                try { con.close(); } catch (Exception e) { }
            }
        }
        return false;
    }
}
