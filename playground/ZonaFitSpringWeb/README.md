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
- JSF (Jakarta Faces)
- PrimeFaces
- PrimeFlex
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

Actualmente la aplicacion principal es `ZonaFitApplication`. La anotacion `@SpringBootApplication` habilita la configuracion, la auto configuracion y el escaneo de componentes dentro del paquete `fm.zona_fit`.

La aplicacion levanta un servicio web en el puerto `8080` y expone la interfaz con JSF + PrimeFaces.

Para ejecutar:

```powershell
.\mvnw.cmd spring-boot:run
```

## Inyeccion de dependencias

La inyeccion de dependencias permite que Spring cree los objetos y conecte sus dependencias en lugar de que cada clase las construya manualmente.

En este proyecto:

```text
ZonaFitSwing
		-> ZonaFitForm
		-> IClienteServicio / ClienteServicio
		-> ClienteRepository
```

`ClienteServicio` esta registrado con `@Service` y recibe un `ClienteRepository` mediante inyeccion por constructor. `ZonaFitForm` es un `@Component` de Spring y tambien recibe el servicio por constructor para poder inicializar y usar la UI con acceso a datos.

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

### Interfaz grafica web (JSF + PrimeFaces)

La vista principal es `index.xhtml` y se renderiza con JSF. Desde esta UI se listan, agregan, actualizan y eliminan clientes usando componentes de PrimeFaces (`p:dataTable`, `p:dialog`, `p:commandButton`), manteniendo JPA/Hibernate en la capa de persistencia.

### Arranque de aplicacion

`ZonaFitApplication` inicia Spring Boot en modo web y registra la aplicacion JSF/PrimeFaces. Con la configuracion por defecto de Spring Boot, el servicio queda disponible en `http://localhost:8080`.

## Flujo de una operacion

```text
index.xhtml (JSF + PrimeFaces)
	-> IndexControlador (Managed Bean / Controller de vista)
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
spring.datasource.******
spring.jpa.hibernate.ddl-auto=none
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

## Conceptos estudiados

## JSF (Jakarta Faces) y PrimeFaces

JSF (JavaServer Faces, actualmente Jakarta Faces) es un framework para construir aplicaciones web Java basadas en componentes. Usa vistas `.xhtml` y un modelo de ciclo de vida de peticion/respuesta para enlazar la interfaz con beans del servidor.

PrimeFaces es una libreria de componentes UI sobre JSF que acelera la construccion de pantallas web con tablas, dialogos, formularios y mensajes listos para usar.

En este proyecto se usa JoinFaces para integrar JSF/PrimeFaces con Spring Boot, de forma que los beans de Spring (por ejemplo `IndexControlador`) participan en la logica de la vista.

## MVC en este proyecto

La estructura sigue el patron Modelo - Vista - Controlador:

- **Modelo:** `Cliente` representa la entidad de dominio y su persistencia JPA.
- **Vista:** `index.xhtml` define la interfaz con etiquetas JSF y componentes PrimeFaces.
- **Controlador:** `IndexControlador` coordina eventos de la vista (guardar, editar, eliminar, cargar datos) y delega al servicio.

## HTTP y peticiones web

HTTP (HyperText Transfer Protocol) es el protocolo de comunicacion entre navegador y servidor.

- **GET:** solicita recursos o datos (por ejemplo, abrir la pagina principal).
- **POST:** envia datos al servidor (por ejemplo, guardar cambios de un formulario).

## Peticiones AJAX

AJAX permite peticiones asincronas: se actualiza solo una parte de la vista sin recargar toda la pagina.

En PrimeFaces, acciones como `update` en botones/comandos y `PrimeFaces.current().ajax().update(...)` permiten refrescar componentes especificos (tabla y mensajes) despues de guardar o eliminar.

## PrimeFlex

PrimeFlex es una libreria CSS utilitaria para maquetacion y estilos rapidos (flex, espaciado, alineacion, tipografia). En `index.xhtml` se usa para estructurar y alinear bloques visuales de la pagina.
