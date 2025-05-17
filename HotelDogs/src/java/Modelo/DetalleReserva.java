
package Modelo;

/**
 *
 * @author Mario
 */
public class DetalleReserva {
   private int idDetalleReserva;
    private int idMascota;
    private int idServicio;
    private float montoTotalReserva;

    // Getters y Setters

    public int getIdDetalleReserva() {
        return idDetalleReserva;
    }

    public void setIdDetalleReserva(int idDetalleReserva) {
        this.idDetalleReserva = idDetalleReserva;
    }

    public int getIdMascota() {
        return idMascota;
    }

    public void setIdMascota(int idMascota) {
        this.idMascota = idMascota;
    }

    public int getIdServicio() {
        return idServicio;
    }

    public void setIdServicio(int idServicio) {
        this.idServicio = idServicio;
    }

    public float getMontoTotalReserva() {
        return montoTotalReserva;
    }

    public void setMontoTotalReserva(float montoTotalReserva) {
        this.montoTotalReserva = montoTotalReserva;
    }
}