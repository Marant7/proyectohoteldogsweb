
package Modelo;

import java.util.Date;

/**
 *
 * @author Mario
 */
public class Reserva {
 private int idReserva;
    private int idCliente;
    private int idDetalleReserva;
    private int codigoTicket;
    private Date fechaInicio;
    private Date fechaFin;
    private String estado;
    private String proceso;
    private int disponibilidad;
    private float precioTotal;
    private String nombreDormitorio;

    public float getPrecioTotal() {
        return precioTotal;
    }

    public void setPrecioTotal(float precioTotal) {
        this.precioTotal = precioTotal;
    }

    public String getNombreDormitorio() {
        return nombreDormitorio;
    }

    public void setNombreDormitorio(String nombreDormitorio) {
        this.nombreDormitorio = nombreDormitorio;
    }

    public String getNombreMascota() {
        return nombreMascota;
    }

    public void setNombreMascota(String nombreMascota) {
        this.nombreMascota = nombreMascota;
    }

    public String getServiciosContratados() {
        return serviciosContratados;
    }

    public void setServiciosContratados(String serviciosContratados) {
        this.serviciosContratados = serviciosContratados;
    }
        private String nombreMascota;
    private String serviciosContratados;

        
        
        private int[] idServicios;

    public String getProceso() {
        return proceso;
    }

    public void setProceso(String proceso) {
        this.proceso = proceso;
    }

    // Otros getters y setters...
    public int[] getIdServicios() {
        return idServicios;
    }

    public void setIdServicios(int[] idServicios) {
        this.idServicios = idServicios;
    }
    
    // Campos adicionales para facilitar la creación de la reserva
    private int idMascota;

    private float montoTotalReserva;

    // Getters y Setters

    public int getIdReserva() {
        return idReserva;
    }

    public void setIdReserva(int idReserva) {
        this.idReserva = idReserva;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public int getIdDetalleReserva() {
        return idDetalleReserva;
    }

    public void setIdDetalleReserva(int idDetalleReserva) {
        this.idDetalleReserva = idDetalleReserva;
    }

    public int getCodigoTicket() {
        return codigoTicket;
    }

    public void setCodigoTicket(int codigoTicket) {
        this.codigoTicket = codigoTicket;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getDisponibilidad() {
        return disponibilidad;
    }

    public void setDisponibilidad(int disponibilidad) {
        this.disponibilidad = disponibilidad;
    }
    
    // Getters y Setters para los campos adicionales

    public int getIdMascota() {
        return idMascota;
    }

    public void setIdMascota(int idMascota) {
        this.idMascota = idMascota;
    }



    public float getMontoTotalReserva() {
        return montoTotalReserva;
    }

    public void setMontoTotalReserva(float montoTotalReserva) {
        this.montoTotalReserva = montoTotalReserva;
    }
  
}
