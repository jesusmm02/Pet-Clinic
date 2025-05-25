/**
 * 
 * Script para gestionar la vista previa de la foto de una mascota
 * y permitir al usuario quitarla antes de guardar el formulario.
 *
 * Funcionalidades:
 * - Vista previa inmediata tras seleccionar una imagen.
 * - Botón de quitar foto que limpia el input y oculta la vista previa.
 * 
 */
document.addEventListener("DOMContentLoaded", function () {
  const inputFoto = document.getElementById("foto");
  const vistaPreviaFoto = document.getElementById("vistaPreviaFoto");
  const btnQuitarFoto = document.getElementById("btnQuitarFoto");

  /**
   * 
   * Muestra la vista previa de la imagen seleccionada o la oculta si no hay archivo.
   * 
   */
  function actualizarVistaPrevia() {
    const file = inputFoto.files[0];
    if (file) {
      const reader = new FileReader();
      reader.onload = function (e) {
        vistaPreviaFoto.src = e.target.result;
        vistaPreviaFoto.style.display = "block";
        btnQuitarFoto.style.display = "inline-block";
      };
      reader.readAsDataURL(file);
    } else {
      vistaPreviaFoto.src = "";
      vistaPreviaFoto.style.display = "none";
      btnQuitarFoto.style.display = "none";
    }
  }

  // Evento cambio en input file
  inputFoto.addEventListener("change", actualizarVistaPrevia);

  // Quitar imagen seleccionada
  btnQuitarFoto.addEventListener("click", function () {
    inputFoto.value = "";    // Limpia selección
    actualizarVistaPrevia();
  });

  // Inicializa vista previa si ya hay imagen cargada
  if (inputFoto.files.length > 0) {
    actualizarVistaPrevia();
  } else {
    vistaPreviaFoto.style.display = "none";
    btnQuitarFoto.style.display = "none";
  }
});