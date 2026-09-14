# Proyecto: Gestión de Empleados

Este proyecto es un laboratorio de Spring MVC + JPA para estudiar y repasar conceptos básicos de desarrollo web con Java.

La aplicación permite gestionar empleados en una base de datos MySQL, con operaciones de:

- listar empleados
- agregar un nuevo empleado
- editar datos de un empleado
- eliminar un empleado
- navegar entre vistas JSP

La idea principal es entender cómo se comunican las capas de una aplicación web: vista, controlador, servicio, repositorio y base de datos.

## Resumen del proyecto

Es una aplicación de gestión de empleados que:

- muestra la información en una tabla en la vista principal
- permite crear nuevos registros
- permite editar información existente
- permite borrar registros de manera segura mediante un modal
- usa Spring Boot para levantar la aplicación
- usa JPA para interactuar con la base de datos
- usa JSP como tecnología de vista, junto con JSTL y EL

## Estructura del proyecto

```text
playground/empleados/
├── pom.xml
├── README.md
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── fm/empleados/
│   │   │       ├── EmpleadosApplication.java
│   │   │       ├── controller/
│   │   │       │   └── EmpleadoController.java
│   │   │       ├── model/
│   │   │       │   └── Empleado.java
│   │   │       ├── repository/
│   │   │       │   └── EmpleadoRepository.java
│   │   │       └── service/
│   │   │           ├── IEmpleadoService.java
│   │   │           └── EmpleadoService.java
│   │   ├── resources/
│   │   │   └── application.properties
│   │   └── webapp/
│   │       └── WEB-INF/
│   │           └── jsp/
│   │               ├── common/
│   │               │   ├── header.jsp
│   │               │   ├── navbar.jsp
│   │               │   └── footer.jsp
│   │               ├── addEmployee.jsp
│   │               ├── editEmployee.jsp
│   │               ├── index.jsp
│   │               └── index.html
│   └── test/
│       └── java/
│           └── fm/empleados/
│               └── EmpleadosApplicationTests.java
```

## Tecnologías usadas

### Spring Boot
Spring Boot facilita la configuración de una aplicación Java para web, sin tener que configurar manualmente muchísimos elementos del proyecto.

En este caso se usa para:

- levantar la aplicación web
- configurar MVC
- manejar inyección de dependencias
- integrar JPA y MySQL
- manejar controladores y rutas HTTP

### Tomcat embebido
Se usa `tomcat-embed-jasper` para que la aplicación pueda desplegarse con un servidor Tomcat embebido dentro de la app.

Esto permite ejecutar la aplicación sin necesidad de instalar un Tomcat externo y sin desplegar manualmente el proyecto en un servidor aparte.

Ventaja didáctica:

- es más fácil para practicar y aprender
- permite ver cómo Spring Boot integra un contenedor web
- ayuda a comprender la parte de renderizado de vistas JSP

### JSP (JavaServer Pages)
Los JSP son páginas dinámicas que se ejecutan en el servidor y devuelven HTML al navegador.

En este proyecto se usan para renderizar:

- la lista de empleados
- el formulario para agregar empleados
- el formulario para editar empleados
- los componentes compartidos del layout (header, navbar, footer)

### Bootstrap
Bootstrap es una librería de CSS para crear interfaces más limpias y responsivas sin escribir mucho código CSS.

En este proyecto se usa para:

- tablas
- botones
- formularios
- modales
- diseño general de la aplicación

Ejemplo: clases como `btn btn-danger`, `table table-striped`, `form-control`, `modal`, etc.

### JSTL (JavaServer Pages Standard Tag Library)
JSTL es una librería que agrega etiquetas para trabajar con lógica en los JSPs, en lugar de mezclar demasiado Java en el HTML.

Permite cosas como:

- recorrer listas `c:forEach`
- construir URLs con parámetros `c:url`
- pasar parámetros en la URL `c:param`
- manejar condiciones y flujo
- formatear fechas y números

### JSP EL (Expression Language)
El Expression Language (EL) permite acceder a atributos del modelo desde la vista de forma simple.

Ejemplos típicos en este proyecto:

```jsp
${employee.nombre}
${employee.departamento}
${employees}
${application.contextPath}
```

Esto hace que el JSP pueda leer variables que el controlador envió con `ModelMap`, `Model` o `HttpServletRequest`.

El EL se usa para:

- acceder a objetos y propiedades
- recorrer listas
- mostrar valores directamente en la vista
- evitar escribir mucho código Java dentro del HTML

## Estructura lógica del proyecto

### 1. Modelo
La clase `Empleado` representa la entidad persistente de la base de datos.

```java
@Entity
@Data
public class Empleado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idEmpleado;
    private String nombre;
    private String departamento;
    private Double sueldo;
}
```

Conceptos importantes:

- `@Entity`: indica que la clase representa una entidad JPA
- `@Id`: identifica la clave primaria
- `@GeneratedValue(strategy = GenerationType.IDENTITY)`: le indica a la base de datos que genere el valor de la PK automáticamente
- `@Data`: genera getters/setters/toString, entre otros, gracias a Lombok

### 2. Repositorio
`EmpleadoRepository` extiende `JpaRepository<Empleado, Integer>`.

Esto le da acceso a métodos comunes como:

- `findAll()`
- `findById(id)`
- `save(entity)`
- `deleteById(id)`

Es decir, el repositorio encapsula la capa de acceso a datos y permite usar JPA sin escribir SQL manualmente.

### 3. Servicio
`EmpleadoService` implementa la lógica de negocio.

Ejemplo de métodos importantes:

```java
public List<Empleado> getEmpleados()
public Empleado getEmpleadoById(Integer id)
public void saveEmpleado(Empleado empleado)
public void deleteEmpleado(Integer id)
```

Aquí se centraliza la lógica de negocio y se usa el repositorio para persistir o consultar datos.

### 4. Controlador
`EmpleadoController` recibe las peticiones HTTP y decide qué vista devolver o qué acción ejecutar.

Ejemplo de rutas:

- `/` -> lista empleados
- `/agregar` -> formulario para crear
- `/editar` -> formulario para editar
- `/eliminar` -> acción de eliminación

## Anotaciones Spring usadas

### @SpringBootApplication
Se coloca en la clase principal para indicar que la aplicación es de Spring Boot.

Esta anotación incluye la configuración y activación de componentes necesarios para arrancar la app.

### @Controller
Marca una clase como un controlador MVC.

Esto permite que Spring reciba peticiones HTTP y resuelva qué vista retornar.

### @Autowired
Inyecta dependencias automáticamente.

Ejemplo:

```java
@Autowired
private EmpleadoService empleadoService;
```

Spring se encarga de crear y enlazar el bean adecuado.

### @Service
Marca una clase como servicio de negocio.

Es una capa dedicada a la lógica de aplicación, separada del controlador y del repositorio.

### @Component
Es una anotación general para declarar beans de Spring.

`@Service` es una especialización de `@Component`, más semántica para servicios.

### @RequestMapping
`@RequestMapping` define la ruta y el método HTTP asociado a una acción.

Ejemplo:

```java
@RequestMapping(value = "/agregar", method = RequestMethod.GET)
public String showAddEmployeeForm() {
    return "addEmployee";
}
```

Esto significa:

- si llega una petición GET a `/agregar`
- entonces se ejecuta este método
- y se devuelve la vista `addEmployee`

#### ¿Por qué se recomienda usar `@GetMapping` o `@PostMapping`?
Son variantes más específicas y legibles de `@RequestMapping`.

- `@GetMapping` = para peticiones GET
- `@PostMapping` = para peticiones POST
- `@RequestMapping` = más general, sirve para cualquier método HTTP

Sonar puede sugerir reemplazar `@RequestMapping` por versiones más específicas por claridad y prevención de errores, pero no significa que `@RequestMapping` esté "obsoleto". Simplemente es menos explícito y menos recomendable en muchas aplicaciones modernas.

## ModelAttribute
`@ModelAttribute` se usa para enlazar los datos enviados por el formulario a un objeto Java.

Ejemplo:

```java
public String addEmployee(@ModelAttribute("employeeForm") Empleado employeeForm)
```

Esto hace que Spring tome los campos del formulario (nombre, departamento, sueldo) y los convierta automáticamente en un objeto `Empleado`.

Es muy útil porque evita crear manualmente cada campo del formulario y hacer conversiones a mano.

## HttpServletRequest
`HttpServletRequest` es un objeto que representa la petición HTTP del navegador.

Se puede usar para:

- leer parámetros de la URL
- leer encabezados
- obtener datos de sesión
- trabajar con cookies
- acceder a información del request

En proyectos Spring MVC se usa principalmente cuando necesitas leer datos directamente del request, aunque en este laboratorio se usa más `@RequestParam` y `@ModelAttribute` para facilitar el trabajo.

## @RequestParam
`@RequestParam` permite leer un parámetro enviado en la URL o en el formulario.

Ejemplo:

```java
@RequestMapping(value = "/editar", method = RequestMethod.GET)
public String editEmployee(@RequestParam int idEmpleado, ModelMap model) {
    Empleado employee = empleadoService.getEmpleadoById(idEmpleado);
    model.put("employee", employee);
    return "editEmployee";
}
```

Aquí se espera un parámetro `idEmpleado` en la URL, por ejemplo:

```text
/editar?idEmpleado=5
```

## ModelMap
`ModelMap` es usado para enviar datos desde el controlador a la vista.

Ejemplo:

```java
model.put("employees", employees);
```

Luego en JSP se puede leer con EL:

```jsp
${employees}
```

## Flujo de trabajo del proyecto

1. El usuario entra a `/empleados/`
2. El controlador ejecuta `initialize()`
3. Se consulta la lista de empleados desde el servicio
4. El servicio consulta el repositorio JPA
5. La vista `index.jsp` recibe la lista para renderizarla
6. El usuario puede agregar, editar o eliminar empleados
7. El formulario hace `POST` al controlador
8. El controlador guarda o actualiza la entidad
9. Se redirige nuevamente a la vista principal

## JSTL: etiquetas principales usadas

### c:forEach
Permite recorrer una colección de elementos.

Ejemplo:

```jsp
<c:forEach var="employee" items="${employees}">
    ${employee.nombre}
</c:forEach>
```

Se usa para imprimir cada empleado en la tabla.

### c:set
Permite crear una variable dentro del JSP.

Ejemplo:

```jsp
<c:set var="urlDelete">
    <c:url value="${application.contextPath}/eliminar">
        <c:param name="idEmpleado" value="${employee.idEmpleado}"/>
    </c:url>
</c:set>
```

### c:url
Construye una URL de forma segura y dinámica.

Ejemplo:

```jsp
<c:url value="${application.contextPath}/editar">
    <c:param name="idEmpleado" value="${employee.idEmpleado}"/>
</c:url>
```

Esto genera una URL con el parámetro `idEmpleado` dentro de la ruta.

### c:param
Se usa dentro de `c:url` para agregar parámetros de consulta.

Ejemplo:

```jsp
<c:param name="idEmpleado" value="${employee.idEmpleado}"/>
```

Esto equivalente a generar algo como:

```text
/empleados/editar?idEmpleado=3
```

## Consultas SQL y ejemplos para estudiar

Aunque este proyecto se apoya en JPA para manejar la capa de persistencia, es útil recordar las consultas SQL que están detrás de la lógica.

### 1. Listar empleados

```sql
SELECT * FROM empleado;
```

### 2. Buscar un empleado por ID

```sql
SELECT * FROM empleado WHERE id_empleado = 1;
```

### 3. Insertar un nuevo empleado

```sql
INSERT INTO empleado (nombre, departamento, sueldo)
VALUES ('Ana', 'TI', 2500000);
```

### 4. Actualizar empleado

```sql
UPDATE empleado
SET nombre = 'Ana Lucia', departamento = 'Finanzas', sueldo = 3000000
WHERE id_empleado = 1;
```

### 5. Eliminar empleado

```sql
DELETE FROM empleado WHERE id_empleado = 1;
```

### 6. Filtrar por departamento

```sql
SELECT * FROM empleado WHERE departamento = 'TI';
```

### 7. Ordenar empleados por sueldo

```sql
SELECT * FROM empleado ORDER BY sueldo DESC;
```

### 8. Contar empleados

```sql
SELECT COUNT(*) FROM empleado;
```

Estas consultas ayudan a entender la lógica que JPA abstrae con métodos como `findAll()`, `findById()`, `save()`, `deleteById()`, etc.

## Configuración de base de datos

En `application.properties` se configura la conexión a MySQL:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/empleados_db?createDatabaseIfNotExist=true
spring.datasource.username=root
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.hibernate.ddl-auto=update
```

Esto significa que:

- Spring conectará a MySQL
- si la base de datos no existe, la creará
- JPA generará o actualizará automáticamente las tablas según la entidad

## Dudas frecuentes y observaciones del desarrollo

### 1. JSPs y Tomcat embebido
Se trabaja con JSPs en un proyecto Spring Boot usando Tomcat embebido. Esto permite renderizar vistas del lado del servidor sin configurar una instalación externa de Tomcat.

### 2. Bootstrap
Bootstrap aporta estilos prefabricados para hacer la interfaz más estética y organizada, sobre todo para botones, tablas y formularios.

### 3. JSTL
Es muy útil para evitar mezclar demasiado Java dentro del JSP.

### 4. EL (Expression Language)
Permite leer propiedades de objetos y variables del modelo de forma muy limpia, por ejemplo `${employee.nombre}`.

### 5. `@RequestMapping` vs `@GetMapping` y `@PostMapping`
`@RequestMapping` es una anotación general. `@GetMapping` y `@PostMapping` son más específicas y se prefieren para claridad.

No es que `@RequestMapping` sea incorrecto ni obsoleto; simplemente se recomienda usar las versiones más específicas cuando el método HTTP es conocido.

## Conclusión

Este proyecto sirve como base para aprender:

- Spring MVC
- Spring Boot
- JSP
- JSTL
- EL
- JPA
- MySQL
- inyección de dependencias
- arquitectura por capas
- manejo de formularios y rutas HTTP

Es un laboratorio práctico ideal para comprender cómo se estructura una aplicación web Java real, desde la petición del navegador hasta la persistencia en la base de datos.

## Recomendaciones de estudio

- revisar la clase `EmpleadoController`
- comprender cómo el modelo llega a la vista
- estudiar la relación entre `EmpleadoService` y `EmpleadoRepository`
- probar cada operación CRUD desde la UI
- practicar el uso de `c:forEach`, `c:url` y `c:param`
- revisar el archivo `application.properties` para entender la configuración de la base de datos
- comparar `@RequestMapping`, `@GetMapping` y `@PostMapping`
- practicar la diferencia entre un `GET` y un `POST` en el flujo web

## Objetivo didáctico

Este proyecto no es solo una app para administrar empleados; es una base de aprendizaje para entender cómo se estructuran aplicaciones web Java con Spring y cómo se conectan las vistas, controladores, servicios y persistencia.
