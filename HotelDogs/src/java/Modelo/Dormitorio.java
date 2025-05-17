
package Modelo;

/**
 *
 * @author Mario
 */
public class Dormitorio {
private int id_dormitorio;
    private String nombre;
    private int disponibilidad;

    public int getId_dormitorio() {
        return id_dormitorio;
    }

    public void setId_dormitorio(int id_dormitorio) {
        this.id_dormitorio = id_dormitorio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getDisponibilidad() {
        return disponibilidad;
    }

    public void setDisponibilidad(int disponibilidad) {
        this.disponibilidad = disponibilidad;
    }
}
