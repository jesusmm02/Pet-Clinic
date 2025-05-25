<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="es">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <jsp:include page="/INC/metas.inc"/>
        <title>Veterinario - Pet Clinic</title>
        <link rel="icon" href="${contexto}/IMG/logoPetClinic_min.png" type="image/x-icon" />
        <link rel="stylesheet" href="${bootstrap}">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
    </head>
    <body>

        <%@ include file="/INC/cabecera.jsp" %>
        <%@ include file="/INC/barraVeterinario.jsp" %>

        <div class="container my-5">
            <!-- Apartado 1: Bienvenida personalizada -->
            <div class="mb-5 text-center">
                <h2 class="text-primary">¡Hola, Dra. ${sessionScope.usuario.apellidos}!</h2>
                <p class="lead">Estas son tus actividades para hoy.</p>
            </div>

            <!-- Citas del día de hoy -->
            <div class="row justify-content-center">
                <!-- Próximas citas centradas -->
                <div class="col-md-6 mb-4">
                    <div class="card shadow-sm">
                        <div class="card-header bg-primary text-white">
                            <span><i class="fas fa-calendar-check me-2"></i> Citas de hoy</span>
                            <span>
                               (<fmt:formatDate value="${hoy}" pattern="dd/MM/yyyy" />)
                            </span>
                        </div>
                        <div class="card-body">
                            <ul class="list-group">
                                <c:choose>
                                    <c:when test="${not empty resumenCitasHoy}">
                                        <c:forEach var="linea" items="${resumenCitasHoy}">
                                            <li class="list-group-item">${linea}</li>
                                        </c:forEach>
                                    </c:when>
                                    <c:otherwise>
                                        <li class="list-group-item text-center text-muted">No hay citas programadas para hoy.</li>
                                    </c:otherwise>
                                </c:choose>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>

            <!-- Algunas mascotas registradas -->
            <div class="my-5">
                <h4 class="text-center mb-4">Pacientes de la clínica</h4>
                <div class="row text-center">
                    <c:choose>
                        <c:when test="${not empty mascotasRandom}">
                            <c:forEach var="mascota" items="${mascotasRandom}">
                                <div class="col-md-4 mb-4">
                                    <img src="${contexto}/IMG/fotosMascotas/${mascota.foto}" class="img-fluid rounded shadow mb-2" style="max-width: 150px; height: auto; margin: 0 auto 10px auto">
                                    <h4>${mascota.nombre}</h4>
                                </div>
                            </c:forEach>
                        </c:when>
                        <c:otherwise>
                            <div class="col-12 text-center">
                                <div class="alert alert-info">No hay mascotas registradas actualmente.</div>
                            </div>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>

                        
        </div>

        <%@ include file="/INC/pie.jsp" %>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>

    </body>
</html>
