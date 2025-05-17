<%-- 
    Document   : index_principal
    Created on : 1 jul 2024, 11:53:49
    Author     : mario
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="Modelo.Cliente" %>


<!DOCTYPE html>
<html>
<head>
    <title>Inicio - Hotel para Perros</title>
    <link rel="stylesheet" type="text/css" href="styles.css">
    <script src="script.js"></script>
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
   
    
         <section class="hero">
        <div class="hero-content">
            <h2>Bienvenido a La Granja Bull</h2>
            <p>El mejor lugar para el cuidado de tu mascota</p>
           
        </div>
    </section>
    <section class="carousel">
        <div class="carousel-container">
            <div class="carousel-slide">
                <img src="images/CARRUSEL.png" alt="Foto 1">
                <img src="images/CARRUSEL2.png" alt="Foto 2">
                <img src="images/CARRUSEL3.png" alt="Foto 3">
            </div>
            <button class="carousel-button prev">‹</button>
            <button class="carousel-button next">›</button>
        </div>
    </section>
    <section class="about">
        <h2>Acerca de Nosotros</h2>
        <p>En La Granja Bull, nos dedicamos a proporcionar el mejor cuidado y atención para tus mascotas. Nuestra misión es asegurar que cada mascota tenga una estancia placentera y segura.</p>
        <h2>Vision</h2>
        <p>Ser la referencia en hospedaje canino, reconocidos por nuestro compromiso con el cuidado integral y el uso de tecnologías avanzadas para optimizar la experiencia del cliente y la eficiencia operativa.
</p>
<h2>Misión
</h2>
<p>Proporcionar servicios de hospedaje canino de calidad, centrados en la seguridad y el bienestar de las mascotas, mediante nuestros servicios asegurando que cada perro tenga una estancia placentera y estimulante. Con un equipo comprometido con el cuidado y la atención.
</p>

    </section>
 <section class="services">
        <h2>Nuestros Servicios</h2>
        <div class="service-item">
            <img src="images/aseocompleto.jpeg" alt="Estadía Básica">
            <h3>Estadía Básica</h3>
            <p>Alojamiento en una habitación estándar.</p>
            <p>Alimentación (comida seca y agua fresca)</p>
            <p>Paseos diarios (2 veces al día)</p>
                <p>Precio: S/ 30 por día</p>
              
        </div>
        <div class="service-item">
            <img src="images/alimentacion.jpg" alt="Estadía Plus">
            <h3>Estadía Plus</h3>

                     <p>Alojamiento en una habitación estándar.</p>
                     <p>Alimentación (comida seca y agua fresca)</p>
                     <p>Paseos diarios (3 veces al día)</p>
                     <p>Precio: S/ 50 por día</p>
        </div>
        <div class="service-item">
            <img src="images/paseocanino.jpg" alt="Cuidado de Cachorros">
            <h3>Cuidado de Cachorros</h3>
            <p>Alojamiento en una habitación segura y adaptada para cachorros. Alimentación específica para cachorros. Paseos y juegos supervisados (2 veces al día). Entrenamiento básico (comandos simples). Precio: S/ 70 por día.</p>
            <p>Alojamiento en una habitación segura y adaptada para cachorro.</p>
           <p> Alimentación específica para cachorros</p>
           <p> Entrenamiento básico (comandos simples).</p>
           <p> Precio: S/ 70 por día.</p>
        </div>
    </section>

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