<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="es">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Panel de Veterinario - Pet Clinic</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css">
        <style>
            .navbar-veterinario {
                background-color: #e1f1f7;
                border-bottom: 1px solid #ccc;
                position: sticky;
                top: 0;
                z-index: 1030;
            }

            .navbar-veterinario .nav-item {
                margin-right: 10px;
            }

            .navbar-veterinario .nav-link {
                color: #007bff;
                font-size: 16px;
                font-weight: 500;
                transition: background-color 0.3s ease, color 0.3s ease;
            }

            .navbar-veterinario .nav-link:hover {
                background-color: #007bff;
                color: #fff;
                border-radius: 5px;
            }

            @media (max-width: 768px) {
                .navbar-veterinario {
                    display: flex;
                    flex-wrap: wrap;
                }
                .navbar-veterinario .nav-item {
                    flex: 1 1 100%;
                    text-align: center;
                }
            }
        </style>
    </head>
    <body>

        <!-- Barra de navegación -->
        <nav class="navbar navbar-expand-lg navbar-light navbar-veterinario">
            <div class="container-fluid">
                <div class="navbar-collapse" id="navbarNav">
                    <ul class="navbar-nav">

                        <!-- Botón de Home -->
                        <li class="nav-item">
                            <form action="VeterinarioController" method="POST">
                                <input type="hidden" name="accion" value="homeVeterinario">
                                <button type="submit" 
                                        class="nav-link <c:if test='${pageContext.request.requestURI.contains("veterinario.jsp")}'>active</c:if>">
                                    <i class="fas fa-home"></i>
                                </button>
                            </form>
                        </li>

                        <!-- Ver Clientes -->
                        <li class="nav-item">
                            <form action="VeterinarioController" method="POST">
                                <input type="hidden" name="accion" value="verClientes">
                                <button type="submit" 
                                        class="nav-link <c:if test='${pageContext.request.requestURI.contains("clientes_mascotas")}'>active</c:if>">
                                    Ver Clientes
                                </button>
                            </form>
                        </li>

                        <!-- Historial Médico -->
                        <li class="nav-item">
                            <form action="VeterinarioController" method="POST">
                                <input type="hidden" name="accion" value="historialMedico">
                                <button type="submit" 
                                        class="nav-link <c:if test='${pageContext.request.requestURI.contains("historialMedico")}'>active</c:if>">
                                    Historiales médicos
                                </button>
                            </form>
                        </li>

                        <!-- Ver citas -->
                        <li class="nav-item">
                            <form action="VeterinarioController" method="POST">
                                <input type="hidden" name="accion" value="citas">
                                <button type="submit" 
                                        class="nav-link <c:if test='${pageContext.request.requestURI.contains("citas")}'>active</c:if>">
                                    Ver citas
                                </button>
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
