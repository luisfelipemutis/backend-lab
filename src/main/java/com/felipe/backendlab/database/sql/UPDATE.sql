-- OPERACIÓN 3: UPDATE (Actualizar datos)

-- Actualizar un cliente
UPDATE cliente 
SET membresia = 300 
WHERE id = 2;

-- ¡ATENCIÓN! Sin WHERE, actualiza TODO
-- UPDATE cliente SET membresia = 500;  -- ❌ Evitar