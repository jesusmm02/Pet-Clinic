<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="es">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Panel de Cliente - Pet Clinic</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css">
        <style>
            .navbar-cliente {
                background-color: #e1f1f7;
                border-bottom: 1px solid #ccc;
                position: sticky;
                top: 0;
                z-index: 1030;
            }

            .navbar-cliente .nav-item {
                margin-right: 10px;
            }

            .navbar-cliente .nav-link {
                color: #007bff;
                font-size: 16px;
                font-weight: 500;
                transition: background-color 0.3s ease, color 0.3s ease;
            }

            .navbar-cliente .nav-link:hover {
                background-color: #007bff;
                color: #fff;
                border-radius: 5px;
            }

            @media (max-width: 768px) {
                .navbar-cliente {
                    display: flex;
                    flex-wrap: wrap;
                }
                .navbar-cliente .nav-item {
                    flex: 1 1 100%;
                    text-align: center;
                }
            }
        </style>

    </head>
    <body>

        <!-- Barra de navegación -->
        <nav class="navbar navbar-expand-lg navbar-light navbar-cliente">
            <div class="container-fluid">
                <div class="navbar-collapse" id="navbarNav">
                    <ul class="navbar-nav">
                        <li class="nav-item">
                            <form action="ClienteController" method="POST">
                                <input type="hidden" name="accion" value="homeCliente">
                                <button type="submit" 
                                        class="nav-link <c:if test='${pageContext.request.requestURI.contains("cliente")}'>active</c:if>">
                                    <i class="fas fa-home"></i>
                                </button>
                            </form>
                        </li>
                        <li class="nav-item">
                            <form action="ClienteController" method="POST">
                                <input type="hidden" name="accion" value="gestionMascotas">
                                <button type="submit" 
                                        class="nav-link <c:if test='${pageContext.request.requestURI.contains("mascotas")}'>active</c:if>">
                                    Tus Mascotas</button>
                            </form>
                        </li>
                        <li class="nav-item">
                            <form action="ClienteController" method="POST">
                                <input type="hidden" name="accion" value="solicitarCita">
                                <button type="submit" 
                                        class="nav-link <c:if test='${pageContext.request.requestURI.contains("citas")}'>active</c:if>">
                                    Citas</button>
                            </form>
                        </li>
                        <li class="nav-item">
                            <form action="ClienteController" method="POST">
                                <input type="hidden" name="accion" value="historialMedico">
                                <button type="submit" 
                                        class="nav-link <c:if test='${pageContext.request.requestURI.contains("historialMedico")}'>active</c:if>">
                                    Historiales médicos</button>
                            </form>
                        </li>
                        <li class="nav-item">
                            <form action="ClienteController" method="POST">
                                <input type="hidden" name="accion" value="verServicios">
                                <button type="submit" 
                                        class="nav-link <c:if test='${pageContext.request.requestURI.contains("servicios")}'>active</c:if>">
                                    Servicios disponibles</button>
                            </form>
                        </li>
                        <li class="nav-item">
                            <form action="ClienteController" method="POST">
                                <input type="hidden" name="accion" value="verInfografia">
                                <button type="submit" 
                                        class="nav-link <c:if test='${pageContext.request.requestURI.contains("infografia")}'>active</c:if>">
                                    Infografías</button>
                            </form>
                        </li>
                        <li class="nav-item">
                            <form action="ClienteController" method="POST">
                                <input type="hidden" name="accion" value="editarPerfil">
                                <button type="submit" 
                                        class="nav-link <c:if test='${pageContext.request.requestURI.contains("editarPerfil")}'>active</c:if>">
                                    Editar perfil</button>
                            </form>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>

        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
        <script src="https://kit.fontawesome.com/a076d05399.js" crossorigin="anonymous"></script>
    </body>
</html>
