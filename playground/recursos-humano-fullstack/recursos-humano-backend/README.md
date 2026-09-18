# Recursos Humano Backend (Spring Boot)

Backend REST del sistema de recursos humanos. Expone endpoints CRUD de empleados y persiste datos en MySQL.

## Tecnologías utilizadas

- Java 23
- Spring Boot
- Spring Web MVC
- Spring Data JPA
- MySQL Connector/J
- Lombok
- Maven

## ¿Qué hace este backend?

Permite:

- Listar empleados
- Consultar empleado por id
- Crear empleado
- Actualizar empleado
- Eliminar empleado

## Endpoints principales

Base URL: `http://localhost:8080/rh-app`

- `GET /empleados`
- `GET /empleados/{id}`
- `POST /empleados`
- `PUT /empleados/{id}`
- `DELETE /empleados/{id}`

## Configuración de base de datos

Archivo: `src/main/resources/application.properties`

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/recursos_humanos_db?createDatabaseIfNotExist=true
spring.datasource.username=root
spring.datasource.password=TU_PASSWORD
spring.jpa.hibernate.ddl-auto=update
```

> Ajusta `username` y `password` según tu entorno local.

## Ejecución

```bash
mvn spring-boot:run
```

## Clase destacada: `ResourceNotFoundException`

`ResourceNotFoundException` es una excepción personalizada para recursos inexistentes (por ejemplo, un empleado con id que no existe).

Código clave:

```java
@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
```

### ¿Para qué funciona y por qué se implementa?

- Estandariza errores 404 en toda la API.
- Evita respuestas ambiguas o nulas cuando un recurso no existe.
- Facilita mantenimiento: la intención del error queda clara y reutilizable.

### Ejemplo de uso en el controlador

```java
Empleado employee = this.empleadoService.getEmpleadoById(id);
if (employee == null)
    throw new ResourceNotFoundException("Empleado no encontrado con id " + id);
```

Cuando ocurre, Spring responde automáticamente con HTTP **404 Not Found**.

## Relación con el frontend

Este backend permite que la app React consuma y administre empleados mediante Axios usando la URL:

`http://localhost:8080/rh-app/empleados`
