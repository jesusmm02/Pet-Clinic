<jsp:directive.page contentType="text/html" pageEncoding="UTF-8"/>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="es">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <jsp:include page="/INC/metas.inc"/>
        <title>Infografía - Pet Clinic</title>
        <link rel="stylesheet" href="${bootstrap}">
        <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
        <script src="${contexto}/JS/grafico.js" defer></script>
        <style>

            /* Estilo general de la página */
            body {
                font-family: Arial, sans-serif;
                margin: 0;
                padding: 0;
                background-color: #f4f4f4;
            }

            header {
                background-color: #28a745;
                color: white;
                text-align: center;
                padding: 20px 0;
            }

            h1 {
                font-size: 2.5rem;
                margin: 0;
            }

            /* Contenedor de la infografía */
            .infografia-container {
                padding: 30px;
                text-align: center;
            }

            .infografia-header h2 {
                font-size: 2rem;
                color: #333;
                margin-bottom: 10px;
            }

            .infografia-header p {
                font-size: 1rem;
                color: #555;
                margin-bottom: 40px;
            }

            /* Cajas de infografía con efectos */
            .infografia-body {
                display: grid;
                grid-template-columns: repeat(3, 1fr);
                gap: 20px;
                justify-items: center;
                margin-top: 30px;
            }

            .infografia-box {
                background-color: #fff;
                border-radius: 10px;
                padding: 20px;
                width: 280px;
                box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
                text-align: center;
                transition: transform 0.3s ease, box-shadow 0.3s ease; /* Transición suave para el hover */
            }

            .infografia-box:hover {
                transform: translateY(-10px); /* Mueve la carta hacia arriba al pasar el ratón */
                box-shadow: 0 8px 16px rgba(0, 0, 0, 0.2); /* Aumenta la sombra */
            }

            .infografia-img {
                max-width: 100%;
                height: auto;
                border-radius: 10px;
                margin-bottom: 20px;
            }

            /* Efecto de zoom en las imágenes */
            .infografia-img:hover {
                transform: scale(1.1); /* Efecto de zoom */
                box-shadow: 0 8px 20px rgba(0, 0, 0, 0.15); /* Aumenta la sombra */
            }

            h3 {
                font-size: 1.5rem;
                color: #333;
                margin-bottom: 10px;
                transition: transform 0.3s ease, color 0.3s ease, text-shadow 0.3s ease; /* Transición suave para efectos */
            }

            /* Efecto al pasar el ratón sobre los títulos */
            h3:hover {
                transform: scale(1.1); /* Aumenta el tamaño del título */
                color: #28a745; /* Cambia el color del texto */
                text-shadow: 0px 4px 6px rgba(0, 0, 0, 0.1); /* Añade una sombra suave al texto */
            }

            h4 {
                font-size: 1.5rem;
                color: #333;
                margin-bottom: 15px;
                transition: transform 0.3s ease, color 0.3s ease, text-shadow 0.3s ease; /* Transición suave para efectos */
            }

            /* Efecto al pasar el ratón sobre los subtítulos */
            h4:hover {
                transform: scale(1.05); /* Aumenta ligeramente el tamaño del subtítulo */
                color: #ff6384; /* Cambia el color del subtítulo */
                text-shadow: 0px 4px 6px rgba(0, 0, 0, 0.1); /* Añade una sombra suave al texto */
            }

            p {
                font-size: 1rem;
                color: #555;
            }

            ul {
                text-align: left;
                list-style-type: disc;
                padding-left: 20px;
            }

            /* Estilo para el contenedor de estadísticas */
            .statistic-container {
                margin-top: 50px;
                display: flex;
                flex-direction: column;
                align-items: center;
            }

            .statistic-container h3 {
                text-align: center;
                font-size: 2rem;
                margin-bottom: 20px;
            }

            .statistic-container .statistic-box {
                width: 48%;
                margin-bottom: 40px;
            }

            /* Hacer el gráfico circular más pequeño */
            .statistic-container .statistic-box:nth-child(1) canvas {
                height: 150px;
                width: 100% !important;
            }

            .statistic-container .statistic-box canvas {
                max-width: 100%;
                background-color: #fff;
                border-radius: 10px;
            }

            .statistic-container .statistic-box h4 {
                font-size: 1.5rem;
                color: #333;
                margin-bottom: 15px;
            }

            .statistic-container {
                display: flex;
                justify-content: space-between;
                gap: 20px;
            }

            canvas {
                max-width: 100%;
                height: 200px;
                background-color: #fff;
                border-radius: 10px;
            }

            h4 {
                font-size: 1.5rem;
                color: #333;
                margin-bottom: 15px;
            }

            /* Responsividad para dispositivos pequeños */
            @media screen and (max-width: 768px) {
                .infografia-body {
                    grid-template-columns: 1fr 1fr;
                }
            }

            @media screen and (max-width: 480px) {
                .infografia-body {
                    grid-template-columns: 1fr;
                }
            }

            /* Para pantallas más pequeñas */
            @media screen and (max-width: 768px) {
                .statistic-container {
                    flex-direction: column;
                }

                .statistic-box {
                    width: 100%;
                }
            }
        </style>
    </head>
    <body>

        <%@ include file="/INC/cabecera.jsp" %>
        
        <%@ include file="/INC/barraCliente.jsp" %>

        <header>
            <h1>Infografía: Cuidados Esenciales para tu Mascota</h1>
        </header>

        <section class="infografia-container">
            <div class="infografia-header">
                <h2>Consejos para una Vida Saludable de tu Mascota</h2>
                <p>En esta infografía te ofrecemos información valiosa sobre el cuidado adecuado para tu perro o gato.</p>
            </div>

            <!-- Consejos de cuidado para mascotas -->
            <div class="infografia-body">
                <div class="infografia-box">
                    <img src="${contexto}/IMG/alimentacion.jpg" alt="Alimentación" class="infografia-img">
                    <h3>1. Alimentación Balanceada</h3>
                    <p>Proporciona a tu mascota una dieta adecuada según su edad, peso y especie. ¡No les des alimentos para humanos!</p>
                </div>
                <div class="infografia-box">
                    <img src="${contexto}/IMG/ejercicio.jpeg" alt="Ejercicio" class="infografia-img">
                    <h3>2. Ejercicio Regular</h3>
                    <p>Los perros y gatos necesitan ejercicio diario para mantenerse en forma. Paseos y juegos son esenciales para su bienestar.</p>
                </div>
                <div class="infografia-box">
                    <img src="${contexto}/IMG/higiene.jpg" alt="Higiene" class="infografia-img">
                    <h3>3. Higiene Adecuada</h3>
                    <p>Baños regulares, corte de uñas y cuidado dental son fundamentales para la salud de tu mascota.</p>
                </div>
                <div class="infografia-box">
                    <img src="${contexto}/IMG/visitas.png" alt="Visitas Veterinarias" class="infografia-img">
                    <h3>4. Visitas al Veterinario</h3>
                    <p>Visita regularmente al veterinario para chequeos y vacunaciones. ¡La prevención es clave para una vida larga y sana!</p>
                </div>
                <div class="infografia-box">
                    <img src="${contexto}/IMG/comodidad.jpg" alt="Comodidad" class="infografia-img">
                    <h3>5. Proporciona Comodidad</h3>
                    <p>Asegúrate de que tu mascota tenga un lugar cómodo para descansar. Su cama debe ser adecuada a su tamaño.</p>
                </div>
                <div class="infografia-box">
                    <img src="${contexto}/IMG/parasitos.jpg" alt="Prevención de Parásitos" class="infografia-img">
                    <h3>6. Prevención de Parásitos</h3>
                    <p>Protege a tu mascota contra parásitos como pulgas, garrapatas y lombrices con tratamientos preventivos.</p>
                </div>
            </div>

            <!-- Estadísticas globales de mascotas y clínicas veterinarias -->
            <div class="statistic-container">
                <h3>Estadísticas Globales sobre Mascotas y Clínicas Veterinarias</h3>
                <div class="statistic-box">
                    <h4>% de Mascotas en los Hogares</h4>
                    <canvas id="petStatsChart"></canvas>
                </div>
            </div>

            <!-- Nueva sección de Enfermedades Comunes en Algunos Animales -->
            <div class="infografia-header">
                <h2>Enfermedades Comunes en Diferentes Tipos de Mascotas</h2>
                <p>A continuación, te proporcionamos información sobre las enfermedades más comunes en perros, gatos y aves.</p>
            </div>

            <div class="infografia-body">
                <div class="infografia-box">
                    <img src="${contexto}/IMG/enfermedad-perro.jpg" alt="Enfermedades en Perros" class="infografia-img">
                    <h3>Enfermedades Comunes en Perros</h3>
                    <ul>
                        <li>Parvovirus</li>
                        <li>Leishmaniasis</li>
                        <li>Displasia de Cadera</li>
                        <li>Obesidad</li>
                    </ul>
                    <p>Las vacunas y visitas regulares al veterinario pueden prevenir muchas de estas enfermedades.</p>
                </div>
                <div class="infografia-box">
                    <img src="${contexto}/IMG/enfermedad-gato.jpg" alt="Enfermedades en Gatos" class="infografia-img">
                    <h3>Enfermedades Comunes en Gatos</h3>
                    <ul>
                        <li>Enfermedad Renal Crónica</li>
                        <li>Leucemia Felina</li>
                        <li>Asma Felina</li>
                        <li>Infección del Tracto Urinario</li>
                    </ul>
                    <p>Es importante realizar chequeos regulares y mantener una dieta adecuada para prevenir estas condiciones.</p>
                </div>
                <div class="infografia-box">
                    <img src="${contexto}/IMG/enfermedad-ave.jpg" alt="Enfermedades en Aves" class="infografia-img">
                    <h3>Enfermedades Comunes en Aves</h3>
                    <ul>
                        <li>Psitacosis</li>
                        <li>Gripe Aviar</li>
                        <li>Obesidad</li>
                        <li>Candida</li>
                    </ul>
                    <p>El ambiente adecuado y una dieta balanceada son esenciales para la salud de las aves.</p>
                </div>
            </div>

            <!-- Nueva gráfica de enfermedades comunes en mascotas -->
            <div class="statistic-container">
                <h3>Enfermedades Comunes en Mascotas</h3>
                <div class="statistic-box">
                    <h4>Prevalencia de Enfermedades en Mascotas (%)</h4>
                    <canvas id="diseasePrevalenceChart"></canvas>
                </div>
            </div>

        </section>

        <%@ include file="/INC/pie.jsp" %>

    </body>
</html>
