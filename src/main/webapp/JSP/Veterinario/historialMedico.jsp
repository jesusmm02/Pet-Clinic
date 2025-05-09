<jsp:directive.page contentType="text/html" pageEncoding="UTF-8"/>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html lang="es">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <jsp:include page="/INC/metas.inc"/>
        <title>Historial Médico - Pet Clinic</title>
        <link rel="stylesheet" href="${bootstrap}">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
        <link rel="stylesheet" href="${contexto}/CSS/mensajeFlotante.css">
        <script src="${contexto}/JS/mensajeFlotante.js" defer></script>
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

        <div class="container mt-5">
            <h2 class="text-center text-primary mb-4">Historial Médico de Mascotas</h2>

            <!-- Botón para añadir nuevo historial -->
            <button type="button" class="btn btn-success mb-3" data-bs-toggle="modal" data-bs-target="#modalNuevoHistorial">
                <i class="fas fa-plus-circle"></i> Añadir Historial Médico
            </button>

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
                                    <select name="idMascota" id="mascota" class="form-select">
                                        <c:forEach var="mascota" items="${listaMascotas}">
                                            <option value="${mascota.id}">
                                                ${mascota.nombre} (${mascota.especie}) - Dueño: ${mascota.propietario.nombre} ${mascota.propietario.apellidos}
                                            </option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div class="mb-3">
                                    <label for="descripcion" class="form-label">Descripción:</label>
                                    <textarea name="descripcion" id="descripcion" class="form-control" rows="3"></textarea>
                                </div>
                                <div class="mb-3">
                                    <label for="tratamiento" class="form-label">Tratamiento:</label>
                                    <textarea name="tratamiento" id="tratamiento" class="form-control" rows="3"></textarea>
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