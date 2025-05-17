<%-- 
    Document   : crearReserva
    Created on : 29 jun 2024, 23:07:27
    Author     : mario
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.List" %>
<%@ page import="Modelo.Mascota" %>
<%@ page import="Modelo.Servicio" %>
<%@ page import="Modelo.Cliente" %>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
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
    <title>Crear Reserva</title>
    <link rel="stylesheet" type="text/css" href="styles.css">
    <script>
        function calcularMontoTotal() {
            var select = document.getElementById("id_servicioFK");
            var total = 0;
            for (var i = 0; i < select.options.length; i++) {
                if (select.options[i].selected) {
                    var precio = parseFloat(select.options[i].getAttribute("data-precio"));
                    total += precio;
                }
            }

            var fechaInicio = new Date(document.getElementById("fecha_inicio").value + 'T00:00:00');
            var fechaFin = new Date(document.getElementById("fecha_fin").value + 'T00:00:00');

            if (fechaFin >= fechaInicio) {
                var tiempoEstancia = fechaFin.getTime() - fechaInicio.getTime();
                var diasEstancia = tiempoEstancia / (1000 * 3600 * 24) + 1; // Incluyendo el día de inicio

                total *= diasEstancia;
            }

            document.getElementById("monto_total_reserva").value = total.toFixed(2);
        }

        function validateDates() {
            var fechaInicio = new Date(document.getElementById("fecha_inicio").value + 'T' + document.getElementById("hora_inicio").value);
            var fechaFin = new Date(document.getElementById("fecha_fin").value + 'T' + document.getElementById("hora_fin").value);

            if (fechaFin <= fechaInicio) {
                alert("La fecha y hora de fin deben ser posteriores a la fecha y hora de inicio.");
                return false;
            }

            return true;
        } 
        function redirigirAIndex() {
            setTimeout(function() {
                window.location.href = "index.jsp";
            }, 1000); // espera un segundo para la redirección
        }
        function generatePDF() {
            window.open('ControladorReserva'); // Cambia 'your_servlet_url_here' por la URL de tu servlet que genera el PDF
            redirigirAIndex();
        }
        

        document.addEventListener('DOMContentLoaded', function() {
            var today = new Date().toISOString().split('T')[0];
            document.getElementById("fecha_inicio").setAttribute('min', today);
            document.getElementById("fecha_fin").setAttribute('min', today);

            document.getElementById("id_servicioFK").addEventListener("change", calcularMontoTotal);
            document.getElementById("fecha_inicio").addEventListener("change", calcularMontoTotal);
            document.getElementById("fecha_fin").addEventListener("change", calcularMontoTotal);

            document.getElementById("crearReservaForm").addEventListener("submit", function(event) {
                event.preventDefault(); // Evita el envío del formulario inmediato
                if (validateDates()) {
                    setTimeout(function() {
                        document.getElementById("crearReservaForm").submit(); // Envía el formulario después de 1 segundo
                        generatePDF();
                    }, 1000);
                }
            });
        });
    </script>
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
                <li><a href="ControladorReserva?action=mostrarDormitorios">Reservas</a></li>
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
    <div class="crearreservacontainer">
    <main>
        <h2>Crear Reserva</h2>
         
        <form id="crearReservaForm" action="ControladorReserva" method="post" >
            <input type="hidden" name="action" value="crearReserva">
            <input type="hidden" name="dormitorio" value="${dormitorio}">

            <label for="id_mascotaFK">Mascota:</label>
            <select name="id_mascotaFK" id="id_mascotaFK" required>
                <c:forEach var="mascota" items="${listaMascotas}">
                    <option value="${mascota.idMascota}">${mascota.nombre}</option>
                </c:forEach>
            </select>
<label for="id_servicioFK">Servicio:</label>
                <select name="id_servicioFK" id="id_servicioFK" required onchange="calcularMontoTotal()">
                    <c:forEach var="servicio" items="${listaServicios}">
                        <option value="${servicio.idServicio}" data-precio="${servicio.precio}">${servicio.descripcion} - ${servicio.precio}</option>
                    </c:forEach>
                </select>

            <label for="monto_total_reserva">Monto Total:</label>
            <input type="number" name="monto_total_reserva" id="monto_total_reserva" step="0.01" readonly required>

            <label for="fecha_inicio">Fecha Inicio:</label>
            <input type="date" name="fecha_inicio" id="fecha_inicio" required>
            <input type="time" name="hora_inicio" id="hora_inicio" required>

            <label for="fecha_fin">Fecha Fin:</label>
            <input type="date" name="fecha_fin" id="fecha_fin" required>
            <input type="time" name="hora_fin" id="hora_fin" required>

            

            <button type="submit">Crear Reserva</button>
            </form>
        </main>
        </form>
    </main>
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