<jsp:directive.page contentType="text/html" pageEncoding="UTF-8"/>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <jsp:include page="/INC/metas.inc"/>
        <title>Editar perfil - Pet Clinic</title>
        <link rel="stylesheet" href="${bootstrap}">
        <script src="${contexto}/JS/validarAvatar.js" defer></script>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
        <script src="${contexto}/JS/mensajeFlotante.js" defer></script>
    </head>
    <body>

        <%@ include file="/INC/cabecera.jsp" %>

        <%@ include file="/INC/barraCliente.jsp" %>
        
        <c:if test="${not empty error}">
            <div id="mensajeFlotante" class="mensaje-flotante">
                <c:choose>
                        <c:when test="${not empty error}">
                            ${error}
                        </c:when>
                    </c:choose>
            </div>
            
            
            <script>
                document.addEventListener("DOMContentLoaded", function () {
                    var mensaje = document.getElementById('mensajeFlotante');
                    if (mensaje) {
                        setTimeout(function () {
                            mensaje.style.opacity = '0';
                            setTimeout(function () {
                                mensaje.remove();
                            }, 1000);
                        }, 3000);
                    }
                });
            </script>
            <style>
                .mensaje-flotante {
                    position: absolute;
                    right: 20px;
                    margin-top: 10px;
                    background-color: #f8d7da;
                    color: #721c24;
                    padding: 10px 20px;
                    border: 1px solid #f5c6cb;
                    border-radius: 5px;
                    box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
                    z-index: 1050;
                    font-size: 14px;
                    animation: link 0.5s ease-out;
                }

                @keyframes link {
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
                        <label><strong>DNI</strong></label>
                        <input type="text" class="form-control" value="${usuario.dni}" readonly>
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
                        <input type="date" name="fechaNacimiento" class="form-control" value="${cliente.fechaNacimiento}">
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
                                <div class="form-group">
                                    <label><strong>Avatar Actual</strong></label>
                                    <p>${usuario.avatar}</p> <!-- Nombre del avatar actual -->
                                </div>
                            </c:if>
                        </label>
                        <div class="border p-3 rounded bg-light">
                            <input type="file" id="avatar" name="avatar" accept="image/*" value="${usuario.avatar}" class="form-control-file mx-auto">
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
