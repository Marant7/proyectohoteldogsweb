
package ModeloDAO;

import Config.Conexion;
import Interfaces.interfaceCliente;
import Modelo.Cliente;
import java.sql.*;
/**
 *
 * @author Mario
 */
public class ClienteDAO implements interfaceCliente {
Conexion cn = new Conexion();
    Connection con;
    @Override
    public boolean registrar(Cliente cliente) {
        String sql = "{CALL registrarCliente(?, ?, ?, ?, ?, ?, ?)}";
        try {
            con = cn.getConnection();
            CallableStatement cs = con.prepareCall(sql);
            cs.setString(1, cliente.getNombre());
            cs.setString(2, cliente.getApellido());
            cs.setString(3, cliente.getDireccion());
            cs.setString(4, cliente.getTelefono());
            cs.setString(5, cliente.getUsuario());
            cs.setString(6, cliente.getClave());
            cs.setBytes(7, cliente.getImg_usuario());
            cs.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Cliente autenticar(String usuario, String clave) {
      if ("admin".equals(usuario) && "admin".equals(clave)) {
        Cliente cliente = new Cliente();
        cliente.setId_cliente(0);
        cliente.setNombre("Admin");
        cliente.setApellido("Administrador");
        cliente.setDireccion("Oficina central");
        cliente.setTelefono("000000000");
        cliente.setUsuario("admin");
        cliente.setClave("admin");
        cliente.setImg_usuario(null); // o alguna imagen por defecto si lo necesitas
        return cliente;
    } else {
        return null;
    }
    }

    @Override
    public boolean actualizar(Cliente cliente) {
       String sqlConImagen = "{CALL actualizarCliente(?, ?, ?, ?, ?, ?, ?, ?)}";
    String sqlSinImagen = "{CALL actualizarCliente(?, ?, ?, ?, ?, ?, ?, NULL)}"; // Aquí ajustamos para pasar NULL cuando no hay imagen
    try {
        con = cn.getConnection();
        CallableStatement cs = con.prepareCall(cliente.getImg_usuario() != null ? sqlConImagen : sqlSinImagen);
        cs.setInt(1, cliente.getId_cliente());
        cs.setString(2, cliente.getNombre());
        cs.setString(3, cliente.getApellido());
        cs.setString(4, cliente.getDireccion());
        cs.setString(5, cliente.getTelefono());
        cs.setString(6, cliente.getUsuario());
        if (cliente.getClave() != null && !cliente.getClave().isEmpty()) {
            cs.setString(7, cliente.getClave());
        } else {
            cs.setNull(7, java.sql.Types.VARCHAR);
        }
        if (cliente.getImg_usuario() != null) {
            cs.setBytes(8, cliente.getImg_usuario());
        }
        int rowsAffected = cs.executeUpdate();
        return rowsAffected > 0;
    } catch (Exception e) {
        e.printStackTrace();
        System.out.println("Error al actualizar el cliente: " + e.getMessage());
        return false;
    }
    }

}
