<%-- 
    Document   : login
    Created on : 1 jul 2024, 11:41:54
    Author     : mario
--%>

--%><%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <title>Iniciar Sesión - La Granja Bull</title>
    <link rel="stylesheet" type="text/css" href="styles.css">
     <script src="scripts.js" defer></script>
</head>
<body>
    <header>
        <h1>La Granja Bull</h1>
        <nav>
            <ul>
                <li><a href="index_principal.jsp">Inicio</a></li>
                <li><a href="login.jsp">Reservas</a></li>
                <li><a href="login.jsp">Servicios</a></li>
                <li><a href="login.jsp">Mascotas</a></li>
                <li><a href="login.jsp">Iniciar Sesión</a></li>
            </ul>
        </nav>
    </header>
    <main>
        <div class="login-container">
            <h2>Iniciar Sesión</h2>
            <form action="ControladorCliente" method="post">
                <input type="hidden" name="action" value="login">
                <div class="form-group">
                    <label for="usuario">Usuario:</label>
                    <input type="text" id="usuario" name="usuario" required>
                </div>
                <div class="form-group">
                    <label for="clave">Contraseña:</label>
                    <input type="password" id="clave" name="clave" required>
                </div>
                <div class="form-group">
                    <button type="submit">Ingresar</button>
                </div>
            </form>
            <c:if test="${param.error == 1}">
                <p style="color: red;">Usuario o contraseña incorrectos.</p>
            </c:if>
            <p>¿Aún no tienes cuenta? <a href="registrar.jsp">Regístrate</a></p>
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

