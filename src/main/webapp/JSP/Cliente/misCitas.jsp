<jsp:directive.page contentType="text/html" pageEncoding="UTF-8"/>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="es">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <jsp:include page="/INC/metas.inc"/>
        <title>Mis citas - Pet Clinic</title>
        <link rel="icon" href="${contexto}/IMG/logoPetClinic_min.png" type="image/x-icon" />
        <link rel="stylesheet" href="${bootstrap}">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
        <link rel="stylesheet" href="${contexto}/CSS/mensajeFlotante.css">
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
        <script src="${contexto}/JS/mensajeFlotante.js" defer></script>
    </head>
    <body>

        <%@ include file="/INC/cabecera.jsp" %>
        <%@ include file="/INC/barraCliente.jsp" %>

        <div class="container mt-5">
            <h2 class="text-center text-primary mb-4">Mis Citas Programadas</h2>

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

            <form action="CitaController" method="POST" class="mb-4">
                <input type="hidden" name="accion" value="filtrarCitasCliente">

                <div class="row g-3">
                    <!-- Filtro por Mascota -->
                    <div class="col-md-4">
                        <label for="mascota" class="form-label">Filtrar por Mascota:</label>
                        <select name="mascotaId" id="mascota" class="form-select">
                            <option value="">Todas las mascotas</option>
                            <c:forEach var="mascota" items="${listaMascotas}">
                                <option value="${mascota.id}" 
                                    <c:if test="${param.mascotaId == mascota.id}">selected</c:if>>
                                    ${mascota.nombre}
                                </option>
                            </c:forEach>
                        </select>
                    </div>

                    <!-- Filtro por Servicio -->
                    <div class="col-md-4">
                        <label for="servicio" class="form-label">Filtrar por Servicio:</label>
                        <select name="servicioId" id="servicio" class="form-select">
                            <option value="">Todos los servicios</option>
                            <c:forEach var="servicio" items="${listaServicios}">
                                <option value="${servicio.id}" 
                                    <c:if test="${param.servicioId == servicio.id}">selected</c:if>>
                                    ${servicio.nombre}
                                </option>
                            </c:forEach>
                        </select>
                    </div>

                    <!-- Filtro por Fecha -->
                    <div class="col-md-4">
                        <label for="fecha" class="form-label">Filtrar por Fecha:</label>
                        <input type="date" name="fechaFiltro" id="fecha" class="form-control"
                               value="${param.fechaFiltro}">
                    </div>
                </div>

                <div class="mt-3">
                    <button type="submit" class="btn btn-primary">Buscar</button>
                    <button type="button" class="btn btn-secondary ms-2" id="btnLimpiarFiltros">Limpiar filtros</button>
                </div>
            </form>
                    
                    
            <!-- Sección citas futuras -->
            <h3>Próximas citas</h3>
            <c:choose>
                <c:when test="${not empty citasFuturas}">
                    <div class="row g-4">
                        <c:forEach var="cita" items="${citasFuturas}">
                            <div class="col-md-6">
                                <div class="card shadow-sm h-100">
                                    <div class="card-header bg-light d-flex align-items-center">
                                        <i class="fas fa-calendar-check fa-2x text-info me-3"></i>
                                        <div>
                                            <h5 class="mb-0">${cita.servicio.nombre}</h5>
                                            <small class="text-muted">
                                                Fecha: <fmt:formatDate value="${fechasFormateadasFuturas[cita.id]}" pattern="dd/MM/yyyy" />
                                                Hora: ${horariosCitasFuturas[cita.id]}
                                            </small>
                                        </div>
                                    </div>
                                    <div class="card-body">
                                        <h6 class="text-secondary">Mascota:</h6>
                                        <p>${cita.mascota.nombre} (${cita.mascota.especie})</p>

                                        <h6 class="text-secondary">Veterinario:</h6>
                                        <p>${cita.veterinario.nombre} ${cita.veterinario.apellidos}</p>

                                        <!-- Botón para abrir modal cancelar -->
                                        <button type="button" class="btn btn-danger btn-sm mt-2" data-bs-toggle="modal" data-bs-target="#confirmarCancelarCita${cita.id}">
                                            <i class="fas fa-trash"></i> Cancelar Cita
                                        </button>

                                        <!-- Modal de confirmación -->
                                        <div class="modal fade" id="confirmarCancelarCita${cita.id}" tabindex="-1" aria-labelledby="modalLabel${cita.id}" aria-hidden="true">
                                          <div class="modal-dialog modal-dialog-centered">
                                            <div class="modal-content">
                                              <form action="CitaController" method="POST">
                                                <input type="hidden" name="accion" value="eliminarCita" />
                                                <input type="hidden" name="idCita" value="${cita.id}" />
                                                <div class="modal-header">
                                                  <h5 class="modal-title" id="modalLabel${cita.id}">Confirmar Cancelación</h5>
                                                  <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Cerrar"></button>
                                                </div>
                                                <div class="modal-body">
                                                  ¿Estás seguro que quieres cancelar la cita para <strong>${cita.mascota.nombre}</strong> el día <fmt:formatDate value="${fechasFormateadasFuturas[cita.id]}" pattern="dd/MM/yyyy" /> a las ${cita.calendario.horaInicio}?
                                                </div>
                                                <div class="modal-footer">
                                                  <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">No, mantener</button>
                                                  <button type="submit" class="btn btn-danger">Sí, cancelar</button>
                                                </div>
                                              </form>
                                            </div>
                                          </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                </c:when>
                <c:otherwise>
                    <div class="alert alert-info text-center">
                        No tienes citas programadas.
                    </div>
                </c:otherwise>
            </c:choose>

            <hr>        

            
            <!-- Sección citas pasadas -->
            <h3 class="mt-5">Historial de citas pasadas</h3>
            <c:choose>
                <c:when test="${not empty citasPasadas}">
                    <div class="row g-4">
                        <c:forEach var="cita" items="${citasPasadas}">
                            <div class="col-md-6">
                                <div class="card shadow-sm h-100">
                                    <div class="card-header bg-light d-flex align-items-center">
                                        <i class="fas fa-calendar-check fa-2x text-secondary me-3"></i>
                                        <div>
                                            <h5 class="mb-0">${cita.servicio.nombre}</h5>
                                            <small class="text-muted">
                                                Fecha: <fmt:formatDate value="${fechasFormateadasPasadas[cita.id]}" pattern="dd/MM/yyyy" />
                                                Hora: ${horariosCitasPasadas[cita.id]}
                                            </small>
                                        </div>
                                    </div>
                                    <div class="card-body">
                                        <h6 class="text-secondary">Mascota:</h6>
                                        <p>${cita.mascota.nombre} (${cita.mascota.especie})</p>

                                        <h6 class="text-secondary">Veterinario:</h6>
                                        <p>${cita.veterinario.nombre} ${cita.veterinario.apellidos}</p>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                </c:when>
                <c:otherwise>
                    <div class="alert alert-info text-center">
                        No tienes citas pasadas.
                    </div>
                </c:otherwise>
            </c:choose>
            
        </div>

        
        <script>
            document.addEventListener("DOMContentLoaded", function () {
                document.getElementById("btnLimpiarFiltros").addEventListener("click", function () {
                    document.getElementById("mascota").selectedIndex = 0;
                    document.getElementById("servicio").selectedIndex = 0;
                    document.getElementById("fecha").value = "";
                });
            });
        </script>

        <%@ include file="/INC/pie.jsp" %>
        
    </body>
</html>
