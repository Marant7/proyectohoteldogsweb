
package Modelo;

/**
 *
 * @author Mario
 */
public class Cliente {
 private int id_cliente;
    private String nombre;
    private String apellido;
    private String direccion;
    private String telefono;
    private String usuario;
    private String clave;
    private byte[] img_usuario;

    // Constructor por defecto
    public Cliente() {
    }

    // Constructor con parámetros
    public Cliente(int id_cliente, String nombre, String apellido, String direccion, String telefono, String usuario, String clave, byte[] img_usuario) {
        this.id_cliente = id_cliente;
        this.nombre = nombre;
        this.apellido = apellido;
        this.direccion = direccion;
        this.telefono = telefono;
        this.usuario = usuario;
        this.clave = clave;
        this.img_usuario = img_usuario;
    }

    // Getters y Setters
    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public byte[] getImg_usuario() {
        return img_usuario;
    }

    public void setImg_usuario(byte[] img_usuario) {
        this.img_usuario = img_usuario;
    }
}
