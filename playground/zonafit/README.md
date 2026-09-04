# 🏋️ Zonafit - Gym Management Laboratory

## Objetivo

Zonafit es un **laboratorio de aprendizaje** donde practicamos conceptos 
de **bases de datos, JDBC y patrones DAO** construyendo un sistema 
real de gestión de clientes de un gimnasio.

## Conceptos Estudiados

Este proyecto integra y enseña:

### 📚 Bases de Datos
- ¿Qué es una BD?
- Tablas, filas, columnas
- Relaciones entre tablas
- Schema SQL

### 🛠 DBMS
- MySQL como gestor de base de datos
- Creación de bases de datos

### 🔍 SQL
- SELECT, INSERT, UPDATE, DELETE
- WHERE, ORDER BY
- JOIN (futuro)

### 🔌 JDBC
- Conexión a BD desde Java
- DriverManager
- PreparedStatement (seguridad)
- ResultSet (procesar resultados)

### 🏗 Patrones de Diseño
- **DAO Pattern**: IClienteDAO + ClienteDAO
- Interfaz vs Implementación
- Separación de responsabilidades

### 📊 ORM (Concepto)
- Mapeo Objeto-Relacional
- Cliente.java ↔ tabla cliente
- (Base para aprender JPA/Hibernate después)

## Estructura del Proyecto
zonafit/
├── dominio/
│ └── Cliente.java ← Entidad (representa tabla)
├── datos/
│ ├── IClienteDAO.java ← Interfaz DAO
│ ├── ClienteDAO.java ← Implementación DAO
│ └── scripts/
│ └── schema.sql ← Definición de BD
├── conexion/
│ └── Conexion.java ← Maneja JDBC
├── presentation/
│ └── ZonaFit.java ← Interfaz usuario (menú)
└── README.md ← Este archivo



## Cómo Usar

### 1. Crear Base de Datos
Ejecutar el script SQL:
```sql
-- Ver archivo: datos/scripts/schema.sql
CREATE DATABASE IF NOT EXISTS zona_fit_db;
USE zona_fit_db;

CREATE TABLE cliente (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(100) NOT NULL,
    apellido VARCHAR(100) NOT NULL,
    membresia INT NOT NULL
);

INSERT INTO cliente VALUES ...
```

### 2. Configurar Conexión (en Conexion.java)
String url = "jdbc:mysql://localhost:3306/zona_fit_db";
String user = "root";
String password = "root";  // Cambiar según tu setup

### 3. Ejecutar
cd backend-lab
mvn clean install
mvn exec:java -Dexec.mainClass="com.felipe.backendlab.playground.zonafit.presentation.ZonaFit"

Operaciones Disponibles
    1. Listar clientes → SELECT * FROM cliente
    2. Buscar por ID → SELECT * FROM cliente WHERE id = ?
    3. Agregar cliente → INSERT INTO cliente (...)
    4. Actualizar cliente → UPDATE cliente SET ...
    5. Eliminar cliente → DELETE FROM cliente WHERE id = ?



## Puntos de Aprendizaje Clave

### IClienteDAO

Define el contrato de operaciones disponibles:

```java
public interface IClienteDAO {
    List<Cliente> getClientes();
    Cliente getClienteById(int id);
    boolean addCliente(Cliente cliente);
    boolean updateCliente(Cliente cliente);
    boolean deleteCliente(int id);
}
```

### ClienteDAO

Implementa cada operación usando JDBC:

```java
public class ClienteDAO implements IClienteDAO {
    @Override
    public List<Cliente> getClientes() {
        // Conexión → PreparedStatement → SQL → ResultSet → Objetos
        String sql = "SELECT * FROM cliente ORDER BY id";
        try (Connection con = getConexion();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            
            while (rs.next()) {
                // Mapear: ResultSet → Cliente (ORM manual)
                Cliente cliente = new Cliente();
                cliente.setId(rs.getInt("id"));
                // ...
            }
        }
    }
}
```

### ZonaFit (Presentación)

Usa el DAO sin saber detalles de JDBC:

```java
IClienteDAO clienteDao = new ClienteDAO();
List<Cliente> clientes = clienteDao.getClientes();  // Simple!
clientes.forEach(System.out::println);
```