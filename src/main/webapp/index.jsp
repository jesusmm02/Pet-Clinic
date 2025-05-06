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
        <link rel="stylesheet" href="${bootstrap}">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
        <script src="${contexto}/JS/mensajeFlotante.js" defer></script>
        <script src="${contexto}/JS/comprobarEmail.js" defer></script>
        <script src="${contexto}/JS/comprobarDNI.js" defer></script>
        <script src="${contexto}/JS/validarAvatar.js" defer></script>
        <script src="${contexto}/JS/vistaAvatar.js" defer></script>
        <style>
            .mensaje-flotanteError {
                position: fixed;
                top: 80px;
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
                top: 80px;
                right: 20px;
                background-color: #d4edda;
                color: #155724;
                padding: 10px 20px;
                border: 1px solid #c3e6cb;
                border-radius: 5px;
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
                        <p>Vacunación, cirugía, desparasitación y más</p>
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
                    <i class="fas fa-stethoscope fa-3x mb-3 text-primary"></i>
                    <h5>Consultas Generales</h5>
                    <p>Evaluaciones médicas completas para perros y gatos.</p>
                </div>
                <div class="col-md-4 mb-4">
                    <i class="fas fa-syringe fa-3x mb-3 text-success"></i>
                    <h5>Vacunación</h5>
                    <p>Protége a tu mascota con nuestras vacunas al día.</p>
                </div>
                <div class="col-md-4 mb-4">
                    <i class="fas fa-dog fa-3x mb-3 text-warning"></i>
                    <h5>Peluquería Canina</h5>
                    <p>¡Baño, corte y mucho amor para tu mascota!</p>
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

        <!-- Galería de mascotas -->
        <section class="container my-5">
            <h2 class="text-center mb-4">¡Nuestros pacientes más felices!</h2>
            <div class="row row-cols-1 row-cols-md-3 g-4">
                <div class="col">
                    <div class="card h-100">
                        <img src="${contexto}/IMG/perro1.jpg" class="card-img-top" style="height: 250px; object-fit: cover;">
                        <div class="card-body d-flex flex-column">
                            <h5 class="card-title">Max</h5>
                            <p class="card-text">Max vino con fiebre y se fue con una gran sonrisa peluda 🐶.</p>
                        </div>
                    </div>
                </div>
                <div class="col">
                    <div class="card h-100">
                        <img src="${contexto}/IMG/gato1.jpg" class="card-img-top" style="height: 250px; object-fit: cover;">
                        <div class="card-body d-flex flex-column">
                            <h5 class="card-title">Luna</h5>
                            <p class="card-text">Nuestra estrella felina recibió su vacuna y un premio 🍖.</p>
                        </div>
                    </div>
                </div>
                <div class="col">
                    <div class="card h-100">
                        <img src="${contexto}/IMG/conejo1.jpg" class="card-img-top" style="height: 250px; object-fit: cover;">
                        <div class="card-body d-flex flex-column">
                            <h5 class="card-title">Bunny</h5>
                            <p class="card-text">¡Una revisión rápida y a seguir brincando feliz! 🐰</p>
                        </div>
                    </div>
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
            <div class="modal-dialog">
                <div class="modal-content">
                    <form action="${contexto}/RegistroController" method="POST" enctype="multipart/form-data">
                        <div class="modal-header">
                            <h5 class="modal-title" id="modalRegistroLabel">Registro de Cliente</h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Cerrar"></button>
                        </div>

                        <div class="modal-body">
                            <div class="row">
                                <div class="col-md-6 mb-3">
                                    <label for="nombre" class="form-label">* Nombre</label>
                                    <input type="text" class="form-control" id="nombreRegistro" name="nombre">
                                </div>

                                <div class="col-md-6 mb-3">
                                    <label for="apellidos" class="form-label">* Apellidos</label>
                                    <input type="text" class="form-control" id="apellidos" name="apellidos">
                                </div>
                            </div>

                            <div class="row">
                                <div class="col-md-6">
                                    <label for="genero" class="form-label">* Género</label>
                                    <select class="form-select" id="genero" name="genero">
                                        <option value="MUJER" ${usuario.genero == 'MUJER' ? 'selected' : ''}>Mujer</option>
                                        <option value="HOMBRE" ${usuario.genero == 'HOMBRE' ? 'selected' : ''}>Hombre</option>
                                        <option value="OTRO" ${usuario.genero == 'OTRO' ? 'selected' : ''}>Otro</option>
                                    </select>
                                </div>

                                <div class="col-md-6">
                                    <label for="fechaNacimiento" class="form-label">Fecha de nacimiento</label>
                                    <input type="date" class="form-control" id="fechaNacimiento" name="fechaNacimiento">
                                </div>
                            </div>

                            <div class="row">
                                <div class="col-md-6 mb-3">
                                    <label for="dni" class="form-label">* DNI</label>
                                    <input type="text" class="form-control" id="dni" name="dni">
                                    <small id="mensajeDNI" class="text-danger"></small>
                                </div>

                                <div class="col-md-6 mb-3">
                                    <label for="email" class="form-label">* Correo electrónico</label>
                                    <input type="email" class="form-control" id="email" name="email">
                                    <small id="emailError" class="text-danger"></small>
                                </div>
                            </div>

                            <div class="row">
                                <div class="col-md-6 mb-3">
                                    <label for="password" class="form-label">* Contraseña</label>
                                    <input type="password" class="form-control" id="passwordRegistro" name="password">
                                </div>

                                <div class="col-md-6 mb-3">
                                    <label for="confirmPassword">* Confirmar Contraseña</label>
                                    <input type="password" class="form-control" id="confirmPassword" name="confirmPassword">
                                </div>
                            </div>

                            <div class="row">
                                <div class="col-md-6 form-group">
                                    <label for="avatar">Avatar (Se permiten .png, .jpg, .jpeg y menos de 100 KB)</label>
                                    <input type="file" id="avatar" name="avatar" accept="image/*">
                                    <img class="mt-2" id="vistaPreviaAvatar">
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
