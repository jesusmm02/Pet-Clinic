<jsp:directive.page contentType="text/html" pageEncoding="UTF-8"/>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="es">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <jsp:include page="/INC/metas.inc"/>
        <title>Editar Mascota - Pet Clinic</title>
        <link rel="icon" href="${contexto}/IMG/logoPetClinic_min.png" type="image/x-icon" />
        <link rel="stylesheet" href="${bootstrap}">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
        <link rel="stylesheet" href="${contexto}/CSS/mensajeFlotante.css">
        <script src="${contexto}/JS/mensajeFlotante.js" defer></script>
        <script src="${contexto}/JS/validarAvatarMascota.js" defer></script>
        <script src="${contexto}/JS/vistaFotoEditar.js" defer></script>
        <script src="${contexto}/JS/quitarFotoEditar.js" defer></script>
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
                        <select class="form-select" id="selectEspecie" name="especie">
                            <option value="" disabled>Seleccione una especie...</option>
                            <c:forEach var="especie" items="${listaEspecies}">
                                <option value="${especie}" ${mascota.especie == especie ? 'selected' : ''}>${especie}</option>
                            </c:forEach>
                            <option value="otra" ${mascota.especie == 'otra' ? 'selected' : ''}>Otra...</option>
                        </select>
                        <input type="text" class="form-control mt-2" id="inputEspecie" name="especie" placeholder="Especie no registrada" style="display: none;" value="${mascota.especie != 'otra' ? '' : mascota.especie}">
                    </div>
                </div>

                <div class="row">
                    <div class="col-md-6 mb-3">
                        <label for="raza" class="form-label">* Raza</label>
                        <select class="form-select" id="selectRaza" name="raza">
                            <option value="" disabled>Seleccione una raza...</option>
                            <c:forEach var="raza" items="${listaRazas}">
                                <option value="${raza}" ${mascota.raza == raza ? 'selected' : ''}>${raza}</option>
                            </c:forEach>
                            <option value="otra" ${mascota.raza == 'otra' ? 'selected' : ''}>Otra...</option>
                        </select>
                        <input type="text" class="form-control mt-2" id="inputRaza" name="raza" placeholder="Raza no registrada" style="display: none;" value="${mascota.raza != 'otra' ? '' : mascota.raza}">
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

                <div class="row justify-content-center">
                    <div class="col-md-6 mb-3 text-center">
                        <label for="foto" class="form-label">Foto Actual:</label>

                        <!-- Mostrar la foto actual si existe -->
                        <c:if test="${not empty mascota.foto}">
                            <div class="border p-3 rounded bg-light mb-3 d-flex justify-content-center">
                                <img 
                                    src="${pageContext.request.contextPath}/IMG/fotosMascotas/${mascota.foto}" 
                                    alt="Foto de Mascota" 
                                    id="vistaPreviaFoto" 
                                    class="img-fluid"
                                    style="max-width: 150px; max-height: 150px; object-fit: cover; border-radius: 10px;">
                            </div>
                        </c:if>

                        <!-- Input para seleccionar una nueva foto -->
                        <div class="border p-3 rounded bg-light d-flex justify-content-center">
                            <input type="file" id="foto" name="foto" accept="image/*" class="form-control-file">
                        </div>
                        
                        <div class="mt-2 text-center">
                            <input type="hidden" name="quitarFoto" id="quitarFotoMascota" value="false">
                            <button type="button" id="btnQuitarFoto" class="btn btn-sm btn-outline-danger" style="display:none;">Quitar foto</button>
                        </div>

                        <p id="fotoError" style="color: red"></p>
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
