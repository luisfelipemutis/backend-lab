# EjemploManejoJDBC

Proyecto de ejemplo en **Java con Maven** para trabajar con **JDBC**, operaciones CRUD y manejo básico de transacciones sobre **MySQL**. El diseño busca **alta cohesión y bajo acoplamiento** entre los componentes.

## Tecnologías utilizadas

| Tecnología | Uso en el proyecto |
| --- | --- |
| Java | Implementación de la lógica del ejemplo |
| Maven | Gestión de dependencias y construcción |
| JDBC | Acceso directo a la base de datos |
| MySQL Connector/J | Driver JDBC para MySQL |
| Apache Commons DBCP2 | Implementación del pool de conexiones |
| MySQL | Motor de base de datos |

## Qué se estudia en este proyecto

### Objetivo de diseño de software

Tener **alta cohesión** y **bajo acoplamiento** entre los componentes de software.

- **Alta cohesión:** cada componente realiza únicamente la responsabilidad para la que fue creado; las tareas complementarias se delegan a otros componentes.
- **Bajo acoplamiento:** los cambios en un componente deben impactar lo menos posible a los demás componentes.

## Patrones y conceptos aplicados

### Capa de presentación

- **MVC:** separa la interacción, el flujo y la representación de la información.
- **Front Controller:** centraliza el punto de entrada de una petición del usuario.
- **DTO (Data Transfer Object):** transporta información entre capas.

> Nota: este proyecto es un ejemplo de consola enfocado en JDBC, por lo que los patrones de presentación se documentan como parte de la arquitectura estudiada, aunque aquí no exista una interfaz web completa.

### Capa de servicio

- **Business Delegate:** desacopla la capa cliente de la lógica de negocio o del acceso al servicio.
- **Service Locator:** centraliza la localización de servicios o recursos compartidos.
- **DTO (Data Transfer Object):** permite intercambiar datos entre presentación, servicio y acceso a datos.

> Nota: en este ejemplo la capa de servicio no está desarrollada completamente, pero forma parte de los conceptos estudiados junto con la arquitectura por capas.

### Capa de acceso a datos

- **DAO (Data Access Object):** encapsula las operaciones de acceso a datos. En este proyecto se observa en `PersonaDao`, `PersonaDaoJDBC` y `UsuarioJDBC`.
- **DTO (Data Transfer Object):** representado principalmente por `PersonaDTO` y `Usuario`.
- **JDBC:** se utiliza para ejecutar consultas SQL, inserciones, actualizaciones y eliminaciones.
- **Pool de conexiones:** se configura en la clase `Conexion` para obtener conexiones reutilizables hacia la base de datos.

## Pool de conexiones

Un **pool de conexiones** es un conjunto de conexiones a base de datos que se crean y se mantienen listas para reutilizarse. En lugar de abrir y cerrar una conexión física cada vez que se hace una consulta, la aplicación solicita una conexión al pool, la usa y luego la devuelve.

### Cómo se utiliza en este proyecto

El proyecto usa **JDBC de Java** junto con **Apache Commons DBCP2**:

- La clase `Conexion` crea un `BasicDataSource`.
- Allí se configuran la URL JDBC, el usuario, la contraseña y el tamaño inicial del pool.
- Cada DAO obtiene una conexión mediante `Conexion.getConnection()`.

### Por qué es mejor utilizarlo

- **Mejora el rendimiento:** evita crear una conexión nueva en cada operación.
- **Reduce el costo de acceso:** abrir conexiones a la base de datos es una operación costosa.
- **Facilita la escalabilidad:** varias operaciones pueden reutilizar conexiones disponibles.
- **Centraliza la configuración:** la conexión queda configurada en un solo lugar.

> En este proyecto se usa `BasicDataSource` de Apache Commons DBCP2 como implementación del pool de conexiones sobre JDBC.

## Estructura del proyecto

- `fm.ejemplomanejojdbc`: clases de ejecución del ejemplo (`ManejoPersona` y `ManejoUsuario`).
- `datos`: acceso a datos, conexión y contratos DAO.
- `domain`: objetos DTO del dominio.

## Cómo ejecutar la prueba

1. Crear la base de datos en MySQL.
2. Crear las tablas `persona` y `usuario`.
3. Ajustar credenciales si es necesario en `src\main\java\datos\Conexion.java`.
4. Ejecutar las clases `ManejoPersona` o `ManejoUsuario`.

## Estructura de base de datos para la prueba

```sql
CREATE SCHEMA `db_manejo_jdbc`;

CREATE TABLE `db_manejo_jdbc`.`usuario` (
  `id_usuario` INT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(45) NULL,
  `password` VARCHAR(45) NULL,
  PRIMARY KEY (`id_usuario`)
);

CREATE TABLE `db_manejo_jdbc`.`persona` (
  `id_persona` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NULL,
  `apellido` VARCHAR(45) NULL,
  `email` VARCHAR(45) NULL,
  `telefono` VARCHAR(45) NULL,
  PRIMARY KEY (`id_persona`)
);
```

## Configuración actual de conexión

La conexión está configurada actualmente así:

- Base de datos: `db_manejo_jdbc`
- URL: `jdbc:mysql://localhost/db_manejo_jdbc?useSSL=false&serverTimezone=UTC`
- Usuario: `root`
- Contraseña: `root`

Si vas a probar el proyecto en otro entorno, cambia esos valores en la clase `Conexion`.
