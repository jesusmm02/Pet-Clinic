<jsp:directive.page contentType="text/html" pageEncoding="UTF-8"/>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <jsp:include page="/INC/metas.inc"/>
        <title>Completar perfil - Pet Clinic</title>
        <link rel="stylesheet" href="${bootstrap}">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
        <script src="${contexto}/JS/mensajeFlotante.js" defer></script>
        <style>
            .mensaje-flotanteError {
                position: fixed;
                top: 20px;
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

            .mensaje-flotanteCorrecto {
                position: fixed;
                top: 20px;
                right: 20px;
                background-color: #66ff66;
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

        <h2>Completa tu perfil</h2>

        <form action="${contexto}/CompletarPerfilController" method="POST">
            <input type="hidden" name="idUsuario" value="${usuario.id}">

            <div class="row mb-3">
                <div class="col-md-6">
                    <label for="genero" class="form-label">Género</label>
                    <select class="form-select" id="genero" name="genero">
                        <option value="MUJER" ${usuario.genero == 'MUJER' ? 'selected' : ''}>Mujer</option>
                        <option value="HOMBRE" ${usuario.genero == 'HOMBRE' ? 'selected' : ''}>Hombre</option>
                        <option value="OTRO" ${usuario.genero == 'OTRO' ? 'selected' : ''}>Otro</option>
                    </select>
                </div>

                <div class="col-md-6">
                    <label for="fechaNacimiento" class="form-label">Fecha de nacimiento</label>
                    <input type="date" class="form-control" id="fechaNacimiento" name="fechaNacimiento"}">
                </div>
            </div>

            <button type="submit" class="btn btn-success">Completar</button>
        </form>

        <%@ include file="/INC/pie.jsp" %>

    </body>
</html>
