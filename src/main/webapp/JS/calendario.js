// Llenar el calendario al cargar la página
$(document).ready(function () {
    
    // Botón para limpiar los filtros y volver a mostrar todas las citas
    $('#limpiarFiltros').click(function () {
        $('#animalSelect').val('');
        $('#servicioSelect').val('');
        $('#calendar').fullCalendar('refetchEvents');
    });
    
    $('#citaModal').on('hide.bs.modal', function () {
        // Quitamos foco del botón cerrar para que no quede en elemento oculto
        $('#cerrarModalBtn').blur();
    });
    
    $('#citaModal').on('hide.bs.modal', function () {
        // Si el foco está dentro del modal, lo quitamos para evitar warning
        if ($(document.activeElement).closest('#citaModal').length) {
            $(document.activeElement).blur();
        }
    });



    // Inicializar FullCalendar
    $('#calendar').fullCalendar({
        locale: 'es',
        events: function (start, end, timezone, callback) {
            const animalId = $('#animalSelect').val();
            const servicioId = $('#servicioSelect').val();

            $.ajax({
                url: 'AJAXController',
                method: 'POST',
                data: {
                    accion: 'obtenerCitasCalendario',
                    animalId: animalId,
                    servicioId: servicioId
                },
                dataType: 'json',
                success: function (data) {
                    const eventos = data.map(function (cita) {
                        return {
                            title: cita.title,
                            start: cita.start,
                            id: cita.id
                        };
                    });
                    callback(eventos);
                }
            });
        },
        eventClick: function (event) {
            // Al hacer clic en un evento (cita), mostrar los detalles en el modal
            $.ajax({
                url: 'AJAXController',
                method: 'POST',
                data: {
                    accion: 'obtenerDetalleCita',
                    idCita: event.id
                },
                dataType: 'json',
                success: function (data) {
                    
                    // Convertir string fecha a objeto Date
                    const fechaObj = new Date(data.fecha);

                    // Formatear fecha a dd-MM-yyyy
                    const dia = String(fechaObj.getDate()).padStart(2, '0');
                    const mes = String(fechaObj.getMonth() + 1).padStart(2, '0'); // Enero es 0
                    const anio = fechaObj.getFullYear();

                    const fechaFormateada = `${dia}-${mes}-${anio}`;
                    
                    // Rellenar campos del modal individualmente
                    $('#modalMascota').text(data.mascota);
                    $('#modalServicio').text(data.servicio);
                    $('#modalVeterinario').text(data.veterinario);
                    $('#modalFecha').text(`${fechaFormateada}`);
                    $('#modalHora').text(`${data.horaInicio} - ${data.horaFin}`);

                    // Mostrar modal con Bootstrap 5 API
                    var modal = new bootstrap.Modal(document.getElementById('citaModal'));
                    modal.show();
                }
            });
        }
    });

    // Refrescar cuando cambian los filtros
    $('#animalSelect, #servicioSelect').change(function () {
        $('#calendar').fullCalendar('refetchEvents');
    });


});