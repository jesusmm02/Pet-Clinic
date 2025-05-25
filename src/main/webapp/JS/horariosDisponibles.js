$(document).ready(function () {
    $('#fechaSeleccionada, #idServicio').on('change', function () {
        const fecha = $('#fechaSeleccionada').val();
        const idServicio = $('#idServicio').val();

        if (fecha && idServicio) {
            $.ajax({
                url: 'CitaController',
                method: 'GET',
                data: {
                    accion: 'obtenerHorarios',
                    fechaSeleccionada: fecha,
                    idServicio: idServicio
                },
                success: function (response) {
                    $('#selectHorarios').empty();
                    if (response.length > 0) {
                        $('#selectHorarios').append('<option value="" disabled selected>Seleccionar Horario...</option>');
                        response.forEach(function (horario) {
                            $('#selectHorarios').append(
                                `<option value="${horario.id}">${horario.horaInicio}                                                                                                                                                                                                                                   </option>`
                            );
                        });
                        $('#selectHorarios').prop('disabled', false);
                    } else {
                        $('#selectHorarios').append('<option value="" disabled>No hay horarios disponibles</option>');
                        $('#selectHorarios').prop('disabled', true);
                    }
                },
                error: function () {
                    alert("Error al cargar los horarios disponibles.");
                }
            });
        } else {
            // Si falta fecha o servicio, limpiar select y deshabilitar
            $('#selectHorarios').empty().append('<option value="" disabled selected>Seleccionar Horario...</option>').prop('disabled', true);
        }
    });
});