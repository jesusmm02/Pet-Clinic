<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="es">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Panel de Cliente - Pet Clinic</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css">
        <style>

            /* Aplica solo a la barra de navegación para clientes */
            .navbar-cliente {
                background-color: #e1f1f7; /* Color de fondo de la barra */
            }

            .navbar-cliente .navbar-brand {
                font-size: 1.8rem;
                font-weight: bold;
                color: #4a7c68; /* Color verde del logo */
            }

            .navbar-cliente .navbar-brand img {
                max-width: 30px;
                margin-right: 10px;
            }

            .navbar-cliente .nav-pills .nav-link {
                border-radius: 0.25rem;
                font-size: 16px;
                font-weight: 500;
                padding: 10px 20px;
                color: #007bff;
                transition: background-color 0.3s ease, color 0.3s ease;
                border: none;
                background-color: transparent;
            }

            .navbar-cliente .nav-pills .nav-link:hover {
                background-color: #007bff; /* Cambia el fondo al pasar el cursor */
                color: #fff; /* Cambia el texto al pasar el cursor */
                text-decoration: underline; /* Subraya el texto al pasar el cursor */
            }

            .navbar-cliente .nav-pills .nav-link.active {
                background-color: #0056b3; /* Color del botón activo */
                color: #fff; /* Color del texto activo */
                border: 2px solid #003d73; /* Color más oscuro del botón activo */
            }

            .navbar-cliente .nav-pills {
                margin-bottom: 20px;
            }

            .navbar-cliente .card {
                margin-bottom: 20px;
                box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
                border-radius: 10px;
            }

            .navbar-cliente .card-body {
                padding: 20px;
            }

            .navbar-cliente .card .btn-block {
                width: 100%;
            }

            .navbar-cliente .btn-success {
                background-color: #4a7c68;
                border-color: #4a7c68;
            }

            .navbar-cliente .btn-primary {
                background-color: #007bff;
                border-color: #007bff;
            }

            .navbar-cliente .btn-warning {
                background-color: #ffcb01;
                border-color: #ffcb01;
            }

            .navbar-cliente .btn-info {
                background-color: #00aaff;
                border-color: #00aaff;
            }


        </style>
    </head>
    <body>

        <!-- Barra de navegación -->
        <nav class="navbar navbar-expand-lg navbar-light navbar-cliente">
            <div class="container-fluid">
                <div class="collapse navbar-collapse" id="navbarNav">
                    <ul class="navbar-nav">
                        <li class="nav-item">
                            <form action="ClienteController" method="POST">
                                <input type="hidden" name="accion" value="gestionMascotas">
                                <button type="submit" class="nav-link">Tus Mascotas</button>
                            </form>
                        </li>
                        <li class="nav-item">
                            <form action="ClienteController" method="POST">
                                <input type="hidden" name="accion" value="solicitarCita">
                                <button type="submit" class="nav-link">Cita</button>
                            </form>
                        </li>
                        <li class="nav-item">
                            <form action="ClienteController" method="POST">
                                <input type="hidden" name="accion" value="historialMedico">
                                <button type="submit" class="nav-link">Historial médico</button>
                            </form>
                        </li>
                        <li class="nav-item">
                            <form action="ClienteController" method="POST">
                                <input type="hidden" name="accion" value="verInfografia">
                                <button type="submit" class="nav-link">Infografías</button>
                            </form>
                        </li>
                        <li class="nav-item">
                            <form action="ClienteController" method="POST">
                                <input type="hidden" name="accion" value="editarPerfil">
                                <button type="submit" class="nav-link">Editar perfil</button>
                            </form>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>


        <!-- Bootstrap 5 JS y Popper.js -->
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>
