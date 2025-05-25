/**
 * 
 * Script que gestiona la vista previa y eliminación del avatar seleccionado por el usuario.
 * 
 * Funcionalidades:
 * - Muestra una vista previa de la imagen seleccionada en el input de tipo `file`.
 * - Permite quitar la imagen seleccionada antes de enviar el formulario.
 * - Oculta la vista previa y el botón de quitar si no hay imagen.
 * 
 */
document.addEventListener("DOMContentLoaded", function () {
  
  // Referencias a los elementos del DOM
  const inputAvatar = document.getElementById("avatar");
  const vistaPrevia = document.getElementById("vistaPreviaAvatar");
  const btnQuitar = document.getElementById("btnQuitarAvatar");

  /**
   * 
   * Actualiza la vista previa del avatar cuando el usuario selecciona una imagen.
   * Si hay archivo, se muestra como imagen; si no, se oculta la vista previa.
   * 
   */
  function actualizarVistaPrevia() {
    const file = inputAvatar.files[0];
    if (file) {
      const reader = new FileReader();
      reader.onload = function (e) {
        vistaPrevia.src = e.target.result;
        vistaPrevia.style.display = "block";
        btnQuitar.style.display = "inline-block";
      };
      reader.readAsDataURL(file); // Convierte archivo a base64 para vista previa
    } else {
      vistaPrevia.src = "";
      vistaPrevia.style.display = "none";
      btnQuitar.style.display = "none";
    }
  }

  // Evento: al seleccionar archivo, actualizar la vista previa
  inputAvatar.addEventListener("change", actualizarVistaPrevia);

  // Evento: al hacer clic en "Quitar", limpiar input y ocultar vista previa
  btnQuitar.addEventListener("click", function () {
    inputAvatar.value = "";   // Borra archivo seleccionado
    actualizarVistaPrevia();  // Oculta vista previa y botón
  });

  // Inicialización al cargar la página (por si ya hay archivo cargado)
  if (inputAvatar.files.length > 0) {
    actualizarVistaPrevia();
  } else {
    vistaPrevia.style.display = "none";
    btnQuitar.style.display = "none";
  }
});