package Modelo;

/**
 *
 * @author Mario
 */
public class Mascota {

    public Mascota() {
    }

    public Mascota(int idMascota, int idCliente, String nombre, String tamaño, int edad, byte[] imgMascota, String observacion) {
        this.idMascota = idMascota;
        this.idCliente = idCliente;
        this.nombre = nombre;
        this.tamaño = tamaño;
        this.edad = edad;
        this.imgMascota = imgMascota;
        this.observacion = observacion;
    }

    private int idMascota;
    private int idCliente;
    private String nombre;
    private String tamaño;
    private int edad;
    private byte[] imgMascota;
    private String observacion;

    // Getters y Setters
    public int getIdMascota() {
        return idMascota;
    }

    public void setIdMascota(int idMascota) {
        this.idMascota = idMascota;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTamaño() {
        return tamaño;
    }

    public void setTamaño(String tamaño) {
        this.tamaño = tamaño;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public byte[] getImgMascota() {
        return imgMascota;
    }

    public void setImgMascota(byte[] imgMascota) {
        this.imgMascota = imgMascota;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }
}