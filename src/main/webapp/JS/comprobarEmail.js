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
                        $("#emailError").text("⚠️ El email ya está registrado.").show();
                        $("#btnRegistrar").prop("disabled", true);
                    } else {
                        $("#emailError").text("").hide();
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
