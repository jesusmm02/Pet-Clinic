const URL = "AJAXController";

$(document).ready(function () {
    $("#email").on("blur", function () {
        var email = $(this).val().trim();
        if (email !== "") {
            $.ajax({
                url: URL, // Usamos la URL fija
                method: "POST",
                data: { accion: "comprobarEmail", email: email },
                success: function (response) {
                    console.log("🔍 Respuesta del servidor: " + response); // Debugging
                    if (response.trim() === "existe") {
                        // Crear el mensaje flotante si el email ya está registrado
                        var mensaje = document.createElement('div');
                        mensaje.id = "mensajeFlotanteEmail";
                        mensaje.classList.add('mensaje-flotanteError');
                        mensaje.classList.add('mt-5');
                        mensaje.innerHTML = "⚠️ El email ya está registrado.";

                        // Insertar el mensaje flotante al cuerpo del documento
                        document.body.appendChild(mensaje);

                        // Eliminar el mensaje después de un tiempo
                        setTimeout(function () {
                            mensaje.style.opacity = '0';
                            setTimeout(function () {
                                mensaje.remove();
                            }, 1000);
                        }, 3000);

                        // Deshabilitar el botón de registrar
                        $("#btnRegistrar").prop("disabled", true);
                    } else {
                        // Si el email no existe, eliminar cualquier mensaje flotante previo
                        var mensajeFlotante = document.getElementById('mensajeFlotanteEmail');
                        if (mensajeFlotante) {
                            mensajeFlotante.remove();
                        }
                        $("#btnRegistrar").prop("disabled", false);
                    }
                },
                error: function (xhr, status, error) {
                    console.error("❌ Error en AJAX comprobarEmail:", error);
                }
            });
        }
    });
});