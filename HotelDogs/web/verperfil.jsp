<%-- 
    Document   : verperfil
    Created on : 1 jul 2024, 11:43:01
    Author     : mario
--%>

<%@page import="java.util.Base64"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="Modelo.Cliente" %>
<%@ page import="javax.servlet.http.HttpSession" %>
<%
    Cliente cliente = (Cliente) session.getAttribute("usuario");
    if (cliente == null) {
        response.sendRedirect("login.jsp");
        return;
    }
%>

<!DOCTYPE html>
<html>
<head>
    <title>Perfil - La Granja Bull</title>
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
    <main>
        <div class="profile-container">
            <h2>Perfil</h2>
            <div class="profile-details">
                <div class="profile-picture">
                    <img src="data:image/jpeg;base64,<%= Base64.getEncoder().encodeToString(cliente.getImg_usuario()) %>" alt="Foto de Perfil">
                </div>
                <div class="profile-info">
                    <h3>Detalles</h3>
                    <p><strong>Nombre:</strong> <%= cliente.getNombre() %> <%= cliente.getApellido() %></p>
                    <p><strong>Dirección:</strong> <%= cliente.getDireccion() %></p>
                    <p><strong>Teléfono:</strong> <%= cliente.getTelefono() %></p>
                    <p><strong>Usuario:</strong> <%= cliente.getUsuario() %></p>
                </div>
            </div>
        </div>
    </main>
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