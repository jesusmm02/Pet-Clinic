/**
 * 
 * Script para gestionar la vista previa, validación y eliminación del avatar del usuario.
 *
 * Funciones principales:
 * - Mostrar vista previa de la imagen seleccionada.
 * - Validar que la imagen sea PNG o JPG y que no supere los 100 KB.
 * - Permitir quitar la imagen seleccionada.
 * 
 */
document.addEventListener('DOMContentLoaded', function() {
    const avatarInput = document.getElementById("avatar");
    const errorDiv = document.getElementById("avatarError");
    const imagenVistaPrevia = document.getElementById('vistaPreviaAvatar');
    const btnQuitarAvatar = document.getElementById("btnQuitarAvatar");
    const quitarAvatar = document.getElementById("quitarAvatar");

    // Mostrar botón si ya hay imagen al cargar la página
    if (imagenVistaPrevia && imagenVistaPrevia.src && imagenVistaPrevia.src.trim() !== '') {
        btnQuitarAvatar.style.display = 'inline-block';
    } else {
        btnQuitarAvatar.style.display = 'none';
    }

    /**
     * 
     * Evento: cambio en input file.
     * Valida el archivo seleccionado y muestra vista previa.
     * 
     */
    avatarInput.addEventListener('change', function(event) {
        const file = event.target.files[0];

        // Si no hay archivo, limpiar campos
        if (!file) {
            errorDiv.textContent = '';
            imagenVistaPrevia.src = '';
            imagenVistaPrevia.style.display = 'none';
            btnQuitarAvatar.style.display = 'none';
            quitarAvatar.value = 'false';
            return;
        }

        const allowedTypes = ['image/png', 'image/jpg', 'image/jpeg'];
        const maxSizeKB = 100;

        // Validación de tipo de archivo
        if (!allowedTypes.includes(file.type)) {
            errorDiv.textContent = 'Solo se permiten archivos PNG o JPG';
            avatarInput.value = '';
            imagenVistaPrevia.src = '';
            imagenVistaPrevia.style.display = 'none';
            btnQuitarAvatar.style.display = 'inline-block';
            quitarAvatar.value = 'true';
            return;
        }

        // Validación de tamaño máximo
        const fileSizeKB = file.size / 1024;
        if (fileSizeKB > maxSizeKB) {
            errorDiv.textContent = 'El archivo no puede superar 100 KB';
            avatarInput.value = '';
            imagenVistaPrevia.src = '';
            imagenVistaPrevia.style.display = 'none';
            btnQuitarAvatar.style.display = 'inline-block';
            quitarAvatar.value = 'true';
            return;
        }

        // Mostrar vista previa si todo es válido
        errorDiv.textContent = '';
        quitarAvatar.value = 'false';

        const reader = new FileReader();
        reader.onload = function(e) {
            imagenVistaPrevia.src = e.target.result;
            imagenVistaPrevia.style.display = 'block';
            btnQuitarAvatar.style.display = 'inline-block';
        };
        reader.readAsDataURL(file);
    });

    /**
     * 
     * Evento: clic en el botón de quitar avatar.
     * Limpia la selección y oculta la vista previa.
     * 
     */
    btnQuitarAvatar.addEventListener('click', function() {
        avatarInput.value = '';
        quitarAvatar.value = 'true';
        imagenVistaPrevia.src = '';
        imagenVistaPrevia.style.display = 'none';
        btnQuitarAvatar.style.display = 'none';
        errorDiv.textContent = '';
    });
});