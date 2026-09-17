# Backend - Inventarios API REST

Este proyecto es el backend de una aplicación full stack para la gestión de inventario. Su función principal es exponer una API REST que permite consultar, crear, actualizar y eliminar productos almacenados en una base de datos MySQL.

## ¿Para qué sirve?

El backend sirve como capa de lógica de negocio y persistencia. Es responsable de:

- recibir peticiones HTTP desde el frontend,
- validar la operación a realizar,
- consultar y guardar información en la base de datos,
- devolver respuestas JSON al cliente.

En este caso, la API gestiona productos con datos como:

- `id`
- `descripcion`
- `precio`
- `existencia`

## Tecnologías utilizadas

- Java 23
- Spring Boot 4.1.1
- Spring Web MVC
- Spring Data JPA
- Hibernate
- MySQL Connector/J
- Lombok
- Maven
- SLF4J + logging de consola
- Base de datos MySQL

### Dependencias básicas del proyecto

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-webmvc</artifactId>
</dependency>

<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>

<dependency>
    <groupId>com.mysql</groupId>
    <artifactId>mysql-connector-j</artifactId>
</dependency>

<dependency>
    <groupId>org.projectlombok</groupId>
    <artifactId>lombok</artifactId>
</dependency>
```

Estas dependencias permiten:

- crear controladores REST y manejar peticiones HTTP,
- trabajar con entidades y persistencia usando JPA/Hibernate,
- conectar la app a MySQL,
- reducir el código repetitivo con Lombok.

## Conceptos estudiados

- Full Stack
- API REST
- Empaquetado JAR vs WAR
- Dependencias básicas de Spring Boot
- Logs y configuración de consola
- Anotaciones Spring
- `@RestController` vs `@Controller`
- `@RequestMapping`
- `@CrossOrigin`
- `@Autowired`
- `@GetMapping`, `@PostMapping`, `@PutMapping`, `@DeleteMapping`
- `@Entity` y mapeo JPA
- `JpaRepository`
- Servicios y repositorios
- `ResponseEntity<T>`
- Manejo de excepciones
- CRUD con base de datos

## Estructura del proyecto

```text
inventarios/
├── src/
│   ├── main/
│   │   ├── java/fm/inventarios/
│   │   │   ├── controller/
│   │   │   │   └── ProductoController.java
│   │   │   ├── service/
│   │   │   │   ├── ProductoService.java
│   │   │   │   └── IProductoService.java
│   │   │   ├── repository/
│   │   │   │   └── ProductoRepository.java
│   │   │   ├── model/
│   │   │   │   └── Producto.java
│   │   │   ├── exception/
│   │   │   │   └── RecursoNoEncontradoExcepcion.java
│   │   │   └── InventariosApplication.java
│   │   └── resources/
│   │       └── application.properties
│   └── test/
│       └── java/fm/inventarios/InventariosApplicationTests.java
├── pom.xml
├── mvnw
├── mvnw.cmd
└── README.md
```

## Desglose de conceptos

### 1. API REST

Una API REST (Representational State Transfer) es una interfaz que permite comunicar aplicaciones mediante HTTP usando recursos y verbos como GET, POST, PUT y DELETE.

En este proyecto, la API expone endpoints para manipular productos. Por ejemplo:

```http
GET http://localhost:8080/inventario-app/getProductos
POST http://localhost:8080/inventario-app/agregarProducto
PUT http://localhost:8080/inventario-app/editarProducto/1
DELETE http://localhost:8080/inventario-app/eliminarProducto/1
```

Esto permite que el frontend Angular pueda hacer peticiones y consumir datos sin recargar la página.

### 2. Empaquetado: JAR vs WAR

En Spring Boot, el empaquetado más común es JAR. Un JAR incluye la aplicación y su servidor embebido (Tomcat, Jetty, Undertow) en un solo artefacto. Es ideal para microservicios, despliegues modernos en la nube y pipelines de CI/CD.

Un WAR se usa cuando la aplicación se despliega en un servidor de aplicaciones externo, como Tomcat o Jetty configurado por separado, o en entornos empresariales legacy que requieren un control más fuerte sobre la infraestructura.

En 2026, la tendencia predominante sigue siendo JAR en proyectos Spring Boot modernos, especialmente en Kubernetes, Docker y despliegues cloud. WAR sigue siendo útil en proyectos legacy o cuando se necesita integrar con un servidor de aplicaciones existente.

### 3. Dependencias básicas

Este proyecto usa varias dependencias esenciales:

- `spring-boot-starter-webmvc`: para crear endpoints REST y manejar HTTP.
- `spring-boot-starter-data-jpa`: para persistencia con JPA/Hibernate.
- `mysql-connector-j`: para conectarse a MySQL.
- `lombok`: para reducir código repetitivo.

### 4. Configuración de logs

En `application.properties` se define el patrón de logging para la consola:

```properties
logging.pattern.console=[%thread] %-5level: %logger - %msg%n
logging.level.org=INFO
```

Explicación:

- `[%thread]`: muestra el hilo de ejecución.
- `%-5level`: muestra el nivel del log (`INFO`, `DEBUG`, `ERROR`).
- `%logger`: nombre del logger.
- `%msg`: texto del mensaje.

Esto ayuda a observar tráfico, errores y eventos importantes durante la ejecución del backend.

### 5. Anotaciones clave

#### `@RestController`

```java
@RestController
public class ProductoController {
}
```

`@RestController` combina `@Controller` + `@ResponseBody`. Esto indica que la clase manejará peticiones HTTP y devolverá directamente JSON como respuesta.

La diferencia con `@Controller` es que `@Controller` normalmente se usa cuando la app retorna vistas HTML (MVC tradicional), mientras que `@RestController` está pensado para APIs REST.

#### `@RequestMapping`

```java
@RequestMapping("inventario-app")
public class ProductoController {
}
```

Define la ruta base del controlador. En este caso, todos los endpoints empiezan por:

```text
http://localhost:8080/inventario-app
```

#### `@CrossOrigin`

```java
@CrossOrigin(value = "http://localhost:4200")
```

Permite que la app Angular, que corre en el puerto 4200, pueda consumir la API del backend. Es el mecanismo de CORS que habilita peticiones cruzadas entre dominios/puertos.

#### `@Autowired`

```java
@Autowired
private ProductoService productoService;
```

Spring inyecta automáticamente la dependencia del servicio para que el controlador pueda usar la lógica de negocio.

### 6. Endpoints del proyecto

El controlador usa anotaciones HTTP específicas:

```java
@GetMapping("/getProductos")
@PostMapping("/agregarProducto")
@GetMapping("/getProducto/{id}")
@PutMapping("/editarProducto/{id}")
@DeleteMapping("/eliminarProducto/{id}")
```

Ejemplo:

```java
@GetMapping("/getProductos")
public List<Producto> getAllProducts() {
    return productoService.getAllProducts();
}
```

Aquí se responde con la lista completa de productos en formato JSON.

### 7. Entidad y base de datos

La entidad `Producto` está mapeada con JPA:

```java
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String descripcion;
    private Double precio;
    private Integer existencia;
}
```

Explicación:

- `@Entity`: indica que la clase representa una tabla en la base de datos.
- `@Id`: define la clave primaria.
- `@GeneratedValue(strategy = GenerationType.IDENTITY)`: genera el valor automáticamente.
- `@Data`, `@NoArgsConstructor`, `@AllArgsConstructor`: Lombok genera getters, setters, constructors y métodos útiles.

### 8. Repositorio y servicio

El repositorio extiende `JpaRepository`:

```java
public interface ProductoRepository extends JpaRepository<Producto, Integer> {
}
```

Esto proporciona métodos CRUD listos para usar, como `save()`, `findAll()`, `findById()`, `deleteById()`, entre otros.

La capa servicio encapsula la lógica:

```java
@Service
public class ProductoService implements IProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    public List<Producto> getAllProducts() {
        return productoRepository.findAll();
    }
}
```

### 9. `ResponseEntity<T>`

`ResponseEntity` permite controlar de forma precisa la respuesta HTTP.

```java
return ResponseEntity.ok(product);
```

También se puede devolver un estado HTTP específico o un objeto con metadatos. En este proyecto se usa para:

- devolver un producto encontrado,
- indicar cuando un registro no existe,
- devolver un JSON con confirmación de eliminación.

Ejemplo:

```java
public ResponseEntity<Map<String, Boolean>> deleteProduct(@PathVariable Integer id) {
    Map<String, Boolean> response = new HashMap<>();
    response.put("eliminado", Boolean.TRUE);
    return ResponseEntity.ok(response);
}
```

### 10. Manejo de excepciones

Cuando un producto no existe, se lanza una excepción personalizada:

```java
throw new RecursoNoEncontradoExcepcion("Producto con id " + id + " no encontrado");
```

Esto permite responder con un error controlado en lugar de dejar que la app falle sin mensaje útil.

## Cómo ejecutar el backend

### Requisitos

- Java 23
- Maven
- MySQL corriendo localmente
- Base de datos llamada `inventario_db`

### Comandos

```bash
cd inventario-fullstack/inventarios
./mvnw spring-boot:run
```

Si se usa Windows:

```bash
mvnw.cmd spring-boot:run
```

## Conclusión

Este backend demuestra de forma clara cómo construir una API REST con Spring Boot para gestionar datos comerciales. La aplicación combina lo mejor de Java, JPA y Spring para crear una capa robusta y escalable que sirve como base para aplicaciones modernas de negocio.
