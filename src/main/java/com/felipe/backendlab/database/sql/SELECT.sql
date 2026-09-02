-- SQL: Structured Query Language
-- Lenguaje para consultar y manipular datos

-- OPERACIÓN 1: SELECT (Consultar/Leer datos)
USE zona_fit_db;

-- Obtener todos los clientes
SELECT * FROM cliente;

-- Obtener solo nombre y apellido
SELECT nombre, apellido FROM cliente;

-- Obtener con condición
SELECT * FROM cliente WHERE membresia > 100;

-- Obtener ordenado
SELECT * FROM cliente ORDER BY nombre ASC;

-- Con LIMIT
SELECT * FROM cliente LIMIT 5;