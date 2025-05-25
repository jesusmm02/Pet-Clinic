/**
 * 
 * Vista previa del avatar seleccionado por el usuario.
 *
 * Este script:
 * - Lee la imagen seleccionada desde el input.
 * - Muestra la vista previa en un <img> con ID 'vistaPreviaAvatar'.
 * - Ajusta el tamaño de la imagen a 100x100 píxeles.
 * 
 */
document.getElementById('avatar').addEventListener('change', function(event) {
    var archivo = event.target.files[0]; // Obtener el archivo seleccionado
    console.log(archivo); // Verifica que el archivo se haya cargado correctamente
    if (archivo) {
        var reader = new FileReader(); // Crear un nuevo FileReader

        // Definir lo que sucede cuando la imagen se ha leído
        reader.onload = function (e) {
            var imagenVistaPrevia = document.getElementById('vistaPreviaAvatar');
            imagenVistaPrevia.src = e.target.result; // Asignar la imagen leída al src de la imagen
            imagenVistaPrevia.style.display = 'block'; // Mostrar la vista previa

            // Establecer el tamaño de la imagen
            imagenVistaPrevia.width = 100; // Cambiar el ancho a 100px
            imagenVistaPrevia.height = 100; // Cambiar el alto a 100px
        };

        // Leer el archivo como una URL de datos
        reader.readAsDataURL(archivo);
    }
});
