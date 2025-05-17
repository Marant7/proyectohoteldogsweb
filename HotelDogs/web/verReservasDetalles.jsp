<%-- 
    Document   : verReservasDetalles
    Created on : 30 jun 2024, 19:43:57
    Author     : mario
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Lista de Reservas Detallada</title>
    <link rel="stylesheet" type="text/css" href="styles.css">
</head>
<body>
    <header>
        <h1>La Granja Bull</h1>
        <nav>
            <ul>
                <li><a href="index.jsp">Inicio</a></li>
                <li class="dropdown">
                    <a href="javascript:void(0)" class="dropbtn">Mi Perfil</a>
                    <div class="dropdown-content">
                        <a href="verperfil.jsp">Ver Perfil</a>
                        <a href="editarperfil.jsp">Editar Perfil</a>
                    </div>
                </li>
               
                <li class="dropdown">
                    <a href="javascript:void(0)" class="dropbtn">Reservas</a>
                    <div class="dropdown-content">
                        <a href="ControladorReserva?action=mostrarDormitorios">Crear Reservas</a>
                        <a href="ControladorReserva?action=listarReservasDetalles">Mis Reservas</a>
                    </div>
                </li>
                <li><a href="#">Servicios</a></li>
                <li class="dropdown">
                    <a href="javascript:void(0)" class="dropbtn">Mascota</a>
                    <div class="dropdown-content">
                        <a href="ControladorMascota?action=listar">Editar Mascota</a>
                        <a href="registrarMascota.jsp">Registrar Mascota</a>
                    </div>
                </li>
                <li><a href="login.jsp">Cerrar Sesión</a></li>
            </ul>
        </nav>
    </header>
      <div class="tablaverreservas">
        <h2>Lista de Reservas Detallada</h2>
        <table>
            <thead>
                <tr>
              
                    <th>Código Ticket</th>
                    <th>Nombre Mascota</th>
                    <th>Servicios Contratados</th>
                    <th>Precio Total</th>
                    <th>Nombre Dormitorio</th>
                    <th>Proceso</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="reserva" items="${listaReservasDetalles}">
                    <tr>
                        
                        <td>${reserva.codigoTicket}</td>
                        <td>${reserva.nombreMascota}</td>
                        <td>${reserva.serviciosContratados}</td>
                        <td>${reserva.montoTotalReserva}</td>
                        <td>${reserva.nombreDormitorio}</td>
                        <td>${reserva.proceso}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
      <footer>
        <div class="footer-content">
            <div class="footer-section contact">
                <h3>Contáctanos</h3>
                <p>Pocollay:</p>
                <p>Teléfono: (01) 3635937</p>
                <p>WhatsApp: +51 991 328 751</p>
                <p>Dirección: Av.los Angeles 756, Tacna, Perú</p>
                <p>Pocollay:</p>
                <p>Teléfono: (01) 7682864</p>
                <p>WhatsApp: +51 970 307 676</p>
                <p>Dirección: Av.los Angeles 765, Tacna, Perú</p>
            </div>
            <div class="footer-section hours">
                <h3>Horario de atención</h3>
                <p>Informacion</p>
                <p>Lunes a domingos de 9 am a 9 pm</p>
                <p>Emergencias 24 horas (Pocollay)</p>
            </div>
            <div class="footer-section social">
                <h3>Síguenos</h3>
                <div class="social-icons">
                    <a href="#"><img src="images/facebook.png" alt="Facebook"></a>
                </div>
            </div>
        </div>
        <div class="footer-bottom">
            <p>&copy; 2024 Hotel para Perros. Todos los derechos reservados. Developed by [EPIS]</p>
        </div>
    </footer>
</body>
</html>