<jsp:directive.page contentType="text/html" pageEncoding="UTF-8"/>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="es">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <jsp:include page="/INC/metas.inc"/>
        <title>Registrar Mascota - Pet Clinic</title>
        <link rel="icon" href="${contexto}/IMG/logoPetClinic_min.png" type="image/x-icon" />
        <link rel="stylesheet" href="${bootstrap}">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
        <link rel="stylesheet" href="${contexto}/CSS/mensajeFlotante.css">
        <script src="${contexto}/JS/mensajeFlotante.js" defer></script>
        <script src="${contexto}/JS/validarAvatarMascota.js" defer></script>
        <script src="${contexto}/JS/vistaAvatarMascota.js" defer></script>
        <script src="${contexto}/JS/quitarFoto.js" defer></script>
        <script src="${contexto}/JS/selectMascotas.js" defer></script>
    </head>
    <body>

        <%@ include file="/INC/cabecera.jsp" %>
        <%@ include file="/INC/barraCliente.jsp" %>
        
        <c:if test="${not empty error}">
            <div id="mensajeFlotante" class="mensaje-flotanteError mt-5">
                <c:out value="${error}" />
            </div>
        </c:if>

        <!-- Mostrar el mensaje de error si existe -->
        <c:if test="${not empty errorCreate}">
            <div id="mensajeFlotante" class="mensaje-flotanteError mt-5">
                <c:out value="${errorCreate}" />
            </div>
        </c:if>

        <div class="container mt-4">
            <h2><i class="fas fa-paw"></i> Registro de Mascota</h2>

            <form action="${contexto}/MascotaController" method="POST" enctype="multipart/form-data" class="mt-4">
                <input type="hidden" name="accion" value="guardarMascota">

                <div class="row">
                    <div class="col-md-6 mb-3">
                        <label for="nombre" class="form-label">* Nombre</label>
                        <input type="text" class="form-control" id="nombre" name="nombre">
                    </div>

                    <div class="col-md-6 mb-3">
                        <label for="especie" class="form-label">* Especie</label>
                        <select class="form-select" id="selectEspecie" name="especie">
                            <option value="" disabled selected>Seleccione una especie...</option>
                            <c:forEach var="especie" items="${listaEspecies}">
                                <option value="${especie}">${especie}</option>
                            </c:forEach>
                            <option value="otra">Otra...</option>
                        </select>
                        <input type="text" class="form-control mt-2" id="inputEspecie" name="especie" placeholder="Especie no registrada" style="display: none;">
                    </div>
                </div>

                <div class="row">
                    <div class="col-md-6 mb-3">
                        <label for="raza" class="form-label">* Raza</label>
                        <select class="form-select" id="selectRaza" name="raza">
                            <option value="" disabled selected>Seleccione una raza...</option>
                            <c:forEach var="raza" items="${listaRazas}">
                                <option value="${raza}">${raza}</option>
                            </c:forEach>
                            <option value="otra">Otra...</option>
                        </select>
                        <input type="text" class="form-control mt-2" id="inputRaza" name="raza" placeholder="Raza no registrada" style="display: none;">
                    </div>

                    <div class="col-md-6 mb-3">
                        <label for="fechaNacimiento" class="form-label">Fecha de nacimiento</label>
                        <input type="date" class="form-control" id="fechaNacimiento" name="fechaNacimiento">
                    </div>
                </div>

                <div class="row">
                    <div class="col-md-6 mb-3">
                        <label for="peso" class="form-label">* Peso (kg)</label>
                        <input type="number" class="form-control" id="peso" name="peso" step="0.01" min="0">
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
                        <label for="foto" class="form-label">Foto</label>
                        <input type="file" class="form-control" id="foto" name="foto" accept="image/*">
                        <small class="form-text text-muted">Se permite .png, .jpg o .jpeg</small>
                        <img class="mt-2" id="vistaPreviaFoto">
                        <button type="button" id="btnQuitarFoto" class="btn btn-sm btn-outline-danger mt-2" style="display:none;">Quitar foto</button>
                        <p id="fotoError" style="color: red"></p>
                    </div>
                </div>

                <div class="mt-4 d-flex justify-content-between">
                    <!--<form action="ClienteController" method="POST" class="d-inline">
                        <input type="hidden" name="accion" value="gestionMascotas">
                        <button type="submit" class="btn btn-secondary">Volver</button>
                    </form>-->
                    <button type="submit" id="btnRegistrarMascota" name="accion" value="guardarMascota" class="btn btn-success">Guardar mascota</button>
                </div>
            </form>
        </div>

        <%@ include file="/INC/pie.jsp" %>

    </body>
</html>
