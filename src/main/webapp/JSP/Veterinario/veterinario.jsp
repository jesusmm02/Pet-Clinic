<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:url var="bootstrap" value="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" scope="application" />
<c:set var="contexto" value="${pageContext.request.contextPath}" scope="application" />

<!DOCTYPE html>
<html lang="es">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <jsp:include page="/INC/metas.inc"/>
        <title>Veterinario - Pet Clinic</title>
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
                <!-- Imagen de bienvenida -->
                <img src="${contexto}/IMG/avatares/default.jpg" class="img-fluid rounded shadow my-3" alt="Bienvenido Veterinario">
            </div>

            <!-- Apartado 2: Resumen del día -->
            <div class="row justify-content-center">
                <!-- Próximas citas centradas -->
                <div class="col-md-6 mb-4">
                    <div class="card shadow-sm">
                        <div class="card-header bg-primary text-white">
                            <i class="fas fa-calendar-check me-2"></i> Próximas Citas
                        </div>
                        <div class="card-body">
                            <ul class="list-group">
                                <li class="list-group-item">
                                    <strong>09:30</strong> - Luna (con María J.)
                                </li>
                                <li class="list-group-item">
                                    <strong>11:00</strong> - Max (con Carlos G.)
                                </li>
                                <li class="list-group-item">
                                    <strong>12:15</strong> - Thor (con Ana M.)
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>

            <!-- Apartado 3: Galería de mascotas felices -->
            <div class="my-5">
                <h4 class="text-center mb-4">🐾 Pacientes de esta semana</h4>
                <div class="row text-center">
                    <div class="col-md-4 mb-4">
                        <img src="${contexto}/IMG/perro_clinica.jpg" class="img-fluid rounded shadow mb-2">
                        <h6>Max</h6>
                    </div>
                    <div class="col-md-4 mb-4">
                        <img src="${contexto}/IMG/gato_clinica.jpg" class="img-fluid rounded shadow mb-2">
                        <h6>Luna</h6>
                    </div>
                    <div class="col-md-4 mb-4">
                        <img src="${contexto}/IMG/huron_clinica.jpg" class="img-fluid rounded shadow mb-2">
                        <h6>Gabriela</h6>
                    </div>
                </div>
            </div>
        </div>

        <%@ include file="/INC/pie.jsp" %>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>

    </body>
</html>
