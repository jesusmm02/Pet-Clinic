<jsp:directive.page contentType="text/html" pageEncoding="UTF-8"/>

<!DOCTYPE html>
<html lang="es">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <jsp:include page="/INC/metas.inc"/>
        <title>Gestión de Citas - Pet Clinic</title>
        <link rel="icon" href="${contexto}/IMG/logoPetClinic_min.png" type="image/x-icon" />
        <link rel="stylesheet" href="${bootstrap}">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
        <link rel="stylesheet" href="${contexto}/CSS/mensajeFlotante.css">
        <script src="${contexto}/JS/mensajeFlotante.js" defer></script>
    </head>
    <body>

        <%@ include file="/INC/cabecera.jsp" %>
        <%@ include file="/INC/barraCliente.jsp" %>

        <div class="container mt-5">
            <h2 class="text-center text-primary mb-4">Gestión de Citas</h2>

            <!-- Menú de navegación -->
            <div class="d-flex justify-content-center gap-3 mb-4">
                <form action="CitaController" method="POST">
                    <input type="hidden" name="accion" value="verCitas">
                    <button type="submit" class="btn btn-success btn-block"><i class="fas fa-calendar-check"></i> Ver mis citas</button>
                </form>
                <form action="CitaController" method="POST">
                    <input type="hidden" name="accion" value="solicitarCita">
                    <button type="submit" class="btn btn-success btn-block"><i class="fas fa-calendar-plus"></i> Solicitar cita</button>
                </form>
            </div>

            <!-- Icono representativo -->
            <div class="text-center mt-5">
                <i class="fas fa-paw fa-5x text-secondary"></i>
            </div>
        </div>

        <%@ include file="/INC/pie.jsp" %>

    </body>
</html>
