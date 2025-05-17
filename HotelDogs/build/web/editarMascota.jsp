<%-- 
    Document   : editarMascota
    Created on : 29 jun 2024, 20:00:16
    Author     : mario
--%>
<%@page import="Modelo.Mascota"%>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Editar Mascota</title>
       <link rel="stylesheet" type="text/css" href="styles.css">
    <script src="script.js"></script>
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
                <li><a href="#">Reservas</a></li>
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
    <div class="editarmascota-container">
    <h1>Editar Mascota</h1>
    <form action="ControladorMascota?action=actualizar" method="post">
        <input type="hidden" id="idMascota" name="idMascota" value="<%= ((Mascota)request.getAttribute("mascota")).getIdMascota() %>">
        <label for="nombre">Nombre:</label>
        <input type="text" id="nombre" name="nombre" value="<%= ((Mascota)request.getAttribute("mascota")).getNombre() %>" required><br>

        <label for="tamaño">Tamaño:</label>
        <select id="tamaño" name="tamaño" required>
            <option value="Pequeño" <%= ((Mascota)request.getAttribute("mascota")).getTamaño().equals("Pequeño") ? "selected" : "" %>>Pequeño</option>
            <option value="Mediano" <%= ((Mascota)request.getAttribute("mascota")).getTamaño().equals("Mediano") ? "selected" : "" %>>Mediano</option>
            <option value="Grande" <%= ((Mascota)request.getAttribute("mascota")).getTamaño().equals("Grande") ? "selected" : "" %>>Grande</option>
        </select><br>

        <label for="edad">Edad:</label>
        <input type="number" id="edad" name="edad" value="<%= ((Mascota)request.getAttribute("mascota")).getEdad() %>" required><br>

        <label for="observacion">Observación:</label>
        <textarea id="observacion" name="observacion"><%= ((Mascota)request.getAttribute("mascota")).getObservacion() %></textarea><br>

        <input type="submit" value="Actualizar">
    </form>
    <a href="ControladorMascota?action=listar">Ver Mascotas Registradas</a>
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