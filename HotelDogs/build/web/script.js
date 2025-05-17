document.addEventListener('DOMContentLoaded', (event) => {
    console.log('Document fully loaded and parsed');
});


document.addEventListener('DOMContentLoaded', function() {
    const loginForm = document.getElementById('loginForm');

    loginForm.addEventListener('submit', function(event) {
        const usuario = document.getElementById('usuario').value.trim();
        const clave = document.getElementById('clave').value.trim();

        // Expresión regular para permitir solo letras y números
        const regex = /^[a-zA-Z0-9]+$/;

        if (!usuario || !clave) {
            alert('Por favor, complete todos los campos.');
            event.preventDefault(); // Evitar el envío del formulario
        } else if (!regex.test(usuario) || !regex.test(clave)) {
            alert('El usuario y la contraseña solo pueden contener letras y números.');
            event.preventDefault(); // Evitar el envío del formulario
        }
    });
});

document.addEventListener('DOMContentLoaded', function() {
    const registerForm = document.getElementById('registerForm');

    registerForm.addEventListener('submit', function(event) {
        const nombre = document.getElementById('nombre').value.trim();
        const apellido = document.getElementById('apellido').value.trim();
        const direccion = document.getElementById('direccion').value.trim();
        const telefono = document.getElementById('telefono').value.trim();
        const usuario = document.getElementById('usuario').value.trim();
        const clave = document.getElementById('clave').value.trim();
        const confirmarClave = document.getElementById('confirmar_clave').value.trim();

        // Expresión regular para permitir solo letras, números y espacios
        const regex = /^[a-zA-Z0-9\s]+$/;

        if (!nombre || !apellido || !direccion || !telefono || !usuario || !clave || !confirmarClave) {
            alert('Por favor, complete todos los campos.');
            event.preventDefault(); // Evitar el envío del formulario
        } else if (!regex.test(nombre) || !regex.test(apellido) || !regex.test(direccion) || !regex.test(telefono) || !regex.test(usuario)) {
            alert('Los campos no deben contener caracteres especiales.');
            event.preventDefault(); // Evitar el envío del formulario
        } else if (clave !== confirmarClave) {
            alert('Las contraseñas no coinciden.');
            event.preventDefault(); // Evitar el envío del formulario
        }
    });
});
document.addEventListener('DOMContentLoaded', function() {
    const carouselContainer = document.querySelector('.carousel-container');
    const slides = document.querySelectorAll('.carousel-slide img');
    const prevButton = document.querySelector('.carousel-button.prev');
    const nextButton = document.querySelector('.carousel-button.next');

    let currentIndex = 0;
    const totalSlides = slides.length;

    function showSlide(index) {
        const offset = -index * 100;
        carouselContainer.style.transform = `translateX(${offset}%)`;
    }

    prevButton.addEventListener('click', function() {
        currentIndex = (currentIndex > 0) ? currentIndex - 1 : totalSlides - 1;
        showSlide(currentIndex);
    });

    nextButton.addEventListener('click', function() {
        currentIndex = (currentIndex < totalSlides - 1) ? currentIndex + 1 : 0;
        showSlide(currentIndex);
    });

    // Auto slide
    setInterval(function() {
        currentIndex = (currentIndex < totalSlides - 1) ? currentIndex + 1 : 0;
        showSlide(currentIndex);
    }, 5000); // Change image every 5 seconds
});