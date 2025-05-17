
package Interfaces;

import Modelo.Servicio;
import java.util.List;


public interface interfaceServicio {
public List<Servicio> listarServicios(); 
     public List<String> obtenerNombresServicios(int[] idServicios);
}
