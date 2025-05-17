<%-- 
    Document   : registrarMascota
    Created on : 29 jun 2024, 14:46:36
    Author     : mario
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="Modelo.Cliente" %>
<%
    Cliente cliente = (Cliente) session.getAttribute("usuario");
    if (cliente == null) {
        response.sendRedirect("login.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html lang="es">
<head>
    <link rel="stylesheet" type="text/css" href="styles.css">
    <meta charset="UTF-8">
    <title>Registrar Mascota</title>
    <script>
        function validateForm() {
            var edad = document.getElementById("edad").value;
            if (edad < 0) {
                alert("La edad no puede ser negativa.");
                return false;
            }
            return true;
        }

        function redirigirAIndex() {
            setTimeout(function() {
                window.location.href = "index.jsp";
            }, 1000); // espera un segundo para la redirección
        }
        function validateImage(input) {
            var file = input.files[0];
            if (file) {
                var img = new Image();
                img.onload = function() {
                    var minWidth = 400; // Mínimo ancho aceptable
                    var maxWidth = 400; // Máximo ancho aceptable
                    var minHeight = 400; // Mínimo alto aceptable
                    var maxHeight = 40; // Máximo alto aceptable

                    if (
                        this.width < minWidth || this.width > maxWidth ||
                        this.height < minHeight || this.height > maxHeight
                    ) {
                        alert("La imagen debe tener dimensiones cercanas a 700x700.");
                        input.value = ''; // Limpiar el valor del campo de entrada de la imagen
                    } else {
                        document.getElementById("formRegistrarMascota").submit(); // Envía el formulario si la imagen es válida
                    }
                };
                img.src = URL.createObjectURL(file);
            }
        }

        document.addEventListener('DOMContentLoaded', function() {
            document.getElementById("formRegistrarMascota").addEventListener("submit", function(event) {
                if (validateForm()) {
                    setTimeout(function() {
                        redirigirAIndex();
                    }, 1000);
                } else {
                    event.preventDefault(); // Evita el envío del formulario si la validación falla
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
     <div class="registromascota">
    <h1>Registrar Mascota</h1>
    <form id="formRegistrarMascota" action="ControladorMascota" method="post" enctype="multipart/form-data" onsubmit="return validateForm()">
        <label for="nombre">Nombre:</label>
        <input type="text" id="nombre" name="nombre" required><br>

        <label for="tamaño">Tamaño:</label>
        <select id="tamaño" name="tamaño" required>
            <option value="Pequeño">Pequeño</option>
            <option value="Mediano">Mediano</option>
            <option value="Grande">Grande</option>
        </select><br>

        <label for="edad">Edad:</label>
        <input type="number" id="edad" name="edad" min="0" required><br>

        <label for="imgMascota">Imagen de la mascota:</label>
        <input type="file" id="imgMascota" name="imgMascota" accept="image/*" onchange="validateImage()" required><br>

        <label for="observacion">Observación:</label>
        <textarea id="observacion" name="observacion" required></textarea><br>

        <input type="submit" value="Registrar">
    </form>
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