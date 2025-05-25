/**
 * 
 * Vista previa de avatar con creación dinámica del elemento <img>.
 *
 * Este script:
 * - Lee la imagen cargada desde el input con ID 'avatar'.
 * - Si no existe una imagen previa, crea un nuevo elemento <img> en el DOM.
 * - Aplica clases y estilos para ajustar la presentación.
 * 
 */
document.getElementById('avatar').addEventListener('change', function(event) {
    var archivo = event.target.files[0];

    if (archivo) {
        var reader = new FileReader();

        /**
         * 
         * Al cargar la imagen, la muestra en una vista previa.
         * Si no existe el elemento <img>, lo crea dentro de un contenedor específico.
         * 
         * @param {type} e
         */
        reader.onload = function(e) {
            var imagenVistaPrevia = document.getElementById('vistaPreviaAvatar');
            
            // Si no existe, crearla dinámicamente
            if (!imagenVistaPrevia) {
                imagenVistaPrevia = document.createElement('img');
                imagenVistaPrevia.id = 'vistaPreviaAvatar';
                imagenVistaPrevia.className = 'img-fluid rounded-circle mt-3';
                document.querySelector('.form-group.mb-3 .border').appendChild(imagenVistaPrevia);
            }
            imagenVistaPrevia.src = e.target.result;
            imagenVistaPrevia.style.display = 'block';
            imagenVistaPrevia.style.maxWidth = '150px';
            imagenVistaPrevia.style.maxHeight = '150px';
        };

        reader.readAsDataURL(archivo);
    }
});