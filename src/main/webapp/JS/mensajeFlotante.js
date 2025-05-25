/**
 * 
 * Script que gestiona la desaparición automática de mensajes flotantes.
 *
 * Este script busca un elemento con ID 'mensajeFlotante' y:
 * 1. Espera 3 segundos (3000ms).
 * 2. Reduce su opacidad a 0 para crear un efecto de desvanecimiento.
 * 3. Luego de 1 segundo adicional, elimina el elemento del DOM.
 *
 * Este comportamiento se aplica a todos los IDs definidos en el array `mensajes`.
 * 
 */
document.addEventListener("DOMContentLoaded", function () {
    var mensajes = ["mensajeFlotante"];

    mensajes.forEach(function(id) {
        var mensaje = document.getElementById(id);
        if (mensaje) {
            // Espera 3 segundos antes de iniciar el desvanecimiento
            setTimeout(function () {
                mensaje.style.opacity = '0';
                
                // Después de 1 segundo con opacidad 0, elimina el elemento
                setTimeout(function () {
                    mensaje.remove();
                }, 1000);
            }, 3000);
        }
    });
});