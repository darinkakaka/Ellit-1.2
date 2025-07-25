-- =============================================
-- Base de datos: ellit (versión completa con datos demo)
-- =============================================
CREATE DATABASE IF NOT EXISTS ellit CHARACTER SET utf8mb4 COLLATE utf8mb4_spanish_ci;
USE ellit;

-- =============================================
-- Tabla: usuarios
-- =============================================
CREATE TABLE usuarios (
    id INT AUTO_INCREMENT PRIMARY KEY,
    usuario VARCHAR(50) NOT NULL UNIQUE,
    contrasenia VARCHAR(100) NOT NULL,
    correo VARCHAR(100) NOT NULL UNIQUE,
    edad FLOAT,
    rol VARCHAR(20) NOT NULL DEFAULT 'cliente',
    fecha_registro TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- =============================================
-- Tabla: rutinas
-- =============================================
CREATE TABLE rutinas (
    idRutina INT AUTO_INCREMENT PRIMARY KEY,
    nombreRutina VARCHAR(100) NOT NULL,
    descripcionRutina TEXT,
    objetivoRutina ENUM('Tonificar', 'Cardio', 'Perder grasa', 'Aumentar masa muscular', 'Resistencia') NOT NULL,
    dificultadRutina ENUM('Baja', 'Estándar', 'Avanzada') NOT NULL,
    diasPorSemana TINYINT NOT NULL CHECK (diasPorSemana BETWEEN 1 AND 7),
    horasEstimadas DECIMAL(3,1) NOT NULL,
    fechaCreacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- =============================================
-- Tabla: ejercicios_rutina
-- =============================================
CREATE TABLE ejercicios_rutina (
    id INT AUTO_INCREMENT PRIMARY KEY,
    rutinaId INT NOT NULL,
    diaSemana ENUM('Lunes', 'Martes', 'Miércoles', 'Jueves', 'Viernes', 'Sábado', 'Domingo') NOT NULL,
    nombreEjercicio VARCHAR(100) NOT NULL,
    series SMALLINT NOT NULL,
    repeticiones SMALLINT NOT NULL,
    FOREIGN KEY (rutinaId) REFERENCES rutinas(idRutina) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- =============================================
-- Datos demo: Usuarios
-- =============================================
INSERT INTO usuarios (usuario, contrasenia, correo, edad, rol) VALUES
('admin', 'admin123', 'admin@ellit.com', 30, 'admin'),
('maria', 'mariagym', 'maria@email.com', 28, 'cliente'),
('carlos', 'carlosfit', 'carlos@email.com', 35, 'cliente');

-- =============================================
-- Datos demo: Rutina Principiante (Tonificación)
-- =============================================
INSERT INTO rutinas (nombreRutina, descripcionRutina, objetivoRutina, dificultadRutina, diasPorSemana, horasEstimadas) VALUES
('Rutina Principiante', 'Rutina básica para empezar en el gimnasio y tonificar músculos', 'Tonificar', 'Baja', 3, 1.0);

SET @rutina1 = LAST_INSERT_ID();

INSERT INTO ejercicios_rutina (rutinaId, diaSemana, nombreEjercicio, series, repeticiones) VALUES
(@rutina1, 'Lunes', 'Sentadillas', 3, 12),
(@rutina1, 'Lunes', 'Flexiones de rodillas', 3, 10),
(@rutina1, 'Lunes', 'Plancha abdominal', 3, 20),
(@rutina1, 'Miércoles', 'Peso muerto con mancuernas', 3, 10),
(@rutina1, 'Miércoles', 'Remo con banda elástica', 3, 12),
(@rutina1, 'Viernes', 'Zancadas', 3, 10),
(@rutina1, 'Viernes', 'Elevaciones de talones', 3, 15);

-- =============================================
-- Datos demo: Rutina Intermedia (Cardio)
-- =============================================
INSERT INTO rutinas (nombreRutina, descripcionRutina, objetivoRutina, dificultadRutina, diasPorSemana, horasEstimadas) VALUES
('Cardio Intenso', 'Rutina para mejorar resistencia cardiovascular y quemar grasa', 'Cardio', 'Estándar', 4, 1.5);

SET @rutina2 = LAST_INSERT_ID();

INSERT INTO ejercicios_rutina (rutinaId, diaSemana, nombreEjercicio, series, repeticiones) VALUES
(@rutina2, 'Lunes', 'Burpees', 4, 15),
(@rutina2, 'Lunes', 'Saltos de tijera', 4, 30),
(@rutina2, 'Martes', 'Correr en el lugar', 3, 5),
(@rutina2, 'Martes', 'Escaladores', 3, 20),
(@rutina2, 'Jueves', 'Sentadillas con salto', 4, 12),
(@rutina2, 'Jueves', 'Mountain climbers', 3, 15),
(@rutina2, 'Viernes', 'Box jumps', 3, 10);

-- =============================================
-- Datos demo: Rutina Avanzada (Masa Muscular)
-- =============================================
INSERT INTO rutinas (nombreRutina, descripcionRutina, objetivoRutina, dificultadRutina, diasPorSemana, horasEstimadas) VALUES
('Volumen Extremo', 'Rutina para ganancia muscular avanzada con pesos pesados', 'Aumentar masa muscular', 'Avanzada', 5, 2.0);

SET @rutina3 = LAST_INSERT_ID();

INSERT INTO ejercicios_rutina (rutinaId, diaSemana, nombreEjercicio, series, repeticiones) VALUES
(@rutina3, 'Lunes', 'Press de banca', 5, 6),
(@rutina3, 'Lunes', 'Fondos en paralelas', 4, 8),
(@rutina3, 'Martes', 'Sentadillas con barra', 5, 6),
(@rutina3, 'Martes', 'Peso muerto', 4, 6),
(@rutina3, 'Miércoles', 'Dominadas pronas', 4, 8),
(@rutina3, 'Jueves', 'Press militar', 5, 6),
(@rutina3, 'Viernes', 'Curl de bíceps', 4, 8),
(@rutina3, 'Viernes', 'Extensión de tríceps', 4, 10);

-- =============================================
-- Datos demo: Rutina Full Body (Resistencia)
-- =============================================
INSERT INTO rutinas (nombreRutina, descripcionRutina, objetivoRutina, dificultadRutina, diasPorSemana, horasEstimadas) VALUES
('Full Body Challenge', 'Rutina completa para mejorar resistencia muscular', 'Resistencia', 'Estándar', 3, 1.5);

SET @rutina4 = LAST_INSERT_ID();

INSERT INTO ejercicios_rutina (rutinaId, diaSemana, nombreEjercicio, series, repeticiones) VALUES
(@rutina4, 'Lunes', 'Sentadillas con press de hombros', 4, 12),
(@rutina4, 'Lunes', 'Remo con mancuerna a una mano', 4, 10),
(@rutina4, 'Miércoles', 'Estocadas caminando', 3, 10),
(@rutina4, 'Miércoles', 'Flexiones diamante', 3, 12),
(@rutina4, 'Viernes', 'Plancha con levantamiento de brazo', 3, 8),
(@rutina4, 'Viernes', 'Burpees con salto', 3, 10);

-- =============================================
-- Datos demo: Rutina Quema Grasa
-- =============================================
INSERT INTO rutinas (nombreRutina, descripcionRutina, objetivoRutina, dificultadRutina, diasPorSemana, horasEstimadas) VALUES
('Quema Grasa Express', 'Rutina HIIT para máxima quema de calorías', 'Perder grasa', 'Avanzada', 4, 0.75);

SET @rutina5 = LAST_INSERT_ID();

INSERT INTO ejercicios_rutina (rutinaId, diaSemana, nombreEjercicio, series, repeticiones) VALUES
(@rutina5, 'Lunes', 'Sprints en intervalo', 8, 30),
(@rutina5, 'Lunes', 'Saltos de caja', 4, 12),
(@rutina5, 'Martes', 'Kettlebell swings', 5, 15),
(@rutina5, 'Jueves', 'Battle ropes', 5, 20),
(@rutina5, 'Viernes', 'Burpees con salto', 5, 12);