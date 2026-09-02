# Fundamentos de Bases de Datos

## ¿Qué es una Base de Datos?

Una base de datos es una **colección organizada de datos** almacenados y 
accesibles de forma estructurada.

### Problema sin Base de Datos
- Guardar en archivos: Lento, difícil de buscar, duplicado de datos
- Guardar en memoria: Se pierden al cerrar la aplicación
- Escalabilidad: Imposible con millones de registros

### Solución: Base de Datos Relacional

TABLA: CLIENTES
┌────┬──────────┬───────────┬───────────┐
│ id │ nombre │ apellido │ membresia │
├────┼──────────┼───────────┼───────────┤
│ 1 │ Juan │ Pérez │ 100 │
│ 2 │ María │ García │ 150 │
│ 3 │ Carlos │ López │ 120 │
└────┴──────────┴───────────┴───────────┘

### Conceptos Clave
- **Tabla**: Estructura tipo Excel
- **Fila/Registro**: Datos de una entidad
- **Columna/Campo**: Atributo específico
- **Clave Primaria (PK)**: Identificador único (id)
- **Clave Foránea (FK)**: Referencia a otra tabla
- **Índice**: Acelera búsquedas



