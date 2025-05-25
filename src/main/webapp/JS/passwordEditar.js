/**
 * 
 * Script para validar la nueva contraseña en el formulario de edición de perfil.
 *
 * Requisitos de la nueva contraseña:
 * - Mínimo 8 caracteres
 * - Al menos una letra mayúscula
 * - Al menos una letra minúscula
 * - Al menos un número
 * - Al menos un carácter especial (símbolo)
 *
 * Si el campo está vacío, se permite guardar (el cambio de contraseña es opcional).
 * Si no cumple los requisitos, se muestra un mensaje y se desactiva el botón de guardar.
 * 
 */
document.addEventListener("DOMContentLoaded", function () {
  
  // Elementos del DOM necesarios  
  const passwordNuevaInput = document.getElementById("passwordNueva");
  const btnGuardarCambios = document.getElementById("guardarCambios");

  // Crear elemento visual para mostrar errores debajo del input
  const passwordError = document.createElement("small");
  passwordError.style.color = "red";
  passwordNuevaInput.parentNode.appendChild(passwordError);

  // Patrón de contraseña
  const passwordPattern = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[\W_]).{8,}$/;

  /**
   * 
   * Función que valida la nueva contraseña:
   * - Permite vacío (campo no obligatorio)
   * - Si tiene valor, debe cumplir el patrón
   * 
   */
  function validarPasswordNueva() {
    const pass = passwordNuevaInput.value.trim();

    // Campo vacío = válido, no es obligatorio cambiar
    if (pass === "") {
      passwordError.textContent = "";
      btnGuardarCambios.disabled = false;
      return;
    }

    // Si hay valor pero no cumple el patrón, mostrar error
    if (!passwordPattern.test(pass)) {
      passwordError.textContent = "La contraseña debe tener mínimo 8 caracteres, incluir mayúsculas, minúsculas, números y símbolos.";
      btnGuardarCambios.disabled = true;
    } else {
      passwordError.textContent = "";
      btnGuardarCambios.disabled = false;
    }
  }

  // Validar cuando el usuario quita el foco del campo (blur)
  passwordNuevaInput.addEventListener("blur", validarPasswordNueva);

  // Validar automáticamente si ya hay un valor al cargar la página
  if (passwordNuevaInput.value.trim() !== "") {
    validarPasswordNueva();
  }
});
