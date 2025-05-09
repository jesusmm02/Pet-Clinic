$(document).ready(function () {
    $("#identificacionInput").on("blur", function () {
        var numIdentificacion = $(this).val().trim();
        if (numIdentificacion !== "") {
            $.ajax({
                url: "AJAXController",
                method: "POST",
                data: { 
                    accion: "comprobarIdentificacion", // Acción para comprobar DNI o NIE
                    numIdentificacion: numIdentificacion // Solo enviamos el número de identificación
                },
                success: function (response) {
                    if (response.trim() === "existe") {
                        // Crear mensaje flotante dinámico
                        var mensajeFlotante = $("<div class='mensaje-flotanteError mt-5'></div>");
                        mensajeFlotante.text("⚠️ El número de identificación ya está registrado.");

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
                        // Si el número de identificación es válido, habilitar el botón de registro
                        $("#btnRegistrar").prop("disabled", false);
                    }
                },
                error: function (xhr, status, error) {
                    console.error("❌ Error en AJAX comprobarIdentificacion:", error);
                }
            });
        }
    });
});