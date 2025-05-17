/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package Controlador;

import Modelo.Cliente;
import Modelo.Dormitorio;
import Modelo.Mascota;
import Modelo.Reserva;
import Modelo.Servicio;
import ModeloDAO.DetalleReservaDAO;
import ModeloDAO.MascotaDAO;
import ModeloDAO.ReservaDAO;
import ModeloDAO.ServicioDAO;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Random;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import ModeloDAO.PagoDAO;  // Añadir esta importación


public class ControladorReserva extends HttpServlet {
    ReservaDAO reservaDAO = new ReservaDAO();
    DetalleReservaDAO detalleReservaDAO = new DetalleReservaDAO();
    MascotaDAO mascotaDAO = new MascotaDAO();
    ServicioDAO servicioDAO = new ServicioDAO();
        PagoDAO pagoDAO = new PagoDAO();  // Añadir esta línea
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
            out.println("<title>Servlet ControladorReserva</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ControladorReserva at " + request.getContextPath () + "</h1>");
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
        if (null != action) switch (action) {
          case "mostrarDormitorios" -> verificarDisponibilidad(request, response);
          case "detalleReserva" -> mostrarDetalleReserva(request, response);
          case "listarReservasDetalles"-> listarReservasDetalles(request, response);
          default -> {
            }
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
       String action = request.getParameter("action");
    if ("crearReserva".equals(action)) {
         crearReserva(request, response);
    } 
    }
     private void verificarDisponibilidad(HttpServletRequest request, HttpServletResponse response)
throws ServletException, IOException {
    List<Dormitorio> listaDormitorios = reservaDAO.obtenerListaDormitorios();
    request.setAttribute("listaDormitorios", listaDormitorios);
    request.getRequestDispatcher("mostrarDormitorios.jsp").forward(request, response);
}

    private void mostrarDetalleReserva(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Cliente cliente = (Cliente) session.getAttribute("usuario");
        if (cliente == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        int dormitorio = Integer.parseInt(request.getParameter("dormitorio"));
        List<Mascota> listaMascotas = mascotaDAO.listarMascotasPorClienterereservados(cliente.getId_cliente());
        List<Servicio> listaServicios = servicioDAO.listarServicios();

        request.setAttribute("dormitorio", dormitorio);
        request.setAttribute("listaMascotas", listaMascotas);
        request.setAttribute("listaServicios", listaServicios);
        request.getRequestDispatcher("crearReserva.jsp").forward(request, response);
    }

    private void crearReserva(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            
            
        HttpSession session = request.getSession();
        Cliente cliente = (Cliente) session.getAttribute("usuario");

        if (cliente == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        int id_mascotaFK = Integer.parseInt(request.getParameter("id_mascotaFK"));
        String[] idServiciosStr = request.getParameterValues("id_servicioFK");
        int[] idServicios = new int[idServiciosStr.length];
        for (int i = 0; i < idServiciosStr.length; i++) {
            idServicios[i] = Integer.parseInt(idServiciosStr[i]);
        }

        float monto_total_reserva = Float.parseFloat(request.getParameter("monto_total_reserva"));
        //horas
        String fechaInicioStr = request.getParameter("fecha_inicio") + " " + request.getParameter("hora_inicio")+ ":00";
        String fechaFinStr = request.getParameter("fecha_fin") + " " + request.getParameter("hora_fin")+ ":00";
        int dormitorio = Integer.parseInt(request.getParameter("dormitorio")); // Capturar el valor de dormitorio

        
        
        Reserva reserva = new Reserva();
        reserva.setIdCliente(cliente.getId_cliente());
        reserva.setIdMascota(id_mascotaFK);
        reserva.setIdServicios(idServicios); // Establecer array de int aquí
        reserva.setMontoTotalReserva(monto_total_reserva);
        reserva.setEstado("pendiente");
    reserva.setProceso("pago");
   
        
        reserva.setCodigoTicket(new Random().nextInt(100000)); // Generar código de ticket aleatorio
              reserva.setFechaInicio(java.sql.Timestamp.valueOf(fechaInicioStr)); // Fecha actual
        reserva.setFechaFin(java.sql.Timestamp.valueOf(fechaFinStr)); // Fecha de fin

        boolean resultado = reservaDAO.crearReserva(reserva, dormitorio);

        if (resultado) {
                        // Registrar el pago en la base de datos de pagos
                pagoDAO.registrarPago(reserva.getIdReserva(), cliente.getId_cliente(), monto_total_reserva);
                // Obtener nombres en lugar de IDs
                String nombreMascota = mascotaDAO.obtenerNombreMascota(id_mascotaFK);
                List<String> nombresServicios = servicioDAO.obtenerNombresServicios(idServicios);
                String nombreDormitorio = reservaDAO.obtenerNombreDormitorio(dormitorio);

               

              generarPDF(response, reserva, nombreMascota, nombresServicios, nombreDormitorio);
              
               response.getWriter().write("PDF_GENERATED");

            // Redirigir al index.jsp después de la descarga del PDF
             // Redirigir al index.jsp después de la descarga del PDF
            
        } else {
            System.out.println("Error: Falló la creación de la reserva en la base de datos");
            response.sendRedirect("error.jsp");
        }
    } catch (Exception e) {
        e.printStackTrace();
        System.out.println("Error en el controlador: " + e.getMessage());
        response.sendRedirect("error.jsp");
    }
    }
     private void generarPDF(HttpServletResponse response, Reserva reserva, String nombreMascota, List<String> nombresServicios, String nombreDormitorio) throws IOException, DocumentException {
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=Reserva_" + reserva.getCodigoTicket() + ".pdf");

        Document document = new Document();
        try {
            PdfWriter.getInstance(document, response.getOutputStream());
            document.open();

          document.add(new Paragraph("Reserva Detalles"));
        document.add(new Paragraph("Código Ticket: " + reserva.getCodigoTicket()));
        document.add(new Paragraph("Nombre Mascota: " + nombreMascota));
        document.add(new Paragraph("Servicios Contratados: " + String.join(", ", nombresServicios)));
        document.add(new Paragraph("Precio Total: " + reserva.getMontoTotalReserva()));
        document.add(new Paragraph("Numero Dormitorio: " + nombreDormitorio));
       // document.add(new Paragraph("Proceso: " + reserva.getProceso()));

            document.close();
        } catch (DocumentException e) {
            throw new IOException(e);
        }
    }


 private void listarReservasDetalles(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         
          // Obtener la sesión actual
    HttpSession session = request.getSession();
    // Obtener el cliente de la sesión
    Cliente cliente = (Cliente) session.getAttribute("usuario");

    // Verificar si el cliente está autenticado
    if (cliente == null) {
        // Redirigir a la página de inicio de sesión si no está autenticado
        response.sendRedirect("login.jsp");
        return;
    }

    // Obtener la lista de reservas y sus detalles para el cliente autenticado
    List<Reserva> listaReservasDetalles = reservaDAO.listarReservasDetalles(cliente.getId_cliente());
    // Establecer la lista como un atributo de la solicitud
    request.setAttribute("listaReservasDetalles", listaReservasDetalles);
    // Reenviar la solicitud y respuesta a la página JSP correspondiente
    request.getRequestDispatcher("verReservasDetalles.jsp").forward(request, response);
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
