$(document).ready(function () {
    const numIdentificacion = $('#identificacionInput');
    const tipoIdentificacion = $('#tipoIdentificacion');
    const mensajeIdentificacion = $('#mensajeIdentificacion');
    const btnRegistrar = $('#btnRegistrar');

    // Validar DNI
    function validarDNI(dni) {
        const regexDNI = /^[0-9]{8}[A-Za-z]$/;
        return regexDNI.test(dni);
    }

    // Validar NIE
    function validarNIE(nie) {
        const regexNIE = /^[XYZ][0-9]{7}[A-Za-z]$/;
        return regexNIE.test(nie);
    }

    // Cambiar el patrón al seleccionar el tipo de documento
    tipoIdentificacion.change(function() {
        mensajeIdentificacion.text("");  // Limpiar mensaje de error al cambiar tipo
        numIdentificacion.val('');  // Limpiar el input cuando se cambie el tipo

        if (tipoIdentificacion.val() === "DNI") {
            numIdentificacion.attr("pattern", "^[0-9]{8}[A-Za-z]$");  // solo se permiten números y letra
            numIdentificacion.attr("placeholder", "Introduce tu DNI");
        } else {
            numIdentificacion.attr("pattern", "^[XYZ][0-9]{7}[A-Za-z]$"); // patrón para NIE
            numIdentificacion.attr("placeholder", "Introduce tu NIE");
        }
    });

    // Comprobación al salir del input
    numIdentificacion.blur(function() {
        let numId = numIdentificacion.val().trim();

        // Si el input no está vacío
        if (numId !== "") {
            // Validar según el tipo de documento
            if (tipoIdentificacion.val() === "DNI" && !validarDNI(numId)) {
                // Mostrar mensaje flotante para formato incorrecto de DNI
                let mensajeFlotante = $('<div class="mensaje-flotanteError mt-5"></div>');
                mensajeFlotante.text("Formato de DNI: 8 números + letra.");
                $('body').append(mensajeFlotante);
                btnRegistrar.prop('disabled', true);  // Deshabilitar el botón de registro

                setTimeout(function() {
                    mensajeFlotante.remove();
                }, 3000);
            } else if (tipoIdentificacion.val() === "NIE" && !validarNIE(numId)) {
                // Mostrar mensaje flotante para formato incorrecto de NIE
                let mensajeFlotante = $('<div class="mensaje-flotanteError mt-5"></div>');
                mensajeFlotante.text("Formato de NIE: X/Y/Z + 7 números + letra.");
                $('body').append(mensajeFlotante);
                btnRegistrar.prop('disabled', true);  // Deshabilitar el botón de registro

                setTimeout(function() {
                    mensajeFlotante.remove();
                }, 3000);
            } else {
                // Si el formato es correcto, hacemos la comprobación AJAX
                $.ajax({
                    url: "AJAXController",
                    method: "POST",
                    data: { 
                        accion: "comprobarIdentificacion", // Acción para comprobar DNI o NIE
                        numIdentificacion: numId // Solo enviamos el número de identificación
                    },
                    success: function (response) {
                        if (response.trim() === "existe") {
                            // Si ya está registrado
                            mensajeIdentificacion.text("Número de identificación ya registrado.");
                            mensajeIdentificacion.show(); // Mostrar el mensaje de error
                            btnRegistrar.prop('disabled', true); // Deshabilitar el botón de registro
                        } else {
                            // Si está disponible, ocultamos el mensaje y habilitamos el botón de registro
                            mensajeIdentificacion.hide();
                            btnRegistrar.prop('disabled', false);
                        }
                    },
                    error: function (xhr, status, error) {
                        console.error("❌ Error en AJAX comprobarIdentificacion:", error);
                    }
                });
            }
        }
    });
});
