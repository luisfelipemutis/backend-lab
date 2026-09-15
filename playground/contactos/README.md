# Proyecto Contactos

Este proyecto es una aplicación web CRUD para gestionar contactos, desarrollada con Java y Spring Boot. Su objetivo es permitir crear, listar, editar y eliminar registros de contacto desde una interfaz web simple y funcional, usando una base de datos relacional.

La aplicación se apoya en una arquitectura MVC (Modelo-Vista-Controlador), donde:

- El modelo representa los datos del contacto.
- La vista se construye con HTML + Thymeleaf.
- El controlador procesa las peticiones HTTP.
- El servicio encapsula la lógica de negocio.
- El repositorio comunica con la base de datos mediante JPA.

## Resumen de tecnologías utilizadas

El proyecto usa principalmente estas tecnologías:

- Java 23
- Spring Boot 4.1.1
- Spring Web MVC
- Spring Data JPA
- Thymeleaf
- MySQL
- Lombok
- Maven
- Bootstrap
- HTML / CSS
- SLF4J + Logback

En conjunto, estas tecnologías permiten crear una aplicación web moderna, fácil de mantener y con una capa de persistencia robusta. La aplicación no expone una API REST; en cambio, usa renderizado del lado del servidor con Thymeleaf y formularios HTML.

## 1. Java

Java es el lenguaje principal de la aplicación. Es un lenguaje orientado a objetos, robusto y ampliamente usado en entornos empresariales.

Este proyecto está configurado para usar Java 23 (`<java.version>23</java.version>` en `pom.xml`), por lo que se recomienda ejecutar el proyecto con un JDK 23 o superior. En esta máquina, la validación con Java 17 falló con `release version 23 not supported`, lo que confirma la necesidad de ese JDK.

En este proyecto, Java se usa para definir:

- la entidad `Contacto`
- los controladores `@Controller`
- la lógica de negocio en servicios
- la conexión con la base de datos

Ejemplo de la entidad principal:

```java
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Contacto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nombre;
    private String celular;
    private String email;
}
```

Aquí se observa que la clase `Contacto` forma el modelo de datos del sistema. Tiene atributos básicos como nombre, celular y email.

## 2. Spring Boot

Spring Boot es un framework de Java que facilita la creación de aplicaciones listas para producción con mínima configuración. En este proyecto, Spring Boot centraliza la configuración del proyecto y permite arrancar la aplicación con un solo comando o la clase principal.

La clase principal es:

```java
@SpringBootApplication
public class ContactosApplication {
    public static void main(String[] args) {
        SpringApplication.run(ContactosApplication.class, args);
    }
}
```

La anotación `@SpringBootApplication` activa la configuración automática de Spring Boot y prepara la aplicación para usar MVC, JPA, componentes, etc.

### ¿Qué aporta Spring Boot en este proyecto?

- Configuración automática de dependencias.
- Creación de la aplicación web con tomcat embebido.
- Integración con JPA, MVC y Thymeleaf.
- Menos archivos de configuración manual.

## 3. Spring Web MVC

Spring MVC es la parte de Spring que se encarga de manejar peticiones HTTP y devolver vistas o respuestas. Este proyecto usa MVC para manejar rutas como `/`, `/agregar`, `/editar/{id}` y `/eliminar/{id}`.

Ejemplo del controlador:

```java
@Controller
public class ContactoController {

    @Autowired
    private ContactoService contactoService;

    @GetMapping("/")
    public String started(ModelMap model) {
        List<Contacto> contacts = contactoService.getAllContacts();
        model.put("contacts", contacts);
        return "index";
    }

    @PostMapping("/agregar")
    public String saveContact(@ModelAttribute("ContactForm") Contacto contact) {
        contactoService.saveContact(contact);
        return "redirect:/";
    }
}
```

### Anotaciones clave

- `@Controller`: indica que la clase es un controlador web.
- `@GetMapping`: maneja peticiones GET.
- `@PostMapping`: maneja peticiones POST.
- `@ModelAttribute`: vincula los datos enviados en un formulario con un objeto Java.
- `@PathVariable`: extrae un valor desde la URL.

En resumen, Spring MVC convierte la interacción del navegador con la aplicación en objetos Java y rutas claramente definidas.

## 4. Thymeleaf

Thymeleaf es una librería de plantillas para Java y Spring. Se usa para generar vistas HTML desde el lado del servidor, integrándose muy bien con Spring MVC.

La sintaxis de Thymeleaf permite mezclar HTML normal con expresiones especiales, por ejemplo:

```html
<html lang="en" xmlns:th="http://www.thymeleaf.org">
```

La línea anterior usa `xmlns:th`, que significa XML namespace. Es decir, le indica al navegador/HTML que estamos usando el namespace de Thymeleaf para poder interpretar atributos como:

- `th:text`
- `th:href`
- `th:field`
- `th:each`

### Ejemplos reales del proyecto

#### Listar contactos

```html
<tr th:each="contact : ${contacts}">
    <td th:text="${contact.nombre}"></td>
    <td th:text="${contact.celular}"></td>
    <td th:text="${contact.email}"></td>
</tr>
```

Esto hace que, por cada elemento de la lista `contacts`, se genere una fila en la tabla.

#### Enlaces dinámicos

```html
<a th:href="@{/editar/{id}(id=${contact.id})}">Editar</a>
```

Aquí, Thymeleaf construye la URL completa con el ID del contacto.

#### Campos del formulario vinculados al modelo

```html
<input type="hidden" th:field="*{id}" />
```

Esto enlaza el campo con la propiedad `id` del objeto `contact`.

### ¿Por qué es importante `xmlns:th`?

El atributo `xmlns` define un namespace XML. En otras palabras, el namespace de Thymeleaf prepara el documento para reconocer sus etiquetas y atributos. Sin `xmlns:th`, el navegador no sabría qué hacer con atributos como `th:href`, `th:text` o `th:field`.

### Concepto: HTTP es stateless y el id oculto

El protocolo HTTP es stateless, lo que significa que no guarda el estado entre una petición y otra. Cada petición es independiente y no “recuerda” datos del usuario ni del servidor automáticamente.

Por eso, al editar un contacto se usa un campo oculto con el ID:

```html
<input type="hidden" th:field="*{id}"/>
```

Cuando el usuario envía el formulario, el navegador manda ese `id` junto con el resto de los datos. Así el backend sabe qué contacto debe actualizarse. Si no se enviara el ID, el servidor no sabría cuál registro editar.

Este detalle es muy importante en una aplicación web con formularios y sesiones inexistentes.

## 5. Spring Data JPA

Spring Data JPA permite trabajar con bases de datos relacionales usando entidades Java y repositorios. En este proyecto, la capa de persistencia está encapsulada en `ContactoRepository`.

```java
public interface ContactoRepository extends JpaRepository<Contacto, Integer> {
}
```

Esto le da al proyecto acceso automático a métodos como:

- `findAll()`
- `findById(id)`
- `save(contacto)`
- `deleteById(id)`

### ¿Cómo se usa en servicio?

```java
@Service
public class ContactoService implements IContactoService {

    @Autowired
    private ContactoRepository contactoRepository;

    public List<Contacto> getAllContacts() {
        return contactoRepository.findAll();
    }
}
```

Spring Data JPA reduce el código repetitivo de acceso a base de datos y permite manejar la entidad `Contacto` de forma natural.

## 6. MySQL

La base de datos del proyecto es MySQL. La configuración se encuentra en `application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/contactos_db?createDatabaseIfNotExist=true
spring.datasource.username=root
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

### ¿Qué hace esto?

- `jdbc:mysql://localhost:3306/contactos_db`: conecta con la base de datos MySQL.
- `createDatabaseIfNotExist=true`: crea la base de datos si no existe.
- `ddl-auto=update`: actualiza el esquema según la entidad `Contacto`.
- `show-sql=true`: muestra en consola las consultas SQL ejecutadas.

La persistencia se hace sobre una tabla que se genera automáticamente a partir de la entidad Java.

## 7. Lombok

Lombok es una librería que reduce el boilerplate (código repetitivo) en Java. En este proyecto se usa para generar constructores, getters, setters y métodos como `toString`, `equals` y `hashCode`.

Ejemplo:

```java
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Contacto { ... }
```

Sin Lombok, tendríamos que escribir manualmente muchos métodos. Con él, el código queda más limpio y legible.

## 8. Maven

Maven es la herramienta de automatización y gestión de dependencias del proyecto. Se usa para:

- descargar librerías
- compilar la aplicación
- ejecutar pruebas
- empaquetar el proyecto

El archivo principal es `pom.xml`.

Ejemplo de dependencia esencial:

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-thymeleaf</artifactId>
</dependency>
```

Gracias a Maven, el proyecto puede declarar sus dependencias de forma ordenada y reproducible.

## 9. Bootstrap

Bootstrap es un framework de CSS para construir interfaces web con estilo atractivo, responsive y rápido.

En este proyecto se usa para los formularios y la tabla de contactos:

```html
<table class="table table-striped table-hover align-middle table-bordered">
```

También se usan botones tipo:

```html
<button class="btn btn-warning btn-sm me-3">Editar Contacto</button>
```

Esto permite que la interfaz se vea más profesional sin escribir mucho CSS personalizado.

## 10. HTML y CSS

La capa visual del proyecto está construida con HTML y CSS, usando Bootstrap como ayuda visual. Las páginas se encuentran en `src/main/resources/templates`:

- `index.html`
- `addContact.html`
- `editContact.html`
- `fragments/header.html`
- `fragments/navbar.html`
- `fragments/footer.html`

El flujo es:

1. El controlador devuelve el nombre de la vista.
2. Thymeleaf renderiza la plantilla HTML.
3. El navegador muestra la página al usuario.

## 11. SLF4J y Logback

La aplicación registra eventos y trazas con SLF4J y Logback. Esto facilita depurar la app y observar el flujo de acciones.

Ejemplo:

```java
private static final Logger log = LoggerFactory.getLogger(ContactoController.class);
```

Y luego:

```java
log.info("Agregando contacto : {}", contact);
```

Esto permite ver en consola eventos importantes como creación, edición o eliminación de contactos.

## Arquitectura general del proyecto

La aplicación sigue una estructura simple:

- `controller/`: controla las rutas web
- `model/`: entidades del dominio
- `service/`: lógica de negocio
- `repository/`: acceso a datos con JPA
- `resources/templates/`: vistas HTML con Thymeleaf
- `resources/application.properties`: configuración del proyecto

## Flujo de funcionamiento

1. El usuario entra a la aplicación desde el navegador.
2. El controlador recibe la petición HTTP.
3. El servicio consulta o guarda datos en la base de datos.
4. Thymeleaf genera la vista HTML con los resultados.
5. El navegador muestra la interfaz.

Ejemplo de flujo de edición:

- Se abre `/editar/{id}`
- El controlador busca el contacto por ID
- Se devuelve la vista `editContact.html`
- El usuario modifica los datos
- El formulario se envía al backend
- Se guarda usando `saveContact(...)`
- El sistema redirige a `/`

## Conclusión

Este proyecto demuestra de manera clara cómo se combinan varias tecnologías para construir una aplicación web Java moderna:

- Java y Spring Boot como base del backend.
- Spring MVC para manejar rutas y formularios.
- Thymeleaf para renderizar HTML con lógica del servidor.
- JPA para persistencia con MySQL.
- Bootstrap para mejorar la interfaz.
- Maven para dependencias y compilación.

La combinación de estas tecnologías hace que el proyecto sea simple, legible y perfecto como ejemplo de desarrollo web con Java y Spring.

## Cómo ejecutar el proyecto

Desde la raíz del proyecto:

```bash
./mvnw spring-boot:run
```

Asegúrate de tener MySQL corriendo y una base de datos llamada `contactos_db` o que la configuración de `createDatabaseIfNotExist=true` pueda crearla automáticamente.

Luego abre la aplicación en:

```text
http://localhost:8080
```

Si el proyecto se ejecuta con un puerto distinto, revisa `application.properties`.
