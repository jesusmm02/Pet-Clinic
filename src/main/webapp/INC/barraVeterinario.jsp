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
            }

            .navbar-veterinario .navbar-brand {
                font-size: 1.8rem;
                font-weight: bold;
                color: #4a7c68;
            }

            .navbar-veterinario .navbar-brand img {
                max-width: 30px;
                margin-right: 10px;
            }

            .navbar-veterinario .nav-pills .nav-link {
                border-radius: 0.25rem;
                font-size: 16px;
                font-weight: 500;
                padding: 10px 20px;
                color: #007bff;
                transition: background-color 0.3s ease, color 0.3s ease;
                border: none;
                background-color: transparent;
            }

            .navbar-veterinario .nav-pills .nav-link:hover {
                background-color: #007bff;
                color: #fff;
                text-decoration: underline;
            }

            .navbar-veterinario .nav-pills .nav-link.active {
                background-color: #0056b3;
                color: #fff;
                border: 2px solid #003d73;
            }

            .navbar-veterinario .nav-pills {
                margin-bottom: 20px;
            }

            .navbar-veterinario .card {
                margin-bottom: 20px;
                box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
                border-radius: 10px;
            }

            .navbar-veterinario .card-body {
                padding: 20px;
            }

            .navbar-veterinario .card .btn-block {
                width: 100%;
            }

            .navbar-veterinario .btn-success {
                background-color: #4a7c68;
                border-color: #4a7c68;
            }

            .navbar-veterinario .btn-primary {
                background-color: #007bff;
                border-color: #007bff;
            }

            .navbar-veterinario .btn-warning {
                background-color: #ffcb01;
                border-color: #ffcb01;
            }

            .navbar-veterinario .btn-info {
                background-color: #00aaff;
                border-color: #00aaff;
            }

        </style>
    </head>
    <body>

        <!-- Barra de navegación -->
        <nav class="navbar navbar-expand-lg navbar-light navbar-veterinario">
            <div class="container-fluid">
                <div class="collapse navbar-collapse" id="navbarNav">
                    <ul class="navbar-nav">
                        <li class="nav-item">
                            <form action="VeterinarioController" method="POST">
                                <input type="hidden" name="accion" value="verClientes">
                                <button type="submit" class="nav-link">Ver Clientes</button>
                            </form>
                        </li>
                        <li class="nav-item">
                            <form action="VeterinarioController" method="POST">
                                <input type="hidden" name="accion" value="historialMedico">
                                <button type="submit" class="nav-link">Historiales médicos</button>
                            </form>
                        </li>
                        <li class="nav-item">
                            <form action="VeterinarioController" method="POST">
                                <input type="hidden" name="accion" value="citas">
                                <button type="submit" class="nav-link">Ver citas</button>
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
