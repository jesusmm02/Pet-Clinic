const URL = "AJAXController";

$(document).ready(function () {
    $("#dni").on("blur", function () {
        var dni = $(this).val().trim();
        if (dni !== "") {
            $.ajax({
                url: URL, // URL del controlador
                method: "POST",
                data: { accion: "comprobarDNI", dni: dni }, // Verifica el DNI
                success: function (response) {
                    if (response.trim() === "existe") {
                        // Crear mensaje flotante dinámico
                        var mensajeFlotante = $("<div class='mensaje-flotanteError mt-5'></div>");
                        mensajeFlotante.text("⚠️ El DNI ya está registrado.");

                        // Añadir mensaje al body
                        $("body").append(mensajeFlotante);

                        // Deshabilitar el botón de registro
                        $("#btnRegistrar").prop("disabled", true);

                        // Ocultar el mensaje flotante después de 3 segundos
                        setTimeout(function () {
                            mensajeFlotante.fadeOut(1000, function () {
                                mensajeFlotante.remove(); // Eliminar mensaje después de desvanecerse
                            });
                        }, 3000);
                    } else {
                        // Si el DNI es válido, habilitar el botón de registro
                        $("#btnRegistrar").prop("disabled", false);
                    }
                },
                error: function (xhr, status, error) {
                    console.error("❌ Error en AJAX comprobarDNI:", error);
                }
            });
        }
    });
});