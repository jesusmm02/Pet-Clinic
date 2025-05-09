<jsp:directive.page contentType="text/html" pageEncoding="UTF-8"/>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html lang="es">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <jsp:include page="/INC/metas.inc"/>
        <title>Historial Médico de Mis Mascotas - Pet Clinic</title>
        <link rel="stylesheet" href="${bootstrap}">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
        <style>
            .card {
                border: 1px solid #dee2e6;
                border-radius: 8px;
                box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
                transition: transform 0.2s ease;
            }

            .card:hover {
                transform: scale(1.02);
            }

            .card-header {
                background-color: #f8f9fa;
                border-bottom: 1px solid #dee2e6;
            }

            .card-body {
                padding: 1.25rem;
            }

            h6 {
                font-size: 1.1rem;
                margin-top: 0.5rem;
                margin-bottom: 0.2rem;
                color: #495057;
            }

            p {
                margin: 0.2rem 0 0.6rem;
            }

            .text-secondary {
                font-weight: 500;
            }
        </style>
    </head>
    <body>

        <%@ include file="/INC/cabecera.jsp" %>
        <%@ include file="/INC/barraCliente.jsp" %>

        <div class="container mt-5">
            <h2 class="text-center text-primary mb-4">Historial Médico de tus Mascotas</h2>

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
                                    <h6 class="text-secondary">Veterinario Responsable:</h6>
                                    <p>Carmen Vidal</p>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </c:if>

            <c:if test="${empty listaHistoriales}">
                <div class="alert alert-info text-center">
                    No hay historiales médicos registrados para tus mascotas.
                </div>
            </c:if>
        </div>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
        <%@ include file="/INC/pie.jsp" %>

    </body>
</html>
