<jsp:directive.page contentType="text/html" pageEncoding="UTF-8"/>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="es">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <jsp:include page="/INC/metas.inc"/>
        <title>Citas programadas - Pet Clinic</title>
        <link rel="icon" href="${contexto}/IMG/logoPetClinic_min.png" type="image/x-icon" />
        <link rel="stylesheet" href="${bootstrap}">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
        <link rel="stylesheet" href="${contexto}/CSS/mensajeFlotante.css">
        
        <!-- Estilo para calendario -->
        <link href="https://cdn.jsdelivr.net/npm/fullcalendar@3.2.0/dist/fullcalendar.min.css" rel="stylesheet">
        <!-- Scripts para calendario -->
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/moment@2.29.1/moment.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/fullcalendar@3.2.0/dist/fullcalendar.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/fullcalendar@3.2.0/dist/locale/es.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
        
        <script src="${contexto}/JS/calendario.js" defer></script>
        
        <style>
            
            .fc-toolbar {
                margin-bottom: 1rem;
            }
            .fc th {
                background-color: #f8f9fa;
                font-weight: 500;
            }
            .fc-event {
                background-color: #0d6efd !important;
                border: none;
                font-size: 0.85rem;
                padding: 2px 4px;
            }

        </style>
    </head>
    <body>

        <%@ include file="/INC/cabecera.jsp" %>
        <%@ include file="/INC/barraVeterinario.jsp" %>

        <div class="container mt-5">
            <h2 class="text-center text-primary mb-4">Calendario de Citas</h2>

            <!-- Filtro de animales -->
            <div class="mb-3">
                <label for="animalSelect">Seleccionar Animal:</label>
                <select id="animalSelect" class="form-select">
                    <option value="">Todos los animales</option>
                    <c:forEach var="mascota" items="${listaMascotas}">
                        <option value="${mascota.id}">${mascota.nombre}</option>
                    </c:forEach>
                </select>
            </div>

            <!-- Filtro de servicios -->
            <div class="mb-3">
                <label for="servicioSelect">Seleccionar Servicio:</label>
                <select id="servicioSelect" class="form-select">
                    <option value="">Todos los servicios</option>
                    <c:forEach var="servicio" items="${listaServicios}">
                        <option value="${servicio.id}">${servicio.nombre}</option>
                    </c:forEach>
                </select>
            </div>
            
            <button class="btn btn-secondary mb-3" id="limpiarFiltros">Limpiar filtros</button>

            <!-- Calendario -->
            <div id="calendar" style="max-width:700px; height:500px; margin: 0 auto; margin-bottom: 100px"></div>

            <!-- Modal para ver detalles de la cita -->
            <div class="modal fade" id="citaModal" tabindex="-1" aria-labelledby="citaModalLabel" aria-hidden="true">
              <div class="modal-dialog modal-dialog-centered">
                <div class="modal-content shadow-sm border-0 rounded-4">
                  <div class="modal-header border-0">
                    <h5 class="modal-title fw-bold" id="citaModalLabel">Detalles de la Cita</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Cerrar"></button>
                  </div>
                  <div class="modal-body">
                    <ul class="list-unstyled mb-0">
                      <li><strong>Mascota:</strong> <span id="modalMascota"></span></li>
                      <li><strong>Servicio:</strong> <span id="modalServicio"></span></li>
                      <li><strong>Veterinario:</strong> <span id="modalVeterinario"></span></li>
                      <li><strong>Fecha:</strong> <span id="modalFecha"></span></li>
                      <li><strong>Hora:</strong> <span id="modalHora"></span></li>
                    </ul>
                  </div>
                  <div class="modal-footer border-0 pt-0">
                    <button type="button" id="cerrarModalBtn" class="btn btn-secondary rounded-pill px-4" data-bs-dismiss="modal">Cerrar</button>
                  </div>
                </div>
              </div>
            </div>


        </div>

        <%@ include file="/INC/pie.jsp" %>

    </body>
</html>
