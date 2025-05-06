document.addEventListener('DOMContentLoaded', function() {
    const fotoInput = document.getElementById("foto");
    const errorDiv = document.getElementById("fotoError");

    fotoInput.addEventListener('change', function(event) {
        const file = event.target.files[0];
        
        // Validaciones
        const allowedTypes = ['image/png', 'image/jpg', 'image/jpeg']; // Extensiones permitidas
        const maxSizeKB = 100; // Tamaño máximo permitido

        // Limpiar mensaje de error previo
        errorDiv.textContent = '';

        // Comprobar tipo de archivo
        if (!allowedTypes.includes(file.type)) {
            errorDiv.textContent = 'Solo se permiten archivos PNG o JPG';
            fotoInput.value = ''; // Limpiar selección
            return;
        }

        // Comprobar tamaño de archivo
        const fileSizeKB = file.size / 1024;
        if (fileSizeKB > maxSizeKB) {
            errorDiv.textContent = 'El archivo no puede superar 100 KB';
            fotoInput.value = ''; // Limpiar selección
            return;
        }
    });
});