CREATE DATABASE  DB_Power;
USE DB_Power;

CREATE TABLE Login (
    id_login INT AUTO_INCREMENT,
    Rol VARCHAR(50) NOT NULL,
    Nombre VARCHAR(100) NOT NULL,
    Correo VARCHAR(100) NOT NULL,
    Contrasena VARCHAR(255) NOT NULL,
    PRIMARY KEY (id_login)
);

ALTER TABLE Login
MODIFY Rol ENUM('Administrador','Usuario');


INSERT INTO Login
(Rol,Nombre,Correo,Contrasena)
VALUES
('Administrador',
 'admin',
 'admin@power.com',
 '1234');

CREATE TABLE Reparacion_Mantenimiento (
    id_mantenimiento INT AUTO_INCREMENT,
    id_login INT,
    estado_maquina VARCHAR(100),
    Componentes_danados VARCHAR(255),
    utima_modificacion DATETIME,
    PRIMARY KEY (id_mantenimiento),
    CONSTRAINT fk_mantenimiento_login 
        FOREIGN KEY (id_login) REFERENCES Login(id_login)
        ON DELETE CASCADE ON UPDATE CASCADE
);


CREATE TABLE Datos (
    id_datos INT AUTO_INCREMENT,
    id_login INT,
    edad INT,
    peso_lb DECIMAL(5,2),
    ritmo_cardiaco INT,
    punteo_usuario INT,
    fecha_registro DATETIME,
    PRIMARY KEY (id_datos),
    CONSTRAINT fk_datos_login 
        FOREIGN KEY (id_login) REFERENCES Login(id_login)
        ON DELETE CASCADE ON UPDATE CASCADE
);

ALTER TABLE Datos
MODIFY fecha_registro DATETIME
DEFAULT CURRENT_TIMESTAMP;
SELECT id_datos,
       id_login,
       fecha_registro
FROM Datos
ORDER BY id_datos DESC;


CREATE TABLE Rutinas (
    id_rutina INT AUTO_INCREMENT,
    nombre VARCHAR(100) NOT NULL,
    imagen VARCHAR(100) NOT NULL,
    nivel ENUM('Principiante','Intermedio','Avanzado') NOT NULL,
    PRIMARY KEY (id_rutina)
);

CREATE TABLE Comidas (
    id_comida INT AUTO_INCREMENT,
    nombre VARCHAR(100) NOT NULL,
    imagen VARCHAR(100) NOT NULL,
    nivel ENUM('Principiante','Intermedio','Avanzado') NOT NULL,
    PRIMARY KEY (id_comida)
);

CREATE TABLE Configuracion (
    id_configuracion INT AUTO_INCREMENT,
    nivel ENUM('Principiante','Intermedio','Avanzado') NOT NULL UNIQUE,
    id_rutina INT,
    id_comida INT,
    PRIMARY KEY (id_configuracion),
    CONSTRAINT fk_config_rutina
        FOREIGN KEY (id_rutina) REFERENCES Rutinas(id_rutina)
        ON DELETE SET NULL ON UPDATE CASCADE,
    CONSTRAINT fk_config_comida
        FOREIGN KEY (id_comida) REFERENCES Comidas(id_comida)
        ON DELETE SET NULL ON UPDATE CASCADE
);

INSERT INTO Rutinas (nombre, imagen, nivel) VALUES
('Flexiones Arqueras2', 'Flexiones_Arqueras2.png', 'Principiante'),
('Press Militar', 'Press_Militar2.png', 'Principiante'),
('Flexiones Declinadas', 'Flexiones_Declinadas.png', 'Principiante'),

('Flexiones Pecho', 'Flexiones_Pecho.png', 'Intermedio'),
('Remo Mancuernas', 'Remo_Mancuernas.png', 'Intermedio'),
('Press Militar_Pie', 'Press_Militar_Pie.png', 'Intermedio'),

('Flexiones Arqueras', 'Flexiones_Arqueras.png', 'Avanzado'),
('Press Militar', 'Press_Militar.png', 'Avanzado'),
('Flexiones Declinadas2', 'Flexiones_Declinadas2.png', 'Avanzado');

SELECT * FROM Rutinas;

INSERT INTO Comidas (nombre, imagen, nivel) VALUES
-- PRINCIPIANTE
('Salsa Champiñones', 'Salsa_Champiñones.png', 'Principiante'),
('Filete de Salmón', 'Filete_Salmon.png', 'Principiante'),
('Champiñones con Arroz', 'Champiñones_Arroz.png', 'Principiante'),

-- INTERMEDIO
('Ensalada de Atún', 'Ensalada_Atun.png', 'Intermedio'),
('Carne de Res', 'Carne_De_Res.png', 'Intermedio'),
('Atún con Aguacate', 'Atun_Aguacate.png', 'Intermedio'),

-- AVANZADO
('Camote Tostado', 'Camote_Tostado.png', 'Avanzado'),
('Pancakes', 'Pancakes.png', 'Avanzado'),
('Tortilla de Espinacas', 'Tortilla_Espinacas.png', 'Avanzado');

SELECT * FROM Comidas;