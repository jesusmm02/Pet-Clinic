<jsp:directive.page contentType="text/html" pageEncoding="UTF-8"/>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="es">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <jsp:include page="/INC/metas.inc"/>
        <title>Servicios Disponibles - Pet Clinic</title>
        <link rel="icon" href="${contexto}/IMG/logoPetClinic_min.png" type="image/x-icon" />
        <link rel="stylesheet" href="${bootstrap}">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
        <style>
            .card {
                height: 100%;
                box-shadow: 0px 4px 8px rgba(0, 0, 0, 0.1);
                transition: transform 0.2s ease;
            }

            .card:hover {
                transform: scale(1.02);
            }

            .card-title {
                font-size: 1.25rem;
                font-weight: bold;
            }

            .card-text {
                flex-grow: 1;
            }

            .card-body {
                display: flex;
                flex-direction: column;
            }

            .btn-reservar {
                background-color: #007bff;
                color: white;
                border: none;
                width: 100%;
            }

            .btn-reservar:hover {
                background-color: #0056b3;
            }

            .card-group {
                display: grid;
                grid-template-columns: repeat(auto-fill, minmax(300px, 1fr));
                gap: 20px;
            }

            .card-img-top {
                height: 200px;
                object-fit: cover;
                border-bottom: 1px solid #ddd;
            }
        </style>
    </head>
    <body>

        <%@ include file="/INC/cabecera.jsp" %>
        <%@ include file="/INC/barraCliente.jsp" %>

        <div class="container mt-5">
            <h2 class="text-center text-primary mb-4">Servicios Disponibles</h2>

            <c:if test="${not empty listaServicios}">
                <div class="card-group">
                    <c:forEach var="servicio" items="${listaServicios}">
                        <div class="card">
                            <img src="${pageContext.request.contextPath}/IMG/servicios/${servicio.id}.jpg" class="card-img-top" >

                            <div class="card-body">
                                <h5 class="card-title">${servicio.nombre}</h5>
                                <p class="card-text">${servicio.descripcion}</p>
                                <ul class="list-group list-group-flush mb-3">
                                    <li class="list-group-item"><strong>Precio:</strong> 
                                        <fmt:formatNumber value="${servicio.precio}" type="currency" currencySymbol="€" minFractionDigits="2" maxFractionDigits="2"/>
                                    </li>
                                    <li class="list-group-item"><strong>Duración:</strong> ${servicio.duracion} minutos</li>
                                </ul>
                                <form action="CitaController" method="POST">
                                    <button class="btn btn-primary" name="accion" value="solicitarCita">Reservar</button>
                                </form>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </c:if>

            <c:if test="${empty listaServicios}">
                <div class="alert alert-warning text-center">
                    No hay servicios disponibles en este momento.
                </div>
            </c:if>
        </div>

        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>

        <%@ include file="/INC/pie.jsp" %>

    </body>
</html>