<jsp:directive.page contentType="text/html" pageEncoding="UTF-8"/>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html lang="es">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <jsp:include page="/INC/metas.inc"/>
        <title>Editar perfil - Pet Clinic</title>
        <link rel="stylesheet" href="${bootstrap}">
        <script src="${contexto}/JS/validarAvatar.js" defer></script>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
        <script src="${contexto}/JS/mensajeFlotante.js" defer></script>
        <script src="${contexto}/JS/vistaAvatarEditar.js" defer></script>
        
    </head>
    <body>

        <%@ include file="/INC/cabecera.jsp" %>
        <%@ include file="/INC/barraCliente.jsp" %>
        
        
        <c:if test="${not empty error}">
            <div id="mensajeFlotante" class="mensaje-flotanteError mt-5">
                <c:out value="${error}" />
            </div>
        </c:if>


        <div class="container mt-5">
            <h2>Editar Perfil</h2>
            <form action="ClienteController" method="POST" enctype="multipart/form-data">
                <input type="hidden" name="accion" value="guardarCambios">


                <div class="row">
                    <div class="form-group col-md-6">
                        <label><strong>Email</strong></label>
                        <input type="email" class="form-control" value="${usuario.email}" readonly>
                    </div>
                    <div class="form-group col-md-6">
                        <label><strong>Número de identificación</strong></label>
                        <input type="text" class="form-control" value="${usuario.numIdentificacion}" readonly>
                    </div>
                </div>


                <div class="row">
                    <div class="form-group col-md-6">
                        <label><strong>* Nombre</strong></label>
                        <input type="text" class="form-control" name="nombre" value="${usuario.nombre}">
                    </div>
                    <div class="form-group col-md-6">
                        <label><strong>* Apellidos</strong></label>
                        <input type="text" class="form-control" name="apellidos" value="${usuario.apellidos}">
                    </div>
                </div>


                <div class="row">
                    <div class="form-group col-md-6">
                        <label><strong>* Género:</strong></label>
                        <select name="genero" class="form-control">
                            <option value="Hombre" ${cliente.genero == 'HOMBRE' ? 'selected' : ''}>Hombre</option>
                            <option value="Mujer" ${cliente.genero == 'MUJER' ? 'selected' : ''}>Mujer</option>
                            <option value="Otro" ${cliente.genero == 'OTRO' ? 'selected' : ''}>Otro</option>
                        </select>
                    </div>
                    <div class="form-group col-md-6">
                        <label><strong>Fecha de nacimiento:</strong></label>
                        <input type="date" class="form-control" name="fechaNacimiento" value="<fmt:formatDate value='${usuario.fechaNacimiento}' pattern='yyyy-MM-dd' />">
                    </div>
                </div>

                <div class="form-group col-md-6">
                    <label><strong>Contraseña Actual</strong></label>
                    <input type="password" class="form-control" name="passwordActual">
                </div>


                <div class="row">
                    <div class="form-group col-md-6">
                        <label><strong>Nueva Contraseña</strong></label>
                        <input type="password" class="form-control" name="passwordNueva1">
                    </div>
                    <div class="form-group col-md-6">
                        <label><strong>Repetir Nueva Contraseña</strong></label>
                        <input type="password" class="form-control" name="passwordNueva2">
                    </div>
                </div>

                <div class="row justify-content-center">
                    <div class="form-group col-md-6 text-center">
                        <label for="avatar">
                            <c:if test="${not empty usuario.avatar}">
                                <div class="form-group mb-3">
                                    <label><strong>Avatar Actual</strong></label>
                                    <div class="border p-3 rounded bg-light">
                                        <img 
                                            src="${pageContext.request.contextPath}/IMG/avatares/${usuario.avatar}"
                                            class="img-fluid rounded-circle" 
                                            id="vistaPreviaAvatar" 
                                            style="max-width: 150px; max-height: 150px;">
                                    </div>
                                </div>
                            </c:if>
                        </label>

                        <div class="border p-3 rounded bg-light">
                            <input type="file" id="avatar" name="avatar" accept="image/*" class="form-control-file mx-auto">
                        </div>
                        <p id="avatarError" style="color: red"></p>
                    </div>
                </div>

                <!-- Botones para confirmar o volver -->
                <div class="row">
                    <div class="col-md-12 text-center">
                        <button type="submit" class="btn btn-primary" name="accion" value="guardarCambios">Guardar Cambios</button>
                    </div>
                </div>
                
            </form>
                        
            <div class="row mt-3">
                <div class="col-md-12 text-center">
                    <form action="ClienteController" method="POST">
                        <input type="hidden" name="accion" value="volver">
                        <button type="submit" class="btn btn-secondary">
                            Volver
                        </button>
                    </form>
                </div>
            </div>            
                        
        </div>

        <%@ include file="/INC/pie.jsp" %>

    </body>
</html>
