
package ModeloDAO;
import Config.Conexion;
import Interfaces.interfaceMascota;
import Modelo.Mascota;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Mario
 */
public class MascotaDAO implements interfaceMascota {
   Conexion cn = new Conexion();
    Connection con;
    CallableStatement cs;
    ResultSet rs;
     PreparedStatement ps;
    @Override
    public boolean registrar(Mascota mascota) {
        String sql = "CALL registrarMascota(?, ?, ?, ?, ?, ?)";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
           ps.setInt(1, mascota.getIdCliente());
            ps.setString(2, mascota.getNombre());
            ps.setString(3, mascota.getTamaño());
            ps.setInt(4, mascota.getEdad());
            ps.setBytes(5, mascota.getImgMascota());
            ps.setString(6, mascota.getObservacion());
            
        System.out.println("Valores enviados al procedimiento almacenado:");
        System.out.println("ID Cliente: " + mascota.getIdCliente());
        System.out.println("Nombre: " + mascota.getNombre());
        System.out.println("Tamaño: " + mascota.getTamaño());
        System.out.println("Edad: " + mascota.getEdad());
        System.out.println("Observación: " + mascota.getObservacion());
           ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Mascota> listar() {
       List<Mascota> lista = new ArrayList<>();
        String sql = "CALL listarMascotas()";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Mascota mascota = new Mascota();
                mascota.setIdMascota(rs.getInt("id_mascota"));
                mascota.setIdCliente(rs.getInt("id_cliente"));
                mascota.setNombre(rs.getString("nombre"));
                mascota.setTamaño(rs.getString("tamaño"));
                mascota.setEdad(rs.getInt("edad"));
                mascota.setImgMascota(rs.getBytes("img_mascota"));
                mascota.setObservacion(rs.getString("observacion"));
                lista.add(mascota);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    @Override
    public Mascota obtenerPorId(int idMascota) {
         Mascota mascota = null;
        String sql = "SELECT * FROM tbmascota WHERE id_mascota = ?";
        try {
            con = cn.getConnection();
             ps = con.prepareStatement(sql);
          ps.setInt(1, idMascota);
            rs = ps.executeQuery();
            if (rs.next()) {
                mascota = new Mascota();
                mascota.setIdMascota(rs.getInt("id_mascota"));
                mascota.setIdCliente(rs.getInt("id_cliente"));
                mascota.setNombre(rs.getString("nombre"));
                mascota.setTamaño(rs.getString("tamaño"));
                mascota.setEdad(rs.getInt("edad"));
                mascota.setImgMascota(rs.getBytes("img_mascota"));
                mascota.setObservacion(rs.getString("observacion"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return mascota;
    }

    @Override
    public boolean actualizar(Mascota mascota) {
        String sql = "CALL actualizarMascotaSinImagen(?, ?, ?, ?, ?, ?)";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, mascota.getIdMascota());
            ps.setInt(2, mascota.getIdCliente());
            ps.setString(3, mascota.getNombre());
            ps.setString(4, mascota.getTamaño());
            ps.setInt(5, mascota.getEdad());
            ps.setString(6, mascota.getObservacion());

            System.out.println("Valores enviados al procedimiento almacenado:");
            System.out.println("ID Mascota: " + mascota.getIdMascota());
            System.out.println("ID Cliente: " + mascota.getIdCliente());
            System.out.println("Nombre: " + mascota.getNombre());
            System.out.println("Tamaño: " + mascota.getTamaño());
            System.out.println("Edad: " + mascota.getEdad());
            System.out.println("Observación: " + mascota.getObservacion());

            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Mascota> listarMascotasPorCliente(int idCliente) {
        List<Mascota> mascotas = new ArrayList<>();
        Conexion con = new Conexion();
        Connection cn = con.getConnection();
        String sql = "SELECT * FROM tbmascota WHERE id_cliente = ?";

        try {
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setInt(1, idCliente);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Mascota mascota = new Mascota();
                mascota.setIdMascota(rs.getInt("id_mascota"));
                mascota.setIdCliente(rs.getInt("id_cliente"));
                mascota.setNombre(rs.getString("nombre"));
                mascota.setTamaño(rs.getString("tamaño"));
                mascota.setEdad(rs.getInt("edad"));
                mascota.setImgMascota(rs.getBytes("img_mascota"));
                mascota.setObservacion(rs.getString("observacion"));
                mascotas.add(mascota);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return mascotas;
    }

    @Override
    public List<Mascota> listarMascotasPorClienterereservados(int idCliente) {
       List<Mascota> lista = new ArrayList<>();
    String sql = "{CALL listarMascotasDisponiblesPorCliente(?)}";
    try {
        con = cn.getConnection();
        CallableStatement cs = con.prepareCall(sql);
        cs.setInt(1, idCliente);
        rs = cs.executeQuery();
        while (rs.next()) {
            Mascota mascota = new Mascota();
            mascota.setIdMascota(rs.getInt("id_mascota"));
            mascota.setIdCliente(rs.getInt("id_cliente"));
            mascota.setNombre(rs.getString("nombre"));
            mascota.setTamaño(rs.getString("tamaño"));
            mascota.setEdad(rs.getInt("edad"));
            mascota.setImgMascota(rs.getBytes("img_mascota"));
            mascota.setObservacion(rs.getString("observacion"));
            lista.add(mascota);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return lista;
    }

    @Override
    public String obtenerNombreMascota(int idMascota) {
       String sql = "SELECT nombre FROM tbmascota WHERE id_mascota = ?";
    try (Connection con = cn.getConnection();
         PreparedStatement ps = con.prepareStatement(sql)) {
        ps.setInt(1, idMascota);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            return rs.getString("nombre");
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return null;
    }

}
