/**
 * 
 * Script para gestionar la vista previa del avatar del usuario,
 * y permitir su eliminación antes de enviar el formulario de edición de perfil.
 *
 * Funcionalidades:
 * - Muestra una vista previa del nuevo avatar seleccionado.
 * - Permite eliminar la imagen seleccionada.
 * - Marca un input oculto para indicar si el avatar debe ser eliminado.
 * 
 */
document.addEventListener("DOMContentLoaded", function () {
  
  // Obtener referencias a los elementos del DOM necesarios  
  const inputAvatar = document.getElementById("avatar");
  const vistaPreviaAvatar = document.getElementById("vistaPreviaAvatar");
  const btnQuitarAvatar = document.getElementById("btnQuitarAvatar");
  const inputQuitarAvatar = document.getElementById("quitarAvatar");

  /**
   * 
   * Muestra una vista previa de la imagen seleccionada en el input de tipo file.
   * Si se selecciona una imagen, se carga como base64 en el <img> y se actualiza el estado.
   * 
   */
  function actualizarVistaPreviaAvatar() {
    const file = inputAvatar.files[0];
    if (file) {
      const reader = new FileReader();
      reader.onload = function (e) {
        vistaPreviaAvatar.src = e.target.result;
        vistaPreviaAvatar.style.display = "block";
        btnQuitarAvatar.style.display = "inline-block";
        inputQuitarAvatar.value = "false"; // No eliminar el avatar si hay una nueva imagen
      };
      reader.readAsDataURL(file); // Leer archivo como base64
    } else {
      // Si se limpia el input o no hay archivo, ocultamos vista previa
      vistaPreviaAvatar.src = "";
      vistaPreviaAvatar.style.display = "none";
      btnQuitarAvatar.style.display = "none";
    }
  }

  // Si todos los elementos requeridos están disponibles
  if (inputAvatar && vistaPreviaAvatar && btnQuitarAvatar && inputQuitarAvatar) {
    
    // Escuchar cambios en el input para cargar vista previa
    inputAvatar.addEventListener("change", actualizarVistaPreviaAvatar);
    
    // Acción al hacer clic en "Quitar avatar"  
    btnQuitarAvatar.addEventListener("click", function () {
      inputAvatar.value = "";
      vistaPreviaAvatar.src = "";
      vistaPreviaAvatar.style.display = "none";
      btnQuitarAvatar.style.display = "none";
      inputQuitarAvatar.value = "true";
    });

    // Si ya hay una imagen cargada al entrar (modo edición), mostrarla
    if (vistaPreviaAvatar.src) {
      btnQuitarAvatar.style.display = "inline-block";
      vistaPreviaAvatar.style.display = "block";
    } else {
      btnQuitarAvatar.style.display = "none";
      vistaPreviaAvatar.style.display = "none";
    }
  }
});