
package ModeloDAO;

import Config.Conexion;
import Interfaces.interfaceServicio;
import Modelo.Servicio;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

/**
 *
 * @author Mario
 */
public class ServicioDAO implements interfaceServicio {
Conexion cn = new Conexion();
    Connection con;
    CallableStatement cs;
    ResultSet rs;
     PreparedStatement ps;
    @Override
    public List<Servicio> listarServicios() {
       List<Servicio> servicios = new ArrayList<>();
        Conexion con = new Conexion();
        Connection cn = con.getConnection();
        String sql = "SELECT * FROM tbservicios";
        
        try {
            PreparedStatement ps = cn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Servicio servicio = new Servicio();
                servicio.setIdServicio(rs.getInt("id_servicio"));
                servicio.setDescripcion(rs.getString("descripcion"));
                servicio.setPrecio(rs.getDouble("precio"));
                servicios.add(servicio);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return servicios;
    }

    @Override
    public List<String> obtenerNombresServicios(int[] idServicios) {
       List<String> nombresServicios = new ArrayList<>();
    String sql = "SELECT descripcion FROM tbservicios WHERE id_servicio = ?";
    try (Connection con = cn.getConnection();
         PreparedStatement ps = con.prepareStatement(sql)) {
        for (int id : idServicios) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                nombresServicios.add(rs.getString("descripcion"));
            }
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
    return nombresServicios;
    }

}
