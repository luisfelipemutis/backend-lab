CREATE DATABASE IF NOT EXISTS zona_fit_db;
USE zona_fit_db;

CREATE TABLE IF NOT EXISTS cliente (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(100) NOT NULL,
    apellido VARCHAR(100) NOT NULL,
    membresia INT NOT NULL
);

INSERT INTO cliente (nombre, apellido, membresia) VALUES
('Juan', 'Pérez', 100),
('María', 'García', 150),
('Carlos', 'López', 120);

-- UPDATES
-- UPDATE `zona_fit_db`.`cliente` SET `apellido` = 'Castle', `membresia` = '300' -- WHERE (`id` = '2');

-- DELETE
-- DELETE FROM `zona_fit_db`.`cliente` WHERE (`id` = '5');