<jsp:directive.page contentType="text/html" pageEncoding="UTF-8"/>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:url var="bootstrap" value="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" scope="application" />
<c:set var="contexto" value="${pageContext.request.contextPath}" scope="application" />

<!DOCTYPE html>
<html lang="es">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <jsp:include page="/INC/metas.inc"/>
        <title>Pet Clinic</title>
        <link rel="icon" href="${contexto}/IMG/logoPetClinic_min.png" type="image/x-icon" />
        <link rel="stylesheet" href="${bootstrap}">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
        <link rel="stylesheet" href="${contexto}/CSS/mensajeFlotante.css">
        <script src="${contexto}/JS/mensajeFlotante.js" defer></script>
        <script src="${contexto}/JS/comprobarEmail.js" defer></script>
        <script src="${contexto}/JS/numIdentificacion.js" defer></script>
        <script src="${contexto}/JS/password.js" defer></script>
        <script src="${contexto}/JS/validarAvatar.js" defer></script>
        <script src="${contexto}/JS/vistaAvatar.js" defer></script>
        <script src="${contexto}/JS/quitarAvatar.js" defer></script>
        <style>
            .modal-dialog {
              max-width: 800px;
              width: 90vw;
            }
            .modal-content {
              max-height: 90vh;
              display: flex;
              flex-direction: column;
            }
            .modal-body {
              overflow-y: auto;
              max-height: calc(90vh - 140px)
            }
            .modal-footer {
              border-top: 1px solid #dee2e6;
              padding-top: 1rem;
              padding-bottom: 1rem;
              background-color: #fff;
              flex-shrink: 0;
            }
        </style>
    </head>
    <body>

        <%@ include file="/INC/cabecera.jsp" %>

        <c:if test="${not empty error}">
            <div id="mensajeFlotante" class="mensaje-flotanteError mt-5">
                <c:out value="${error}" />
            </div>
        </c:if>

        <!-- Mostrar el mensaje de error si existe -->
        <c:if test="${not empty errorCreate}">
            <div id="mensajeFlotante" class="mensaje-flotanteError mt-5">
                <c:out value="${errorCreate}" />
            </div>
        </c:if>

        <c:if test="${not empty logout}">
            <div id="mensajeFlotante" class="mensaje-flotanteCorrecto mt-5">
                <c:out value="${logout}" />
            </div>
        </c:if>

        <!-- Carrusel principal -->
        <div id="carouselBienvenida" class="carousel slide" data-bs-ride="carousel" style="max-height: 400px; overflow: hidden;">
            <div class="carousel-inner">
                <div class="carousel-item active">
                    <img src="${contexto}/IMG/carrusel1.jpg" class="d-block w-100" style="object-fit: cover; height: 400px;">
                    <div class="carousel-caption d-none d-md-block bg-dark bg-opacity-50 rounded">
                        <h2 class="text-white">Bienvenido a Pet Clinic</h2>
                        <p>Cuidamos de tu mascota como si fuera nuestra</p>
                    </div>
                </div>
                <div class="carousel-item">
                    <img src="${contexto}/IMG/carrusel2.jpg" class="d-block w-100" style="object-fit: cover; height: 400px;">
                    <div class="carousel-caption d-none d-md-block bg-dark bg-opacity-50 rounded">
                        <h2 class="text-white">Atención profesional y cercana</h2>
                        <p>Veterinarios con experiencia al servicio de tu mejor amigo</p>
                    </div>
                </div>
                <div class="carousel-item">
                    <img src="${contexto}/IMG/carrusel3.jpg" class="d-block w-100" style="object-fit: cover; height: 400px;">
                    <div class="carousel-caption d-none d-md-block bg-dark bg-opacity-50 rounded">
                        <h2 class="text-white">Servicios completos</h2>
                        <p>Vacunación, análisis, desparasitación y más</p>
                    </div>
                </div>
            </div>
            <button class="carousel-control-prev" type="button" data-bs-target="#carouselBienvenida" data-bs-slide="prev">
                <span class="carousel-control-prev-icon"></span>
            </button>
            <button class="carousel-control-next" type="button" data-bs-target="#carouselBienvenida" data-bs-slide="next">
                <span class="carousel-control-next-icon"></span>
            </button>
        </div>


        <!-- Sección de Servicios -->
        <section class="container my-5">
            <h2 class="text-center mb-4">Nuestros Servicios</h2>
            <div class="row text-center">
                <div class="col-md-4 mb-4">
                    <i class="fas fa-scissors fa-3x mb-3 text-primary"></i>
                    <h5>Peluquería</h5>
                    <p>Sesión completa de peluquería animal que incluye baño, cepillado, corte de pelo, corte de uñas, limpieza de oídos y perfume suave. Atención personalizada en un entorno tranquilo.</p>
                </div>
                <div class="col-md-4 mb-4">
                    <i class="fas fa-syringe fa-3x mb-3 text-success"></i>
                    <h5>Vacunación</h5>
                    <p>Servicio de vacunación para animales con control veterinario, aplicación de vacunas obligatorias y asesoramiento sobre el calendario vacunal. Ambiente seguro y trato profesional.</p>
                </div>
                <div class="col-md-4 mb-4">
                    <i class="fas fa-microscope fa-3x mb-3 text-warning"></i>
                    <h5>Análisis</h5>
                    <p>Servicio de análisis clínicos para animales: extracción de muestras, diagnóstico preventivo y entrega de resultados. Supervisado por veterinario.</p>
                </div>
                <div class="col-md-6 mb-4">
                    <i class="fas fa-id-badge fa-3x mb-3 text-info"></i>
                    <h5>Implantación de microchip</h5>
                    <p>Implantación de microchip para identificación permanente del animal. Incluye registro oficial y control veterinario. Procedimiento rápido y seguro.</p>
                </div>
                <div class="col-md-6 mb-4">
                    <i class="fas fa-bug fa-3x mb-3 text-danger"></i>
                    <h5>Desparasitación</h5>
                    <p>Tratamiento antiparasitario completo para prevenir y eliminar parásitos internos y externos. Incluye revisión veterinaria, aplicación del producto y asesoramiento personalizado.</p>
                </div>
            </div>
        </section>

        <!-- Sobre Nosotros -->
        <section class="container my-5">
            <div class="row align-items-center">
                <div class="col-md-6">
                    <img src="${contexto}/IMG/clinica.jpg" class="img-fluid rounded shadow" alt="Nuestra clínica">
                </div>
                <div class="col-md-6">
                    <h3>Conócenos</h3>
                    <p>Pet Clinic nació con el amor por los animales en el corazón. Nuestro equipo de veterinarios y auxiliares está comprometido con brindar el mejor cuidado a tu mascota, con un trato humano, cercano y profesional.</p>
                    <p>Visítanos y descubre por qué nuestras mascotas y sus dueños nos recomiendan.</p>
                </div>
            </div>
        </section>


        <!-- Testimonios -->
        <section class="bg-light py-5">
            <div class="container">
                <h2 class="text-center mb-5">Lo que dicen nuestros clientes</h2>
                <div class="row justify-content-center">
                    <div class="col-md-5 mb-4">
                        <div class="card h-100 shadow-sm border-0">
                            <div class="card-body text-center">
                                <i class="fas fa-quote-left fa-2x text-secondary mb-3"></i>
                                <p class="card-text">"Trato espectacular, mi perro ya no tiene miedo de ir al veterinario."</p>
                                <h6 class="card-subtitle mt-3 text-muted">— Carlos G.</h6>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-5 mb-4">
                        <div class="card h-100 shadow-sm border-0">
                            <div class="card-body text-center">
                                <i class="fas fa-quote-left fa-2x text-secondary mb-3"></i>
                                <p class="card-text">"Llevo a mis tres gatos y siempre salimos contentos. ¡Gracias Pet Clinic!"</p>
                                <h6 class="card-subtitle mt-3 text-muted">— María J.</h6>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>


        <!-- Mapa de ubicación -->
        <section class="container my-5">
            <h2 class="text-center mb-4">¿Dónde estamos?</h2>
            <div class="ratio ratio-16x9">
                <iframe
                    src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d12418.9982981906!2d-6.6258941394890964!3d38.9068416475489!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0xd1690e6cd11df0d%3A0x70172a48866edaee!2s06480%20Montijo%2C%20Badajoz!5e0!3m2!1ses!2ses!4v1743258306162!5m2!1ses!2ses"
                    style="border:0;" allowfullscreen="" loading="lazy">
                </iframe>
            </div>
        </section>


        <!-- Modal de Login -->
        <div class="modal fade" id="modalLogin" tabindex="-1" role="dialog" aria-labelledby="modalLoginLabel" aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="modalLoginLabel">Iniciar Sesión</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Cerrar"></button>
                    </div>
                    <div class="modal-body">
                        <c:if test="${not empty error}">
                            <p class="text-danger text-center"><c:out value="${error}" /></p>
                        </c:if>
                        <form action="${contexto}/FrontController" method="POST">
                            <div class="mb-3">
                                <label for="email">Correo</label>
                                <input type="text" class="form-control" id="emailLogin" name="email">
                            </div>
                            <div class="mb-3">
                                <label for="password">Contraseña</label>
                                <input type="password" class="form-control" id="passwordLogin" name="password">
                            </div>
                            <input type="hidden" name="boton" value="login">
                            <button type="submit" class="btn btn-primary w-100">Iniciar sesión</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>


        <!-- Modal de Registro -->
        <div class="modal fade" id="modalRegistro" tabindex="-1" aria-labelledby="modalRegistroLabel" aria-hidden="true">
            <div class="modal-dialog modal-lg">
                <div class="modal-content">
                    <form action="${contexto}/RegistroController" method="POST" enctype="multipart/form-data">
                        <div class="modal-header">
                            <h5 class="modal-title" id="modalRegistroLabel">Registro de Cliente</h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Cerrar"></button>
                        </div>

                        <div class="modal-body">
                            <div class="row g-3">
                                <div class="col-md-6">
                                    <label for="nombre" class="form-label">* Nombre</label>
                                    <input type="text" class="form-control" id="nombreRegistro" name="nombre">
                                </div>

                                <div class="col-md-6">
                                    <label for="apellidos" class="form-label">* Apellidos</label>
                                    <input type="text" class="form-control" id="apellidos" name="apellidos">
                                </div>
                            </div>

                            <div class="row g-3 mt-2">
                                <div class="col-md-6">
                                    <label for="genero" class="form-label">* Género</label>
                                    <select class="form-select" id="genero" name="genero">
                                        <option value="MUJER">Mujer</option>
                                        <option value="HOMBRE">Hombre</option>
                                        <option value="OTRO">Otro</option>
                                    </select>
                                </div>

                                <div class="col-md-6">
                                    <label for="fechaNacimiento" class="form-label">Fecha de nacimiento</label>
                                    <input type="date" class="form-control" id="fechaNacimiento" name="fechaNacimiento">
                                </div>
                            </div>

                            <div class="row g-3 mt-2">
                                <div class="col-md-6">
                                    <label for="tipoIdentificacion" class="form-label">* Tipo de Documento</label>
                                    <select class="form-select" id="tipoIdentificacion" name="tipoIdentificacion">
                                        <option value="DNI" selected>DNI</option>
                                        <option value="NIE">NIE</option>
                                    </select>
                                </div>

                                <div class="col-md-6">
                                    <label for="identificacionInput" class="form-label">* Número de identificación</label>
                                    <input type="text" class="form-control" id="identificacionInput" name="numIdentificacion" placeholder="Introduce tu DNI o NIE">
                                    <p id="mensajeIdentificacion" class="text-danger" style="display: none"></p>
                                </div>
                            </div>


                            <div class="row g-3 mt-2">
                                <div class="col-md-12">
                                    <label for="email" class="form-label">* Correo electrónico</label>
                                    <input type="email" class="form-control" id="email" name="email">
                                    <small id="emailError" class="text-danger"></small>
                                </div>
                            </div>

                            <div class="row g-3 mt-2">
                                <div class="col-md-6">
                                    <label for="password" class="form-label">* Contraseña</label>
                                    <input type="password" class="form-control" id="passwordRegistro" name="password">
                                </div>

                                <div class="col-md-6">
                                    <label for="confirmPassword" class="form-label">* Confirmar Contraseña</label>
                                    <input type="password" class="form-control" id="confirmPassword" name="confirmPassword">
                                </div>
                            </div>

                            <div class="row g-3 mt-2">
                                <div class="col-md-12">
                                    <label for="avatar">Avatar (Se permiten .png, .jpg, .jpeg y menos de 100 KB)</label>
                                    
                                    <div class="d-flex gap-3 align-items-start mt-2">
                                        <input type="file" id="avatar" name="avatar" accept="image/*">
                                    </div>
                                    
                                    <div class="mt-3 border p-2 rounded bg-light text-center" style="max-width: 150px;">
                                        <img class="img-fluid rounded-circle" id="vistaPreviaAvatar" style="max-width: 150px; max-height: 150px;">
                                        <button type="button" id="btnQuitarAvatar" class="btn btn-sm btn-outline-danger mt-2" style="display:none;">Quitar foto</button>
                                    </div>

                                    <p id="avatarError" style="color: red"></p>
                                </div>
                            </div>
                        </div>

                        <div class="modal-footer">
                            <button type="submit" id="btnRegistrar" class="btn btn-success">Registrarse</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>


        <%@ include file="/INC/pie.jsp" %>

        <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>

    </body>
</html>
