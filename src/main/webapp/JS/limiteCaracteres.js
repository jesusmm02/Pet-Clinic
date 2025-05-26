/**
 * 
 * Script que controla el contador de caracteres en los campos
 * "descripción" y "tratamiento" del historial médico.
 * 
 * Funcionalidades:
 * 1. Muestra en tiempo real cuántos caracteres se han escrito.
 * 2. Cambia el color del contador:
 *    - Verde: < 200 caracteres
 *    - Naranja: entre 201 y 255
 *    - Rojo: > 255 (inválido)
 * 3. Deshabilita el botón "Guardar Historial" si se sobrepasa el límite de 255 caracteres en alguno de los campos.
 * 
 * Este comportamiento mejora la experiencia de usuario y previene errores de validación.
 * 
 */
document.addEventListener("DOMContentLoaded", function () {
    const descripcion = document.getElementById("descripcion");
    const tratamiento = document.getElementById("tratamiento");
    const contadorDescripcion = document.getElementById("contadorDescripcion");
    const contadorTratamiento = document.getElementById("contadorTratamiento");
    const btnGuardar = document.getElementById("btnGuardarHistorial");
    const limite = 255; // límite máximo de caracteres permitidos

    /**
     * 
     * Actualiza el contador de caracteres y su color según la longitud.
     * 
     * @param {type} textarea - El área de texto a observar.
     * @param {type} contador - El elemento <span> donde se muestra el número.
     */
    function actualizarContador(textarea, contador) {
        const longitud = textarea.value.length;
        contador.textContent = longitud;
        contador.classList.remove("verde", "naranja", "rojo");

        if (longitud > limite) {
            contador.classList.add("rojo");      // excede el límite
        } else if (longitud > 200) {
            contador.classList.add("naranja");   // se acerca al límite
        } else {
            contador.classList.add("verde");     // dentro del rango seguro
        }
    }

    /**
     * 
     * Comprueba ambos campos y actualiza contadores + estado del botón.
     * 
     */
    function actualizarEstado() {
        actualizarContador(descripcion, contadorDescripcion);
        actualizarContador(tratamiento, contadorTratamiento);

        if (descripcion.value.length > limite || tratamiento.value.length > limite) {
            btnGuardar.disabled = true;
        } else {
            btnGuardar.disabled = false;
        }
    }

    descripcion.addEventListener("input", actualizarEstado);
    tratamiento.addEventListener("input", actualizarEstado);
});