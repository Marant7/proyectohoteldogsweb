
package ModeloDAO;

import Config.Conexion;
import Interfaces.interfaceReserva;
import Modelo.Dormitorio;
import Modelo.Reserva;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Mario
 */
public class ReservaDAO implements interfaceReserva {
 Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
   CallableStatement cs;

    @Override
    public int verificarDisponibilidadDormitorios() {
       String sql = "{CALL verificarDisponibilidadDormitorios()}";
        int disponibles = 0;
        try {
            con = cn.getConnection();
            cs = con.prepareCall(sql);
            rs = cs.executeQuery();
            if (rs.next()) {
                disponibles = rs.getInt("disponibles");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                if (rs != null) rs.close();
                if (cs != null) cs.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return disponibles;
    }

    @Override
    public boolean crearReserva(Reserva reserva, int dormitorio) {
        
    String sql = "{CALL crearReserva(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}"; // 9 IN parameters + 1 OUT parameter
    try {
        con = cn.getConnection();
        CallableStatement cs = con.prepareCall(sql);
        cs.setInt(1, reserva.getIdCliente());
        cs.setInt(2, reserva.getIdMascota());
        cs.setString(3, arrayToCommaSeparatedString(reserva.getIdServicios()));
        cs.setFloat(4, reserva.getMontoTotalReserva());
        cs.setInt(5, reserva.getCodigoTicket());
               cs.setTimestamp(6, new java.sql.Timestamp(reserva.getFechaInicio().getTime()));
    cs.setString(7, reserva.getEstado());

       
            cs.setTimestamp(8, new java.sql.Timestamp(reserva.getFechaFin().getTime()));
        cs.setInt(9, dormitorio);
             cs.setString(10, reserva.getProceso());
        cs.registerOutParameter(11, Types.INTEGER); // Registrar parámetro de salida para id_reserva

        int rowsAffected = cs.executeUpdate();
        if (rowsAffected > 0) {
            reserva.setIdReserva(cs.getInt(11)); // Obtener ID generado
            return true;
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }finally {
            try {
                if (rs != null) rs.close();
                if (cs != null) cs.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    return false;
    }
     private String arrayToCommaSeparatedString(int[] array) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < array.length; i++) {
            sb.append(array[i]);
            if (i < array.length - 1) {
                sb.append(",");
            }
        }
        return sb.toString();
    }

    @Override
    public List<Dormitorio> obtenerListaDormitorios() {
    List<Dormitorio> lista = new ArrayList<>();

    Dormitorio d1 = new Dormitorio();
    d1.setId_dormitorio(1);
    d1.setNombre("Dormitorio A");
    d1.setDisponibilidad(3);

    Dormitorio d2 = new Dormitorio();
    d2.setId_dormitorio(2);
    d2.setNombre("Dormitorio B");
    d2.setDisponibilidad(0);

    Dormitorio d3 = new Dormitorio();
    d3.setId_dormitorio(3);
    d3.setNombre("Dormitorio C");
    d3.setDisponibilidad(2);

    Dormitorio d4 = new Dormitorio();
    d4.setId_dormitorio(4);
    d4.setNombre("Dormitorio D");
    d4.setDisponibilidad(1);

    lista.add(d1);
    lista.add(d2);
    lista.add(d3);
    lista.add(d4);

    return lista;
}

    @Override
    public boolean liberarDormitorios() {
        String sql = "{CALL liberarDormitorios()}";
    try {
        con = cn.getConnection();
        CallableStatement cs = con.prepareCall(sql);
        int rowsAffected = cs.executeUpdate();
        return rowsAffected > 0;
    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
    }

    @Override
    public List<Reserva> listarReservasDetalles(int idCliente) {
         List<Reserva> lista = new ArrayList<>();
    String sql = "SELECT " +
        "r.id_reserva, " +
        "r.codigo_ticket, " +
        "m.nombre AS nombre_mascota, " +
        "GROUP_CONCAT(s.descripcion SEPARATOR ', ') AS servicios_contratados, " +
        "dr.monto_total_reserva AS monto_total_reserva, " +  // Mostrar monto_total_reserva directamente desde tbdetalle_reserva
        "d.nombre AS nombre_dormitorio, " +
        "r.proceso " +
        "FROM tbreserva r " +
        "JOIN tbdetalle_reserva dr ON r.id_detalle_reserva = dr.id_detalle_reserva " +
        "JOIN tbmascota m ON dr.id_mascota = m.id_mascota " +
        "JOIN tbreserva_servicio rs ON dr.id_detalle_reserva = rs.id_detalle_reserva " +
        "JOIN tbservicios s ON rs.id_servicio = s.id_servicio " +
        "JOIN tbdormitorios d ON r.id_dormitorio = d.id_dormitorio " +
        "WHERE r.id_cliente = ? " +
        "GROUP BY r.id_reserva, r.codigo_ticket, m.nombre, dr.monto_total_reserva, d.nombre, r.proceso";
    try (Connection con = cn.getConnection(); 
         PreparedStatement ps = con.prepareStatement(sql)) {
        ps.setInt(1, idCliente);
        try (ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Reserva detalle = new Reserva();
                detalle.setIdReserva(rs.getInt("id_reserva"));
                detalle.setCodigoTicket(rs.getInt("codigo_ticket"));
                detalle.setNombreMascota(rs.getString("nombre_mascota"));
                detalle.setServiciosContratados(rs.getString("servicios_contratados"));
                detalle.setMontoTotalReserva(rs.getFloat("monto_total_reserva"));
                detalle.setNombreDormitorio(rs.getString("nombre_dormitorio"));
                detalle.setProceso(rs.getString("proceso"));
                lista.add(detalle);
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return lista;
}

    @Override
    public String obtenerNombreDormitorio(int idDormitorio) {
       String sql = "SELECT nombre FROM tbdormitorios WHERE id_dormitorio = ?";
    try (Connection con = cn.getConnection();
         PreparedStatement ps = con.prepareStatement(sql)) {
        ps.setInt(1, idDormitorio);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            return rs.getString("nombre");
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }finally {
            try {
                if (rs != null) rs.close();
                if (cs != null) cs.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    return null;
    }

}
