package Interfaces;

import Modelo.Dormitorio;
import Modelo.Reserva;
import java.util.List;

 
public interface interfaceReserva {
 public int verificarDisponibilidadDormitorios();
 public boolean crearReserva(Reserva reserva, int dormitorio);
public List<Dormitorio> obtenerListaDormitorios();
public boolean liberarDormitorios();
  public List<Reserva> listarReservasDetalles(int idCliente) ;
  public String obtenerNombreDormitorio(int idDormitorio) ;
}
