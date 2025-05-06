<jsp:directive.page contentType="text/html" pageEncoding="UTF-8"/>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="es">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <jsp:include page="/INC/metas.inc"/>
        <title>Editar Mascota - Pet Clinic</title>
        <link rel="stylesheet" href="${bootstrap}">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
        <script src="${contexto}/JS/mensajeFlotante.js" defer></script>
        <style>
            .mensaje-flotanteError {
                position: fixed;
                top: 80px;
                right: 20px;
                background-color: #f8d7da;
                color: #721c24;
                padding: 10px 20px;
                border: 1px solid #f5c6cb;
                border-radius: 5px;
                box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
                z-index: 1050;
                font-size: 14px;
                animation: fadeIn 0.5s ease-out;
            }

            @keyframes fadeIn {
                from {
                    opacity: 0;
                    transform: translateY(-10px);
                }
                to {
                    opacity: 1;
                    transform: translateY(0);
                }
            }
        </style>
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

        <div class="container mt-4">
            <h2><i class="fas fa-edit"></i> Editar información de ${mascota.nombre}</h2>

            <form action="MascotaController" method="POST" enctype="multipart/form-data" class="mt-4">
                <input type="hidden" name="accion" value="modificarMascota">
                <input type="hidden" name="id" value="${mascota.id}">

                <div class="row">
                    <div class="col-md-6 mb-3">
                        <label for="nombre" class="form-label">* Nombre</label>
                        <input type="text" class="form-control" id="nombre" name="nombre" value="${mascota.nombre}">
                    </div>

                    <div class="col-md-6 mb-3">
                        <label for="especie" class="form-label">* Especie</label>
                        <input type="text" class="form-control" id="especie" name="especie" value="${mascota.especie}">
                    </div>
                </div>

                <div class="row">
                    <div class="col-md-6 mb-3">
                        <label for="raza" class="form-label">* Raza</label>
                        <input type="text" class="form-control" id="raza" name="raza" value="${mascota.raza}">
                    </div>

                    <div class="col-md-6 mb-3">
                        <label for="fechaNacimiento" class="form-label">Fecha de nacimiento</label>
                        <input type="date" class="form-control" id="fechaNacimiento" name="fechaNacimiento" value="${mascota.fechaNacimiento}">
                    </div>
                </div>

                <div class="row">
                    <div class="col-md-6 mb-3">
                        <label for="peso" class="form-label">* Peso (kg)</label>
                        <input type="number" class="form-control" id="peso" name="peso" step="0.01" min="0" value="${mascota.peso}">
                    </div>

                    <div class="col-md-6 mb-3">
                        <label for="genero" class="form-label">* Género</label>
                        <select class="form-select" id="genero" name="genero">
                            <option value="MACHO" ${mascota.genero == 'MACHO' ? 'selected' : ''}>Macho</option>
                            <option value="HEMBRA" ${mascota.genero == 'HEMBRA' ? 'selected' : ''}>Hembra</option>
                        </select>
                    </div>
                </div>

                <div class="row">
                    <div class="col-md-6 mb-3">
                        <label for="foto" class="form-label">Foto (actual: ${mascota.foto})</label>
                        <input type="file" class="form-control" id="foto" name="foto" accept="image/*">
                    </div>
                </div>

                <div class="mt-4 d-flex justify-content-between">
                    <button type="submit" class="btn btn-warning text-white">
                        <i class="fas fa-save"></i> Guardar cambios
                    </button>
                </div>
            </form>

            <form action="ClienteController" method="POST" class="mt-3">
                <input type="hidden" name="accion" value="gestionMascotas">
                <button type="submit" class="btn btn-secondary">
                    <i class="fas fa-arrow-left"></i> Volver
                </button>
            </form>
        </div>

        <%@ include file="/INC/pie.jsp" %>

    </body>
</html>
