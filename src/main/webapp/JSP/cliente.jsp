<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:url var="bootstrap" value="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" scope="application" />
<c:set var="contexto" value="${pageContext.request.contextPath}" scope="application" />

<!DOCTYPE html>
<html lang="es">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <jsp:include page="/INC/metas.inc"/>
        <title>Cliente - Pet Clinic</title>
        <link rel="stylesheet" href="${bootstrap}">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
    </head>
    <body>

        <%@ include file="/INC/cabecera.jsp" %>

        <div class="container my-5">
            <h2 class="text-center text-primary mb-5">Área de Cliente</h2>

            <div class="row g-4">
                <!-- Gestionar mascotas -->
                <div class="col-md-6">
                    <div class="card shadow-sm h-100">
                        <div class="card-body text-center">
                            <i class="fas fa-paw fa-3x text-success mb-3"></i>
                            <h5 class="card-title">Gestionar Mascotas</h5>
                            <p class="card-text">Añade nuevas mascotas o da de baja a las que ya no estén bajo tu cuidado.</p>
                            <a href="${contexto}/gestionMascotas.jsp" class="btn btn-outline-success mt-2">Ir a gestión</a>
                        </div>
                    </div>
                </div>

                <!-- Solicitar cita -->
                <div class="col-md-6">
                    <div class="card shadow-sm h-100">
                        <div class="card-body text-center">
                            <i class="fas fa-calendar-plus fa-3x text-primary mb-3"></i>
                            <h5 class="card-title">Solicitar Cita</h5>
                            <p class="card-text">Solicita una nueva cita para tu mascota en cuestión de segundos.</p>
                            <a href="${contexto}/solicitarCita.jsp" class="btn btn-outline-primary mt-2">Solicitar cita</a>
                        </div>
                    </div>
                </div>

                <!-- Consultar historial -->
                <div class="col-md-6">
                    <div class="card shadow-sm h-100">
                        <div class="card-body text-center">
                            <i class="fas fa-file-medical-alt fa-3x text-warning mb-3"></i>
                            <h5 class="card-title">Historial Médico</h5>
                            <p class="card-text">Consulta el historial médico de tus mascotas cuando lo necesites.</p>
                            <a href="${contexto}/historialMascotas.jsp" class="btn btn-outline-warning mt-2">Ver historial</a>
                        </div>
                    </div>
                </div>

                <!-- Infografía -->
                <div class="col-md-6">
                    <div class="card shadow-sm h-100">
                        <div class="card-body text-center">
                            <i class="fas fa-chart-pie fa-3x text-info mb-3"></i>
                            <h5 class="card-title">Infografía</h5>
                            <p class="card-text">Visualiza información útil sobre vacunas, cuidados y más.</p>
                            <a href="${contexto}/infografia.jsp" class="btn btn-outline-info mt-2">Ver infografía</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <%@ include file="/INC/pie.jsp" %>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>

    </body>
</html>
