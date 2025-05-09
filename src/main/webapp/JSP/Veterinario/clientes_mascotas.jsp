<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="es">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <jsp:include page="/INC/metas.inc"/>
        <title>Clientes y Mascotas - Pet Clinic</title>
        <link rel="stylesheet" href="${bootstrap}">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
        <style>
            .card-img-top {
                width: 150px;
                height: 150px;
                object-fit: cover;
                border-radius: 8px;
                margin-bottom: 10px;
            }
            .card {
                max-width: 300px;
            }
            .card-title {
                font-size: 1.1rem;
                margin-bottom: 0.5rem;
            }
            .list-group-item {
                font-size: 0.9rem;
            }
        </style>
    </head>
    <body>

        <%@ include file="/INC/cabecera.jsp" %>
        <%@ include file="/INC/barraVeterinario.jsp" %>

        <div class="container mt-5">
            <h2 class="text-center text-primary mb-4">Listado de Clientes y sus Mascotas</h2>

            <c:if test="${not empty listaClientes}">
                <div class="accordion" id="accordionClientes">
                    <!-- Iterar sobre todos los clientes -->
                    <c:forEach var="cliente" items="${listaClientes}">
                        <div class="accordion-item">
                            <h2 class="accordion-header" id="heading${cliente.id}">
                                <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#collapse${cliente.id}" aria-expanded="false" aria-controls="collapse${cliente.id}">
                                    ${cliente.nombre} ${cliente.apellidos} - ${cliente.email}
                                </button>
                            </h2>
                            <div id="collapse${cliente.id}" class="accordion-collapse collapse" aria-labelledby="heading${cliente.id}" data-bs-parent="#accordionClientes">
                                <div class="accordion-body">
                                    <h5 class="mb-3">Datos del Cliente:</h5>
                                    <ul class="list-group mb-3">
                                        <li class="list-group-item"><strong>Nombre completo:</strong> ${cliente.nombre} ${cliente.apellidos}</li>
                                        <li class="list-group-item"><strong>Correo electrónico:</strong> ${cliente.email}</li>
                                        <li class="list-group-item"><strong>Identificación:</strong> ${cliente.numIdentificacion}</li>
                                    </ul>

                                    <h5 class="mt-4">Mascotas:</h5>
                                    
                                    <c:if test="${not empty cliente.mascotas}">
                                        <div class="d-flex flex-wrap gap-4">
                                            <c:forEach var="mascota" items="${cliente.mascotas}">
                                                <div class="card p-2">
                                                    <img src="${pageContext.request.contextPath}/IMG/fotosMascotas/${mascota.foto}" class="card-img-top" alt="${mascota.nombre}">
                                                    <div class="card-body text-center">
                                                        <h5 class="card-title">${mascota.nombre}</h5>
                                                        <p class="card-text">
                                                            <strong>Especie:</strong> ${mascota.especie} <br>
                                                            <strong>Raza:</strong> ${mascota.raza} <br>
                                                            <strong>Género:</strong> ${mascota.genero} <br>
                                                        </p>
                                                    </div>
                                                </div>
                                            </c:forEach>
                                        </div>
                                    </c:if>

                                    <c:if test="${empty cliente.mascotas}">
                                        <div class="alert alert-warning mt-3" role="alert">
                                            <i class="fa-solid fa-circle-info"></i> Este cliente no tiene mascotas registradas.
                                        </div>
                                    </c:if>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </c:if>

            <c:if test="${empty listaClientes}">
                <p class="text-center text-danger">No hay clientes registrados.</p>
            </c:if>
        </div>

        <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>

        <%@ include file="/INC/pie.jsp" %>

    </body>
</html>