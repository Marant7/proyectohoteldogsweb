/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package Controlador;

import Modelo.Cliente;
import Modelo.Mascota;
import ModeloDAO.MascotaDAO;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
@MultipartConfig

public class ControladorMascota extends HttpServlet {
    MascotaDAO mascotaDAO = new MascotaDAO();
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
            out.println("<title>Servlet ControladorMascota</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ControladorMascota at " + request.getContextPath () + "</h1>");
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
        String action = request.getParameter("action");
        if (action != null) {
            switch (action) {
                case "listar":
                    listarMascotas(request, response);
                    break;
                case "editar":
                    mostrarFormularioEdicion(request, response);
                    break;
                default:
                    processRequest(request, response);
            }
        } else {
            processRequest(request, response);
        }
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
        if ("actualizar".equals(action)) {
            actualizarMascota(request, response);
        } else {
            registrarMascota(request, response);
        }
    }
    private void listarMascotas(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        HttpSession session = request.getSession();
    Cliente cliente = (Cliente) session.getAttribute("usuario");
    
    if (cliente == null) {
        response.sendRedirect("login.jsp");
        return;
    }
        List<Mascota> listaMascotas = mascotaDAO.listarMascotasPorCliente(cliente.getId_cliente());
        request.setAttribute("listaMascotas", listaMascotas);
        request.getRequestDispatcher("listarMascotas.jsp").forward(request, response);
    }

    private void mostrarFormularioEdicion(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        int idMascota = Integer.parseInt(request.getParameter("id"));
        Mascota mascota = mascotaDAO.obtenerPorId(idMascota);
        request.setAttribute("mascota", mascota);
        request.getRequestDispatcher("editarMascota.jsp").forward(request, response);
    }

    private void registrarMascota(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        Cliente cliente = (Cliente) session.getAttribute("usuario");
        
        if (cliente == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        String nombre = request.getParameter("nombre");
        String tamaño = request.getParameter("tamaño");
        String edadStr = request.getParameter("edad");
        String observacion = request.getParameter("observacion");

        if (nombre == null || nombre.isEmpty() || 
            tamaño == null || tamaño.isEmpty() ||
            edadStr == null || edadStr.isEmpty()) {
            response.sendRedirect("error.jsp");
            return;
        }

        int edad;
        try {
            edad = Integer.parseInt(edadStr);
        } catch (NumberFormatException e) {
            response.sendRedirect("error.jsp");
            return;
        }

        Part imgPart = request.getPart("imgMascota");
        byte[] imgMascota = null;
        if (imgPart != null && imgPart.getSize() > 0) {
            try (InputStream inputStream = imgPart.getInputStream()) {
                imgMascota = inputStream.readAllBytes();
            }
        }

        Mascota mascota = new Mascota();
        mascota.setNombre(nombre);
        mascota.setTamaño(tamaño);
        mascota.setEdad(edad);
        mascota.setIdCliente(cliente.getId_cliente());
        mascota.setImgMascota(imgMascota);
        mascota.setObservacion(observacion);

        boolean registrado = mascotaDAO.registrar(mascota);
        if (registrado) {
            response.sendRedirect("registrarMascota.jsp");
        } else {
            response.sendRedirect("error.jsp");
        }
    }

    private void actualizarMascota(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
    request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        int idMascota = Integer.parseInt(request.getParameter("idMascota"));
        String nombre = request.getParameter("nombre");
        String tamaño = request.getParameter("tamaño");
        String edadStr = request.getParameter("edad");
        String observacion = request.getParameter("observacion");

        System.out.println("Datos recibidos para actualización:");
        System.out.println("ID Mascota: " + idMascota);
        System.out.println("Nombre: " + nombre);
        System.out.println("Tamaño: " + tamaño);
        System.out.println("Edad: " + edadStr);
        System.out.println("Observación: " + observacion);

        if (nombre == null || nombre.isEmpty() || 
            tamaño == null || tamaño.isEmpty() ||
            edadStr == null || edadStr.isEmpty()) {
            response.sendRedirect("error.jsp");
            return;
        }

        int edad;
        try {
            edad = Integer.parseInt(edadStr);
        } catch (NumberFormatException e) {
            response.sendRedirect("error.jsp");
            return;
        }

        Mascota mascota = mascotaDAO.obtenerPorId(idMascota);
        if (mascota != null) {
            mascota.setNombre(nombre);
            mascota.setTamaño(tamaño);
            mascota.setEdad(edad);
            mascota.setObservacion(observacion);

            System.out.println("Datos enviados al DAO:");
            System.out.println("ID Mascota: " + mascota.getIdMascota());
            System.out.println("Nombre: " + mascota.getNombre());
            System.out.println("Tamaño: " + mascota.getTamaño());
            System.out.println("Edad: " + mascota.getEdad());
            System.out.println("Observación: " + mascota.getObservacion());

            boolean actualizado = mascotaDAO.actualizar(mascota);
            if (actualizado) {
                response.sendRedirect("ControladorMascota?action=listar");
            } else {
                response.sendRedirect("error.jsp");
            }
        } else {
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
