# ZonaFitSpring

Proyecto de practica para estudiar Spring Boot mediante una aplicacion de gestion de clientes de un gimnasio.

## Objetivos

- Comprender Spring Framework y Spring Boot.
- Estudiar inversion de control e inyeccion de dependencias.
- Persistir datos con JPA, Hibernate y MySQL.
- Separar responsabilidades mediante capas.
- Practicar pruebas y configuracion de aplicaciones Spring.

## Tecnologias

- Java 17
- Spring Boot 4.1.1
- Spring Data JPA
- JPA
- Hibernate
- MySQL
- Lombok
- Maven
- JUnit
- SLF4J y Logback

## Spring Framework

Spring Framework es un conjunto de modulos, clases, interfaces y mecanismos para construir aplicaciones Java mantenibles, reutilizables y desacopladas.

Entre sus capacidades se encuentran:

- Inyeccion de dependencias.
- Inversion de control.
- Configuracion de componentes.
- Acceso a datos y transacciones.
- Desarrollo web.
- Seguridad e integracion con otros servicios.

## Spring Boot

Spring Boot simplifica el uso de Spring Framework mediante auto configuracion, dependencias agrupadas en starters y convenciones que reducen la configuracion inicial.

La clase `ZonaFitApplication` inicia la aplicacion. La anotacion `@SpringBootApplication` habilita la configuracion, la auto configuracion y el escaneo de componentes dentro del paquete `fm.zona_fit`.

La aplicacion implementa `CommandLineRunner`, por lo que muestra un menu de consola despues de iniciar el contexto de Spring. Actualmente no es una API REST y se ejecuta con:

```powershell
.\mvnw.cmd spring-boot:run
```

## Inyeccion de dependencias

La inyeccion de dependencias permite que Spring cree los objetos y conecte sus dependencias en lugar de que cada clase las construya manualmente.

En este proyecto:

```text
ZonaFitApplication
		-> IClienteServicio
		-> ClienteServicio
		-> ClienteRepository
```

`ClienteServicio` esta registrado con `@Service` y recibe un `ClienteRepository` mediante `@Autowired`. `ClienteRepository` es creado automaticamente por Spring Data JPA.

Actualmente se utiliza inyeccion sobre un atributo. Un proximo ejercicio recomendado es reemplazarla por inyeccion mediante constructor, que hace las dependencias obligatorias y facilita las pruebas.

## JPA e Hibernate

JPA, o Java Persistence API, es una especificacion para mapear objetos Java a tablas de una base de datos. JPA define contratos como `@Entity`, `@Id` y los repositorios, pero no ejecuta por si misma la persistencia.

Hibernate es el proveedor JPA utilizado internamente por Spring Boot. Se encarga de generar y ejecutar las operaciones SQL a partir del modelo de objetos.

La clase `Cliente` es una entidad JPA:

```java
@Entity
public class Cliente {
}
```

- `@Entity`: indica que la clase se persiste en la base de datos.
- `@Id`: identifica la clave primaria.
- `@GeneratedValue`: delega la generacion del ID a la base de datos.
- `JpaRepository`: ofrece operaciones CRUD sin escribir SQL basico.

## Capas del proyecto

### Modelo

`Cliente` representa los datos del dominio y esta mapeado como una entidad JPA. Lombok genera constructores, getters, setters, `toString`, `equals` y `hashCode`.

### Repositorio

`ClienteRepository` extiende `JpaRepository<Cliente, Integer>`. Spring Data genera su implementacion y proporciona operaciones como `findAll`, `findById`, `save` y `deleteById`.

### Servicio

`IClienteServicio` define el contrato de operaciones y `ClienteServicio` implementa la logica de acceso a clientes. Esta separacion permite cambiar la implementacion sin acoplar la aplicacion directamente al repositorio.

### Aplicacion

`ZonaFitApplication` inicia Spring Boot, recibe el servicio inyectado y contiene el menu de consola para listar, consultar, crear, modificar y eliminar clientes.

## Flujo de una operacion

```text
Menu de consola
	-> ClienteServicio
	-> ClienteRepository
	-> Spring Data JPA
	-> Hibernate
	-> MySQL
```

## Configuracion de MySQL

La configuracion se encuentra en `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/zona_fit_db
spring.datasource.username=root
spring.datasource.password=root
spring.jpa.hibernate.ddl-auto=none
spring.main.web-application-type=none
```

Antes de ejecutar el proyecto, MySQL debe estar iniciado y la base de datos debe existir:

```sql
CREATE DATABASE zona_fit_db;

CREATE TABLE IF NOT EXISTS cliente (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(100) NOT NULL,
    apellido VARCHAR(100) NOT NULL,
    membresia INT NOT NULL
);

```

La propiedad `spring.jpa.hibernate.ddl-auto=none` indica que Hibernate no creara ni modificara tablas automaticamente. Por tanto, las tablas deben existir previamente o crearse mediante scripts de base de datos.

No guardes contrasenas reales en el repositorio. Para un entorno real utiliza variables de entorno o perfiles como `application-dev.properties` y `application-prod.properties`.

## Logging

El proyecto utiliza SLF4J como API de logging y Logback como implementacion. La configuracion esta en `src/main/resources/logback-spring.xml` y define un appender para mostrar mensajes en la consola.

## Pruebas

`ZonaFitApplicationTests` utiliza `@SpringBootTest` y el metodo `contextLoads()` para comprobar que el contexto de Spring pueda iniciar.

Como la aplicacion usa JPA y MySQL, las pruebas de contexto necesitan una base de datos disponible y correctamente configurada. Una mejora posterior es usar H2 para pruebas aisladas.

Ejecutar las pruebas:

```powershell
.\mvnw.cmd clean test
```

Compilar el proyecto:

```powershell
.\mvnw.cmd clean package
```

Ejecutar el JAR generado:

```powershell
java -jar .\target\zona_fit-0.0.1-SNAPSHOT.jar
```

## Temas pendientes de estudio

- Inyeccion mediante constructor.
- Transacciones con `@Transactional`.
- Consultas derivadas y `@Query`.
- Relaciones JPA: `@OneToMany`, `@ManyToOne` y `@ManyToMany`.
- DTOs y conversion entre entidades y DTOs.
- Validacion con `@Valid`, `@NotNull` y `@Size`.
- Controladores REST con `@RestController`.
- Manejo global de errores con `@ControllerAdvice`.
- Perfiles y variables de entorno.
- Pruebas unitarias, de repositorio y de integracion.
- Base de datos H2 para pruebas.
- Paginacion y ordenamiento.
- Migraciones con Flyway o Liquibase.
- Spring Security y Actuator.


