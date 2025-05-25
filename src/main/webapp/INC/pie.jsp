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
                    <h6 class="text-uppercase mb-4 font-weight-bold">Compa��a</h6>
                    <p><a href="#" class="text-primary-emphasis" data-bs-toggle="modal" data-bs-target="#modalAccesibilidad">Declaraci�n de Accesibilidad</a></p>
                    <p><a href="#" class="text-primary-emphasis" data-bs-toggle="modal" data-bs-target="#modalCookies">Pol�tica de cookies (UE)</a></p>
                    <p><a href="#" class="text-primary-emphasis" data-bs-toggle="modal" data-bs-target="#modalPrivacidad">Declaraci�n de privacidad (UE)</a></p>
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
                <h5 class="modal-title">Declaraci�n de Accesibilidad</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
            </div>
            <div class="modal-body">
                <p>Pet Clinic se compromete firmemente a garantizar que todos sus servicios digitales sean accesibles para personas con diferentes capacidades, incluyendo aquellas con discapacidades visuales, auditivas, motrices o cognitivas.
                    Implementamos pr�cticas y tecnolog�as conforme a las directrices internacionales de accesibilidad WCAG 2.1 para ofrecer una experiencia inclusiva, f�cil de navegar y sin barreras.
                    Nos esforzamos continuamente en mejorar nuestros est�ndares y revisamos peri�dicamente nuestros contenidos para asegurar el cumplimiento con la legislaci�n vigente y fomentar la igualdad de acceso para todos nuestros usuarios.
                </p>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modalCookies" tabindex="-1">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">Pol�tica de Cookies (UE)</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
            </div>
            <div class="modal-body">
                <p>
                    En Pet Clinic utilizamos cookies para mejorar tu experiencia de navegaci�n, analizar el tr�fico y comportamiento de los usuarios en nuestro sitio web, as� como para personalizar contenido y publicidad acorde a tus intereses.
                    Las cookies nos permiten ofrecer funciones esenciales, garantizar la seguridad y optimizar la usabilidad de la plataforma.
                    Cumplimos estrictamente con la legislaci�n europea en materia de privacidad y cookies (GDPR), y te informamos claramente sobre los tipos de cookies utilizadas, su finalidad, y c�mo puedes gestionarlas o desactivarlas en cualquier momento a trav�s de las opciones de configuraci�n de tu navegador.
                </p>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modalPrivacidad" tabindex="-1">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">Declaraci�n de Privacidad (UE)</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
            </div>
            <div class="modal-body">
                <p>
                    La privacidad y protecci�n de tus datos personales es una prioridad en Pet Clinic.
                    Todos los datos recopilados a trav�s de nuestra web se tratan con la m�xima confidencialidad, solo con fines espec�ficos y leg�timos relacionados con la prestaci�n de nuestros servicios veterinarios y la mejora continua de la atenci�n al cliente.
                    Cumplimos con el Reglamento General de Protecci�n de Datos (RGPD) de la Uni�n Europea, garantizando tus derechos de acceso, rectificaci�n, supresi�n, portabilidad y limitaci�n del tratamiento.
                    Puedes consultar m�s detalles sobre c�mo gestionamos y protegemos tus datos, as� como tus derechos, en esta declaraci�n.
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
                    Pet Clinic opera en estricto cumplimiento con la legislaci�n vigente aplicable a los servicios prestados online y a la protecci�n de los usuarios.
                    En este apartado encontrar�s informaci�n legal relevante sobre la identidad de nuestra empresa, datos de registro, condiciones generales de uso del sitio web, responsabilidades y limitaciones legales.
                    Nuestro objetivo es ofrecer transparencia total sobre los t�rminos bajo los cuales proporcionamos nuestros servicios veterinarios digitales, garantizando as� una relaci�n clara y segura con nuestros usuarios.
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
                    La informaci�n publicada en Pet Clinic tiene car�cter meramente informativo y no sustituye ni reemplaza la consulta directa con profesionales veterinarios cualificados.
                    Aunque nos esforzamos por mantener los contenidos actualizados y precisos, no asumimos responsabilidad alguna por posibles errores, omisiones o interpretaciones incorrectas.
                    Recomendamos siempre consultar a un veterinario certificado para obtener un diagn�stico personalizado, tratamiento adecuado y atenci�n especializada para tu mascota.
                    Este sitio web no es responsable de cualquier da�o derivado del uso o mala interpretaci�n de la informaci�n aqu� presentada.
                </p>
            </div>
        </div>
    </div>
</div>

<!-- Scripts necesarios -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>