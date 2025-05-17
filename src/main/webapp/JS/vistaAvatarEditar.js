document.getElementById('avatar').addEventListener('change', function(event) {
    var archivo = event.target.files[0];

    if (archivo) {
        var reader = new FileReader();

        reader.onload = function(e) {
            var imagenVistaPrevia = document.getElementById('vistaPreviaAvatar');
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