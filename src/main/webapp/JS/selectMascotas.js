document.addEventListener('DOMContentLoaded', function () {
    const selectEspecie = document.getElementById('selectEspecie');
    const inputEspecie = document.getElementById('inputEspecie');
    const selectRaza = document.getElementById('selectRaza');
    const inputRaza = document.getElementById('inputRaza');

    // Control de la especie
    selectEspecie.addEventListener('change', function () {
        if (selectEspecie.value === 'otra') {
            inputEspecie.style.display = 'block';
            inputEspecie.required = true;
            selectEspecie.name = ""; // Quitamos el nombre para no enviar doble valor
            inputEspecie.name = "especie"; // Asignamos el nombre al input
        } else {
            inputEspecie.style.display = 'none';
            inputEspecie.required = false;
            selectEspecie.name = "especie";
            inputEspecie.name = "";
        }
    });

    // Control de la raza
    selectRaza.addEventListener('change', function () {
        if (selectRaza.value === 'otra') {
            inputRaza.style.display = 'block';
            inputRaza.required = true;
            selectRaza.name = "";
            inputRaza.name = "raza";
        } else {
            inputRaza.style.display = 'none';
            inputRaza.required = false;
            selectRaza.name = "raza";
            inputRaza.name = "";
        }
    });
});
