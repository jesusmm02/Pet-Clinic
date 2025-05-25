/**
 * 
 * Script que permite al usuario indicar una especie o raza personalizada
 * en los formularios de mascotas, cuando selecciona "otra" en el desplegable.
 *
 * Funcionalidades:
 * - Alterna entre un `<select>` y un `<input>` para especie y raza.
 * - Asegura que solo uno de los campos (select o input) se envía al backend.
 * - Aplica y quita la validación requerida (`required`) según corresponda.
 * 
 */
document.addEventListener('DOMContentLoaded', function () {
    const selectEspecie = document.getElementById('selectEspecie');
    const inputEspecie = document.getElementById('inputEspecie');
    const selectRaza = document.getElementById('selectRaza');
    const inputRaza = document.getElementById('inputRaza');

    /**
     * 
     * Evento: cambio en el selector de especie.
     * Si el usuario selecciona "otra", se muestra el campo para especie personalizada.
     * 
     */
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

    /**
     * 
     * Evento: cambio en el selector de raza.
     * Si el usuario selecciona "otra", se muestra el campo para raza personalizada.
     * 
     */
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
