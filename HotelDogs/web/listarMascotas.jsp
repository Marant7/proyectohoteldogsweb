<%-- 
    Document   : listarMascotas
    Created on : 29 jun 2024, 19:27:01
    Author     : mario
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="Modelo.Mascota" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Lista de Mascotas</title>
    <style>
        table {
            width: 100%;
            border-collapse: collapse;
        }
        table, th, td {
            border: 1px solid black;
        }
        th, td {
            padding: 10px;
            text-align: left;
        }
        .hidden {
            display: none;
        }
    </style>
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
    <div class="tablalistarmascotas">
        <h1>Lista de Mascotas</h1>
        <table>
            <thead>
                <tr>
                    <th class="hidden">ID Mascota</th>
                    <th class="hidden">ID Cliente</th>
                    <th>Nombre</th>
                    <th>Tamaño</th>
                    <th>Edad</th>
                    <th>Imagen</th>
                    <th>Observación</th>
                    <th>Operaciones</th>
                </tr>
            </thead>
            <tbody>
                <%
                    List<Mascota> listaMascotas = (List<Mascota>) request.getAttribute("listaMascotas");
                    if (listaMascotas != null) {
                        for (Mascota mascota : listaMascotas) {
                %>
                <tr>
                    <td class="hidden"><%= mascota.getIdMascota() %></td>
                    <td class="hidden"><%= mascota.getIdCliente() %></td>
                    <td><%= mascota.getNombre() %></td>
                    <td><%= mascota.getTamaño() %></td>
                    <td><%= mascota.getEdad() %></td>
                    <td>
                        <% if (mascota.getImgMascota() != null) { %>
                            <img src="data:image/jpeg;base64,<%= java.util.Base64.getEncoder().encodeToString(mascota.getImgMascota()) %>" alt="Imagen de la Mascota" width="100" height="100"/>
                        <% } %>
                    </td>
                    <td><%= mascota.getObservacion() %></td>
                    <td>
                        <a href="ControladorMascota?action=editar&id=<%= mascota.getIdMascota() %>">Editar</a>
                    </td>
                </tr>
                <%
                        }
                    }
                %>
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