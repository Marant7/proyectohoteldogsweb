
package ModeloDAO;
import Config.Conexion;
import Interfaces.interfaceDetalleReserva;
import Modelo.DetalleReserva;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.*;

/**
 *
 * @author Mario
 */
public class DetalleReservaDAO implements interfaceDetalleReserva {
Conexion cn = new Conexion();
    Connection con;
    CallableStatement cs;
    ResultSet rs;
     PreparedStatement ps;

    @Override
    public boolean crearDetalleReserva(DetalleReserva detalleReserva) {
         String sql = "{CALL crearDetalleReserva(?, ?, ?, ?)}";
        try {
            con = cn.getConnection();
            CallableStatement cs = con.prepareCall(sql);
            cs.setInt(1, detalleReserva.getIdMascota());
            cs.setInt(2, detalleReserva.getIdServicio());
            cs.setFloat(3, detalleReserva.getMontoTotalReserva());
            cs.registerOutParameter(4, Types.INTEGER); // Registrar parámetro de salida para id_detalle_reserva

            int rowsAffected = cs.executeUpdate();
            if (rowsAffected > 0) {
                detalleReserva.setIdDetalleReserva(cs.getInt(4)); // Obtener ID generado
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

}
