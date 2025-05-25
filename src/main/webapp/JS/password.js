/**
 * 
 * Script para validar la contraseña del formulario de registro
 * 
 * Requisitos de la contraseña:
 * - Mínimo 8 caracteres
 * - Al menos una letra mayúscula
 * - Al menos una letra minúscula
 * - Al menos un número
 * - Al menos un carácter especial (símbolo)
 * 
 * Deshabilita el botón de registro si no se cumple el patrón.
 * 
 * @type type
 */
document.addEventListener("DOMContentLoaded", function () {
 
  // Obtener el input de contraseña y el botón de registro
  const passwordInput = document.getElementById("passwordRegistro");
  const btnRegistrar = document.getElementById("btnRegistrar");

  // Crear elemento para mostrar el mensaje de error
  const passwordError = document.createElement("small");
  passwordError.style.color = "red";
  
  // Insertar el mensaje justo debajo del input
  passwordInput.parentNode.appendChild(passwordError);

  // Patrón de contraseña
  const passwordPattern = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[\W_]).{8,}$/;

  /**
   * 
   * Función que valida el campo de contraseña.
   * Si no cumple el patrón, muestra mensaje y desactiva el botón.
   * 
   */
  function validatePassword() {
    const pass = passwordInput.value.trim();
    if (!passwordPattern.test(pass)) {
      passwordError.textContent = "La contraseña debe tener al menos 8 caracteres, incluyendo mayúsculas, minúsculas, números y símbolos.";
      btnRegistrar.disabled = true;
    } else {
      passwordError.textContent = "";
      btnRegistrar.disabled = false;
    }
  }

  // Validar cuando se pierde el foco del input (blur)
  passwordInput.addEventListener("blur", validatePassword);

  // Deshabilitar el botón al cargar la página
  btnRegistrar.disabled = true;
});
