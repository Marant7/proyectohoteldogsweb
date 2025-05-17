/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package Controlador;

import Modelo.Cliente;
import ModeloDAO.ClienteDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

@MultipartConfig
public class ControladorCliente extends HttpServlet {
    ClienteDAO clienteDAO = new ClienteDAO();
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ControladorCliente</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ControladorCliente at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
       request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");

        if ("registrar".equals(action)) {
            registrarCliente(request, response);
        } else if ("login".equals(action)) {
            loginCliente(request, response);
        } else if ("editarPerfil".equals(action)) {
            editarPerfil(request, response);
        }
    }
    private void registrarCliente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String nombre = request.getParameter("nombre");
            String apellido = request.getParameter("apellido");
            String direccion = request.getParameter("direccion");
            String telefono = request.getParameter("telefono");
            String usuario = request.getParameter("usuario");
            String clave = request.getParameter("clave");
            Part imgPart = request.getPart("img_usuario");
            byte[] img_usuario = null;

            if (imgPart != null && imgPart.getSize() > 0) {
                img_usuario = imgPart.getInputStream().readAllBytes();
            }

            if (nombre == null) System.out.println("Error: Parámetro 'nombre' es nulo");
            if (apellido == null) System.out.println("Error: Parámetro 'apellido' es nulo");
            if (direccion == null) System.out.println("Error: Parámetro 'direccion' es nulo");
            if (telefono == null) System.out.println("Error: Parámetro 'telefono' es nulo");
            if (usuario == null) System.out.println("Error: Parámetro 'usuario' es nulo");
            if (clave == null) System.out.println("Error: Parámetro 'clave' es nulo");
            if (img_usuario == null) System.out.println("Advertencia: Parámetro 'img_usuario' es nulo o vacío");

            if (nombre == null || apellido == null || direccion == null || telefono == null || usuario == null || clave == null) {
                System.out.println("Error: Uno o más parámetros son nulos");
                response.sendRedirect("error.jsp");
                return;
            }

            Cliente cliente = new Cliente();
            cliente.setNombre(nombre);
            cliente.setApellido(apellido);
            cliente.setDireccion(direccion);
            cliente.setTelefono(telefono);
            cliente.setUsuario(usuario);
            cliente.setClave(clave);
            cliente.setImg_usuario(img_usuario);

            boolean resultado = clienteDAO.registrar(cliente);
            if (resultado) {
                response.sendRedirect("login.jsp");
            } else {
                System.out.println("Error: Falló el registro del cliente en la base de datos");
                response.sendRedirect("error.jsp");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error en el controlador: " + e.getMessage());
            response.sendRedirect("error.jsp");
        }
    }

    private void loginCliente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
             String usuario = request.getParameter("usuario");
            String clave = request.getParameter("clave");

              // Agregar mensajes de depuración
            System.out.println("Usuario: " + usuario);
            System.out.println("Clave: " + clave);

            
            Cliente cliente = clienteDAO.autenticar(usuario, clave);
            if (cliente != null) {
                HttpSession session = request.getSession();
                session.setAttribute("usuario", cliente);
                response.sendRedirect("index.jsp");
            } else {
                response.sendRedirect("login.jsp?error=1");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error en el controlador: " + e.getMessage());
            response.sendRedirect("error.jsp");
        }
    }
    private void editarPerfil(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            HttpSession session = request.getSession();
            Cliente cliente = (Cliente) session.getAttribute("usuario");

            if (cliente == null) {
                response.sendRedirect("login.jsp");
                return;
            }

            String nombre = request.getParameter("nombre");
            String apellido = request.getParameter("apellido");
            String direccion = request.getParameter("direccion");
            String telefono = request.getParameter("telefono");
            String usuario = request.getParameter("usuario");
            String clave = request.getParameter("clave");
            Part imgPart = request.getPart("img_usuario");
            byte[] img_usuario = null;

            if (imgPart != null && imgPart.getSize() > 0) {
                img_usuario = imgPart.getInputStream().readAllBytes();
            }

            if (nombre == null || apellido == null || direccion == null || telefono == null || usuario == null) {
                System.out.println("Error: Uno o más parámetros son nulos");
                response.sendRedirect("error.jsp");
                return;
            }

            cliente.setNombre(nombre);
            cliente.setApellido(apellido);
            cliente.setDireccion(direccion);
            cliente.setTelefono(telefono);
            cliente.setUsuario(usuario);

            if (clave != null && !clave.isEmpty()) {
                cliente.setClave(clave);
            }

            if (img_usuario != null && img_usuario.length > 0) {
                cliente.setImg_usuario(img_usuario);
            }

            boolean resultado = clienteDAO.actualizar(cliente);
            if (resultado) {
                session.setAttribute("usuario", cliente);
                response.sendRedirect("verperfil.jsp");
            } else {
                System.out.println("Error: Falló la actualización del cliente en la base de datos");
                response.sendRedirect("error.jsp");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error en el controlador: " + e.getMessage());
            response.sendRedirect("error.jsp");
        }
    }


    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
