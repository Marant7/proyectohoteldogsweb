
package Interfaces;

import Modelo.Cliente;

 
public interface interfaceCliente {
 public boolean registrar(Cliente cliente);
 public Cliente autenticar(String usuario, String clave);
 public boolean actualizar(Cliente cliente);
}
