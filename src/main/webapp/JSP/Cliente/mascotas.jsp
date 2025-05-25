<jsp:directive.page contentType="text/html" pageEncoding="UTF-8"/>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html lang="es">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <jsp:include page="/INC/metas.inc"/>
        <title>Tus Mascotas - Pet Clinic</title>
        <link rel="icon" href="${contexto}/IMG/logoPetClinic_min.png" type="image/x-icon" />
        <link rel="stylesheet" href="${bootstrap}">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
        <link rel="stylesheet" href="${contexto}/CSS/mensajeFlotante.css">
        <script src="${contexto}/JS/mensajeFlotante.js" defer></script>
        <style>
            .table img {
                width: 80px;
                height: 80px;
                object-fit: cover;
                border-radius: 8px;
                transition: transform 0.2s ease;
                box-shadow: 0px 2px 5px rgba(0, 0, 0, 0.2);
            }

            .table img:hover {
                transform: scale(1.05);
            }

            .btn-group {
                display: flex;
                gap: 5px;
            }

            .btn {
                display: flex;
                align-items: center;
                justify-content: center;
                gap: 5px;
            }
        </style>
    </head>
    <body>

        <%@ include file="/INC/cabecera.jsp" %>
        <%@ include file="/INC/barraCliente.jsp" %>
        
        <c:if test="${not empty creada}">
            <div id="mensajeFlotante" class="mensaje-flotanteCorrecto mt-5">
                <c:out value="${creada}" />
            </div>
        </c:if>
        
        <c:if test="${not empty editada}">
            <div id="mensajeFlotante" class="mensaje-flotanteCorrecto mt-5">
                <c:out value="${editada}" />
            </div>
        </c:if>
        
        <c:if test="${not empty eliminada}">
            <div id="mensajeFlotante" class="mensaje-flotanteCorrecto mt-5">
                <c:out value="${eliminada}" />
            </div>
        </c:if>
        
        <c:if test="${not empty error}">
            <div id="mensajeFlotante" class="mensaje-flotanteError mt-5">
                <c:out value="${error}" />
            </div>
        </c:if>

        <div class="container mt-4">
            <h2 class="mb-4">Tus Mascotas</h2>

            <!-- Botón CREAR mascota -->
            <form action="MascotaController" method="POST">
                <input type="hidden" name="accion" value="crearMascota">
                <button type="submit" class="btn btn-success mb-3">
                    <i class="fas fa-plus-circle"></i> Añadir Mascota
                </button>
            </form>

            <!-- Mostrar mensaje si no hay mascotas -->
            <c:if test="${empty listaMascotas}">
                <div class="alert alert-info text-center">
                    <i class="fas fa-dog fa-lg"></i> Aún no tienes mascotas registradas. ¡Añade la primera!
                </div>
            </c:if>

            <!-- Mostrar tabla si hay mascotas -->
            <c:if test="${not empty listaMascotas}">
                <table class="table table-striped align-middle">
                    <thead>
                        <tr>
                            <th>Foto</th>
                            <th>Nombre</th>
                            <th>Especie</th>
                            <th>Raza</th>
                            <th>Edad (Años)</th>
                            <th>Acciones</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="mascota" items="${listaMascotas}">
                            <tr>
                                <td>
                                    <img src="${contexto}/IMG/fotosMascotas/${mascota.foto}" class="img-fluid">
                                </td>
                                <td>${mascota.nombre}</td>
                                <td>${mascota.especie}</td>
                                <td>${mascota.raza}</td>
                                <td>
                                    <c:if test="${mascota.fechaNacimiento != null}">
                                        <fmt:formatDate value="${mascota.fechaNacimiento}" pattern="yyyy" var="anioNacimiento"/>
                                        <fmt:formatDate value="<%= new java.util.Date()%>" pattern="yyyy" var="anioActual"/>
                                        ${anioActual - anioNacimiento}
                                    </c:if>
                                </td>
                                <td>
                                    <div class="btn-group">
                                        <!-- Botón EDITAR -->
                                        <form action="MascotaController" method="POST">
                                            <input type="hidden" name="accion" value="editarMascota">
                                            <input type="hidden" name="id" value="${mascota.id}">
                                            <button type="submit" class="btn btn-warning btn-sm text-white" title="Editar">
                                                <i class="fas fa-edit"></i>
                                            </button>
                                        </form>

                                        <!-- Botón ELIMINAR -->
                                        <button type="button" class="btn btn-danger btn-sm" title="Eliminar" data-toggle="modal" data-target="#confirmarEliminar${mascota.id}">
                                            <i class="fas fa-trash-alt"></i>
                                        </button>

                                        <!-- Modal confirmación eliminar -->
                                        <div class="modal fade" id="confirmarEliminar${mascota.id}" tabindex="-1" role="dialog">
                                            <div class="modal-dialog modal-dialog-centered" role="document">
                                                <div class="modal-content">
                                                    <div class="modal-header">
                                                        <h5 class="modal-title">Eliminar Mascota</h5>
                                                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                                                    </div>
                                                    <div class="modal-body">
                                                        ¿Seguro que quieres eliminar a <strong>${mascota.nombre}</strong> de "Tus Mascotas"?
                                                    </div>
                                                    <div class="modal-footer">
                                                        <button type="button" class="btn btn-secondary" data-dismiss="modal">
                                                            <i class="fas fa-times"></i> Cancelar
                                                        </button>
                                                        <form action="MascotaController" method="POST">
                                                            <input type="hidden" name="accion" value="eliminarMascota">
                                                            <input type="hidden" name="id" value="${mascota.id}">
                                                            <button type="submit" class="btn btn-danger">
                                                                <i class="fas fa-check"></i> Eliminar
                                                            </button>
                                                        </form>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>

                                        <!-- Botón VER -->
                                        <button type="button" class="btn btn-info btn-sm" title="Ver detalles" data-toggle="modal" data-target="#verDetalles${mascota.id}">
                                            <i class="fas fa-eye"></i>
                                        </button>

                                        <!-- Modal ver detalles -->
                                        <div class="modal fade" id="verDetalles${mascota.id}" tabindex="-1" role="dialog">
                                            <div class="modal-dialog modal-dialog-centered" role="document">
                                                <div class="modal-content">
                                                    <div class="modal-header">
                                                        <h5 class="modal-title">Información de ${mascota.nombre}</h5>
                                                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                                                    </div>
                                                    <div class="modal-body d-flex align-items-start">
                                                        <div class="mt-4 mr-4" style="min-width: 160px;">
                                                            <img src="${contexto}/IMG/fotosMascotas/${mascota.foto}" 
                                                                 class="img-fluid rounded" 
                                                                 style="width: 160px; height: 160px; object-fit: cover; box-shadow: 0px 2px 8px rgba(0, 0, 0, 0.1);">
                                                        </div>
                                                        <div class="d-flex flex-column justify-content-center" style="gap: 5px; margin-left: 30px">
                                                            <p><strong>Especie:</strong> ${mascota.especie}</p>
                                                            <p><strong>Raza:</strong> ${mascota.raza}</p>
                                                            <p><strong>Peso:</strong> ${mascota.peso} kg</p>
                                                            <p><strong>Fecha de nacimiento:</strong> 
                                                                <fmt:formatDate value="${mascota.fechaNacimiento}" pattern="dd/MM/yyyy"/>
                                                            </p>
                                                            <p><strong>Género:</strong> ${mascota.genero}</p>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </c:if>
        </div>

        <%@ include file="/INC/pie.jsp" %>

        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>

    </body>
</html>