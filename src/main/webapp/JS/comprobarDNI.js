const url = "AjaxController"

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
                        $("#mensajeDNI").text("⚠️ El DNI ya está registrado.").addClass("text-danger").show(); // Muestra mensaje de error
                    }
                },
                error: function (xhr, status, error) {
                    console.error("Error en AJAX comprobarDNI:", error);
                }
            });
        }
    });
});