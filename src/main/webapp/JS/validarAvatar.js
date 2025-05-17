document.addEventListener('DOMContentLoaded', function() {
    const avatarInput = document.getElementById("avatar");
    const errorDiv = document.getElementById("avatarError");
    const btnEliminar = document.getElementById("btnEliminarAvatar");
    const imagenVistaPrevia = document.getElementById('vistaPreviaAvatar');

    avatarInput.addEventListener('change', function(event) {
        const file = event.target.files[0];
        
        // Validaciones
        const allowedTypes = ['image/png', 'image/jpg', 'image/jpeg']; // Extensiones permitidas
        const maxSizeKB = 100; // Tamaño máximo permitido

        // Limpiar mensaje de error previo y mostrar botón de eliminar
        errorDiv.textContent = '';
        btnEliminar.style.display = 'block';

        // Comprobar tipo de archivo
        if (!allowedTypes.includes(file.type)) {
            errorDiv.textContent = 'Solo se permiten archivos PNG o JPG';
            avatarInput.value = ''; // Limpiar selección
            imagenVistaPrevia.src = '';
            btnEliminar.style.display = 'none';
            return;
        }

        // Comprobar tamaño de archivo
        const fileSizeKB = file.size / 1024;
        if (fileSizeKB > maxSizeKB) {
            errorDiv.textContent = 'El archivo no puede superar 100 KB';
            avatarInput.value = ''; // Limpiar selección
            imagenVistaPrevia.src = '';
            btnEliminar.style.display = 'none';
            return;
        }

        // Mostrar imagen en la vista previa
        var reader = new FileReader();
        reader.onload = function(e) {
            imagenVistaPrevia.src = e.target.result;
            imagenVistaPrevia.style.display = 'block';
        };
        reader.readAsDataURL(file);
    });
});