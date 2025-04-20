document.addEventListener("DOMContentLoaded", function () {
    var mensajes = ["mensajeFlotante"];

    mensajes.forEach(function(id) {
        var mensaje = document.getElementById(id);
        if (mensaje) {
            setTimeout(function () {
                mensaje.style.opacity = '0';
                setTimeout(function () {
                    mensaje.remove();
                }, 1000);
            }, 3000);
        }
    });
});