<jsp:directive.page contentType="text/html" pageEncoding="UTF-8"/>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="es">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <jsp:include page="/INC/metas.inc"/>
        <title>Solicitar cita - Pet Clinic</title>
        <link rel="icon" href="${contexto}/IMG/logoPetClinic_min.png" type="image/x-icon" />
        <link rel="stylesheet" href="${bootstrap}">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
        <link rel="stylesheet" href="${contexto}/CSS/mensajeFlotante.css">
        <script src="${contexto}/JS/mensajeFlotante.js" defer></script>
        <script src="${contexto}/JS/horariosDisponibles.js" defer></script>
        <script src="${contexto}/JS/cargarHorarios.js" defer></script>
    </head>
    <body>

        <%@ include file="/INC/cabecera.jsp" %>
        <%@ include file="/INC/barraCliente.jsp" %>
        
        <c:if test="${not empty errorCita}">
            <div id="mensajeFlotante" class="mensaje-flotanteError mt-5">
                <c:out value="${errorCita}" />
            </div>
        </c:if>
        
        <c:if test="${not empty mensajeCita}">
            <div id="mensajeFlotante" class="mensaje-flotanteCorrecto mt-5">
                <c:out value="${mensajeCita}" />
            </div>
        </c:if>

        <div class="container mt-5">
            <h2 class="text-center text-primary mb-4">Pedir Cita para Mascota</h2>

            <form action="CitaController" method="POST">
                <input type="hidden" name="accion" value="crearCita">

                <div class="row g-3">

                    <!-- Seleccionar Mascota -->
                    <div class="col-md-4">
                        <label for="idMascota" class="form-label">Selecciona tu Mascota:</label>
                        <select class="form-select" id="idMascota" name="idMascota">
                            <option value="" disabled selected>Seleccionar Mascota...</option>
                            <c:forEach var="mascota" items="${listaMascotas}">
                                <option value="${mascota.id}">${mascota.nombre} (${mascota.especie})</option>
                            </c:forEach>
                        </select>
                    </div>

                    <!-- Seleccionar Servicio -->
                    <div class="col-md-4">
                        <label for="idServicio" class="form-label">Selecciona el Servicio:</label>
                        <select class="form-select" id="idServicio" name="idServicio">
                            <option value="" disabled selected>Seleccionar Servicio...</option>
                            <c:forEach var="servicio" items="${listaServicios}">
                                <option value="${servicio.id}">${servicio.nombre} (${servicio.duracion} min.)</option>
                            </c:forEach>
                        </select>
                    </div>

                    <!-- Seleccionar Fecha -->
                    <div class="col-md-4">
                        <label for="fecha" class="form-label">Selecciona una Fecha:</label>
                        <input type="date" class="form-control" id="fechaSeleccionada" name="fechaSeleccionada">
                    </div>
                </div>

                <div class="row mt-4">
                    <div class="col-md-4">
                        <label for="selectHorarios">Horarios Disponibles:</label>
                        <select class="form-select" id="selectHorarios" name="idHorario">
                            <option value="" disabled selected>Seleccionar Horario...</option>
                            <c:forEach var="horario" items="${listaHorarios}">
                                <option value="${horario.id}">
                                    ${horario.horaInicio}
                                </option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                
                <div class="row mt-2">
                    <div class="col-md-6">
                      <div id="mensajeNoHorarios" class="alert alert-warning d-none p-2">
                        <i class="fas fa-exclamation-circle"></i> No hay huecos disponibles para este servicio en la fecha seleccionada.
                      </div>
                    </div>
                </div>

                <!-- Botón para enviar -->
                <div class="mt-4">
                    <c:choose>
                        <c:when test="${not empty listaMascotas}">
                            <button id="btnConfirmarCita" type="submit" class="btn btn-success">
                                <i class="fas fa-calendar-check"></i> Confirmar Cita
                            </button>
                        </c:when>
                        <c:otherwise>
                            <button type="button" class="btn btn-success" disabled>
                                <i class="fas fa-calendar-check"></i> Confirmar Cita
                            </button>
                            <div class="alert alert-warning mt-2">
                                <i class="fas fa-exclamation-circle"></i> Necesitas registrar al menos una mascota para solicitar una cita.
                            </div>
                        </c:otherwise>
                    </c:choose>
                </div>

            </form>
        </div>

        <%@ include file="/INC/pie.jsp" %>

    </body>
</html>
