/**
 * 
 * Script para gestionar la vista previa, validación y eliminación de la foto de una mascota.
 *
 * Funciones:
 * - Validar tipo de archivo y tamaño (máx. 100 KB, JPG o PNG).
 * - Mostrar vista previa tras selección.
 * - Permitir quitar la imagen y restaurar estado inicial.
 * 
 */
document.addEventListener('DOMContentLoaded', function() {
    const fotoInput = document.getElementById("foto");
    const errorDiv = document.getElementById("fotoError");
    const imagenVistaPrevia = document.getElementById('vistaPreviaFoto');
    const btnQuitarFoto = document.getElementById("btnQuitarFoto");
    const quitarFoto = document.getElementById("quitarFotoMascota");

    // Mostrar botón si ya hay imagen al cargar la página
    if (imagenVistaPrevia && imagenVistaPrevia.src && imagenVistaPrevia.src.trim() !== '' && !imagenVistaPrevia.src.endsWith('null')) {
        btnQuitarFoto.style.display = 'inline-block';
    } else {
        btnQuitarFoto.style.display = 'none';
    }

    /**
     * 
     * Evento: cambio en input file.
     * Valida el archivo y muestra vista previa si es válido.
     * 
     */
    fotoInput.addEventListener('change', function(event) {
        const file = event.target.files[0];

        if (!file) {
            errorDiv.textContent = '';
            imagenVistaPrevia.src = '';
            imagenVistaPrevia.style.display = 'none';
            btnQuitarFoto.style.display = 'none';
            quitarFoto.value = 'false';
            return;
        }

        const allowedTypes = ['image/png', 'image/jpg', 'image/jpeg'];
        const maxSizeKB = 100;

        if (!allowedTypes.includes(file.type)) {
            errorDiv.textContent = 'Solo se permiten archivos PNG o JPG';
            fotoInput.value = '';
            imagenVistaPrevia.src = '';
            imagenVistaPrevia.style.display = 'none';
            btnQuitarFoto.style.display = 'inline-block';
            quitarFoto.value = 'true';
            return;
        }

        const fileSizeKB = file.size / 1024;
        if (fileSizeKB > maxSizeKB) {
            errorDiv.textContent = 'El archivo no puede superar 100 KB';
            fotoInput.value = '';
            imagenVistaPrevia.src = '';
            imagenVistaPrevia.style.display = 'none';
            btnQuitarFoto.style.display = 'inline-block';
            quitarFoto.value = 'true';
            return;
        }

        errorDiv.textContent = '';
        quitarFoto.value = 'false';

        const reader = new FileReader();
        reader.onload = function(e) {
            imagenVistaPrevia.src = e.target.result;
            imagenVistaPrevia.style.display = 'block';
            btnQuitarFoto.style.display = 'inline-block';
        };
        reader.readAsDataURL(file);
    });

    /**
     * 
     * Evento: clic en botón para quitar imagen.
     * Limpia la imagen actual y marca la intención de quitarla.
     * 
     */
    btnQuitarFoto.addEventListener('click', function() {
        fotoInput.value = '';
        quitarFoto.value = 'true';
        imagenVistaPrevia.src = '';
        imagenVistaPrevia.style.display = 'none';
        btnQuitarFoto.style.display = 'none';
        errorDiv.textContent = '';
    });
});