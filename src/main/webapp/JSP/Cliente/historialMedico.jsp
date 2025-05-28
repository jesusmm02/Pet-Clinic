<jsp:directive.page contentType="text/html" pageEncoding="UTF-8"/>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html lang="es">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <jsp:include page="/INC/metas.inc"/>
        <title>Historial Médico de Mis Mascotas - Pet Clinic</title>
        <link rel="icon" href="${contexto}/IMG/logoPetClinic_min.png" type="image/x-icon" />
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

            <c:if test="${not empty mapaHistorialesPorMascota}">
                <div class="accordion" id="accordionMascotas">
                <c:forEach var="entry" items="${mapaHistorialesPorMascota.entrySet()}">
                  <c:set var="mascota" value="${entry.key}" />
                  <c:set var="historiales" value="${entry.value}" />

                  <div class="accordion-item">
                    <h2 class="accordion-header" id="heading${mascota.id}">
                      <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#collapse${mascota.id}" aria-expanded="false" aria-controls="collapse${mascota.id}">
                        <img src="${pageContext.request.contextPath}/IMG/fotosMascotas/${mascota.foto}" alt="${mascota.nombre}" style="width:60px; height:60px; object-fit:cover; border-radius:50%; margin-right:10px;">
                        ${mascota.nombre}
                      </button>
                    </h2>
                    <div id="collapse${mascota.id}" class="accordion-collapse collapse" aria-labelledby="heading${mascota.id}" data-bs-parent="#accordionMascotas">
                      <div class="accordion-body">
                        <c:forEach var="historial" items="${historiales}">
                          <p><strong>Fecha:</strong> <fmt:formatDate value="${historial.fecha}" pattern="dd/MM/yyyy HH:mm" /></p>
                          <p><strong>Descripción:</strong> ${historial.descripcion}</p>
                          <p><strong>Tratamiento:</strong> ${historial.tratamiento}</p>
                          <hr/>
                        </c:forEach>
                      </div>
                    </div>
                  </div>
                </c:forEach>
              </div>
            </c:if>

            <c:if test="${empty mapaHistorialesPorMascota}">
                <div class="alert alert-info text-center">
                    No hay historiales médicos registrados para tus mascotas.
                </div>
            </c:if>
        </div>

        <!-- Bootstrap JS -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>

        <%@ include file="/INC/pie.jsp" %>

    </body>
</html>
