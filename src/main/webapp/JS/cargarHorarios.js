document.addEventListener("DOMContentLoaded", function() {
  // Obtener referencias a los elementos del DOM  
  const fechaSeleccionada = document.getElementById("fechaSeleccionada");
  const idServicio = document.getElementById("idServicio");
  const selectHorarios = document.getElementById("selectHorarios");
  const mensajeNoHorarios = document.getElementById("mensajeNoHorarios");
  const btnConfirmarCita = document.getElementById("btnConfirmarCita");
  const form = document.querySelector("form[action='CitaController']");

  // Verifica que los elementos necesarios existen en el DOM
  if (!fechaSeleccionada || !idServicio || !selectHorarios || !form || !btnConfirmarCita) {
    console.error("No se encontraron los elementos necesarios en el DOM.");
    return;
  }

  /**
   * 
   * Deshabilita el select de horarios y el botón de confirmación de cita.
   * Muestra un mensaje si se proporciona.
   * 
   * @param {type} mensaje - Texto opcional para mostrar cuando no hay horarios disponibles.
   */
  function deshabilitarSelectYBoton(mensaje = null) {
    selectHorarios.innerHTML = '<option value="" disabled selected>No hay horarios disponibles</option>';
    selectHorarios.disabled = true;
    btnConfirmarCita.disabled = true;
    if (mensajeNoHorarios && mensaje) {
      mensajeNoHorarios.textContent = mensaje;
      mensajeNoHorarios.classList.remove("d-none");
    } else if (mensajeNoHorarios) {
      mensajeNoHorarios.classList.add("d-none");
    }
  }

  // Si no hay fecha o servicio seleccionados, se desactiva el select y el botón
  if (!fechaSeleccionada.value || !idServicio.value) {
    deshabilitarSelectYBoton();
  }

  /**
   * 
   * Realiza una petición AJAX para obtener los horarios disponibles según la fecha y el servicio.
   * Actualiza el select con las opciones o muestra un mensaje si no hay disponibilidad.
   * 
   */
  function cargarHorarios() {
    // Oculta el mensaje si se había mostrado antes
    if (mensajeNoHorarios) mensajeNoHorarios.classList.add("d-none");

    // Verifica que hay fecha y servicio seleccionados antes de continuar
    if (!fechaSeleccionada.value || !idServicio.value) {
      deshabilitarSelectYBoton();
      return;
    }

    // Habilita el select por si estaba desactivado
    selectHorarios.disabled = false;

    // Realiza la petición GET al controlador para obtener los horarios disponibles
    fetch(`CitaController?accion=obtenerHorarios&fechaSeleccionada=${fechaSeleccionada.value}&idServicio=${idServicio.value}`, {
      method: "GET"
    })
    .then(response => {
      if (!response.ok) throw new Error(`HTTP error ${response.status}`);
      return response.json();
    })
    .then(data => {
      selectHorarios.innerHTML = "";

      // Si se devuelven horarios disponibles, los muestra en el select
      if (data.length > 0) {
        data.forEach(horario => {
          const option = document.createElement("option");
          option.value = horario.id;
          option.textContent = horario.horaInicio;
          selectHorarios.appendChild(option);
        });
        selectHorarios.disabled = false;
        btnConfirmarCita.disabled = false;
      } else {
        // Si no hay horarios disponibles, desactiva el select y muestra mensaje
        deshabilitarSelectYBoton("No hay huecos disponibles ese día para este servicio.");
      }
    })
    .catch(error => {
      console.error("Error al cargar horarios:", error);
      deshabilitarSelectYBoton("Error al cargar horarios.");
    });
  }

  // Vuelve a cargar los horarios cuando se cambia la fecha o el servicio
  fechaSeleccionada.addEventListener("change", cargarHorarios);
  idServicio.addEventListener("change", cargarHorarios);

   // Asegura que el valor del campo fecha esté limpio al enviar el formulario si no se selecciona
  form.addEventListener("submit", function(e) {
    if (!fechaSeleccionada.value) {
      fechaSeleccionada.value = "";
    }
  });
});