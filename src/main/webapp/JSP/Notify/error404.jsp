<jsp:directive.page contentType="text/html" pageEncoding="UTF-8"/>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="es">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <jsp:include page="/INC/metas.inc"/>
        <title>ERROR 404</title>
        <link rel="icon" href="${contexto}/IMG/logoPetClinic_min.png" type="image/x-icon" />
        <link rel="stylesheet" href="${bootstrap}">
        <style>
            body {
                display: flex;
                flex-direction: column;
                justify-content: center;
                align-items: center;
                height: 100vh;
                background-color: #f7f9fc;
                margin: 0;
                font-family: Arial, sans-serif;
            }
            .container {
                text-align: center;
                max-width: 500px;
            }
            .logo {
                width: 280px;
                margin-bottom: 20px;
            }
            .animation {
                width: 300px;
                margin-bottom: 20px;
            }
            h1 {
                font-size: 2.5rem;
                color: #333;
                margin-bottom: 10px;
            }
            p {
                font-size: 1.2rem;
                margin-bottom: 30px;
                color: #666;
            }
            a {
                text-decoration: none;
                color: #007bff;
                font-size: 1.1rem;
            }
            a:hover {
                color: #0056b3;
            }
        </style>
    </head>
    <body style="text-align: center">
        <img src="${pageContext.request.contextPath}/IMG/logoPetClinic.png" class="logo" alt="Logo">
        <img src="https://cdni.iconscout.com/illustration/premium/thumb/cat-with-laptop-error-illustration-download-in-svg-png-gif-file-formats--page-404-pack-design-development-illustrations-10163650.png?f=webp" class="animation" alt="404 Illustration">
        <h1>¡Ups! Página no encontrada</h1>
        <p>Lo sentimos, pero la página que buscas no está disponible. Puede que hayas escrito mal la URL o que el contenido haya sido movido o eliminado.</p>
        <p>
            <c:choose>
                <c:when test="${sessionScope.usuario != null && sessionScope.usuario.rol == 'VETERINARIO'}">
                    <form action="${pageContext.request.contextPath}/VeterinarioController" method="POST">
                        <input type="hidden" name="accion" value="homeVeterinario">
                        <button type="submit" class="btn btn-secondary">Volver al menú principal</button>
                    </form>
                </c:when>
                <c:when test="${sessionScope.usuario != null && sessionScope.usuario.rol == 'CLIENTE'}">
                    <form action="${pageContext.request.contextPath}/ClienteController" method="POST">
                        <input type="hidden" name="accion" value="homeCliente">
                        <button type="submit" class="btn btn-secondary">Volver al menú principal</button>
                    </form>
                </c:when>
                <c:otherwise>
                    <a href="${pageContext.request.contextPath}/FrontController">Volver al menú principal</a>
                </c:otherwise>
            </c:choose>
        </p>
    </body>
</html>
