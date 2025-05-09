<jsp:directive.page contentType="text/html" pageEncoding="UTF-8"/>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="es">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <jsp:include page="/INC/metas.inc"/>
        <title>Cliente - Pet Clinic</title>
        <link rel="stylesheet" href="${bootstrap}">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
        <link rel="stylesheet" href="${contexto}/CSS/mensajeFlotante.css">
        <script src="${contexto}/JS/mensajeFlotante.js" defer></script>
    </head>
    <body>

        <%@ include file="/INC/cabecera.jsp" %>
        <%@ include file="/INC/barraCliente.jsp" %>

        <c:if test="${not empty error}">
            <div id="mensajeFlotante" class="mensaje-flotanteError mt-5">
                <c:out value="${error}" />
            </div>
        </c:if>

        <c:if test="${not empty errorCreate}">
            <div id="mensajeFlotante" class="mensaje-flotanteError mt-5">
                <c:out value="${errorCreate}" />
            </div>
        </c:if>

        <c:if test="${not empty editado}">
            <div id="mensajeFlotante" class="mensaje-flotanteCorrecto mt-5">
                <c:out value="${editado}" />
            </div>
        </c:if>

        <div class="container my-5">
            <h2 class="text-center text-primary mb-5">Área de Cliente</h2>


            <div class="row g-4 justify-content-center">
                <div class="col-md-4">
                    <div class="card shadow-sm h-100">
                        <div class="card-body text-center">
                            <i class="fas fa-paw fa-3x text-success mb-3"></i>
                            <h5 class="card-title">Gestionar Mascotas</h5>
                            <p class="card-text">Añade nuevas mascotas o da de baja a las que ya no estén bajo tu cuidado.</p>
                            <form action="ClienteController" method="POST">
                                <input type="hidden" name="accion" value="gestionMascotas">
                                <button type="submit" class="btn btn-success btn-block">Ir a gestión</button>
                            </form>
                        </div>
                    </div>
                </div>

                <div class="col-md-4">
                    <div class="card shadow-sm h-100">
                        <div class="card-body text-center">
                            <i class="fas fa-calendar-plus fa-3x text-primary mb-3"></i>
                            <h5 class="card-title">Solicitar Cita</h5>
                            <p class="card-text">Solicita una nueva cita para tu mascota en cuestión de segundos.</p>
                            <form action="ClienteController" method="POST">
                                <input type="hidden" name="accion" value="solicitarCita">
                                <button type="submit" class="btn btn-primary btn-block">Solicitar cita</button>
                            </form>
                        </div>
                    </div>
                </div>

                <div class="col-md-4">
                    <div class="card shadow-sm h-100">
                        <div class="card-body text-center">
                            <i class="fas fa-file-medical-alt fa-3x text-warning mb-3"></i>
                            <h5 class="card-title">Historial Médico</h5>
                            <p class="card-text">Consulta el historial médico de tus mascotas cuando lo necesites.</p>
                            <form action="ClienteController" method="POST">
                                <input type="hidden" name="accion" value="historialMedico">
                                <button type="submit" class="btn btn-warning btn-block">Ver historial</button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>


            <div class="row g-4 justify-content-center mt-3">
                <div class="col-md-4">
                    <div class="card shadow-sm h-100">
                        <div class="card-body text-center">
                            <i class="fas fa-medkit fa-3x text-secondary mb-3"></i>
                            <h5 class="card-title">Consultar Servicios</h5>
                            <p class="card-text">Explora todos los servicios que tenemos disponibles para el cuidado de tu mascota.</p>
                            <form action="ClienteController" method="POST">
                                <input type="hidden" name="accion" value="verServicios">
                                <button type="submit" class="btn btn-secondary btn-block">Ver servicios</button>
                            </form>
                        </div>
                    </div>
                </div>

                <div class="col-md-4">
                    <div class="card shadow-sm h-100">
                        <div class="card-body text-center">
                            <i class="fas fa-chart-pie fa-3x text-info mb-3"></i>
                            <h5 class="card-title">Infografía</h5>
                            <p class="card-text">Visualiza información útil sobre vacunas, cuidados y más.</p>
                            <form action="ClienteController" method="POST">
                                <input type="hidden" name="accion" value="verInfografia">
                                <button type="submit" class="btn btn-info btn-block">Ver infografía</button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>



        <%@ include file="/INC/pie.jsp" %>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>

    </body>
</html>
