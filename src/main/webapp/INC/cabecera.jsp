<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<style>
    .btn-primary:hover {
        background-color: #004c99 !important;
        border-color: #004c99 !important;
    }
</style>

<nav class="navbar bg-primary-subtle" style="height: 120px; padding: 0 20px;">
    <div class="container-fluid d-flex justify-content-between align-items-center">

        <a class="navbar-brand d-flex align-items-center">
            <img src="${pageContext.request.contextPath}/IMG/logoPetClinic.png"
                 alt="Logo"
                 class="mr-3"
                 style="max-height: 119px; object-fit: contain;">
            <strong class="fs-2">PET CLINIC</strong>
        </a>

        <div class="d-flex align-items-center">
            <c:choose>
                <c:when test="${not empty sessionScope.usuario}">
                    <form action="${contexto}/FrontController" method="POST">
                        <button type="submit" class="btn btn-outline-danger mr-3" name="boton" value="logout">Cerrar sesión</button>
                    </form>

                    <div class="d-flex flex-column align-items-center">
                        <img src="${contexto}/IMG/avatares/${sessionScope.usuario.avatar}" class="rounded-circle" style="width: 40px; height: 40px;">
                        <span class="ml-2">${sessionScope.usuario.nombre}</span>

                        <c:if test="${not empty sessionScope.usuario.ultimoAcceso}">
                            <span class="text-muted" style="font-size: 12px;">
                                Último acceso: 
                                <fmt:formatDate value="${sessionScope.usuario.ultimoAcceso}" pattern="dd/MM/yyyy HH:mm"/>
                            </span>
                        </c:if>
                    </div>
                </c:when>
                <c:otherwise>
                    <button type="button" class="btn btn-primary me-4" data-bs-toggle="modal" data-bs-target="#modalLogin">
                        Iniciar Sesi&oacute;n
                    </button>
                    <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#modalRegistro">
                        Registrarse como cliente
                    </button>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
</nav>