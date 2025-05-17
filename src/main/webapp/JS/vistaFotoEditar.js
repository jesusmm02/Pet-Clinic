document.getElementById('foto').addEventListener('change', function(event) {
    var archivo = event.target.files[0];

    if (archivo) {
        var reader = new FileReader();

        reader.onload = function(e) {
            var imagenVistaPrevia = document.getElementById('vistaPreviaFoto');

            // Si no existe la imagen, la creamos
            if (!imagenVistaPrevia) {
                imagenVistaPrevia = document.createElement('img');
                imagenVistaPrevia.id = 'vistaPreviaFoto';
                imagenVistaPrevia.className = 'img-fluid mt-3';
                imagenVistaPrevia.style.objectFit = 'cover';
                imagenVistaPrevia.style.borderRadius = '10px';
                document.querySelector('.border.d-flex').appendChild(imagenVistaPrevia);
            }
            
            // Actualizamos la imagen con la nueva seleccionada
            imagenVistaPrevia.src = e.target.result;
            imagenVistaPrevia.style.display = 'block';
            imagenVistaPrevia.style.maxWidth = '150px';
            imagenVistaPrevia.style.maxHeight = '150px';
            imagenVistaPrevia.style.margin = '0 auto'; // Centrado horizontal
        };

        reader.readAsDataURL(archivo);
    }
});