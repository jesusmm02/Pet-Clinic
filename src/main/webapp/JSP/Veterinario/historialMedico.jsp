<jsp:directive.page contentType="text/html" pageEncoding="UTF-8"/>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html lang="es">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <jsp:include page="/INC/metas.inc"/>
        <title>Historial Médico - Pet Clinic</title>
        <link rel="icon" href="${contexto}/IMG/logoPetClinic_min.png" type="image/x-icon" />
        <link rel="stylesheet" href="${bootstrap}">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
        <link rel="stylesheet" href="${contexto}/CSS/mensajeFlotante.css">
        <script src="${contexto}/JS/mensajeFlotante.js" defer></script>
        <script src="${contexto}/JS/limiteCaracteres.js" defer></script>
        <script>
            function limpiarFiltros() {
                // Restablecer todos los select a su valor por defecto
                document.getElementById('dueño').selectedIndex = 0;
                document.getElementById('especie').selectedIndex = 0;
                document.getElementById('raza').selectedIndex = 0;
            }
        </script>
        <style>
            .contador { font-weight: bold; }
            .contador.verde { color: #198754; }
            .contador.naranja { color: #fd7e14; }
            .contador.rojo { color: #dc3545; }
        </style>
    </head>
    <body>

        <%@ include file="/INC/cabecera.jsp" %>
        <%@ include file="/INC/barraVeterinario.jsp" %>

        <c:if test="${not empty creacionHistorial}">
            <div id="mensajeFlotante" class="mensaje-flotanteCorrecto mt-5">
                <c:out value="${creacionHistorial}" />
            </div>
        </c:if>

        <c:if test="${not empty eliminacionHistorial}">
            <div id="mensajeFlotante" class="mensaje-flotanteCorrecto mt-5">
                <c:out value="${eliminacionHistorial}" />
            </div>
        </c:if>

        <c:if test="${not empty errorHistorial}">
            <div id="mensajeFlotante" class="mensaje-flotanteError mt-5">
                <c:out value="${errorHistorial}" />
            </div>
        </c:if>

        <div class="container mt-5">
            <h2 class="text-center text-primary mb-4">Historial Médico de Mascotas</h2>

            <!-- Filtros -->
            <form action="VeterinarioController" method="POST" class="mb-4">
                <input type="hidden" name="accion" value="filtrarHistoriales">

                <div class="row g-3">
                    <!-- Filtro de dueño -->
                    <div class="col-md-4">
                        <label for="dueño" class="form-label">Dueño:</label>
                        <select class="form-select" id="dueño" name="dueño">
                            <option value="">Seleccionar dueño...</option>
                            <c:forEach var="cliente" items="${listaClientes}">
                                <option value="${cliente.id}" ${param.dueño == cliente.id ? 'selected' : ''}>
                                    ${cliente.nombre} ${cliente.apellidos}
                                </option>
                            </c:forEach>
                        </select>
                    </div>

                    <!-- Filtro de especie -->
                    <div class="col-md-4">
                        <label for="especie" class="form-label">Especie:</label>
                        <select class="form-select" id="especie" name="especie">
                            <option value="">Seleccionar especie...</option>
                            <c:forEach var="especie" items="${listaEspecies}">
                                <option value="${especie}" ${param.especie == especie ? 'selected' : ''}>${especie}</option>
                            </c:forEach>
                        </select>
                    </div>

                    <!-- Filtro de raza -->
                    <div class="col-md-4">
                        <label for="raza" class="form-label">Raza:</label>
                        <select class="form-select" id="raza" name="raza">
                            <option value="">Seleccionar raza...</option>
                            <c:forEach var="raza" items="${listaRazas}">
                                <option value="${raza}" ${param.raza == raza ? 'selected' : ''}>${raza}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>

                <button type="submit" class="btn btn-primary mt-3">Buscar</button>
                
                <!-- Botón para limpiar filtros -->
                <button type="button" class="btn btn-secondary mt-3" onclick="limpiarFiltros()">Limpiar Filtros</button>
                
            </form>



            <!-- Botón para añadir nuevo historial -->
            <c:choose>
                <c:when test="${not empty listaMascotas}">
                    <!-- Botón habilitado si hay mascotas -->
                    <button type="button" class="btn btn-success mb-3" data-bs-toggle="modal" data-bs-target="#modalNuevoHistorial">
                        <i class="fas fa-plus-circle"></i> Añadir Historial Médico
                    </button>
                </c:when>
                <c:otherwise>
                    <!-- Botón deshabilitado con aviso -->
                    <button type="button" class="btn btn-success mb-3" disabled>
                        <i class="fas fa-plus-circle"></i> Añadir Historial Médico
                    </button>
                    <div class="alert alert-warning mt-2">
                        <i class="fas fa-exclamation-triangle"></i> No se puede añadir un historial porque no hay mascotas registradas.
                    </div>
                </c:otherwise>
            </c:choose>


            <!-- Modal para añadir historial -->
            <div class="modal fade" id="modalNuevoHistorial" tabindex="-1">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title">Nuevo Historial Médico</h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
                        </div>
                        <form action="VeterinarioController" method="POST">
                            <input type="hidden" name="accion" value="guardarHistorial">
                            <div class="modal-body">
                                <div class="mb-3">
                                    <label for="mascota" class="form-label">Mascota:</label>
                                    <select name="idMascota" id="mascota" class="form-select" ${empty listaMascotas ? 'disabled' : ''}>
                                        <c:choose>
                                            <c:when test="${not empty listaMascotas}">
                                                <c:forEach var="mascota" items="${listaMascotas}">
                                                    <option value="${mascota.id}">
                                                        ${mascota.nombre} (${mascota.especie}) - Dueño: ${mascota.propietario.nombre} ${mascota.propietario.apellidos}
                                                    </option>
                                                </c:forEach>
                                            </c:when>
                                            <c:otherwise>
                                                <option value="" disabled>No hay mascotas registradas</option>
                                            </c:otherwise>
                                        </c:choose>
                                    </select>
                                </div>
                                <div class="mb-3">
                                    <label for="descripcion" class="form-label">Descripción:</label>
                                    <textarea name="descripcion" id="descripcion" class="form-control" rows="3" maxlength="255"></textarea>
                                    <div class="form-text text-end">
                                        <span id="contadorDescripcion" class="contador">0</span>/255 caracteres
                                    </div>
                                </div>
                                <div class="mb-3">
                                    <label for="tratamiento" class="form-label">Tratamiento:</label>
                                    <textarea name="tratamiento" id="tratamiento" class="form-control" rows="3" maxlength="255"></textarea>
                                    <div class="form-text text-end">
                                        <span id="contadorTratamiento" class="contador">0</span>/255 caracteres
                                    </div>
                                </div>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
                                <button type="submit" class="btn btn-success">Guardar Historial</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>

            <!-- Listado de historiales médicos -->
            <c:if test="${not empty listaHistoriales}">

                <div class="row g-4">
                    <c:forEach var="historial" items="${listaHistoriales}">
                        <div class="col-md-6">
                            <div class="card shadow-sm h-100">
                                <div class="card-header bg-light d-flex align-items-center">
                                    <img src="${pageContext.request.contextPath}/IMG/fotosMascotas/${historial.mascota.foto}"
                                         class="img-thumbnail me-3"
                                         style="width: 80px; height: 80px; object-fit: cover; border-radius: 50%;">
                                    <i class="fas fa-notes-medical fa-2x text-info me-3"></i>
                                    <div>
                                        <h5 class="mb-0">${historial.mascota.nombre} (${historial.mascota.especie})</h5>
                                        <small class="text-muted">
                                            Fecha: <fmt:formatDate value="${historial.fecha}" pattern="dd/MM/yyyy HH:mm" />
                                        </small>
                                    </div>
                                </div>
                                <div class="card-body">
                                    <h6 class="text-secondary">Descripción del Problema:</h6>
                                    <p>${historial.descripcion}</p>
                                    <h6 class="text-secondary">Tratamiento Aplicado:</h6>
                                    <p>${historial.tratamiento}</p>
                                    <h6 class="text-secondary">Dueño:</h6>
                                    <p>${historial.mascota.propietario.nombre} ${historial.mascota.propietario.apellidos}</p>

                                    <!-- Botón para eliminar el historial -->
                                    <form action="VeterinarioController" method="POST" style="display:inline;">
                                        <input type="hidden" name="accion" value="eliminarHistorial">
                                        <input type="hidden" name="idHistorial" value="${historial.id}">
                                        <button type="submit" class="btn btn-danger btn-sm mt-2">
                                            <i class="fas fa-trash"></i> Eliminar
                                        </button>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </c:if>
            <c:if test="${empty listaHistoriales}">
                <div class="alert alert-info text-center">
                    <i class="fa fa-file-text" aria-hidden="true"></i> Aún no hay historiales médicos registrados.
                </div>
            </c:if>

        </div>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
        <%@ include file="/INC/pie.jsp" %>
    </body>

</html>