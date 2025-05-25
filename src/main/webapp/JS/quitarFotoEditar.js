/**
 * 
 * Script mejorado para la vista previa de la foto de mascota con gestión
 * explícita de eliminación mediante input hidden.
 *
 * Funcionalidades:
 * - Muestra la imagen seleccionada.
 * - Permite eliminar la imagen y marcar intención con un campo oculto.
 *
 */
document.addEventListener("DOMContentLoaded", function () {
  const inputFoto = document.getElementById("foto");
  const vistaPreviaFoto = document.getElementById("vistaPreviaFoto");
  const btnQuitarFoto = document.getElementById("btnQuitarFoto");
  const inputQuitarFoto = document.getElementById("quitarFotoMascota");

  /**
   * 
   * Actualiza la vista previa cuando el usuario selecciona una imagen.
   * También marca que no se desea eliminar la foto existente.
   * 
   */
  function actualizarVistaPreviaFoto() {
    const file = inputFoto.files[0];
    if (file) {
      const reader = new FileReader();
      reader.onload = function (e) {
        vistaPreviaFoto.src = e.target.result;
        vistaPreviaFoto.style.display = "block";
        btnQuitarFoto.style.display = "inline-block";
        inputQuitarFoto.value = "false";
      };
      reader.readAsDataURL(file);
    } else {
      vistaPreviaFoto.src = "";
      vistaPreviaFoto.style.display = "none";
      btnQuitarFoto.style.display = "none";
    }
  }

  if (inputFoto && vistaPreviaFoto && btnQuitarFoto && inputQuitarFoto) {
    
    // Evento al seleccionar archivo
    inputFoto.addEventListener("change", actualizarVistaPreviaFoto);
    
    // Evento para eliminar manualmente la imagen
    btnQuitarFoto.addEventListener("click", function () {
      inputFoto.value = "";
      vistaPreviaFoto.src = "";
      vistaPreviaFoto.style.display = "none";
      btnQuitarFoto.style.display = "none";
      inputQuitarFoto.value = "true";
    });

    // Si ya hay imagen cargada al entrar en modo edición
    if (vistaPreviaFoto.src) {
      btnQuitarFoto.style.display = "inline-block";
      vistaPreviaFoto.style.display = "block";
    } else {
      btnQuitarFoto.style.display = "none";
      vistaPreviaFoto.style.display = "none";
    }
  }
});