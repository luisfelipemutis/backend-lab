# JDBC - Java Database Connectivity

## ¿Qué es JDBC?

API de Java que permite conectar y ejecutar queries en bases de datos.

## Arquitectura JDBC
┌─────────────┐
│ Java App │
└──────┬──────┘
│ JDBC API
▼
┌──────────────────┐
│ JDBC Driver │ ← mysql-connector-j (en pom.xml)
│ (Manager) │
└──────┬───────────┘
│ JDBC Protocol
▼
┌─────────────┐
│ MySQL │
└─────────────┘


## Pasos principales

1. **Registrar Driver**: `Class.forName("com.mysql.cj.jdbc.Driver");`
2. **Crear Conexión**: `DriverManager.getConnection(url, user, pass)`
3. **Preparar Query**: `connection.prepareStatement(sql)`
4. **Ejecutar**: `executeQuery()` (SELECT) o `executeUpdate()` (INSERT/UPDATE/DELETE)
5. **Procesar Resultados**: Iterar sobre `ResultSet`
6. **Cerrar Recursos**: `connection.close()`


