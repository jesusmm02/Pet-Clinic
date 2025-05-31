-- Crear base de datos y usuario
CREATE DATABASE IF NOT EXISTS `petclinic` CHARACTER SET utf8mb4 COLLATE utf8mb4_spanish_ci;
USE `petclinic`;

CREATE USER IF NOT EXISTS 'petclinic'@'localhost' IDENTIFIED BY 'petclinic';
GRANT SELECT, INSERT, UPDATE, DELETE ON petclinic.* TO 'petclinic'@'localhost';

-- Eliminar tablas en orden inverso de dependencias
DROP TABLE IF EXISTS citas;
DROP TABLE IF EXISTS historialesMedicos;
DROP TABLE IF EXISTS mascotas;
DROP TABLE IF EXISTS clientes;
DROP TABLE IF EXISTS veterinarios;
DROP TABLE IF EXISTS calendarios;
DROP TABLE IF EXISTS servicios;
DROP TABLE IF EXISTS usuarios;

-- Crear tabla usuarios
CREATE TABLE `usuarios` (
  `IdUsuario` INT AUTO_INCREMENT PRIMARY KEY,
  `Nombre` VARCHAR(30) DEFAULT NULL,
  `Apellidos` VARCHAR(60) DEFAULT NULL,
  `NumIdentificacion` VARCHAR(9) DEFAULT NULL UNIQUE,
  `Email` VARCHAR(80) NOT NULL UNIQUE,
  `Password` VARCHAR(128) NOT NULL,
  `Rol` ENUM('VETERINARIO','CLIENTE') NOT NULL,
  `UltimoAcceso` DATE,
  `Avatar` VARCHAR(30) NOT NULL DEFAULT 'avatar.png'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

-- Crear tabla veterinarios
CREATE TABLE `veterinarios` (
  `IdUsuario` INT PRIMARY KEY,
  `ModoDios` BOOLEAN NOT NULL DEFAULT FALSE,
  CONSTRAINT `FK_veterinarios_usuarios` FOREIGN KEY (`IdUsuario`) REFERENCES `usuarios` (`IdUsuario`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

-- Crear tabla clientes
CREATE TABLE `clientes` (
  `IdUsuario` INT PRIMARY KEY,
  `Genero` ENUM('MUJER', 'HOMBRE', 'OTRO') NOT NULL DEFAULT 'MUJER',
  `FechaNacimiento` DATE DEFAULT NULL,
  CONSTRAINT `FK_clientes_usuarios` FOREIGN KEY (`IdUsuario`) REFERENCES `usuarios` (`IdUsuario`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

-- Crear tabla servicios
CREATE TABLE `servicios` (
  `IdServicio` INT AUTO_INCREMENT PRIMARY KEY,
  `Nombre` VARCHAR(50) NOT NULL,
  `Descripcion` VARCHAR(255),
  `Precio` DECIMAL(5,2) NOT NULL,
  `Duracion` INT NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

-- Crear tabla calendarios
CREATE TABLE `calendarios` (
  `IdCalendario` INT AUTO_INCREMENT PRIMARY KEY,
  `Fecha` DATE NOT NULL,
  `HoraInicio` TIME NOT NULL,
  `HoraFin` TIME NOT NULL,
  `Disponible` BOOLEAN NOT NULL DEFAULT TRUE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

-- Crear tabla mascotas
CREATE TABLE `mascotas` (
  `IdMascota` INT AUTO_INCREMENT PRIMARY KEY,
  `Nombre` VARCHAR(30) NOT NULL,
  `Especie` VARCHAR(40) NOT NULL,
  `Raza` VARCHAR(60) NOT NULL,
  `FechaNacimiento` DATE DEFAULT NULL,
  `Peso` DECIMAL(5,2) DEFAULT NULL,
  `Genero` ENUM('MACHO', 'HEMBRA') DEFAULT NULL,
  `Foto` VARCHAR(30) NOT NULL DEFAULT 'foto.png',
  `IdUsuario` INT NOT NULL,
  CONSTRAINT `FK_mascotas_usuarios` FOREIGN KEY (`IdUsuario`) REFERENCES `usuarios`(`IdUsuario`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

-- Crear tabla historialesMedicos
CREATE TABLE `historialesMedicos` (
  `IdHistorial` INT AUTO_INCREMENT PRIMARY KEY,
  `Fecha` DATETIME NOT NULL,
  `Descripcion` VARCHAR(255) DEFAULT NULL,
  `Tratamiento` VARCHAR(255) DEFAULT NULL,
  `IdMascota` INT NOT NULL,
  CONSTRAINT `FK_historialesMedicos_mascotas` FOREIGN KEY (`IdMascota`) REFERENCES `mascotas`(`IdMascota`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

-- Crear tabla citas
CREATE TABLE `citas` (
  `IdCita` INT AUTO_INCREMENT PRIMARY KEY,
  `IdMascota` INT NOT NULL,
  `IdCalendario` INT NOT NULL,
  `IdServicio` INT NOT NULL,
  `IdVeterinario` INT NOT NULL,
  CONSTRAINT `FK_citas_mascotas` FOREIGN KEY (`IdMascota`) REFERENCES `mascotas`(`IdMascota`) ON DELETE CASCADE,
  CONSTRAINT `FK_citas_calendarios` FOREIGN KEY (`IdCalendario`) REFERENCES `calendarios`(`IdCalendario`) ON DELETE CASCADE,
  CONSTRAINT `FK_citas_servicios` FOREIGN KEY (`IdServicio`) REFERENCES servicios(IdServicio) ON DELETE CASCADE,
  CONSTRAINT `FK_citas_veterinarios` FOREIGN KEY (`IdVeterinario`) REFERENCES `usuarios`(`IdUsuario`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;


-- Insercción de datos en tabla `usuarios`
LOCK TABLES `usuarios` WRITE;
INSERT INTO `usuarios` VALUES
(1,'Carmen','Vidal',NULL,'adminveterinario@gmail.com','202CB962AC59075B964B07152D234B70','VETERINARIO','2025-04-16','avatar.png');
UNLOCK TABLES;

-- Inserción de datos en tabla `veterinarios`
LOCK TABLES `veterinarios` WRITE;
INSERT INTO `veterinarios` VALUES 
(1, TRUE);
UNLOCK TABLES;


-- Insercción de datos en tabla `servicios`

LOCK TABLES `servicios` WRITE;
INSERT INTO `servicios` VALUES
(1,'Peluquería','Sesión completa de peluquería animal que incluye baño, cepillado, corte de pelo, corte de uñas, limpieza de oídos y perfume suave. Atención personalizada en un entorno tranquilo.',40.00,60),
(2,'Vacunación','Servicio de vacunación para animales con control veterinario, aplicación de vacunas obligatorias y asesoramiento sobre el calendario vacunal. Ambiente seguro y trato profesional.',25.00,15),
(3,'Análisis','Servicio de análisis clínicos para animales: extracción de muestras, diagnóstico preventivo y entrega de resultados. Supervisado por veterinario.',35.00,30),
(4,'Implantación de microchip','Implantación de microchip para identificación permanente del animal. Incluye registro oficial y control veterinario. Procedimiento rápido y seguro.',20.00,15),
(5,'Desparasitación','Tratamiento antiparasitario completo para prevenir y eliminar parásitos internos y externos. Incluye revisión veterinaria, aplicación del producto y asesoramiento personalizado.',22.00,45);
UNLOCK TABLES;

-- Insertar todas las franjas generadas en la tabla calendarios
INSERT INTO calendarios (Fecha, HoraInicio, HoraFin, Disponible)
WITH RECURSIVE fechas(dia) AS (
    SELECT CURDATE()
    UNION ALL
    SELECT DATE_ADD(dia, INTERVAL 1 DAY)
    FROM fechas
    WHERE dia < DATE_ADD(CURDATE(), INTERVAL 365 DAY)
),
intervalos AS (
    SELECT 0 AS minutos UNION ALL SELECT 15 UNION ALL SELECT 30 UNION ALL SELECT 45
),
lunes_a_viernes AS (
    SELECT
        dia,
        TIME(SEC_TO_TIME((9*3600) + (h.hora*3600) + (i.minutos*60))) AS hora_inicio,
        TIME(SEC_TO_TIME((9*3600) + (h.hora*3600) + (i.minutos*60) + (15*60))) AS hora_fin
    FROM fechas f
    CROSS JOIN (SELECT 0 AS hora UNION SELECT 1 UNION SELECT 2 UNION SELECT 3 UNION SELECT 4) h
    CROSS JOIN intervalos i
    WHERE WEEKDAY(f.dia) BETWEEN 0 AND 4
),
tarde_lunes_a_viernes AS (
    SELECT
        dia,
        TIME(SEC_TO_TIME((17*3600) + (h.hora*3600) + (i.minutos*60))) AS hora_inicio,
        TIME(SEC_TO_TIME((17*3600) + (h.hora*3600) + (i.minutos*60) + (15*60))) AS hora_fin
    FROM fechas f
    CROSS JOIN (SELECT 0 AS hora UNION SELECT 1 UNION SELECT 2) h
    CROSS JOIN intervalos i
    WHERE WEEKDAY(f.dia) BETWEEN 0 AND 4
),
sabados AS (
    SELECT
        dia,
        TIME(SEC_TO_TIME((10*3600) + (h.hora*3600) + (i.minutos*60))) AS hora_inicio,
        TIME(SEC_TO_TIME((10*3600) + (h.hora*3600) + (i.minutos*60) + (15*60))) AS hora_fin
    FROM fechas f
    CROSS JOIN (SELECT 0 AS hora UNION SELECT 1 UNION SELECT 2 UNION SELECT 3) h
    CROSS JOIN intervalos i
    WHERE WEEKDAY(f.dia) = 5
)
SELECT dia, hora_inicio, hora_fin, TRUE FROM lunes_a_viernes
UNION ALL
SELECT dia, hora_inicio, hora_fin, TRUE FROM tarde_lunes_a_viernes
UNION ALL
SELECT dia, hora_inicio, hora_fin, TRUE FROM sabados;