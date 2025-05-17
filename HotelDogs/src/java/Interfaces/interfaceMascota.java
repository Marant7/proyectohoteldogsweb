
package Interfaces;



import Modelo.Mascota;
import java.util.List;


 
public interface interfaceMascota {
    public boolean registrar(Mascota mascota);
    public List<Mascota> listar();
    public Mascota obtenerPorId(int idMascota);
    public boolean actualizar(Mascota mascota);
    public List<Mascota> listarMascotasPorCliente(int idCliente);
    public List<Mascota> listarMascotasPorClienterereservados(int idCliente);
    public String obtenerNombreMascota(int idMascota);
}
