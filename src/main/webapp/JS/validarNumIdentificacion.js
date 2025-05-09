$(document).ready(function() {
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
                let mensajeFlotante = $('<div id="mensajeFlotante" class="mensaje-flotanteError mt-5"></div>');
                mensajeFlotante.text("⚠️ El DNI no es válido. Debe tener 8 dígitos seguidos de una letra.");
                $('body').append(mensajeFlotante);
                btnRegistrar.prop('disabled', true);  // Deshabilitar botón de registro

                setTimeout(function() {
                    mensajeFlotante.remove();
                }, 3000);
            } else if (tipoIdentificacion.val() === "NIE" && !validarNIE(numId)) {
                let mensajeFlotante = $('<div id="mensajeFlotante" class="mensaje-flotanteError mt-5"></div>');
                mensajeFlotante.text("⚠️ El NIE no es válido. Debe comenzar con una letra (X, Y o Z), seguido de 7 números y terminar con una letra.");
                $('body').append(mensajeFlotante);
                btnRegistrar.prop('disabled', true);  // Deshabilitar botón de registro

                setTimeout(function() {
                    mensajeFlotante.remove();
                }, 3000);
            } else {
                // Si la identificación es válida, habilitar el botón de registro
                btnRegistrar.prop('disabled', false);
            }
        }
    });
});