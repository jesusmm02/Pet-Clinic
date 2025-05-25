<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<footer class="text-center text-lg-start text-white mt-5" style="background-color: #929fba">
    <div class="container p-4 pb-0">
        <section>
            <div class="row">
                <div class="col-md-3 col-lg-3 col-xl-3 mx-auto mt-3">
                    <img src="${pageContext.request.contextPath}/IMG/logoPetClinic.png" alt="Logo" height="140">
                    <h6 class="text-uppercase mb-4 font-weight-bold">PET CLINIC</h6>
                    <p>Centro veterinario integral para el cuidado de tus mascotas.</p>
                </div>

                <div class="col-md-2 col-lg-2 col-xl-2 mx-auto mt-3">
                    <h6 class="text-uppercase mb-4 font-weight-bold">Compañía</h6>
                    <p><a href="#" class="text-primary-emphasis" data-bs-toggle="modal" data-bs-target="#modalAccesibilidad">Declaración de Accesibilidad</a></p>
                    <p><a href="#" class="text-primary-emphasis" data-bs-toggle="modal" data-bs-target="#modalCookies">Política de cookies (UE)</a></p>
                    <p><a href="#" class="text-primary-emphasis" data-bs-toggle="modal" data-bs-target="#modalPrivacidad">Declaración de privacidad (UE)</a></p>
                    <p><a href="#" class="text-primary-emphasis" data-bs-toggle="modal" data-bs-target="#modalAvisoLegal">Aviso Legal / Imprint</a></p>
                    <p><a href="#" class="text-primary-emphasis" data-bs-toggle="modal" data-bs-target="#modalDescargo">Descargo de responsabilidad</a></p>
                </div>

                <div class="col-md-4 col-lg-3 col-xl-3 mx-auto mt-3">
                    <h6 class="text-uppercase mb-4 font-weight-bold">Contacto</h6>
                    <p class="text-primary-emphasis">Montijo, Extremadura, Espa&ntilde;a</p>
                    <p class="text-primary-emphasis">infopetclinic@gmail.com</p>
                    <p class="text-primary-emphasis">+34 924 56 78 90</p>
                </div>

                <div class="col-md-3 col-lg-2 col-xl-2 mx-auto mt-3">
                    <h6 class="text-uppercase mb-4 font-weight-bold">S&iacute;guenos</h6>
                    <a class="btn btn-primary m-1" style="background-color: #3b5998"><i class="fab fa-facebook-f"></i></a>
                    <a class="btn btn-primary m-1" style="background-color: #55acee"><i class="fab fa-twitter"></i></a>
                    <a class="btn btn-primary m-1" style="background-color: #ac2bac"><i class="fab fa-instagram"></i></a>
                </div>
            </div>
        </section>
    </div>

    <div class="text-center p-3" style="background-color: rgba(0, 0, 0, 0.2)">
        &copy; 2025 Pet Clinic
    </div>
</footer>
                    
<!-- Modales -->
<div class="modal fade" id="modalAccesibilidad" tabindex="-1">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">Declaración de Accesibilidad</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
            </div>
            <div class="modal-body">
                <p>Pet Clinic se compromete firmemente a garantizar que todos sus servicios digitales sean accesibles para personas con diferentes capacidades, incluyendo aquellas con discapacidades visuales, auditivas, motrices o cognitivas.
                    Implementamos prácticas y tecnologías conforme a las directrices internacionales de accesibilidad WCAG 2.1 para ofrecer una experiencia inclusiva, fácil de navegar y sin barreras.
                    Nos esforzamos continuamente en mejorar nuestros estándares y revisamos periódicamente nuestros contenidos para asegurar el cumplimiento con la legislación vigente y fomentar la igualdad de acceso para todos nuestros usuarios.
                </p>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modalCookies" tabindex="-1">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">Política de Cookies (UE)</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
            </div>
            <div class="modal-body">
                <p>
                    En Pet Clinic utilizamos cookies para mejorar tu experiencia de navegación, analizar el tráfico y comportamiento de los usuarios en nuestro sitio web, así como para personalizar contenido y publicidad acorde a tus intereses.
                    Las cookies nos permiten ofrecer funciones esenciales, garantizar la seguridad y optimizar la usabilidad de la plataforma.
                    Cumplimos estrictamente con la legislación europea en materia de privacidad y cookies (GDPR), y te informamos claramente sobre los tipos de cookies utilizadas, su finalidad, y cómo puedes gestionarlas o desactivarlas en cualquier momento a través de las opciones de configuración de tu navegador.
                </p>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modalPrivacidad" tabindex="-1">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">Declaración de Privacidad (UE)</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
            </div>
            <div class="modal-body">
                <p>
                    La privacidad y protección de tus datos personales es una prioridad en Pet Clinic.
                    Todos los datos recopilados a través de nuestra web se tratan con la máxima confidencialidad, solo con fines específicos y legítimos relacionados con la prestación de nuestros servicios veterinarios y la mejora continua de la atención al cliente.
                    Cumplimos con el Reglamento General de Protección de Datos (RGPD) de la Unión Europea, garantizando tus derechos de acceso, rectificación, supresión, portabilidad y limitación del tratamiento.
                    Puedes consultar más detalles sobre cómo gestionamos y protegemos tus datos, así como tus derechos, en esta declaración.
                </p>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modalAvisoLegal" tabindex="-1">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">Aviso Legal / Imprint</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
            </div>
            <div class="modal-body">
                <p>
                    Pet Clinic opera en estricto cumplimiento con la legislación vigente aplicable a los servicios prestados online y a la protección de los usuarios.
                    En este apartado encontrarás información legal relevante sobre la identidad de nuestra empresa, datos de registro, condiciones generales de uso del sitio web, responsabilidades y limitaciones legales.
                    Nuestro objetivo es ofrecer transparencia total sobre los términos bajo los cuales proporcionamos nuestros servicios veterinarios digitales, garantizando así una relación clara y segura con nuestros usuarios.
                </p>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modalDescargo" tabindex="-1">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">Descargo de responsabilidad</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
            </div>
            <div class="modal-body">
                <p>
                    La información publicada en Pet Clinic tiene carácter meramente informativo y no sustituye ni reemplaza la consulta directa con profesionales veterinarios cualificados.
                    Aunque nos esforzamos por mantener los contenidos actualizados y precisos, no asumimos responsabilidad alguna por posibles errores, omisiones o interpretaciones incorrectas.
                    Recomendamos siempre consultar a un veterinario certificado para obtener un diagnóstico personalizado, tratamiento adecuado y atención especializada para tu mascota.
                    Este sitio web no es responsable de cualquier daño derivado del uso o mala interpretación de la información aquí presentada.
                </p>
            </div>
        </div>
    </div>
</div>

<!-- Scripts necesarios -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>