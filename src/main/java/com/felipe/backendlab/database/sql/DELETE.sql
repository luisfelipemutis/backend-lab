-- OPERACIÓN 4: DELETE (Eliminar datos)

-- Eliminar un cliente
DELETE FROM cliente WHERE id = 5;

-- ¡ATENCIÓN! Sin WHERE, elimina TODO
-- DELETE FROM cliente;  -- ❌ Evitar