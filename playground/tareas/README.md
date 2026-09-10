# Sistema de Tareas

## Descripción general

Este proyecto es una aplicación de escritorio para gestionar tareas utilizando JavaFX como interfaz gráfica y Spring Boot como base de la lógica de negocio y acceso a datos. La aplicación permite crear, consultar, actualizar y eliminar tareas, almacenándolas en una base de datos MySQL mediante JPA/Hibernate.

La idea principal del proyecto es combinar dos tecnologías muy usadas en la industria:

- JavaFX para la capa de presentación y la interfaz visual del usuario.
- Spring Boot para la inyección de dependencias, configuración del proyecto, acceso a datos y administración del ciclo de vida de la aplicación.

En este caso, la base de datos no se crea manualmente con SQL, sino que se configura con propiedades de Spring Boot y se genera automáticamente por Hibernate utilizando anotaciones JPA en la entidad `Tarea`.

## Tecnologías utilizadas

- Java 23
- Spring Boot 4.1.1
- JavaFX 20
- MySQL Connector/J
- Spring Data JPA
- Hibernate
- Maven
- Lombok
- Scene Builder (versión gratuita)
- SLF4J / Logback

## Estructura del proyecto

```text
playground/tareas/
├── .idea/                     # Configuración del IDE
├── .mvn/                      # Wrapper de Maven
├── src/
│   ├── main/
│   │   ├── java/fm/tareas/
│   │   │   ├── TareasApplication.java
│   │   │   ├── controller/
│   │   │   │   └── IndexController.java
│   │   │   ├── model/
│   │   │   │   └── Tarea.java
│   │   │   ├── presentation/
│   │   │   │   └── SistemasTareasFx.java
│   │   │   ├── repository/
│   │   │   │   └── TareaRepository.java
│   │   │   └── service/
│   │   │       ├── ITareaService.java
│   │   │       └── TareaService.java
│   │   └── resources/
│   │       ├── application.properties
│   │       ├── logback-spring.xml
│   │       └── templates/
│   │           └── index.fxml
│   └── test/java/fm/tareas/
│       └── TareasApplicationTests.java
├── HELP.md
├── mvnw
├── mvnw.cmd
├── pom.xml
├── README.md
└── target/                    # Compilación y archivos temporales
```

## Descripción de cada paquete

### 1. `fm.tareas`

Paquete raíz del proyecto. En este nivel se encuentra la clase principal de la aplicación:

- `TareasApplication.java`

Esta clase está anotada con `@SpringBootApplication` y es la entrada del proyecto. Sin embargo, en esta implementación no se ejecuta el arranque web de Spring Boot; la aplicación se lanza con JavaFX.

Código relevante:

```java
@SpringBootApplication
public class TareasApplication {
    public static void main(String[] args) {
        Application.launch(SistemasTareasFx.class, args);
    }
}
```

Esto significa que la aplicación de escritorio se inicia con JavaFX, pero Spring Boot se inicia dentro de la clase `SistemasTareasFx` para crear el contexto de la aplicación y poder inyectar servicios, repositorios y controladores.

### 2. `fm.tareas.presentation`

Aquí se define la ventana principal de la app:

- `SistemasTareasFx.java`

Esta clase extiende `javafx.application.Application`, que es la base para cualquier aplicación JavaFX.

#### ¿Qué hace?

- crea el contexto de Spring con `SpringApplicationBuilder`
- carga la vista `index.fxml`
- conecta el controlador con el contexto de Spring usando `loader.setControllerFactory(applicationContext::getBean)`
- muestra el `Stage` con `stage.show()`

Código principal:

```java
public class SistemasTareasFx extends Application {

    private ConfigurableApplicationContext applicationContext;

    @Override
    public void init() {
        this.applicationContext = new SpringApplicationBuilder(TareasApplication.class).run();
    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(TareasApplication.class.getResource("/templates/index.fxml"));
        loader.setControllerFactory(applicationContext::getBean);
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
        stage.setTitle("Tareas");
        stage.show();
    }
}
```

### 3. `fm.tareas.controller`

Contiene la lógica de interacción entre la vista y la capa de servicio:

- `IndexController.java`

Este controlador es un `@Component` de Spring y además implementa `Initializable` de JavaFX.

#### Funciones principales

- inicializar columnas de la tabla
- cargar la lista de tareas
- guardar una tarea
- actualizar una tarea
- eliminar una tarea
- limpiar el formulario
- cargar los datos de la tarea seleccionada

El controlador inyecta el servicio:

```java
@Autowired
private TareaService tareaService;
```

Esto permite que el `IndexController` use la lógica de negocio sin instanciar manualmente el servicio. Este es un ejemplo claro del patrón de inyección de dependencias.

### 4. `fm.tareas.service`

Aquí está la lógica de negocio del sistema:

- `ITareaService.java`
- `TareaService.java`

`TareaService` implementa la interfaz `ITareaService` y utiliza el repositorio para interactuar con la base de datos.

```java
@Service
public class TareaService implements ITareaService {

    @Autowired
    private TareaRepository tareaRepository;

    @Override
    public List<Tarea> getTareas() {
        return tareaRepository.findAll();
    }
}
```

### 5. `fm.tareas.repository`

Contiene el acceso a datos:

- `TareaRepository.java`

La interfaz extiende `JpaRepository<Tarea, Integer>`, lo que le permite utilizar métodos CRUD de forma automática sin escribir SQL manualmente.

```java
public interface TareaRepository extends JpaRepository<Tarea, Integer> {
}
```

### 6. `fm.tareas.model`

Este paquete representa la entidad persistente del sistema:

- `Tarea.java`

La clase `Tarea` está anotada con `@Entity` y define los atributos que se guardarán en la base de datos.

```java
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Tarea {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idTarea;

    private String nombreTarea;
    private String responsable;
    private String status;
}
```

#### ¿Qué hace cada atributo?

- `idTarea`: identificador de la tarea
- `nombreTarea`: nombre o descripción de la tarea
- `responsable`: persona responsable
- `status`: estado de la tarea

## Configuración de la base de datos

La base de datos se configura en `src/main/resources/application.properties`.

```properties
spring.application.name=tareas
spring.datasource.url=jdbc:mysql://localhost:3306/tareas_db?createDatabaseIfNotExist=true
spring.datasource.username=root
spring.datasource.password=TU_PASSWORD
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.main.web-application-type=none
```

### ¿Qué significa esto?

- `spring.datasource.url`: define la conexión a MySQL
- `createDatabaseIfNotExist=true`: crea la base de datos si no existe
- `spring.jpa.hibernate.ddl-auto=update`: crea o modifica las tablas según las entidades JPA
- `spring.jpa.show-sql=true`: muestra las consultas SQL en consola
- `spring.main.web-application-type=none`: desactiva el servidor web embebido porque la aplicación es de escritorio y no una aplicación web

Esto confirma la idea del enunciado: la creación de la base de datos se realiza desde el proyecto Spring Boot mediante propiedades de configuración y anotaciones JPA, no con scripts SQL manuales.

## Vista y diseño de la interfaz

La interfaz gráfica está definida en `src/main/resources/templates/index.fxml`.

### Elementos principales

- `TableView` para mostrar la lista de tareas
- `TextField` para ingresar nombre, responsable y estatus
- `Button` para agregar, modificar, eliminar y limpiar
- `AnchorPane` como contenedor principal

La vista tiene un `fx:controller` apuntando a:

```xml
fx:controller="fm.tareas.controller.IndexController"
```

Esto permite que el FXML y el controlador estén conectados directamente. El flujo es:

1. JavaFX carga `index.fxml`
2. crea los componentes visuales
3. injeta el controlador desde Spring
4. el controlador inicializa la tabla y carga datos desde la base de datos

## JavaFX: ¿qué es y cómo se utiliza?

JavaFX es una tecnología de Java para construir interfaces gráficas de usuario (GUI) y aplicaciones desktop. No es un framework web como React o Angular, sino una librería de componentes gráficos para escritorio, pensada para crear ventanas, formularios, botones, tablas, menús, estilos visuales y animaciones.

### ¿Es un framework frontend?

No exactamente en el sentido web.

- En la web, el "frontend" se refiere a la interfaz que corre en el navegador.
- JavaFX es una tecnología de interfaz gráfica para aplicaciones Java de escritorio.
- Su enfoque es distinto: no trabaja con HTML/CSS/JS, sino con componentes Java, FXML (XML para interfaces) y estilos CSS propios de JavaFX.

En otras palabras, JavaFX es la capa de presentación para aplicaciones desktop, mientras que Spring Boot puede ser la capa de negocio y acceso a datos.

### ¿Cómo se usa en este proyecto?

En este proyecto se usa JavaFX para construir la pantalla de gestión de tareas:

- una ventana principal (`Stage`)
- una escena con todos los elementos visuales (`Scene`)
- un formulario para ingresar datos (`TextField`)
- una tabla para mostrar tareas (`TableView`)
- botones para guardar, actualizar y eliminar

La estructura visual se define en `index.fxml`, y la interacción con los datos se maneja desde `IndexController`.

Esta separación es muy útil:

- `FXML` define la parte visual
- Java code define la lógica de negocio y eventos
- Spring Boot inyecta los servicios necesarios
- JavaFX solo se encarga de la interfaz de usuario

### Ejemplo en este proyecto

La ventana principal se carga así:

```java
public class SistemasTareasFx extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(TareasApplication.class.getResource("/templates/index.fxml"));
        loader.setControllerFactory(applicationContext::getBean);
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
        stage.setTitle("Tareas");
        stage.show();
    }
}
```

Y en el FXML se declaran los componentes visuales:

```xml
<TableView fx:id="tareaTable" ... />
<TextField fx:id="nombreTareaField" ... />
<Button fx:id="agregarBtn" onAction="#saveTarea" text="Agregar" />
```

### Relación con Spring Boot

Aunque JavaFX no es un framework web, puede integrarse con Spring Boot para construir aplicaciones de escritorio con arquitectura limpia. En este caso:

- JavaFX se encarga de la parte visual
- Spring Boot se encarga de la configuración, servicios, repositorios y acceso a datos
- el contexto de Spring se inicializa cuando se lanza la app de escritorio

Esto permite que el controlador JavaFX obtenga automáticamente una referencia al servicio de tareas:

```java
@Autowired
private TareaService tareaService;
```

### Resumen

JavaFX es la tecnología gráfica de Java para crear interfaces de usuario de escritorio. En este proyecto se usa como front-end de la aplicación, pero no como frontend web; es el frontend de una aplicación desktop basada en Java.

## JavaFX: conceptos clave

### Stage

Un `Stage` representa la ventana principal de la aplicación.

Es el contenedor superior de la interfaz. En esta app, el `Stage` se crea en `SistemasTareasFx.start(Stage stage)`.

```java
stage.setScene(scene);
stage.setTitle("Tareas");
stage.show();
```

### Scene

Una `Scene` representa el contenido visual que se muestra dentro del `Stage`.

En este proyecto se crea así:

```java
Scene scene = new Scene(loader.load());
stage.setScene(scene);
```

### Scene Graph

El `Scene Graph` es el árbol de nodos visuales de JavaFX. Cada componente visual es un nodo. En esta app, el nodo raíz es un `AnchorPane` y dentro de él se agregan componentes como:

- `Label`
- `TextField`
- `Button`
- `TableView`

La estructura visual se ve en `index.fxml` y se acompaña de un árbol de nodos que JavaFX renderiza en pantalla.

### Inyección de objetos en JavaFX

Se usa la posibilidad de inyectar objetos desde Spring en el controlador y, además, se usa el `controller factory` del `FXMLLoader` para recuperar beans del contexto de Spring:

```java
loader.setControllerFactory(applicationContext::getBean);
```

Eso permite que el `IndexController` reciba automáticamente dependencias como `TareaService` mediante `@Autowired`.

Esto es una ventaja importante porque evita instanciar manualmente objetos y facilita la reutilización, pruebas y mantenimiento del código.

## Diferencia entre `implements` y `extends`

Es importante entender la diferencia:

### `implements`

Se usa para implementar una interfaz.

- la clase debe definir los métodos de la interfaz
- sirve para definir un contrato
- puede implementar varias interfaces

Ejemplo del proyecto:

```java
public class TareaService implements ITareaService {
```

Aquí `TareaService` debe implementar los métodos de `ITareaService`.

### `extends`

Se usa para heredar de una clase base.

- la clase hija hereda atributos y comportamientos
- solo se puede extender una clase
- se usa para reutilizar lógica

Ejemplo del proyecto:

```java
public class SistemasTareasFx extends Application {
```

Aquí `SistemasTareasFx` hereda el ciclo de vida de una aplicación JavaFX desde `Application`.

### Comparación rápida

- `implements`: define contrato, “tengo que cumplir esto”
- `extends`: hereda comportamiento, “ya viene con esto”

## Scene Builder

Se utilizó una versión gratuita de Scene Builder para trabajar de manera visual los componentes de JavaFX.

Esto ayuda a:

- diseñar la interfaz sin escribir todo el XML manualmente
- visualizar el layout en tiempo real
- ajustar tamaños, posiciones y estilos visuales
- reducir errores al definir botones, tablas y formularios

La vista `index.fxml` puede editarse desde Scene Builder y luego se integra en el proyecto JavaFX.

## Flujo de ejecución de la aplicación

El siguiente diagrama conceptual explica el funcionamiento del proyecto:

```text
Usuario -> JavaFX Stage -> FXML (index.fxml)
                               |
                               v
                    IndexController
                               |
                               v
                        TareaService
                               |
                               v
                      TareaRepository
                               |
                               v
                          MySQL
```

### Secuencia

1. `TareasApplication.main()` lanza la app JavaFX
2. `SistemasTareasFx.init()` inicia el contexto de Spring
3. `start(Stage)` carga la interfaz FXML
4. `IndexController.initialize()` se ejecuta y carga las tareas
5. `TareaService` consulta datos desde `TareaRepository`
6. `JpaRepository` comunica con la base de datos MySQL
7. Los resultados se muestran en la `TableView`

## Ejemplo de uso con Spring Boot

Aunque la interfaz es de escritorio, la aplicación sigue la arquitectura de Spring Boot y JPA.

### Entidad

```java
@Entity
public class Tarea {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idTarea;

    private String nombreTarea;
    private String responsable;
    private String status;
}
```

### Repositorio

```java
public interface TareaRepository extends JpaRepository<Tarea, Integer> {
}
```

### Servicio

```java
@Service
public class TareaService implements ITareaService {

    @Autowired
    private TareaRepository tareaRepository;

    public void saveTarea(Tarea tarea) {
        tareaRepository.save(tarea);
    }
}
```

### Controlador JavaFX

```java
@Component
public class IndexController implements Initializable {

    @Autowired
    private TareaService tareaService;

    @FXML
    private TableView<Tarea> tareaTable;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadTareas();
    }
}
```

Este patrón es muy útil porque mezcla la robustez del backend con la experiencia de una aplicación de escritorio moderna.

## Cómo ejecutar el proyecto

1. Asegúrate de tener MySQL instalado y corriendo.
2. Crea la base de datos `tareas_db` o deja que Hibernate la cree con `createDatabaseIfNotExist=true`.
3. Actualiza la contraseña en `application.properties`.
4. Ejecuta el proyecto:

```bash
./mvnw spring-boot:run
```

En Windows puede ejecutarse:

```powershell
mvnw.cmd spring-boot:run
```

## Observaciones importantes

- La aplicación es de escritorio, no es una app web.
- El backend se usa como motor de persistencia y lógica, pero la vista se entrega con JavaFX.
- La configuración de MySQL se toma desde propiedades Spring.
- La integración entre JavaFX y Spring es una solución muy útil para aplicaciones de gestión o administración que requieren funcionalidad de escritorio con acceso a datos.

## Resumen

El proyecto `tareas` es una aplicación desktop CRUD basada en JavaFX y Spring Boot. Su estructura está organizada por capas:

- modelo (`model`)
- acceso a datos (`repository`)
- lógica de negocio (`service`)
- presentación (`presentation` y `controller`)
- configuración (`application.properties`)

Esto genera una aplicación limpia, mantenible y fácil de expandir para incluir más entidades, validaciones, reportes o servicios adicionales.
