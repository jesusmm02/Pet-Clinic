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
                    <p><a href="#" class="text-primary" data-bs-toggle="modal" data-bs-target="#modalAccesibilidad">Declaración de Accesibilidad</a></p>
                    <p><a href="#" class="text-primary" data-bs-toggle="modal" data-bs-target="#modalCookies">Política de cookies (UE)</a></p>
                    <p><a href="#" class="text-primary" data-bs-toggle="modal" data-bs-target="#modalPrivacidad">Declaración de privacidad (UE)</a></p>
                    <p><a href="#" class="text-primary" data-bs-toggle="modal" data-bs-target="#modalAvisoLegal">Aviso Legal / Imprint</a></p>
                    <p><a href="#" class="text-primary" data-bs-toggle="modal" data-bs-target="#modalDescargo">Descargo de responsabilidad</a></p>
                </div>

                <div class="col-md-4 col-lg-3 col-xl-3 mx-auto mt-3">
                    <h6 class="text-uppercase mb-4 font-weight-bold">Contacto</h6>
                    <p>Montijo, Extremadura, Espa&ntilde;a</p>
                    <p>infopetclinic@gmail.com</p>
                    <p>+34 924 56 78 90</p>
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
                <p>Pet Clinic se compromete a garantizar la accesibilidad digital para personas con discapacidades. Continuamente estamos mejorando la experiencia del usuario para todos y aplicando los estándares de accesibilidad pertinentes.</p>
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
                <p>En Pet Clinic utilizamos cookies para mejorar tu experiencia de navegación, analizar el tráfico del sitio y ofrecer funciones personalizadas.</p>
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
                <p>Pet Clinic está comprometido con la protección de la privacidad de tus datos personales. Toda la información que recopilamos es tratada con la mayor confidencialidad y según las normativas vigentes.</p>
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
                <p>Pet Clinic cumple con todas las regulaciones legales aplicables y proporciona esta información legal para que nuestros usuarios estén informados.</p>
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
                <p>La información proporcionada en este sitio web es solo para fines informativos y no reemplaza el consejo de un veterinario. Consulta a un profesional para una atención personalizada.</p>
            </div>
        </div>
    </div>
</div>

<!-- Scripts necesarios -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>